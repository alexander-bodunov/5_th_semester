/*
 * Created on 11.05.2005
 * 
 * Copyright BMSTU 2005
 */
package ru.bmstu.iu5.opsk.path;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import ru.bmstu.iu5.opsk.Messages;

/**
 * @author Egorova Olga
 */
public class PathTableLabelProvider extends LabelProvider implements ITableLabelProvider {

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnImage(java.lang.Object, int)
	 */
	public Image getColumnImage(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnText(java.lang.Object, int)
	 */
	public String getColumnText(Object element, int columnIndex) {
		String result = ""; //$NON-NLS-1$
		Path path = (Path) element;
		switch (columnIndex) {
		case 0:
			result = String.valueOf(path.getNumber());
			break;
		case 1:
			result = path.getPathAsString();
			break;
		case 2:
			result = converToSeconds(path.calculateDelay());
			break;
		case 3:
			result = String.valueOf(path.getPathCost());
		}
		return result;
	}

	private static final String converToSeconds(float value) {
		System.err.println("Value = " + value); //$NON-NLS-1$
		StringBuffer result = new StringBuffer();
		if (value > 3600) {
			result.append(String.valueOf((int)Math.floor(value / 3600)) + Messages.getString("ru.bmstu.iu5.opsk.path-list.abbreviation.hour")); //$NON-NLS-1$
			value = value % 3600;
		}
		if (value > 60) {
			result.append(String.valueOf((int)Math.floor(value / 60)) + Messages.getString("ru.bmstu.iu5.opsk.path-list.abbreviation.minute")); //$NON-NLS-1$
			value = value % 60;
		}
		if (value > 0) {
			String tmp = String.valueOf((int)Math.floor(value));
			if (!tmp.equals("0")) result.append(String.valueOf((int)Math.floor(value)) + Messages.getString("ru.bmstu.iu5.opsk.path-list.abbreviation.second")); //$NON-NLS-1$ //$NON-NLS-2$
			value = value % 1f;
		}
		if (value > 0) {
			result.append(String.valueOf((int)Math.floor(value * 100)) + Messages.getString("ru.bmstu.iu5.opsk.path-list.abbreviation.millisecond")); //$NON-NLS-1$
			value = value % 0.001f;
		}
		return result.toString();
	}

}
