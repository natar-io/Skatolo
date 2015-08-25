import fr.inria.skatolo.*;
import fr.inria.skatolo.events.*;
import fr.inria.skatolo.gui.controllers.*;

Skatolo skatolo;


int myColor = color(0);


void setup() {
  size(400,600);
  skatolo = new Skatolo(this);
  
  // replace the default skatolo button with an image.
  // button.setImages(defaultImage, rolloverImage, pressedImage);
  // use button.updateSize() to adjust the size of the button and 
  // resize to the dimensions of the defaultImage
  
  skatolo.addButton("buttonA")
     .setPosition(175,275)
     .setImages(loadImage("Arrow-Left.png"), loadImage("Arrow-Right.png"), loadImage("Refresh.png"))
     .updateSize();
}

void draw() {
  background(myColor);
}

public void controlEvent(ControlEvent theEvent) {
  println(theEvent.getController().getName());
  
}

// function buttonA will receive changes from 
// controller with name buttonA
public void buttonA(int theValue) {
  println("a button event from buttonA: "+theValue);
  myColor = color(128);
}

