/**
* ControlP5 Multi-Touch
*
* Default mouse actions use the Pointer class to trigger events.
* you can manipulate the x and y fields of the Pointer class
* for customizing input events for example when using a 
* different input than the mouse.
* cp5 can now have multiple Pointers !
*
* by Jeremy Laviole, 2015
* https://github.com/potioc/ControlP5
*
*/

import fr.inria.controlP5.*;
import fr.inria.controlP5.events.*;
import fr.inria.controlP5.gui.controllers.*;

ControlP5 cp5;

int hello;

void setup() {
  size(400, 600);

  cp5 = new ControlP5(this);
  // disable outodraw because we want to draw our 
  // custom cursor on to of controlP5
  cp5.setAutoDraw(false);
  
  cp5.addSlider("hello", 0, 100, 50, 40, 40, 100, 20);
  
  // enable the pointer (and disable the mouse as input) 
  cp5.getPointer().enable();
  cp5.getPointer().set(width/2, height/2);
}


void draw() {

  background(cp5.get("hello").getValue());
  // first draw controlP5
  cp5.draw();
  
  // the draw our pointer
  cp5.getPointer().set(width - mouseX, height - mouseY);
  pushMatrix();
  translate(cp5.getPointer().getX(), cp5.getPointer().getY());
  stroke(255);
  line(-10,0,10,0);
  line(0,-10,0,10);
  popMatrix();

}

void mousePressed() {
  cp5.getPointer().pressed();
}

void mouseReleased() {
  cp5.getPointer().released();
}
