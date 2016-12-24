/*
 * Created on May 4, 2005
 * 
 * Copyright BMSTU
 */
package ru.bmstu.iu5.opsk.gef.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.PropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

import ru.bmstu.iu5.opsk.Messages;
import ru.bmstu.iu5.opsk.model.Node;
import ru.bmstu.iu5.opsk.path.Path;

/**
 * Node view element
 * 
 * @author Egorova Olga
 */
public class NodeElement extends ViewModel implements IPropertySource {
	
	private boolean isSourceNode = false;
	
	private boolean isDestinationNode = false;

	/**
	 * @return Returns the isDestinationNode.
	 */
	public boolean isDestinationNode() {
		return isDestinationNode;
	}
	/**
	 * @param isDestinationNode The isDestinationNode to set.
	 */
	void setDestinationNode(boolean value) {
		firePropertyChange("DESTINATION_NODE", new Boolean(isDestinationNode),new Boolean(value)); //$NON-NLS-1$
		this.isDestinationNode = value;
	}
	/**
	 * @return Returns the isSourceNode.
	 */
	public boolean isSourceNode() {
		return isSourceNode;
	}
	/**
	 * @param isSourceNode The isSourceNode to set.
	 */
	void setSourceNode(boolean value) {
		firePropertyChange("SOURCE_NODE", new Boolean(isSourceNode),new Boolean(value)); //$NON-NLS-1$
		this.isSourceNode = value;
	}
	
	private Node model = new Node(null); //$NON-NLS-1$

	private Point location = new Point(0, 0);

	private Dimension dimension = new Dimension(50, 50);

	public Dimension getDimension() {
		return dimension;
	}
	private List sourceConnections = new ArrayList();

	private List targetConnections = new ArrayList();

	public final static IPropertyDescriptor LOCATION_PROP = new PropertyDescriptor(
			"LOCATION", Messages.getString("ru.bmstu.iu5.opsk.gef.model.node.property.location")); //$NON-NLS-1$ //$NON-NLS-2$
	
	public final static TextPropertyDescriptor NAME_PROP = new TextPropertyDescriptor("NAME", Messages.getString("ru.bmstu.iu5.opsk.gef.model.node.property.name")); //$NON-NLS-1$ //$NON-NLS-2$
	
	public final static IPropertyDescriptor SOURCE_CONNECTIONS_PROP = new PropertyDescriptor("SOURCE", Messages.getString("ru.bmstu.iu5.opsk.gef.model.node.property.source-connections")); //$NON-NLS-1$ //$NON-NLS-2$

	public final static IPropertyDescriptor TARGET_CONNECTIONS_PROP = new PropertyDescriptor("TARGET", Messages.getString("ru.bmstu.iu5.opsk.gef.model.node.property.target-connections"));	 //$NON-NLS-1$ //$NON-NLS-2$
	
	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		if (location == null)
			throw new IllegalArgumentException(Messages.getString("ru.bmstu.iu5.opsk.gef.model.node.error.location-is-null")); //$NON-NLS-1$
		this.location = location;
		firePropertyChange(LOCATION_PROP, null, location);
	}

	public void addConnection(ConnectionElement conn) {
		if (conn == null || conn.getSource() == conn.getTarget()) {
			throw new IllegalArgumentException();
		}
		if (conn.getSource() == this) {
			sourceConnections.add(conn);
			firePropertyChange(SOURCE_CONNECTIONS_PROP, null, conn);
		} else if (conn.getTarget() == this) {
			targetConnections.add(conn);
			firePropertyChange(TARGET_CONNECTIONS_PROP, null, conn);
		}
	}

	public void removeConnection(ConnectionElement conn) {
		if (conn == null) {
			throw new IllegalArgumentException();
		}
		if (conn.getSource() == this) {
			sourceConnections.remove(conn);
			firePropertyChange(SOURCE_CONNECTIONS_PROP, conn, null);
		} else if (conn.getTarget() == this) {
			targetConnections.remove(conn);
			firePropertyChange(TARGET_CONNECTIONS_PROP, conn, null);
		}
	}

	public List getSourceConnections() {
		return new ArrayList(sourceConnections);
	}

	public List getTargetConnections() {
		return new ArrayList(targetConnections);
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
		return new IPropertyDescriptor[] { NodeElement.NAME_PROP };
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java.lang.Object)
	 */
	public Object getPropertyValue(Object id) {
		return String.valueOf(model.getId());
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
		location = new Point(40,40);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java.lang.Object, java.lang.Object)
	 */
	public void setPropertyValue(Object id, Object value) {
		String oldId = model.getId();
		model.setId((String) value);
		setIdentity((String)value);
		firePropertyChange(NAME_PROP, oldId, model.getId());
	}

	/**
	 * @param string
	 */
	public void setIdentity(String string) {
		String oldId = model.getId();
		model.setId(string);
		firePropertyChange(NAME_PROP, oldId, model.getId());
		for (Iterator iter = sourceConnections.iterator(); iter.hasNext();) {
			((ConnectionElement)iter.next()).updateName(getIdentity());
		}
		for (Iterator iter = targetConnections.iterator(); iter.hasNext();) {
			((ConnectionElement)iter.next()).updateName(getIdentity());
		}
	}

	/**
	 * @return
	 */
	public String getIdentity() {
		return model.getId();
	}

	/**
	 * This method will highlight/remove highlight if node is in given path
	 * 
	 * @param path
	 */
	public void highlight(Path path) {
		if (path.includes(this)) {
			firePropertyChange("HIGH", null, "ON"); //$NON-NLS-1$ //$NON-NLS-2$
		} else {
			firePropertyChange("HIGH", "ON", null); //$NON-NLS-1$ //$NON-NLS-2$
		}
		for (Iterator iter = sourceConnections.iterator(); iter.hasNext();) {
			((ConnectionElement)iter.next()).highlight(path);
		}
		for (Iterator iter = targetConnections.iterator(); iter.hasNext();) {
			((ConnectionElement)iter.next()).highlight(path);
		}
	}

	/**
	 * This method should be called before node is removed from the diagramm
	 * 
	 * @param network
	 */
	public List disconnectAll() {
		List res = new ArrayList(sourceConnections);
		for (Iterator iter = res.iterator(); iter.hasNext();) {
			ConnectionElement element = (ConnectionElement) iter.next();
			element.disconnect();			
		}
		List ret = new ArrayList(targetConnections);
		for (Iterator iter = ret.iterator(); iter.hasNext();) {
			ConnectionElement element = (ConnectionElement) iter.next();
			element.disconnect();
		}
		res.addAll(ret);
		return res;
	}

	/**
	 * Reconnect connections of the node 
	 */
	public void reconnectAll(List connections) {
		for (Iterator iter = connections.iterator(); iter.hasNext();) {
			ConnectionElement element = (ConnectionElement) iter.next();
			element.reconnect();
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Node: " + model.getId(); //$NON-NLS-1$
	}
	
}
