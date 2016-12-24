/*
 * Created on 22.05.2005
 * 
 * Copyright BMSTU 2005
 */
package ru.bmstu.iu5.opsk.gef.commands;

import ru.bmstu.iu5.opsk.Messages;

/**
 * @author Egorova Olga
 */
public class SetDestinationNodeCommand extends SetSpecificNodeCommand {

	/* (non-Javadoc)
	 * @see ru.bmstu.iu5.opsk.gef.commands.SetSpecificNodeCommand#getCommandLabel()
	 */
	public String getCommandLabel() {
		return Messages.getString("ru.bmstu.iu5.opsk.command.set-dest-node.name"); //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#redo()
	 */
	public void redo() {
		oldNode = network.setDestinationNode(node);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {
		network.setDestinationNode(oldNode);
	}

}
