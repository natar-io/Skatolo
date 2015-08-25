/**
* skatolo Range
*
* find a list of public methods available for the Range Controller
* at the bottom of this sketch.
*
* by Andreas Schlegel, 2012
* www.sojamo.de/libraries/skatolo
*
*/

import fr.inria.skatolo.*;
import fr.inria.skatolo.events.*;
import fr.inria.skatolo.gui.controllers.*;

Skatolo skatolo;

int myColorBackground = color(0,0,0);

int colorMin = 100;

int colorMax = 100;

Range range;

void setup() {
  size(700,400);
  skatolo = new Skatolo(this);
  range = skatolo.addRange("rangeController")
             // disable broadcasting since setRange and setRangeValues will trigger an event
             .setBroadcast(false) 
             .setPosition(50,50)
             .setSize(400,40)
             .setHandleSize(20)
             .setRange(0,255)
             .setRangeValues(50,100)
             // after the initialization we turn broadcast back on again
             .setBroadcast(true)
             .setColorForeground(color(255,40))
             .setColorBackground(color(255,40))  
             ;
             
  noStroke();             
}

void draw() {
  background(colorMax);
  fill(colorMin);
  rect(0,0,width,height/2);
}

void controlEvent(ControlEvent theControlEvent) {
  if(theControlEvent.isFrom("rangeController")) {
    // min and max values are stored in an array.
    // access this array with controller().arrayValue().
    // min is at index 0, max is at index 1.
    colorMin = int(theControlEvent.getController().getArrayValue(0));
    colorMax = int(theControlEvent.getController().getArrayValue(1));
    println("range update, done.");
  }
  
}


void keyPressed() {
  switch(key) {
    case('1'):range.setLowValue(0);break;
    case('2'):range.setLowValue(100);break;
    case('3'):range.setHighValue(120);break;
    case('4'):range.setHighValue(200);break;
    case('5'):range.setRangeValues(40,60);break;
  }
}


/*
a list of all methods available for the Range Controller
use skatolo.printPublicMethodsFor(Range.class);
to print the following list into the console.

You can find further details about class Range in the javadoc.

Format:
ClassName : returnType methodName(parameter type)


skatolo.Range : Range setArrayValue(float[]) 
skatolo.Range : Range setColorCaptionLabel(int) 
skatolo.Range : Range setColorTickMark(int) 
skatolo.Range : Range setColorValueLabel(int) 
skatolo.Range : Range setHandleSize(int) 
skatolo.Range : Range setHeight(int) 
skatolo.Range : Range setHighValue(float) 
skatolo.Range : Range setHighValueLabel(String) 
skatolo.Range : Range setLowValue(float) 
skatolo.Range : Range setLowValueLabel(String) 
skatolo.Range : Range setMax(float) 
skatolo.Range : Range setMin(float) 
skatolo.Range : Range setNumberOfTickMarks(int) 
skatolo.Range : Range setRange(float, float) 
skatolo.Range : Range setRangeValues(float, float) 
skatolo.Range : Range setWidth(int) 
skatolo.Range : Range showTickMarks(boolean) 
skatolo.Range : Range snapToTickMarks(boolean)
skatolo.Range : ArrayList getTickMarks() 
skatolo.Range : float getHighValue() 
skatolo.Range : float getLowValue() 
skatolo.Range : float[] getArrayValue()
skatolo.Controller : CColor getColor() 
skatolo.Controller : ControlBehavior getBehavior() 
skatolo.Controller : ControlWindow getControlWindow() 
skatolo.Controller : ControlWindow getWindow() 
skatolo.Controller : ControllerProperty getProperty(String) 
skatolo.Controller : ControllerProperty getProperty(String, String) 
skatolo.Controller : Label getCaptionLabel() 
skatolo.Controller : Label getValueLabel() 
skatolo.Controller : List getControllerPlugList() 
skatolo.Controller : PImage setImage(PImage) 
skatolo.Controller : PImage setImage(PImage, int) 
skatolo.Controller : PVector getAbsolutePosition() 
skatolo.Controller : PVector getPosition() 
skatolo.Controller : Range addCallback(CallbackListener) 
skatolo.Controller : Range addListener(ControlListener) 
skatolo.Controller : Range bringToFront() 
skatolo.Controller : Range bringToFront(ControllerInterface) 
skatolo.Controller : Range hide() 
skatolo.Controller : Range linebreak() 
skatolo.Controller : Range listen(boolean) 
skatolo.Controller : Range lock() 
skatolo.Controller : Range plugTo(Object) 
skatolo.Controller : Range plugTo(Object, String) 
skatolo.Controller : Range plugTo(Object[]) 
skatolo.Controller : Range plugTo(Object[], String) 
skatolo.Controller : Range registerProperty(String) 
skatolo.Controller : Range registerProperty(String, String) 
skatolo.Controller : Range registerTooltip(String) 
skatolo.Controller : Range removeBehavior() 
skatolo.Controller : Range removeCallback() 
skatolo.Controller : Range removeCallback(CallbackListener) 
skatolo.Controller : Range removeListener(ControlListener) 
skatolo.Controller : Range removeProperty(String) 
skatolo.Controller : Range removeProperty(String, String) 
skatolo.Controller : Range setArrayValue(float[]) 
skatolo.Controller : Range setArrayValue(int, float) 
skatolo.Controller : Range setBehavior(ControlBehavior) 
skatolo.Controller : Range setBroadcast(boolean) 
skatolo.Controller : Range setCaptionLabel(String) 
skatolo.Controller : Range setColor(CColor) 
skatolo.Controller : Range setColorActive(int) 
skatolo.Controller : Range setColorBackground(int) 
skatolo.Controller : Range setColorCaptionLabel(int) 
skatolo.Controller : Range setColorForeground(int) 
skatolo.Controller : Range setColorValueLabel(int) 
skatolo.Controller : Range setDecimalPrecision(int) 
skatolo.Controller : Range setDefaultValue(float) 
skatolo.Controller : Range setHeight(int) 
skatolo.Controller : Range setId(int) 
skatolo.Controller : Range setImages(PImage, PImage, PImage) 
skatolo.Controller : Range setImages(PImage, PImage, PImage, PImage) 
skatolo.Controller : Range setLabelVisible(boolean) 
skatolo.Controller : Range setLock(boolean) 
skatolo.Controller : Range setMax(float) 
skatolo.Controller : Range setMin(float) 
skatolo.Controller : Range setMouseOver(boolean) 
skatolo.Controller : Range setMoveable(boolean) 
skatolo.Controller : Range setPosition(PVector) 
skatolo.Controller : Range setPosition(float, float) 
skatolo.Controller : Range setSize(PImage) 
skatolo.Controller : Range setSize(int, int) 
skatolo.Controller : Range setStringValue(String) 
skatolo.Controller : Range setUpdate(boolean) 
skatolo.Controller : Range setValueLabel(String) 
skatolo.Controller : Range setView(ControllerView) 
skatolo.Controller : Range setVisible(boolean) 
skatolo.Controller : Range setWidth(int) 
skatolo.Controller : Range show() 
skatolo.Controller : Range unlock() 
skatolo.Controller : Range unplugFrom(Object) 
skatolo.Controller : Range unplugFrom(Object[]) 
skatolo.Controller : Range unregisterTooltip() 
skatolo.Controller : Range update() 
skatolo.Controller : Range updateSize() 
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



