
/**
* skatolo Mouseover
*
*
* this example demonstrates the use of the mouseover methods 
* isMouseOver(), getMouseOverList()
*
* by Andreas Schlegel, 2012
* www.sojamo.de/libraries/skatolo
*
*/

import fr.inria.skatolo.*;
import fr.inria.skatolo.events.*;
import fr.inria.skatolo.gui.controllers.*;
import fr.inria.skatolo.gui.group.*;

Skatolo skatolo;

public int slider1 = 64;
public int slider2 = 128;


void setup() {
  size(700, 400);
  smooth();
  skatolo = new Skatolo(this);

  skatolo.addSlider("slider1", 0, 255, 20, 100, 128, 20);
  skatolo.addSlider("slider2", 0, 255, 20, 150, 128, 20);

  ListBox l = skatolo.addListBox("myList", 250, 260, 100, 80);
  for (int i=0;i<80;i++) {
    l.addItem("item "+i, i);
  }
  
  skatolo.addButton("b1", 0, 20, 350, 80, 12);
  skatolo.addButton("b2", 0, 101, 350, 80, 12);

  skatolo.setMoveable(true);

}

color hover = color(0, 230, 150);

void draw() {
  background(0);
  // check if the mouse is inside of any of the controllers 
  // displayed in the main window
  if(skatolo.isMouseOver()) {
    fill(hover);
  } else {
    fill(128);
  }
  
  ellipse(45,50,50,50);
  
  // check if the mouse is hovering controller slider1 and set the color accordingly
  fill(skatolo.isMouseOver(skatolo.getController("slider1")) ? hover:color(slider1));
  rect(250, 100, 100, 20);
  
  
  fill(skatolo.isMouseOver(skatolo.getController("slider2")) ? hover:color(slider2));
  rect(250, 150, 100, 20);
  
  fill(skatolo.isMouseOver(skatolo.getController("b1")) ? hover:color(128));
  ellipse(30, 330, 20, 20);
  
  fill(skatolo.isMouseOver(skatolo.getController("b2")) ? hover:color(128));
  ellipse(110, 330, 20, 20);
  
  fill(skatolo.isMouseOver(skatolo.getGroup("myList")) ? hover:color(128));
  ellipse(260, 230, 20, 20);
  
}


void mousePressed() {
  // print the current mouseoverlist on mouse pressed
  print("The Current mouseoverlist:\t");
  println(skatolo.getWindow().getMouseOverList());
}


/*
a list of all methods available for the skatolo Controller
use skatolo.printPublicMethodsFor(skatolo.class);
to print the full list into the console.

You can find further details about class skatolo in the javadoc.

Format:
ClassName : returnType methodName(parameter type)

skatolo.skatolo : List getMouseOverList() 
skatolo.skatolo : boolean isMouseOver() 
skatolo.skatolo : boolean isMouseOver(ControllerInterface) 

*/


