/**
* skatolo RadioButton
*
*
* find a list of public methods available for the RadioButton Controller
* at the bottom of this sketch.
*
* by Andreas Schlegel, 2012
* www.sojamo.de/libraries/skatolo
*
*/

import fr.inria.skatolo.*;
import fr.inria.skatolo.events.*;
import fr.inria.skatolo.gui.controllers.*;
import fr.inria.skatolo.gui.group.*;

Skatolo skatolo;

int myColorBackground = color(0,0,0);

RadioButton r;

void setup() {
  size(700,400);
  
  skatolo = new Skatolo(this);
  r = skatolo.addRadioButton("radioButton")
         .setPosition(20,160)
         .setSize(40,20)
         .setColorForeground(color(120))
         .setColorActive(color(255))
         .setColorLabel(color(255))
         .setItemsPerRow(5)
         .setSpacingColumn(50)
         .addItem("50",1)
         .addItem("100",2)
         .addItem("150",3)
         .addItem("200",4)
         .addItem("250",5)
         ;
     
     for(Toggle t:r.getItems()) {
       t.captionLabel().setColorBackground(color(255,80));
       t.captionLabel().getStyle().moveMargin(-7,0,0,-3);
       t.captionLabel().getStyle().movePadding(7,0,0,3);
       t.captionLabel().getStyle().backgroundWidth = 45;
       t.captionLabel().getStyle().backgroundHeight = 13;
     }
}


void draw() {
  background(myColorBackground);
}


void keyPressed() {
  switch(key) {
    case('0'): r.deactivateAll(); break;
    case('1'): r.activate(0); break;
    case('2'): r.activate(1); break;
    case('3'): r.activate(2); break;
    case('4'): r.activate(3); break;
    case('5'): r.activate(4); break;
  }
  
}

void controlEvent(ControlEvent theEvent) {
  if(theEvent.isFrom(r)) {
    print("got an event from "+theEvent.getName()+"\t");
    for(int i=0;i<theEvent.getGroup().getArrayValue().length;i++) {
      print(int(theEvent.getGroup().getArrayValue()[i]));
    }
    println("\t "+theEvent.getValue());
    myColorBackground = color(int(theEvent.getGroup().getValue()*50),0,0);
  }
}

void radioButton(int a) {
  println("a radio Button event: "+a);
}

