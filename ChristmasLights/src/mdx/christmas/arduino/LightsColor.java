package mdx.christmas.arduino;

/**
 * Simple Enum for the lights' colors
 * @author martinellimi
 */
public enum LightsColor {

	Green(0,255,0),
	Red(255,0,0),
	Blue(0,0,255),
	Yellow(255,255,0),
	Black(0,0,0),
	Grey(128, 128, 128),
	White(0, 0, 0);
	
	public int R;
	public int G;
	public int B;
	
	LightsColor(int R, int G, int B) { 
		this.R = R;
		this.G = G;
		this.B = B;
	}
}
