/**
 * 
 */
package hu.mcp2200.ui.figures;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;

/**
 * @author balazs.grill
 *
 */
public class DeviceContainerFigure extends Figure {

	public DeviceContainerFigure() {
		setLayoutManager(new DeviceLayoutManager());
		add(new DeviceFigure());
	}
	
	@Override
	protected void paintFigure(Graphics graphics) {
		Rectangle r = getClientArea();
		graphics.setBackgroundColor(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));
		graphics.fillRectangle(r);
	}
	
}
