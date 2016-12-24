/*
 * Created on 05.05.2005
 * 
 * Copyright BMSTU 2005
 */
package ru.bmstu.iu5.opsk.gef.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.geometry.Dimension;

/**
 * Figure class for network Node
 * 
 * @author Egorova Olga
 */
public class NodeFigure extends Ellipse {

	public static final int TYPE_SOURCE = 1;
	
	public static final int TYPE_DESTINATION = 2;
	
	public static final int TYPE_NORMAL = 3;
	
	private Label name;
	
	private int type = TYPE_NORMAL;

	/**
	 * @param type The type to set.
	 */
	public void setType(int type) {
		this.type = type;
		if (type == TYPE_DESTINATION) {
			this.setBackgroundColor(ColorConstants.darkGray);
		}
		if (type == TYPE_NORMAL) {
			this.setBackgroundColor(ColorConstants.lightBlue);
		}
		if (type == TYPE_SOURCE) {
			this.setBackgroundColor(ColorConstants.darkGreen);
		}
	}

	public NodeFigure() {
		super();
		setLayoutManager(new StackLayout());
		name = new Label("UNKNOWN"); //$NON-NLS-1$
		add(name);
		setOpaque(true);
		setBackgroundColor(ColorConstants.lightBlue);
		setSize(new Dimension(50,50));
	}

	/**
	 * @param identity
	 */
	public void setName(String identity) {
		name.setText(identity);		
	}

	/**
	 * Set figure to the highlighted state
	 * 
	 * @param b
	 */
	public void setHighlighted(boolean b) {
		if (b) {
			setLineWidth(4);
			setForegroundColor(ColorConstants.red);
			name.setForegroundColor(ColorConstants.black);
		} else {
			setLineWidth(1);
			setForegroundColor(ColorConstants.black);
			name.setForegroundColor(ColorConstants.black);
		}
	}
	
}
