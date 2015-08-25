/**
* skatolo ControllerStyle
*
*
* find a list of public methods available for the ControllerStyle Controller
* at the bottom of this sketch.
*
* by Andreas Schlegel, 2011
* www.sojamo.de/libraries/skatolo
*
*/
import fr.inria.skatolo.*;
import fr.inria.skatolo.events.*;
import fr.inria.skatolo.gui.*;
import fr.inria.skatolo.gui.controllers.*;


Skatolo skatolo;

float v1 = 50, v2 = 100, v3 = 100, v4 = 100;

void setup() {
  size(400,600);
  smooth();
  noStroke();
  skatolo = new Skatolo(this);
  
  skatolo.begin(100,100);
  skatolo.addSlider("v1",0,255).linebreak();
  skatolo.addSlider("v2",0,200).linebreak();
  skatolo.addSlider("v3",0,300).linebreak();
  skatolo.addSlider("v4",0,400);
  skatolo.end();
  
  // change the caption label for controller v1 and apply styles
  skatolo.getController("v1").setCaptionLabel("Background");
  style("v1");
  
  // change the caption label for controller v2 and apply styles
  skatolo.getController("v2").setCaptionLabel("Ellipse A");
  style("v2");
  
  // change the caption label for controller v3 and apply styles
  skatolo.getController("v3").setCaptionLabel("Ellipse B");
  style("v3");
  
  // change the caption label for controller v3 and apply styles
  skatolo.getController("v4").setCaptionLabel("Ellipse C");
  style("v4");
  
  
}

void style(String theControllerName) {
  Controller c = skatolo.getController(theControllerName);
  // adjust the height of the controller
  c.setHeight(15);
  
  // add some padding to the caption label background
  c.getCaptionLabel().getStyle().setPadding(4,4,3,4);
  
  // shift the caption label up by 4px
  c.getCaptionLabel().getStyle().setMargin(-4,0,0,0); 
  
  // set the background color of the caption label
  c.getCaptionLabel().setColorBackground(color(10,20,30,140));
}

void draw() {
  background(v1);
  fill(255,255,220,100);
  ellipse(width/2-100, height/2-100,v2 + 50,v2 + 50);
  ellipse(width/2+100, height/2,v3,v3);
  ellipse(width/2, height/2+100,v4,v4);
}



/*
a list of all methods available for the ControllerStyle Controller
use skatolo.printPublicMethodsFor(ControllerStyle.class);
to print the following list into the console.

You can find further details about class ControllerStyle in the javadoc.

Format:
ClassName : returnType methodName(parameter type)




skatolo.ControllerStyle : ControllerStyle margin(int) 
skatolo.ControllerStyle : ControllerStyle margin(int, int, int, int) 
skatolo.ControllerStyle : ControllerStyle moveMargin(int, int, int, int) 
skatolo.ControllerStyle : ControllerStyle movePadding(int, int, int, int) 
skatolo.ControllerStyle : ControllerStyle padding(int) 
skatolo.ControllerStyle : ControllerStyle padding(int, int, int, int) 
skatolo.ControllerStyle : ControllerStyle setMargin(int, int, int, int) 
skatolo.ControllerStyle : ControllerStyle setMarginBottom(int) 
skatolo.ControllerStyle : ControllerStyle setMarginLeft(int) 
skatolo.ControllerStyle : ControllerStyle setMarginRight(int) 
skatolo.ControllerStyle : ControllerStyle setMarginTop(int) 
skatolo.ControllerStyle : ControllerStyle setPadding(int, int, int, int) 
skatolo.ControllerStyle : ControllerStyle setPaddingBottom(int) 
skatolo.ControllerStyle : ControllerStyle setPaddingLeft(int) 
skatolo.ControllerStyle : ControllerStyle setPaddingRight(int) 
skatolo.ControllerStyle : ControllerStyle setPaddingTop(int) 
java.lang.Object : String toString() 
java.lang.Object : boolean equals(Object) 


*/

