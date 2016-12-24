/*
 * Created on May 4, 2005
 * 
 * Copyright BMSTU
 */
package ru.bmstu.iu5.opsk.gef.commands;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateRequest;

import ru.bmstu.iu5.opsk.Messages;
import ru.bmstu.iu5.opsk.gef.model.NetworkElement;
import ru.bmstu.iu5.opsk.gef.model.NodeElement;

/**
 * 
 *
 * @author Egorova Olga
 */
public class NodeCreateCommand extends Command {
	
	private NodeElement node;

	private NetworkElement network;
	
	private CreateRequest request;
	
	private boolean nodeAdded = false;
	
	public NodeCreateCommand(NetworkElement network, CreateRequest request) {
		this.network = network;
		this.request = request;
		setLabel(Messages.getString("ru.bmstu.iu5.opsk.command.create.name")); //$NON-NLS-1$
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {
		node = (NodeElement) request.getNewObject();
		node.setLocation(request.getLocation());
		redo();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#redo()
	 */
	public void redo() {
		nodeAdded = network.addChild(node);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {
		network.removeChild(node);
	}
	

}
