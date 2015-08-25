/**
 * skatolo Timer
 * by andreas schlegel, 2009
 */

import fr.inria.skatolo.*;
import fr.inria.skatolo.extra.ControlTimer;
import fr.inria.skatolo.gui.controllers.*;


Skatolo skatolo;
ControlTimer c;
Textlabel t;

void setup() {
  size(400,400);
  frameRate(30);
  skatolo = new Skatolo(this);
  c = new ControlTimer();
  t = new Textlabel(skatolo,"--",100,100);
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
