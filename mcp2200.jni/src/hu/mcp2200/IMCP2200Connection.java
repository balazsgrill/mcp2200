/**
 * 
 */
package hu.mcp2200;

/**
 * @author balazs.grill
 *
 */
public interface IMCP2200Connection {

	public void dispose();
	
	public void configure(MCP2200Configuration configuration) throws MCP2200Exception;
	
	public void setPin(int pin) throws MCP2200Exception;
	
	public void clearPin(int pin) throws MCP2200Exception;
	
	public void writeEEPROM(int address, int value) throws MCP2200Exception;
	
	public int readEEPROM(int address) throws MCP2200Exception;
	
	public IMCP2200Device getDevice();
	
}
