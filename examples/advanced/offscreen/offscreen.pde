/**
 * skatolo Offscreen rendering
 * 
 * In this example a "MyCanvas" object contains its own rendering and 
 * its own skatolo object. 
 *
 * by Jérémy Laviole
 * https://github.com/potioc/skatolo
 * 
 */

import fr.inria.skatolo.*;
import fr.inria.skatolo.events.*;
import fr.inria.skatolo.gui.controllers.*;

Skatolo skatoloGlobal;

int drawingOffsetX = 50;
int drawingOffsetY = 50;

// Automatically linked with the skatoloGlobal.
int bgColor = 20;

MyCanvas canvas;

void setup() {
    size(300, 300);

  skatoloGlobal = new Skatolo(this);

  // change the trigger event, by default it is PRESSED.
  skatoloGlobal.addBang("bang")
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
    Skatolo skatoloLocal;
    
    // Automaticaly linked to the controller, as usual. With skatoloLocal.
    int bgColor = 100;

    public MyCanvas(PApplet applet){

	// Graphics are required to initialize. skatolo correctly. 
	// or use skatoloLocal.draw(pg);
	pg = createGraphics(200, 200);
	
	skatoloLocal = new Skatolo(applet, this);

	// Manual draw required with offscreens. 
	skatoloLocal.setAutoDraw(false);

	// You can graphical elements as usual... 
	skatoloLocal.addSlider("bgColor", 0, 100, 50, 40, 40, 100, 20);
	
	// Disable the mouse as pointer. 
	skatoloLocal.getMousePointer().disable();

	// add a pointer with a specific ID
	skatoloLocal.addPointer(pointerID);
	skatoloLocal.getTooltip().setDelay(200);
	skatoloLocal.getTooltip().register("bgColor", "Background color");
    }

    // method for skatolo to find the graphics. 
    public PGraphics getGraphics(){
	return pg;
    }
    
    public void draw(int x, int y){
	pg.beginDraw();

	pg.background(bgColor);

	// Set the pointer to interact with the controllers.
	skatoloLocal.updatePointer(pointerID, 
			       mouseX - drawingOffsetX,
			       mouseY - drawingOffsetY);


	skatoloLocal.draw(pg);

	pg.endDraw();
    }
}


void mousePressed() {
    canvas.skatoloLocal.updatePointerPress(pointerID, true);
}

void mouseReleased() {
    canvas.skatoloLocal.updatePointerPress(pointerID, false);
}


