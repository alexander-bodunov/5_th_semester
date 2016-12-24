/*
 * Created on 11.05.2005
 * 
 * Copyright BMSTU 2005
 */
package ru.bmstu.iu5.opsk.path;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.PropertyDescriptor;

import ru.bmstu.iu5.opsk.Messages;
import ru.bmstu.iu5.opsk.computation.SimpleAlgorithm;
import ru.bmstu.iu5.opsk.gef.model.ConnectionElement;
import ru.bmstu.iu5.opsk.gef.model.NetworkElement;
import ru.bmstu.iu5.opsk.gef.model.NodeElement;

/**
 * This is a model class encasulating path calculated
 * 
 * @author Egorova Olga
 */
public class Path implements IPropertySource {
	
	public static final PropertyDescriptor PATH = new PropertyDescriptor("PATH", Messages.getString("ru.bmstu.iu5.opsk.path-list.column.path.property")); //$NON-NLS-1$ //$NON-NLS-2$
	
	public static final PropertyDescriptor TIME = new PropertyDescriptor("TIME", Messages.getString("ru.bmstu.iu5.opsk.path-list.column.length.property")); //$NON-NLS-1$ //$NON-NLS-2$
	
	public static final PropertyDescriptor COSTS = new PropertyDescriptor("COST", Messages.getString("ru.bmstu.iu5.opsk.path-list.column.cost.property")); //$NON-NLS-1$ //$NON-NLS-2$
	
	
	private int number;
	
	private List connections = new ArrayList();
	
	private NetworkElement network;
	
	public Path(NetworkElement net) {
		this.network = net;
	}

	/**
	 * @param element
	 * @return
	 */
	public boolean includes(NodeElement element) {
		for (Iterator iter = connections.iterator(); iter.hasNext();) {
			ConnectionElement connection = (ConnectionElement) iter.next();
			if (connection.getTarget().equals(element)) return true;
			if (connection.getSource().equals(element)) return true;
		}
		return false;
	}
	/**
	 * @param element
	 * @return
	 */
	public boolean includes(ConnectionElement element) {
		return connections.contains(element);
	}

	/**
	 * This method returns a copy of the current path as new object
	 * 
	 * @return
	 */
	public Path copy() {
		Path result = new Path(this.network);
		result.connections = new ArrayList(connections);
		return result;
	}

	/**
	 * @param connection
	 */
	public void addElement(ConnectionElement connection) {
		connections.add(connection);	
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Path:" + connections.toString(); //$NON-NLS-1$
	}

	/**
	 * This method calculates delay
	 * 
	 * @return
	 */
	public float calculateDelay() {
		return SimpleAlgorithm.computePathAvgTime(this);
	}

	
	/**
	 * @return Returns the network.
	 */
	public NetworkElement getNetwork() {
		return network;
	}
	
	/**
	 * This method returns string representation of the path
	 * as a sequence of node names
	 * 
	 * @return
	 */
	public String getPathAsString() {
		StringBuffer result = new StringBuffer();
		for (Iterator iter = connections.iterator(); iter.hasNext();) {
			ConnectionElement connection = (ConnectionElement) iter.next();
			if (result.length() == 0) {
				result.append(connection.getSource().getIdentity());
			}
			result.append("-"); //$NON-NLS-1$
			result.append(connection.getTarget().getIdentity());
		}
		return result.toString();
	}
	
	public float getPathCost() {
		float result = 0;
		float d = network.getModel().getChannelSpecificCosts();
		for (Iterator iter = connections.iterator(); iter.hasNext();) {
			ConnectionElement connection = (ConnectionElement) iter.next();
			result += connection.getModel().getThrougthput() * d;
		}
		return result;
	}

	/**
	 * Returns connections from the path
	 * 
	 * @return
	 */
	public List getConnections() {
		return connections;
	}
	
	/**
	 * @param number The number to set.
	 */
	public void setNumber(int number) {
		this.number = number;
	}
	
	/**
	 * @return Returns the number.
	 */
	public int getNumber() {
		return number;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.views.properties.IPropertySource#getEditableValue()
	 */
	public Object getEditableValue() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyDescriptors()
	 */
	public IPropertyDescriptor[] getPropertyDescriptors() {
		return new IPropertyDescriptor[] {PATH, TIME, COSTS};
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java.lang.Object)
	 */
	public Object getPropertyValue(Object id) {
		if (PATH.getId().equals(id)) {
			return getPathAsString();
		}
		if (TIME.getId().equals(id)) {
			return String.valueOf(this.calculateDelay());
		}
		if (COSTS.getId().equals(id)) {
			return String.valueOf(this.getPathCost());
		}
		return null;
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
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java.lang.Object, java.lang.Object)
	 */
	public void setPropertyValue(Object id, Object value) {
		// TODO Auto-generated method stub
		
	}
}
