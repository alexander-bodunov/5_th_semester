/*
 * Created on 11.05.2005
 * 
 * Copyright BMSTU 2005
 */
package ru.bmstu.iu5.opsk.path;

import java.util.Vector;

/**
 * A model class encapsulating path list
 * 
 * @author Egorova Olga
 */
public class PathList {
	
	private Vector pathes = new Vector();
	
	public PathList() {
		//
	}
	
	public Vector getPathes() {
		return pathes;
	}

	/**
	 * @param provider
	 */
	public void removeChangeListener(PathTableContentProvider provider) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @param provider
	 */
	public void addChangeListener(PathTableContentProvider provider) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @param currentPath
	 */
	public void addPath(Path currentPath) {
		pathes.add(currentPath);
		currentPath.setNumber(pathes.size());
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "PathList:" + pathes.toString(); //$NON-NLS-1$
	}
}
