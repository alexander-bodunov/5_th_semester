/*
 * Created on May 4, 2005
 * 
 * Copyright BMSTU
 */
package ru.bmstu.iu5.opsk.model;

import java.io.Serializable;


/**
 * 
 * 
 * @author Egorova Olga
 */
public class Connection implements Serializable {
	private Node source=null;
	private Node target=null;
	
	private long througthput = 1;

	private int length = 1;

	private float intensivity = 1;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public Connection(Node source, Node target) {
		if (source.equals( target)) throw new IllegalArgumentException("Loop connections are not allowed"); //$NON-NLS-1$
		this.source = source;
		this.target = target;
		
		source.addOutConnection( this);
		target.addInConnection( this);
		
	}

	/**
	 * 
	 */
	public Connection() {
		// This constructor is for ussage inside connectionElement model
	}

	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		return super.equals(arg0);
	}

	/**
	 * @return Returns the intensivity.
	 */
	public float getIntensivity() {
		return intensivity;
	}
	/**
	 * @param intensivity The intensivity to set.
	 */
	public void setIntensivity(float intensivity) {
		this.intensivity = intensivity;
	}
	/**
	 * @return Returns the length.
	 */
	public int getLength() {
		return length;
	}
	/**
	 * @param length The length to set.
	 */
	public void setLength(int length) {
		this.length = length;
	}
	/**
	 * @return Returns the througthput.
	 */
	public long getThrougthput() {
		return througthput;
	}
	/**
	 * @param througthput The througthput to set.
	 */
	public void setThrougthput(long througthput) {
		this.througthput = througthput;
	}

}
