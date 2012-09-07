/**
 * 
 */
package hu.mcp2200.ui.databinding;

import hu.mcp2200.IMCP2200Connection;
import hu.mcp2200.IMCP2200Device;
import hu.mcp2200.MCP2200Exception;

import org.eclipse.core.databinding.observable.value.ComputedValue;
import org.eclipse.core.databinding.observable.value.IObservableValue;

/**
 * @author balazs.grill
 *
 */
public class DeviceConnection extends ComputedValue {

	private IMCP2200Connection connection = null;
	private final IObservableValue device;
	
	public DeviceConnection(IObservableValue device) {
		super(device.getRealm());
		this.device = device;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.core.databinding.observable.value.ComputedValue#calculate()
	 */
	@Override
	protected Object calculate() {
		Object v = device.getValue();
		if (connection != null && connection.getDevice() != v){
			connection.dispose();
			connection = null;
		}
		
		if (v instanceof IMCP2200Device){
			try {
				connection = ((IMCP2200Device) v).connect();
			} catch (MCP2200Exception e) {
				e.printStackTrace();
			}
		}
		
		return connection;
	}

}
