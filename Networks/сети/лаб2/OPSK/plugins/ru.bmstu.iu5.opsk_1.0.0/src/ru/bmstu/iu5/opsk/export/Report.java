/*
 * Created on 04.06.2005
 * 
 * Copyright BMSTU 2005
 */
package ru.bmstu.iu5.opsk.export;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import ru.bmstu.iu5.opsk.gef.model.NetworkElement;
import ru.bmstu.iu5.opsk.path.Path;
import ru.bmstu.iu5.opsk.path.PathList;

/**
 * Report object
 * 
 * @author Egorova Olga
 */
public class Report {
	
	public Date creationDate = new Date();
	
	public float overalNetworkCost = 0;
	
	public List content = new ArrayList();
	
	public Report(NetworkElement netElem) {
		overalNetworkCost = netElem.calculate();
		PathList pathes = netElem.getPaths();
		for (Iterator iter = pathes.getPathes().iterator(); iter.hasNext();) {
			Path path = (Path) iter.next();
			content.add(new PathDescriptor(content.size(), path));
		}
	}
	
	/**
	 * Transforms report to list of string array
	 * @return
	 */
	public List toStringList() {
		List result = new ArrayList();
		result.add(new String[] {"Number","Path", "Length", "Cost"}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		for (Iterator iter = content.iterator(); iter.hasNext();) {
			PathDescriptor desc = (PathDescriptor) iter.next();
			result.add(new String[] {String.valueOf(desc.number), desc.path, String.valueOf(desc.length), String.valueOf(desc.cost)});
		}
		return result;
	}

}
