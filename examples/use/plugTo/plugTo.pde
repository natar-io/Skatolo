/**
* skatolo plugTo
*
* This example demonstrate how to use the plugTo method to
* connect a controller to a field or method of a particular object.
* 
*
* find a list of public methods available for the skatolo Controller
* at the bottom of this sketch.
*
* by Andreas Schlegel, 2011
* www.sojamo.de/libraries/skatolo
*
*/


import processing.opengl.*;

import fr.inria.skatolo.*;
import fr.inria.skatolo.events.*;
import fr.inria.skatolo.gui.*;
import fr.inria.skatolo.gui.controllers.*;

Skatolo skatolo;

Test[] testarray;

Test test;

Button b;

int cnt;

void setup() {
  size(600,400,OPENGL);
  smooth();
  test = new Test(50);
  testarray = new Test[10];
  for(int i=0;i<10;i++) {
    testarray[i] = new Test(200 + i*20);
  }
  
  skatolo = new Skatolo(this);
  
  skatolo.begin(100,20);
  
  b = skatolo.addButton("trigger",1);
  b.setColorBackground(color(255,0,0));
  
  skatolo.addButton("plug",2);
  skatolo.addButton("unplug",3);
  
  // b is a button previously added to skatolo with name 'trigger'
  // skatolo no tries to find a field or method inside object test
  // in order to connect controller 'trigger' with test.trigger()
  b.plugTo(test);
  skatolo.end();
}

// connects controller 'trigger' with objects of type Test contained 
// inside arrat testarray
void plug(int theValue) {
   b.plugTo(testarray);
   b.setColorBackground(color(0,128,0));
   println("plugging controller b1 to array 'testarray' and variable 'test'.");
}

// disconnects controller 'trigger' from objects of type Test stored 
// inside array testarray
void unplug(int theValue) {
  b.unplugFrom(testarray);
  b.setColorBackground(color(255,0,0));
  println("removing array 'testarray' and variable 'test' from controller b1.");
}


void draw() {
  background(0);
  fill(255);
  for(int i=0;i<10;i++) {
    testarray[i].display();
  }
  test.display();
  cnt++;
  if(cnt%30 == 0) {
    skatolo.getController("trigger").update();
  }
}


class Test {
  float n0 = 0; 
  float n1 = 1; 
  float x;
  
  Test(float theX) {
    x = theX;
  } 
  
  void trigger(int theValue) {
    n1 = random(100);
  }
  
  void display() {
    n0 += (n1-n0) * 0.1;
    rect(x,200,10,n0);
  }

  void controlEvent(ControlEvent theEvent) {
    //println("\t\t b1 event sub \n\n");
  }
}


