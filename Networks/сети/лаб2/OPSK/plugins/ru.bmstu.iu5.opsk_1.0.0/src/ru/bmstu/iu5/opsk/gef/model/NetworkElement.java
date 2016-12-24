/*
 * Created on May 4, 2005
 * 
 * Copyright BMSTU
 */
package ru.bmstu.iu5.opsk.gef.model;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

import ru.bmstu.iu5.opsk.Messages;
import ru.bmstu.iu5.opsk.computation.SimpleAlgorithm;
import ru.bmstu.iu5.opsk.model.Network;
import ru.bmstu.iu5.opsk.path.Path;
import ru.bmstu.iu5.opsk.path.PathList;

/**
 * Network element
 * 
 * @author Egorova Olga
 */
public class NetworkElement extends ViewModel implements IPropertySource {

	private Network model = new Network();

	private Collection nodes = new Vector();

	private int uniqueNumber = 1;

	public boolean addChild(NodeElement s) {
		if (s != null && nodes.add(s)) {
			if (s.getIdentity() == null)
				s.setIdentity(generateIdentity());
			firePropertyChange("ADD_CHILD", null, s); //$NON-NLS-1$
			return true;
		}
		return false;
	}

	/**
	 * @return
	 */
	private String generateIdentity() {
		return String.valueOf(uniqueNumber++);
	}

	public List getChildren() {
		return new Vector(nodes);
	}

	public boolean removeChild(NodeElement s) {
		if (s != null && nodes.remove(s)) {
			firePropertyChange("REMOVE_CHILD", s, null); //$NON-NLS-1$
			return true;
		}
		return false;
	}

	//
	//
	//  PropertySource methods
	//
	//

	public static final TextPropertyDescriptor PAKET_LENGTH = new TextPropertyDescriptor(
			"PLENGTH", Messages.getString("ru.bmstu.iu5.opsk.gef.model.network.property.packet-length")); //$NON-NLS-1$ //$NON-NLS-2$

	public static final TextPropertyDescriptor DELIVERY_TIME = new TextPropertyDescriptor(
			"DELIVERY", Messages.getString("ru.bmstu.iu5.opsk.gef.model.network.property.delivery-time")); //$NON-NLS-1$ //$NON-NLS-2$

	public static final TextPropertyDescriptor DELAY_TIME = new TextPropertyDescriptor(
			"DELAY", Messages.getString("ru.bmstu.iu5.opsk.gef.model.network.property.delay-time")); //$NON-NLS-1$ //$NON-NLS-2$

	public static final TextPropertyDescriptor COSTS = new TextPropertyDescriptor(
			"COSTS", Messages.getString("ru.bmstu.iu5.opsk.gef.model.network.property.cost-per-unit")); //$NON-NLS-1$ //$NON-NLS-2$

