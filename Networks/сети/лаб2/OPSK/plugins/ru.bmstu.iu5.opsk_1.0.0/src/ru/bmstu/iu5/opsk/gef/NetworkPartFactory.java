/*
 * Created on May 4, 2005
 * 
 * Copyright BMSTU
 */
package ru.bmstu.iu5.opsk.gef;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import ru.bmstu.iu5.opsk.gef.model.ConnectionElement;
import ru.bmstu.iu5.opsk.gef.model.NetworkElement;
import ru.bmstu.iu5.opsk.gef.model.NodeElement;
import ru.bmstu.iu5.opsk.gef.parts.ConnectionEditPart;
import ru.bmstu.iu5.opsk.gef.parts.NetworkEditPart;
import ru.bmstu.iu5.opsk.gef.parts.NodeEditPart;

/**
 * EditPart factory
 *
 * @author Egorova Olga
 */
public class NetworkPartFactory implements EditPartFactory {

	/* (non-Javadoc)
	 * @see org.eclipse.gef.EditPartFactory#createEditPart(org.eclipse.gef.EditPart, java.lang.Object)
	 */
	public EditPart createEditPart(EditPart context, Object model) {
        EditPart part = null;
        if (model instanceof NetworkElement)
            part = new NetworkEditPart();
        else if (model instanceof NodeElement)
            part = new NodeEditPart();
        else if (model instanceof ConnectionElement)
            part = new ConnectionEditPart();
        part.setModel(model);
        return part;	
	}

}
