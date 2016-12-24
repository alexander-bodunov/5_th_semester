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
import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editparts.AbstractTreeEditPart;
import org.eclipse.swt.graphics.Image;

import ru.bmstu.iu5.opsk.Messages;
import ru.bmstu.iu5.opsk.gef.NetworkEditorPaletteFactory;
import ru.bmstu.iu5.opsk.gef.model.NodeElement;
import ru.bmstu.iu5.opsk.gef.model.ViewModel;
import ru.bmstu.iu5.opsk.gef.policies.NodeComponentEditPolicy;

/**
 * TreeEditPart used for Shape instances (more specific for EllipticalShape and
 * RectangularShape instances). This is used in the Outline View of the
 * ShapesEditor.
 * <p>
 * This edit part must implement the PropertyChangeListener interface, so it can
 * be notified of property changes in the corresponding model element.
 * </p>
 * 
 * @author Elias Volanakis
 */
class NodeTreeEditPart extends AbstractTreeEditPart implements
		PropertyChangeListener {

	/**
	 * Create a new instance of this edit part using the given model element.
	 * 
	 * @param model
	 *            a non-null Shapes instance
	 */
	NodeTreeEditPart(NodeElement model) {
		super(model);
	}

	/**
	 * Upon activation, attach to the model element as a property change
	 * listener.
	 */
	public void activate() {
		if (!isActive()) {
			super.activate();
			((ViewModel) getModel()).addPropertyChangeListener(this);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractTreeEditPart#createEditPolicies()
	 */
	protected void createEditPolicies() {
		// allow removal of the associated model element
		installEditPolicy(EditPolicy.COMPONENT_ROLE,
				new NodeComponentEditPolicy());
	}

	/**
	 * Upon deactivation, detach from the model element as a property change
	 * listener.
	 */
	public void deactivate() {
		if (isActive()) {
			super.deactivate();
			((ViewModel) getModel()).removePropertyChangeListener(this);
		}
	}

	private NodeElement getCastedModel() {
		return (NodeElement) getModel();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractTreeEditPart#getImage()
	 */
	protected Image getImage() {
		return NetworkEditorPaletteFactory.nodeIcon.createImage();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractTreeEditPart#getText()
	 */
	protected String getText() {
		return Messages.getString("ru.bmstu.iu5.opsk.gef.outline.node.name-prefix") + getCastedModel().getIdentity(); //$NON-NLS-1$
	}

	private EditPart getEditPartForChild(Object child) {
		return (EditPart) getViewer().getEditPartRegistry().get(child);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
	 */
	public void propertyChange(PropertyChangeEvent evt) {
		String prop = evt.getPropertyName();
		if ("SOURCE".equals(prop) || "TARGET".equals(prop)) { //$NON-NLS-1$ //$NON-NLS-2$
			if (evt.getNewValue() != null) {
				addChild(createChild(evt.getNewValue()), -1);
			} else {
				EditPart child = getEditPartForChild(evt.getOldValue());
				if (child != null) {
					removeChild(child);
				}
			}
		} else {
			refreshVisuals();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractEditPart#getModelChildren()
	 */
	protected List getModelChildren() {
		List result = new ArrayList();
		result.addAll(getCastedModel().getSourceConnections());
		result.addAll(getCastedModel().getTargetConnections());
		return result;
	}
}