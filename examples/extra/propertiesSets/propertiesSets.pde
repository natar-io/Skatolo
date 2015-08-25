/**
 * skatolo properties sets.
 *
 * saves/loads controller values into/from properties-file.
 * this example shows how to make property sets of controllers that can be loaded and
 * saved individually. By default property files come in a serialized format 
 * with a .ser extension.
 *
 *
 * default properties load/save key combinations are 
 * alt+shift+l to load properties
 * alt+shift+s to save properties
 *
 *
 * find a list of public methods available for the ControllerPropererties Controller 
 * at the bottom of this sketch's source code
 *
 * by andreas schlegel, 2011
 * www.sojamo.de/libraries/skatolo
 * 
 */

import skatolo.*;

Skatolo skatolo;

public int slider1 = 32;
public int slider2 = 64;
public int slider3 = 128;
public int slider4 = 255;


void setup() {
  size(400, 600);
  skatolo = new Skatolo(this);
  skatolo.printPublicMethodsFor(ControllerProperties.class);
  // add a vertical slider
  skatolo.addSlider("slider1", 0, 255, 20, 100, 128, 20);
  skatolo.addSlider("slider2", 0, 255, 20, 150, 128, 20);
  skatolo.addSlider("slider3", 0, 255, 20, 200, 128, 20);
  skatolo.addSlider("slider4", 0, 255, 20, 250, 128, 20);

  skatolo.addButton("b1", 0, 20, 350, 80, 12).setCaptionLabel("save setA");
  skatolo.addButton("b2", 0, 101, 350, 80, 12).setCaptionLabel("load setA").setColorBackground(color(0, 100, 50));

  skatolo.addButton("b3", 0, 200, 350, 80, 12).setCaptionLabel("save default");
  skatolo.addButton("b4", 0, 281, 350, 80, 12).setCaptionLabel("load default").setColorBackground(color(0, 100, 50));

  // add a new properties set 'setA'
  skatolo.getProperties().addSet("setA");

  // move controller 'slider' from the default set to setA
  // the 3 parameters read like this: move controller(1) from set(2) to set(3) 
  skatolo.getProperties().move(skatolo.getController("slider1"), "default", "setA");
  // use copy instead of move to register 'slider' with both sets (default and setA)

  // prints the current list of properties registered and the set(s) they belong to 
  skatolo.getProperties().print();
  
}

void draw() {
  background(0);

  fill(slider1);
  rect(250, 100, 100, 20);

  fill(slider2);
  rect(250, 150, 100, 20);

  fill(slider3);
  rect(250, 200, 100, 20);

  fill(slider4);
  rect(250, 250, 100, 20);
}

void b1(float v) {
  skatolo.saveProperties("setA.ser", "setA");
}

void b2(float v) {
  skatolo.loadProperties(("setA.ser"));
}

void b3(float v) {
  skatolo.saveProperties("default.ser", "default");
}

void b4(float v) {
  skatolo.loadProperties(("default.ser"));
}





/*

 a list of all methods available for the ControllerProperties class
 use skatolo.printPublicMethodsFor(ControllerProperties.class);
 to print the following list into the console.
 
 You can find further details about class ControllerProperties in the javadoc.
 
 Format:
 ClassName : returnType methodName(parameter type)
 
skatolo.ControllerProperties : ControllerProperties remove(ControllerInterface) 
skatolo.ControllerProperties : ControllerProperties remove(ControllerInterface, String) 
skatolo.ControllerProperties : ControllerProperties remove(ControllerInterface, String, String) 
skatolo.ControllerProperties : ControllerProperty getProperty(ControllerInterface, String) 
skatolo.ControllerProperties : ControllerProperty getProperty(ControllerInterface, String, String) 
skatolo.ControllerProperties : ControllerProperty register(ControllerInterface, String) 
skatolo.ControllerProperties : ControllerProperty register(ControllerInterface, String, String) 
skatolo.ControllerProperties : HashSet addSet(String) 
skatolo.ControllerProperties : HashSet getPropertySet(ControllerInterface) 
skatolo.ControllerProperties : List get(ControllerInterface) 
skatolo.ControllerProperties : Map get() 
skatolo.ControllerProperties : String toString() 
skatolo.ControllerProperties : boolean load() 
skatolo.ControllerProperties : boolean load(String) 
skatolo.ControllerProperties : void delete(ControllerProperty) 
skatolo.ControllerProperties : void move(ControllerInterface, String, String) 
skatolo.ControllerProperties : void move(ControllerProperty, String, String) 
skatolo.ControllerProperties : void only(ControllerProperty, String) 
skatolo.ControllerProperties : void print() 
skatolo.ControllerProperties : void setFormat(Format) 
java.lang.Object : String toString() 
java.lang.Object : boolean equals(Object) 
*/
