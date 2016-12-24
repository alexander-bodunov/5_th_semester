/*
 * Created on May 4, 2005
 * 
 * Copyright BMSTU
 */
package ru.bmstu.iu5.opsk.gef.parts;

import java.beans.PropertyChangeListener;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.gef.editpolicies.ConnectionEditPolicy;
import org.eclipse.gef.editpolicies.ConnectionEndpointEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import ru.bmstu.iu5.opsk.gef.commands.ConnectionDeleteCommand;
import ru.bmstu.iu5.opsk.gef.figures.ConnectionFigure;
import ru.bmstu.iu5.opsk.gef.model.ConnectionElement;
import ru.bmstu.iu5.opsk.gef.model.ViewModel;

/**
 * 
 * 
 * @author Egorova Olga
 */
public class ConnectionEditPart extends AbstractConnectionEditPart implements
		PropertyChangeListener {

	private boolean highlighted = false;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
	 */
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.CONNECTION_ROLE,
				new ConnectionEditPolicy() {
					protected Command getDeleteCommand(GroupRequest request) {
						return new ConnectionDeleteCommand((ConnectionElement)getModel());
					}
				});
		installEditPolicy(EditPolicy.CONNECTION_ENDPOINTS_ROLE,
				new ConnectionEndpointEditPolicy());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractEditPart#activate()
	 */
	public void activate() {
		if (!isActive()) {
			super.activate();
			((ViewModel) this.getModel()).addPropertyChangeListener(this);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#deactivate()
	 */
	public void deactivate() {
		if (isActive()) {
			super.deactivate();
			((ViewModel) this.getModel()).removePropertyChangeListener(this);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
	 */
	public void propertyChange(java.beans.PropertyChangeEvent evt) {
		if ("HIGH".equals(evt.getPropertyName())) { //$NON-NLS-1$
			((ConnectionFigure)figure).setHighlighted(evt.getNewValue() != null);
		} else if (ConnectionElement.THROUGHPUT.getId().equals(evt.getPropertyName())) {
			Long value = (Long) evt.getNewValue();
			((ConnectionFigure)figure).setThroughput(value.longValue());
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractConnectionEditPart#createFigure()
	 */
	protected IFigure createFigure() {
		ConnectionFigure result = new ConnectionFigure();
		result.setThroughput(((ConnectionElement)getModel()).getModel().getThrougthput());
		return result;
	}

	
}
