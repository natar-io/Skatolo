import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import fr.inria.skatolo.*; 
import fr.inria.skatolo.events.*; 
import fr.inria.skatolo.gui.group.*; 
import fr.inria.skatolo.gui.widgets.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class pixelSelect extends PApplet {

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






Skatolo skatolo;

PixelSelect select1, select2;

public void setup() {
  
  noStroke();
  skatolo = new Skatolo(this);

  select1 = skatolo.addPixelSelect("select1")
     .setPosition(100,100)
     ;

  select2 = skatolo.addPixelSelect("select2")
     .setPosition(100,120)
     ;

}

public void draw() {
    background(0);

}

boolean key1 = true;

public void keyPressed(){

    if(key == 's'){
        select1.setKeyboardControlled(key1);
        select2.setKeyboardControlled(!key1);
        key1 = !key1;
    }
}

public void controlEvent(ControlEvent theEvent) {
  println(theEvent.getController().getName());

}
  public void settings() {  size(400,600); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "pixelSelect" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
