/*
 * Created on 07.05.2005
 * 
 * Copyright BMSTU 2005
 */
package ru.bmstu.iu5.opsk.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;

/**
 * @author Egorova Olga
 */
public class ShowViewAction extends  Action implements IPartListener {
	
	IWorkbenchWindow window;
	
	private String viewId = null;
	
	public ShowViewAction(IWorkbenchWindow wind, String viewId, String menuItemName, String description) {
		super(menuItemName, IAction.AS_CHECK_BOX); //$NON-NLS-1$
		window = wind;
		window.getPartService().addPartListener(this);
		this.viewId = viewId;
		setDescription(description);
		setId("show_" + viewId); //$NON-NLS-1$
		setChecked(true);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.action.Action#run()
	 */
	public void run() {
		try {
			if (!isChecked()) {
				IViewReference[] refs = window.getActivePage().getViewReferences();
				for (int i = 0; i < refs.length; i++) {
					IViewReference reference = refs[i];
					if (reference.getId().equals(viewId)) {
						window.getActivePage().hideView(reference);
					}
				}
			} else {
				window.getActivePage().showView(viewId);
			}
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.actions.ActionFactory.IWorkbenchAction#dispose()
	 */
	public void dispose() {
		window.getPartService().removePartListener(this);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IPartListener#partActivated(org.eclipse.ui.IWorkbenchPart)
	 */
	public void partActivated(IWorkbenchPart part) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IPartListener#partBroughtToTop(org.eclipse.ui.IWorkbenchPart)
	 */
	public void partBroughtToTop(IWorkbenchPart part) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IPartListener#partClosed(org.eclipse.ui.IWorkbenchPart)
	 */
	public void partClosed(IWorkbenchPart part) {
		String id = part.getSite().getId();
		if (viewId.equals(id)) {
			setChecked(false);
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IPartListener#partDeactivated(org.eclipse.ui.IWorkbenchPart)
	 */
	public void partDeactivated(IWorkbenchPart part) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IPartListener#partOpened(org.eclipse.ui.IWorkbenchPart)
	 */
	public void partOpened(IWorkbenchPart part) {
		// TODO Auto-generated method stub
		
	}


}

