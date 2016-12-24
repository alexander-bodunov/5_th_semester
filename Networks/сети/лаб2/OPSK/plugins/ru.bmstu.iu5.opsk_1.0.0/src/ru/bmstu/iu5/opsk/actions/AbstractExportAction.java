/*
 * Created on 04.06.2005
 * 
 * Copyright BMSTU 2005
 */
package ru.bmstu.iu5.opsk.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchWindow;

import ru.bmstu.iu5.opsk.Messages;
import ru.bmstu.iu5.opsk.export.Exporter;
import ru.bmstu.iu5.opsk.gef.NetworkEditor;

/**
 * Export action to XML or CSV
 * 
 * @author Egorova Olga
 */
public abstract class AbstractExportAction extends Action {

	private IWorkbenchWindow window;
	
	public AbstractExportAction(IWorkbenchWindow wind) {
		super(Messages.getString("ru.bmstu.iu5.opsk.actions.common-export.export-to"));  //$NON-NLS-1$
		this.window = wind;
	}
	
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.action.Action#run()
	 */
	public void run() {
		IEditorPart editor = window.getActivePage().getActiveEditor();
		if (editor == null || !(editor instanceof NetworkEditor)) {
			MessageBox box = new MessageBox(window.getShell(), SWT.OK | SWT.ICON_ERROR);
			box.setText(Messages.getString("ru.bmstu.iu5.opsk.actions.common-export.export-error")); //$NON-NLS-1$
			box.setMessage(Messages.getString("ru.bmstu.iu5.opsk.actions.common-export.nothing-to-export")); //$NON-NLS-1$
			box.open();
			return;
		}
		try {
			FileDialog dialog = new FileDialog(window.getShell(), SWT.SAVE);
			dialog.setFilterExtensions(getFilterExtensions()); 
			dialog.setFilterNames(getFilterNames());
			String fileName = dialog.open();
			if (fileName != null) {
				NetworkEditor ed = (NetworkEditor) editor;
				Exporter exporter = getExporter();
				String error = exporter.doExport(fileName, ed.getDiagramm());
				if (error != null) throw new Exception(error);
			}
		} catch (Exception e) {
			MessageBox box = new MessageBox(window.getShell(), SWT.OK | SWT.ICON_ERROR);
			box.setMessage(Messages.getString("ru.bmstu.iu5.opsk.get.error.filesaveas") + e.getMessage()); //$NON-NLS-1$
			box.open();
		}		
	}
	
	public abstract Exporter getExporter();
	
	public abstract String[] getFilterExtensions();
	
	public abstract String[] getFilterNames();
	
}
