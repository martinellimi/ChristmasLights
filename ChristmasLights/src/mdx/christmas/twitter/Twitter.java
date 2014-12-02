package mdx.christmas.twitter;

import twitter4j.FilterQuery;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

/** This class starts to follow mdxChristmas twitter account using Filterquery **/

/** @author martinellimi **/

public class Twitter {

	public static void startTwitter() {
		
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
