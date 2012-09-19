/**
 * 
 */
package hu.mcp2200.ui.figures;

import hu.mcp2200.ui.control.IPinDescriptor;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.TextUtilities;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;

/**
 * @author balazs.grill
 *
 */
public class PinFigure extends Figure {

	public static final int indent = 70;
	
	private final IPinDescriptor pindesc;
	
	private boolean left = true;
	
	public void setLeft(boolean left) {
		this.left = left;
	}
	
	public PinFigure(final IPinDescriptor pindesc) {
		this.pindesc = pindesc;
	}
	
	public IPinDescriptor getPinDescriptor() {
		return pindesc;
	}
	
	@Override
	protected void paintFigure(Graphics graphics) {
		Rectangle r = getClientArea();
		Rectangle p = null;
		if (left){
			p = new Rectangle(r.x+r.width-indent-r.height, r.y, r.height, r.height-1);
		}else{
			p = new Rectangle(r.x+indent-1, r.y, r.height, r.height-1);
		}
		graphics.setForegroundColor(Display.getDefault().getSystemColor(SWT.COLOR_BLACK));
		graphics.setBackgroundColor(Display.getDefault().getSystemColor(SWT.COLOR_GRAY));
		graphics.fillRectangle(p);
		graphics.drawRectangle(p);
		
		graphics.drawString(""+pindesc.getNum(), p.x+2, p.y+2);
		String label = pindesc.toString();
		if (left){
			graphics.drawString(label, p.x+2+p.width+2, p.y+2);
		}else{
			Dimension d = TextUtilities.INSTANCE.getStringExtents(label, graphics.getFont());
			graphics.drawString(label, p.x-d.width-2, p.y+2);
		}
		
		String mode = pindesc.getMode();
		if (mode != null){
			
			Rectangle circle = null;
			if (left){
				Dimension d = TextUtilities.INSTANCE.getStringExtents(mode, graphics.getFont());
				graphics.drawString(mode, p.x-d.width-2, p.y+2);
				circle = new Rectangle(r.x+1, r.y+3, 10, 10);
			}else{
				graphics.drawString(mode, p.x+p.width+2, p.y+2);
				circle = new Rectangle(r.x+r.width-12, r.y+3, 10, 10);
			}
			
			boolean state = pindesc.getState();
			graphics.setBackgroundColor(Display.getDefault().getSystemColor(
					state ? SWT.COLOR_GREEN : SWT.COLOR_DARK_RED
					));
			graphics.fillOval(circle);
			graphics.drawOval(circle);
			
		}
	}
	
}
