/*
 * Created on May 4, 2005
 * 
 * Copyright BMSTU
 */
package ru.bmstu.iu5.opsk.gef.parts;

import java.beans.PropertyChangeEvent;
import java.util.List;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.EllipseAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;

import ru.bmstu.iu5.opsk.gef.figures.NodeFigure;
import ru.bmstu.iu5.opsk.gef.model.NodeElement;
import ru.bmstu.iu5.opsk.gef.policies.NodeComponentEditPolicy;
import ru.bmstu.iu5.opsk.gef.policies.NodeGraphicalNodeRolePolicy;

/**
 * Edit part for NODE
 * 
 * @author Egorova Olga
 */
public class NodeEditPart extends PropertyAwarePart implements org.eclipse.gef.NodeEditPart {

	private boolean highlighted;


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
	 */
	protected IFigure createFigure() {
		NodeFigure figure = new NodeFigure();
		figure.setLocation(getCastedModel().getLocation());
		if (getCastedModel().isDestinationNode()) figure.setType(NodeFigure.TYPE_DESTINATION);
		if (getCastedModel().isSourceNode()) figure.setType(NodeFigure.TYPE_SOURCE);
		return figure;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
	 */
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new NodeComponentEditPolicy());
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new NodeGraphicalNodeRolePolicy());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
	 */
	public void propertyChange(PropertyChangeEvent evt) {
        String prop = evt.getPropertyName();
        if (NodeElement.SOURCE_CONNECTIONS_PROP.getId().equals(prop)) {
            refreshSourceConnections();
        } else if (NodeElement.TARGET_CONNECTIONS_PROP.getId().equals(prop)) {
            refreshTargetConnections();
        } else {
			if ("HIGH".equals(evt.getPropertyName())) { //$NON-NLS-1$
				if (!highlighted) {
					if (evt.getNewValue() != null) {
						((NodeFigure)figure).setHighlighted(true);
						highlighted = true;
					}
				} else {
					if (evt.getNewValue() == null) {
						((NodeFigure)figure).setHighlighted(false);
						highlighted = false;
					}
				}
        		}
			if ("SOURCE_NODE".equals(evt.getPropertyName())) { //$NON-NLS-1$
				if (Boolean.TRUE.equals(evt.getNewValue())) {
					((NodeFigure)figure).setType(NodeFigure.TYPE_SOURCE);
				} else {
					((NodeFigure)figure).setType(NodeFigure.TYPE_NORMAL);
				}
			}
			if ("DESTINATION_NODE".equals(evt.getPropertyName())) { //$NON-NLS-1$
				if (Boolean.TRUE.equals(evt.getNewValue())) {
					((NodeFigure)figure).setType(NodeFigure.TYPE_DESTINATION);
				} else {
					((NodeFigure)figure).setType(NodeFigure.TYPE_NORMAL);
				}
			}
        	refreshVisuals();
        }        
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#getModelSourceConnections()
	 */
	protected List getModelSourceConnections() {
		return getCastedModel().getSourceConnections();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#getModelTargetConnections()
	 */
	protected List getModelTargetConnections() {
		return getCastedModel().getTargetConnections();
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.NodeEditPart#getSourceConnectionAnchor(org.eclipse.gef.ConnectionEditPart)
	 */
	public ConnectionAnchor getSourceConnectionAnchor(ConnectionEditPart connection) {
		return new EllipseAnchor(getFigure());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.NodeEditPart#getTargetConnectionAnchor(org.eclipse.gef.ConnectionEditPart)
	 */
	public ConnectionAnchor getTargetConnectionAnchor(ConnectionEditPart connection) {
		return new EllipseAnchor(getFigure());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.NodeEditPart#getSourceConnectionAnchor(org.eclipse.gef.Request)
	 */
	public ConnectionAnchor getSourceConnectionAnchor(Request request) {
		return new EllipseAnchor(getFigure());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.NodeEditPart#getTargetConnectionAnchor(org.eclipse.gef.Request)
	 */
	public ConnectionAnchor getTargetConnectionAnchor(Request request) {
		return new EllipseAnchor(getFigure());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractEditPart#refreshVisuals()
	 */
	protected void refreshVisuals() {
		Rectangle bounds = new Rectangle(getCastedModel().getLocation(),
                getCastedModel().getDimension());
        figure.setBounds(bounds);
        ((GraphicalEditPart) getParent()).setLayoutConstraint(this, figure, bounds);
        ((NodeFigure)figure).setName(getCastedModel().getIdentity());
    }

	protected NodeElement getCastedModel() {
		return ((NodeElement)getModel());
	}

	
}