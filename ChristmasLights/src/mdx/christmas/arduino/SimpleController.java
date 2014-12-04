package mdx.christmas.arduino;

import mdx.christmas.twitter.Twitter;
import uk.ac.mdx.cs.asip.AsipClient;
import uk.ac.mdx.cs.asip.SimpleSerialBoard;
import uk.ac.mdx.cs.asip.services.DistanceService;
import uk.ac.mdx.cs.asip.services.NeoPixelService;

/**
 * @author Franco Raimondi
 * this class provides a simple example of a Distance sensor connected to pin 4
 * and 2 NeoPixel strips on pin 6 and 7, respectively.
 * (remember to upload the correct version of ASIP on the board. You will find it
 * in a branch of the main ASIP git repository)
 */

public class SimpleController  {

	private SimpleNeoPixelWithDistance board;
	private Twitter twitterChecker;
	
	public SimpleController(String port) {
		board = new SimpleNeoPixelWithDistance(port);
		twitterChecker = new Twitter();
		twitterChecker.setBoard(this.board);
		twitterChecker.startTwitter();
	}
	
	public SimpleNeoPixelWithDistance getBoard() {
		return this.board;
	}
	
	public static void main(String[] args) {


		SimpleController controller = new SimpleController("/dev/tty.usbmodem1411");
		
		try {
			Thread.sleep(500);

			System.out.println("Starting distance loop");
			while (true) {
				int distance = controller.getBoard().getDistance();
				if ( (distance < 20) && (distance>0)) {
					controller.getBoard().doStrip0Show();
				}
				Thread.sleep(20);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
}