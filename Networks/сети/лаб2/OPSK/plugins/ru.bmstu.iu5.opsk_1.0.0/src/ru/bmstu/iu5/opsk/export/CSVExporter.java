/*
 * Created on 04.06.2005
 * 
 * Copyright BMSTU 2005
 */
package ru.bmstu.iu5.opsk.export;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;

import ru.bmstu.iu5.opsk.gef.model.NetworkElement;

import com.Ostermiller.util.CSVPrint;
import com.Ostermiller.util.CSVPrinter;

/**
 * Exporter to CSV format
 * 
 * @author Egorova Olga
 */
public class CSVExporter implements Exporter {

	/* (non-Javadoc)
	 * @see ru.bmstu.iu5.opsk.export.Exporter#doExport(java.lang.String, ru.bmstu.iu5.opsk.gef.model.NetworkElement)
	 */
	public String doExport(String fileName, NetworkElement network) {
		try {
			CSVPrint out = new CSVPrinter(new FileOutputStream(new File(fileName)));
			Report rep = new Report(network);
			out.println("Overal network costs:" + rep.overalNetworkCost); //$NON-NLS-1$
			List strings = rep.toStringList();
			for (Iterator iter = strings.iterator(); iter.hasNext();) {
				out.println((String[])iter.next());
			}
			out.close();
		} catch (Exception e) {
			return e.getMessage();
		}
		return null;
	}

	
	
}
