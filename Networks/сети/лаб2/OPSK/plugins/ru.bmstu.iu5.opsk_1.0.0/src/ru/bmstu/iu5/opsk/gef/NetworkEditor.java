/*
 * Created on May 4, 2005
 * 
 * Copyright BMSTU
 */
package ru.bmstu.iu5.opsk.gef;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.Iterator;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.KeyHandler;
import org.eclipse.gef.KeyStroke;
import org.eclipse.gef.dnd.TemplateTransferDragSourceListener;
import org.eclipse.gef.dnd.TemplateTransferDropTargetListener;
import org.eclipse.gef.editparts.ScalableRootEditPart;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.gef.requests.SimpleFactory;
import org.eclipse.gef.tools.ConnectionCreationTool;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.CopyTemplateAction;
import org.eclipse.gef.ui.actions.ZoomInAction;
import org.eclipse.gef.ui.actions.ZoomOutAction;
import org.eclipse.gef.ui.palette.PaletteViewer;
import org.eclipse.gef.ui.palette.PaletteViewerProvider;
import org.eclipse.gef.ui.parts.GraphicalEditorWithPalette;
import org.eclipse.gef.ui.parts.GraphicalViewerKeyHandler;
import org.eclipse.gef.ui.parts.TreeViewer;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.util.TransferDropTargetListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

import ru.bmstu.iu5.opsk.Messages;
import ru.bmstu.iu5.opsk.gef.model.NetworkElement;
import ru.bmstu.iu5.opsk.gef.outline.TreeRootModel;
import ru.bmstu.iu5.opsk.path.IShortestPathPage;
import ru.bmstu.iu5.opsk.path.Path;
import ru.bmstu.iu5.opsk.path.ShortestPathPage;
import ru.bmstu.iu5.opsk.rpc.FileEditorInput;

/**
 * Main class - editor for network model
 * 
 * @author Egorova Olga
 */
public class NetworkEditor extends GraphicalEditorWithPalette implements ISelectionListener {

	private NetworkElement diagram;

	private PaletteRoot root;

	private KeyHandler sharedKeyHandler;

	private boolean savePreviouslyNeeded = false;
	
	private FileEditorInput input = null;
	
	private boolean modelIsHiglighted = false;
	
	public NetworkEditor() {
		DefaultEditDomain defaultEditDomain = new DefaultEditDomain(this);
		defaultEditDomain.setActiveTool(new ConnectionCreationTool());
		setEditDomain(defaultEditDomain);
		
	}

	/**
	 * @see org.eclipse.gef.commands.CommandStackListener#commandStackChanged(java.util.EventObject)
	 */
	public void commandStackChanged(EventObject event) {
		if (isDirty()) {
			if (!savePreviouslyNeeded()) {
				setSavePreviouslyNeeded(true);
				firePropertyChange(IEditorPart.PROP_DIRTY);
			}
		} else {
			setSavePreviouslyNeeded(false);
			firePropertyChange(IEditorPart.PROP_DIRTY);
		}
		// Here we should also recalculate throughput of the models connections
		diagram.calculate();
		super.commandStackChanged(event);
	}
	

	/**
	 * @see org.eclipse.gef.ui.parts.GraphicalEditor#createActions()
	 */
	protected void createActions() {
		super.createActions();
		ActionRegistry registry = getActionRegistry();
		IActionBars bars = ((IEditorSite)getSite()).getActionBars();
		String id = ActionFactory.UNDO.getId();
		bars.setGlobalActionHandler(id, registry.getAction(id));
		id = ActionFactory.REDO.getId();
		bars.setGlobalActionHandler(id, registry.getAction(id));
		id = ActionFactory.DELETE.getId();
		bars.setGlobalActionHandler(id, registry.getAction(id));
		id = ActionFactory.SELECT_ALL.getId();
		bars.setGlobalActionHandler(id, registry.getAction(id));
		IAction action = new CopyTemplateAction(this);
		registry.registerAction(action);
		bars.setGlobalActionHandler(ActionFactory.PRINT.getId(), registry.getAction(ActionFactory.PRINT.getId()));
	}

