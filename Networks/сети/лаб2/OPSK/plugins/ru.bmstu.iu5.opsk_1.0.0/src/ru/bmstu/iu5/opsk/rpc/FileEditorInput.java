/*
 * Created on 06.05.2005
 * 
 * Copyright BMSTU 2005
 */
package ru.bmstu.iu5.opsk.rpc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import ru.bmstu.iu5.opsk.Messages;
import ru.bmstu.iu5.opsk.gef.model.NetworkElement;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * This class represents functionality for loading/saving model from/to file
 * and it is an inpit for NetworkEditor
 * 
 * @author Egorova Olga
 */
public class FileEditorInput implements IEditorInput {

	private File file = null;
	
	XStream xstream = new XStream(new DomDriver());
	
	public FileEditorInput(File file) {
		this.file = file;
	}
	
	public FileEditorInput() {
		// This constructor construct new file
	}
	
	public boolean isNewlyCreated() {
		return file == null;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.IEditorInput#exists()
	 */
	public boolean exists() {
		if (file == null) return false;
		return file.exists();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IEditorInput#getImageDescriptor()
	 */
	public ImageDescriptor getImageDescriptor() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IEditorInput#getName()
	 */
	public String getName() {
		if (file == null) return Messages.getString("ru.bmstu.iu5.opsk.rpc.unnamed-file-name"); //$NON-NLS-1$
		return file.getName();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IEditorInput#getPersistable()
	 */
	public IPersistableElement getPersistable() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IEditorInput#getToolTipText()
	 */
	public String getToolTipText() {
		if (file == null) return Messages.getString("ru.bmstu.iu5.opsk.rpc.unnamed-file-name"); //$NON-NLS-1$
		return file.getAbsolutePath();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
	 */
	public Object getAdapter(Class adapter) {
		return null;
	}

	/**
	 * This method will retrieve diagram
	 * from file
	 * 
	 * @return
	 * @throws FileNotFoundException
	 */
	public NetworkElement getDiagram() throws FileNotFoundException {
		if ((file == null) || (!file.canRead())) return new NetworkElement();
		NetworkElement result = (NetworkElement) xstream.fromXML(new FileReader(file));
		return result;
	}

	/**
	 * This method will save diagramm to the file
	 * 
	 * @param diagram
	 * @throws IOException
	 */
	public void save(NetworkElement diagram) throws IOException {
		xstream.toXML(diagram, new FileWriter(file));
	}

	/**
	 * @param fileName
	 */
	public void setFileName(String fileName) {
		file = new File(fileName);		
	}

}
