/**
 * skatolo Label (extended)
 * this examples shows how to use ControlFont for 
 * custom PFonts used for labeling controllers.
 * by andreas schlegel, 2009
 */

import fr.inria.skatolo.*;
import fr.inria.skatolo.events.*;
import fr.inria.skatolo.gui.controllers.*;
import fr.inria.skatolo.gui.Label;

Skatolo skatolo;

int myColorBackground = color(0,0,0);

int sliderValue = 100;

void setup() {
  size(400,400);
  noStroke();
  skatolo = new Skatolo(this);
  PFont p = createFont("Georgia",12); 
  skatolo.setControlFont(p,12);
  skatolo.setColorLabel(color(255,128));
  Slider s = skatolo.addSlider("slider",100,167,128,100,160,10,100);
  s = skatolo.addSlider("sliderValue",0,255,128,200,200,64,100);
  
  Label label = s.valueLabel();
  label.setColor(color(255,128));
  label.style().marginTop = -10;
  
  label = s.captionLabel();
  label.toUpperCase(false);
  
}

void draw() {
  background(0);
  fill(sliderValue);
  rect(0,0,width,100);
}

void slider(float theColor) {
  myColorBackground = color(theColor);
  println("a slider event. setting background to "+theColor);
}
