/*
 * Created on 11.05.2005
 * 
 * Copyright BMSTU 2005
 */
package ru.bmstu.iu5.opsk.path;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;

/**
 * @author Egorova Olga
 */
public class PathTableContentProvider implements IStructuredContentProvider, IPathListViewer {

	PathList input = null;
	
	TableViewer viewer = null;
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
	 */
	public Object[] getElements(Object inputElement) {
		return input.getPathes().toArray();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
	 */
	public void dispose() {
		input.removeChangeListener(this);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
	 */
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		if (newInput != null) {
			input = ((PathList) newInput);
			input.addChangeListener(this);
			this.viewer = (TableViewer) viewer;
		}
		if (oldInput != null) {
			((PathList) oldInput).removeChangeListener(this);
		}
	}

	/* (non-Javadoc)
	 * @see ru.bmstu.iu5.opsk.path.IPathListViewer#addPath(ru.bmstu.iu5.opsk.path.Path)
	 */
	public void addPath(Path path) {
		viewer.add(path);
	}

	/* (non-Javadoc)
	 * @see ru.bmstu.iu5.opsk.path.IPathListViewer#removePath(ru.bmstu.iu5.opsk.path.Path)
	 */
	public void removePath(Path path) {
		viewer.remove(path);
	}

	/* (non-Javadoc)
	 * @see ru.bmstu.iu5.opsk.path.IPathListViewer#updatePath(ru.bmstu.iu5.opsk.path.Path)
	 */
	public void updatePath(Path path) {
		viewer.update(path, null);
	}

	

}
