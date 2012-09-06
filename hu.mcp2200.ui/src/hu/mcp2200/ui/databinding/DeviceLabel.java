/**
 * 
 */
package hu.mcp2200.ui.databinding;

import hu.mcp2200.IMCP2200Connection;

import org.eclipse.core.databinding.observable.value.ComputedValue;
import org.eclipse.core.databinding.observable.value.IObservableValue;

/**
 * @author balazs.grill
 *
 */
public class DeviceLabel extends ComputedValue {

	private final IObservableValue device;
	
	public DeviceLabel(IObservableValue device) {
		super(device.getRealm());
		this.device = device;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.core.databinding.observable.value.ComputedValue#calculate()
	 */
	@Override
	protected Object calculate() {
		Object v = device.getValue();
		if (v instanceof IMCP2200Connection){
			IMCP2200Connection con = (IMCP2200Connection)v;
			StringBuilder sb = new StringBuilder();
			sb.append("Connected to ");
			sb.append(con.getDevice().getBus());
			sb.append(":");
			sb.append(con.getDevice().getAddress());
			return sb.toString();
		}
		return "<Not connected>";
	}

}
