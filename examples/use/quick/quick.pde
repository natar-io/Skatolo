/**
 * skatolo quick
 *
 * this example demonstrates how to quickly add Controllers such as
 * Button, Slider,Toggle and Numberbox to a sketch without having to set
 * positions, this is done automatically by skatolo.
 * controllers will be aligned horizontally - .linebreak() will
 * force the next controller to the next line.
 * the example shows as well how to link variables and functions to
 * a controller. this is done by assigning the name of the variable
 * or function to a controller.  
 *
 * by Andreas Schlegel, 2012
 * www.sojamo.de/libraries/skatolo
 *
 */


// skatoloquick
// this example demonstrates how to quickly add Controllers such as
// Button, Slider,Toggle and Numberbox to a sketch without having to set
// positions, this is done automatically by skatolo.
// controllers will be aligned horizontally - .linebreak() will
// force the next controller to the next line.
// the example shows as well how to link variables and functions to
// a controller. this is done by assigning the name of the variable
// or function to a controller.
//
// by andreas schlegel 2010
//

import fr.inria.skatolo.*;
import fr.inria.skatolo.events.*;
import fr.inria.skatolo.gui.controllers.*;

Skatolo skatolo;

float s1 = 5;
float s2 = 2;
boolean t1 = true;
boolean t2 = true;
boolean t3 = true;
boolean t4 = true;
float n1 = 100;
int n2 = 50;

void setup() {
  size(600,400);
  noStroke();
  skatolo = new Skatolo(this);
  skatolo.addButton("b1",1);
  skatolo.addButton("b2",2);
  skatolo.addButton("b3",3);
  skatolo.addButton("b4",4).linebreak();
  skatolo.addSlider("s1",0,10);
  skatolo.addSlider("s2",0,10).linebreak();
  skatolo.addButton("b5");
  skatolo.addToggle("t1");
  skatolo.addToggle("t2");
  skatolo.addToggle("t3");
  skatolo.addToggle("t4").linebreak();
  skatolo.addNumberbox("n1");
  skatolo.addNumberbox("n2");
}

void draw() {
  background(0);
  if(t1) {
    fill(s1*25);
    rect(0,200,150,height);
  }
  if(t2) {
    fill(s2*25);
    rect(150,200,150,height);
  }
  if(t3) {
    fill(n1);
    rect(300,200,150,height);
  }
  if(t4) {
    fill(n2);
    rect(450,200,150,height);
  }
}

void b1(int theN) {
  println(theN);
}

void b2(int theN) {
  println(theN);
}




