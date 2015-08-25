/**
 * skatoloworkingWithIDs by andreas schlegel
 * an example to show how to distinguish controllers by IDs.
 * further information in the documentation folder provided in the skatolo folder.
 * skatolo website at http://www.sojamo.de/skatolo
 */

import skatolo.*;

Skatolo skatolo;

public int myColorRect = 200;

public int myColorBackground = 40;


void setup() {
  size(400,400);
  
  noStroke();
  
  /* new instance of skatolo */
  skatolo = new Skatolo(this);
  /* add 2 controllers and give each of them a unique id. */
  skatolo.addNumberbox("numberbox1")
     .setPosition(100,160)
     .setSize(100,14)
     .setId(1)
     .setValue(myColorRect);
     
  skatolo.addSlider("slider1")
     .setRange(10,200)
     .setValue(myColorBackground)
     .setPosition(100,220)
     .setSize(100,10)
     .setId(2);
}

void draw() {
  background(myColorBackground);
  fill(myColorRect);
  rect(0,0,width,100);
}


void controlEvent(ControlEvent theEvent) {
  /* events triggered by controllers are automatically forwarded to 
     the controlEvent method. by checking the id of a controller one can distinguish
     which of the controllers has been changed.
  */
  println("got a control event from controller with id "+theEvent.getController().getId());
  switch(theEvent.getController().getId()) {
    case(1):
    /* controller numberbox1 with id 1 */
    myColorRect = (int)theEvent.getValue();
    break;
    case(2):
    /* controller slider1 with id 2 */
    myColorBackground = (int)theEvent.getValue();
    break;  
  }
}
