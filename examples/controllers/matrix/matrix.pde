/**
 * skatolo Matrix
 *
 * A matrix can be used for example as a sequencer, a drum machine.
 *
 * find a list of public methods available for the Matrix Controller
 * at the bottom of this sketch.
 *
 * by Andreas Schlegel, 2012
 * www.sojamo.de/libraries/skatolo
 *
 */
import fr.inria.skatolo.*;
import fr.inria.skatolo.events.*;
import fr.inria.skatolo.gui.controllers.*;


Skatolo skatolo;

Dong[][] d;
int nx = 10;
int ny = 10;

void setup() {
  size(700, 400);

  skatolo = new Skatolo(this);
  skatolo.printPublicMethodsFor(Matrix.class);

  skatolo.addMatrix("myMatrix")
     .setPosition(50, 100)
     .setSize(200, 200)
     .setGrid(nx, ny)
     .setGap(10, 1)
     .setInterval(200)
     .setMode(skatolo.MULTIPLES)
     .setColorBackground(color(120))
     .setBackground(color(40))
     ;
  
  skatolo.getController("myMatrix").getCaptionLabel().alignX(CENTER);
  
  // use setMode to change the cell-activation which by 
  // default is skatolo.SINGLE_ROW, 1 active cell per row, 
  // but can be changed to skatolo.SINGLE_COLUMN or 
  // skatolo.MULTIPLES

    d = new Dong[nx][ny];
  for (int x = 0;x<nx;x++) {
    for (int y = 0;y<ny;y++) {
      d[x][y] = new Dong();
    }
  }  
  noStroke();
  smooth();
}


void draw() {
  background(0);
  fill(255, 100);
  pushMatrix();
  translate(width/2 + 150, height/2);
  rotate(frameCount*0.001);
  for (int x = 0;x<nx;x++) {
    for (int y = 0;y<ny;y++) {
      d[x][y].display();
    }
  }
  popMatrix();
}


void myMatrix(int theX, int theY) {
  println("got it: "+theX+", "+theY);
  d[theX][theY].update();
}


void keyPressed() {
  if (key=='1') {
    skatolo.get(Matrix.class, "myMatrix").set(0, 0, true);
  } 
  else if (key=='2') {
    skatolo.get(Matrix.class, "myMatrix").set(0, 1, true);
  }  
  else if (key=='3') {
    skatolo.get(Matrix.class, "myMatrix").trigger(0);
  }
  else if (key=='p') {
    if (skatolo.get(Matrix.class, "myMatrix").isPlaying()) {
      skatolo.get(Matrix.class, "myMatrix").pause();
    } 
    else {
      skatolo.get(Matrix.class, "myMatrix").play();
    }
  }  
  else if (key=='0') {
    skatolo.get(Matrix.class, "myMatrix").clear();
  }
}

void controlEvent(ControlEvent theEvent) {
}


class Dong {
  float x, y;
  float s0, s1;

  Dong() {
    float f= random(-PI, PI);
    x = cos(f)*random(100, 150);
    y = sin(f)*random(100, 150);
    s0 = random(2, 10);
  }

  void display() {
    s1 += (s0-s1)*0.1;
    ellipse(x, y, s1, s1);
  }

  void update() {
    s1 = 50;
  }
}



