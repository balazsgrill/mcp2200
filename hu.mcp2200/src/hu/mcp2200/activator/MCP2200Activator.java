/**
 * 
 */
package hu.mcp2200.activator;

import hu.mcp2200.MCP2200Exception;
import hu.mcp2200.MCP2200JNI;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

/**
 * @author balazs.grill
 *
 */
public class MCP2200Activator extends Plugin {

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		int r = MCP2200JNI.getInstance().init();
		if (r != 0) throw new MCP2200Exception(r);
	}
	
	@Override
	public void stop(BundleContext context) throws Exception {
		MCP2200JNI.getInstance().close();
		super.stop(context);
	}
	
}
