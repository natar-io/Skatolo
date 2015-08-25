/**
* skatolo Group
*
*
* find a list of public methods available for the Group Controller
* at the bottom of this sketch.
*
* by Andreas Schlegel, 2012
* www.sojamo.de/libraries/skatolo
*
*/

import fr.inria.skatolo.*;
import fr.inria.skatolo.gui.group.*;
import fr.inria.skatolo.events.*;

Skatolo skatolo;

void setup() {  
  size(700,400);

  skatolo = new Skatolo(this);
  
  Group g1 = skatolo.addGroup("g1")
                .setPosition(100,100)
                .setBackgroundHeight(100)
                .setBackgroundColor(color(255,50))
                ;
                     
  skatolo.addBang("A-1")
     .setPosition(10,20)
     .setSize(80,20)
     .setGroup(g1)
     ;
          
  skatolo.addBang("A-2")
     .setPosition(10,60)
     .setSize(80,20)
     .setGroup(g1)
     ;
     
  
  Group g2 = skatolo.addGroup("g2")
                .setPosition(300,100)
                .setWidth(300)
                .activateEvent(true)
                .setBackgroundColor(color(255,80))
                .setBackgroundHeight(100)
                .setLabel("Hello World.")
                ;
  
  skatolo.addSlider("S-1")
     .setPosition(80,10)
     .setSize(180,9)
     .setGroup(g2)
     ;
     
  skatolo.addSlider("S-2")
     .setPosition(80,20)
     .setSize(180,9)
     .setGroup(g2)
     ;
     
  skatolo.addRadioButton("radio")
     .setPosition(10,10)
     .setSize(20,9)
     .addItem("black",0)
     .addItem("red",1)
     .addItem("green",2)
     .addItem("blue",3)
     .addItem("grey",4)
     .setGroup(g2)
     ;
}


void draw() {
  background(0);
}


void controlEvent(ControlEvent theEvent) {
  if(theEvent.isGroup()) {
    println("got an event from group "
            +theEvent.getGroup().getName()
            +", isOpen? "+theEvent.getGroup().isOpen()
            );
            
  } else if (theEvent.isController()){
    println("got something from a controller "
            +theEvent.getController().getName()
            );
  }
}


void keyPressed() {
  if(key==' ') {
    if(skatolo.getGroup("g1")!=null) {
      skatolo.getGroup("g1").remove();
    }
  }
}




/*
a list of all methods available for the Group Controller
use skatolo.printPublicMethodsFor(Group.class);
to print the following list into the console.

You can find further details about class Group in the javadoc.

Format:
ClassName : returnType methodName(parameter type)


skatolo.ControlGroup : Group activateEvent(boolean) 
skatolo.ControlGroup : Group addListener(ControlListener) 
skatolo.ControlGroup : Group hideBar() 
skatolo.ControlGroup : Group removeListener(ControlListener) 
skatolo.ControlGroup : Group setBackgroundColor(int) 
skatolo.ControlGroup : Group setBackgroundHeight(int) 
skatolo.ControlGroup : Group setBarHeight(int) 
skatolo.ControlGroup : Group showBar() 
skatolo.ControlGroup : Group updateInternalEvents(PApplet) 
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
skatolo.ControllerGroup : Group add(ControllerInterface) 
skatolo.ControllerGroup : Group bringToFront() 
skatolo.ControllerGroup : Group bringToFront(ControllerInterface) 
skatolo.ControllerGroup : Group close() 
skatolo.ControllerGroup : Group disableCollapse() 
skatolo.ControllerGroup : Group enableCollapse() 
skatolo.ControllerGroup : Group hide() 
skatolo.ControllerGroup : Group moveTo(ControlWindow) 
skatolo.ControllerGroup : Group moveTo(PApplet) 
skatolo.ControllerGroup : Group open() 
skatolo.ControllerGroup : Group registerProperty(String) 
skatolo.ControllerGroup : Group registerProperty(String, String) 
skatolo.ControllerGroup : Group remove(CDrawable) 
skatolo.ControllerGroup : Group remove(ControllerInterface) 
skatolo.ControllerGroup : Group removeCanvas(ControlWindowCanvas) 
skatolo.ControllerGroup : Group removeProperty(String) 
skatolo.ControllerGroup : Group removeProperty(String, String) 
skatolo.ControllerGroup : Group setAddress(String) 
skatolo.ControllerGroup : Group setArrayValue(float[]) 
skatolo.ControllerGroup : Group setColor(CColor) 
skatolo.ControllerGroup : Group setColorActive(int) 
skatolo.ControllerGroup : Group setColorBackground(int) 
skatolo.ControllerGroup : Group setColorForeground(int) 
skatolo.ControllerGroup : Group setColorLabel(int) 
skatolo.ControllerGroup : Group setColorValue(int) 
skatolo.ControllerGroup : Group setHeight(int) 
skatolo.ControllerGroup : Group setId(int) 
skatolo.ControllerGroup : Group setLabel(String) 
skatolo.ControllerGroup : Group setMouseOver(boolean) 
skatolo.ControllerGroup : Group setMoveable(boolean) 
skatolo.ControllerGroup : Group setOpen(boolean) 
skatolo.ControllerGroup : Group setPosition(PVector) 
skatolo.ControllerGroup : Group setPosition(float, float) 
skatolo.ControllerGroup : Group setStringValue(String) 
skatolo.ControllerGroup : Group setUpdate(boolean) 
skatolo.ControllerGroup : Group setValue(float) 
skatolo.ControllerGroup : Group setVisible(boolean) 
skatolo.ControllerGroup : Group setWidth(int) 
skatolo.ControllerGroup : Group show() 
skatolo.ControllerGroup : Group update() 
skatolo.ControllerGroup : Group updateAbsolutePosition() 
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



