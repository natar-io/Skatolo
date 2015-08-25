/**
 * skatolo Color picker. a simple color picker, 
 * 4 horizontal sliders controlling the RGBA channels of a color.
 * to grab the current color value, use function getColorValue() of
 * the color picker.
 *
 * find a list of public methods available for the ColorPicker Controller 
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

ColorPicker cp;

void setup() {
  size(400, 600);
  noStroke();
  skatolo = new Skatolo(this);
  cp = skatolo.addColorPicker("picker")
          .setPosition(60, 100)
          .setColorValue(color(255, 128, 0, 128))
          ;
}

void draw() {
  background(cp.getColorValue());
  fill(0, 80);
  rect(50, 90, 275, 80);
}

public void controlEvent(ControlEvent c) {
  // when a value change from a ColorPicker is received, extract the ARGB values
  // from the controller's array value
  if(c.isFrom(cp)) {
    int r = int(c.getArrayValue(0));
    int g = int(c.getArrayValue(1));
    int b = int(c.getArrayValue(2));
    int a = int(c.getArrayValue(3));
    color col = color(r,g,b,a);
    println("event\talpha:"+a+"\tred:"+r+"\tgreen:"+g+"\tblue:"+b+"\tcol"+col);
  }
}

// color information from ColorPicker 'picker' are forwarded to the picker(int) function
void picker(int col) {
  println("picker\talpha:"+alpha(col)+"\tred:"+red(col)+"\tgreen:"+green(col)+"\tblue:"+blue(col)+"\tcol"+col);
}


void keyPressed() {
  switch(key) {
    case('1'):
    // method A to change color
    cp.setArrayValue(new float[] {120, 0, 120, 255});
    break;
    case('2'):
    // method B to change color
    cp.setColorValue(color(255, 0, 0, 255));
    break;
  }
}




/*
 a list of all methods available for the ColorPicker Controller
 use skatolo.printPublicMethodsFor(ColorPicker.class);
 to print the following list into the console.
 
 You can find further details about class ColorPicker in the javadoc.
 
 Format:
 ClassName : returnType methodName(parameter type)
 
 
 skatolo.ColorPicker : ColorPicker setArrayValue(float[]) 
 skatolo.ColorPicker : ColorPicker setColorValue(int)
 skatolo.ColorPicker : String getInfo() 
 skatolo.ColorPicker : int getColorValue() 
 skatolo.ControlGroup : ColorPicker activateEvent(boolean) 
 skatolo.ControlGroup : ColorPicker addListener(ControlListener) 
 skatolo.ControlGroup : ColorPicker hideBar() 
 skatolo.ControlGroup : ColorPicker removeListener(ControlListener) 
 skatolo.ControlGroup : ColorPicker setBackgroundColor(int) 
 skatolo.ControlGroup : ColorPicker setBackgroundHeight(int) 
 skatolo.ControlGroup : ColorPicker setBarHeight(int) 
 skatolo.ControlGroup : ColorPicker showBar() 
 skatolo.ControlGroup : ColorPicker updateInternalEvents(PApplet) 
 skatolo.ControlGroup : String getInfo() 
 skatolo.ControlGroup : String toString() 
 skatolo.ControlGroup : boolean isBarVisible() 
 skatolo.ControlGroup : int getBackgroundHeight() 
 skatolo.ControlGroup : int getBarHeight() 
 skatolo.ControlGroup : int listenerSize() 
 skatolo.ControllerGroup : CColor getColor() 
 skatolo.ControllerGroup : ColorPicker add(ControllerInterface) 
 skatolo.ControllerGroup : ColorPicker bringToFront() 
 skatolo.ControllerGroup : ColorPicker bringToFront(ControllerInterface) 
 skatolo.ControllerGroup : ColorPicker close() 
 skatolo.ControllerGroup : ColorPicker disableCollapse() 
 skatolo.ControllerGroup : ColorPicker enableCollapse() 
 skatolo.ControllerGroup : ColorPicker hide() 
 skatolo.ControllerGroup : ColorPicker moveTo(ControlWindow) 
 skatolo.ControllerGroup : ColorPicker moveTo(PApplet) 
 skatolo.ControllerGroup : ColorPicker open() 
 skatolo.ControllerGroup : ColorPicker registerProperty(String) 
 skatolo.ControllerGroup : ColorPicker registerProperty(String, String) 
 skatolo.ControllerGroup : ColorPicker remove(CDrawable) 
 skatolo.ControllerGroup : ColorPicker remove(ControllerInterface) 
 skatolo.ControllerGroup : ColorPicker removeCanvas(ControlWindowCanvas) 
 skatolo.ControllerGroup : ColorPicker removeProperty(String) 
 skatolo.ControllerGroup : ColorPicker removeProperty(String, String) 
 skatolo.ControllerGroup : ColorPicker setAddress(String) 
 skatolo.ControllerGroup : ColorPicker setArrayValue(float[]) 
 skatolo.ControllerGroup : ColorPicker setColor(CColor) 
 skatolo.ControllerGroup : ColorPicker setColorActive(int) 
 skatolo.ControllerGroup : ColorPicker setColorBackground(int) 
 skatolo.ControllerGroup : ColorPicker setColorForeground(int) 
 skatolo.ControllerGroup : ColorPicker setColorLabel(int) 
 skatolo.ControllerGroup : ColorPicker setColorValue(int) 
 skatolo.ControllerGroup : ColorPicker setHeight(int) 
 skatolo.ControllerGroup : ColorPicker setId(int) 
 skatolo.ControllerGroup : ColorPicker setLabel(String) 
 skatolo.ControllerGroup : ColorPicker setMouseOver(boolean) 
 skatolo.ControllerGroup : ColorPicker setMoveable(boolean) 
 skatolo.ControllerGroup : ColorPicker setOpen(boolean) 
 skatolo.ControllerGroup : ColorPicker setPosition(PVector) 
 skatolo.ControllerGroup : ColorPicker setPosition(float, float) 
 skatolo.ControllerGroup : ColorPicker setStringValue(String) 
 skatolo.ControllerGroup : ColorPicker setUpdate(boolean) 
 skatolo.ControllerGroup : ColorPicker setValue(float) 
 skatolo.ControllerGroup : ColorPicker setVisible(boolean) 
 skatolo.ControllerGroup : ColorPicker setWidth(int) 
 skatolo.ControllerGroup : ColorPicker show() 
 skatolo.ControllerGroup : ColorPicker update() 
 skatolo.ControllerGroup : ColorPicker updateAbsolutePosition() 
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