/*
a list of all methods available for the Matrix Controller
 use skatolo.printPublicMethodsFor(Matrix.class);
 to print the following list into the console.
 
 You can find further details about class Matrix in the javadoc.
 
 Format:
 ClassName : returnType methodName(parameter type)
 
 skatolo.Matrix : Matrix clear()
 skatolo.Matrix : Matrix isPlaying() 
 skatolo.Matrix : Matrix pause() 
 skatolo.Matrix : Matrix play() 
 skatolo.Matrix : Matrix plugTo(Object) 
 skatolo.Matrix : Matrix plugTo(Object, String) 
 skatolo.Matrix : Matrix set(int, int, boolean) 
 skatolo.Matrix : Matrix setCells(int[][]) 
 skatolo.Matrix : Matrix setGap(int, int) 
 skatolo.Matrix : Matrix setGrid(int, int) 
 skatolo.Matrix : Matrix setInterval(int) 
 skatolo.Matrix : Matrix setMode(int) 
 skatolo.Matrix : Matrix setValue(float) 
 skatolo.Matrix : Matrix stop()
 skatolo.Matrix : Matrix trigger(int) 
 skatolo.Matrix : Matrix update() 
 skatolo.Matrix : boolean get(int, int) 
 skatolo.Matrix : int getInterval() 
 skatolo.Matrix : int getMode() 
 skatolo.Matrix : int[][] getCells() 
 skatolo.Matrix : void remove() 
 skatolo.Controller : CColor getColor() 
 skatolo.Controller : ControlBehavior getBehavior() 
 skatolo.Controller : ControlWindow getControlWindow() 
 skatolo.Controller : ControlWindow getWindow() 
 skatolo.Controller : ControllerProperty getProperty(String) 
 skatolo.Controller : ControllerProperty getProperty(String, String) 
 skatolo.Controller : Label getCaptionLabel() 
 skatolo.Controller : Label getValueLabel() 
 skatolo.Controller : List getControllerPlugList() 
 skatolo.Controller : Matrix addCallback(CallbackListener) 
 skatolo.Controller : Matrix addListener(ControlListener) 
 skatolo.Controller : Matrix align(int, int, int, int) 
 skatolo.Controller : Matrix bringToFront() 
 skatolo.Controller : Matrix bringToFront(ControllerInterface) 
 skatolo.Controller : Matrix hide() 
 skatolo.Controller : Matrix linebreak() 
 skatolo.Controller : Matrix listen(boolean) 
 skatolo.Controller : Matrix lock() 
 skatolo.Controller : Matrix plugTo(Object) 
 skatolo.Controller : Matrix plugTo(Object, String) 
 skatolo.Controller : Matrix plugTo(Object[]) 
 skatolo.Controller : Matrix plugTo(Object[], String) 
 skatolo.Controller : Matrix registerProperty(String) 
 skatolo.Controller : Matrix registerProperty(String, String) 
 skatolo.Controller : Matrix registerTooltip(String) 
 skatolo.Controller : Matrix removeBehavior() 
 skatolo.Controller : Matrix removeCallback() 
 skatolo.Controller : Matrix removeCallback(CallbackListener) 
 skatolo.Controller : Matrix removeListener(ControlListener) 
 skatolo.Controller : Matrix removeProperty(String) 
 skatolo.Controller : Matrix removeProperty(String, String) 
 skatolo.Controller : Matrix setArrayValue(float[]) 
 skatolo.Controller : Matrix setArrayValue(int, float) 
 skatolo.Controller : Matrix setBehavior(ControlBehavior) 
 skatolo.Controller : Matrix setBroadcast(boolean) 
 skatolo.Controller : Matrix setCaptionLabel(String) 
 skatolo.Controller : Matrix setColor(CColor) 
 skatolo.Controller : Matrix setColorActive(int) 
 skatolo.Controller : Matrix setColorBackground(int) 
 skatolo.Controller : Matrix setColorCaptionLabel(int) 
 skatolo.Controller : Matrix setColorForeground(int) 
 skatolo.Controller : Matrix setColorValueLabel(int) 
 skatolo.Controller : Matrix setDecimalPrecision(int) 
 skatolo.Controller : Matrix setDefaultValue(float) 
 skatolo.Controller : Matrix setHeight(int) 
 skatolo.Controller : Matrix setId(int) 
 skatolo.Controller : Matrix setImage(PImage) 
 skatolo.Controller : Matrix setImage(PImage, int) 
 skatolo.Controller : Matrix setImages(PImage, PImage, PImage) 
 skatolo.Controller : Matrix setImages(PImage, PImage, PImage, PImage) 
 skatolo.Controller : Matrix setLabelVisible(boolean) 
 skatolo.Controller : Matrix setLock(boolean) 
 skatolo.Controller : Matrix setMax(float) 
 skatolo.Controller : Matrix setMin(float) 
 skatolo.Controller : Matrix setMouseOver(boolean) 
 skatolo.Controller : Matrix setMoveable(boolean) 
 skatolo.Controller : Matrix setPosition(PVector) 
 skatolo.Controller : Matrix setPosition(float, float) 
 skatolo.Controller : Matrix setSize(PImage) 
 skatolo.Controller : Matrix setSize(int, int) 
 skatolo.Controller : Matrix setStringValue(String) 
 skatolo.Controller : Matrix setUpdate(boolean) 
 skatolo.Controller : Matrix setValue(float) 
 skatolo.Controller : Matrix setValueLabel(String) 
 skatolo.Controller : Matrix setView(ControllerView) 
 skatolo.Controller : Matrix setVisible(boolean) 
 skatolo.Controller : Matrix setWidth(int) 
 skatolo.Controller : Matrix show() 
 skatolo.Controller : Matrix unlock() 
 skatolo.Controller : Matrix unplugFrom(Object) 
 skatolo.Controller : Matrix unplugFrom(Object[]) 
 skatolo.Controller : Matrix unregisterTooltip() 
 skatolo.Controller : Matrix update() 
 skatolo.Controller : Matrix updateSize() 
 skatolo.Controller : PVector getAbsolutePosition() 
 skatolo.Controller : PVector getPosition() 
 skatolo.Controller : String getAddress() 
 skatolo.Controller : String getInfo() 
 skatolo.Controller : String getName() 
 skatolo.Controller : String getStringValue() 
 skatolo.Controller : String toString() 
 skatolo.Controller : Tab getTab() 
 skatolo.Controller : boolean isActive() 
 skatolo.Controller : boolean isBroadcast() 
 skatolo.Controller : boolean isInside() 
 skatolo.Controller : boolean isLabelVisible() 
 skatolo.Controller : boolean isListening() 
 skatolo.Controller : boolean isLock() 
 skatolo.Controller : boolean isMouseOver() 
 skatolo.Controller : boolean isMousePressed() 
 skatolo.Controller : boolean isMoveable() 
 skatolo.Controller : boolean isUpdate() 
 skatolo.Controller : boolean isVisible() 
 skatolo.Controller : float getArrayValue(int) 
 skatolo.Controller : float getDefaultValue() 
 skatolo.Controller : float getMax() 
 skatolo.Controller : float getMin() 
 skatolo.Controller : float getValue() 
 skatolo.Controller : float[] getArrayValue() 
 skatolo.Controller : int getDecimalPrecision() 
 skatolo.Controller : int getHeight() 
 skatolo.Controller : int getId() 
 skatolo.Controller : int getWidth() 
 skatolo.Controller : int listenerSize() 
 skatolo.Controller : void remove() 
 skatolo.Controller : void setView(ControllerView, int) 
 java.lang.Object : String toString() 
 java.lang.Object : boolean equals(Object) 
 
 
 */
