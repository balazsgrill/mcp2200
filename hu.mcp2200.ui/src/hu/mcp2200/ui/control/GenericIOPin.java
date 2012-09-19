/**
 * 
 */
package hu.mcp2200.ui.control;

import java.util.ArrayList;
import java.util.List;

import hu.mcp2200.MCP2200Exception;

/**
 * @author balazs.grill
 *
 */
public class GenericIOPin implements IPinDescriptor {

	private final int id;
	private final int gpID;
	
	private final PinManager pm;
	
	public GenericIOPin(int id, int gpID, PinManager pm) {
		this.id = id;
		this.gpID = gpID;
		this.pm = pm;
	}
	
	/* (non-Javadoc)
	 * @see hu.mcp2200.ui.control.IPinDescriptor#getNum()
	 */
	@Override
	public int getNum() {
		return id;
	}

	public String getAlternate(){
		return null; 
	}
	
	@Override
	public String toString() {
		String alternate = getAlternate();
		return "GP"+gpID+(alternate == null? "" : ("/"+getAlternate()));
	}
	
	@Override
	public String getMode() {
		String alt = getAlternate(); 
		if (alt != null && pm.isAlt(gpID)) return alt;
		return pm.isInput(gpID) ? "IN" : "OUT";
	}

	@Override
	public boolean getState() {
		return pm.getState(gpID);
	}

	@Override
	public void switchState() throws MCP2200Exception {
		pm.setState(gpID, !getState());
	}
	
	@Override
	public Object[] getModes() {
		List<Object> modes = new ArrayList<Object>(3);
		modes.add("INPUT");
		modes.add("OUTPUT");
		String alt = getAlternate();
		if (alt != null) modes.add(alt);
		return modes.toArray();
	}
	
	@Override
	public void setMode(Object mode) throws MCP2200Exception {
		if ("INPUT".equals(mode)){
			pm.setPinMode(gpID, false, true);
		}
		if ("OUTPUT".equals(mode)){
			pm.setPinMode(gpID, false, false);
		}
		String alt = getAlternate();
		if (alt != null && alt.equals(mode)){
			pm.setPinMode(gpID, true, true);
		}
	}
	
}
