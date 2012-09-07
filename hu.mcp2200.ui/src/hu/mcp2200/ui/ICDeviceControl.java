/**
 * 
 */
package hu.mcp2200.ui;

import hu.mcp2200.IMCP2200Device;
import hu.mcp2200.MCP2200Manager;
import hu.mcp2200.ui.control.PinManager;
import hu.mcp2200.ui.figures.DeviceContainerFigure;
import hu.mcp2200.ui.figures.DeviceFigure;

import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;
import org.eclipse.ui.dialogs.ISelectionStatusValidator;

/**
 * @author balazs.grill
 *
 */
public class ICDeviceControl extends Canvas {

	private final LightweightSystem lw;

	public ICDeviceControl(Composite parent, int style) {
		super(parent, style);
		lw = new LightweightSystem(this);
		
		final IFigure root = new DeviceContainerFigure();
		lw.setContents(root);
		root.addMouseListener(new MouseListener(){
			@Override
			public void mouseReleased(MouseEvent me) {
				IFigure figure = root.findFigureAt(me.x, me.y);
				if (figure instanceof DeviceFigure){
					Object[] devices = MCP2200Manager.detectDevices().toArray();
					ElementListSelectionDialog dialog = new ElementListSelectionDialog(getShell(), new DeviceLabelProvider());
					dialog.setElements(devices);
					dialog.setTitle("Select device");
					dialog.setValidator(new ISelectionStatusValidator() {
						
						@Override
						public IStatus validate(Object[] selection) {
							if (selection.length == 1 && selection[0] instanceof IMCP2200Device){
								return Status.OK_STATUS;
							}
							return new Status(IStatus.ERROR, Activator.PLUGIN_ID, "A device must be selected");
						}
					});
					if (dialog.open() == Window.OK){
						Object o = dialog.getFirstResult();
						if (o instanceof IMCP2200Device){
							IObservableValue deviceValue = ((DeviceFigure) figure).getDevice();
							deviceValue.setValue(o);
						}
					}
				}
				
			}

			@Override
			public void mousePressed(MouseEvent me) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseDoubleClicked(MouseEvent me) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
	}

}
