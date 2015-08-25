/**
* skatolo Slider2D
*
*
* find a list of public methods available for the Slider2D Controller
* at the bottom of this sketch.
*
* by Andreas Schlegel, 2012
* www.sojamo.de/libraries/skatolo
*
*/

import fr.inria.skatolo.*;
import fr.inria.skatolo.gui.controllers.*;

Skatolo skatolo;

Slider2D s;

void setup() {
  size(700,400);
  skatolo = new Skatolo(this);
  s = skatolo.addSlider2D("wave")
         .setPosition(30,40)
         .setSize(100,100)
         .setArrayValue(new float[] {50, 50})
         //.disableCrosshair()
         ;
         
  smooth();
}

float cnt;
void draw() {
  background(0);
  pushMatrix();
  translate(160,140);
  noStroke();
  fill(50);
  rect(0, -100, 400,200);
  strokeWeight(1);
  line(0,0,200, 0);
  stroke(255);
  
  for(int i=1;i<400;i++) {
    float y0 = cos(map(i-1,0,s.arrayValue()[0],-PI,PI)) * s.arrayValue()[1]; 
    float y1 = cos(map(i,0,s.arrayValue()[0],-PI,PI)) * s.arrayValue()[1];
    line((i-1),y0,i,y1);
  }
  
  popMatrix();
}















/*
a list of all methods available for the Slider2D Controller
use skatolo.printPublicMethodsFor(Slider2D.class);
to print the following list into the console.

You can find further details about class Slider2D in the javadoc.

Format:
ClassName : returnType methodName(parameter type)

skatolo.Slider2D : Slider2D enableCrosshair()
skatolo.Slider2D : Slider2D disableCrosshair()
skatolo.Slider2D : Slider2D setArrayValue(float[]) 
skatolo.Slider2D : Slider2D setMaxX(float) 
skatolo.Slider2D : Slider2D setMaxY(float) 
skatolo.Slider2D : Slider2D setMinX(float) 
skatolo.Slider2D : Slider2D setMinY(float) 
skatolo.Slider2D : Slider2D setValue(float) 
skatolo.Slider2D : Slider2D shuffle() 
skatolo.Slider2D : float getCursorHeight() 
skatolo.Slider2D : float getCursorWidth() 
skatolo.Slider2D : float getCursorX() 
skatolo.Slider2D : float getCursorY() 
skatolo.Slider2D : float getMaxX() 
skatolo.Slider2D : float getMaxY() 
skatolo.Slider2D : float getMinX() 
skatolo.Slider2D : float getMinY() 
skatolo.Slider2D : float[] getArrayValue() 
skatolo.Slider2D : void setValueLabelSeparator(String) 
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
skatolo.Controller : Slider2D addCallback(CallbackListener) 
skatolo.Controller : Slider2D addListener(ControlListener) 
skatolo.Controller : Slider2D bringToFront() 
skatolo.Controller : Slider2D bringToFront(ControllerInterface) 
skatolo.Controller : Slider2D hide() 
skatolo.Controller : Slider2D linebreak() 
skatolo.Controller : Slider2D listen(boolean) 
skatolo.Controller : Slider2D lock() 
skatolo.Controller : Slider2D plugTo(Object) 
skatolo.Controller : Slider2D plugTo(Object, String) 
skatolo.Controller : Slider2D plugTo(Object[]) 
skatolo.Controller : Slider2D plugTo(Object[], String) 
skatolo.Controller : Slider2D registerProperty(String) 
skatolo.Controller : Slider2D registerProperty(String, String) 
skatolo.Controller : Slider2D registerTooltip(String) 
skatolo.Controller : Slider2D removeBehavior() 
skatolo.Controller : Slider2D removeCallback() 
skatolo.Controller : Slider2D removeCallback(CallbackListener) 
skatolo.Controller : Slider2D removeListener(ControlListener) 
skatolo.Controller : Slider2D removeProperty(String) 
skatolo.Controller : Slider2D removeProperty(String, String) 
skatolo.Controller : Slider2D setArrayValue(float[]) 
skatolo.Controller : Slider2D setArrayValue(int, float) 
skatolo.Controller : Slider2D setBehavior(ControlBehavior) 
skatolo.Controller : Slider2D setBroadcast(boolean) 
skatolo.Controller : Slider2D setCaptionLabel(String) 
skatolo.Controller : Slider2D setColor(CColor) 
skatolo.Controller : Slider2D setColorActive(int) 
skatolo.Controller : Slider2D setColorBackground(int) 
skatolo.Controller : Slider2D setColorCaptionLabel(int) 
skatolo.Controller : Slider2D setColorForeground(int) 
skatolo.Controller : Slider2D setColorValueLabel(int) 
skatolo.Controller : Slider2D setDecimalPrecision(int) 
skatolo.Controller : Slider2D setDefaultValue(float) 
skatolo.Controller : Slider2D setHeight(int) 
skatolo.Controller : Slider2D setId(int) 
skatolo.Controller : Slider2D setImages(PImage, PImage, PImage) 
skatolo.Controller : Slider2D setImages(PImage, PImage, PImage, PImage) 
skatolo.Controller : Slider2D setLabelVisible(boolean) 
skatolo.Controller : Slider2D setLock(boolean) 
skatolo.Controller : Slider2D setMax(float) 
skatolo.Controller : Slider2D setMin(float) 
skatolo.Controller : Slider2D setMouseOver(boolean) 
skatolo.Controller : Slider2D setMoveable(boolean) 
skatolo.Controller : Slider2D setPosition(PVector) 
skatolo.Controller : Slider2D setPosition(float, float) 
skatolo.Controller : Slider2D setSize(PImage) 
skatolo.Controller : Slider2D setSize(int, int) 
skatolo.Controller : Slider2D setStringValue(String) 
skatolo.Controller : Slider2D setUpdate(boolean) 
skatolo.Controller : Slider2D setValueLabel(String) 
skatolo.Controller : Slider2D setView(ControllerView) 
skatolo.Controller : Slider2D setVisible(boolean) 
skatolo.Controller : Slider2D setWidth(int) 
skatolo.Controller : Slider2D show() 
skatolo.Controller : Slider2D unlock() 
skatolo.Controller : Slider2D unplugFrom(Object) 
skatolo.Controller : Slider2D unplugFrom(Object[]) 
skatolo.Controller : Slider2D unregisterTooltip() 
skatolo.Controller : Slider2D update() 
skatolo.Controller : Slider2D updateSize() 
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



