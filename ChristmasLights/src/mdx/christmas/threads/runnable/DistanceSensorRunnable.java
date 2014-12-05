package mdx.christmas.threads.runnable;

import mdx.christmas.threads.DistanceSensor;
import mdx.christmas.threads.MailBox;

/**
 * This class is the runnable for the distance sensor
 * When this thread is started, it will execute the distance sensor
 * @author martinellimi
 *
 */
public class DistanceSensorRunnable implements Runnable {
	
	private DistanceSensor distanceSensor;
	
	public DistanceSensorRunnable() {
		super();
	}

	@Override()
	public void run() {
		this.distanceSensor = new DistanceSensor();
		
		try {
			Thread.sleep(1000);
			
			while (true) {
				MailBox.getInstance().executeDistanceSensor(this.distanceSensor);
			}
		}
		catch (final InterruptedException interruptedException) {
			interruptedException.printStackTrace();
		}
	}

}