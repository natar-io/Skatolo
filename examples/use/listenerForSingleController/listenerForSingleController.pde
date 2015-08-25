/**
 * skatolo Listener.
 * the ControlListener interface can be used to implement a custom 
 * ControlListener which listens for incoming ControlEvent from specific
 * controller(s). MyControlListener in the example below listens to
 * ControlEvents coming in from controller 'mySlider'.
 *
 * by andreas schlegel, 2012
 */
import skatolo.*;

Skatolo skatolo;
MyControlListener myListener;

void setup() {
  size(700,400);


  skatolo = new Skatolo(this);
  skatolo.setColor(new CColor(0xffaa0000, 0xff330000, 0xffff0000, 0xffffffff, 0xffffffff));  
  
  skatolo.addSlider("mySlider")
     .setRange(100,200)
     .setValue(140)
     .setPosition(200,200)
     .setSize(100,20);
  
  myListener = new MyControlListener();
  
  skatolo.getController("mySlider").addListener(myListener);
}

void draw() {
  background(myListener.col);  
}


class MyControlListener implements ControlListener {
  int col;
  public void controlEvent(ControlEvent theEvent) {
    println("i got an event from mySlider, " +
            "changing background color to "+
            theEvent.getController().getValue());
    col = (int)theEvent.getController().getValue();
  }

}
