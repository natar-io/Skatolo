/**
* skatolo Textfield
*
*
* find a list of public methods available for the Textfield Controller
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

String textValue = "";

void setup() {
  size(700,400);
  
  PFont font = createFont("arial",20);
  
  skatolo = new Skatolo(this);
  
  skatolo.addTextfield("input")
     .setPosition(20,100)
     .setSize(200,40)
     .setFont(font)
     .setFocus(true)
     .setColor(color(255,0,0))
     ;
                 
  skatolo.addTextfield("textValue")
     .setPosition(20,170)
     .setSize(200,40)
     .setFont(createFont("arial",20))
     .setAutoClear(false)
     ;
       
  skatolo.addBang("clear")
     .setPosition(240,170)
     .setSize(80,40)
     .getCaptionLabel().align(skatolo.CENTER, skatolo.CENTER)
     ;    
  
  skatolo.addTextfield("default")
     .setPosition(20,350)
     .setAutoClear(false)
     ;
     
  textFont(font);
}

void draw() {
  background(0);
  fill(255);
  text(skatolo.get(Textfield.class,"input").getText(), 360,130);
  text(textValue, 360,180);
}

public void clear() {
  skatolo.get(Textfield.class,"textValue").clear();
}

void controlEvent(ControlEvent theEvent) {
  if(theEvent.isAssignableFrom(Textfield.class)) {
    println("controlEvent: accessing a string from controller '"
            +theEvent.getName()+"': "
            +theEvent.getStringValue()
            );
  }
}


public void input(String theText) {
  // automatically receives results from controller input
  println("a textfield event for controller 'input' : "+theText);
}




/*
a list of all methods available for the Textfield Controller
use skatolo.printPublicMethodsFor(Textfield.class);
to print the following list into the console.

You can find further details about class Textfield in the javadoc.

Format:
ClassName : returnType methodName(parameter type)

skatolo.Textfield : String getText() 
skatolo.Textfield : Textfield clear() 
skatolo.Textfield : Textfield keepFocus(boolean) 
skatolo.Textfield : Textfield setAutoClear(boolean) 
skatolo.Textfield : Textfield setFocus(boolean) 
skatolo.Textfield : Textfield setFont(ControlFont) 
skatolo.Textfield : Textfield setFont(PFont) 
skatolo.Textfield : Textfield setFont(int) 
skatolo.Textfield : Textfield setText(String) 
skatolo.Textfield : Textfield setValue(String) 
skatolo.Textfield : Textfield setValue(float) 
skatolo.Textfield : boolean isAutoClear() 
skatolo.Textfield : int getIndex() 
skatolo.Textfield : void draw(PApplet) 
skatolo.Textfield : void keyEvent(KeyEvent) 
skatolo.Textfield : void setInputFilter(int) 
skatolo.Textfield : void setPasswordMode(boolean) 
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
skatolo.Controller : Textfield addCallback(CallbackListener) 
skatolo.Controller : Textfield addListener(ControlListener) 
skatolo.Controller : Textfield bringToFront() 
skatolo.Controller : Textfield bringToFront(ControllerInterface) 
skatolo.Controller : Textfield hide() 
skatolo.Controller : Textfield linebreak() 
skatolo.Controller : Textfield listen(boolean) 
skatolo.Controller : Textfield lock() 
skatolo.Controller : Textfield plugTo(Object) 
skatolo.Controller : Textfield plugTo(Object, String) 
skatolo.Controller : Textfield plugTo(Object[]) 
skatolo.Controller : Textfield plugTo(Object[], String) 
skatolo.Controller : Textfield registerProperty(String) 
skatolo.Controller : Textfield registerProperty(String, String) 
skatolo.Controller : Textfield registerTooltip(String) 
skatolo.Controller : Textfield removeBehavior() 
skatolo.Controller : Textfield removeCallback() 
skatolo.Controller : Textfield removeCallback(CallbackListener) 
skatolo.Controller : Textfield removeListener(ControlListener) 
skatolo.Controller : Textfield removeProperty(String) 
skatolo.Controller : Textfield removeProperty(String, String) 
skatolo.Controller : Textfield setArrayValue(float[]) 
skatolo.Controller : Textfield setArrayValue(int, float) 
skatolo.Controller : Textfield setBehavior(ControlBehavior) 
skatolo.Controller : Textfield setBroadcast(boolean) 
skatolo.Controller : Textfield setCaptionLabel(String) 
skatolo.Controller : Textfield setColor(CColor) 
skatolo.Controller : Textfield setColorActive(int) 
skatolo.Controller : Textfield setColorBackground(int) 
skatolo.Controller : Textfield setColorCaptionLabel(int) 
skatolo.Controller : Textfield setColorForeground(int) 
skatolo.Controller : Textfield setColorValueLabel(int) 
skatolo.Controller : Textfield setDecimalPrecision(int) 
skatolo.Controller : Textfield setDefaultValue(float) 
skatolo.Controller : Textfield setHeight(int) 
skatolo.Controller : Textfield setId(int) 
skatolo.Controller : Textfield setImages(PImage, PImage, PImage) 
skatolo.Controller : Textfield setImages(PImage, PImage, PImage, PImage) 
skatolo.Controller : Textfield setLabelVisible(boolean) 
skatolo.Controller : Textfield setLock(boolean) 
skatolo.Controller : Textfield setMax(float) 
skatolo.Controller : Textfield setMin(float) 
skatolo.Controller : Textfield setMouseOver(boolean) 
skatolo.Controller : Textfield setMoveable(boolean) 
skatolo.Controller : Textfield setPosition(PVector) 
skatolo.Controller : Textfield setPosition(float, float) 
skatolo.Controller : Textfield setSize(PImage) 
skatolo.Controller : Textfield setSize(int, int) 
skatolo.Controller : Textfield setStringValue(String) 
skatolo.Controller : Textfield setUpdate(boolean) 
skatolo.Controller : Textfield setValueLabel(String) 
skatolo.Controller : Textfield setView(ControllerView) 
skatolo.Controller : Textfield setVisible(boolean) 
skatolo.Controller : Textfield setWidth(int) 
skatolo.Controller : Textfield show() 
skatolo.Controller : Textfield unlock() 
skatolo.Controller : Textfield unplugFrom(Object) 
skatolo.Controller : Textfield unplugFrom(Object[]) 
skatolo.Controller : Textfield unregisterTooltip() 
skatolo.Controller : Textfield update() 
skatolo.Controller : Textfield updateSize() 
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
