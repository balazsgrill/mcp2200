/**
 * 
 */
package hu.mcp2200;

/**
 * @author balazs.grill
 *
 */
public final class MCP2200Configuration {

	public final boolean[] GPx_input;
	
	public boolean GP0_Suspend = true;
	public boolean GP1_USBConfig = true;
	public boolean GP6_RxLed = true;
	public boolean GP7_TxLed = true;
	
	public final boolean[] GPx_default; 

	public boolean RxTGL = false;
	public boolean TxTGL = false;
	public boolean LEDx = false;
	
	public boolean invert = false;
	public boolean HWFlow = false;
	
	public int desiredBaudRate = 9600;
	
	public MCP2200Configuration() {
		GPx_input = new boolean[8];
		GPx_default = new boolean[8];
		for(int i=0;i<8;i++){
			GPx_input[i] = false;
			GPx_default[i] = false;
		}
		
	}
	
	public int get_IO_map(){
		int r = 0;
		for(int i=0;i<8;i++){
			if (GPx_input[i])
				r += (1<<i); 
		}
		return r;
	}
	
	public int get_config_alt_pins(){
		int r = 0;
		if (GP7_TxLed) r += 4;
		if (GP6_RxLed) r += 8;
		if (GP1_USBConfig) r+= 64;
		if (GP0_Suspend) r += 128;
		return r;
	}
	
	public int get_default_IO_map(){
		int r = 0;
		for(int i=0;i<8;i++){
			if (GPx_default[i])
				r += (1<<i); 
		}
		return r;
	}
	
	public int get_config_alt_options(){
		int r = 0;
		if (HWFlow) r += 1;
		if (invert) r += 2;
		if (LEDx) r+= 32;
		if (TxTGL) r += 64;
		if (RxTGL) r += 128;
		return r;
	}
	
	public int getBaud(){
		return (12000000 / desiredBaudRate)-1;
	}
}
