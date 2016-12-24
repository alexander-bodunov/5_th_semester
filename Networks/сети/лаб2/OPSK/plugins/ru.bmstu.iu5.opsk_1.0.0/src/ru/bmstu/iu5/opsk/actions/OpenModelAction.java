/*
 * Created on 07.05.2005
 * 
 * Copyright BMSTU 2005
 */
package ru.bmstu.iu5.opsk.actions;

import java.io.File;
import java.net.URL;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.ActionFactory;

import ru.bmstu.iu5.opsk.Messages;
import ru.bmstu.iu5.opsk.rpc.FileEditorInput;
import ru.bmstu.iu5.opsk.rpc.RPCPlugin;

/**
 * @author Egorova Olga
 */
public class OpenModelAction extends  Action implements ActionFactory.IWorkbenchAction {

	  private static ImageDescriptor image;
	  static {
	    URL url = null;
	    url = RPCPlugin.getDefault().getBundle().getEntry("/icons/open_file.gif"); //$NON-NLS-1$
	    image = ImageDescriptor.createFromURL(url);
	  }	
	
	IWorkbenchWindow window;
	
	public OpenModelAction(IWorkbenchWindow wind) {
		super(Messages.getString("ru.bmstu.iu5.opsk.actions.open-file"), image); //$NON-NLS-1$
		window = wind;
		setAccelerator(SWT.CONTROL | 'O');
		setDescription(Messages.getString("ru.bmstu.iu5.opsk.actions.open-filedescription")); //$NON-NLS-1$
		setToolTipText(Messages.getString("ru.bmstu.iu5.opsk.actions.open-filetooltip")); //$NON-NLS-1$
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.action.Action#run()
	 */
	public void run() {
		try {
			FileDialog dialog = new FileDialog(window.getShell(), SWT.OPEN);
			dialog.setFilterExtensions(new String[] { Messages.getString("ru.bmstu.iu5.opsk.actions.open-file.opsk-file-pattern"), Messages.getString("ru.bmstu.iu5.opsk.actions.open-file.all-files-file-pattern") }); //$NON-NLS-1$ //$NON-NLS-2$
			dialog.setFilterNames(new String[] { Messages.getString("ru.bmstu.iu5.opsk.actions.open-file.opsk-file-pattern-description"), Messages.getString("ru.bmstu.iu5.opsk.actions.open-file.all-file-pattern-description") }); //$NON-NLS-1$ //$NON-NLS-2$
			String fileName = dialog.open();
			if (fileName != null) {
				window.getActivePage().openEditor(new FileEditorInput(new File(fileName)), "networkEditor"); //$NON-NLS-1$
			}
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

