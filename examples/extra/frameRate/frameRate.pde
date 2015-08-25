/**
 * skatolo FrameRate
 *
 *
 * uses a textlabel to display the current or average 
 * framerate of the sketch.
 *
 * by Andreas Schlegel, 2012
 * www.sojamo.de/libraries/skatolo
 *
 */


import skatolo.*;

Skatolo skatolo;

void setup() {
  size(400,500);
  frameRate(30);
  skatolo = new Skatolo(this);
  skatolo.addFrameRate().setInterval(10).setPosition(0,height - 10);
  
}

void draw() {
  background(129);
}