	/**
	 * @see org.eclipse.gef.ui.parts.GraphicalEditor#configureGraphicalViewer()
	 */
	protected void configureGraphicalViewer() {
		super.configureGraphicalViewer();
		GraphicalViewer viewer = getGraphicalViewer();
		ScalableRootEditPart root = new ScalableRootEditPart();
		getGraphicalViewer().setRootEditPart(root);
		IAction zoomIn = new ZoomInAction(root.getZoomManager());
		IAction zoomOut = new ZoomOutAction(root.getZoomManager());
		getActionRegistry().registerAction(zoomIn);
		getActionRegistry().registerAction(zoomOut);
		getSite().getKeyBindingService().registerAction(zoomIn);
		getSite().getKeyBindingService().registerAction(zoomOut);

		getGraphicalViewer().setEditPartFactory(new NetworkPartFactory());
		viewer.setKeyHandler(new GraphicalViewerKeyHandler(viewer)
				.setParent(getCommonKeyHandler()));		
		getGraphicalViewer().setKeyHandler(new GraphicalViewerKeyHandler(viewer)
				.setParent(getCommonKeyHandler()));
		ContextMenuProvider provider = new NetworkEditorContextMenuProvider(
				getGraphicalViewer(), getActionRegistry());
		getGraphicalViewer().setContextMenu(provider);
		getSite().registerContextMenu("ru.bmstu.iu5.opsk.contextmenu", //$NON-NLS-1$
				provider, getGraphicalViewer());
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.ISelectionListener#selectionChanged(org.eclipse.ui.IWorkbenchPart, org.eclipse.jface.viewers.ISelection)
	 */
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if (selection instanceof StructuredSelection) {
			StructuredSelection sel = (StructuredSelection) selection;
			ArrayList pathes = new ArrayList();
			for (Iterator iter = sel.iterator(); iter.hasNext();) {
				Object element = (Object) iter.next();
				if (element instanceof Path) {
					pathes.add(element);
				}
			}
			if (modelIsHiglighted && pathes.size() == 0) {
				// Insert an empty path element 
				// that includes nothing in order
				// to dishighlight all nodes
				pathes.add(new Path(null));
				diagram.highlight(pathes);
				modelIsHiglighted = false;
			} else if (pathes.size() > 0) {
				diagram.highlight(pathes);
				modelIsHiglighted = true;
			}
			
		}
		super.selectionChanged(part, selection);
	}
	
