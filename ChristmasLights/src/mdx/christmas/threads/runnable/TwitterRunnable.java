package mdx.christmas.threads.runnable;

import mdx.christmas.threads.MailBox;
import mdx.christmas.threads.Twitter;

/**
 * This class is the runnable for the twitter
 * When this thread is started, it will execute the twitter thread
 * @author martinellimi
 *
 */

public class TwitterRunnable implements Runnable {
	
	private Twitter twitter;
	
	public TwitterRunnable() {
		super();
	}
	
	@Override()
	public void run() {
		this.twitter = new Twitter();
		
		this.twitter.startTwitter();
		
		while (true) {
			MailBox.getInstance().executeTwitter(this.twitter);
		}
	}

}