/**
 * Control5 DropdownList
 * A dropdownList controller, extends the ListBox controller.
 * the most recently selected dropdownlist item is displayed inside
 * the menu bar of the DropdownList.
 *
 * find a list of public methods available for the DropdownList Controller 
 * at the bottom of this sketch's source code
 *
 *
 * by andreas schlegel, 2012
 * www.sojamo.de/libraries/skatolo
 */

import fr.inria.skatolo.*;
import fr.inria.skatolo.gui.group.*;
import fr.inria.skatolo.events.*;

Skatolo skatolo;

DropdownList d1, d2;

int cnt = 0;

void setup() {
  size(700, 400,P3D);
  
  skatolo = new Skatolo(this);
  // create a DropdownList
  d1 = skatolo.addDropdownList("myList-d1")
          .setPosition(100, 100)
          ;
          
  customize(d1); // customize the first list
  
  // create a second DropdownList
  d2 = skatolo.addDropdownList("myList-d2")
          .setPosition(400, 100)
          .setSize(200,200)
          ;
  
  customize(d2); // customize the second list
  d2.setIndex(10);
}


void customize(DropdownList ddl) {
  // a convenience function to customize a DropdownList
  ddl.setBackgroundColor(color(190));
  ddl.setItemHeight(20);
  ddl.setBarHeight(15);
  ddl.captionLabel().set("dropdown");
  ddl.captionLabel().getStyle().marginTop = 3;
  ddl.captionLabel().getStyle().marginLeft = 3;
  ddl.valueLabel().getStyle().marginTop = 3;
  for (int i=0;i<40;i++) {
    ddl.addItem("item "+i, i);
  }
  //ddl.scroll(0);
  ddl.setColorBackground(color(60));
  ddl.setColorActive(color(255, 128));
}



void keyPressed() {
  // some key events to change the properties of DropdownList d1
  if (key=='1') {
    // set the height of a pulldown menu, should always be a multiple of itemHeight
    d1.setHeight(210);
  } 
  else if (key=='2') {
    // set the height of a pulldown menu, should always be a multiple of itemHeight
    d1.setHeight(120);
  }
  else if (key=='3') {
    // set the height of a pulldown menu item, should always be a fraction of the pulldown menu
    d1.setItemHeight(30);
  } 
  else if (key=='4') {
    // set the height of a pulldown menu item, should always be a fraction of the pulldown menu
    d1.setItemHeight(12);
    d1.setBackgroundColor(color(255));
  } 
  else if (key=='5') {
    // add new items to the pulldown menu
    int n = (int)(random(100000));
    d1.addItem("item "+n, n);
  } 
  else if (key=='6') {
    // remove items from the pulldown menu  by name
    d1.removeItem("item "+cnt);
    cnt++;
  }
  else if (key=='7') {
    d1.clear();
  }
}

void controlEvent(ControlEvent theEvent) {
  // DropdownList is of type ControlGroup.
  // A controlEvent will be triggered from inside the ControlGroup class.
  // therefore you need to check the originator of the Event with
  // if (theEvent.isGroup())
  // to avoid an error message thrown by skatolo.

  if (theEvent.isGroup()) {
    // check if the Event was triggered from a ControlGroup
    println("event from group : "+theEvent.getGroup().getValue()+" from "+theEvent.getGroup());
  } 
  else if (theEvent.isController()) {
    println("event from controller : "+theEvent.getController().getValue()+" from "+theEvent.getController());
  }
}

void draw() {
  background(128);
}

