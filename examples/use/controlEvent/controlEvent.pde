/**
 * skatolo ControlEvent.
 * every control event is automatically forwarded to the function controlEvent(ControlEvent)
 * inside a sketch if such function is available. For further details about the API of 
 * the ControlEvent class, please refer to the documentation.
 *
 *
 * find a list of public methods available for ControlEvent
 * at the bottom of this sketch's source code
 *
 * by Andreas Schlegel, 2012
 * www.sojamo.de/libraries/skatolo
 *
 */

import skatolo.*;

Skatolo skatolo;

public int myColorRect = 200;

public int myColorBackground = 100;


void setup() {
  size(400, 400);
  noStroke();
  
  skatolo = new Skatolo(this);
  skatolo.addNumberbox("n1")
     .setValue(myColorRect)
     .setPosition(100, 160)
     .setSize(100, 14)
     .setId(1);
     
  skatolo.addNumberbox("n2")
     .setValue(myColorBackground)
     .setPosition(100, 200)
     .setSize(100, 14)
     .setId(2);
     
  skatolo.addTextfield("n3")
     .setPosition(100, 240)
     .setSize(100, 20)
     .setId(3);
     
}

void draw() {
  background(myColorBackground);
  fill(myColorRect);
  rect(0, 0, width, 100);
}


void controlEvent(ControlEvent theEvent) {
  println("got a control event from controller with id "+theEvent.getController().getId());
  
  if (theEvent.isFrom(skatolo.getController("n1"))) {
    println("this event was triggered by Controller n1");
  }
  
  switch(theEvent.getController().getId()) {
    case(1):
    myColorRect = (int)(theEvent.getController().getValue());
    break;
    case(2):
    myColorBackground = (int)(theEvent.getController().getValue());
    break;
    case(3):
    println(theEvent.getController().getStringValue());
    break;
  }
}


/*
 a list of all methods available for ControlEvent
 use skatolo.printPublicMethodsFor(ControlEvent.class);
 to print the following list into the console.
 
 You can find further details about class ControlEvent in the javadoc.
 
 Format:
 ClassName : returnType methodName(parameter type)
 
 skatolo.ControlEvent : ControlGroup getGroup() 
 skatolo.ControlEvent : Controller getController() 
 skatolo.ControlEvent : String getLabel() 
 skatolo.ControlEvent : String getName() 
 skatolo.ControlEvent : String getStringValue() 
 skatolo.ControlEvent : Tab getTab() 
 skatolo.ControlEvent : boolean isController() 
 skatolo.ControlEvent : boolean isFrom(ControllerInterface) 
 skatolo.ControlEvent : boolean isFrom(String) 
 skatolo.ControlEvent : boolean isGroup() 
 skatolo.ControlEvent : boolean isTab() 
 skatolo.ControlEvent : float getValue() 
 skatolo.ControlEvent : float[] getArrayValue() 
 skatolo.ControlEvent : int getId() 
 skatolo.ControlEvent : int getType() 
 java.lang.Object : String toString() 
 java.lang.Object : boolean equals(Object) 
 */
 
 
