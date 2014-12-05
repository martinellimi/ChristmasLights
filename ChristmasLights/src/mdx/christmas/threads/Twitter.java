package mdx.christmas.threads;

import twitter4j.FilterQuery;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import mdx.christmas.arduino.LightsColor;
import mdx.christmas.arduino.SimpleNeoPixelWithDistance;
/**
 * This is the Twitter class, it starts the twitter listener and has the execution for the 
 * twitter thread, when the Twitter receives one tweet, the Twitter thread will start this
 * startsLeds method
 * @author martinellimi
 *
 */
public class Twitter {

	public Twitter() { } 
	
	public synchronized void startLeds() {
		try {
			
			SimpleNeoPixelWithDistance.getInstance().doStrip0Show(LightsColor.Blue, 0);
			SimpleNeoPixelWithDistance.getInstance().doStrip1Show(LightsColor.Blue, 0);
			
			Thread.sleep(2000);
			
			SimpleNeoPixelWithDistance.getInstance().doStrip0Show(LightsColor.Grey, 0);
			SimpleNeoPixelWithDistance.getInstance().doStrip1Show(LightsColor.Grey, 0);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public void startTwitter() {
		
		try {
			TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
			
			FilterQuery fq = new FilterQuery();

		    String keywords[] = {"mdxChristmas"};

		    fq.track(keywords);

		    Listener listener = new Listener();
		    twitterStream.addListener(listener);
		    twitterStream.filter(fq);  
		    
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in twitter thread: " + e.getMessage());
		}
	}
}
