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
import fr.inria.controlP5.gui.Pointer;

ControlP5 cp5;

int hello;

Pointer pointer1, pointer2;

void setup() {
  size(400, 600);

  cp5 = new ControlP5(this);
  // disable outodraw because we want to draw our 
  // custom cursor on to of controlP5
  cp5.setAutoDraw(false);
  
  cp5.addSlider("hello", 0, 100, 50, 40, 40, 100, 20);
  
  // enable the pointer (and disable the mouse as input) 

  pointer1 = cp5.addPointer(1);
  pointer2 = cp5.addPointer(2);
}


void draw() {

  background(cp5.get("hello").getValue());

  //  pointer1.updatePosition(mouseX, mouseY - 100);
   cp5.updatePointer(1, mouseX, mouseY - 100);

  // first draw controlP5
  cp5.draw();


  for(Pointer p : cp5.getPointerList()){
      pushMatrix();
      translate(p.getX(), p.getY());
      stroke(255);
      line(-10,0,10,0);
      line(0,-10,0,10);
      popMatrix();
  }
  
  // the draw our pointer
  //  cp5.getPointer().set(width - mouseX, height - mouseY);
  pushMatrix();
  //  translate(cp5.getPointer().getX(), cp5.getPointer().getY());
  stroke(255);
  line(-10,0,10,0);
  line(0,-10,0,10);
  popMatrix();

}

void mousePressed() {
    //  cp5.getPointer().pressed();
}

void mouseReleased() {
    //   cp5.getPointer().released();
}
