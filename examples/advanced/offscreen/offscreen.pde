/**
 * ControlP5 Offscreen rendering
 * 
 * In this example a "MyCanvas" object contains its own rendering and 
 * its own ControlP5 object. 
 *
 * by Jérémy Laviole
 * https://github.com/potioc/ControlP5
 * 
 */

import fr.inria.controlP5.*;
import fr.inria.controlP5.events.*;
import fr.inria.controlP5.gui.controllers.*;

ControlP5 cp5Global;

int drawingOffsetX = 50;
int drawingOffsetY = 50;

// Automatically linked with the cp5Global.
int bgColor = 20;

MyCanvas canvas;

void setup() {
    size(300, 300);

  cp5Global = new ControlP5(this);

  // change the trigger event, by default it is PRESSED.
  cp5Global.addBang("bang")
     .setPosition(10, 10)
     .setSize(40, 20)
     ;

  canvas = new MyCanvas(this);
}

void draw() {
    background(bgColor);

    // draw within the offscren buffer. 
    canvas.draw(drawingOffsetX, drawingOffsetY);

    // render it as an image.
    image(canvas.getGraphics(), drawingOffsetX, drawingOffsetY); 
}

void bang(){
    bgColor += 20;
    if(bgColor > 255)
	bgColor = 10;

}

final int pointerID = 1;

public class MyCanvas{

    PGraphics pg;
    ControlP5 cp5Local;
    
    // Automaticaly linked to the controller, as usual. With cp5Local.
    int bgColor = 100;

    public MyCanvas(PApplet applet){

	// Graphics are required to initialize. controlP5 correctly. 
	// or use cp5Local.draw(pg);
	pg = createGraphics(200, 200);
	
	cp5Local = new ControlP5(applet, this);

	// Manual draw required with offscreens. 
	cp5Local.setAutoDraw(false);

	// You can graphical elements as usual... 
	cp5Local.addSlider("bgColor", 0, 100, 50, 40, 40, 100, 20);
	
	// Disable the mouse as pointer. 
	cp5Local.getMousePointer().disable();

	// add a pointer with a specific ID
	cp5Local.addPointer(pointerID);
	cp5Local.getTooltip().setDelay(200);
	cp5Local.getTooltip().register("bgColor", "Background color");
    }

    // method for ControlP5 to find the graphics. 
    public PGraphics getGraphics(){
	return pg;
    }
    
    public void draw(int x, int y){
	pg.beginDraw();

	pg.background(bgColor);

	// Set the pointer to interact with the controllers.
	cp5Local.updatePointer(pointerID, 
			       mouseX - drawingOffsetX,
			       mouseY - drawingOffsetY);


	cp5Local.draw(pg);

	pg.endDraw();
    }
}


void mousePressed() {
    canvas.cp5Local.updatePointerPress(pointerID, true);
}

void mouseReleased() {
    canvas.cp5Local.updatePointerPress(pointerID, false);
}


