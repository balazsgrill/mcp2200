/**
 * 
 */
package hu.mcp2200.ui;

import hu.mcp2200.IMCP2200Device;
import hu.mcp2200.MCP2200Exception;
import hu.mcp2200.MCP2200Manager;
import hu.mcp2200.ui.control.IPinDescriptor;
import hu.mcp2200.ui.figures.DeviceContainerFigure;
import hu.mcp2200.ui.figures.DeviceFigure;
import hu.mcp2200.ui.figures.PinFigure;

import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.dialogs.ListDialog;

/**
 * @author balazs.grill
 *
 */
public class ICDeviceControl extends Canvas {

	private final LightweightSystem lw;

	private IPinDescriptor pinDescriptor = null;
	private IFigure pinFigure = null;
	private final IMenuListener menuListener = new IMenuListener() {
		
		@Override
		public void menuAboutToShow(IMenuManager manager) {
			if (pinDescriptor != null){
				Object[] modes = pinDescriptor.getModes();
				if (modes != null){
					for(final Object mode : modes){
						manager.add(new Action(mode.toString()) {
							public void run() {
								try {
									pinDescriptor.setMode(mode);
								} catch (MCP2200Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							};
						});
					}
				}
			}
			if (pinFigure != null){
				getDisplay().timerExec(50,new Runnable() {
					
					@Override
					public void run() {
						pinFigure.getParent().repaint();
					}
				});
			}
		}
	};
	
	public ICDeviceControl(Composite parent, int style) {
		super(parent, style);
		lw = new LightweightSystem(this);
		MenuManager mm = new MenuManager();
		mm.setRemoveAllWhenShown(true);
		setMenu(mm.createContextMenu(this));
		mm.addMenuListener(menuListener);
		
		
		final IFigure root = new DeviceContainerFigure();
		lw.setContents(root);
		root.addMouseListener(new MouseListener(){
			@Override
			public void mouseReleased(MouseEvent me) {
				IFigure figure = root.findFigureAt(me.x, me.y);
				if (figure instanceof DeviceFigure){
					Object[] devices = MCP2200Manager.detectDevices().toArray();
					ListDialog dialog = new ListDialog(getShell());
					dialog.setContentProvider(new ArrayContentProvider());
					dialog.setLabelProvider(new DeviceLabelProvider());
					dialog.setInput(devices);
					dialog.setTitle("Select device");
					if (dialog.open() == Window.OK){
						Object o = dialog.getResult()[0];
						if (o instanceof IMCP2200Device){
							IObservableValue deviceValue = ((DeviceFigure) figure).getDevice();
							deviceValue.setValue(o);
						}
					}
				}
				if (figure instanceof PinFigure){
					if (me.button == 1){
						try {
							((PinFigure) figure).getPinDescriptor().switchState();
						} catch (MCP2200Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						figure.repaint();
					}
					if (me.button == 3){
						pinDescriptor = ((PinFigure) figure).getPinDescriptor();
						pinFigure = figure;
						Menu menu = getMenu();
						menu.setLocation(toDisplay(me.x, me.y));
						menu.setVisible(true);
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
