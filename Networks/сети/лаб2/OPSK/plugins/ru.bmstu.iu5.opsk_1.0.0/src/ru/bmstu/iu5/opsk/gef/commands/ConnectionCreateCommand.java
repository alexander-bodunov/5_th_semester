/*
 * Created on May 4, 2005
 * 
 * Copyright BMSTU
 */
package ru.bmstu.iu5.opsk.gef.commands;

import java.util.Iterator;

import org.eclipse.gef.commands.Command;

import ru.bmstu.iu5.opsk.Messages;
import ru.bmstu.iu5.opsk.gef.model.ConnectionElement;
import ru.bmstu.iu5.opsk.gef.model.NodeElement;

import com.sun.corba.se.pept.transport.Connection;

/**
 * Command 
 *
 * @author Egorova Olga
 */
public class ConnectionCreateCommand extends Command {
	
	/** The connection instance. */
	private ConnectionElement connection;
	/** Start endpoint for the connection. */
	private final NodeElement source;
	/** Target endpoint for the connection. */
	private NodeElement target;

	/**
	 *	Instantiate a command that can create a connection between two shapes.
	 * @param source the source endpoint (a non-null Shape instance)
	 * @param lineStyle the desired line style. See Connection#setLineStyle(int) for details
	 * @throws IllegalArgumentException if source is null
	 * @see Connection#setLineStyle(int)
	 */
	public ConnectionCreateCommand(NodeElement source) {
		if (source == null) {
			throw new IllegalArgumentException();
		}
		setLabel(Messages.getString("ru.bmstu.iu5.opsk.gef.commands.create.label")); //$NON-NLS-1$
		this.source = source;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#canExecute()
	 */
	public boolean canExecute() {
		// disallow source -> source connections
		if (source.equals(target)) {
			return false;
		}
		// return false, if the source -> target connection exists already
		for (Iterator iter = source.getSourceConnections().iterator(); iter.hasNext();) {
			ConnectionElement conn = (ConnectionElement) iter.next();
			if (conn.getTarget().equals(target)) {
				return false;
			}
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {
		// create a new connection between source and target
		connection = new ConnectionElement(source, target);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#redo()
	 */
	public void redo() {
		connection.reconnect();
	}

	/**
	 * Set the target endpoint for the connection.
	 * @param target that target endpoint (a non-null Shape instance)
	 * @throws IllegalArgumentException if target is null
	 */
	public void setTarget(NodeElement target) {
		if (target == null) {
			throw new IllegalArgumentException();
		}
		this.target = target;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {
		connection.disconnect();
	}

}
