/*
 * Created on May 4, 2005
 * 
 * Copyright BMSTU
 */
package ru.bmstu.iu5.opsk.gef.outline;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import ru.bmstu.iu5.opsk.gef.model.ConnectionElement;
import ru.bmstu.iu5.opsk.gef.model.NetworkElement;
import ru.bmstu.iu5.opsk.gef.model.NodeElement;

/**
 * EditPart factory
 *
 * @author Egorova Olga
 */
public class NetworkTreePartFactory implements EditPartFactory {

	/* (non-Javadoc)
	 * @see org.eclipse.gef.EditPartFactory#createEditPart(org.eclipse.gef.EditPart, java.lang.Object)
	 */
	public EditPart createEditPart(EditPart context, Object model) {
        EditPart part = null;
        if (model instanceof TreeRootModel) {
        	part = new DiagrammTreeEditPart((TreeRootModel) model);
        }
        if (model instanceof NetworkElement)
            part = new NetworkTreeEditPart((NetworkElement) model);
        else if (model instanceof NodeElement)
            part = new NodeTreeEditPart((NodeElement) model);
        else if (model instanceof ConnectionElement)
        	part = new ConnectionTreeEditPart((ConnectionElement)model);
        return part;
	}

}
