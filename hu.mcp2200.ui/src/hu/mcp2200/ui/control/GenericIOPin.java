/**
 * 
 */
package hu.mcp2200.ui.control;

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
	
}
