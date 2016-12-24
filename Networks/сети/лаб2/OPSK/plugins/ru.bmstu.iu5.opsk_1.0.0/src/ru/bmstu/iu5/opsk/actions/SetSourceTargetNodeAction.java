/*
 * Created on 07.05.2005
 * 
 * Copyright BMSTU 2005
 */
package ru.bmstu.iu5.opsk.actions;

import java.net.URL;

import org.eclipse.gef.commands.CommandStack;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.ui.IWorkbenchWindow;

import ru.bmstu.iu5.opsk.Messages;
import ru.bmstu.iu5.opsk.gef.commands.SetDestinationNodeCommand;
import ru.bmstu.iu5.opsk.gef.commands.SetSourceNodeCommand;
import ru.bmstu.iu5.opsk.gef.commands.SetSpecificNodeCommand;
import ru.bmstu.iu5.opsk.gef.model.NetworkElement;
import ru.bmstu.iu5.opsk.gef.model.NodeElement;
import ru.bmstu.iu5.opsk.gef.parts.NodeEditPart;
import ru.bmstu.iu5.opsk.rpc.RPCPlugin;

/**
 * This action sets a node as source/target node
 * 
 * @author Egorova Olga
 */
public class SetSourceTargetNodeAction extends Action implements
		ISelectionChangedListener {

	private static ImageDescriptor sourceImage;

	private static ImageDescriptor destinationImage;

	private boolean isDetinationAction = false;

	private NodeEditPart selected = null;

	static {
		URL sourceUrl = null;
		sourceUrl = RPCPlugin.getDefault().getBundle().getEntry(
				"/icons/sourceNode.gif"); //$NON-NLS-1$
		sourceImage = ImageDescriptor.createFromURL(sourceUrl);

		URL targetUrl = null;
		targetUrl = RPCPlugin.getDefault().getBundle().getEntry(
				"/icons/sourceNode.gif"); //$NON-NLS-1$
		destinationImage = ImageDescriptor.createFromURL(targetUrl);
	}

	IWorkbenchWindow window;

	public SetSourceTargetNodeAction() {
		super(Messages.getString("ru.bmstu.iu5.opsk.actions.set-source-node.name"), IAction.AS_CHECK_BOX); 
		setImageDescriptor(sourceImage);
		setDescription(Messages
				.getString("ru.bmstu.iu5.opsk.actions.open-filedescription")); //$NON-NLS-1$
		setToolTipText(Messages
				.getString("ru.bmstu.iu5.opsk.actions.open-filetooltip")); //$NON-NLS-1$
	}

	public SetSourceTargetNodeAction(Object isSourceNodeAction) {
		super(Messages.getString("ru.bmstu.iu5.opsk.actions.set-dest-node"), IAction.AS_CHECK_BOX); //$NON-NLS-1$
		setImageDescriptor(destinationImage);
		isDetinationAction = true;
		setDescription(Messages
				.getString("ru.bmstu.iu5.opsk.actions.open-filedescription")); //$NON-NLS-1$
		setToolTipText(Messages
				.getString("ru.bmstu.iu5.opsk.actions.open-filetooltip")); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.action.Action#run()
	 */
	public void run() {
		if (selected == null)
			return;
		CommandStack stack = selected.getViewer().getEditDomain().getCommandStack();
		NetworkElement network = (NetworkElement) selected.getParent()
				.getModel();
		SetSpecificNodeCommand cmd = null;
		if (isDetinationAction) {
			cmd = new SetDestinationNodeCommand();
		} else {
			cmd = new SetSourceNodeCommand();
			//network.setSourceNode((NodeElement) selected.getModel());
		}
		cmd.setNetwork(network);
		cmd.setNode((NodeElement)selected.getModel());
		stack.execute(cmd);
		updateActionStatus();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.actions.ActionFactory.IWorkbenchAction#dispose()
	 */
	public void dispose() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
	 */
	public void selectionChanged(SelectionChangedEvent event) {
		if ((event.getSelection() != null)
				&& (event.getSelection() instanceof IStructuredSelection)) {
			IStructuredSelection sel = (IStructuredSelection) event
					.getSelection();
			if (sel.size() == 1) {
				if (sel.getFirstElement() instanceof NodeEditPart) {
					selected = ((NodeEditPart) sel.getFirstElement());
					updateActionStatus();
					return;
				}
			}
		}
		selected = null;
		setEnabled(false);
		setChecked(false);
	}

	/**
	 * This method updates menu selection status
	 *  
	 */
	public void updateActionStatus() {
		if (selected == null) return;
		NodeElement node = (NodeElement) selected.getModel();
		if (isDetinationAction) {
			// Set as Destination action
			if (node.isDestinationNode()) {
				setChecked(true);
				setEnabled(false);
				return;
			} else {
				setChecked(false);
				setEnabled(true);
				return;
			}
		} else {
			if (node.isSourceNode()) {
				setChecked(true);
				setEnabled(false);
				return;
			} else {
				setChecked(false);
				setEnabled(true);
				return;
			}
		}
	}


}
