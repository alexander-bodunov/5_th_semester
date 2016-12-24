/*
 * Created on Apr 5, 2005
 * 
 * Copyright BMSTU
 */
package ru.bmstu.iu5.opsk.rpc;

import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarContributionItem;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchAdvisor;

import ru.bmstu.iu5.opsk.Messages;
import ru.bmstu.iu5.opsk.actions.CSVExportAction;
import ru.bmstu.iu5.opsk.actions.NewModelAction;
import ru.bmstu.iu5.opsk.actions.OpenModelAction;
import ru.bmstu.iu5.opsk.actions.ShowViewAction;
import ru.bmstu.iu5.opsk.actions.XMLExportAction;
import ru.bmstu.iu5.opsk.views.ShortestPathView;

/**
 * 
 *
 * @author Egorova Olga
 */
public class OPSKWorkbenchAdvisor extends WorkbenchAdvisor {

	private IAction newModelAction;
	private IAction openModelAction;
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.application.WorkbenchAdvisor#getInitialWindowPerspectiveId()
	 */
	public String getInitialWindowPerspectiveId() {
		return "ru.bmstu.iu5.opsk.ModelPerspective"; //$NON-NLS-1$
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.application.WorkbenchAdvisor#preWindowOpen(org.eclipse.ui.application.IWorkbenchWindowConfigurer)
	 */
	public void preWindowOpen(IWorkbenchWindowConfigurer configurer) {
		super.preWindowOpen(configurer);
		configurer.setInitialSize(new Point(760, 540));
		configurer.setShowMenuBar(true);
		configurer.setShowStatusLine(true);
		configurer.setShowCoolBar(true);
		configurer.setShowFastViewBars(true);
		configurer.setShowProgressIndicator(true);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.application.WorkbenchAdvisor#fillActionBars(org.eclipse.ui.IWorkbenchWindow, org.eclipse.ui.application.IActionBarConfigurer, int)
	 */
	public void fillActionBars(IWorkbenchWindow window,
			IActionBarConfigurer configurer, int flags) {
		createActions(window);
		IMenuManager menuBar = configurer.getMenuManager();
		menuBar.add(createFileMenu(window));
		menuBar.add(createEditMenu(window));
		menuBar.add(createViewMenu(window));
		menuBar.add(createHelpMenu(window));
		fillToolBar(window, configurer);
		//super.fillActionBars(window, configurer, flags);
	}
	
	private void createActions(IWorkbenchWindow window) {
		newModelAction = new ActionFactory("new_model") { //$NON-NLS-1$
			public IWorkbenchAction create(IWorkbenchWindow window) {
				if (window == null) {
					throw new IllegalArgumentException();
				}
				IWorkbenchAction action = new NewModelAction(window);
				action.setId(getId());
				return action;
			}
		}.create(window);
		openModelAction = new ActionFactory("open_model") { //$NON-NLS-1$
			public IWorkbenchAction create(IWorkbenchWindow window) {
				if (window == null) {
					throw new IllegalArgumentException();
				}
				IWorkbenchAction action = new OpenModelAction(window);
				action.setId(getId());
				return action;
			}
		}.create(window);
	}
	
	
	private void fillToolBar(IWorkbenchWindow window, IActionBarConfigurer configurer) {
		ICoolBarManager cbManager = configurer.getCoolBarManager();
		cbManager.add(new GroupMarker("group.file")); //$NON-NLS-1$
		{ // File Group
			IToolBarManager fileToolBar = new ToolBarManager(cbManager.getStyle());
			fileToolBar.add(newModelAction);
			fileToolBar.add(openModelAction);
			fileToolBar.add(new Separator(IWorkbenchActionConstants.NEW_GROUP));
			fileToolBar.add(new GroupMarker(IWorkbenchActionConstants.NEW_EXT));
			fileToolBar.add(new GroupMarker(IWorkbenchActionConstants.SAVE_GROUP));
			fileToolBar.add(ActionFactory.SAVE.create(window));
			fileToolBar.add(ActionFactory.SAVE_ALL.create(window));
			fileToolBar.add(ActionFactory.SAVE_AS.create(window));
			fileToolBar.add(new GroupMarker(IWorkbenchActionConstants.SAVE_EXT));
			fileToolBar.add(ActionFactory.PRINT.create(window));
			fileToolBar.add(new GroupMarker(IWorkbenchActionConstants.PRINT_EXT));
			fileToolBar.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
			// Add to the cool bar manager
			cbManager.add(new ToolBarContributionItem(fileToolBar,IWorkbenchActionConstants.TOOLBAR_FILE));
		}
		
		cbManager.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
		
		cbManager.add(new GroupMarker(IWorkbenchActionConstants.GROUP_EDITOR)); { // Editor group
			IToolBarManager editorToolBar = new ToolBarManager(cbManager.getStyle());
			editorToolBar.add(ActionFactory.REDO.create(window));
			editorToolBar.add(ActionFactory.UNDO.create(window));
			// Add to the cool bar manager
			cbManager.add(new ToolBarContributionItem(editorToolBar,"editor")); //$NON-NLS-1$
		}
		
	}
	
	/**
	 * Creating a file menu
	 * 
	 * @param window
	 * @return
	 */
	private MenuManager createFileMenu(IWorkbenchWindow window) {
		MenuManager fileMenu = new MenuManager(Messages.getString("ru.bmstu.iu5.opsk.rpc.menu.file"), IWorkbenchActionConstants.M_FILE); //$NON-NLS-1$
		MenuManager exportMenu = new MenuManager(Messages.getString("ru.bmstu.iu5.opsk.main-app-window.export-menu-name")); //$NON-NLS-1$
		fileMenu.add(new GroupMarker(IWorkbenchActionConstants.FILE_START));
		fileMenu.add(newModelAction);
		fileMenu.add(openModelAction);
		fileMenu.add(new Separator());
		fileMenu.add(ActionFactory.CLOSE.create(window));
		fileMenu.add(ActionFactory.CLOSE_ALL.create(window));
		fileMenu.add(new Separator());
		fileMenu.add(ActionFactory.SAVE.create(window));
		fileMenu.add(ActionFactory.SAVE_ALL.create(window));
		fileMenu.add(ActionFactory.SAVE_AS.create(window));
		fileMenu.add(new Separator());
		fileMenu.add(ActionFactory.PRINT.create(window));
		fileMenu.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
		fileMenu.add(new Separator());
		fileMenu.add(exportMenu);
		exportMenu.add(new XMLExportAction(window));
		exportMenu.add(new CSVExportAction(window));		
		//exportMenu.add(new ExportAction(window));
		fileMenu.add(new Separator());
		fileMenu.add(ActionFactory.QUIT.create(window));
		fileMenu.add(new GroupMarker(IWorkbenchActionConstants.FILE_END));
		return fileMenu;
	}

	/**
	 * Creating a view menu
	 * 
	 * @param window
	 * @return
	 */
	private MenuManager createViewMenu(IWorkbenchWindow window) {
		MenuManager viewMenu = new MenuManager(Messages.getString("ru.bmstu.iu5.opsk.main-app-window.view-menu.name"), "view");   //$NON-NLS-1$//$NON-NLS-2$
		viewMenu.add(new ShowViewAction(window, IPageLayout.ID_OUTLINE, Messages.getString("ru.bmstu.iu5.opsk.main-app-window.outline-view.name"), Messages.getString("ru.bmstu.iu5.opsk.main-app-window.outline-view.description"))); //$NON-NLS-1$ //$NON-NLS-2$
		viewMenu.add(new ShowViewAction(window, IPageLayout.ID_PROP_SHEET, Messages.getString("ru.bmstu.iu5.opsk.main-app-window.properties-view.name"), Messages.getString("ru.bmstu.iu5.opsk.main-app-window.properties-view.description"))); //$NON-NLS-1$ //$NON-NLS-2$
		viewMenu.add(new ShowViewAction(window, ShortestPathView.ID, Messages.getString("ru.bmstu.iu5.opsk.main-app-window.shortest-path.name"), Messages.getString("ru.bmstu.iu5.opsk.main-app-window.shortest-path.description"))); //$NON-NLS-1$ //$NON-NLS-2$
		return viewMenu;
	}	
	/**
	 * Creating a edit menu
	 * 
	 * @param window
	 * @return
	 */
	protected MenuManager createEditMenu(IWorkbenchWindow window) {
		MenuManager editMenu = new MenuManager(Messages.getString("ru.bmstu.iu5.opsk.rpc.menu.edit"), IWorkbenchActionConstants.M_WINDOW); //$NON-NLS-1$
		editMenu.add(new GroupMarker(IWorkbenchActionConstants.EDIT_START));
		editMenu.add(ActionFactory.UNDO.create(window));		
		editMenu.add(ActionFactory.REDO.create(window));
		//editMenu.add(ActionFactory.SHOW_VIEW_MENU.create(window));
		editMenu.add(new Separator());
		//editMenu.add(ActionFactory.COPY.create(window));		
		//editMenu.add(ActionFactory.CUT.create(window));
		//editMenu.add(ActionFactory.PASTE.create(window));
		//editMenu.add(new Separator());
		editMenu.add(ActionFactory.DELETE.create(window));
		editMenu.add(ActionFactory.SELECT_ALL.create(window));
		editMenu.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
		editMenu.add(new GroupMarker(IWorkbenchActionConstants.EDIT_END));
		return editMenu;
	}
	
	
	/**
	 * Creating a help menu
	 * 
	 * @param window
	 * @return
	 */
	protected MenuManager createHelpMenu(IWorkbenchWindow window) {
		MenuManager helpMenu = new MenuManager(Messages.getString("ru.bmstu.iu5.opsk.rpc.menu.help"), IWorkbenchActionConstants.M_HELP); //$NON-NLS-1$
		helpMenu.add(new GroupMarker(IWorkbenchActionConstants.HELP_START));
		helpMenu.add(ActionFactory.HELP_CONTENTS.create(window));
		helpMenu.add(ActionFactory.ABOUT.create(window));
		helpMenu.add(new GroupMarker(IWorkbenchActionConstants.HELP_END));
		return helpMenu;
	}
	
	

}
