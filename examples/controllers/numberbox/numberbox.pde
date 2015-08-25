/**
* skatolo Numberbox
*
*
* find a list of public methods available for the Numberbox Controller
* at the bottom of this sketch.
*
* by Andreas Schlegel, 2012
* www.sojamo.de/libraries/skatolo
*
*/

import fr.inria.skatolo.*;
import fr.inria.skatolo.gui.controllers.*;
import fr.inria.skatolo.gui.*;


Skatolo skatolo;

int myColorBackground = color(0,0,0);

public float numberboxValue = 100;

void setup() {
  size(700,400);
  noStroke();
  skatolo = new Skatolo(this);
  
  skatolo.addNumberbox("numberbox")
     .setPosition(100,160)
     .setSize(100,14)
     .setScrollSensitivity(1.1)
     .setValue(50)
     ;
  

  skatolo.addNumberbox("numberboxValue")
     .setPosition(100,200)
     .setSize(100,14)
     .setRange(0,200)
     .setMultiplier(0.1) // set the sensitifity of the numberbox
     .setDirection(Controller.HORIZONTAL) // change the control direction to left/right
     .setValue(100)
     ;
  
}

void draw() {
  background(myColorBackground);
  fill(numberboxValue);
  rect(0,0,width,100);
}

void numberbox(int theColor) {
  myColorBackground = color(theColor);
  println("a numberbox event. setting background to "+theColor);
}



/*
a list of all methods available for the Numberbox Controller
use skatolo.printPublicMethodsFor(Numberbox.class);
to print the following list into the console.

You can find further details about class Numberbox in the javadoc.

Format:
ClassName : returnType methodName(parameter type)

skatolo.Numberbox : Numberbox linebreak() 
skatolo.Numberbox : Numberbox setDirection(int) 
skatolo.Numberbox : Numberbox setMultiplier(float) 
skatolo.Numberbox : Numberbox setRange(float, float) 
skatolo.Numberbox : Numberbox setScrollSensitivity(float) 
skatolo.Numberbox : Numberbox setValue(float) 
skatolo.Numberbox : Numberbox shuffle() 
skatolo.Numberbox : Numberbox update() 
skatolo.Numberbox : float getMultiplier() 
skatolo.Controller : CColor getColor() 
skatolo.Controller : ControlBehavior getBehavior() 
skatolo.Controller : ControlWindow getControlWindow() 
skatolo.Controller : ControlWindow getWindow() 
skatolo.Controller : ControllerProperty getProperty(String) 
skatolo.Controller : ControllerProperty getProperty(String, String) 
skatolo.Controller : Label getCaptionLabel() 
skatolo.Controller : Label getValueLabel() 
skatolo.Controller : List getControllerPlugList() 
skatolo.Controller : Numberbox addCallback(CallbackListener) 
skatolo.Controller : Numberbox addListener(ControlListener) 
skatolo.Controller : Numberbox bringToFront() 
skatolo.Controller : Numberbox bringToFront(ControllerInterface) 
skatolo.Controller : Numberbox hide() 
skatolo.Controller : Numberbox linebreak() 
skatolo.Controller : Numberbox listen(boolean) 
skatolo.Controller : Numberbox lock() 
skatolo.Controller : Numberbox plugTo(Object) 
skatolo.Controller : Numberbox plugTo(Object, String) 
skatolo.Controller : Numberbox plugTo(Object[]) 
skatolo.Controller : Numberbox plugTo(Object[], String) 
skatolo.Controller : Numberbox registerProperty(String) 
skatolo.Controller : Numberbox registerProperty(String, String) 
skatolo.Controller : Numberbox registerTooltip(String) 
skatolo.Controller : Numberbox removeBehavior() 
skatolo.Controller : Numberbox removeCallback() 
skatolo.Controller : Numberbox removeCallback(CallbackListener) 
skatolo.Controller : Numberbox removeListener(ControlListener) 
skatolo.Controller : Numberbox removeProperty(String) 
skatolo.Controller : Numberbox removeProperty(String, String) 
skatolo.Controller : Numberbox setArrayValue(float[]) 
skatolo.Controller : Numberbox setArrayValue(int, float) 
skatolo.Controller : Numberbox setBehavior(ControlBehavior) 
skatolo.Controller : Numberbox setBroadcast(boolean) 
skatolo.Controller : Numberbox setCaptionLabel(String) 
skatolo.Controller : Numberbox setColor(CColor) 
skatolo.Controller : Numberbox setColorActive(int) 
skatolo.Controller : Numberbox setColorBackground(int) 
skatolo.Controller : Numberbox setColorCaptionLabel(int) 
skatolo.Controller : Numberbox setColorForeground(int) 
skatolo.Controller : Numberbox setColorValueLabel(int) 
skatolo.Controller : Numberbox setDecimalPrecision(int) 
skatolo.Controller : Numberbox setDefaultValue(float) 
skatolo.Controller : Numberbox setHeight(int) 
skatolo.Controller : Numberbox setId(int) 
skatolo.Controller : Numberbox setImages(PImage, PImage, PImage) 
skatolo.Controller : Numberbox setImages(PImage, PImage, PImage, PImage) 
skatolo.Controller : Numberbox setLabelVisible(boolean) 
skatolo.Controller : Numberbox setLock(boolean) 
skatolo.Controller : Numberbox setMax(float) 
skatolo.Controller : Numberbox setMin(float) 
skatolo.Controller : Numberbox setMouseOver(boolean) 
skatolo.Controller : Numberbox setMoveable(boolean) 
skatolo.Controller : Numberbox setPosition(PVector) 
skatolo.Controller : Numberbox setPosition(float, float) 
skatolo.Controller : Numberbox setSize(PImage) 
skatolo.Controller : Numberbox setSize(int, int) 
skatolo.Controller : Numberbox setStringValue(String) 
skatolo.Controller : Numberbox setUpdate(boolean) 
skatolo.Controller : Numberbox setValueLabel(String) 
skatolo.Controller : Numberbox setView(ControllerView) 
skatolo.Controller : Numberbox setVisible(boolean) 
skatolo.Controller : Numberbox setWidth(int) 
skatolo.Controller : Numberbox show() 
skatolo.Controller : Numberbox unlock() 
skatolo.Controller : Numberbox unplugFrom(Object) 
skatolo.Controller : Numberbox unplugFrom(Object[]) 
skatolo.Controller : Numberbox unregisterTooltip() 
skatolo.Controller : Numberbox update() 
skatolo.Controller : Numberbox updateSize() 
skatolo.Controller : PImage setImage(PImage) 
skatolo.Controller : PImage setImage(PImage, int) 
skatolo.Controller : PVector getAbsolutePosition() 
skatolo.Controller : PVector getPosition() 
skatolo.Controller : String getAddress() 
skatolo.Controller : String getInfo() 
skatolo.Controller : String getName() 
skatolo.Controller : String getStringValue() 
skatolo.Controller : String toString() 
skatolo.Controller : Tab getTab() 
skatolo.Controller : boolean isActive() 
skatolo.Controller : boolean isBroadcast() 
skatolo.Controller : boolean isInside() 
skatolo.Controller : boolean isLabelVisible() 
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
skatolo.Controller : int getDecimalPrecision() 
skatolo.Controller : int getHeight() 
skatolo.Controller : int getId() 
skatolo.Controller : int getWidth() 
skatolo.Controller : int listenerSize() 
skatolo.Controller : void remove() 
skatolo.Controller : void setView(ControllerView, int)   
java.lang.Object : String toString() 
java.lang.Object : boolean equals(Object) 


*/



