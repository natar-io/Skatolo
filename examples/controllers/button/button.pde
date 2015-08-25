/**
 * skatolo Button
 * this example shows how to create buttons with skatolo.
 * 
 * find a list of public methods available for the Button Controller 
 * at the bottom of this sketch's source code
 *
 * by Andreas Schlegel, 2012
 * www.sojamo.de/libraries/skatolo
 *
 */
 
import fr.inria.skatolo.*;
import fr.inria.skatolo.events.*;
import fr.inria.skatolo.gui.group.*;

Skatolo skatolo;

int myColor = color(255);

int c1,c2;

float n,n1;


void setup() {
  size(400,600);
  noStroke();
  skatolo = new Skatolo(this);
  
  // create a new button with name 'buttonA'
  skatolo.addButton("colorA")
     .setValue(0)
     .setPosition(100,100)
     .setSize(200,19)
     ;
  
  // and add another 2 buttons
  skatolo.addButton("colorB")
     .setValue(100)
     .setPosition(100,120)
     .setSize(200,19)
     ;
     
  skatolo.addButton("colorC")
     .setPosition(100,140)
     .setSize(200,19)
     .setValue(0)
     ;

  PImage[] imgs = {loadImage("button_a.png"),loadImage("button_b.png"),loadImage("button_c.png")};
  skatolo.addButton("play")
     .setValue(128)
     .setPosition(140,300)
     .setImages(imgs)
     .updateSize()
     ;
     
  skatolo.addButton("playAgain")
     .setValue(128)
     .setPosition(210,300)
     .setImages(imgs)
     .updateSize()
     ;

}

void draw() {
  background(myColor);
  myColor = lerpColor(c1,c2,n);
  n += (1-n)* 0.1; 
}

public void controlEvent(ControlEvent theEvent) {
  println(theEvent.getController().getName());
  n = 0;
}

// function colorA will receive changes from 
// controller with name colorA
public void colorA(int theValue) {
  println("a button event from colorA: "+theValue);
  c1 = c2;
  c2 = color(0,160,100);
}

// function colorB will receive changes from 
// controller with name colorB
public void colorB(int theValue) {
  println("a button event from colorB: "+theValue);
  c1 = c2;
  c2 = color(150,0,0);
}

// function colorC will receive changes from 
// controller with name colorC
public void colorC(int theValue) {
  println("a button event from colorC: "+theValue);
  c1 = c2;
  c2 = color(255,255,0);
}

public void play(int theValue) {
  println("a button event from buttonB: "+theValue);
  c1 = c2;
  c2 = color(0,0,0);
}


/*
a list of all methods available for the Button Controller
use skatolo.printPublicMethodsFor(Button.class);
to print the following list into the console.

You can find further details about class Button in the javadoc.

Format:
ClassName : returnType methodName(parameter type)


skatolo.Button : Button activateBy(int) 
skatolo.Button : Button setOff() 
skatolo.Button : Button setOn() 
skatolo.Button : Button setSwitch(boolean) 
skatolo.Button : Button setValue(float) 
skatolo.Button : Button update() 
skatolo.Button : String getInfo() 
skatolo.Button : String toString() 
skatolo.Button : boolean getBooleanValue() 
skatolo.Button : boolean isOn() 
skatolo.Button : boolean isPressed() 
skatolo.Controller : Button addCallback(CallbackListener) 
skatolo.Controller : Button addListener(ControlListener) 
skatolo.Controller : Button bringToFront() 
skatolo.Controller : Button bringToFront(ControllerInterface) 
skatolo.Controller : Button hide() 
skatolo.Controller : Button linebreak() 
skatolo.Controller : Button listen(boolean) 
skatolo.Controller : Button lock() 
skatolo.Controller : Button plugTo(Object) 
skatolo.Controller : Button plugTo(Object, String) 
skatolo.Controller : Button plugTo(Object[]) 
skatolo.Controller : Button plugTo(Object[], String) 
skatolo.Controller : Button registerProperty(String) 
skatolo.Controller : Button registerProperty(String, String) 
skatolo.Controller : Button registerTooltip(String) 
skatolo.Controller : Button removeBehavior() 
skatolo.Controller : Button removeCallback() 
skatolo.Controller : Button removeCallback(CallbackListener) 
skatolo.Controller : Button removeListener(ControlListener) 
skatolo.Controller : Button removeProperty(String) 
skatolo.Controller : Button removeProperty(String, String) 
skatolo.Controller : Button setArrayValue(float[]) 
skatolo.Controller : Button setArrayValue(int, float) 
skatolo.Controller : Button setBehavior(ControlBehavior) 
skatolo.Controller : Button setBroadcast(boolean) 
skatolo.Controller : Button setCaptionLabel(String) 
skatolo.Controller : Button setColor(CColor) 
skatolo.Controller : Button setColorActive(int) 
skatolo.Controller : Button setColorBackground(int) 
skatolo.Controller : Button setColorCaptionLabel(int) 
skatolo.Controller : Button setColorForeground(int) 
skatolo.Controller : Button setColorValueLabel(int) 
skatolo.Controller : Button setDecimalPrecision(int) 
skatolo.Controller : Button setDefaultValue(float) 
skatolo.Controller : Button setHeight(int) 
skatolo.Controller : Button setId(int) 
skatolo.Controller : Button setImages(PImage, PImage, PImage) 
skatolo.Controller : Button setImages(PImage, PImage, PImage, PImage) 
skatolo.Controller : Button setLabelVisible(boolean) 
skatolo.Controller : Button setLock(boolean) 
skatolo.Controller : Button setMax(float) 
skatolo.Controller : Button setMin(float) 
skatolo.Controller : Button setMouseOver(boolean) 
skatolo.Controller : Button setMoveable(boolean) 
skatolo.Controller : Button setPosition(PVector) 
skatolo.Controller : Button setPosition(float, float) 
skatolo.Controller : Button setSize(PImage) 
skatolo.Controller : Button setSize(int, int) 
skatolo.Controller : Button setStringValue(String) 
skatolo.Controller : Button setUpdate(boolean) 
skatolo.Controller : Button setValueLabel(String) 
skatolo.Controller : Button setView(ControllerView) 
skatolo.Controller : Button setVisible(boolean) 
skatolo.Controller : Button setWidth(int) 
skatolo.Controller : Button show() 
skatolo.Controller : Button unlock() 
skatolo.Controller : Button unplugFrom(Object) 
skatolo.Controller : Button unplugFrom(Object[]) 
skatolo.Controller : Button unregisterTooltip() 
skatolo.Controller : Button update() 
skatolo.Controller : Button updateSize() 
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



