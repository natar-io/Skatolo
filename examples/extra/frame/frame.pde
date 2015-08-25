/**
 * skatolo Controlframe
 * with skatolo 2.0 all java.awt dependencies have been removed
 * as a consequence the option to display controllers in a separate
 * window had to be removed as well. 
 * this example shows you how to create a java.awt.frame and use skatolo
 *
 * by Andreas Schlegel, 2012
 * www.sojamo.de/libraries/skatolo
 *
 */

import java.awt.Frame;
import java.awt.BorderLayout;

import fr.inria.skatolo.*;
import fr.inria.skatolo.events.*;
import fr.inria.skatolo.gui.controllers.*;

private Skatolo skatolo;

ControlFrame cf;

int def;

void setup() {
  size(400, 400);
  skatolo = new Skatolo(this);
  
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
// new frame with a skatolo object loaded
public class ControlFrame extends PApplet {

    Skatolo skatolo;
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
	skatolo = new Skatolo(this);
	skatolo.addSlider("abc").setRange(0, 255).setPosition(10,10);
	skatolo.addSlider("def").plugTo(parent,"def").setRange(0, 255).setPosition(10,30);
    }
    
    public void draw() {
	background(abc);
    }
    
    public skatolo control() {
	return skatolo;
    }
  
  
    
    
}

