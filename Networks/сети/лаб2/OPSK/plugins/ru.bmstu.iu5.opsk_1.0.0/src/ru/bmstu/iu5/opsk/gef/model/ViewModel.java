/*
 * Created on May 4, 2005
 * 
 * Copyright BMSTU
 */
package ru.bmstu.iu5.opsk.gef.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

import org.eclipse.ui.views.properties.IPropertyDescriptor;

/**
 * 
 *
 * @author Egorova Olga
 */
public abstract class ViewModel implements Serializable {

	private transient PropertyChangeSupport pcsDelegate = new PropertyChangeSupport(
			this);

	public synchronized void addPropertyChangeListener(PropertyChangeListener l) {
		if (l == null) {
			throw new IllegalArgumentException();
		}
		pcsDelegate.addPropertyChangeListener(l);
	}

	protected void firePropertyChange(IPropertyDescriptor property, Object oldValue,
			Object newValue) {
		firePropertyChange(String.valueOf(property.getId()), oldValue, newValue);
	}

	protected void firePropertyChange(String property, Object oldValue,
			Object newValue) {
		if (pcsDelegate.hasListeners(property)) {
			pcsDelegate.firePropertyChange(property, oldValue, newValue);
		}
	}
	
	private void readObject(ObjectInputStream in) throws IOException,
			ClassNotFoundException {
		in.defaultReadObject();
		pcsDelegate = new PropertyChangeSupport(this);
	}

	public synchronized void removePropertyChangeListener(
			PropertyChangeListener l) {
		if (l != null) {
			pcsDelegate.removePropertyChangeListener(l);
		}
	}
	
}
