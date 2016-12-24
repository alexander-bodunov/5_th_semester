/*
 * Created on Apr 7, 2005
 * 
 * Copyright BMSTU
 */
package ru.bmstu.iu5.opsk.actions;

import java.net.URL;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.ActionFactory;

import ru.bmstu.iu5.opsk.Messages;
import ru.bmstu.iu5.opsk.rpc.FileEditorInput;
import ru.bmstu.iu5.opsk.rpc.RPCPlugin;

/**
 * This action creates a new model
 *
 * @author Egorova Olga
 */
public class NewModelAction extends Action implements ActionFactory.IWorkbenchAction {

	  private static ImageDescriptor image;
	  static {
	    URL url = null;
	    url = RPCPlugin.getDefault().getBundle().getEntry("/icons/new_model.gif"); //$NON-NLS-1$
	    image = ImageDescriptor.createFromURL(url);
	  }	
	
	IWorkbenchWindow window;
	
	public NewModelAction(IWorkbenchWindow wind) {
		super(Messages.getString("ru.bmstu.iu5.opsk.actions.new-model"), image); //$NON-NLS-1$
		window = wind;
		setAccelerator(SWT.ALT | SWT.SHIFT | 'N');
		setDescription(Messages.getString("ru.bmstu.iu5.opsk.actions.new-model.description")); //$NON-NLS-1$
		setToolTipText(Messages.getString("ru.bmstu.iu5.opsk.actions.new-model.tolltip")); //$NON-NLS-1$
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.action.Action#run()
	 */
	public void run() {
		//MessageDialog.openInformation(window.getShell(), "NewModelAction", "Action has runned successfully");
		try {
			window.getActivePage().openEditor(new FileEditorInput(), "networkEditor"); //$NON-NLS-1$
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.actions.ActionFactory.IWorkbenchAction#dispose()
	 */
	public void dispose() {
		// TODO Auto-generated method stub
	}
	

}
