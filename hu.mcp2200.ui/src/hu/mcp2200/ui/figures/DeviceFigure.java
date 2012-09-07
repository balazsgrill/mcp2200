/**
 * 
 */
package hu.mcp2200.ui.figures;

import hu.mcp2200.ui.databinding.DeviceConnection;
import hu.mcp2200.ui.databinding.DeviceLabel;

import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.TextUtilities;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;

/**
 * @author balazs.grill
 *
 */
public class DeviceFigure extends Figure {

	private final IObservableValue device = new WritableValue(SWTObservables.getRealm(Display.getDefault()));
	private final IObservableValue connection = new DeviceConnection(device);
	private final IObservableValue label = new DeviceLabel(connection);
	
	public DeviceFigure() {
		
	}
	
	public IObservableValue getConnection() {
		return connection;
	}
	
	public IObservableValue getDevice() {
		return device;
	}
	
	@Override
	protected void paintFigure(Graphics graphics) {
		Rectangle r = getClientArea();
		r = new Rectangle(r.x, r.y, r.width-1, r.height-1);
		graphics.setForegroundColor(Display.getDefault().getSystemColor(SWT.COLOR_BLACK));
		graphics.setBackgroundColor(Display.getDefault().getSystemColor(SWT.COLOR_GRAY));
		graphics.fillRectangle(r);
		graphics.drawRectangle(r);
		String label = this.label.getValue().toString();
		Dimension d = TextUtilities.INSTANCE.getStringExtents(label, graphics.getFont());
		graphics.drawString(label, r.x+(r.width-d.width)/2, r.y+(r.height-d.height)/2);
	}
	
	
	
}
