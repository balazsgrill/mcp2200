/**
 * 
 */
package hu.mcp2200.ui.figures;

import org.eclipse.draw2d.AbstractLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * @author balazs.grill
 *
 */
public class DeviceLayoutManager extends AbstractLayout {

	private static final float ratio = 0.6f;
	
	private static final int pins = 20;
	
	@Override
	public void layout(IFigure container) {
		Rectangle r = container.getClientArea();
		int deviceheight = r.height-40;
		int devicewidth = Math.round(deviceheight*ratio);
		int devicex = (r.width-devicewidth)/2;
		
		for (Object f : container.getChildren()){
			if (f instanceof DeviceFigure){
				
				Rectangle bounds = new Rectangle(devicex, 20, devicewidth, deviceheight);
				((DeviceFigure) f).setBounds(bounds);
			}
			if (f instanceof PinFigure){
				PinFigure figure = (PinFigure)f;
				int id = figure.getPinDescriptor().getNum();
				int rows = pins/2;
				int row = id;
				if (row > rows){
					row = row-rows;
					row = rows-row+1;
					figure.setLeft(false);
				}
				int height = 20;
				int width = 150;
				int x = devicex-width+PinFigure.indent;
				if (id > rows) x = devicex+devicewidth-PinFigure.indent;
				int rowheight = deviceheight/rows;
				int y = 20 + (rowheight*row) - (rowheight/2);
				Rectangle bounds = new Rectangle(x, y, width, height);
				figure.setBounds(bounds);
			}
		}
	}

	@Override
	protected Dimension calculatePreferredSize(IFigure container, int wHint,
			int hHint) {
		return new Dimension(wHint, hHint);
	}


}
