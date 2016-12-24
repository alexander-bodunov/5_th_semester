/*
 * Created on 04.06.2005
 * 
 * Copyright BMSTU 2005
 */
package ru.bmstu.iu5.opsk.actions;

import org.eclipse.ui.IWorkbenchWindow;

import ru.bmstu.iu5.opsk.Messages;
import ru.bmstu.iu5.opsk.export.Exporter;
import ru.bmstu.iu5.opsk.export.XMLExporter;

/**
 * XMLExporter
 * 
 * @author Egorova Olga
 */
public class XMLExportAction extends AbstractExportAction {

	/**
	 * @param wind
	 */
	public XMLExportAction(IWorkbenchWindow wind) {
		super(wind);
		setText(Messages.getString("ru.bmstu.iu5.opsk.actions.export.xml.text")); //$NON-NLS-1$
		setDescription(Messages.getString("ru.bmstu.iu5.opsk.actions.export.xml.description"));  //$NON-NLS-1$
		setToolTipText(Messages.getString("ru.bmstu.iu5.opsk.actions.export.xml.tooltip")); //$NON-NLS-1$
		
	}

	/* (non-Javadoc)
	 * @see ru.bmstu.iu5.opsk.actions.AbstractExportAction#getExporter()
	 */
	public Exporter getExporter() {
		return new XMLExporter();
	}

	/* (non-Javadoc)
	 * @see ru.bmstu.iu5.opsk.actions.AbstractExportAction#getFilterExtensions()
	 */
	public String[] getFilterExtensions() {
		return new String[] { Messages.getString("ru.bmstu.iu5.opsk.actions.export.xml.filter-extension") }; //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see ru.bmstu.iu5.opsk.actions.AbstractExportAction#getFilterNames()
	 */
	public String[] getFilterNames() {
		return new String[] { Messages.getString("ru.bmstu.iu5.opsk.actions.export.xml.filter-name") }; //$NON-NLS-1$
	}
	
	

}
