/*
 * Created on May 4, 2005
 * 
 * Copyright BMSTU
 */
package ru.bmstu.iu5.opsk.gef.commands;

import java.util.List;

import org.eclipse.gef.commands.Command;

import ru.bmstu.iu5.opsk.Messages;
import ru.bmstu.iu5.opsk.gef.model.NetworkElement;
import ru.bmstu.iu5.opsk.gef.model.NodeElement;

/**
 * This command remove the node from the network
 *
 * @author Egorova Olga
 */
public class RemoveNodeCommand extends Command {
	
	private NodeElement node;
	
	private NetworkElement network;
	
	private List connections = null;
	
	
	public RemoveNodeCommand() {
		setLabel(Messages.getString("ru.bmstu.iu5.opsk.command.remove.name")); //$NON-NLS-1$
	}

	public void setNetwork(NetworkElement network) {
		this.network = network;
	}
	public void setNode(NodeElement node) {
		this.node = node;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {
		redo();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#redo()
	 */
	public void redo() {
		// Disconnect all connections of the removable node
		connections = node.disconnectAll();
		network.removeChild(node);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {
		network.addChild(node);
		node.reconnectAll(connections);
	}
	
}
