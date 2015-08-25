/**
 * ControlP5 Controlframe
 * with controlP5 2.0 all java.awt dependencies have been removed
 * as a consequence the option to display controllers in a separate
 * window had to be removed as well. 
 * this example shows you how to create a java.awt.frame and use controlP5
 *
 * by Andreas Schlegel, 2012
 * www.sojamo.de/libraries/controlp5
 *
 */

import java.awt.Frame;
import java.awt.BorderLayout;

import fr.inria.controlP5.*;
import fr.inria.controlP5.events.*;
import fr.inria.controlP5.gui.controllers.*;

private ControlP5 cp5;

ControlFrame cf;

int def;

void setup() {
  size(400, 400);
  cp5 = new ControlP5(this);
  
  // by calling function addControlFrame() a
  // new frame is created and an instance of class
  // ControlFrame is instanziated.
  cf = new ControlFrame(this);

  // add Controllers to the 'extra' Frame inside 
  // the ControlFrame class setup() method below.
  
}

void draw() {
  background(def);
}


// the ControlFrame class extends PApplet, so we 
// are creating a new processing applet inside a
// new frame with a controlP5 object loaded
public class ControlFrame extends PApplet {

    ControlP5 cp5;
    Object parent;
    int w, h;
    
    int abc = 100;

    public ControlFrame(PApplet parent){
	super();
	this.parent = parent;
	PApplet.runSketch(new String[]{this.getClass().getName()}, this);
    }

    public void settings(){
	size(200, 200);
    }
    
    public void setup() {
	frameRate(25);
	cp5 = new ControlP5(this);
	cp5.addSlider("abc").setRange(0, 255).setPosition(10,10);
	cp5.addSlider("def").plugTo(parent,"def").setRange(0, 255).setPosition(10,30);
    }
    
    public void draw() {
	background(abc);
    }
    
    public ControlP5 control() {
	return cp5;
    }
  
  
    
    
}

