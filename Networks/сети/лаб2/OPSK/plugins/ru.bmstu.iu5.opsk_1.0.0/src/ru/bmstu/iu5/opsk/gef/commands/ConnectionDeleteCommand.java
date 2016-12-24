/*
 * Created on May 4, 2005
 * 
 * Copyright BMSTU
 */
package ru.bmstu.iu5.opsk.gef.commands;

import org.eclipse.gef.commands.Command;

import ru.bmstu.iu5.opsk.Messages;
import ru.bmstu.iu5.opsk.gef.model.ConnectionElement;

/**
 * 
 *
 * @author Egorova Olga
 */
public class ConnectionDeleteCommand extends Command {

	private ConnectionElement model;
	
	/**
	 * @param element
	 */
	public ConnectionDeleteCommand(ConnectionElement element) {
		model = element;
		setLabel(Messages.getString("ru.bmstu.iu5.opsk.command.delete-connection.name")); //$NON-NLS-1$
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
		model.disconnect();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {
		model.reconnect();
	}
	
	

}
