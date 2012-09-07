/**
 * 
 */
package hu.mcp2200.ui.control;

import hu.mcp2200.IMCP2200Connection;
import hu.mcp2200.MCP2200Configuration;
import hu.mcp2200.MCP2200Exception;
import hu.mcp2200.ui.Activator;

import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;

/**
 * @author balazs.grill
 *
 */
public class PinManager {

	private IMCP2200Connection connection = null;
	
	private final IValueChangeListener listener = new IValueChangeListener() {
		
		@Override
		public void handleValueChange(ValueChangeEvent event) {
			Object o = event.diff.getNewValue();
			if (o instanceof IMCP2200Connection){
				connection = (IMCP2200Connection)o;
				try {
					connection.configure(config);
				} catch (MCP2200Exception e) {
					Activator.showError(e);
				}
			}else{
				connection = null;
			}
		}
	};
	
	private final MCP2200Configuration config = new MCP2200Configuration();
	
	public PinManager(IObservableValue connection) {
		connection.addValueChangeListener(listener);
	}
	
	public boolean isAlt(int id){
		switch(id){
		case 0: return config.GP0_Suspend;
		case 1: return config.GP1_USBConfig;
		case 6: return config.GP6_RxLed;
		case 7: return config.GP7_TxLed;
		default:
			return false;
		}
	}
	
	public boolean isInput(int id){
		return config.GPx_input[id];
	}
	
	public boolean getState(int id){
		return false;
	}
	
}
