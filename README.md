

## GUI library for the programming environment processing. 

ControlP5 is a GUI and controller library for processing that can be used in application, applet and android mode. Controllers such as Sliders, Buttons, Toggles, Knobs, Textfields, RadioButtons, Checkboxes amongst others are easily added to a processing sketch. They can be arranged in separate control windows, and can be organized in tabs or groups.


## Important notes

* Update 27 Feb 2015 *

There is now a working example with :
* One global ControlP5 which works as usual.
* An object with a second renderer (`PGraphics`), and a second
  `ControlP5` object.
* This object can use the power of ControlP5 and use its own variables
  and methods automatically linked to the controller.

The examples in at the bottom of this page. 


This is a work in progress which just started. It means that the name might change if it becomes really a "fork" of ControlP5. 

For now the focus is to implement a new feature to handle multiple
“windows” within one PApplet. For the average Processing user, the use
would be the same. For advanced users it means that you could attach a
ControlP5 object to a specific PGraphics.

Another focus is to dive into this library to look at the introspection features and port them to other languages such as [ruby](https://github.com/jashkenas/ruby-processing) or [python](http://py.processing.org/) modes for Processing. Consequently, these other flavours of Processing could benefit from the ease of use of ControlP5 ! 


# Features 

* Since original ControlP5 *

## Automatic controller-event detect

ControlP5 offers a range of controllers that allow you to easily change and adjust values while your sketch is running. Each controller is identified by a unique name assigned when creating a controller. ControlP5 locates variables and functions inside your sketch and will link controllers to matching variables or functions automatically. Controller changes can easily be captured within your sketch by implementing the controlEvent function.


## Show, hide, load, save

Controllers that have been added to your sketch can be arranged in tabs and groups to keep your controller sets organized. All controllers are drawn on top of a processing sketch by default. Several key combinations allow you to show and hide the user interface, and to saved and loaded ControlP5 properties

## Examples

An extensive list of examples can be found at www.sojamo.de/libraries/controlP5/#examples.

Here is the example of the new features.  (work still in progress). 


``` java
/**
 * ControlP5 Offscreen rendering
 * 
 * In this example a "MyCanvas" object contains its own rendering and 
 * its own ControlP5 object. 
 * To show the power tha
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
	
	// enable the pointer (and disable the mouse as input) 
	cp5Local.getPointer().enable();
    }

    // method for ControlP5 to find the graphics. 
    public PGraphics getGraphics(){
	return pg;
    }
    
    public void draw(int x, int y){
	pg.beginDraw();

	pg.background(bgColor);

	// Set the pointer to interact with the controllers.
	cp5Local.getPointer().set(mouseX - x, mouseY - y);
	cp5Local.draw(pg);

	pg.endDraw();
    }
}


void mousePressed() {
  canvas.cp5Local.getPointer().pressed();
}

void mouseReleased() {
  canvas.cp5Local.getPointer().released();
}

```
