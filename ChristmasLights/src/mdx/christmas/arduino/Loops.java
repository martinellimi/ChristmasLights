package mdx.christmas.arduino;

/**
 * This is a class that contains all loops for the leds
 * 
 * @author martinellimi
 *
 */
public class Loops {

	/**
	 * This loop turns on first the lights in the odd number leds and then turns
	 * on the leds in the even numbers leds and last everything off
	 * 
	 * @param color
	 * @param stripNumber
	 * @param numPixels
	 */
	public void startBlinkerLoopOddEven(LightsColor color, int stripNumber,
			int numPixels) {
		try {
			for (int i = 0; i < numPixels; i++) {
				if (!(i % 2 == 0)) {
					SimpleNeoPixelWithDistance.getInstance().setColor(
							SimpleNeoPixelWithDistance.getInstance().getStrip(
									stripNumber), i, color);
				} else {
					SimpleNeoPixelWithDistance.getInstance().setColor(
							SimpleNeoPixelWithDistance.getInstance().getStrip(
									stripNumber), i, LightsColor.Black);
				}
				SimpleNeoPixelWithDistance.getInstance().showStrip(
						SimpleNeoPixelWithDistance.getInstance().getStrip(
								stripNumber));
				Thread.sleep(50);
			}

			for (int i = numPixels - 1; i >= 0; i--) {
				if ((i % 2 == 0)) {
					SimpleNeoPixelWithDistance.getInstance().setColor(
							SimpleNeoPixelWithDistance.getInstance().getStrip(
									stripNumber), i, color);
				} else {
					SimpleNeoPixelWithDistance.getInstance().setColor(
							SimpleNeoPixelWithDistance.getInstance().getStrip(
									stripNumber), i, LightsColor.Black);
				}
				Thread.sleep(5);
				SimpleNeoPixelWithDistance.getInstance().showStrip(
						SimpleNeoPixelWithDistance.getInstance().getStrip(
								stripNumber));
				Thread.sleep(50);
			}

			// turning off everything
			for (int i = numPixels - 1; i >= 0; i--) {
				SimpleNeoPixelWithDistance.getInstance().setColor(
						SimpleNeoPixelWithDistance.getInstance().getStrip(
								stripNumber), i, LightsColor.Black);
				Thread.sleep(5);
			}

			SimpleNeoPixelWithDistance.getInstance().showStrip(
					SimpleNeoPixelWithDistance.getInstance().getStrip(
							stripNumber));

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		SimpleNeoPixelWithDistance.getInstance().setDoingStripShow(stripNumber,
				false);

	}

	/**
	 * This loop turns the lights on from the end of the strip to the middle and
	 * then turns off the middle to the extremity
	 * 
	 * @param color
	 * @param stripNumber
	 * @param numPixels
	 */
	public synchronized void startEndToMiddleLoop(LightsColor color,
			int stripNumber, int numPixels) {
		int j = numPixels - 1;

		try {
			for (int i = 0; i < numPixels / 2; i++) {
				SimpleNeoPixelWithDistance.getInstance().setColor(
						SimpleNeoPixelWithDistance.getInstance().getStrip(
								stripNumber), i, color);
				SimpleNeoPixelWithDistance.getInstance().setColor(
						SimpleNeoPixelWithDistance.getInstance().getStrip(
								stripNumber), j, color);
				SimpleNeoPixelWithDistance.getInstance().showStrip(
						SimpleNeoPixelWithDistance.getInstance().getStrip(
								stripNumber));
				Thread.sleep(100);
				j--;
			}

			j = (numPixels / 2) + 1;
			for (int i = 8; i >= 0; i--) {
				SimpleNeoPixelWithDistance.getInstance().setColor(
						SimpleNeoPixelWithDistance.getInstance().getStrip(
								stripNumber), i, LightsColor.Black);
				SimpleNeoPixelWithDistance.getInstance().setColor(
						SimpleNeoPixelWithDistance.getInstance().getStrip(
								stripNumber), j, LightsColor.Black);
				SimpleNeoPixelWithDistance.getInstance().showStrip(
						SimpleNeoPixelWithDistance.getInstance().getStrip(
								stripNumber));
				Thread.sleep(100);
				j++;
			}

			// turning off everything
			for (int i = numPixels - 1; i >= 0; i--) {
				SimpleNeoPixelWithDistance.getInstance().setColor(
						SimpleNeoPixelWithDistance.getInstance().getStrip(
								stripNumber), i, LightsColor.Black);
				Thread.sleep(5);
			}

			SimpleNeoPixelWithDistance.getInstance().setDoingStripShow(
					stripNumber, false);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This loop is the simple blinker, just one light blinks.
	 * 
	 * @param color
	 * @param stripNumber
	 * @param numPixels
	 */
	public void startSimpleBlinkerLoop(LightsColor color, int stripNumber,
			int numPixels) {
		try {
			for (int i = 0; i < numPixels; i++) {
				// setting a pixel to red and everything else to off
				for (int j = 0; j < numPixels; j++) {
					if (i == j) {
						SimpleNeoPixelWithDistance.getInstance().setColor(
								SimpleNeoPixelWithDistance.getInstance()
										.getStrip(stripNumber), j, color);
					} else {
						SimpleNeoPixelWithDistance.getInstance().setColor(
								SimpleNeoPixelWithDistance.getInstance()
										.getStrip(stripNumber), j,
								LightsColor.Black);
					}

					Thread.sleep(5);
				}
				SimpleNeoPixelWithDistance.getInstance().showStrip(
						SimpleNeoPixelWithDistance.getInstance().getStrip(
								stripNumber));
				Thread.sleep(50);
			}

			for (int i = numPixels - 1; i >= 0; i--) {
				// setting a pixel to red and everything else to off
				for (int j = 0; j < numPixels; j++) {
					if (i == j) {
						SimpleNeoPixelWithDistance.getInstance().setColor(
								SimpleNeoPixelWithDistance.getInstance()
										.getStrip(stripNumber), j, color);
					} else {
						SimpleNeoPixelWithDistance.getInstance().setColor(
								SimpleNeoPixelWithDistance.getInstance()
										.getStrip(stripNumber), j,
								LightsColor.Black);
					}
					Thread.sleep(5);
				}
				SimpleNeoPixelWithDistance.getInstance().showStrip(
						SimpleNeoPixelWithDistance.getInstance().getStrip(
								stripNumber));
				Thread.sleep(50);
			}

			// turning off everything
			for (int i = numPixels - 1; i >= 0; i--) {
				SimpleNeoPixelWithDistance.getInstance().setColor(
						SimpleNeoPixelWithDistance.getInstance().getStrip(
								stripNumber), i, LightsColor.Black);
				Thread.sleep(5);
			}
			SimpleNeoPixelWithDistance.getInstance().showStrip(
					SimpleNeoPixelWithDistance.getInstance().getStrip(
							stripNumber));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SimpleNeoPixelWithDistance.getInstance().setDoingStripShow(stripNumber,
				false);
	}

	/**
	 * This loop blinks all the lights
	 * @param color
	 * @param stripNumber
	 * @param numPixels
	 */
	public void startBlinkAllLoop(LightsColor color, int stripNumber,
			int numPixels) {
		try {
			for (int i = 0; i < numPixels; i++) {
				SimpleNeoPixelWithDistance.getInstance().setColor(
						SimpleNeoPixelWithDistance.getInstance().getStrip(
								stripNumber), i, color);
				Thread.sleep(5);
				SimpleNeoPixelWithDistance.getInstance().showStrip(
						SimpleNeoPixelWithDistance.getInstance().getStrip(
								stripNumber));
				Thread.sleep(50);
			}

			for (int i = numPixels - 1; i >= 0; i--) {
				SimpleNeoPixelWithDistance.getInstance().setColor(
						SimpleNeoPixelWithDistance.getInstance().getStrip(
								stripNumber), i, LightsColor.Black);
				Thread.sleep(5);
				SimpleNeoPixelWithDistance.getInstance().showStrip(
						SimpleNeoPixelWithDistance.getInstance().getStrip(
								stripNumber));
				Thread.sleep(50);
			}

			// turning off everything
			for (int i = numPixels - 1; i >= 0; i--) {
				SimpleNeoPixelWithDistance.getInstance().setColor(
						SimpleNeoPixelWithDistance.getInstance().getStrip(
								stripNumber), i, LightsColor.Black);
				Thread.sleep(5);
			}
			SimpleNeoPixelWithDistance.getInstance().showStrip(
					SimpleNeoPixelWithDistance.getInstance().getStrip(
							stripNumber));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SimpleNeoPixelWithDistance.getInstance().setDoingStripShow(stripNumber,
				false);
	}
}