	/**
	 * Set up the editor's inital content (after creation).
	 * @see org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette#initializeGraphicalViewer()
	 */
	protected void initializeGraphicalViewer() {
		GraphicalViewer viewer = getGraphicalViewer();
		viewer.setContents(diagram); // set the contents of this editor
//		// add the ShortestPathConnectionRouter
//		ScalableFreeformRootEditPart root = 
//				(ScalableFreeformRootEditPart)viewer.getRootEditPart();
//		ConnectionLayer connLayer =
//				(ConnectionLayer)root.getLayer(LayerConstants.CONNECTION_LAYER);
//		GraphicalEditPart contentEditPart = (GraphicalEditPart)root.getContents();
//		ShortestPathConnectionRouter router = 
//				new ShortestPathConnectionRouter(contentEditPart.getFigure());
//		connLayer.setConnectionRouter(router);
//		contentEditPart.getContentPane().addLayoutListener(router.getLayoutListener());

		// listen for dropped parts
		viewer.addDropTargetListener(createTransferDropTargetListener());
	}	

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.ui.parts.GraphicalEditor#getAdapter(java.lang.Class)
	 */
	public Object getAdapter(Class type) {
		if (type == IContentOutlinePage.class)
			return new NetworkOutlinePage(new TreeViewer(), getEditDomain(),
					getActionRegistry(), getSelectionSynchronizer(),
					new TreeRootModel(diagram));
		if (type == IShortestPathPage.class)
			return new ShortestPathPage(diagram, getCommandStack());
		if (type == ZoomManager.class) {
			return ((ScalableRootEditPart)getGraphicalViewer()
					.getRootEditPart()).getZoomManager();
		}
		return super.getAdapter(type);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.ui.parts.GraphicalEditor#init(org.eclipse.ui.IEditorSite, org.eclipse.ui.IEditorInput)
	 */
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		super.init(site, input);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette#createPaletteViewerProvider()
	 */
	protected PaletteViewerProvider createPaletteViewerProvider() {
		return new PaletteViewerProvider(getEditDomain()) {
			protected void configurePaletteViewer(PaletteViewer viewer) {
				super.configurePaletteViewer(viewer);
				// create a drag source listener for this palette viewer
				// together with an appropriate transfer drop target listener, this will enable
				// model element creation by dragging a CombinatedTemplateCreationEntries 
				// from the palette into the editor
				// @see ShapesEditor#createTransferDropTargetListener()
				viewer.addDragSourceListener(new TemplateTransferDragSourceListener(viewer));
			}
		};
	}

	/**
	 * Create a transfer drop target listener. When using a CombinedTemplateCreationEntry
	 * tool in the palette, this will enable model element creation by dragging from the palette.
	 * @see #createPaletteViewerProvider()
	 */
	private TransferDropTargetListener createTransferDropTargetListener() {
		return new TemplateTransferDropTargetListener(getGraphicalViewer()) {
			protected CreationFactory getFactory(Object template) {
				return new SimpleFactory((Class) template);
			}
		};
	}
	/**
	 * @see org.eclipse.ui.ISaveablePart#doSave(org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void doSave(IProgressMonitor monitor) {
		if (input.isNewlyCreated() || (!input.exists())) {
			doSaveAs();
		} else {
			saveData();
		}

	}
	
	private void saveData() {
		try {
			input.save(diagram);
			getCommandStack().markSaveLocation();
			setContentDescription(input.getToolTipText());
			setPartName(input.getName());
		} catch (Exception e) {
			MessageBox box = new MessageBox(getSite().getWorkbenchWindow().getShell(), SWT.OK | SWT.ICON_ERROR);
			box.setMessage(Messages.getString("ru.bmstu.iu5.opsk.get.error.filesave") + e.getMessage()); //$NON-NLS-1$
			box.open();
		}
	}

	/**
	 * @see org.eclipse.ui.ISaveablePart#doSaveAs()
	 */
	public void doSaveAs() {
		try {
			FileDialog dialog = new FileDialog(getSite().getWorkbenchWindow()
					.getShell(), SWT.SAVE);
			dialog.setFilterExtensions(new String[] { Messages.getString("ru.bmstu.iu5.opsk.get.opsk-file-pattern"), Messages.getString("ru.bmstu.iu5.opsk.get.all-file-pattern") }); //$NON-NLS-1$ //$NON-NLS-2$
			dialog.setFilterNames(new String[] { Messages.getString("ru.bmstu.iu5.opsk.get.opsk-file-pattern.description"), Messages.getString("ru.bmstu.iu5.opsk.get.all-file-pattern.description") }); //$NON-NLS-1$ //$NON-NLS-2$
			String fileName = dialog.open();
			if (fileName != null) {
				input.setFileName(fileName);
				saveData();
			}
		} catch (Exception e) {
			MessageBox box = new MessageBox(getSite().getWorkbenchWindow().getShell(), SWT.OK | SWT.ICON_ERROR);
			box.setMessage(Messages.getString("ru.bmstu.iu5.opsk.get.error.filesaveas") + e.getMessage()); //$NON-NLS-1$
			box.open();
		}		
	}

	protected KeyHandler getCommonKeyHandler() {
		if (sharedKeyHandler == null) {
			sharedKeyHandler = new KeyHandler();
			sharedKeyHandler
					.put(KeyStroke.getPressed(SWT.DEL, 127, 0),
							getActionRegistry().getAction(
									ActionFactory.DELETE.getId()));
			
		}
		return sharedKeyHandler;
	}

	/**
	 * @see org.eclipse.gef.ui.parts.GraphicalEditorWithPalette#getPaletteRoot()
	 */
	protected PaletteRoot getPaletteRoot() {
		if (root == null)
			root = NetworkEditorPaletteFactory.createPalette();
		return root;
	}

	public void gotoMarker(IMarker marker) {
	}

	/**
	 * @see org.eclipse.ui.ISaveablePart#isDirty()
	 */
	public boolean isDirty() {
		return isSaveOnCloseNeeded();
	}

	/**
	 * @see org.eclipse.ui.ISaveablePart#isSaveAsAllowed()
	 */
	public boolean isSaveAsAllowed() {
		return true;
	}

	/**
	 * @see org.eclipse.ui.ISaveablePart#isSaveOnCloseNeeded()
	 */
	public boolean isSaveOnCloseNeeded() {
		return getCommandStack().isDirty();
	}

	private boolean savePreviouslyNeeded() {
		return savePreviouslyNeeded;
	}

	/**
	 * @see org.eclipse.ui.part.EditorPart#setInput(org.eclipse.ui.IEditorInput)
	 */
	protected void setInput(IEditorInput input) {
		super.setInput(input);
		this.input = (FileEditorInput) input;
		setContentDescription(input.getToolTipText());
		setPartName(input.getName());
		try {
			diagram = this.input.getDiagram();
		} catch (FileNotFoundException e) {
			MessageBox box = new MessageBox(getSite().getWorkbenchWindow().getShell(), SWT.OK | SWT.ICON_ERROR);
			box.setMessage(Messages.getString("ru.bmstu.iu5.opsk.get.error.fileload") + e.getMessage()); //$NON-NLS-1$
			box.open();
			diagram = new NetworkElement();
		} 
	}

	private void setSavePreviouslyNeeded(boolean value) {
		savePreviouslyNeeded = value;
	}
	
	public NetworkElement getDiagramm() {
		return diagram;
	}

}
