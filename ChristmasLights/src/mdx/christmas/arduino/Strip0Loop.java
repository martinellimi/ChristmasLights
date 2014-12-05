package mdx.christmas.arduino;

/**
 *  This class is the controller for the strip1.
 * It receives the color that the strip should start and execute the for loop 
 * This strip contains 12 leds
 * @author martinellimi
 */
public class Strip0Loop implements Runnable {

	private LightsColor color;
	private int counter;
	private static int NUM_PIXEL = 12;
	private static int STRIP = 0;
	
	public Strip0Loop(LightsColor color, int counter) {	
		this.color = color;
		this.counter = counter;
	}

	private void startSimpleBlinker() {
		Loops loops = new Loops();
		loops.startSimpleBlinkerLoop(color, STRIP, NUM_PIXEL);
	}
	
	private void startEndToMiddle() {
		Loops loops = new Loops();
		loops.startEndToMiddleLoop(color, STRIP, NUM_PIXEL);
	}
	
	private void startBlinkerOddEven() {
		Loops loops = new Loops();
		loops.startBlinkerLoopOddEven(color, STRIP, NUM_PIXEL);
	}
	
	private void startBlinkAll() {
		Loops loops = new Loops();
		loops.startBlinkAllLoop(color, STRIP, NUM_PIXEL);
	}
	
	public void run() {
		if(counter > 0 && counter < 100) {
			this.startSimpleBlinker();
		} else if(counter >= 100 && counter < 200) {
			this.startEndToMiddle();
		} else if(counter >= 200 && counter < 250) {
			this.startBlinkerOddEven();
		} else {
			this.startBlinkAll();
		}
	}
}