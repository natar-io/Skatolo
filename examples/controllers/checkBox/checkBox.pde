/**
 * skatolo Checkbox
 * an example demonstrating the use of a checkbox in skatolo. 
 * CheckBox extends the RadioButton class.
 * to control a checkbox use: 
 * activate(), deactivate(), activateAll(), deactivateAll(), toggle(), getState()
 *
 * find a list of public methods available for the Checkbox Controller 
 * at the bottom of this sketch's source code
 *
 * by Andreas Schlegel, 2012
 * www.sojamo.de/libraries/skatolo
 *
 */

import fr.inria.skatolo.*;
import fr.inria.skatolo.gui.group.*;
import fr.inria.skatolo.events.*;

Skatolo skatolo;

CheckBox checkbox;

int myColorBackground;

void setup() {
  size(700, 400);
  smooth();
  skatolo = new Skatolo(this);
  checkbox = skatolo.addCheckBox("checkBox")
                .setPosition(100, 200)
                .setColorForeground(color(120))
                .setColorActive(color(255))
                .setColorLabel(color(255))
                .setSize(40, 40)
                .setItemsPerRow(3)
                .setSpacingColumn(30)
                .setSpacingRow(20)
                .addItem("0", 0)
                .addItem("50", 50)
                .addItem("100", 100)
                .addItem("150", 150)
                .addItem("200", 200)
                .addItem("255", 255)
                ;
}

void keyPressed() {
  if (key==' ') {
    checkbox.deactivateAll();
  } 
  else {
    for (int i=0;i<6;i++) {
      // check if key 0-5 have been pressed and toggle
      // the checkbox item accordingly.
      if (keyCode==(48 + i)) { 
        // the index of checkbox items start at 0
        checkbox.toggle(i);
        println("toggle "+checkbox.getItem(i).name());
        // also see 
        // checkbox.activate(index);
        // checkbox.deactivate(index);
      }
    }
  }
}

void draw() {
  background(0);
  pushMatrix();
  translate(width/2 + 200, height/2);
  stroke(255);
  strokeWeight(2);
  fill(myColorBackground);
  ellipse(0,0,200,200);
  popMatrix();
}

void controlEvent(ControlEvent theEvent) {
  if (theEvent.isFrom(checkbox)) {
    myColorBackground = 0;
    print("got an event from "+checkbox.getName()+"\t\n");
    // checkbox uses arrayValue to store the state of 
    // individual checkbox-items. usage:
    println(checkbox.getArrayValue());
    int col = 0;
    for (int i=0;i<checkbox.getArrayValue().length;i++) {
      int n = (int)checkbox.getArrayValue()[i];
      print(n);
      if(n==1) {
        myColorBackground += checkbox.getItem(i).internalValue();
      }
    }
    println();    
  }
}

void checkBox(float[] a) {
  println(a);
}


