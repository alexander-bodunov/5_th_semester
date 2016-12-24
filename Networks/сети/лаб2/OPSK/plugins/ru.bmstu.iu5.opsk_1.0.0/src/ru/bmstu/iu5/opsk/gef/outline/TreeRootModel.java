/*
 * Created on 06.05.2005
 * 
 * Copyright BMSTU 2005
 */
package ru.bmstu.iu5.opsk.gef.outline;

import java.util.ArrayList;
import java.util.List;

import ru.bmstu.iu5.opsk.gef.model.NetworkElement;

/**
 * @author Egorova Olga
 */
public class TreeRootModel {
	
	NetworkElement root;
	
	public TreeRootModel(NetworkElement element) {
		root = element;
	}
	
	public List getChildren() {
		List result = new ArrayList();
		result.add(root);
		return result;
	}

}
