/**
* skatolo Knob
*
*
* find a list of public methods available for the Knob Controller
* at the bottom of this sketch.
*
* by Andreas Schlegel, 2012
* www.sojamo.de/libraries/skatolo
*
*/

import fr.inria.skatolo.*;
import fr.inria.skatolo.gui.controllers.*;

Skatolo skatolo;

int myColorBackground = color(0,0,0);
int knobValue = 100;

Knob myKnobA;
Knob myKnobB;

void setup() {
  size(700,400);
  smooth();
  noStroke();
  
  skatolo = new Skatolo(this);
  
  myKnobA = skatolo.addKnob("knob")
               .setRange(0,255)
               .setValue(50)
               .setPosition(100,70)
               .setRadius(50)
               .setDragDirection(Knob.VERTICAL)
               ;
                     
  myKnobB = skatolo.addKnob("knobValue")
               .setRange(0,255)
               .setValue(220)
               .setPosition(100,210)
               .setRadius(50)
               .setNumberOfTickMarks(10)
               .setTickMarkLength(4)
               .snapToTickMarks(true)
               .setColorForeground(color(255))
               .setColorBackground(color(0, 160, 100))
               .setColorActive(color(255,255,0))
               .setDragDirection(Knob.HORIZONTAL)
               ;
}

void draw() {
  background(myColorBackground);
  fill(knobValue);
  rect(0,height/2,width,height/2);
  fill(0,100);
  rect(80,40,140,320);
}


void knob(int theValue) {
  myColorBackground = color(theValue);
  println("a knob event. setting background to "+theValue);
}


void keyPressed() {
  switch(key) {
    case('1'):myKnobA.setValue(180);break;
    case('2'):myKnobB.setConstrained(false).hideTickMarks().snapToTickMarks(false);break;
    case('3'):myKnobA.shuffle();myKnobB.shuffle();break;
  }
  
}

