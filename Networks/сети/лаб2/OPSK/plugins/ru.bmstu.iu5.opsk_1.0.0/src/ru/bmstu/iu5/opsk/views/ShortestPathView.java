/*
 * Created on 10.05.2005
 * 
 * Copyright BMSTU 2005
 */
package ru.bmstu.iu5.opsk.views;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.IContributedContentsView;
import org.eclipse.ui.part.IPage;
import org.eclipse.ui.part.IPageBookViewPage;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.part.MessagePage;
import org.eclipse.ui.part.PageBook;
import org.eclipse.ui.part.PageBookView;

import ru.bmstu.iu5.opsk.Messages;
import ru.bmstu.iu5.opsk.path.IShortestPathPage;

/**
 * This view shows the shortest connections available in the model
 * 
 * @author Egorova Olga
 */
public class ShortestPathView extends PageBookView implements ISelectionProvider {
	

	public static final String ID = "shortestPath"; //$NON-NLS-1$
	private MessagePage page = null;

	public ShortestPathView() {
		super();
	}

	/*
	 * (non-Javadoc) Method declared on ISelectionProvider.
	 */
	public void addSelectionChangedListener(ISelectionChangedListener listener) {
		getSelectionProvider().addSelectionChangedListener(listener);
	}

	/*
	 * (non-Javadoc) Method declared on PageBookView.
	 */
	protected IPage createDefaultPage(PageBook book) {
		page = new MessagePage();
		initPage(page);
		page.createControl(book);
		page.setMessage(Messages.getString("ru.bmstu.iu5.opsk.views.shortest-path.nothing-found-message")); //$NON-NLS-1$
		return page;
	}

	/**
	 * The <code>PageBookView</code> implementation of this
	 * <code>IWorkbenchPart</code> method creates a <code>PageBook</code>
	 * control with its default page showing.
	 */
	public void createPartControl(Composite parent) {
		super.createPartControl(parent);
	}

	/*
	 * (non-Javadoc) Method declared on PageBookView.
	 */
	protected PageRec doCreatePage(IWorkbenchPart part) {
		// Try to get an outline page.
		Object obj = part.getAdapter(IShortestPathPage.class);
		try {
		if (obj instanceof IShortestPathPage) {
			IShortestPathPage page = (IShortestPathPage) obj;
			page.setView(this);
			if (page instanceof IPageBookViewPage)
				initPage((IPageBookViewPage) page);
			page.createControl(getPageBook());
			return new PageRec(part, page);
		}
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		// There is no content outline
		return null;
	}

	/*
	 * (non-Javadoc) Method declared on PageBookView.
	 */
	protected void doDestroyPage(IWorkbenchPart part, PageRec rec) {
		IShortestPathPage page = (IShortestPathPage) rec.page;
		page.dispose();
		rec.dispose();
	}

	/*
	 * (non-Javadoc) Method declared on IAdaptable.
	 */
	public Object getAdapter(Class key) {
		if (key == IContributedContentsView.class)
			return new IContributedContentsView() {
				public IWorkbenchPart getContributingPart() {
					return getContributingEditor();
				}
			};
		return super.getAdapter(key);
	}

	/*
	 * (non-Javadoc) Method declared on PageBookView.
	 */
	protected IWorkbenchPart getBootstrapPart() {
		IWorkbenchPage page = getSite().getPage();
		if (page != null)
			return page.getActiveEditor();
		else
			return null;
	}

	/**
	 * Returns the editor which contributed the current page to this view.
	 * 
	 * @return the editor which contributed the current page or
	 *         <code>null</code> if no editor contributed the current page
	 */
	private IWorkbenchPart getContributingEditor() {
		return getCurrentContributingPart();
	}

	/*
	 * (non-Javadoc) Method declared on ISelectionProvider.
	 */
	public ISelection getSelection() {
		// get the selection from the selection provider
		return getSelectionProvider().getSelection();
	}

	/*
	 * (non-Javadoc) Method declared on PageBookView. We only want to track
	 * editors.
	 */
	protected boolean isImportant(IWorkbenchPart part) {
		//We only care about editors
		return (part instanceof IEditorPart);
	}

	/*
	 * (non-Javadoc) Method declared on IViewPart. Treat this the same as part
	 * activation.
	 */
	public void partBroughtToTop(IWorkbenchPart part) {
		partActivated(part);
	}

	/*
	 * (non-Javadoc) Method declared on ISelectionProvider.
	 */
	public void removeSelectionChangedListener(
			ISelectionChangedListener listener) {
		getSelectionProvider().removeSelectionChangedListener(listener);
	}

	/*
	 * (non-Javadoc) Method declared on ISelectionChangedListener.
	 */
	public void selectionChanged(SelectionChangedEvent event) {
		getSelectionProvider().selectionChanged(event);
	}

	/*
	 * (non-Javadoc) Method declared on ISelectionProvider.
	 */
	public void setSelection(ISelection selection) {
		getSelectionProvider().setSelection(selection);
	}

	/**
	 * The <code>ContentOutline</code> implementation of this
	 * <code>PageBookView</code> method extends the behavior of its parent to
	 * use the current page as a selection provider.
	 * 
	 * @param pageRec
	 *            the page record containing the page to show
	 */
	protected void showPageRec(PageRec pageRec) {
		IPageSite pageSite = getPageSite(pageRec.page);
		ISelectionProvider provider = pageSite.getSelectionProvider();
		if (provider == null && (pageRec.page instanceof IShortestPathPage))
			// This means that the page did not set a provider during its
			// initialization
			// so for backward compatibility we will set the page itself as the
			// provider.
			pageSite.setSelectionProvider((IShortestPathPage) pageRec.page);
		super.showPageRec(pageRec);
	}

	/**
	 * @param message
	 */
	public void showError(String message) {
		// FIXME
	}
}
