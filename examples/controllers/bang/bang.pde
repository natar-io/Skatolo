/**
 * skatolo Bang
 * A bang triggers an event that can be received by a function named after the bang.
 * By default a bang is triggered when pressed, this can be changed to 'release' 
 * using theBang.setTriggerEvent(Bang.RELEASE).
 *
 * find a list of public methods available for the Bang Controller 
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

int myColorBackground = color(0, 0, 0);

color[] col = new color[] {
  color(100), color(150), color(200), color(250)
};


void setup() {
  size(400, 600);
  noStroke();
  skatolo = new Skatolo(this);
  for (int i=0;i<col.length;i++) {
    skatolo.addBang("bang"+i)
       .setPosition(40+i*80, 200)
       .setSize(40, 40)
       .setId(i)
       ;
  }
  
  // change the trigger event, by default it is PRESSED.
  skatolo.addBang("bang")
     .setPosition(40, 300)
     .setSize(280, 40)
     .setTriggerEvent(Bang.RELEASE)
     .setLabel("changeBackground")
     ;
           
}

void draw() {
  background(myColorBackground);
  for (int i=0;i<col.length;i++) {
    fill(col[i]);
    rect(40+i*80, 50, 40, 80);
  }
}


public void bang() {
  int theColor = (int)random(255);
  myColorBackground = color(theColor);
  println("### bang(). a bang event. setting background to "+theColor);
}

public void controlEvent(ControlEvent theEvent) {
  for (int i=0;i<col.length;i++) {
    if (theEvent.getController().getName().equals("bang"+i)) {
      col[i] = color(random(255));
    }
  }
  
  println(
  "## controlEvent / id:"+theEvent.controller().id()+
    " / name:"+theEvent.controller().name()+
    " / label:"+theEvent.controller().label()+
    " / value:"+theEvent.controller().value()
    );
}


/*
a list of all methods available for the Bang Controller
use skatolo.printPublicMethodsFor(Bang.class);
to print the following list into the console.

You can find further details about class Bang in the javadoc.

Format:
ClassName : returnType methodName(parameter type)


skatolo.Bang : Bang setTriggerEvent(int) 
skatolo.Bang : Bang setValue(float) 
skatolo.Bang : Bang update() 
skatolo.Bang : String getInfo() 
skatolo.Bang : String toString() 
skatolo.Bang : int getTriggerEvent() 
skatolo.Controller : Bang addCallback(CallbackListener) 
skatolo.Controller : Bang addListener(ControlListener) 
skatolo.Controller : Bang bringToFront() 
skatolo.Controller : Bang bringToFront(ControllerInterface) 
skatolo.Controller : Bang hide() 
skatolo.Controller : Bang linebreak() 
skatolo.Controller : Bang listen(boolean) 
skatolo.Controller : Bang lock() 
skatolo.Controller : Bang plugTo(Object) 
skatolo.Controller : Bang plugTo(Object, String) 
skatolo.Controller : Bang plugTo(Object[]) 
skatolo.Controller : Bang plugTo(Object[], String) 
skatolo.Controller : Bang registerProperty(String) 
skatolo.Controller : Bang registerProperty(String, String) 
skatolo.Controller : Bang registerTooltip(String) 
skatolo.Controller : Bang removeBehavior() 
skatolo.Controller : Bang removeCallback() 
skatolo.Controller : Bang removeCallback(CallbackListener) 
skatolo.Controller : Bang removeListener(ControlListener) 
skatolo.Controller : Bang removeProperty(String) 
skatolo.Controller : Bang removeProperty(String, String) 
skatolo.Controller : Bang setArrayValue(float[]) 
skatolo.Controller : Bang setArrayValue(int, float) 
skatolo.Controller : Bang setBehavior(ControlBehavior) 
skatolo.Controller : Bang setBroadcast(boolean) 
skatolo.Controller : Bang setCaptionLabel(String) 
skatolo.Controller : Bang setColor(CColor) 
skatolo.Controller : Bang setColorActive(int) 
skatolo.Controller : Bang setColorBackground(int) 
skatolo.Controller : Bang setColorCaptionLabel(int) 
skatolo.Controller : Bang setColorForeground(int) 
skatolo.Controller : Bang setColorValueLabel(int) 
skatolo.Controller : Bang setDecimalPrecision(int) 
skatolo.Controller : Bang setDefaultValue(float) 
skatolo.Controller : Bang setHeight(int) 
skatolo.Controller : Bang setId(int) 
skatolo.Controller : Bang setImages(PImage, PImage, PImage) 
skatolo.Controller : Bang setImages(PImage, PImage, PImage, PImage) 
skatolo.Controller : Bang setLabelVisible(boolean) 
skatolo.Controller : Bang setLock(boolean) 
skatolo.Controller : Bang setMax(float) 
skatolo.Controller : Bang setMin(float) 
skatolo.Controller : Bang setMouseOver(boolean) 
skatolo.Controller : Bang setMoveable(boolean) 
skatolo.Controller : Bang setPosition(PVector) 
skatolo.Controller : Bang setPosition(float, float) 
skatolo.Controller : Bang setSize(PImage) 
skatolo.Controller : Bang setSize(int, int) 
skatolo.Controller : Bang setStringValue(String) 
skatolo.Controller : Bang setUpdate(boolean) 
skatolo.Controller : Bang setValueLabel(String) 
skatolo.Controller : Bang setView(ControllerView) 
skatolo.Controller : Bang setVisible(boolean) 
skatolo.Controller : Bang setWidth(int) 
skatolo.Controller : Bang show() 
skatolo.Controller : Bang unlock() 
skatolo.Controller : Bang unplugFrom(Object) 
skatolo.Controller : Bang unplugFrom(Object[]) 
skatolo.Controller : Bang unregisterTooltip() 
skatolo.Controller : Bang update() 
skatolo.Controller : Bang updateSize() 
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



