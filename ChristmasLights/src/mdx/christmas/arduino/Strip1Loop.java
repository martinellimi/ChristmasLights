package mdx.christmas.arduino;

import uk.ac.mdx.cs.asip.services.NeoPixelService;

/**
 * This class is the controller for the strip1.
 * It receives the color that the strip should start and execute the for loop 
 * This strip contains 16 leds
 * @author martinellimi
 */

public class Strip1Loop implements Runnable {

	private LightsColor color;
	
	private int counter;

	private int numPixels = 16;

	public Strip1Loop(LightsColor color, int counter) {
		this.color = color;
		this.counter = counter;
	}

	private void startBlinkerOddEven() {
		Loops loops = new Loops();
		loops.startBlinkerLoopOddEven(color, 1, numPixels);

	}

	private synchronized void startEndToMiddle() {
		Loops loops = new Loops();
		loops.startEndToMiddleLoop(color, 1, numPixels);
	}

	private void startSimpleBlinker() {
		Loops loops = new Loops();
		loops.startSimpleBlinkerLoop(color, 1, numPixels);
	}
	
	public void run() {
		if(counter < 100 ) {
			this.startBlinkerOddEven();
		} else if(counter > 100 && counter < 200) {
			this.startEndToMiddle();
		} else {
			this.startSimpleBlinker();
		}
	}
}
