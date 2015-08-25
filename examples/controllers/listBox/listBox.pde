/**
* skatolo ListBox
*
* find a list of public methods available for the ListBox Controller
* at the bottom of this sketch.
* use the scrollwheel, up or down cursors to scroll through 
* a listbox when hovering with the mouse.
*
* by Andreas Schlegel, 2012
* www.sojamo.de/libraries/skatolo
*
*/

import fr.inria.skatolo.*;
import fr.inria.skatolo.gui.group.*;
import fr.inria.skatolo.events.*;

Skatolo skatolo;

ListBox l;

int cnt = 0;

void setup() {
  size(700, 400);
  
  skatolo.printPublicMethodsFor(ListBox.class);

  skatolo = new Skatolo(this);
  l = skatolo.addListBox("myList")
         .setPosition(100, 100)
         .setSize(120, 120)
         .setItemHeight(15)
         .setBarHeight(15)
         .setColorBackground(color(255, 128))
         .setColorActive(color(0))
         .setColorForeground(color(255, 100,0))
         ;

  l.captionLabel().toUpperCase(true);
  l.captionLabel().set("A Listbox");
  l.captionLabel().setColor(0xffff0000);
  l.captionLabel().style().marginTop = 3;
  l.valueLabel().style().marginTop = 3;
  
  for (int i=0;i<80;i++) {
    ListBoxItem lbi = l.addItem("item "+i, i);
    lbi.setColorBackground(0xffff0000);
  }
  
}

void keyPressed() {
  if (key=='0') {
    // will activate the listbox item with value 5
    l.setValue(5);
  }
  if (key=='1') {
    // set the height of a listBox should always be a multiple of itemHeight
    l.setHeight(210);
  } 
  else if (key=='2') {
    // set the height of a listBox should always be a multiple of itemHeight
    l.setHeight(120);
  } 
  else if (key=='3') {
    // set the width of a listBox
    l.setWidth(200);
  }
  else if (key=='i') {
    // set the height of a listBoxItem, should always be a fraction of the listBox
    l.setItemHeight(30);
  } 
  else if (key=='u') {
    // set the height of a listBoxItem, should always be a fraction of the listBox
    l.setItemHeight(10);
    l.setBackgroundColor(color(100, 0, 0));
  } 
  else if (key=='a') {
    int n = (int)(random(100000));
    l.addItem("item "+n, n);
  } 
  else if (key=='d') {
    l.removeItem("item "+cnt);
    cnt++;
  } else if (key=='c') {
    l.clear();
  }
}

void controlEvent(ControlEvent theEvent) {
  // ListBox is if type ControlGroup.
  // 1 controlEvent will be executed, where the event
  // originates from a ControlGroup. therefore
  // you need to check the Event with
  // if (theEvent.isGroup())
  // to avoid an error message from skatolo.

  if (theEvent.isGroup()) {
    // an event from a group e.g. scrollList
    println(theEvent.group().value()+" from "+theEvent.group());
  }
  
  if(theEvent.isGroup() && theEvent.name().equals("myList")){
    int test = (int)theEvent.group().value();
    println("test "+test);
}
}

void draw() {
  background(128);
  // scroll the scroll List according to the mouseX position
  // when holding down SPACE.
  if (keyPressed && key==' ') {
    //l.scroll(mouseX/((float)width)); // scroll taks values between 0 and 1
  }
  if (keyPressed && key==' ') {
    l.setWidth(mouseX);
  }
}



