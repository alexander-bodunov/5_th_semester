/*******************************************************************************
 * Copyright (c) 2004 Elias Volanakis.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Elias Volanakis - initial API and implementation
 *    IBM Corporation
 *******************************************************************************/
package ru.bmstu.iu5.opsk.gef.outline;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.gef.editparts.AbstractTreeEditPart;
import org.eclipse.swt.graphics.Image;

import ru.bmstu.iu5.opsk.Messages;
import ru.bmstu.iu5.opsk.gef.NetworkEditorPaletteFactory;
import ru.bmstu.iu5.opsk.gef.model.ConnectionElement;
import ru.bmstu.iu5.opsk.gef.model.ViewModel;

/**
 * TreeEditPart used for Shape instances (more specific for EllipticalShape and
 * RectangularShape instances). This is used in the Outline View of the ShapesEditor.
 * <p>This edit part must implement the PropertyChangeListener interface, 
 * so it can be notified of property changes in the corresponding model element.
 * </p>
 * 
 * @author Elias Volanakis
 */
class ConnectionTreeEditPart extends AbstractTreeEditPart implements
		PropertyChangeListener {

/**
 * Create a new instance of this edit part using the given model element.
 * @param model a non-null Shapes instance
 */
ConnectionTreeEditPart(ConnectionElement model) {
	super(model);
}

/**
 * Upon activation, attach to the model element as a property change listener.
 */
public void activate() {
	if (!isActive()) {
		super.activate();
		((ViewModel) getModel()).addPropertyChangeListener(this);
	}
}

/**
 * Upon deactivation, detach from the model element as a property change listener.
 */
public void deactivate() {
	if (isActive()) {
		super.deactivate();
		((ViewModel) getModel()).removePropertyChangeListener(this);
	}
}

private ConnectionElement getCastedModel() {
	return (ConnectionElement) getModel();
}

/* (non-Javadoc)
 * @see org.eclipse.gef.editparts.AbstractTreeEditPart#getImage()
 */
protected Image getImage() {
	return NetworkEditorPaletteFactory.connectionIcon.createImage();
}

/* (non-Javadoc)
 * @see org.eclipse.gef.editparts.AbstractTreeEditPart#getText()
 */
protected String getText() {
	return Messages.getString("ru.bmstu.iu5.opsk.gef.outline.connection.name.prefix")  + getCastedModel().getSource().getIdentity() + Messages.getString("ru.bmstu.iu5.opsk.gef.outline.connection.name.connection") + getCastedModel().getTarget().getIdentity() + Messages.getString("ru.bmstu.iu5.opsk.gef.outline.connection.name.suffix"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
}

/* (non-Javadoc)
 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
 */
public void propertyChange(PropertyChangeEvent evt) {
	refreshVisuals(); // this will cause an invocation of getImage() and getText(), see below
}
}