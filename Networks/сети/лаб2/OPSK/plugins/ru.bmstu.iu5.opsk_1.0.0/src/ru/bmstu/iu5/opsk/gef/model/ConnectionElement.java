/*
 * Created on May 4, 2005
 * 
 * Copyright BMSTU
 */
package ru.bmstu.iu5.opsk.gef.model;

import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

import ru.bmstu.iu5.opsk.Messages;
import ru.bmstu.iu5.opsk.model.Connection;
import ru.bmstu.iu5.opsk.path.Path;

/**
 * Connection element representation in view model
 *
 * @author Egorova Olga
 */
public class ConnectionElement extends ViewModel implements IPropertySource {
	
	private Connection model = new Connection();

	private NodeElement source = null;
	
	private NodeElement target = null;

	private boolean isConnected = false;
	
	private boolean isValid = true;
	
	public ConnectionElement(NodeElement s, NodeElement t) {
		source = s;
		target = t;
		reconnect();
	}
	
	/**
	 * @return
	 */
	public NodeElement getSource() {
		return source;
	}

	/**
	 * @return
	 */
	public NodeElement getTarget() {
		return target;
	}

	/**
	 * 
	 */
	public void disconnect() {
		if (isConnected) {
			source.removeConnection(this);
			target.removeConnection(this);
			isConnected = false;
		}
	}

	/**
	 * 
	 */
	public void reconnect() {
		if (!isConnected) {
			source.addConnection(this);
			target.addConnection(this);
			isConnected = true;
		}
	}

//
//
//	IPropertySource methods	
//
//

	public static final TextPropertyDescriptor THROUGHPUT = new TextPropertyDescriptor("THROUGHPUT", Messages.getString("ru.bmstu.iu5.opsk.gef.model.connectionproperty.throughput")); //$NON-NLS-1$ //$NON-NLS-2$
	
	public static final TextPropertyDescriptor LENGTH = new TextPropertyDescriptor("LENGTH", Messages.getString("ru.bmstu.iu5.opsk.gef.model.connectionproperty.length")); //$NON-NLS-1$ //$NON-NLS-2$
	
	public static final TextPropertyDescriptor INTENSIVITY = new TextPropertyDescriptor("INTENS", Messages.getString("ru.bmstu.iu5.opsk.gef.model.connectionproperty.intensivity")); //$NON-NLS-1$ //$NON-NLS-2$

	private static final long MAX_THROUGHPUT_VALUE = 2048 * 1024;
	
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.views.properties.IPropertySource#getEditableValue()
	 */
	public Object getEditableValue() {
		return ""; //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyDescriptors()
	 */
	public IPropertyDescriptor[] getPropertyDescriptors() {
		ICellEditorValidator integerValidator = new ICellEditorValidator() {
			public String isValid(Object value) {
				int val = 0;
				String errorMsg = Messages.getString("ru.bmstu.iu5.opsk.gef.model.connectionerror.integer-value"); //$NON-NLS-1$
				try {
					val = Integer.parseInt((String) value);
				} catch (NumberFormatException e) {
					return errorMsg;
				}
				if (val < 0) return errorMsg;
				return null;
			}
		};		
		ICellEditorValidator floatValidator = new ICellEditorValidator() {
			public String isValid(Object value) {
				float val = 0;
				String errorMsg = Messages
						.getString("ru.bmstu.iu5.opsk.gef.model.network.error.non-float-value"); //$NON-NLS-1$
				try {
					val = Float.parseFloat((String) value);
				} catch (NumberFormatException e) {
					return errorMsg;
				}
				if (val < 0)
					return errorMsg;
				return null;
			}
		};
		//THROUGHPUT.setValidator(floatValidator);
		LENGTH.setValidator(integerValidator);
		INTENSIVITY.setValidator(floatValidator);
		THROUGHPUT.setAlwaysIncompatible(true);
		return new IPropertyDescriptor[] {INTENSIVITY, LENGTH};
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java.lang.Object)
	 */
	public Object getPropertyValue(Object id) {
		if (id.equals(THROUGHPUT.getId())) {
			return String.valueOf(model.getThrougthput());
		}
		if (id.equals(INTENSIVITY.getId())) {
			return String.valueOf(model.getIntensivity());
		}
		if (id.equals(LENGTH.getId())) {
			return String.valueOf(model.getLength());
		}
		return "UNKNOWN_ID: " + id; //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.views.properties.IPropertySource#isPropertySet(java.lang.Object)
	 */
	public boolean isPropertySet(Object id) {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.views.properties.IPropertySource#resetPropertyValue(java.lang.Object)
	 */
	public void resetPropertyValue(Object id) {
		// Do nothing
	}
	
	/**
	 * This metod sets the throughput of underlying model object
	 * it also doing rounding value to long value that can be represented as
	 * channel bandwidth like 1024, 2048, etc
	 * From now the rounding isn't valid anymore
	 * @param throughput
	 */
	public void setThroughput(float throughput) {
		long value = Math.round(throughput);
//		long[] standartValues = new long[] {300,600, 1200, 2400, 4800, 9600, 19200, 38400};
//		long granularity = 64 * 1024;
//		for (int i = 0; i < standartValues.length; i++) {
//			if (throughput < standartValues[i]) {
//				value = standartValues[i];
//				break;
//			}
//		}
//		if (value == 0) {
//			value = (long) ((throughput - (throughput % granularity)) + granularity);
//		}
		// Now we need to limit throughput to the maximum value
		if (value > MAX_THROUGHPUT_VALUE) {
			isValid = false;
			value = MAX_THROUGHPUT_VALUE; 
		} else {
			isValid = true;
		}
		model.setThrougthput(value);
		firePropertyChange(THROUGHPUT, null, new Long(model.getThrougthput()));
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java.lang.Object, java.lang.Object)
	 */
	public void setPropertyValue(Object id, Object value) {
		if (id.equals(INTENSIVITY.getId())) {
			model.setIntensivity(Float.parseFloat((String) value));
		}
		if (id.equals(LENGTH.getId())) {
			model.setLength(Integer.parseInt((String) value));
		}
	}

	/**
	 * 
	 */
	public void updateName(String newIdentity) {
		firePropertyChange("NAME", null, newIdentity); //$NON-NLS-1$
	}

	/**
	 * Highligt/no-highlight connection if it is in the path
	 * 
	 * @param path
	 */
	public void highlight(Path path) {
		if (path.includes(this)) {
			firePropertyChange("HIGH", null, "ON"); //$NON-NLS-1$ //$NON-NLS-2$
		} else {
			firePropertyChange("HIGH", "ON", null);			 //$NON-NLS-1$ //$NON-NLS-2$
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object arg0) {
		if (arg0 == null) return false;
		if (!(arg0 instanceof ConnectionElement)) return false;
		ConnectionElement arg = (ConnectionElement) arg0;
		return arg.source.equals(this.source) && arg.target.equals(this.target);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Connection: " + source.toString() + " -> " + target.toString(); //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * @return
	 */
	public Connection getModel() {
		return model;
	}
	
	/**
	 * This method returns true when channel satisfy with the channel throughput requirements
	 * @return Returns the isValid.
	 */
	public boolean isValid() {
		return isValid;
	}
}
