package mdx.christmas.arduino;

import uk.ac.mdx.cs.asip.AsipClient;
import uk.ac.mdx.cs.asip.SimpleSerialBoard;
import uk.ac.mdx.cs.asip.services.DistanceService;
import uk.ac.mdx.cs.asip.services.NeoPixelService;

/**
 * @author Franco Raimondi
 * this class provides a simple example of a Distance sensor connected to pin 4
 * and 2 NeoPixel strips on pin 6 and 7, respectively.
 * (remember to upload the correct version of ASIP on the board. You will find it
 * in a branch of the main ASIP git repository)
 */

/**
 * This class is a singleton and will just have one instance for the board and neoPixel and
 * distance sensor
 * @author martinellimi
 */

public class SimpleNeoPixelWithDistance extends SimpleSerialBoard {

	private DistanceService d0;
	private NeoPixelService s0;
	private NeoPixelService s1;
	
	private boolean doingStrip0Show = false;
	private boolean doingStrip1Show = false;
	
	static String PORT = "/dev/tty.usbmodem1411";
	
	private static SimpleNeoPixelWithDistance simpleNeoPixelWithDistance;
	
	private LightsColor color;
	
	private SimpleNeoPixelWithDistance() {
		super(PORT);
		try {
			Thread.sleep(300);
			d0 = new DistanceService(0, getAsipClient());
			Thread.sleep(300);	
			addService('D', d0);
			Thread.sleep(300);	
			d0.enableContinuousReporting(100);
			Thread.sleep(100);
			getAsipClient().setAutoReportInterval(0);
			s0 = new NeoPixelService(0, getAsipClient());
			s1 = new NeoPixelService(1, getAsipClient());
			addService('N', s0);
			Thread.sleep(300);
			s0.show();			
			Thread.sleep(100);
			addService('N', s1);
			Thread.sleep(100);
			s1.show();
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public static SimpleNeoPixelWithDistance getInstance() {
		 if(SimpleNeoPixelWithDistance.simpleNeoPixelWithDistance == null) {
			 SimpleNeoPixelWithDistance.simpleNeoPixelWithDistance =  new SimpleNeoPixelWithDistance();
		 }
		 
		 return SimpleNeoPixelWithDistance.simpleNeoPixelWithDistance;
	}
	
	public boolean getDoingStrip0Show() {
		return doingStrip0Show;
	}
	
	public void setDoingStrip0Show(boolean v) {
		doingStrip0Show = v;
	}
	
	public boolean getDoingStrip1Show() {
		return doingStrip1Show;
	}
	
	public void setDoingStrip1Show(boolean v) {
		doingStrip1Show = v;
	}
	
	public void setDoingStripShow(int strip, boolean v) {
		if(strip == 0) {
			doingStrip0Show = v;
		} else if(strip == 1) {
			doingStrip1Show = v;
		}
	}
	
	public int getDistance() {
		return d0.getDistance();
	}
	
	public void setColor(NeoPixelService ns, int pixel, LightsColor colors) {
		ns.setPixelColor(pixel, colors.R, colors.G, colors.B);
	}
	
	public void setColor(NeoPixelService ns, int pixel, int r, int g, int b) {
		ns.setPixelColor(pixel, r, g, b);
	}
	
	public void setStripBrightness(NeoPixelService ns, int b) {
		ns.setBrightness(b);
	}
	
	public void showStrip(NeoPixelService ns) {
		ns.show();
	}
	
	// A utility code to retrieve the pointer to a strip.
	// Used in the main method below
	public NeoPixelService getStrip(int id) {
		if ( id == 0 ) {
			return this.s0;
		} else if ( id == 1 ) {
			return this.s1;
		} else {
			return null;
		}
	}
	
	public void doStrip0Show(LightsColor color, int counter) {
		setStripBrightness(this.getStrip(0), 50);
		if (!doingStrip0Show) {
			doingStrip0Show = true;
			this.color = color;
			Thread t = new Thread(new Strip0Loop(color, counter));
			t.start();
		}	
	}
	
	public void doStrip1Show(LightsColor color, int counter) {
		setStripBrightness(this.getStrip(1), 50);
		if (!doingStrip1Show) {
			doingStrip1Show = true;
			Thread t = new Thread(new Strip1Loop(color, counter));
			t.start();
		}	
	}
}