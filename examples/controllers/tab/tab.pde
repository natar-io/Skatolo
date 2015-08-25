/**
* skatolo Tab
*
*
* find a list of public methods available for the Tab Controller
* at the bottom of this sketch.
*
* by Andreas Schlegel, 2012
* www.sojamo.de/libraries/skatolo
*
*/

import fr.inria.skatolo.*;
import fr.inria.skatolo.events.*;
import fr.inria.skatolo.gui.group.*;

Skatolo skatolo;

int myColorBackground = color(128);

int sliderValue = 100;

void setup() {
  size(700,400);
  noStroke();
  skatolo = new Skatolo(this);
  
  // By default all controllers are stored inside Tab 'default' 
  // add a second tab with name 'extra'
  
  skatolo.addTab("extra")
     .setColorBackground(color(0, 160, 100))
     .setColorLabel(color(255))
     .setColorActive(color(255,128,0))
     ;
     
  // if you want to receive a controlEvent when
  // a  tab is clicked, use activeEvent(true)
  
  skatolo.getTab("default")
     .activateEvent(true)
     .setLabel("my default tab")
     .setId(1)
     ;

  skatolo.getTab("extra")
     .activateEvent(true)
     .setId(2)
     ;

  
  // create a few controllers
  
  skatolo.addButton("button")
     .setBroadcast(false)
     .setPosition(100,100)
     .setSize(80,40)
     .setValue(1)
     .setBroadcast(true)
     .getCaptionLabel().align(CENTER,CENTER)
     ;
     
  skatolo.addButton("buttonValue")
     .setBroadcast(false)
     .setPosition(220,100)
     .setSize(80,40)
     .setValue(2)
     .setBroadcast(true)
     .getCaptionLabel().align(CENTER,CENTER)
     ;
  
  skatolo.addSlider("slider")
     .setBroadcast(false)
     .setRange(100,200)
     .setValue(128)
     .setPosition(100,160)
     .setSize(200,20)
     .setBroadcast(true)
     ;
     
  skatolo.addSlider("sliderValue")
     .setBroadcast(false)
     .setRange(0,255)
     .setValue(128)
     .setPosition(100,200)
     .setSize(200,20)
     .setBroadcast(true)
     ;
     
  // arrange controller in separate tabs
  
  skatolo.getController("sliderValue").moveTo("extra");
  skatolo.getController("slider").moveTo("global");
  //  skatolo.getController("slider").moveTo("default");
  
  // Tab 'global' is a tab that lies on top of any 
  // other tab and is always visible
  
}

void draw() {
  background(myColorBackground);
  fill(sliderValue);
  rect(0,0,width,100);
}

void controlEvent(ControlEvent theControlEvent) {
  if (theControlEvent.isTab()) {
    println("got an event from tab : "+theControlEvent.getTab().getName()+" with id "+theControlEvent.getTab().getId());
  }
}

void slider(int theColor) {
  myColorBackground = color(theColor);
  println("a slider event. setting background to "+theColor);
}


void keyPressed() {
  if(keyCode==TAB) {
    skatolo.getTab("extra").bringToFront();
  }
}

/*
a list of all methods available for the Tab Controller
use skatolo.printPublicMethodsFor(Tab.class);
to print the following list into the console.

You can find further details about class Tab in the javadoc.

Format:
ClassName : returnType methodName(parameter type)

skatolo.Tab : String getStringValue() 
skatolo.Tab : Tab activateEvent(boolean) 
skatolo.Tab : Tab bringToFront() 
skatolo.Tab : Tab moveTo(ControlWindow) 
skatolo.Tab : Tab setActive(boolean) 
skatolo.Tab : Tab setHeight(int) 
skatolo.Tab : Tab setLabel(String) 
skatolo.Tab : Tab setValue(float) 
skatolo.Tab : Tab setWidth(int) 
skatolo.Tab : float getValue() 
skatolo.ControllerGroup : CColor getColor() 
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
skatolo.ControllerGroup : Tab add(ControllerInterface) 
skatolo.ControllerGroup : Tab bringToFront() 
skatolo.ControllerGroup : Tab bringToFront(ControllerInterface) 
skatolo.ControllerGroup : Tab close() 
skatolo.ControllerGroup : Tab disableCollapse() 
skatolo.ControllerGroup : Tab enableCollapse() 
skatolo.ControllerGroup : Tab getTab() 
skatolo.ControllerGroup : Tab hide() 
skatolo.ControllerGroup : Tab moveTo(ControlWindow) 
skatolo.ControllerGroup : Tab moveTo(PApplet) 
skatolo.ControllerGroup : Tab open() 
skatolo.ControllerGroup : Tab registerProperty(String) 
skatolo.ControllerGroup : Tab registerProperty(String, String) 
skatolo.ControllerGroup : Tab remove(CDrawable) 
skatolo.ControllerGroup : Tab remove(ControllerInterface) 
skatolo.ControllerGroup : Tab removeCanvas(ControlWindowCanvas) 
skatolo.ControllerGroup : Tab removeProperty(String) 
skatolo.ControllerGroup : Tab removeProperty(String, String) 
skatolo.ControllerGroup : Tab setAddress(String) 
skatolo.ControllerGroup : Tab setArrayValue(float[]) 
skatolo.ControllerGroup : Tab setColor(CColor) 
skatolo.ControllerGroup : Tab setColorActive(int) 
skatolo.ControllerGroup : Tab setColorBackground(int) 
skatolo.ControllerGroup : Tab setColorForeground(int) 
skatolo.ControllerGroup : Tab setColorLabel(int) 
skatolo.ControllerGroup : Tab setColorValue(int) 
skatolo.ControllerGroup : Tab setHeight(int) 
skatolo.ControllerGroup : Tab setId(int) 
skatolo.ControllerGroup : Tab setLabel(String) 
skatolo.ControllerGroup : Tab setMouseOver(boolean) 
skatolo.ControllerGroup : Tab setMoveable(boolean) 
skatolo.ControllerGroup : Tab setOpen(boolean) 
skatolo.ControllerGroup : Tab setPosition(PVector) 
skatolo.ControllerGroup : Tab setPosition(float, float) 
skatolo.ControllerGroup : Tab setStringValue(String) 
skatolo.ControllerGroup : Tab setUpdate(boolean) 
skatolo.ControllerGroup : Tab setValue(float) 
skatolo.ControllerGroup : Tab setVisible(boolean) 
skatolo.ControllerGroup : Tab setWidth(int) 
skatolo.ControllerGroup : Tab show() 
skatolo.ControllerGroup : Tab update() 
skatolo.ControllerGroup : Tab updateAbsolutePosition() 
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





