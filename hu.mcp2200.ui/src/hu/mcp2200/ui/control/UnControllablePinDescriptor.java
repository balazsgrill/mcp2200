/**
 * 
 */
package hu.mcp2200.ui.control;

/**
 * @author balazs.grill
 *
 */
public class UnControllablePinDescriptor implements IPinDescriptor {

	private final int num;
	private final String name;
	
	public UnControllablePinDescriptor(int num, String name) {
		this.num = num;
		this.name = name;
	}
	
	/* (non-Javadoc)
	 * @see hu.mcp2200.ui.control.IPinDescriptor#getNum()
	 */
	@Override
	public int getNum() {
		return num;
	}

	@Override
	public String toString() {
		return name;
	}
	
	@Override
	public String getMode() {
		return null;
	}
	
	@Override
	public boolean getState() {
		return false;
	}

	@Override
	public void switchState() {
	}
	
	@Override
	public Object[] getModes() {
		return null;
	}
	
	@Override
	public void setMode(Object mode) {
	}
	
}
