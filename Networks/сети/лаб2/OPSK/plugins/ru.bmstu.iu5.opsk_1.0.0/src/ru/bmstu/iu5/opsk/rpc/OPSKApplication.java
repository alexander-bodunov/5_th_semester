/*
 * Created on Apr 5, 2005
 */
package ru.bmstu.iu5.opsk.rpc;

import org.eclipse.core.runtime.IPlatformRunnable;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.WorkbenchAdvisor;

/**
 * 
 * 
 * @author Egorova Olga
 */
public class OPSKApplication implements IPlatformRunnable {

	/* (non-Javadoc)
	 * @see org.eclipse.core.runtime.IPlatformRunnable#run(java.lang.Object)
	 */
	public Object run(Object args) throws Exception {
        WorkbenchAdvisor workbenchAdvisor = new OPSKWorkbenchAdvisor();
        Display display = PlatformUI.createDisplay();
        int returnCode = PlatformUI.createAndRunWorkbench(display,
                      workbenchAdvisor);
        if (returnCode == PlatformUI.RETURN_RESTART)
                return IPlatformRunnable.EXIT_RESTART;
        else
                return IPlatformRunnable.EXIT_OK;
	}
}
