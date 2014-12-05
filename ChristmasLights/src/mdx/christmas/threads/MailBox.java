package mdx.christmas.threads;

/**
 * This class is the mailBox and it controls the distanceSensor and the Twitter
 * One thread will run when the other is not executing, controlled by the executeFlag
 * When I receive a twitter, my distance sensor thread will wait and when I finish 
 * to execute the twitter, I will return the sensor distance thread.
 * @author martinellimi
 *
 */

public class MailBox {

	public static Character EXECUTE_DISTANCE = 'D';
	public static Character EXECUTE_TWITTER = 'T';
	
	private Character executeFlag;
	private static MailBox mailBox;
	
	private MailBox() {
		this.executeFlag = MailBox.EXECUTE_DISTANCE;
	}
	
	public static MailBox getInstance() {
		if (MailBox.mailBox == null) {
			MailBox.mailBox = new MailBox();
		}
		
		return MailBox.mailBox;
	}
	
	public synchronized Character getExecuteFlag() {
		return this.executeFlag;
	}
	
	public synchronized void setExecuteFlag(final Character executeFlag) {
		this.executeFlag = executeFlag;
	}
	
	public synchronized void executeDistanceSensor(final DistanceSensor distanceSensor) {
		if (!(MailBox.getInstance().getExecuteFlag().equals(MailBox.EXECUTE_DISTANCE))) {
			try {
				wait();
			}
			catch (final InterruptedException exception) {
				exception.printStackTrace();
			}
		}
		
		distanceSensor.startLeds();
	}
	
	public synchronized void changeExecuteFlag(Character flag) {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		MailBox.getInstance().setExecuteFlag(flag.equals(MailBox.EXECUTE_TWITTER) ? MailBox.EXECUTE_TWITTER : MailBox.EXECUTE_DISTANCE);
		
		notify();
	}
	
	public synchronized void executeTwitter(final Twitter twitter) {
		if (!(MailBox.getInstance().getExecuteFlag().equals(MailBox.EXECUTE_TWITTER))) {
			try {
				wait();
			}
			catch (final InterruptedException exception) {
				exception.printStackTrace();
			}
		}
		
		twitter.startLeds();
		
		changeExecuteFlag(MailBox.EXECUTE_DISTANCE);
	}
}
