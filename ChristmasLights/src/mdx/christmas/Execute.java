package mdx.christmas;

import mdx.christmas.arduino.SimpleNeoPixel;
import mdx.christmas.twitter.Twitter;

public class Execute {
	public static void main(String[] args) {
		Twitter twt = new Twitter();
		twt.startTwitter();

		SimpleNeoPixel sp = new SimpleNeoPixel("");
		sp.startNeoPixel();
	}
}
