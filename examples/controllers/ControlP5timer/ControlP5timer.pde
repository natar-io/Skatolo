/**
 * ControlP5 Timer
 * by andreas schlegel, 2009
 */

import fr.inria.controlP5.*;
import fr.inria.controlP5.extra.ControlTimer;
import fr.inria.controlP5.gui.controllers.*;


ControlP5 cp5;
ControlTimer c;
Textlabel t;

void setup() {
  size(400,400);
  frameRate(30);
  cp5 = new ControlP5(this);
  c = new ControlTimer();
  t = new Textlabel(cp5,"--",100,100);
  c.setSpeedOfTime(1);
}


void draw() {
  background(0);
  t.setValue(c.toString());
  t.draw(this.g);
  t.setPosition(mouseX, mouseY);
}


void mousePressed() {
  c.reset();
}
