/*
 * Created on 04.06.2005
 * 
 * Copyright BMSTU 2005
 */
package ru.bmstu.iu5.opsk.export;

import ru.bmstu.iu5.opsk.path.Path;

/**
 * Simple path descriptor for pathes
 * 
 * @author Egorova Olga
 */
public class PathDescriptor {
	
	public String path = null;
	
	public float length = 0f;

	public int number;
	
	public float cost;
	
	public PathDescriptor(int num, Path path) {
		this.number = num;
		this.path = path.getPathAsString();
		this.length = path.calculateDelay();
		this.cost = path.getPathCost();
	}

}
