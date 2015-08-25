
/**
* skatolo Toggle
*
*
* find a list of public methods available for the Toggle Controller
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

int col = color(255);

boolean toggleValue = false;

void setup() {
  size(400,400);
  smooth();
  skatolo = new Skatolo(this);
  
  // create a toggle
  skatolo.addToggle("toggleValue")
     .setPosition(40,100)
     .setSize(50,20)
     ;
  
  // create a toggle and change the default look to a (on/off) switch look
  skatolo.addToggle("toggle")
     .setPosition(40,250)
     .setSize(50,20)
     .setValue(true)
     .setMode(skatolo.SWITCH)
     ;
     
}
  

void draw() {
  background(0);
  
  pushMatrix();
  
  if(toggleValue==true) {
    fill(255,255,220);
  } else {
    fill(128,128,110);
  }
  translate(280,100);
  ellipse(0,0,100,100);
  
  
  translate(0,150);
  fill(col);
  ellipse(0,0,40,40);
  
  popMatrix();
}



void toggle(boolean theFlag) {
  if(theFlag==true) {
    col = color(255);
  } else {
    col = color(100);
  }
  println("a toggle event.");
}






/*
a list of all methods available for the Toggle Controller
use skatolo.printPublicMethodsFor(Toggle.class);
to print the following list into the console.

You can find further details about class Toggle in the javadoc.

Format:
ClassName : returnType methodName(parameter type)


skatolo.Toggle : Toggle setMode(int) 
skatolo.Toggle : Toggle setState(boolean) 
skatolo.Toggle : Toggle setValue(boolean) 
skatolo.Toggle : Toggle setValue(float) 
skatolo.Toggle : Toggle toggle() 
skatolo.Toggle : Toggle update() 
skatolo.Toggle : boolean getState() 
skatolo.Controller : CColor getColor() 
skatolo.Controller : ControlBehavior getBehavior() 
skatolo.Controller : ControlWindow getControlWindow() 
skatolo.Controller : ControlWindow getWindow() 
skatolo.Controller : Controller addCallback(CallbackListener) 
skatolo.Controller : Controller addListener(ControlListener) 
skatolo.Controller : Controller hide() 
skatolo.Controller : Controller linebreak() 
skatolo.Controller : Controller listen(boolean) 
skatolo.Controller : Controller lock() 
skatolo.Controller : Controller plugTo(Object) 
skatolo.Controller : Controller plugTo(Object, String) 
skatolo.Controller : Controller plugTo(Object[]) 
skatolo.Controller : Controller plugTo(Object[], String) 
skatolo.Controller : Controller registerProperty(String) 
skatolo.Controller : Controller registerProperty(String, String) 
skatolo.Controller : Controller registerTooltip(String) 
skatolo.Controller : Controller removeBehavior() 
skatolo.Controller : Controller removeCallback() 
skatolo.Controller : Controller removeCallback(CallbackListener) 
skatolo.Controller : Controller removeListener(ControlListener) 
skatolo.Controller : Controller removeProperty(String) 
skatolo.Controller : Controller removeProperty(String, String) 
skatolo.Controller : Controller setArrayValue(float[]) 
skatolo.Controller : Controller setArrayValue(int, float) 
skatolo.Controller : Controller setBehavior(ControlBehavior) 
skatolo.Controller : Controller setBroadcast(boolean) 
skatolo.Controller : Controller setCaptionLabel(String) 
skatolo.Controller : Controller setColor(CColor) 
skatolo.Controller : Controller setColorActive(int) 
skatolo.Controller : Controller setColorBackground(int) 
skatolo.Controller : Controller setColorCaptionLabel(int) 
skatolo.Controller : Controller setColorForeground(int) 
skatolo.Controller : Controller setColorValueLabel(int) 
skatolo.Controller : Controller setDecimalPrecision(int) 
skatolo.Controller : Controller setDefaultValue(float) 
skatolo.Controller : Controller setDisplay(ControllerDisplay) 
skatolo.Controller : Controller setHeight(int) 
skatolo.Controller : Controller setId(int) 
skatolo.Controller : Controller setImages(PImage, PImage, PImage) 
skatolo.Controller : Controller setImages(PImage, PImage, PImage, PImage) 
skatolo.Controller : Controller setLabelVisible(boolean) 
skatolo.Controller : Controller setLock(boolean) 
skatolo.Controller : Controller setMax(float) 
skatolo.Controller : Controller setMin(float) 
skatolo.Controller : Controller setMoveable(boolean) 
skatolo.Controller : Controller setPosition(PVector) 
skatolo.Controller : Controller setPosition(float, float) 
skatolo.Controller : Controller setSize(PImage) 
skatolo.Controller : Controller setSize(int, int) 
skatolo.Controller : Controller setStringValue(String) 
skatolo.Controller : Controller setUpdate(boolean) 
skatolo.Controller : Controller setValueLabel(String) 
skatolo.Controller : Controller setVisible(boolean) 
skatolo.Controller : Controller setWidth(int) 
skatolo.Controller : Controller show() 
skatolo.Controller : Controller unlock() 
skatolo.Controller : Controller unplugFrom(Object) 
skatolo.Controller : Controller unplugFrom(Object[]) 
skatolo.Controller : Controller unregisterTooltip() 
skatolo.Controller : Controller update() 
skatolo.Controller : Controller updateSize() 
skatolo.Controller : ControllerProperty getProperty(String) 
skatolo.Controller : ControllerProperty getProperty(String, String) 
skatolo.Controller : Label getCaptionLabel() 
skatolo.Controller : Label getValueLabel() 
skatolo.Controller : List getControllerPlugList() 
skatolo.Controller : PImage setImage(PImage) 
skatolo.Controller : PImage setImage(PImage, int) 
skatolo.Controller : PVector getAbsolutePosition() 
skatolo.Controller : PVector getPosition() 
skatolo.Controller : String getAddress() 
skatolo.Controller : String getInfo() 
skatolo.Controller : String getLabel() 
skatolo.Controller : String getName() 
skatolo.Controller : String getStringValue() 
skatolo.Controller : String toString() 
skatolo.Controller : Tab getTab() 
skatolo.Controller : boolean isActive() 
skatolo.Controller : boolean isBroadcast() 
skatolo.Controller : boolean isInside() 
skatolo.Controller : boolean isListening() 
skatolo.Controller : boolean isLock() 
skatolo.Controller : boolean isMouseOver() 
skatolo.Controller : boolean isMousePressed() 
skatolo.Controller : boolean isMoveable() 
skatolo.Controller : boolean isUpdate() 
skatolo.Controller : boolean isVisible() 
skatolo.Controller : float getArrayValue(int) 
skatolo.Controller : float getDefaultValue() 
skatolo.Controller : float getMax() 
skatolo.Controller : float getMin() 
skatolo.Controller : float getValue() 
skatolo.Controller : float[] getArrayValue() 
skatolo.Controller : int getHeight() 
skatolo.Controller : int getId() 
skatolo.Controller : int getWidth() 
skatolo.Controller : int listenerSize() 
skatolo.Controller : void remove() 
skatolo.Controller : void setDisplay(ControllerDisplay, int) 
java.lang.Object : String toString() 
java.lang.Object : boolean equals(Object) 


*/


