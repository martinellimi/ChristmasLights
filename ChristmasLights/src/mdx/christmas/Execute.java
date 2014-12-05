package mdx.christmas;

import mdx.christmas.threads.RunnableController;

/** 
 * This class starts the controllerThreads
 * @author martinellimi
 */
public class Execute {
	public static void main(String[] args) {
		Thread controllerThread = new Thread(RunnableController.getInstance());
		controllerThread.start();
	}
}
