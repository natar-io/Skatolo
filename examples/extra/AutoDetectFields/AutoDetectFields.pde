/**
 * skatolo Autodetect Fields
 *
 * test sketch, controller values will automatically be set 
 * to its corresponding sketch fields.
 *
 * by Andreas Schlegel, 2011
 * www.sojamo.de/libraries/skatolo
 *
 */

import fr.inria.skatolo.*;
import fr.inria.skatolo.events.*;
import fr.inria.skatolo.gui.controllers.*;

int s1 = 50;
int s2 = 50;

int nb1 = 50;
int nb2 = 50;

int k1 = 50;
int k2 = 50;

boolean t1 = false;
boolean t2 = false;

int r1 = 20;
int r2 = 50;

void setup() {
  size(400,400);
  Skatolo skatolo = new Skatolo(this);
  skatolo.addSlider("s1",10,150,10,10,100,15).setLabel("50");
  skatolo.addSlider("s2",10,150,20,150,10,100,15).setLabel("20");
  
  skatolo.addNumberbox("nb1",10,50,100,15).setLabel("50");
  skatolo.addNumberbox("nb2",20,150,50,100,15).setLabel("20");
  
  skatolo.addKnob("k1",10,150,10,150,50).setLabel("50");
  skatolo.addKnob("k2",10,150,20,150,150,50).setLabel("20");
  
  skatolo.addToggle("t1",10,240,100,15).setLabel("false");
  skatolo.addToggle("t2",true,150,240,100,15).setLabel("true");
  
  skatolo.addButton("b1",50,10,280,100,15).setLabel("50");
  skatolo.addButton("b2",20,150,280,100,15).setLabel("20");
  
  skatolo.addRange("r1",10,150,r1,r2,10,320,100,15).setLabel("50");
  
}

void draw() {
  background(0);
}

void controlEvent(ControlEvent c) {
  println(c.getValue());
}
