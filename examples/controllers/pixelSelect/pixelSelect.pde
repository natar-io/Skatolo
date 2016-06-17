/**
 * skatolo Button
 * this example shows how to create buttons with skatolo.
 *
 * find a list of public methods available for the Button Controller
 * at the bottom of this sketch's source code
 *
 * by Andreas Schlegel, 2012
 * www.sojamo.de/libraries/skatolo
 *
 */

import fr.inria.skatolo.*;
import fr.inria.skatolo.events.*;
import fr.inria.skatolo.gui.group.*;
import fr.inria.skatolo.gui.widgets.*;

Skatolo skatolo;


void setup() {
  size(400,600);
  noStroke();
  skatolo = new Skatolo(this);

  skatolo.addPixelSelect("select1")
     .setPosition(100,100)
     ;

  skatolo.addPixelSelect("select2")
     .setPosition(100,120)
     ;

}

void draw() {
    background(0);

}

public void controlEvent(ControlEvent theEvent) {
  println(theEvent.getController().getName());

}
