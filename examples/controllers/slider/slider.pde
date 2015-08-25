/**
 * skatolo Slider. Horizontal and vertical sliders, 
 * with and without tick marks and snap-to-tick behavior.
 * by andreas schlegel, 2010
 */

/**
* skatolo Slider
*
* Horizontal and vertical sliders, 
* With and without tick marks and snap-to-tick behavior.
*
* find a list of public methods available for the Slider Controller
* at the bottom of this sketch.
*
* by Andreas Schlegel, 2012
* www.sojamo.de/libraries/skatolo
*
*/

import fr.inria.skatolo.*;
import fr.inria.skatolo.gui.controllers.*;


Skatolo skatolo;
int myColor = color(0,0,0);

int sliderValue = 100;
int sliderTicks1 = 100;
int sliderTicks2 = 30;
Slider abc;

void setup() {
  size(700,400);
  noStroke();
  skatolo = new Skatolo(this);
  
  // add a horizontal sliders, the value of this slider will be linked
  // to variable 'sliderValue' 
  skatolo.addSlider("sliderValue")
     .setPosition(100,50)
     .setRange(0,255)
     ;
  
  // create another slider with tick marks, now without
  // default value, the initial value will be set according to
  // the value of variable sliderTicks2 then.
  skatolo.addSlider("sliderTicks1")
     .setPosition(100,140)
     .setSize(20,100)
     .setRange(0,255)
     .setNumberOfTickMarks(5)
     ;
     
     
  // add a vertical slider
  skatolo.addSlider("slider")
     .setPosition(100,305)
     .setSize(200,20)
     .setRange(0,200)
     .setValue(128)
     ;
  
  // reposition the Label for controller 'slider'
  skatolo.getController("slider").getValueLabel().align(skatolo.LEFT, skatolo.BOTTOM_OUTSIDE).setPaddingX(0);
  skatolo.getController("slider").getCaptionLabel().align(skatolo.RIGHT, skatolo.BOTTOM_OUTSIDE).setPaddingX(0);
  

  skatolo.addSlider("sliderTicks2")
     .setPosition(100,370)
     .setWidth(400)
     .setRange(255,0) // values can range from big to small as well
     .setValue(128)
     .setNumberOfTickMarks(7)
     .setSliderMode(Slider.FLEXIBLE)
     ;
  // use Slider.FIX or Slider.FLEXIBLE to change the slider handle
  // by default it is Slider.FIX
  

}

void draw() {
  background(sliderTicks1);

  fill(sliderValue);
  rect(0,0,width,100);
  
  fill(myColor);
  rect(0,280,width,70);
  
  fill(sliderTicks2);
  rect(0,350,width,50);

  println("Mouse : "+   skatolo.getMouseX() + " " + skatolo.getMouseY() );
  println("Mouse2 : "+   mouseX + " " + mouseY );
}

void slider(float theColor) {
  myColor = color(theColor);
  println("a slider event. setting background to "+theColor);
}












/**
* skatolo Slider
*
*
* find a list of public methods available for the Slider Controller
* at the bottom of this sketch.
*
* by Andreas Schlegel, 2012
* www.sojamo.de/libraries/skatolo
*
*/

