/**
 * 
 */
package hu.mcp2200.impl;

import hu.mcp2200.IMCP2200Connection;
import hu.mcp2200.IMCP2200Device;
import hu.mcp2200.MCP2200Configuration;
import hu.mcp2200.MCP2200Exception;
import hu.mcp2200.MCP2200JNI;

/**
 * @author balazs.grill
 *
 */
public class MCP2200Connection implements IMCP2200Connection {

	private final int connectionID;
	
	private final MCP2200Device device;
	
	/**
	 * @throws MCP2200Exception 
	 * 
	 */
	public MCP2200Connection(MCP2200Device device) throws MCP2200Exception {
		this.device = device;
		this.connectionID = MCP2200JNI.getInstance().connect(device.getIndex());
		if (this.connectionID < 0) throw new MCP2200Exception(connectionID);
	}

	/* (non-Javadoc)
	 * @see hu.mcp2200.api.IMCP2200Connection#dispose()
	 */
	@Override
	public void dispose() {
		MCP2200JNI.getInstance().disconnect(connectionID);
	}

	@Override
	public void configure(MCP2200Configuration configuration)
			throws MCP2200Exception {
		int r = MCP2200JNI.getInstance().hid_configure(
				connectionID, 
				configuration.get_IO_map(), 
				configuration.get_config_alt_pins(), 
				configuration.get_default_IO_map(), 
				configuration.get_config_alt_options(), 
				configuration.getBaud());
		if (r != 0) throw new MCP2200Exception(r);
	}

	@Override
	public void setPin(int pin) throws MCP2200Exception {
		int set_bmap = (1<<pin)&0xFF;
		int r = MCP2200JNI.getInstance().hid_set_clear_output(connectionID, set_bmap, 0);
		if (r != 0) throw new MCP2200Exception(r);
	}

	@Override
	public void clearPin(int pin) throws MCP2200Exception {
		int clr_bmap = (1<<pin)&0xFF;
		int r = MCP2200JNI.getInstance().hid_set_clear_output(connectionID, 0, clr_bmap);
		if (r != 0) throw new MCP2200Exception(r);
	}

	@Override
	public void writeEEPROM(int address, int value) throws MCP2200Exception {
		int r = MCP2200JNI.getInstance().hid_write_ee(connectionID, address&0xFF, value&0xFF);
		if (r != 0) throw new MCP2200Exception(r);
	}

	@Override
	public int readEEPROM(int address) throws MCP2200Exception {
		int r = MCP2200JNI.getInstance().hid_read_ee(connectionID, address&0xFF);
		if (r < 0) throw new MCP2200Exception(r);
		return r&0xFF;
	}

	@Override
	public IMCP2200Device getDevice() {
		return device;
	}
	
}