/*
a list of all methods available for the RadioButton Controller
use skatolo.printPublicMethodsFor(RadioButton.class);
to print the following list into the console.

You can find further details about class RadioButton in the javadoc.

Format:
ClassName : returnType methodName(parameter type)

skatolo.RadioButton : List getItems() 
skatolo.RadioButton : RadioButton activate(String) 
skatolo.RadioButton : RadioButton activate(int) 
skatolo.RadioButton : RadioButton addItem(String, float) 
skatolo.RadioButton : RadioButton addItem(Toggle, float) 
skatolo.RadioButton : RadioButton deactivate(String) 
skatolo.RadioButton : RadioButton deactivate(int) 
skatolo.RadioButton : RadioButton deactivateAll() 
skatolo.RadioButton : RadioButton hideLabels() 
skatolo.RadioButton : RadioButton removeItem(String) 
skatolo.RadioButton : RadioButton setArrayValue(float[]) 
skatolo.RadioButton : RadioButton setColorLabels(int) 
skatolo.RadioButton : RadioButton setImage(PImage) 
skatolo.RadioButton : RadioButton setImage(PImage, int) 
skatolo.RadioButton : RadioButton setImages(PImage, PImage, PImage) 
skatolo.RadioButton : RadioButton setItemHeight(int) 
skatolo.RadioButton : RadioButton setItemWidth(int) 
skatolo.RadioButton : RadioButton setItemsPerRow(int) 
skatolo.RadioButton : RadioButton setNoneSelectedAllowed(boolean) 
skatolo.RadioButton : RadioButton setSize(PImage) 
skatolo.RadioButton : RadioButton setSize(int, int) 
skatolo.RadioButton : RadioButton setSpacingColumn(int) 
skatolo.RadioButton : RadioButton setSpacingRow(int) 
skatolo.RadioButton : RadioButton showLabels() 
skatolo.RadioButton : RadioButton toUpperCase(boolean) 
skatolo.RadioButton : RadioButton toggle(int) 
skatolo.RadioButton : String getInfo() 
skatolo.RadioButton : Toggle getItem(int) 
skatolo.RadioButton : boolean getState(String) 
skatolo.RadioButton : boolean getState(int) 
skatolo.RadioButton : void updateLayout() 
skatolo.ControlGroup : RadioButton activateEvent(boolean) 
skatolo.ControlGroup : RadioButton addListener(ControlListener) 
skatolo.ControlGroup : RadioButton hideBar() 
skatolo.ControlGroup : RadioButton removeListener(ControlListener) 
skatolo.ControlGroup : RadioButton setBackgroundColor(int) 
skatolo.ControlGroup : RadioButton setBackgroundHeight(int) 
skatolo.ControlGroup : RadioButton setBarHeight(int) 
skatolo.ControlGroup : RadioButton showBar() 
skatolo.ControlGroup : RadioButton updateInternalEvents(PApplet) 
skatolo.ControlGroup : String getInfo() 
skatolo.ControlGroup : String toString() 
skatolo.ControlGroup : boolean isBarVisible() 
skatolo.ControlGroup : int getBackgroundHeight() 
skatolo.ControlGroup : int getBarHeight() 
skatolo.ControlGroup : int listenerSize() 
skatolo.ControllerGroup : CColor getColor() 
skatolo.ControllerGroup : ControlWindow getWindow() 
skatolo.ControllerGroup : ControlWindowCanvas addCanvas(ControlWindowCanvas) 
skatolo.ControllerGroup : Controller getController(String) 
skatolo.ControllerGroup : ControllerProperty getProperty(String) 
skatolo.ControllerGroup : ControllerProperty getProperty(String, String) 
skatolo.ControllerGroup : Label getCaptionLabel() 
skatolo.ControllerGroup : Label getValueLabel() 
skatolo.ControllerGroup : PVector getPosition() 
skatolo.ControllerGroup : RadioButton add(ControllerInterface) 
skatolo.ControllerGroup : RadioButton bringToFront() 
skatolo.ControllerGroup : RadioButton bringToFront(ControllerInterface) 
skatolo.ControllerGroup : RadioButton close() 
skatolo.ControllerGroup : RadioButton disableCollapse() 
skatolo.ControllerGroup : RadioButton enableCollapse() 
skatolo.ControllerGroup : RadioButton hide() 
skatolo.ControllerGroup : RadioButton moveTo(ControlWindow) 
skatolo.ControllerGroup : RadioButton moveTo(PApplet) 
skatolo.ControllerGroup : RadioButton open() 
skatolo.ControllerGroup : RadioButton registerProperty(String) 
skatolo.ControllerGroup : RadioButton registerProperty(String, String) 
skatolo.ControllerGroup : RadioButton remove(CDrawable) 
skatolo.ControllerGroup : RadioButton remove(ControllerInterface) 
skatolo.ControllerGroup : RadioButton removeCanvas(ControlWindowCanvas) 
skatolo.ControllerGroup : RadioButton removeProperty(String) 
skatolo.ControllerGroup : RadioButton removeProperty(String, String) 
skatolo.ControllerGroup : RadioButton setAddress(String) 
skatolo.ControllerGroup : RadioButton setArrayValue(float[]) 
skatolo.ControllerGroup : RadioButton setColor(CColor) 
skatolo.ControllerGroup : RadioButton setColorActive(int) 
skatolo.ControllerGroup : RadioButton setColorBackground(int) 
skatolo.ControllerGroup : RadioButton setColorForeground(int) 
skatolo.ControllerGroup : RadioButton setColorLabel(int) 
skatolo.ControllerGroup : RadioButton setColorValue(int) 
skatolo.ControllerGroup : RadioButton setHeight(int) 
skatolo.ControllerGroup : RadioButton setId(int) 
skatolo.ControllerGroup : RadioButton setLabel(String) 
skatolo.ControllerGroup : RadioButton setMouseOver(boolean) 
skatolo.ControllerGroup : RadioButton setMoveable(boolean) 
skatolo.ControllerGroup : RadioButton setOpen(boolean) 
skatolo.ControllerGroup : RadioButton setPosition(PVector) 
skatolo.ControllerGroup : RadioButton setPosition(float, float) 
skatolo.ControllerGroup : RadioButton setStringValue(String) 
skatolo.ControllerGroup : RadioButton setUpdate(boolean) 
skatolo.ControllerGroup : RadioButton setValue(float) 
skatolo.ControllerGroup : RadioButton setVisible(boolean) 
skatolo.ControllerGroup : RadioButton setWidth(int) 
skatolo.ControllerGroup : RadioButton show() 
skatolo.ControllerGroup : RadioButton update() 
skatolo.ControllerGroup : RadioButton updateAbsolutePosition() 
skatolo.ControllerGroup : String getAddress() 
skatolo.ControllerGroup : String getInfo() 
skatolo.ControllerGroup : String getName() 
skatolo.ControllerGroup : String getStringValue() 
skatolo.ControllerGroup : String toString() 
skatolo.ControllerGroup : Tab getTab() 
skatolo.ControllerGroup : boolean isCollapse() 
skatolo.ControllerGroup : boolean isMouseOver() 
skatolo.ControllerGroup : boolean isMoveable() 
skatolo.ControllerGroup : boolean isOpen() 
skatolo.ControllerGroup : boolean isUpdate() 
skatolo.ControllerGroup : boolean isVisible() 
skatolo.ControllerGroup : boolean setMousePressed(boolean) 
skatolo.ControllerGroup : float getValue() 
skatolo.ControllerGroup : float[] getArrayValue() 
skatolo.ControllerGroup : int getHeight() 
skatolo.ControllerGroup : int getId() 
skatolo.ControllerGroup : int getWidth() 
skatolo.ControllerGroup : void remove() 
java.lang.Object : String toString() 
java.lang.Object : boolean equals(Object) 


*/




