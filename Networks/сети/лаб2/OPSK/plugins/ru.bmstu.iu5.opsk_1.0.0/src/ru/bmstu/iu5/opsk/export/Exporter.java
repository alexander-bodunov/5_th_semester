/*
 * Created on 04.06.2005
 * 
 * Copyright BMSTU 2005
 */
package ru.bmstu.iu5.opsk.export;

import ru.bmstu.iu5.opsk.gef.model.NetworkElement;

/**
 * A interface for all exporters
 * 
 * @author Egorova Olga
 */
public interface Exporter {
	
	/**
	 * Do the export
	 * @param fileName
	 * @param network
	 * @return null if export was fine, or error message otherweise
	 */
	public abstract String doExport(String fileName, NetworkElement network);

}