/* 
 a list of all methods available for the DropdownList Controller
 use skatolo.printPublicMethodsFor(DropdownList.class);
 to print the following list into the console.
 
 You can find further details about DropdownList in the javadoc.
 
 Format: returnType methodName(parameterType)
 
 skatolo.DropdownList : ControllerGroup setValue(float) 
 skatolo.DropdownList : float getValue() 
 skatolo.ListBox : ControllerInterface setColorActive(int) 
 skatolo.ListBox : ControllerInterface setColorBackground(int) 
 skatolo.ListBox : ControllerInterface setColorForeground(int) 
 skatolo.ListBox : ControllerInterface setColorLabel(int) 
 skatolo.ListBox : ControllerInterface setColorValue(int) 
 skatolo.ListBox : ListBox setHeight(int) 
 skatolo.ListBox : ListBox setWidth(int) 
 skatolo.ListBox : ListBox toUpperCase(boolean) 
 skatolo.ListBox : ListBoxItem addItem(String, int) 
 skatolo.ListBox : ListBoxItem item(Controller) 
 skatolo.ListBox : ListBoxItem item(String) 
 skatolo.ListBox : ListBoxItem item(int) 
 skatolo.ListBox : String[][] getListBoxItems() 
 skatolo.ListBox : boolean isScrollbarVisible() 
 skatolo.ListBox : float getScrollPosition() 
 skatolo.ListBox : void actAsPulldownMenu(boolean) 
 skatolo.ListBox : void addItems(List) 
 skatolo.ListBox : void addItems(List, int) 
 skatolo.ListBox : void addItems(String[]) 
 skatolo.ListBox : void clear() 
 skatolo.ListBox : void controlEvent(ControlEvent) 
 skatolo.ListBox : void hideScrollbar() 
 skatolo.ListBox : void keyEvent(KeyEvent) 
 skatolo.ListBox : void removeItem(String) 
 skatolo.ListBox : void scroll(float) 
 skatolo.ListBox : void scrolled(int) 
 skatolo.ListBox : void setItemHeight(int) 
 skatolo.ListBox : void setListBoxItems(String[][]) 
 skatolo.ListBox : void showScrollbar() 
 skatolo.ListBox : void updateListBoxItems() 
 skatolo.ControlGroup : ControlGroup activateEvent(boolean) 
 skatolo.ControlGroup : String info() 
 skatolo.ControlGroup : String stringValue() 
 skatolo.ControlGroup : String toString() 
 skatolo.ControlGroup : boolean isBarVisible() 
 skatolo.ControlGroup : int getBackgroundHeight() 
 skatolo.ControlGroup : int getBarHeight() 
 skatolo.ControlGroup : int listenerSize() 
 skatolo.ControlGroup : void addCloseButton() 
 skatolo.ControlGroup : void addListener(ControlListener) 
 skatolo.ControlGroup : void controlEvent(ControlEvent) 
 skatolo.ControlGroup : void hideBar() 
 skatolo.ControlGroup : void mousePressed() 
 skatolo.ControlGroup : void removeCloseButton() 
 skatolo.ControlGroup : void removeListener(ControlListener) 
 skatolo.ControlGroup : void setBackgroundColor(int) 
 skatolo.ControlGroup : void setBackgroundHeight(int) 
 skatolo.ControlGroup : void setBarHeight(int) 
 skatolo.ControlGroup : void showBar() 
 skatolo.ControllerGroup : CColor getColor() 
 skatolo.ControllerGroup : ControlWindow getWindow() 
 skatolo.ControllerGroup : ControlWindowCanvas addCanvas(ControlWindowCanvas) 
 skatolo.ControllerGroup : Controller getController(String) 
 skatolo.ControllerGroup : ControllerGroup setHeight(int) 
 skatolo.ControllerGroup : ControllerGroup setValue(float) 
 skatolo.ControllerGroup : ControllerGroup setWidth(int) 
 skatolo.ControllerGroup : ControllerInterface add(ControllerInterface) 
 skatolo.ControllerGroup : ControllerInterface hide() 
 skatolo.ControllerGroup : ControllerInterface moveTo(ControlGroup, Tab, ControlWindow) 
 skatolo.ControllerGroup : ControllerInterface registerProperty(String) 
 skatolo.ControllerGroup : ControllerInterface registerProperty(String, String) 
 skatolo.ControllerGroup : ControllerInterface remove(ControllerInterface) 
 skatolo.ControllerGroup : ControllerInterface removeProperty(String) 
 skatolo.ControllerGroup : ControllerInterface removeProperty(String, String) 
 skatolo.ControllerGroup : ControllerInterface setColor(CColor) 
 skatolo.ControllerGroup : ControllerInterface setColorActive(int) 
 skatolo.ControllerGroup : ControllerInterface setColorBackground(int) 
 skatolo.ControllerGroup : ControllerInterface setColorForeground(int) 
 skatolo.ControllerGroup : ControllerInterface setColorLabel(int) 
 skatolo.ControllerGroup : ControllerInterface setColorValue(int) 
 skatolo.ControllerGroup : ControllerInterface setId(int) 
 skatolo.ControllerGroup : ControllerInterface setLabel(String) 
 skatolo.ControllerGroup : ControllerInterface show() 
 skatolo.ControllerGroup : ControllerProperty getProperty(String) 
 skatolo.ControllerGroup : ControllerProperty getProperty(String, String) 
 skatolo.ControllerGroup : Label captionLabel() 
 skatolo.ControllerGroup : Label valueLabel() 
 skatolo.ControllerGroup : PVector getAbsolutePosition() 
 skatolo.ControllerGroup : PVector getPosition() 
 skatolo.ControllerGroup : String getName() 
 skatolo.ControllerGroup : String getStringValue() 
 skatolo.ControllerGroup : String info() 
 skatolo.ControllerGroup : String toString() 
 skatolo.ControllerGroup : Tab getTab() 
 skatolo.ControllerGroup : boolean isCollapse() 
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
 skatolo.ControllerGroup : void close() 
 skatolo.ControllerGroup : void disableCollapse() 
 skatolo.ControllerGroup : void enableCollapse() 
 skatolo.ControllerGroup : void moveTo(ControlGroup) 
 skatolo.ControllerGroup : void moveTo(ControlWindow) 
 skatolo.ControllerGroup : void moveTo(ControlWindow, String) 
 skatolo.ControllerGroup : void moveTo(String) 
 skatolo.ControllerGroup : void moveTo(String, ControlWindow) 
 skatolo.ControllerGroup : void moveTo(Tab) 
 skatolo.ControllerGroup : void moveTo(Tab, ControlWindow) 
 skatolo.ControllerGroup : void open() 
 skatolo.ControllerGroup : void remove() 
 skatolo.ControllerGroup : void remove(CDrawable) 
 skatolo.ControllerGroup : void removeCanvas(ControlWindowCanvas) 
 skatolo.ControllerGroup : void setAbsolutePosition(PVector) 
 skatolo.ControllerGroup : void setArrayValue(float[]) 
 skatolo.ControllerGroup : void setGroup(ControllerGroup) 
 skatolo.ControllerGroup : void setGroup(String) 
 skatolo.ControllerGroup : void setMoveable(boolean) 
 skatolo.ControllerGroup : void setOpen(boolean) 
 skatolo.ControllerGroup : void setPosition(PVector) 
 skatolo.ControllerGroup : void setPosition(float, float) 
 skatolo.ControllerGroup : void setTab(ControlWindow, String) 
 skatolo.ControllerGroup : void setTab(String) 
 skatolo.ControllerGroup : void setTab(Tab) 
 skatolo.ControllerGroup : void setUpdate(boolean) 
 skatolo.ControllerGroup : void setVisible(boolean) 
 skatolo.ControllerGroup : void update() 
 skatolo.ControllerGroup : void updateAbsolutePosition() 
 java.lang.Object : String toString() 
 java.lang.Object : boolean equals(Object) 
 */