/*
a list of all methods available for the ListBox Controller
use skatolo.printPublicMethodsFor(ListBox.class);
to print the following list into the console.

You can find further details about class ListBox in the javadoc.

Format:
ClassName : returnType methodName(parameter type)


skatolo.ControlGroup : ControlGroup activateEvent(boolean) 
skatolo.ControlGroup : ControlGroup addListener(ControlListener) 
skatolo.ControlGroup : ControlGroup hideBar() 
skatolo.ControlGroup : ControlGroup removeListener(ControlListener) 
skatolo.ControlGroup : ControlGroup setBackgroundColor(int) 
skatolo.ControlGroup : ControlGroup setBackgroundHeight(int) 
skatolo.ControlGroup : ControlGroup setBarHeight(int) 
skatolo.ControlGroup : ControlGroup showBar() 
skatolo.ControlGroup : ControllerGroup updateInternalEvents(PApplet) 
skatolo.ControlGroup : String info() 
skatolo.ControlGroup : String toString() 
skatolo.ControlGroup : boolean isBarVisible() 
skatolo.ControlGroup : int getBackgroundHeight() 
skatolo.ControlGroup : int getBarHeight() 
skatolo.ControlGroup : int listenerSize() 
skatolo.ControllerGroup : CColor getColor() 
skatolo.ControllerGroup : ControlWindow getWindow() 
skatolo.ControllerGroup : ControlWindowCanvas addCanvas(ControlWindowCanvas) 
skatolo.ControllerGroup : Controller getController(String) 
skatolo.ControllerGroup : ControllerGroup add(ControllerInterface) 
skatolo.ControllerGroup : ControllerGroup close() 
skatolo.ControllerGroup : ControllerGroup disableCollapse() 
skatolo.ControllerGroup : ControllerGroup enableCollapse() 
skatolo.ControllerGroup : ControllerGroup hide() 
skatolo.ControllerGroup : ControllerGroup moveTo(ControlWindow) 
skatolo.ControllerGroup : ControllerGroup open() 
skatolo.ControllerGroup : ControllerGroup registerProperty(String) 
skatolo.ControllerGroup : ControllerGroup registerProperty(String, String) 
skatolo.ControllerGroup : ControllerGroup remove(CDrawable) 
skatolo.ControllerGroup : ControllerGroup remove(ControllerInterface) 
skatolo.ControllerGroup : ControllerGroup removeCanvas(ControlWindowCanvas) 
skatolo.ControllerGroup : ControllerGroup removeProperty(String) 
skatolo.ControllerGroup : ControllerGroup removeProperty(String, String) 
skatolo.ControllerGroup : ControllerGroup setAddress(String) 
skatolo.ControllerGroup : ControllerGroup setArrayValue(float[]) 
skatolo.ControllerGroup : ControllerGroup setColor(CColor) 
skatolo.ControllerGroup : ControllerGroup setColorActive(int) 
skatolo.ControllerGroup : ControllerGroup setColorBackground(int) 
skatolo.ControllerGroup : ControllerGroup setColorForeground(int) 
skatolo.ControllerGroup : ControllerGroup setColorLabel(int) 
skatolo.ControllerGroup : ControllerGroup setColorValue(int) 
skatolo.ControllerGroup : ControllerGroup setHeight(int) 
skatolo.ControllerGroup : ControllerGroup setId(int) 
skatolo.ControllerGroup : ControllerGroup setLabel(String) 
skatolo.ControllerGroup : ControllerGroup setMoveable(boolean) 
skatolo.ControllerGroup : ControllerGroup setOpen(boolean) 
skatolo.ControllerGroup : ControllerGroup setPosition(PVector) 
skatolo.ControllerGroup : ControllerGroup setPosition(float, float) 
skatolo.ControllerGroup : ControllerGroup setStringValue(String) 
skatolo.ControllerGroup : ControllerGroup setUpdate(boolean) 
skatolo.ControllerGroup : ControllerGroup setValue(float) 
skatolo.ControllerGroup : ControllerGroup setVisible(boolean) 
skatolo.ControllerGroup : ControllerGroup setWidth(int) 
skatolo.ControllerGroup : ControllerGroup show() 
skatolo.ControllerGroup : ControllerGroup update() 
skatolo.ControllerGroup : ControllerGroup updateAbsolutePosition() 
skatolo.ControllerGroup : ControllerProperty getProperty(String) 
skatolo.ControllerGroup : ControllerProperty getProperty(String, String) 
skatolo.ControllerGroup : Label captionLabel() 
skatolo.ControllerGroup : Label valueLabel() 
skatolo.ControllerGroup : PVector getPosition() 
skatolo.ControllerGroup : String getAddress() 
skatolo.ControllerGroup : String getName() 
skatolo.ControllerGroup : String getStringValue() 
skatolo.ControllerGroup : String info() 
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
skatolo.ListBox : ListBox actAsPulldownMenu(boolean) 
skatolo.ListBox : ListBox addItems(List) 
skatolo.ListBox : ListBox addItems(List, int) 
skatolo.ListBox : ListBox addItems(String[]) 
skatolo.ListBox : ListBox clear() 
skatolo.ListBox : ListBox hideScrollbar() 
skatolo.ListBox : ListBox removeItem(String) 
skatolo.ListBox : ListBox scroll(float) 
skatolo.ListBox : ListBox setColorActive(int) 
skatolo.ListBox : ListBox setColorBackground(int) 
skatolo.ListBox : ListBox setColorForeground(int) 
skatolo.ListBox : ListBox setColorLabel(int) 
skatolo.ListBox : ListBox setHeight(int) 
skatolo.ListBox : ListBox setItemHeight(int) 
skatolo.ListBox : ListBox setListBoxItems(String[][]) 
skatolo.ListBox : ListBox setWidth(int) 
skatolo.ListBox : ListBox showScrollbar() 
skatolo.ListBox : ListBox toUpperCase(boolean) 
skatolo.ListBox : ListBoxItem addItem(String, int) 
skatolo.ListBox : ListBoxItem getItem(Controller) 
skatolo.ListBox : ListBoxItem getItem(String) 
skatolo.ListBox : ListBoxItem getItem(int) 
skatolo.ListBox : String[][] getListBoxItems() 
skatolo.ListBox : boolean isScrollbarVisible() 
java.lang.Object : String toString() 
java.lang.Object : String toString() 
java.lang.Object : boolean equals(Object) 


*/



