/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package ru.bmstu.iu5.opsk.gef;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.palette.ConnectionCreationToolEntry;
import org.eclipse.gef.palette.MarqueeToolEntry;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.SelectionToolEntry;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gef.requests.SimpleFactory;
import org.eclipse.jface.resource.ImageDescriptor;

import ru.bmstu.iu5.opsk.Messages;
import ru.bmstu.iu5.opsk.gef.model.NodeElement;
import ru.bmstu.iu5.opsk.rpc.RPCPlugin;

/**
 * Handles the creation of the palette for the Flow Editor.
 * 
 * @author Daniel Lee
 */
public class NetworkEditorPaletteFactory {

	public static ImageDescriptor nodeIcon;

	public static ImageDescriptor connectionIcon;

	public static ImageDescriptor networkIcon;

	static {
		URL url = null;
		url = RPCPlugin.getDefault().getBundle().getEntry("/icons/node.gif"); //$NON-NLS-1$
		nodeIcon = ImageDescriptor.createFromURL(url);
		url = RPCPlugin.getDefault().getBundle().getEntry(
				"/icons/arrow.gif"); //$NON-NLS-1$
		connectionIcon = ImageDescriptor.createFromURL(url);
		url = RPCPlugin.getDefault().getBundle().getEntry(
		"/icons/network.gif"); //$NON-NLS-1$
		networkIcon = ImageDescriptor.createFromURL(url);
	}

	private static List createCategories(PaletteRoot root) {
		List categories = new ArrayList();
		categories.add(createControlGroup(root));
		categories.add(createComponentsDrawer());
		return categories;
	}

	private static PaletteContainer createComponentsDrawer() {

		PaletteDrawer drawer = new PaletteDrawer(
				Messages
						.getString("ru.bmstu.iu5.opsk.gef.palette.drawler.components"), null); //$NON-NLS-1$

		List entries = new ArrayList();

		CombinedTemplateCreationEntry combined = new CombinedTemplateCreationEntry(
				Messages
						.getString("ru.bmstu.iu5.opsk.gef.palette.tools.node.name"), //$NON-NLS-1$
				Messages
						.getString("ru.bmstu.iu5.opsk.gef.palette.tools.node.description"), //$NON-NLS-1$
				NodeElement.class, new SimpleFactory(NodeElement.class),
				nodeIcon, nodeIcon);
		entries.add(combined);

		ToolEntry tool = new ConnectionCreationToolEntry(
				Messages
						.getString("ru.bmstu.iu5.opsk.gef.palette.tools.connection.name"), //$NON-NLS-1$
				Messages
						.getString("ru.bmstu.iu5.opsk.gef.palette.tools.connection.description"), //$NON-NLS-1$
				null, connectionIcon, connectionIcon);
		entries.add(tool);
		
		drawer.addAll(entries);
		return drawer;
	}

	private static PaletteContainer createControlGroup(PaletteRoot root) {
		PaletteGroup controlGroup = new PaletteGroup(Messages
				.getString("ru.bmstu.iu5.opsk.gef.palette.group.control-group")); //$NON-NLS-1$

		List entries = new ArrayList();

		ToolEntry tool = new SelectionToolEntry();
		entries.add(tool);
		root.setDefaultEntry(tool);

		tool = new MarqueeToolEntry();
		entries.add(tool);

		controlGroup.addAll(entries);
		return controlGroup;
	}

	/**
	 * Creates the PaletteRoot and adds all Palette elements.
	 * 
	 * @return the root
	 */
	public static PaletteRoot createPalette() {
		PaletteRoot flowPalette = new PaletteRoot();
		flowPalette.addAll(createCategories(flowPalette));
		return flowPalette;
	}

}
