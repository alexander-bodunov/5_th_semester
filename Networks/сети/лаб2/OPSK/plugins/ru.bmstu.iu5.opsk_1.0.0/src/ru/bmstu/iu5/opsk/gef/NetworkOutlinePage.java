/*
 * Created on 05.05.2005
 * 
 * Copyright BMSTU 2005
 */
package ru.bmstu.iu5.opsk.gef;

import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.EditDomain;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.parts.ContentOutlinePage;
import org.eclipse.gef.ui.parts.SelectionSynchronizer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.part.IPageSite;

import ru.bmstu.iu5.opsk.gef.outline.NetworkTreePartFactory;

/**
 * @author Egorova Olga
 */
public class NetworkOutlinePage extends ContentOutlinePage {
	
	EditDomain domain;
	
	ActionRegistry registry;
	
	SelectionSynchronizer selection;
	
	Object model;
	
	/**
	 * Create a new outline page for the shapes editor.
	 * @param viewer a viewer (TreeViewer instance) used for this outline page
	 * @throws IllegalArgumentException if editor is null
	 */
	public NetworkOutlinePage(EditPartViewer viewer, EditDomain domain, ActionRegistry registry, SelectionSynchronizer selection, Object model) {
		super(viewer);
		this.domain = domain;
		this.registry = registry;
		this.selection = selection;
		this.model = model;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.IPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl(Composite parent) {
		// create outline viewer page
		getViewer().createControl(parent);
		// configure outline viewer
		getViewer().setEditDomain(domain);
		getViewer().setEditPartFactory(new NetworkTreePartFactory());
		// configure & add context menu to viewer
		ContextMenuProvider cmProvider = new NetworkEditorContextMenuProvider(
				getViewer(), registry); 
		getViewer().setContextMenu(cmProvider);
		getSite().registerContextMenu(
				"org.eclipse.gef.examples.shapes.outline.contextmenu", //$NON-NLS-1$
				cmProvider, getSite().getSelectionProvider());		
		// hook outline viewer
		selection.addViewer(getViewer());
		// initialize outline viewer with model
		getViewer().setContents(model);
		// show outline viewer
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.IPage#dispose()
	 */
	public void dispose() {
		// unhook outline viewer
		selection.removeViewer(getViewer());
		// dispose
		super.dispose();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.IPage#getControl()
	 */
	public Control getControl() {
		return getViewer().getControl();
	}
	
	/**
	 * @see org.eclipse.ui.part.IPageBookViewPage#init(org.eclipse.ui.part.IPageSite)
	 */
	public void init(IPageSite pageSite) {
		super.init(pageSite);
		ActionRegistry registry = this.registry;
		IActionBars bars = pageSite.getActionBars();
		String id = ActionFactory.UNDO.getId();
		bars.setGlobalActionHandler(id, registry.getAction(id));
		id = ActionFactory.REDO.getId();
		bars.setGlobalActionHandler(id, registry.getAction(id));
		id = ActionFactory.DELETE.getId();
		bars.setGlobalActionHandler(id, registry.getAction(id));
	}
	

}
