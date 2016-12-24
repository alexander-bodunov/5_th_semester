/*
 * Created on May 4, 2005
 * 
 * Copyright BMSTU
 */
package ru.bmstu.iu5.opsk.gef.parts;

import java.beans.PropertyChangeListener;

import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import ru.bmstu.iu5.opsk.gef.model.ViewModel;

/**
 * Common EditPart for GEF 
 *
 * @author Egorova Olga
 */
public abstract class PropertyAwarePart extends AbstractGraphicalEditPart implements PropertyChangeListener {

	/* (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractEditPart#activate()
	 */
	public void activate() {
		if (!isActive()) {
			super.activate();
			((ViewModel)this.getModel()).addPropertyChangeListener(this);
		}
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#deactivate()
	 */
	public void deactivate() {
		if (isActive()) {
			super.deactivate();
			((ViewModel)this.getModel()).removePropertyChangeListener(this);
		}
	}

}
