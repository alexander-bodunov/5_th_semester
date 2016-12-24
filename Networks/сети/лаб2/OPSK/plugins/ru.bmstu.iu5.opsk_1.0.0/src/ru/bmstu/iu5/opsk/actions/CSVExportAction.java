/*
 * Created on 04.06.2005
 * 
 * Copyright BMSTU 2005
 */
package ru.bmstu.iu5.opsk.actions;

import org.eclipse.ui.IWorkbenchWindow;

import ru.bmstu.iu5.opsk.Messages;
import ru.bmstu.iu5.opsk.export.CSVExporter;
import ru.bmstu.iu5.opsk.export.Exporter;

/**
 * XMLExporter
 * 
 * @author Egorova Olga
 */
public class CSVExportAction extends AbstractExportAction {

	/**
	 * @param wind
	 */
	public CSVExportAction(IWorkbenchWindow wind) {
		super(wind);
		setText(Messages.getString("ru.bmstu.iu5.opsk.actions.csc-export.name")); //$NON-NLS-1$
		setDescription(Messages.getString("ru.bmstu.iu5.opsk.actions.csc-export.description"));  //$NON-NLS-1$
		setToolTipText(Messages.getString("ru.bmstu.iu5.opsk.actions.csc-export.tooltip")); //$NON-NLS-1$
		
	}

	/* (non-Javadoc)
	 * @see ru.bmstu.iu5.opsk.actions.AbstractExportAction#getExporter()
	 */
	public Exporter getExporter() {
		return new CSVExporter();
	}

	/* (non-Javadoc)
	 * @see ru.bmstu.iu5.opsk.actions.AbstractExportAction#getFilterExtensions()
	 */
	public String[] getFilterExtensions() {
		return new String[] { Messages.getString("ru.bmstu.iu5.opsk.actions.csc-export.filter-extension") }; //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see ru.bmstu.iu5.opsk.actions.AbstractExportAction#getFilterNames()
	 */
	public String[] getFilterNames() {
		return new String[] { Messages.getString("ru.bmstu.iu5.opsk.actions.csc-export.filter-name") }; //$NON-NLS-1$
	}
	
	

}
