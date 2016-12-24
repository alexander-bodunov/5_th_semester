/*
 * Created on May 4, 2005
 * 
 * Copyright BMSTU
 */
package ru.bmstu.iu5.opsk.gef.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import ru.bmstu.iu5.opsk.gef.commands.RemoveNodeCommand;
import ru.bmstu.iu5.opsk.gef.model.NetworkElement;
import ru.bmstu.iu5.opsk.gef.model.NodeElement;

/**
 * This edit policy creates a command to remove shape from the diagramm 
 *
 * @author Egorova Olga
 */
public class NodeComponentEditPolicy extends ComponentEditPolicy {

	/* (non-Javadoc)
	 * @see org.eclipse.gef.editpolicies.ComponentEditPolicy#createDeleteCommand(org.eclipse.gef.requests.GroupRequest)
	 */
	protected Command createDeleteCommand(GroupRequest deleteRequest) {
		NetworkElement parent = (NetworkElement)(getHost().getParent().getModel());
		RemoveNodeCommand deleteCmd = new RemoveNodeCommand();
		deleteCmd.setNetwork(parent);
		deleteCmd.setNode((NodeElement)(getHost().getModel()));
		return deleteCmd;
	}
}
