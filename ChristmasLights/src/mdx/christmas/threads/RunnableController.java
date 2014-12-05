package mdx.christmas.threads;

import mdx.christmas.threads.runnable.DistanceSensorRunnable;
import mdx.christmas.threads.runnable.TwitterRunnable;

/** 
 * This class is the controller of threads, there are two threads - One for the 
 * distance sensor and other for the Twitter
 * The executeThreads starts these two threads
 * @author martinellimi
 */

public class RunnableController implements Runnable {

	private static RunnableController runnableController;
	
	private RunnableController() {
		super();
	}
	
	public static RunnableController getInstance() {
		if (RunnableController.runnableController == null) {
			RunnableController.runnableController = new RunnableController();
		}
		
		return RunnableController.runnableController;
	}
	
	private synchronized void executeThreads() {
		DistanceSensorRunnable distanceSensorRunnable = new DistanceSensorRunnable();
		TwitterRunnable twitterRunnable = new TwitterRunnable();
		
		Thread distanceSensorThread = new Thread(distanceSensorRunnable);
		Thread twitterThread = new Thread(twitterRunnable);
		
		twitterThread.start();
		distanceSensorThread.start();
	}
	
	@Override()
	public void run() {
		synchronized(this) {
			this.executeThreads();
		}
	}
}