/*
a list of all methods available for the Knob Controller
use skatolo.printPublicMethodsFor(Knob.class);
to print the following list into the console.

You can find further details about class Knob in the javadoc.

Format:
ClassName : returnType methodName(parameter type)
 
skatolo.Knob : Knob setConstrained(boolean) 
skatolo.Knob : Knob setDragDirection(int) 
skatolo.Knob : Knob setMax(float) 
skatolo.Knob : Knob setMin(float) 
skatolo.Knob : Knob setNumberOfTickMarks(int) 
skatolo.Knob : Knob setRadius(float) 
skatolo.Knob : Knob setRange(float) 
skatolo.Knob : Knob setResolution(float) 
skatolo.Knob : Knob setScrollSensitivity(float) 
skatolo.Knob : Knob setSensitivity(float) 
skatolo.Knob : Knob setShowRange(boolean) 
skatolo.Knob : Knob setStartAngle(float) 
skatolo.Knob : Knob setTickMarkLength(int) 
skatolo.Knob : Knob setTickMarkWeight(float) 
skatolo.Knob : Knob setValue(float) 
skatolo.Knob : Knob setViewStyle(int) 
skatolo.Knob : Knob showTickMarks(boolean) 
skatolo.Knob : Knob shuffle() 
skatolo.Knob : Knob snapToTickMarks(boolean) 
skatolo.Knob : Knob update() 
skatolo.Knob : boolean isConstrained() 
skatolo.Knob : boolean isShowRange() 
skatolo.Knob : boolean isShowTickMarks() 
skatolo.Knob : float getAngle() 
skatolo.Knob : float getRadius() 
skatolo.Knob : float getRange() 
skatolo.Knob : float getResolution() 
skatolo.Knob : float getStartAngle() 
skatolo.Knob : float getTickMarkWeight() 
skatolo.Knob : float getValue() 
skatolo.Knob : int getDragDirection() 
skatolo.Knob : int getNumberOfTickMarks() 
skatolo.Knob : int getTickMarkLength() 
skatolo.Knob : int getViewStyle() 
skatolo.Controller : CColor getColor() 
skatolo.Controller : ControlBehavior getBehavior() 
skatolo.Controller : ControlWindow getControlWindow() 
skatolo.Controller : ControlWindow getWindow() 
skatolo.Controller : ControllerProperty getProperty(String) 
skatolo.Controller : ControllerProperty getProperty(String, String) 
skatolo.Controller : Knob addCallback(CallbackListener) 
skatolo.Controller : Knob addListener(ControlListener) 
skatolo.Controller : Knob bringToFront() 
skatolo.Controller : Knob bringToFront(ControllerInterface) 
skatolo.Controller : Knob hide() 
skatolo.Controller : Knob linebreak() 
skatolo.Controller : Knob listen(boolean) 
skatolo.Controller : Knob lock() 
skatolo.Controller : Knob plugTo(Object) 
skatolo.Controller : Knob plugTo(Object, String) 
skatolo.Controller : Knob plugTo(Object[]) 
skatolo.Controller : Knob plugTo(Object[], String) 
skatolo.Controller : Knob registerProperty(String) 
skatolo.Controller : Knob registerProperty(String, String) 
skatolo.Controller : Knob registerTooltip(String) 
skatolo.Controller : Knob removeBehavior() 
skatolo.Controller : Knob removeCallback() 
skatolo.Controller : Knob removeCallback(CallbackListener) 
skatolo.Controller : Knob removeListener(ControlListener) 
skatolo.Controller : Knob removeProperty(String) 
skatolo.Controller : Knob removeProperty(String, String) 
skatolo.Controller : Knob setArrayValue(float[]) 
skatolo.Controller : Knob setArrayValue(int, float) 
skatolo.Controller : Knob setBehavior(ControlBehavior) 
skatolo.Controller : Knob setBroadcast(boolean) 
skatolo.Controller : Knob setCaptionLabel(String) 
skatolo.Controller : Knob setColor(CColor) 
skatolo.Controller : Knob setColorActive(int) 
skatolo.Controller : Knob setColorBackground(int) 
skatolo.Controller : Knob setColorCaptionLabel(int) 
skatolo.Controller : Knob setColorForeground(int) 
skatolo.Controller : Knob setColorValueLabel(int) 
skatolo.Controller : Knob setDecimalPrecision(int) 
skatolo.Controller : Knob setDefaultValue(float) 
skatolo.Controller : Knob setHeight(int) 
skatolo.Controller : Knob setId(int) 
skatolo.Controller : Knob setImages(PImage, PImage, PImage) 
skatolo.Controller : Knob setImages(PImage, PImage, PImage, PImage) 
skatolo.Controller : Knob setLabelVisible(boolean) 
skatolo.Controller : Knob setLock(boolean) 
skatolo.Controller : Knob setMax(float) 
skatolo.Controller : Knob setMin(float) 
skatolo.Controller : Knob setMouseOver(boolean) 
skatolo.Controller : Knob setMoveable(boolean) 
skatolo.Controller : Knob setPosition(PVector) 
skatolo.Controller : Knob setPosition(float, float) 
skatolo.Controller : Knob setSize(PImage) 
skatolo.Controller : Knob setSize(int, int) 
skatolo.Controller : Knob setStringValue(String) 
skatolo.Controller : Knob setUpdate(boolean) 
skatolo.Controller : Knob setValueLabel(String) 
skatolo.Controller : Knob setView(ControllerView) 
skatolo.Controller : Knob setVisible(boolean) 
skatolo.Controller : Knob setWidth(int) 
skatolo.Controller : Knob show() 
skatolo.Controller : Knob unlock() 
skatolo.Controller : Knob unplugFrom(Object) 
skatolo.Controller : Knob unplugFrom(Object[]) 
skatolo.Controller : Knob unregisterTooltip() 
skatolo.Controller : Knob update() 
skatolo.Controller : Knob updateSize() 
skatolo.Controller : Label getCaptionLabel() 
skatolo.Controller : Label getValueLabel() 
skatolo.Controller : List getControllerPlugList() 
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



