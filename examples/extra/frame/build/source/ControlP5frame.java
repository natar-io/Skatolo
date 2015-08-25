import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.awt.Frame; 
import java.awt.BorderLayout; 
import fr.inria.skatolo.*; 
import fr.inria.skatolo.events.*; 
import fr.inria.skatolo.gui.controllers.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class skatoloframe extends PApplet {

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








private Skatolo skatolo;

ControlFrame cf;

int def;

public void setup() {
  
  skatolo = new Skatolo(this);
  
  // by calling function addControlFrame() a
  // new frame is created and an instance of class
  // ControlFrame is instanziated.
  cf = new ControlFrame(this);

  // add Controllers to the 'extra' Frame inside 
  // the ControlFrame class setup() method below.
  
}

public void draw() {
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

  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "skatoloframe" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
