package hu.mcp2200.ui;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Application implements IApplication {

	@Override
	public Object start(IApplicationContext context) throws Exception {
		Display display = new Display();
		Shell shell = new Shell(display, SWT.SHELL_TRIM);
		shell.setMinimumSize(600, 600);
		
		shell.setLayout(new FillLayout());
		new ICDeviceControl(shell, SWT.DOUBLE_BUFFERED);
		
		shell.open();
	    while (!shell.isDisposed()) {
	      if (!display.readAndDispatch())
	        display.sleep();
	    }
	    display.dispose();
		return null;
	}

	@Override
	public void stop() {
	}

}
