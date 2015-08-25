/**
* skatolo Pointer
*
* Default mouse actions use the Pointer class to trigger events.
* you can manipulate the x and y fields of the Pointer class
* for customizing input events for example when using a 
* different input than the mouse.
* Here in this example the mouse coordiates are reveresed.
*
* by Andreas Schlegel, 2012
* www.sojamo.de/libraries/skatolo
*
*/

import fr.inria.skatolo.*;
import fr.inria.skatolo.events.*;
import fr.inria.skatolo.gui.controllers.*;

Skatolo skatolo;

int hello;

void setup() {
  size(400, 600);

  skatolo = new Skatolo(this);
  // disable outodraw because we want to draw our 
  // custom cursor on to of skatolo
  skatolo.setAutoDraw(true);
  
  skatolo.addSlider("hello", 0, 100, 50, 40, 40, 100, 20);
  
  // enable the pointer (and disable the mouse as input) 
  skatolo.getPointer().enable();
  skatolo.getPointer().set(width/2, height/2);
}


void draw() {



    println("Hello " + hello);
  background(skatolo.get("hello").getValue());
  // first draw skatolo
  skatolo.draw();
  
  // the draw our pointer
  skatolo.getPointer().set(width - mouseX, height - mouseY);
  pushMatrix();
  translate(skatolo.getPointer().getX(), skatolo.getPointer().getY());
  stroke(255);
  line(-10,0,10,0);
  line(0,-10,0,10);
  popMatrix();
  println(skatolo.isMouseOver());
}

void mousePressed() {
  skatolo.getPointer().pressed();
}

void mouseReleased() {
  skatolo.getPointer().released();
}
