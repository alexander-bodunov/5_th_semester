/*
 * Created on May 4, 2005
 * 
 * Copyright BMSTU
 */
package ru.bmstu.iu5.opsk.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This is a network model class
 * 
 * @author Egorova Olga
 */
public class Network implements Serializable {
	
	/**
	 * @return Returns the channelCoasts.
	 */
	public float getChannelSpecificDelay() {
		return channelSpecificDelay;
	}
	/**
	 * @param channelCoasts The channelCoasts to set.
	 */
	public void setChannelSpecificDelay(float channelCoasts) {
		this.channelSpecificDelay = channelCoasts;
	}
	/**
	 * @return Returns the coasts.
	 */
	public float getChannelSpecificCosts() {
		return channelSpecificCosts;
	}
	/**
	 * @param coasts The coasts to set.
	 */
	public void setChannelSpecificCosts(float coasts) {
		this.channelSpecificCosts = coasts;
	}
	/**
	 * @return Returns the delayTime.
	 */
	public float getNodeSpecificDelay() {
		return nodeSpecificDelay;
	}
	/**
	 * @param delayTime The delayTime to set.
	 */
	public void setNodeSpecificDelay(float delayTime) {
		this.nodeSpecificDelay = delayTime;
	}
	/**
	 * @return Returns the deliveryTime.
	 */
	public float getMaximumDeliveryTime() {
		return maximumDeliveryTime;
	}
	/**
	 * @param deliveryTime The deliveryTime to set.
	 */
	public void setMaximumDeliveryTime(float deliveryTime) {
		this.maximumDeliveryTime = deliveryTime;
	}
	/**
	 * @return Returns the packetLength.
	 */
	public int getPacketLength() {
		return packetLength;
	}
	/**
	 * @param packetLength The packetLength to set.
	 */
	public void setPacketLength(int packetLength) {
		this.packetLength = packetLength;
	}
	private int packetLength = 32;

	private float maximumDeliveryTime = 1;

	private float nodeSpecificDelay = 1;

	private float channelSpecificCosts = 1.0f;

	private float channelSpecificDelay = 1.0f;

	protected List nodes = new ArrayList();

	public boolean addNode(Node addNode) {
		return this.nodes.add(addNode);
	}

	public boolean deleteNode(Node deleteNode) {
		return this.nodes.remove(deleteNode);
	}

}
