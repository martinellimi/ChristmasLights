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

public class SimpleNeoPixelWithDistance extends SimpleSerialBoard {

	private DistanceService d0;
	private NeoPixelService s0;
	private NeoPixelService s1;
	
	private boolean doingStrip0Show = false;
	private boolean doingStrip1Show = false;
	
	public SimpleNeoPixelWithDistance(String port) {
		super(port);
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
	
	public int getDistance() {
		return d0.getDistance();
	}
	
	public void setColor(NeoPixelService ns, int pixel, int red, int green, int blue) {
		ns.setPixelColor(pixel, red, green, blue);
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
	
	public void doStrip0Show() {
		if (!doingStrip0Show) {
			doingStrip0Show = true;
			Thread t = new Thread(new Strip0Loop(this));
			t.start();
		}	
	}
	
	public void doStrip1Show() {
		if (!doingStrip1Show) {
			doingStrip1Show = true;
			Thread t = new Thread(new Strip1Loop(this));
			t.start();
		}	
	}
	
	public static void main(String[] args) {


		SimpleNeoPixelWithDistance testBoard = new SimpleNeoPixelWithDistance("/dev/tty.usbmodem1411");

		//testBoard.setPinMode(13, AsipClient.OUTPUT);
		//testBoard.digitalWrite(13, AsipClient.LOW);
		
		NeoPixelService strip0 = testBoard.getStrip(0);
		System.out.println("Service ID: "+strip0.getServiceID()+strip0.getStripID());
		NeoPixelService strip1 = testBoard.getStrip(1);
		System.out.println("Service ID: "+strip1.getServiceID()+strip1.getStripID());
			
		try {

			Thread.sleep(300);
			
			while (true) {

				System.out.println("Distance: " + testBoard.getDistance());
				
				testBoard.getDistance();
				int numPixels = 16;
				for (int i=0; i < numPixels; i++) {
					// setting a pixel to red and everything else to off
					for (int j = 1; j<numPixels; j++) {
						if ( i == j) {
							testBoard.setColor(strip1, j, 255, 0, 0);
						} else {
							testBoard.setColor(strip1, j, 0, 0, 0);
						}
						Thread.sleep(5);
					}
					testBoard.showStrip(strip1);
					Thread.sleep(50);
					System.out.println("Distance: "+testBoard.getDistance());
				}
				
				numPixels = 12;
				for (int i=0; i < numPixels; i++) {
					// setting a pixel to red and everything else to off
					for (int j = 0; j<numPixels; j++) {
						if ( i == j) {
							testBoard.setColor(strip0, j, 255, 0, 0);
						} else {
							testBoard.setColor(strip0, j, 0, 0, 0);
						}
						Thread.sleep(5);
					}
					testBoard.showStrip(strip0);
					Thread.sleep(50);
				}
				
				for (int i=numPixels-1; i>=0; i--) {
					// setting a pixel to red and everything else to off
					for (int j = 0; j<numPixels; j++) {
						if ( i == j) {
							testBoard.setColor(strip0, j, 255, 0, 0);
						} else {
							testBoard.setColor(strip0, j, 0, 0, 0);
						}
						Thread.sleep(5);
					}
					testBoard.showStrip(strip0);
					Thread.sleep(50);
				}
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}     		
	}


	private static class Strip0Loop implements Runnable {

		SimpleNeoPixelWithDistance testBoard;

		public Strip0Loop(SimpleNeoPixelWithDistance b) {
			this.testBoard = b;
		}

		public void run() {
			NeoPixelService strip0 = testBoard.getStrip(0);
			int numPixels = 12;
			try {
				for (int i=0; i < numPixels; i++) {
					// setting a pixel to red and everything else to off
					for (int j = 0; j<numPixels; j++) {
						if ( i == j) {
							testBoard.setColor(strip0, j, 255, 0, 0);
						} else {
							testBoard.setColor(strip0, j, 0, 0, 0);
						}

						Thread.sleep(5);
					}
					testBoard.showStrip(strip0);
					Thread.sleep(50);
				}

				for (int i=numPixels-1; i>=0; i--) {
					// setting a pixel to red and everything else to off
					for (int j = 0; j<numPixels; j++) {
						if ( i == j) {
							testBoard.setColor(strip0, j, 255, 0, 0);
						} else {
							testBoard.setColor(strip0, j, 0, 0, 0);
						}
						Thread.sleep(5);
					}
					testBoard.showStrip(strip0);
					Thread.sleep(50);
				}
				
				// turning off everything
				for (int i=numPixels-1; i>=0; i--) {
					testBoard.setColor(strip0, i, 0, 0, 0);
						Thread.sleep(5);
				}
				testBoard.showStrip(strip0);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			testBoard.setDoingStrip0Show(false);
		}
	}
	
	
	private static class Strip1Loop implements Runnable {

		SimpleNeoPixelWithDistance testBoard;

		public Strip1Loop(SimpleNeoPixelWithDistance b) {
			this.testBoard = b;
		}

		public void run() {
			NeoPixelService strip1 = testBoard.getStrip(1);
			int numPixels = 16;
			try {
				for (int i=0; i < numPixels; i++) {
					// setting a pixel to red and everything else to off
					for (int j = 0; j<numPixels; j++) {
						if ( i == j) {
							testBoard.setColor(strip1, j, 255, 0, 0);
						} else {
							testBoard.setColor(strip1, j, 0, 0, 0);
						}

						Thread.sleep(5);
					}
					testBoard.showStrip(strip1);
					Thread.sleep(50);
				}

				for (int i=numPixels-1; i>=0; i--) {
					// setting a pixel to red and everything else to off
					for (int j = 0; j<numPixels; j++) {
						if ( i == j) {
							testBoard.setColor(strip1, j, 255, 0, 0);
						} else {
							testBoard.setColor(strip1, j, 0, 0, 0);
						}
						Thread.sleep(5);
					}
					testBoard.showStrip(strip1);
					Thread.sleep(50);
				}
				
				// turning off everything
				for (int i=numPixels-1; i>=0; i--) {
					testBoard.setColor(strip1, i, 0, 0, 0);
						Thread.sleep(5);
				}
				testBoard.showStrip(strip1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			testBoard.setDoingStrip1Show(false);
		}
	}

	
}