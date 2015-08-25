/**
 * skatolo controlFont. 
 *
 * this example shows how to create a button with skatolo (1), how to
 * load and use a PFont with skatolo (2), apply a ControlFont to
 * the caption label of a button (3), and adjust the location of a
 * caption label using the style() property of a controller.
 * 
 * by andreas schlegel, 2012
 */
import skatolo.*;

Skatolo skatolo;

skatolo.Button b;

int buttonValue = 1;

boolean isOpen;

int myColorBackground = color(0,0,0);


void setup() {
  size(700,400);
  smooth();
  
  skatolo = new Skatolo(this);
  // (1)
  // create some controllers
  skatolo.addButton("button")
     .setValue(10)
     .setPosition(20,20)
     .setSize(100,30)
     .setId(1);
     
  b = skatolo.addButton("buttonValue")
         .setValue(4)
         .setPosition(100,190)
         .setSize(200,200)
         .setId(2);
  
  // (2)
  // load a new font. ControlFont is a wrapper for processing's PFont
  // with processing 1.1 ControlFont.setSmooth() is not supported anymore.
  // to display a font as smooth or non-smooth, use true/false as 3rd parameter
  // when creating a PFont:
  
  PFont pfont = createFont("Arial",20,true); // use true/false for smooth/no-smooth
  ControlFont font = new ControlFont(pfont,241);
 
  

  // (3)
  // change the font and content of the captionlabels 
  // for both buttons create earlier.
  skatolo.getController("button")
     .getCaptionLabel()
     .setFont(font)
     .toUpperCase(false)
     .setSize(24)
     ;
     
  b.captionLabel()
   .setFont(font)
   .setSize(50)
   .toUpperCase(false)
   .setText("hello")
   ;
//  
  // (4)
  // adjust the location of a catiption label using the 
  // style property of a controller.
  b.captionLabel().getStyle().marginLeft = 4;
  b.captionLabel().getStyle().marginTop = 36;

}

void draw() {
  background(buttonValue*10);
  // animate button b
  b.position().x += ((isOpen==true ? 0:-200) - b.position().x) * 0.2;
}

public void controlEvent(ControlEvent theEvent) {
  println(theEvent.controller().id());
}

public void button(float theValue) {
  println("a button event. "+theValue);
  isOpen = !isOpen;
  skatolo.controller("button").setCaptionLabel((isOpen==true) ? "close":"open");
}




