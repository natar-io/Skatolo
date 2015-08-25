/**
 * skatolo snapshot
 *
 * this example shows how to use the snapshot methods for ControllerProperties.
 * Snapshots allow you to save controller states in memory, recall, save and remove them.
 *
 * find a list of public methods available for the ControllerProperties Controller
 * at the bottom of this sketch.
 *
 * by Andreas Schlegel, 2012
 * www.sojamo.de/libraries/skatolo
 *
 */


import fr.inria.skatolo.*;
import fr.inria.skatolo.events.*;
import fr.inria.skatolo.gui.controllers.*;
import fr.inria.skatolo.gui.*;

Skatolo skatolo;

public float n = 50;
public float s = 10;
public float k = 100;

void setup() {
  size(400, 400);
  smooth();
  skatolo = new Skatolo(this);

  skatolo.addNumberbox("n")
  .setPosition(10, 10)
  .setSize(42, 16)
  .setMultiplier(0.1)
  .setRange(10,60)
  .setValue(20)
  ;

  skatolo.addSlider("s")
  .setPosition(10, 100)
  .setSize(100, 20)
  .setScrollSensitivity(0.01)
  .setRange(60,140)
  .setValue(100)
  ;
  

  skatolo.addKnob("k")
  .setPosition(200, 100)
  .setRadius(50)
  .setScrollSensitivity(0.001)
  .setMin(60)
  .setMax(140)
  .setDisplayStyle(Controller.ARC)
  ;

  skatolo.addRange("r")
  .setPosition(10,200)
  .setSize(100,20)
  .setRange(0, 200)
  .setRangeValues(50,100)
  ;
} 


void draw() {
  background(0);
}


void keyPressed() {
  switch(key) {
    case('1'):
    skatolo.getProperties().setSnapshot("hello1");
    break;
    case('2'):
    skatolo.getProperties().setSnapshot("hello2");
    break;
    case('3'):
    skatolo.getProperties().setSnapshot("hello3");
    break;

    case('a'):
    skatolo.getProperties().getSnapshot("hello1");
    break;
    case('s'):
    skatolo.getProperties().getSnapshot("hello2");
    break;
    case('d'):
    skatolo.getProperties().getSnapshot("hello3");
    break;
    
    case('z'):
    skatolo.getProperties().removeSnapshot("hello1");
    break;
    case('x'):
    skatolo.getProperties().removeSnapshot("hello2");
    break;
    case('c'):
    skatolo.getProperties().removeSnapshot("hello3");
    break;
    
    case('i'):
    skatolo.getProperties().saveSnapshot("hello1");
    break;
    case('o'):
    skatolo.getProperties().load("hello1.ser");
    break;
  }

  println(skatolo.getProperties().getSnapshotIndices());
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



