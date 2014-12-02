package mdx.christmas.arduino;

import uk.ac.mdx.cs.asip.AsipClient;
import uk.ac.mdx.cs.asip.SimpleSerialBoard;
import uk.ac.mdx.cs.asip.services.DistanceService;

public class SimpleDistance extends SimpleSerialBoard {

	private DistanceService d0;

	public SimpleDistance(String port) {
		super(port);
		try {
			Thread.sleep(300);
			d0 = new DistanceService(0, getAsipClient());
			Thread.sleep(300);
			addService('D', d0);
			Thread.sleep(300);
			d0.enableContinuousReporting(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getDistance() {
		return d0.getDistance();
	}

	public static void main(String[] args) {

		// We could pass the port as an argument, for the moment
		// I hard-code it because I'm lazy.

		SimpleDistance testBoard = new SimpleDistance("/dev/tty.usbmodem1411");

		testBoard.setPinMode(13, AsipClient.OUTPUT);
		testBoard.digitalWrite(13, AsipClient.LOW);

		boolean isLEDon = false;

		try {

			Thread.sleep(300);

			while (true) {

				if ((testBoard.getDistance() < 20) && (!isLEDon)) {
					testBoard.digitalWrite(13, AsipClient.HIGH);
					isLEDon = true;
					System.out.println("Turning ON");
				}
				if ((testBoard.getDistance() >= 20) && isLEDon) {
					testBoard.digitalWrite(13, AsipClient.LOW);
					isLEDon = false;
					System.out.println("Turning OFF");
				}
				Thread.sleep(50);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
