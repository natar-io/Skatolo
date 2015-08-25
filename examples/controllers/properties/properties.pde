/**
 * skatolo Properties 
 *
 *
 * saves controller values to a properties-file
 * loads controller values from a properties-file
 *
 * Properties will only save values not the Controller itself.
 * 
 * Also take a look at the use/skatolosnapshot example to 
 * save controller values to memory.
 *
 * Use ControllerProperties to load and save serialized controller properties 
 * to a properties file. 
 * The controllers implementing save/load properties so far are 
 * Slider, Knob, Numberbox, Toggle, Checkbox, RadioButton, Textlabel, 
 * Matrix, Range, Textarea, ListBox, Dropdown, ColorPicker. 
 * Properties are currently saved in the java serialization format.
 *
 * saveProperties(String theFilename) and loadProperties(String theFilename) 
 * by default properties will be saved to your sketch folder as skatolo.ser
 * if that file already exists it will be overwritten. for custom property files
 * see the comments inside keyPressed() below.
 *
 * find a list of public methods available for the ControllerProperties class 
 * at the bottom of this sketch's source code
 *
 * default properties load/save key combinations are 
 * alt+shift+l to load properties
 * alt+shift+s to save properties
 *
 * by andreas schlegel, 2012
 * www.sojamo.de/libraries/skatolo
 *
 */

import fr.inria.skatolo.*;
import fr.inria.skatolo.gui.controllers.*;
import fr.inria.skatolo.gui.*;

Skatolo skatolo;
public int myColor = color(0, 0, 0);

public int sliderValue = 100;
public int sliderTicks1 = 100;
public int sliderTicks2 = 30;


void setup() {
  size(700, 400);
  noStroke();
  
  skatolo = new Skatolo(this);
  
  skatolo.addSlider("slider")
     .setBroadcast(false)
     .setRange(0, 200)
     .setPosition(20, 100)
     .setSize(10, 100)
     .setBroadcast(true)
     .setValue(100)
     ;
  
  skatolo.addSlider("sliderTicks1")
     .setRange(0, 255)
     .setPosition(100, 100)
     .setSize(10, 100)
     .setNumberOfTickMarks(5)
     ;

  skatolo.addSlider("sliderValue")
     .setRange(0, 255)
     .setValue(128)
     .setPosition(200, 180)
     .setSize(100, 10)
     ;
     
  skatolo.addSlider("sliderTicks2")
     .setRange(0, 255)
     .setValue(128)
     .setPosition(200, 220)
     .setSize(100, 10)
     .setNumberOfTickMarks(7)
     .setSliderMode(Slider.FLEXIBLE)
     ;
     
}

void draw() {
  background(sliderTicks1);

  fill(sliderValue);
  rect(0, 0, width, 100);

  fill(myColor);
  rect(0, 300, width, 70);

  fill(sliderTicks2);
  rect(0, 370, width, 30);
}

public void slider(float theColor) {
  myColor = color(theColor);
  println("a slider event. setting background to "+theColor);
}

void keyPressed() {
  // default properties load/save key combinations are 
  // alt+shift+l to load properties
  // alt+shift+s to save properties
  if (key=='1') {
    skatolo.saveProperties(("hello.properties"));
  } 
  else if (key=='2') {
    skatolo.loadProperties(("hello.properties"));
  }
}

/*
a list of all methods available for the ControllerProperties Controller
use skatolo.printPublicMethodsFor(ControllerProperties.class);
to print the following list into the console.

You can find further details about class ControllerProperties in the javadoc.

Format:
ClassName : returnType methodName(parameter type)


skatolo.ControllerProperties : ArrayList getSnapshotIndices() 
skatolo.ControllerProperties : ControllerProperties addSet(String) 
skatolo.ControllerProperties : ControllerProperties delete(ControllerProperty) 
skatolo.ControllerProperties : ControllerProperties getSnapshot(String) 
skatolo.ControllerProperties : ControllerProperties move(ControllerInterface, String, String) 
skatolo.ControllerProperties : ControllerProperties move(ControllerProperty, String, String) 
skatolo.ControllerProperties : ControllerProperties only(ControllerProperty, String) 
skatolo.ControllerProperties : ControllerProperties print() 
skatolo.ControllerProperties : ControllerProperties register(ControllerInterface, String) 
skatolo.ControllerProperties : ControllerProperties remove(ControllerInterface) 
skatolo.ControllerProperties : ControllerProperties remove(ControllerInterface, String) 
skatolo.ControllerProperties : ControllerProperties remove(ControllerInterface, String, String) 
skatolo.ControllerProperties : ControllerProperties removeSnapshot(String) 
skatolo.ControllerProperties : ControllerProperties saveSnapshot(String) 
skatolo.ControllerProperties : ControllerProperties saveSnapshotAs(String, String) 
skatolo.ControllerProperties : ControllerProperties setSnapshot(String) 
skatolo.ControllerProperties : ControllerProperties updateSnapshot(String) 
skatolo.ControllerProperties : ControllerProperty getProperty(ControllerInterface, String) 
skatolo.ControllerProperties : ControllerProperty getProperty(ControllerInterface, String, String) 
skatolo.ControllerProperties : ControllerProperty register(ControllerInterface, String, String) 
skatolo.ControllerProperties : HashSet getPropertySet(ControllerInterface) 
skatolo.ControllerProperties : List get(ControllerInterface) 
skatolo.ControllerProperties : Map get() 
skatolo.ControllerProperties : String toString() 
skatolo.ControllerProperties : boolean load() 
skatolo.ControllerProperties : boolean load(String) 
skatolo.ControllerProperties : boolean save() 
skatolo.ControllerProperties : boolean saveAs(String) 
skatolo.ControllerProperties : void setFormat(Format) 
java.lang.Object : String toString() 
java.lang.Object : boolean equals(Object) 


*/


