/*
 * Created on 04.06.2005
 * 
 * Copyright BMSTU 2005
 */
package ru.bmstu.iu5.opsk.gef.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.MidpointLocator;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.PositionConstants;

/**
 * Figure for connection element
 * 
 * @author Egorova Olga
 */
public class ConnectionFigure extends PolylineConnection {

	private Label label = new Label();
	
	private boolean highlihghted = false;
	
	public ConnectionFigure() {
		super();
		setTargetDecoration(new PolygonDecoration());
		MidpointLocator locator = new MidpointLocator(this,0);
		locator.setRelativePosition(PositionConstants.EAST_WEST);
		locator.setGap(20);
		add(label, locator);
	}
	
	public void setThroughput(long value) {
		label.setText(convertToKB(value));
	}
	
	/**
	 * This function converts long bandwidth in bytes/second value
	 * to KB/sec if needed 
	 * 
	 * @param value
	 * @return
	 */
	private static final String convertToKB(long value) {
		String result = String.valueOf(value) + " b/s"; //$NON-NLS-1$
		if (value > 1000) {
			result = String.valueOf(value / 1000f) + " Kb/s"; //$NON-NLS-1$
		}
		if (value > (1000000)) {
			result = String.valueOf(value / (1000000)) + "Mb/s"; //$NON-NLS-1$
		}
		return result;
	}


	
	public void setHighlighted(boolean isHigh) {
		if (isHigh == highlihghted) return;
		if (isHigh) {
			setForegroundColor(ColorConstants.red);
			setLineWidth(3);
		} else {
			setForegroundColor(ColorConstants.black);
			setLineWidth(1);					
		}
		highlihghted = isHigh;
	}
	
}
