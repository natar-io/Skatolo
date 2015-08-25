/**
* skatolo Multi-Touch
*
* Default mouse actions use the Pointer class to trigger events.
* you can manipulate the x and y fields of the Pointer class
* for customizing input events for example when using a 
* different input than the mouse.
* skatolo can now have multiple Pointers !
*
* by Jeremy Laviole, 2015
* https://github.com/potioc/skatolo
*
*/

import fr.inria.skatolo.*;
import fr.inria.skatolo.events.*;
import fr.inria.skatolo.gui.controllers.*;
import fr.inria.skatolo.gui.Pointer;

Skatolo skatolo;

int hello;

Pointer pointer1, pointer2;

void setup() {
  size(400, 600);

  skatolo = new Skatolo(this);
  // disable outodraw because we want to draw our 
  // custom cursor on to of skatolo
  skatolo.setAutoDraw(false);
  
  skatolo.addSlider("hello", 0, 100, 50, 40, 40, 100, 20);
  

  // Disable the mouse 
  skatolo.getMousePointer().disable();

  pointer1 = skatolo.addPointer(1);
  pointer2 = skatolo.addPointer(2);

  noCursor();
}


void draw() {

  background(skatolo.get("hello").getValue());

  //  pointer1.updatePosition(mouseX, mouseY - 100);
   skatolo.updatePointer(1, mouseX, mouseY - 100);
   skatolo.updatePointer(2, mouseX, mouseY);

  for(Pointer p : skatolo.getPointerList()){

      if(!p.isEnabled())
	  continue;

      pushMatrix();
      translate(p.getX(), p.getY());

      stroke(255);
      if(p.isPressed()){
	  stroke(255, 0, 0);	  
      } 
      if(p.isReleased()){
	  stroke(0, 255, 0);
      } 
      if(p.isDragged()){
	  stroke(0, 0, 255);
      }
      
      
      line(-10,0,10,0);
      line(0,-10,0,10);
      popMatrix();
  }

  // first draw skatolo
  skatolo.draw();

  
}

void mousePressed() {
    skatolo.updatePointerPress(1, true);

}

void mouseReleased() {
    skatolo.updatePointerPress(1, false);
}
