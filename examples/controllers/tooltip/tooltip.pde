  /**
* skatolo Tooltip
*
* add a tooltip to a controller.
* hover your mouse on top of a slider in the example and wait 
* for 1 second for the tooltip to appear.
*
* find a list of public methods available for the Tooltip Controller
* at the bottom of this sketch.
*
* by Andreas Schlegel, 2011
* www.sojamo.de/libraries/skatolo
*
*/

import fr.inria.skatolo.*;
import fr.inria.skatolo.events.*;
import fr.inria.skatolo.gui.controllers.*;

Skatolo skatolo;

float s1 = 20, s2 = 100;

void setup() {
  size(700,400);
  smooth();
  noStroke();
  skatolo = new Skatolo(this);
  skatolo.begin(100,100);
  skatolo.addSlider("s1",10,200).linebreak();
  skatolo.addSlider("s2",0,150);
  skatolo.end();
  
  skatolo.getTooltip().setDelay(500);
  skatolo.getTooltip().register("s1","Changes the size of the ellipse.");
  skatolo.getTooltip().register("s2","Changes the Background");

}


void keyPressed() {
  println("unregistering the tooltip for s2");
  skatolo.getTooltip().unregister("s2");
}
void draw() {
  background(s2);
  fill(255,100);
  ellipse(width/2, height/2, s1,s1);
}



/*
a list of all methods available for the Tooltip Controller
use skatolo.printPublicMethodsFor(Tooltip.class);
to print the following list into the console.

You can find further details about class Tooltip in the javadoc.

Format:
ClassName : returnType methodName(parameter type)


skatolo.Tooltip : Label getLabel() 
skatolo.Tooltip : Tooltip disable() 
skatolo.Tooltip : Tooltip enable() 
skatolo.Tooltip : Tooltip register(Controller, String) 
skatolo.Tooltip : Tooltip register(String, String) 
skatolo.Tooltip : Tooltip setAlpha(int) 
skatolo.Tooltip : Tooltip setBorder(int) 
skatolo.Tooltip : Tooltip setColorBackground(int) 
skatolo.Tooltip : Tooltip setColorLabel(int) 
skatolo.Tooltip : Tooltip setDelay(long) 
skatolo.Tooltip : Tooltip setDisplay(ControllerDisplay) 
skatolo.Tooltip : Tooltip setHeight(int) 
skatolo.Tooltip : Tooltip setLabel(Label) 
skatolo.Tooltip : Tooltip setPositionOffset(float, float) 
skatolo.Tooltip : Tooltip setWidth(int) 
skatolo.Tooltip : Tooltip unregister(Controller) 
skatolo.Tooltip : Tooltip unregister(String) 
skatolo.Tooltip : boolean isEnabled() 
skatolo.Tooltip : int getBorder() 
java.lang.Object : String toString() 
java.lang.Object : boolean equals(Object) 

*/