	public static final TextPropertyDescriptor CHANNEL_DELAY = new TextPropertyDescriptor(
			"CHANNEL_DELAY", Messages.getString("ru.bmstu.iu5.opsk.gef.model.network.property.specific-channel-delay")); //$NON-NLS-1$ //$NON-NLS-2$

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#getEditableValue()
	 */
	public Object getEditableValue() {
		return ""; //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyDescriptors()
	 */
	public IPropertyDescriptor[] getPropertyDescriptors() {
		ICellEditorValidator integerValidator = new ICellEditorValidator() {
			public String isValid(Object value) {
				int val = 0;
				String errorMsg = Messages
						.getString("ru.bmstu.iu5.opsk.gef.model.network.error.non-integer-value"); //$NON-NLS-1$
				try {
					val = Integer.parseInt((String) value);
				} catch (NumberFormatException e) {
					return errorMsg;
				}
				if (val < 0)
					return errorMsg;
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
		ICellEditorValidator plengthValidator = new ICellEditorValidator() {
			public String isValid(Object value) {
				int val = 0;
				String errMsg1 = Messages.getString("ru.bmstu.iu5.opsk.validation.packet-width.example-values");  //$NON-NLS-1$
				try {
					val = Integer.parseInt((String)value);
				} catch (NumberFormatException e) {
					return errMsg1;
				}
				if (val < 0) return errMsg1;
				int c = (int) Math.floor(val / (double)32);
				if (c * 32 == val) return null;
				return Messages.getString("ru.bmstu.iu5.opsk.validation.packet-width.nearest") + ((c + 1) * 32); //$NON-NLS-1$
			}
		};
		DELIVERY_TIME.setValidator(floatValidator);
		DELAY_TIME.setValidator(floatValidator);
		COSTS.setValidator(floatValidator);
		CHANNEL_DELAY.setValidator(floatValidator);
		PAKET_LENGTH.setValidator(plengthValidator);
		return new IPropertyDescriptor[] { CHANNEL_DELAY, COSTS, DELAY_TIME,
				DELIVERY_TIME, PAKET_LENGTH };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java.lang.Object)
	 */
	public Object getPropertyValue(Object id) {
		if (id.equals(PAKET_LENGTH.getId())) {
			return String.valueOf(model.getPacketLength());
		}
		if (id.equals(DELIVERY_TIME.getId())) {
			return String.valueOf(model.getMaximumDeliveryTime());
		}
		if (id.equals(DELAY_TIME.getId())) {
			return String.valueOf(model.getNodeSpecificDelay());
		}
		if (id.equals(COSTS.getId())) {
			return String.valueOf(model.getChannelSpecificCosts());
		}
		if (id.equals(CHANNEL_DELAY.getId())) {
			return String.valueOf(model.getChannelSpecificDelay());
		}
		return "UNKNOWN_ID: " + id.toString(); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#isPropertySet(java.lang.Object)
	 */
	public boolean isPropertySet(Object id) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#resetPropertyValue(java.lang.Object)
	 */
	public void resetPropertyValue(Object id) {
		if (id.equals(PAKET_LENGTH.getId())) {
			model.setPacketLength(16);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java.lang.Object,
	 *      java.lang.Object)
	 */
	public void setPropertyValue(Object id, Object value) {
		if (id.equals(PAKET_LENGTH.getId())) {
			int val = Integer.parseInt((String)value);
			model.setPacketLength(val);
		}
		if (id.equals(DELIVERY_TIME.getId())) {
			model.setMaximumDeliveryTime(Float.parseFloat((String) value));
		}
		if (id.equals(DELAY_TIME.getId())) {
			model.setNodeSpecificDelay(Float.parseFloat((String) value));
		}
		if (id.equals(COSTS.getId())) {
			model.setChannelSpecificCosts(Float.parseFloat((String) value));
		}
		if (id.equals(CHANNEL_DELAY.getId())) {
			model.setChannelSpecificDelay(Float.parseFloat((String) value));
		}
	}

	/**
	 * This method will traverse through given list of pathes and call notify
	 * connections that they need to be highlithed
	 * 
	 * @param pathes
	 */
	public void highlight(List pathes) {
		for (Iterator iter = pathes.iterator(); iter.hasNext();) {
			Path path = (Path) iter.next();
			for (Iterator iterator = nodes.iterator(); iterator.hasNext();) {
				NodeElement node = (NodeElement) iterator.next();
				node.highlight(path);
			}
		}
	}

	/**
	 * Source node
	 * 
	 * @param node
	 */
	public NodeElement setSourceNode(NodeElement node) {
		// If the node isn't here from this network
		if (node != null && !nodes.contains(node))
			return null;
		NodeElement old = getSourceNode();
		if (old != null)
			old.setSourceNode(false);
		if (node != null)
			node.setSourceNode(true);
		return old;
	}

	/**
	 * Returns old destination node
	 * 
	 * @param node
	 * @return
	 */
	public NodeElement setDestinationNode(NodeElement node) {
		// If the node isn't here from this network
		if (node != null && !nodes.contains(node))
			return null;
		NodeElement old = getDestinationNode();
		if (old != null)
			old.setDestinationNode(false);
		if (node != null)
			node.setDestinationNode(true);
		return old;
	}

	/**
	 * This method calculates all possible pathes in the graph
	 * 
	 * @return
	 */
	public PathList getPaths() {
		NodeElement startingNode = getSourceNode();
		if (startingNode == null)
			return new PathList();
		//			throw new PathCalculationException("no.source.node");
		if (getDestinationNode() == null)
			return new PathList();
		//			throw new PathCalculationException("no.destination.node");
		PathList result = new PathList();
		traverse(startingNode, new Path(this), result);
		return result;
	}

	private void traverse(NodeElement node, Path currentPath, PathList result) {
		//		System.out.println("Traversing through node:" + node.getIdentity());
		if (node.isDestinationNode()) {
			result.addPath(currentPath);
			return;
		}
		List connections = node.getSourceConnections();
		for (Iterator iter = connections.iterator(); iter.hasNext();) {
			ConnectionElement connection = (ConnectionElement) iter.next();
			if (!currentPath.includes(connection)) {
				// FIXME Here we should check wherever given traffic is possible
				// to tansfer through the given connection
				if (connection.isValid()) {
					Path path = currentPath.copy();
					path.addElement(connection);
					traverse(connection.getTarget(), path, result);
				} else {
					System.err.println("Connection " + connection + " is invalid"); //$NON-NLS-1$ //$NON-NLS-2$
				}

			}
		}
	}

	/**
	 * Returns destination node in the this network or null if no found
	 * 
	 * @return
	 */
	private NodeElement getDestinationNode() {
		NodeElement result = null;
		for (Iterator iter = nodes.iterator(); iter.hasNext();) {
			NodeElement element = (NodeElement) iter.next();
			if (element.isDestinationNode()) {
				result = element;
			}
		}
		return result;
	}

	/**
	 * Returns source node or null if there is no source node found
	 * 
	 * @return
	 */
	private NodeElement getSourceNode() {
		NodeElement result = null;
		for (Iterator iter = nodes.iterator(); iter.hasNext();) {
			NodeElement element = (NodeElement) iter.next();
			if (element.isSourceNode()) {
				result = element;
			}
		}
		return result;
	}

	public Network getModel() {
		return model;
	}

	/**
	 * This method calculates connections throughput
	 * returns overal network cost
	 */
	public float calculate() {
		SimpleAlgorithm.compute(this);
		float result = 0;
		float d = getModel().getChannelSpecificCosts();
		for (Iterator iter = nodes.iterator(); iter.hasNext();) {
			NodeElement element = (NodeElement) iter.next();
			for (Iterator iterator = element.getSourceConnections().iterator(); iterator.hasNext();) {
				ConnectionElement connectionEl = (ConnectionElement) iterator.next();
				result += connectionEl.getModel().getThrougthput() * d;
			}
		}
		return result;
	}

}
