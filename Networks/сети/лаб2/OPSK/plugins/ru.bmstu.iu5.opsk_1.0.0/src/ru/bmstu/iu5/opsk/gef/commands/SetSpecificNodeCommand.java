/*
 * Created on May 4, 2005
 * 
 * Copyright BMSTU
 */
package ru.bmstu.iu5.opsk.gef.commands;

import org.eclipse.gef.commands.Command;

import ru.bmstu.iu5.opsk.gef.model.NetworkElement;
import ru.bmstu.iu5.opsk.gef.model.NodeElement;

/**
 * 
 *
 * @author Egorova Olga
 */
public abstract class SetSpecificNodeCommand extends Command {
	
	protected NodeElement node;

	protected NetworkElement network;
	
	protected NodeElement oldNode;
	
	public SetSpecificNodeCommand() {
		setLabel(getCommandLabel());
	}
	
	public abstract String getCommandLabel();
		
	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {
		redo();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#redo()
	 */
	public abstract void redo();
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public abstract void undo();
	

	/**
	 * @param network The network to set.
	 */
	public void setNetwork(NetworkElement network) {
		this.network = network;
	}
	/**
	 * @param node The node to set.
	 */
	public void setNode(NodeElement node) {
		this.node = node;
	}
}