/*
a list of all methods available for the CheckBox Controller
use skatolo.printPublicMethodsFor(CheckBox.class);
to print the following list into the console.

You can find further details about class CheckBox in the javadoc.

Format:
ClassName : returnType methodName(parameter type)


skatolo.CheckBox : CheckBox addItem(String, float) 
skatolo.CheckBox : CheckBox addItem(Toggle, float) 
skatolo.CheckBox : CheckBox deactivateAll() 
skatolo.CheckBox : CheckBox hideLabels() 
skatolo.CheckBox : CheckBox removeItem(String) 
skatolo.CheckBox : CheckBox setArrayValue(float[]) 
skatolo.CheckBox : CheckBox setColorLabels(int) 
skatolo.CheckBox : CheckBox setImage(PImage) 
skatolo.CheckBox : CheckBox setImage(PImage, int) 
skatolo.CheckBox : CheckBox setImages(PImage, PImage, PImage) 
skatolo.CheckBox : CheckBox setItemHeight(int) 
skatolo.CheckBox : CheckBox setItemWidth(int) 
skatolo.CheckBox : CheckBox setItemsPerRow(int) 
skatolo.CheckBox : CheckBox setNoneSelectedAllowed(boolean) 
skatolo.CheckBox : CheckBox setSize(PImage) 
skatolo.CheckBox : CheckBox setSize(int, int) 
skatolo.CheckBox : CheckBox setSpacingColumn(int) 
skatolo.CheckBox : CheckBox setSpacingRow(int) 
skatolo.CheckBox : CheckBox showLabels() 
skatolo.CheckBox : String getInfo() 
skatolo.CheckBox : String toString() 
skatolo.CheckBox : Toggle getItem(int)
skatolo.CheckBox : List getItems()
skatolo.CheckBox : boolean getState(String) 
skatolo.CheckBox : boolean getState(int) 
skatolo.CheckBox : void updateLayout() 
skatolo.ControlGroup : CheckBox activateEvent(boolean) 
skatolo.ControlGroup : CheckBox addListener(ControlListener) 
skatolo.ControlGroup : CheckBox hideBar() 
skatolo.ControlGroup : CheckBox removeListener(ControlListener) 
skatolo.ControlGroup : CheckBox setBackgroundColor(int) 
skatolo.ControlGroup : CheckBox setBackgroundHeight(int) 
skatolo.ControlGroup : CheckBox setBarHeight(int) 
skatolo.ControlGroup : CheckBox showBar() 
skatolo.ControlGroup : CheckBox updateInternalEvents(PApplet) 
skatolo.ControlGroup : String getInfo() 
skatolo.ControlGroup : String toString() 
skatolo.ControlGroup : boolean isBarVisible() 
skatolo.ControlGroup : int getBackgroundHeight() 
skatolo.ControlGroup : int getBarHeight() 
skatolo.ControlGroup : int listenerSize() 
skatolo.ControllerGroup : CColor getColor() 
skatolo.ControllerGroup : CheckBox add(ControllerInterface) 
skatolo.ControllerGroup : CheckBox bringToFront() 
skatolo.ControllerGroup : CheckBox bringToFront(ControllerInterface) 
skatolo.ControllerGroup : CheckBox close() 
skatolo.ControllerGroup : CheckBox disableCollapse() 
skatolo.ControllerGroup : CheckBox enableCollapse() 
skatolo.ControllerGroup : CheckBox hide() 
skatolo.ControllerGroup : CheckBox moveTo(ControlWindow) 
skatolo.ControllerGroup : CheckBox moveTo(PApplet) 
skatolo.ControllerGroup : CheckBox open() 
skatolo.ControllerGroup : CheckBox registerProperty(String) 
skatolo.ControllerGroup : CheckBox registerProperty(String, String) 
skatolo.ControllerGroup : CheckBox remove(CDrawable) 
skatolo.ControllerGroup : CheckBox remove(ControllerInterface) 
skatolo.ControllerGroup : CheckBox removeCanvas(ControlWindowCanvas) 
skatolo.ControllerGroup : CheckBox removeProperty(String) 
skatolo.ControllerGroup : CheckBox removeProperty(String, String) 
skatolo.ControllerGroup : CheckBox setAddress(String) 
skatolo.ControllerGroup : CheckBox setArrayValue(float[]) 
skatolo.ControllerGroup : CheckBox setColor(CColor) 
skatolo.ControllerGroup : CheckBox setColorActive(int) 
skatolo.ControllerGroup : CheckBox setColorBackground(int) 
skatolo.ControllerGroup : CheckBox setColorForeground(int) 
skatolo.ControllerGroup : CheckBox setColorLabel(int) 
skatolo.ControllerGroup : CheckBox setColorValue(int) 
skatolo.ControllerGroup : CheckBox setHeight(int) 
skatolo.ControllerGroup : CheckBox setId(int) 
skatolo.ControllerGroup : CheckBox setLabel(String) 
skatolo.ControllerGroup : CheckBox setMouseOver(boolean) 
skatolo.ControllerGroup : CheckBox setMoveable(boolean) 
skatolo.ControllerGroup : CheckBox setOpen(boolean) 
skatolo.ControllerGroup : CheckBox setPosition(PVector) 
skatolo.ControllerGroup : CheckBox setPosition(float, float) 
skatolo.ControllerGroup : CheckBox setStringValue(String) 
skatolo.ControllerGroup : CheckBox setUpdate(boolean) 
skatolo.ControllerGroup : CheckBox setValue(float) 
skatolo.ControllerGroup : CheckBox setVisible(boolean) 
skatolo.ControllerGroup : CheckBox setWidth(int) 
skatolo.ControllerGroup : CheckBox show() 
skatolo.ControllerGroup : CheckBox update() 
skatolo.ControllerGroup : CheckBox updateAbsolutePosition() 
skatolo.ControllerGroup : ControlWindow getWindow() 
skatolo.ControllerGroup : ControlWindowCanvas addCanvas(ControlWindowCanvas) 
skatolo.ControllerGroup : Controller getController(String) 
skatolo.ControllerGroup : ControllerProperty getProperty(String) 
skatolo.ControllerGroup : ControllerProperty getProperty(String, String) 
skatolo.ControllerGroup : Label getCaptionLabel() 
skatolo.ControllerGroup : Label getValueLabel() 
skatolo.ControllerGroup : PVector getPosition() 
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



