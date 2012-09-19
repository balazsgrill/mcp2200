/**
 * 
 */
package hu.mcp2200.ui.control;

import hu.mcp2200.MCP2200Exception;

/**
 * @author balazs.grill
 *
 */
public interface IPinDescriptor {

	public int getNum();
	
	public String getMode();
	
	public Object[] getModes();
	
	public boolean getState();
	
	public void switchState() throws MCP2200Exception;
	
	public void setMode(Object mode) throws MCP2200Exception;
	
}
