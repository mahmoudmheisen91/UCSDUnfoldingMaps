package GUIModule;

import processing.core.PApplet;

public class MyDisplay extends PApplet {
	
	@Override
	public void setup() {
		size(400, 400);
		
		// set the background color:
		background(200, 200, 200);
	}
	
	@Override
	public void draw() {
		// yellow:
		fill(255, 255, 0);
		// face:
		ellipse(200, 200, 390, 390);
		
		// black:
		fill(0, 0, 0);
		// eyes:
		ellipse(120, 130, 50, 70); // left
		ellipse(280, 130, 50, 70); // right
		
		// nofill method: just line:
		//noFill();
		
		// mouth
		// arc: the first 4 parameters are the ellipse to trace
		// last two are the start and end angle
		arc(200, 280, 275, 75, 0, PI);
	}

}
