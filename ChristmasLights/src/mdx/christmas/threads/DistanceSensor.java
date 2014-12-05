package mdx.christmas.threads;

import mdx.christmas.arduino.LightsColor;
import mdx.christmas.arduino.SimpleNeoPixelWithDistance;

/**
 * This class is the execution when the distance sensor is running, when the distance sensor
 * thread is in wait() this method stops to run.
 * @author martinellimi
 *
 */
public class DistanceSensor {

	public DistanceSensor() { } 
	
	public int counter = 0;
	
	public synchronized void startLeds() {
		
		try {
			int distance = SimpleNeoPixelWithDistance.getInstance().getDistance();
			if ( (distance < 20) && (distance>0) && counter < 100) {
				SimpleNeoPixelWithDistance.getInstance().doStrip0Show(LightsColor.Red, counter);
				SimpleNeoPixelWithDistance.getInstance().doStrip1Show(LightsColor.Green, counter);
			} else if ( (distance > 20) && counter < 100 ) {
				SimpleNeoPixelWithDistance.getInstance().doStrip0Show(LightsColor.Yellow, counter);
				SimpleNeoPixelWithDistance.getInstance().doStrip1Show(LightsColor.Red, counter);
			} else if ((distance < 20) && (distance>0) && counter > 100) {
				SimpleNeoPixelWithDistance.getInstance().doStrip0Show(LightsColor.Green, counter);
				SimpleNeoPixelWithDistance.getInstance().doStrip1Show(LightsColor.Yellow, counter);
			} else if( (distance > 20) && counter > 100 ) {
				SimpleNeoPixelWithDistance.getInstance().doStrip0Show(LightsColor.Black, counter);
				SimpleNeoPixelWithDistance.getInstance().doStrip1Show(LightsColor.Black, counter);
			}
			
			if(counter> 300) {
				counter = 0;
			}
			counter++;
			System.out.println(counter);
			Thread.sleep(20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
