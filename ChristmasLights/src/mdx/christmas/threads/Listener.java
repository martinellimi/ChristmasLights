package mdx.christmas.threads;

import mdx.christmas.threads.MailBox;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.User;

/** This class implements the StatusListener interface from twitter4j **/

public class Listener implements StatusListener {

	@Override
	public void onException(Exception arg0) {
	}

	@Override
	public void onDeletionNotice(StatusDeletionNotice arg0) {
	}

	@Override
	public void onScrubGeo(long arg0, long arg1) {
	}

	@Override
	public void onStallWarning(StallWarning arg0) {
	}

	@Override
	public void onStatus(Status status) {
		User user = status.getUser();

		String username = status.getUser().getScreenName();
		String profileLocation = user.getLocation();
		System.out.println(profileLocation);
		long tweetId = status.getId();
		System.out.println(tweetId);
		String content = status.getText();
		System.out.println(content + "\n");

		/**
		 * When I receive a tweet, I will change the flag of execution and the distance 
		 * sensor thread will wait and will execute the twitter
		 */
		
		MailBox.getInstance().changeExecuteFlag(MailBox.EXECUTE_TWITTER);
		
	}

	@Override
	public void onTrackLimitationNotice(int arg0) {
	}

}
