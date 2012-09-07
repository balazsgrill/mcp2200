/**
 * 
 */
package hu.mcp2200.ui.figures;

import hu.mcp2200.ui.control.GenericIOPin;
import hu.mcp2200.ui.control.IPinDescriptor;
import hu.mcp2200.ui.control.PinManager;
import hu.mcp2200.ui.control.UnControllablePinDescriptor;

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

	private final IPinDescriptor[] descriptors;
	
	public DeviceContainerFigure() {
		DeviceFigure devicefigure = new DeviceFigure();
		PinManager pmanager = new PinManager(devicefigure.getConnection());
		descriptors = new IPinDescriptor[]{
				new UnControllablePinDescriptor(1, "Vdd"),
				new UnControllablePinDescriptor(2, "OSC1"),
				new UnControllablePinDescriptor(3, "OSC2"),
				new UnControllablePinDescriptor(4, "RST"),
				new GenericIOPin(5, 7, pmanager){
					
					@Override
					public String getAlternate() {
						return "TxLED";
					}
				},
				new GenericIOPin(6, 6, pmanager){
					
					@Override
					public String getAlternate() {
						return "RxLED";
					}
				},
				new GenericIOPin(7, 5, pmanager),
				new GenericIOPin(8, 4, pmanager),
				new GenericIOPin(9, 3, pmanager),
				
				new UnControllablePinDescriptor(10, "Tx"),
				new UnControllablePinDescriptor(11, "RTS"),
				new UnControllablePinDescriptor(12, "Rx"),
				new UnControllablePinDescriptor(13, "CTS"),
				
				new GenericIOPin(14, 2, pmanager),
				new GenericIOPin(15, 1, pmanager){
					
					@Override
					public String getAlternate() {
						return "USBCFG";
					}
				},
				new GenericIOPin(16, 0, pmanager){
					
					@Override
					public String getAlternate() {
						return "SSPND";
					}
				},
				
				new UnControllablePinDescriptor(17, "Vusb"),
				new UnControllablePinDescriptor(18, "D-"),
				new UnControllablePinDescriptor(19, "D+"),
				new UnControllablePinDescriptor(20, "Vss"),
			};
		
		
		setLayoutManager(new DeviceLayoutManager());
		add(devicefigure);
		for(IPinDescriptor pd : descriptors){
			add(new PinFigure(pd));
		}
	}
	
	@Override
	protected void paintFigure(Graphics graphics) {
		Rectangle r = getClientArea();
		graphics.setBackgroundColor(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));
		graphics.fillRectangle(r);
	}
	
}
