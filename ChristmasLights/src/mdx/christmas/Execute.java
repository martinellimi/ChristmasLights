package mdx.christmas;

import mdx.christmas.twitter.Twitter;

public class Execute {
	public static void main(String[] args) {
		Twitter twt = new Twitter();
		twt.startTwitter();
	}
}
