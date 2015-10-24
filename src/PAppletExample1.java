
import processing.core.PApplet;
import processing.core.PImage;

public class PAppletExample1 extends PApplet {
	
	private String URL;
	private PImage background;
	
	// this method will run just once:
	@Override
	public void setup() {
		size(600, 400);
		
		// the url to image:
		this.URL = "https://upload.wikimedia.org/wikipedia/commons/1/1e/Stonehenge.jpg";
		
		// load image into memory, without displaying it:
		this.background = loadImage(this.URL, "jpg");
		
	}
	
	// this method will run in a loop
	@Override
	public void draw() {
		
		// resize image:
		// 0 mean we do not want to specify width
		// height is an instance variable from PApplet, to make it dynamic
		this.background.resize(0, height);
		
		// display the image:
		// 0, 0 are the position of the image (upper left)
		// this method must be in the draw not in the setup, because it must refreshed
		image(this.background, 0, 0);
		
		int sunColor[] = getSunColor(second()); // return the number of second of the last minute
		// fill color
		fill(sunColor[0], sunColor[1], sunColor[2]);
		
		// draw ellipse
		// x, y (center), width, height (if equal it is a circle)
		// dynamic
		ellipse(width/10, height/8, width/7, width/8);
	}
	
	private int[] getSunColor(double seconds) {
		int[] rgb = new int[3];
		
		// Scale the brightness of the yellow based on the seconds.  0 seconds 
		// is black.  30 seconds is bright yellow.
		double diffFrom30 = Math.abs(30-seconds);
		
		double ratio = diffFrom30/30;
		rgb[0] = (int)(255*ratio);
		rgb[1] = (int)(255*ratio);
		rgb[2] = 0;
		
		return rgb;
	}

}













