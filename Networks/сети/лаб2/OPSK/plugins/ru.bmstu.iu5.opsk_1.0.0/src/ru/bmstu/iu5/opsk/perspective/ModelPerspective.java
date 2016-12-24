/*
 * Created on Apr 5, 2005
 * 
 * Copyright BMSTU
 */
package ru.bmstu.iu5.opsk.perspective;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import ru.bmstu.iu5.opsk.views.ShortestPathView;

/**
 * A network model perspective
 * 
 * @author Egorova Olga
 */
public class ModelPerspective implements IPerspectiveFactory {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IPerspectiveFactory#createInitialLayout(org.eclipse.ui.IPageLayout)
	 */
	public void createInitialLayout(IPageLayout layout) {
		defineActions(layout);
		defineLayout(layout);
	}

	/**
	 * @param layout
	 */
	private void defineLayout(IPageLayout layout) {
        // Editors are placed for free.
        String editorArea = layout.getEditorArea();

        // Place navigator and outline to left of
        // editor area.
        layout.addView(IPageLayout.ID_OUTLINE, IPageLayout.RIGHT, 
        		new Float(0.75).floatValue(), IPageLayout.ID_EDITOR_AREA);
        layout.addView(ShortestPathView.ID, IPageLayout.RIGHT, new Float(0.75).floatValue(), IPageLayout.ID_EDITOR_AREA);
        layout.addView(IPageLayout.ID_PROP_SHEET, IPageLayout.BOTTOM, 
        		new Float(0.80).floatValue(), IPageLayout.ID_EDITOR_AREA);
        
	}

	/**
	 * @param layout
	 */
	private void defineActions(IPageLayout layout) {
		
		
	}
	
	

}
