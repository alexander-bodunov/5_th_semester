/*
 * Created on 11.05.2005
 * 
 * Copyright BMSTU 2005
 */
package ru.bmstu.iu5.opsk.path;

import java.util.EventObject;

import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.commands.CommandStackListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.part.Page;

import ru.bmstu.iu5.opsk.Messages;
import ru.bmstu.iu5.opsk.gef.model.NetworkElement;
import ru.bmstu.iu5.opsk.views.ShortestPathView;

/**
 * @author Egorova Olga
 */
public class ShortestPathPage extends Page implements IShortestPathPage, CommandStackListener {

	private Table table;

	protected TableViewer tableViewer;

	private final String NUM_COLUMN 		= "num"; //$NON-NLS-1$
	private final String PATH_COLUMN 	= "path"; //$NON-NLS-1$
	private final String LENGTH_COLUMN 			= "length"; //$NON-NLS-1$
	private final String COST	=	"cost"; //$NON-NLS-1$

	private NetworkElement diagram;

	
	// Set column names
	private String[] columnNames = new String[] { 
			NUM_COLUMN, 
			PATH_COLUMN,
			LENGTH_COLUMN,
			COST
			};

	private ShortestPathView view;

	private Label label;	

	public ShortestPathPage(NetworkElement diagram, CommandStack cmdst) {
		this.diagram = diagram;
		cmdst.addCommandStackListener(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.part.IPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl(Composite aparent) {
		Composite parent = new Composite(aparent, SWT.NULL);
		parent.setLayout(new GridLayout(1, false));
		
		createLabel(parent);
		// Create the table
		createTable(parent);

		// Create and setup the TableViewer
		createTableViewer();
		tableViewer.setContentProvider(new PathTableContentProvider());
		tableViewer.setLabelProvider(new PathTableLabelProvider());
		// The input for the table viewer is the instance of ExampleTaskList
		tableViewer.setInput(diagram.getPaths());
	}

	/**
	 * @param parent
	 */
	private void createLabel(Composite parent) {
		label = new Label(parent, SWT.NONE);
		updateLabel();
	}
	
	private void updateLabel() {
		label.setText(Messages.getString("ru.bmstu.iu5.opsk.path-list.column.overal-network-costs") + diagram.calculate()); //$NON-NLS-1$
		label.pack(true);
	}

	/**
	 * 
	 */
	private void createTableViewer() {
		tableViewer = new TableViewer(table);
		tableViewer.setUseHashlookup(true);
		
		tableViewer.setColumnProperties(columnNames);

		// Set the default sorter for the viewer 
		tableViewer.setSorter(new PathTableSorter(PathTableSorter.LENGTH));
		
	}

	/**
	 * @param parent
	 */
	private void createTable(Composite parent) {
		int style = SWT.SINGLE | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL
				| SWT.FULL_SELECTION | SWT.HIDE_SELECTION;

		final int NUMBER_COLUMNS = 3;

		table = new Table(parent, style);

		GridData gridData = new GridData(GridData.FILL_BOTH);
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalSpan = 3;
		table.setLayoutData(gridData);

		table.setLinesVisible(true);
		table.setHeaderVisible(true);

		// 1st column with path number
		TableColumn column = new TableColumn(table, SWT.CENTER, 0);
		column.setText(Messages.getString("ru.bmstu.iu5.opsk.path-list.column.sequence-number")); //$NON-NLS-1$
		column.setWidth(20);

		// 2nd column with task Path
		column = new TableColumn(table, SWT.LEFT, 1);
		column.setText(Messages.getString("ru.bmstu.iu5.opsk.path-list.column.path")); //$NON-NLS-1$
		column.setWidth(100);
		// Add listener to column so tasks are sorted by description when
		// clicked
		column.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				tableViewer
						.setSorter(new PathTableSorter(PathTableSorter.PATH));
			}
		});

		// 3rd column with length
		column = new TableColumn(table, SWT.LEFT, 2);
		column.setText(Messages.getString("ru.bmstu.iu5.opsk.path-list.column.length")); //$NON-NLS-1$
		column.setWidth(80);
		// Add listener to column so tasks are sorted by owner when clicked
		column.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				tableViewer.setSorter(new PathTableSorter(
						PathTableSorter.LENGTH));
			}
		});

		column = new TableColumn(table, SWT.LEFT, 3);
		column.setText(Messages.getString("ru.bmstu.iu5.opsk.path-list.column.cost")); //$NON-NLS-1$
		column.setWidth(30);
		// Add listener to column so tasks are sorted by owner when clicked
		column.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				tableViewer.setSorter(new PathTableSorter(
						PathTableSorter.COST));
			}
		});
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.part.IPage#dispose()
	 */
	public void dispose() {
		view = null;
		if (label != null) {
			label.dispose();
		}
		table.dispose();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.part.IPage#getControl()
	 */
	public Control getControl() {
		return table.getParent();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.part.IPage#setActionBars(org.eclipse.ui.IActionBars)
	 */
	public void setActionBars(IActionBars actionBars) {
		// TODO Auto-generated method stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.part.IPage#setFocus()
	 */
	public void setFocus() {
		table.setFocus();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ISelectionProvider#addSelectionChangedListener(org.eclipse.jface.viewers.ISelectionChangedListener)
	 */
	public void addSelectionChangedListener(ISelectionChangedListener listener) {
		tableViewer.addSelectionChangedListener(listener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ISelectionProvider#getSelection()
	 */
	public ISelection getSelection() {
		return tableViewer.getSelection();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ISelectionProvider#removeSelectionChangedListener(org.eclipse.jface.viewers.ISelectionChangedListener)
	 */
	public void removeSelectionChangedListener(
			ISelectionChangedListener listener) {
		tableViewer.removeSelectionChangedListener(listener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ISelectionProvider#setSelection(org.eclipse.jface.viewers.ISelection)
	 */
	public void setSelection(ISelection selection) {
		tableViewer.setSelection(selection);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.CommandStackListener#commandStackChanged(java.util.EventObject)
	 */
	public void commandStackChanged(EventObject event) {
		try  {
			updateLabel();
			PathList list = diagram.getPaths();
			tableViewer.setInput(list);
		} catch (RuntimeException e) {
			view.showError(e.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see ru.bmstu.iu5.opsk.path.IShortestPathPage#setView(ru.bmstu.iu5.opsk.views.ShortestPathView)
	 */
	public void setView(ShortestPathView view) {
		this.view = view;	
	}

}
