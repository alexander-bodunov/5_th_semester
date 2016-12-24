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
 * This is a Node model class.
 * 
 * @author Egorova Olga
 */
public class Node implements Serializable {
	
	public Node(String id) {
		this.id = id;
	}

	private String id;

	private List inConnections = new ArrayList();

	private List outConnections = new ArrayList();

	public void setId(String id) {
		this.id = id;
	}

	public void addOutConnection(Connection outConnection) {
		this.outConnections.add(outConnection);
	}

	public void addInConnection(Connection inConnection) {
		this.inConnections.add(inConnection);
	}

	public void deleteOutConnection(Connection outConnection) {
		this.outConnections.remove(outConnection);
	}

	public void deleteInConnection(Connection inConnection) {
		this.inConnections.remove(inConnection);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object arg0) {
		if (arg0 instanceof Node) {
			return this.id == ((Node) arg0).id;
		}
		return super.equals(arg0);
	}

	/**
	 * @return Returns the id.
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return Returns the inConnections.
	 */
	public List getInConnections() {
		return inConnections;
	}

	/**
	 * @return Returns the outConnections.
	 */
	public List getOutConnections() {
		return outConnections;
	}

}
