/*
 * Created on 12.05.2005
 * 
 * Copyright BMSTU 2005
 */
package ru.bmstu.iu5.opsk.path;

/**
 * Special interface for tracking changes in the path list
 * 
 * @author Egorova Olga
 */
public interface IPathListViewer {
	

	public void addPath(Path path);

	public void removePath(Path path);
	
	public void updatePath(Path path);

}
