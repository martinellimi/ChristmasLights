package mdx.christmas.twitter;

import mdx.christmas.arduino.SimpleNeoPixel;
import mdx.christmas.arduino.SimpleNeoPixelWithDistance;
import twitter4j.FilterQuery;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import uk.ac.mdx.cs.asip.services.NeoPixelService;

/** This class starts to follow mdxChristmas twitter account using Filterquery **/

/** @author martinellimi **/

public class Twitter {

	private SimpleNeoPixelWithDistance b;
	
	public void setBoard(SimpleNeoPixelWithDistance board) {
		this.b = board;
	}
	
	public void startTwitter() {
		
		try {
			TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
			
			FilterQuery fq = new FilterQuery();

		    String keywords[] = {"mdxChristmas"};

		    fq.track(keywords);

		    Listener listener = new Listener();
		    listener.setBoard(b);
		    
		    twitterStream.addListener(listener);
		    twitterStream.filter(fq);  
		    
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in twitter thread: " + e.getMessage());
		}
	}
	
}
