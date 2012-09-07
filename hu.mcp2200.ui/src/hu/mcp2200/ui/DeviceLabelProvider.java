/**
 * 
 */
package hu.mcp2200.ui;

import hu.mcp2200.IMCP2200Device;

import org.eclipse.jface.viewers.LabelProvider;

/**
 * @author balazs.grill
 *
 */
public class DeviceLabelProvider extends LabelProvider {

	@Override
	public String getText(Object element) {
		if (element instanceof IMCP2200Device){
			int bus = ((IMCP2200Device) element).getBus();
			int address = ((IMCP2200Device) element).getAddress();
			return "MCP2200 - "+bus+":"+address;
		}
		return super.getText(element);
	}
	
}
