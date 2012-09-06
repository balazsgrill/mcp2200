/**
 * 
 */
package hu.mcp2200.ui;

import hu.mcp2200.ui.figures.DeviceContainerFigure;
import hu.mcp2200.ui.figures.DeviceFigure;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

/**
 * @author balazs.grill
 *
 */
public class ICDeviceControl extends Canvas {

	private final LightweightSystem lw;

	public ICDeviceControl(Composite parent, int style) {
		super(parent, style);
		lw = new LightweightSystem(this);
		
		lw.setContents(new DeviceContainerFigure());
		
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseUp(MouseEvent e) {
				IFigure figure = lw.getRootFigure().findFigureAt(e.x, e.y);
				if (figure instanceof DeviceFigure){
					System.out.println("DeviceFigure");
				}
				
			}
			
			@Override
			public void mouseDown(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}

}
