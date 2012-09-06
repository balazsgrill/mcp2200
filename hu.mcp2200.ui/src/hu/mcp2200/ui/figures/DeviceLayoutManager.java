/**
 * 
 */
package hu.mcp2200.ui.figures;

import org.eclipse.draw2d.AbstractLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * @author balazs.grill
 *
 */
public class DeviceLayoutManager extends AbstractLayout {

	private static final float ratio = 0.6f;
	
	@Override
	public void layout(IFigure container) {
		Rectangle r = container.getClientArea();
		for (Object f : container.getChildren()){
			if (f instanceof DeviceFigure){
				int height = r.height-40;
				int width = Math.round(height*ratio);
				Rectangle bounds = new Rectangle((r.width-width)/2, 20, width, height);
				((DeviceFigure) f).setBounds(bounds);
			}
		}
	}

	@Override
	protected Dimension calculatePreferredSize(IFigure container, int wHint,
			int hHint) {
		return new Dimension(wHint, hHint);
	}


}
