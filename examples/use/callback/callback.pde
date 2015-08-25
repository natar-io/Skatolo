/**
 * skatolo Callback
 *
 * The following example demonstrates the CallbackListener and CallbackEvent. 
 * Here additional information about each available slider will be show when
 * hovering the controller with the mouse. The info will fade out when leaving
 * the controller. 
 *
 * When hovering a controller, the mouse pointer will change as well.
 * 
 * find a list of public methods available for the CallbackEvent Controller
 * at the bottom of this sketch.
 *
 * by Andreas Schlegel, 2012
 * www.sojamo.de/libraries/skatolo
 *
 */

import fr.inria.skatolo.*;
import fr.inria.skatolo.events.*;
import fr.inria.skatolo.gui.controllers.*;
import fr.inria.skatolo.gui.group.*;

Skatolo skatolo;
Slider s1, s2;
Info info;
CallbackListener cb;

void setup() {
  size(800, 400);

  skatolo = new Skatolo(this);

  
  // create a new instance of class Info
  // info will be used to display a controller's information and
  // will fade in when a CallbackEvent is invoked.
  info = new Info();


  // add 2 sliders
  s1 = skatolo.addSlider("hello")
          .setRange(0, 100)
          .setValue(50)
          .setPosition(40, 40)
          .setSize(100, 20);
          
  s2 = skatolo.addSlider("world")
          .setRange(0, 100)
          .setValue(10)
          .setPosition(40, 70)
          .setSize(100, 20);


  // the following CallbackListener will listen to any skatolo 
  // action such as enter, leave, pressed, released, releasedoutside, broadcast
  // see static variables starting with ACTION_ inside class skatolo.skatoloConstants

  cb = new CallbackListener() {
    public void controlEvent(CallbackEvent theEvent) {
      switch(theEvent.getAction()) {
        case(skatolo.ACTION_ENTER):
        info.n = 1;
        info.label.setText(theEvent.getController().getInfo());
        cursor(HAND);
        break;
        case(skatolo.ACTION_LEAVE):
        case(skatolo.ACTION_RELEASEDOUTSIDE):
        info.n = 0;
        cursor(ARROW);
        break;
      }
    }
  };

  // add the above callback to skatolo
  skatolo.addCallback(cb);

  // add another callback to slider s1, callback event will only be invoked for this 
  // particular controller.
  s1.addCallback(new CallbackListener() {
    public void controlEvent(CallbackEvent theEvent) {
      if (theEvent.getAction()==skatolo.ACTION_BROADCAST) {
        s2.setValue(s2.getMax() - s1.getValue());
      }
    }
  }
  );
}

void draw() {
  background(0);
  info.update();
}


void controlEvent(ControlEvent theEvent) {
  println("Got a ControlEvent for  = " +theEvent);
  info.label.setText(theEvent.getController().getInfo());
}

void keyPressed() {
  // uncomment the line below to remove callback cb from skatolo
  // when a key is pressed.
  //skatolo.removeCallback(cb);
}

// controlEvent(CallbackEvent) is called whenever a callback 
// has been triggered. controlEvent(CallbackEvent) is detected by 
// skatolo automatically.
void controlEvent(CallbackEvent theEvent) {
  if (theEvent.getController().equals(s2)) {
    switch(theEvent.getAction()) {
      case(skatolo.ACTION_ENTER): 
      println("Action:ENTER");
      break;
      case(skatolo.ACTION_LEAVE): 
      println("Action:LEAVE");
      break;
      case(skatolo.ACTION_PRESSED): 
      println("Action:PRESSED");
      break;
      case(skatolo.ACTION_RELEASED): 
      println("Action:RELEASED");
      break;
      case(skatolo.ACTION_RELEASEDOUTSIDE): 
      println("Action:RELEASED OUTSIDE");
      break;
      case(skatolo.ACTION_BROADCAST): 
      println("Action:BROADCAST");
      break;
    }
  }
}



class Info {
  float a;
  float n = 0;
  String txt = "";
  Textarea label;
  
  Info() {
    label = skatolo.addTextarea("Hello\nWorld")
               .setSize(200,200)
               .setPosition(300,40)
               .setColor(color(255))
               .setColorBackground(color(100,0))
               .setLineHeight(12);
                   
  }
  
  void update() {
    a += (n-a)*0.1;
    label.setColorBackground(color(100,255*a));
  }
}















/*
a list of all methods available for the CallbackEvent Controller
 use skatolo.printPublicMethodsFor(CallbackEvent.class);
 to print the following list into the console.
 
 You can find further details about class CallbackEvent in the javadoc.
 
 Format:
 ClassName : returnType methodName(parameter type)
 
 
 skatolo.CallbackEvent : Controller getController() 
 skatolo.CallbackEvent : int getAction() 
 java.lang.Object : String toString() 
 java.lang.Object : boolean equals(Object) 
 
 
 */



