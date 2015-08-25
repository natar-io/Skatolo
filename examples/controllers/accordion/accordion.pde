/**
 * skatolo Accordion
 * arrange controller groups in an accordion like style.
 *
 * find a list of public methods available for the Accordion Controller 
 * at the bottom of this sketch. In the example below 3 groups with controllers
 * are created and added to an accordion controller. Furthermore several key 
 * combinations are mapped to control individual settings of the accordion.
 * An accordion comes in 2 modes, Accordion.SINGLE and Accordion.MULTI where the 
 * latter allows to open multiple groups of an accordion and the SINGLE mode only
 * allows 1 group to be opened at a time.  
 *
 * by Andreas Schlegel, 2012
 * www.sojamo.de/libraries/skatolo
 *
 */

import fr.inria.skatolo.*;
import fr.inria.skatolo.gui.group.*;

Skatolo skatolo;

Accordion accordion;

color c = color(0, 160, 100);

void setup() {
  size(400, 600);
  noStroke();
  smooth();
  gui();
}

void gui() {
  
  skatolo = new Skatolo(this);
  
  // group number 1, contains 2 bangs
  Group g1 = skatolo.addGroup("myGroup1")
                .setBackgroundColor(color(0, 64))
                .setBackgroundHeight(150)
                ;
  
  skatolo.addBang("bang")
     .setPosition(10,20)
     .setSize(100,100)
     .moveTo(g1)
     .plugTo(this,"shuffle");
     ;
     
  // group number 2, contains a radiobutton
  Group g2 = skatolo.addGroup("myGroup2")
                .setBackgroundColor(color(0, 64))
                .setBackgroundHeight(150)
                ;
  
  skatolo.addRadioButton("radio")
     .setPosition(10,20)
     .setItemWidth(20)
     .setItemHeight(20)
     .addItem("black", 0)
     .addItem("red", 1)
     .addItem("green", 2)
     .addItem("blue", 3)
     .addItem("grey", 4)
     .setColorLabel(color(255))
     .activate(2)
     .moveTo(g2)
     ;

  // group number 3, contains a bang and a slider
  Group g3 = skatolo.addGroup("myGroup3")
                .setBackgroundColor(color(0, 64))
                .setBackgroundHeight(150)
                ;
  
  skatolo.addBang("shuffle")
     .setPosition(10,20)
     .setSize(40,50)
     .moveTo(g3)
     ;
     
  skatolo.addSlider("hello")
     .setPosition(60,20)
     .setSize(100,20)
     .setRange(100,500)
     .setValue(100)
     .moveTo(g3)
     ;
     
  skatolo.addSlider("world")
     .setPosition(60,50)
     .setSize(100,20)
     .setRange(100,500)
     .setValue(200)
     .moveTo(g3)
     ;

  // create a new accordion
  // add g1, g2, and g3 to the accordion.
  accordion = skatolo.addAccordion("acc")
                 .setPosition(40,40)
                 .setWidth(200)
                 .addItem(g1)
                 .addItem(g2)
                 .addItem(g3)
                 ;
                 
  skatolo.mapKeyFor(new ControlKey() {public void keyEvent() {accordion.open(0,1,2);}}, 'o');
  skatolo.mapKeyFor(new ControlKey() {public void keyEvent() {accordion.close(0,1,2);}}, 'c');
  skatolo.mapKeyFor(new ControlKey() {public void keyEvent() {accordion.setWidth(300);}}, '1');
  skatolo.mapKeyFor(new ControlKey() {public void keyEvent() {accordion.setPosition(0,0);accordion.setItemHeight(190);}}, '2'); 
  skatolo.mapKeyFor(new ControlKey() {public void keyEvent() {accordion.setCollapseMode(skatolo.ALL);}}, '3');
  skatolo.mapKeyFor(new ControlKey() {public void keyEvent() {accordion.setCollapseMode(skatolo.SINGLE);}}, '4');
  skatolo.mapKeyFor(new ControlKey() {public void keyEvent() {skatolo.remove("myGroup1");}}, '0');
  
  accordion.open(0,1,2);
  
  // use Accordion.MULTI to allow multiple group 
  // to be open at a time.
  accordion.setCollapseMode(Accordion.MULTI);
  
  // when in SINGLE mode, only 1 accordion  
  // group can be open at a time.  
  // accordion.setCollapseMode(Accordion.SINGLE);
}
  

void radio(int theC) {
  switch(theC) {
    case(0):c=color(0,200);break;
    case(1):c=color(255,0,0,200);break;
    case(2):c=color(0, 200, 140,200);break;
    case(3):c=color(0, 128, 255,200);break;
    case(4):c=color(50,128);break;
  }
} 


void shuffle() {
  c = color(random(255),random(255),random(255),random(128,255));
}


