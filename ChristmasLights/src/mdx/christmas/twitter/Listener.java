package mdx.christmas.twitter;

import mdx.christmas.arduino.SimpleNeoPixelWithDistance;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.User;

/** This class implements the StatusListener interface from twitter4j **/

public class Listener implements StatusListener {

	private SimpleNeoPixelWithDistance b;
	
	public void setBoard(SimpleNeoPixelWithDistance board) {
		this.b = board;
	}
	
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
        System.out.println(content +"\n");
        
        b.doStrip1Show();
        
	}

	@Override
	public void onTrackLimitationNotice(int arg0) {
	}
	
}
