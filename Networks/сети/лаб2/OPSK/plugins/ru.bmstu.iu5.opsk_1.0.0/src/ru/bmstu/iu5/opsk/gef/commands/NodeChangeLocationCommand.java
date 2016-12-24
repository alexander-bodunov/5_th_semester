/*
 * Created on 05.05.2005
 * 
 * Copyright BMSTU 2005
 */
package ru.bmstu.iu5.opsk.gef.commands;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.ChangeBoundsRequest;

import ru.bmstu.iu5.opsk.Messages;
import ru.bmstu.iu5.opsk.gef.model.NodeElement;

/**
 * Command for node change location
 * 
 * @author Egorova Olga
 */
public class NodeChangeLocationCommand extends Command {
	
	NodeElement node;
	
	ChangeBoundsRequest request;
	
	Point position;
	
	Point oldPosition;
	
	/**
	 * Create a command that can resize and/or move a shape. 
	 * @param shape	the shape to manipulate
	 * @param req		the move and resize request
	 * @param newBounds the new size and location
	 * @throws IllegalArgumentException if any of the parameters is null
	 */
	public NodeChangeLocationCommand(NodeElement node, ChangeBoundsRequest req, 
			Point newPosition) {
		if (node == null || req == null || newPosition == null) {
			throw new IllegalArgumentException();
		}
		this.node = node;
		this.request = req;
		this.position = newPosition.getCopy();
		setLabel(Messages.getString("ru.bmstu.iu5.opsk.gef.commands.move.label")); //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#canExecute()
	 */
	public boolean canExecute() {
		Object type = request.getType();
		// make sure the Request is of a type we support:
		return (RequestConstants.REQ_MOVE.equals(type)
				|| RequestConstants.REQ_MOVE_CHILDREN.equals(type));
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {
		oldPosition = node.getLocation();
		redo();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#redo()
	 */
	public void redo() {
		node.setLocation(position);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {
		node.setLocation(oldPosition);		
	}
	

}