/*
a list of all methods available for the Slider Controller
use skatolo.printPublicMethodsFor(Slider.class);
to print the following list into the console.

You can find further details about class Slider in the javadoc.

Format:
ClassName : returnType methodName(parameter type)

skatolo.Slider : ArrayList getTickMarks() 
skatolo.Slider : Slider setColorTickMark(int) 
skatolo.Slider : Slider setHandleSize(int) 
skatolo.Slider : Slider setHeight(int) 
skatolo.Slider : Slider setMax(float) 
skatolo.Slider : Slider setMin(float) 
skatolo.Slider : Slider setNumberOfTickMarks(int) 
skatolo.Slider : Slider setRange(float, float) 
skatolo.Slider : Slider setScrollSensitivity(float) 
skatolo.Slider : Slider setSize(int, int) 
skatolo.Slider : Slider setSliderMode(int) 
skatolo.Slider : Slider setTriggerEvent(int) 
skatolo.Slider : Slider setValue(float) 
skatolo.Slider : Slider setWidth(int) 
skatolo.Slider : Slider showTickMarks(boolean) 
skatolo.Slider : Slider shuffle() 
skatolo.Slider : Slider snapToTickMarks(boolean) 
skatolo.Slider : Slider update() 
skatolo.Slider : TickMark getTickMark(int) 
skatolo.Slider : float getValue() 
skatolo.Slider : float getValuePosition() 
skatolo.Slider : int getDirection() 
skatolo.Slider : int getHandleSize() 
skatolo.Slider : int getNumberOfTickMarks() 
skatolo.Slider : int getSliderMode() 
skatolo.Slider : int getTriggerEvent() 
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
skatolo.Controller : Slider addCallback(CallbackListener) 
skatolo.Controller : Slider addListener(ControlListener) 
skatolo.Controller : Slider bringToFront() 
skatolo.Controller : Slider bringToFront(ControllerInterface) 
skatolo.Controller : Slider hide() 
skatolo.Controller : Slider linebreak() 
skatolo.Controller : Slider listen(boolean) 
skatolo.Controller : Slider lock() 
skatolo.Controller : Slider plugTo(Object) 
skatolo.Controller : Slider plugTo(Object, String) 
skatolo.Controller : Slider plugTo(Object[]) 
skatolo.Controller : Slider plugTo(Object[], String) 
skatolo.Controller : Slider registerProperty(String) 
skatolo.Controller : Slider registerProperty(String, String) 
skatolo.Controller : Slider registerTooltip(String) 
skatolo.Controller : Slider removeBehavior() 
skatolo.Controller : Slider removeCallback() 
skatolo.Controller : Slider removeCallback(CallbackListener) 
skatolo.Controller : Slider removeListener(ControlListener) 
skatolo.Controller : Slider removeProperty(String) 
skatolo.Controller : Slider removeProperty(String, String) 
skatolo.Controller : Slider setArrayValue(float[]) 
skatolo.Controller : Slider setArrayValue(int, float) 
skatolo.Controller : Slider setBehavior(ControlBehavior) 
skatolo.Controller : Slider setBroadcast(boolean) 
skatolo.Controller : Slider setCaptionLabel(String) 
skatolo.Controller : Slider setColor(CColor) 
skatolo.Controller : Slider setColorActive(int) 
skatolo.Controller : Slider setColorBackground(int) 
skatolo.Controller : Slider setColorCaptionLabel(int) 
skatolo.Controller : Slider setColorForeground(int) 
skatolo.Controller : Slider setColorValueLabel(int) 
skatolo.Controller : Slider setDecimalPrecision(int) 
skatolo.Controller : Slider setDefaultValue(float) 
skatolo.Controller : Slider setHeight(int) 
skatolo.Controller : Slider setId(int) 
skatolo.Controller : Slider setImages(PImage, PImage, PImage) 
skatolo.Controller : Slider setImages(PImage, PImage, PImage, PImage) 
skatolo.Controller : Slider setLabelVisible(boolean) 
skatolo.Controller : Slider setLock(boolean) 
skatolo.Controller : Slider setMax(float) 
skatolo.Controller : Slider setMin(float) 
skatolo.Controller : Slider setMouseOver(boolean) 
skatolo.Controller : Slider setMoveable(boolean) 
skatolo.Controller : Slider setPosition(PVector) 
skatolo.Controller : Slider setPosition(float, float) 
skatolo.Controller : Slider setSize(PImage) 
skatolo.Controller : Slider setSize(int, int) 
skatolo.Controller : Slider setStringValue(String) 
skatolo.Controller : Slider setUpdate(boolean) 
skatolo.Controller : Slider setValueLabel(String) 
skatolo.Controller : Slider setView(ControllerView) 
skatolo.Controller : Slider setVisible(boolean) 
skatolo.Controller : Slider setWidth(int) 
skatolo.Controller : Slider show() 
skatolo.Controller : Slider unlock() 
skatolo.Controller : Slider unplugFrom(Object) 
skatolo.Controller : Slider unplugFrom(Object[]) 
skatolo.Controller : Slider unregisterTooltip() 
skatolo.Controller : Slider update() 
skatolo.Controller : Slider updateSize() 
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



