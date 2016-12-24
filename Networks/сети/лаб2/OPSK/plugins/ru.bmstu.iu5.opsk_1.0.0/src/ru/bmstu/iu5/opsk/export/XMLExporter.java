/*
 * Created on 04.06.2005
 * 
 * Copyright BMSTU 2005
 */
package ru.bmstu.iu5.opsk.export;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import ru.bmstu.iu5.opsk.gef.model.NetworkElement;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * Export to XML file
 * 
 * @author Egorova Olga
 */
public class XMLExporter implements Exporter {
	
	XStream xstream = new XStream(new DomDriver());	

	public XMLExporter() {
		xstream.alias("report", Report.class); //$NON-NLS-1$
		xstream.alias("path", PathDescriptor.class); //$NON-NLS-1$
	}
	
	/* (non-Javadoc)
	 * @see ru.bmstu.iu5.opsk.export.Exporter#doExport(java.lang.String, ru.bmstu.iu5.opsk.gef.NetworkEditor)
	 */
	public String doExport(String fileName, NetworkElement network) {
		try {
			Writer wr = new FileWriter(new File(fileName));
			xstream.toXML(new Report(network), wr);
			wr.close();
		} catch (IOException e) {
			return e.getMessage();
		}
		return null;
	}

}