void draw() {
  background(220);
  
  fill(c);
  
  float s1 = skatolo.getController("hello").getValue();
  ellipse(200,400,s1,s1);
  
  float s2 = skatolo.getController("world").getValue();
  ellipse(300,100,s2,s2);
}





/*
a list of all methods available for the Accordion Controller
use skatolo.printPublicMethodsFor(Accordion.class);
to print the following list into the console.

You can find further details about class Accordion in the javadoc.

Format:
ClassName : returnType methodName(parameter type)


skatolo.Accordion : Accordion addItem(ControlGroup) 
skatolo.Accordion : Accordion remove(ControllerInterface) 
skatolo.Accordion : Accordion removeItem(ControlGroup) 
skatolo.Accordion : Accordion setItemHeight(int) 
skatolo.Accordion : Accordion setMinItemHeight(int) 
skatolo.Accordion : Accordion setWidth(int) 
skatolo.Accordion : Accordion updateItems() 
skatolo.Accordion : int getItemHeight() 
skatolo.Accordion : int getMinItemHeight() 
skatolo.ControlGroup : Accordion activateEvent(boolean) 
skatolo.ControlGroup : Accordion addListener(ControlListener) 
skatolo.ControlGroup : Accordion hideBar() 
skatolo.ControlGroup : Accordion removeListener(ControlListener) 
skatolo.ControlGroup : Accordion setBackgroundColor(int) 
skatolo.ControlGroup : Accordion setBackgroundHeight(int) 
skatolo.ControlGroup : Accordion setBarHeight(int) 
skatolo.ControlGroup : Accordion showBar() 
skatolo.ControlGroup : Accordion updateInternalEvents(PApplet) 
skatolo.ControlGroup : String getInfo() 
skatolo.ControlGroup : String toString() 
skatolo.ControlGroup : boolean isBarVisible() 
skatolo.ControlGroup : int getBackgroundHeight() 
skatolo.ControlGroup : int getBarHeight() 
skatolo.ControlGroup : int listenerSize() 
skatolo.ControllerGroup : Accordion add(ControllerInterface) 
skatolo.ControllerGroup : Accordion bringToFront() 
skatolo.ControllerGroup : Accordion bringToFront(ControllerInterface) 
skatolo.ControllerGroup : Accordion close() 
skatolo.ControllerGroup : Accordion disableCollapse() 
skatolo.ControllerGroup : Accordion enableCollapse() 
skatolo.ControllerGroup : Accordion hide() 
skatolo.ControllerGroup : Accordion moveTo(ControlWindow) 
skatolo.ControllerGroup : Accordion moveTo(PApplet) 
skatolo.ControllerGroup : Accordion open() 
skatolo.ControllerGroup : Accordion registerProperty(String) 
skatolo.ControllerGroup : Accordion registerProperty(String, String) 
skatolo.ControllerGroup : Accordion remove(CDrawable) 
skatolo.ControllerGroup : Accordion remove(ControllerInterface) 
skatolo.ControllerGroup : Accordion removeCanvas(ControlWindowCanvas) 
skatolo.ControllerGroup : Accordion removeProperty(String) 
skatolo.ControllerGroup : Accordion removeProperty(String, String) 
skatolo.ControllerGroup : Accordion setAddress(String) 
skatolo.ControllerGroup : Accordion setArrayValue(float[]) 
skatolo.ControllerGroup : Accordion setColor(CColor) 
skatolo.ControllerGroup : Accordion setColorActive(int) 
skatolo.ControllerGroup : Accordion setColorBackground(int) 
skatolo.ControllerGroup : Accordion setColorForeground(int) 
skatolo.ControllerGroup : Accordion setColorLabel(int) 
skatolo.ControllerGroup : Accordion setColorValue(int) 
skatolo.ControllerGroup : Accordion setHeight(int) 
skatolo.ControllerGroup : Accordion setId(int) 
skatolo.ControllerGroup : Accordion setLabel(String) 
skatolo.ControllerGroup : Accordion setMouseOver(boolean) 
skatolo.ControllerGroup : Accordion setMoveable(boolean) 
skatolo.ControllerGroup : Accordion setOpen(boolean) 
skatolo.ControllerGroup : Accordion setPosition(PVector) 
skatolo.ControllerGroup : Accordion setPosition(float, float) 
skatolo.ControllerGroup : Accordion setStringValue(String) 
skatolo.ControllerGroup : Accordion setUpdate(boolean) 
skatolo.ControllerGroup : Accordion setValue(float) 
skatolo.ControllerGroup : Accordion setVisible(boolean) 
skatolo.ControllerGroup : Accordion setWidth(int) 
skatolo.ControllerGroup : Accordion show() 
skatolo.ControllerGroup : Accordion update() 
skatolo.ControllerGroup : Accordion updateAbsolutePosition() 
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



