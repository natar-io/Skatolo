/**
* skatolo Textarea
*
*
* find a list of public methods available for the Textarea Controller
* at the bottom of this sketch.
*
* by Andreas Schlegel, 2012
* www.sojamo.de/libraries/skatolo
*
*/

import fr.inria.skatolo.*;
import fr.inria.skatolo.events.*;
import fr.inria.skatolo.gui.group.*;

Skatolo skatolo;
Textarea myTextarea;

void setup() {
    size(700,400);
  skatolo = new Skatolo(this);

  myTextarea = skatolo.addTextarea("txt")
                  .setPosition(100,100)
                  .setSize(200,200)
                  .setFont(createFont("arial",12))
                  .setLineHeight(14)
                  .setColor(color(128))
                  .setColorBackground(color(255,100))
                  .setColorForeground(color(255,100));
                  ;
  myTextarea.setText("Lorem Ipsum is simply dummy text of the printing and typesetting"
                    +" industry. Lorem Ipsum has been the industry's standard dummy text"
                    +" ever since the 1500s, when an unknown printer took a galley of type"
                    +" and scrambled it to make a type specimen book. It has survived not"
                    +" only five centuries, but also the leap into electronic typesetting,"
                    +" remaining essentially unchanged. It was popularised in the 1960s"
                    +" with the release of Letraset sheets containing Lorem Ipsum passages,"
                    +" and more recently with desktop publishing software like Aldus"
                    +" PageMaker including versions of Lorem Ipsum."
                    );

  skatolo.addSlider("changeWidth")
     .setRange(100,400)
     .setValue(200)
     .setPosition(100,20)
     .setSize(100,19)
     ;

  skatolo.addSlider("changeHeight")
     .setRange(100,400)
     .setValue(200)
     .setPosition(100,40)
     .setSize(100,19)
     ;

}


void keyPressed() {
  if(key=='r') {
    myTextarea.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit."
                      +" Quisque sed velit nec eros scelerisque adipiscing vitae eu sem."
                      +" Quisque malesuada interdum lectus. Pellentesque pellentesque molestie"
                      +" vestibulum. Maecenas ultricies, neque at porttitor lacinia, tellus enim"
                      +" suscipit tortor, ut dapibus orci lorem non ipsum. Mauris ut velit velit."
                      +" Fusce at purus in augue semper tincidunt imperdiet sit amet eros."
                      +" Vestibulum nunc diam, fringilla vitae tristique ut, viverra ut felis."
                      +" Proin aliquet turpis ornare leo aliquam dapibus. Integer dui nisi, condimentum"
                      +" ut sagittis non, fringilla vestibulum sapien. Sed ullamcorper libero et massa"
                      +" congue in facilisis mauris lobortis. Fusce cursus risus sit amet leo imperdiet"
                      +" lacinia faucibus turpis tempus. Pellentesque pellentesque augue sed purus varius"
                      +" sed volutpat dui rhoncus. Lorem ipsum dolor sit amet, consectetur adipiscing elit"
                      );

  } else if(key=='c') {
    myTextarea.setColor(0xffffffff);
  }
}
void draw() {
  background(0);
  if(keyPressed && key==' ') {
    myTextarea.scroll((float)mouseX/(float)width);
  }
  if(keyPressed && key=='l') {
    myTextarea.setLineHeight(mouseY);
  }
}

void changeWidth(int theValue) {
  myTextarea.setWidth(theValue);
}

void changeHeight(int theValue) {
  myTextarea.setHeight(theValue);
}




/*
a list of all methods available for the Textarea Controller
use skatolo.printPublicMethodsFor(Textarea.class);
to print the following list into the console.

You can find further details about class Textarea in the javadoc.

Format:
ClassName : returnType methodName(parameter type)

skatolo.Textarea : Label getValueLabel()
skatolo.Textarea : String getStringValue()
skatolo.Textarea : String getText()
skatolo.Textarea : Textarea disableColorBackground()
skatolo.Textarea : Textarea enableColorBackground()
skatolo.Textarea : Textarea hideScrollbar()
skatolo.Textarea : Textarea scroll(float)
skatolo.Textarea : Textarea setBorderColor(int)
skatolo.Textarea : Textarea setColor(int)
skatolo.Textarea : Textarea setColorBackground(int)
skatolo.Textarea : Textarea setFont(ControlFont)
skatolo.Textarea : Textarea setFont(PFont)
skatolo.Textarea : Textarea setFont(int)
skatolo.Textarea : Textarea setHeight(int)
skatolo.Textarea : Textarea setLineHeight(int)
skatolo.Textarea : Textarea setScrollActive(int)
skatolo.Textarea : Textarea setScrollBackground(int)
skatolo.Textarea : Textarea setScrollForeground(int)
skatolo.Textarea : Textarea setSize(int, int)
skatolo.Textarea : Textarea setText(String)
skatolo.Textarea : Textarea setWidth(int)
skatolo.Textarea : Textarea showScrollbar()
skatolo.Textarea : boolean isScrollable()
skatolo.Textarea : float getValue()
skatolo.Textarea : void controlEvent(ControlEvent)
skatolo.ControllerGroup : CColor getColor()
skatolo.ControllerGroup : ControlWindow getWindow()
skatolo.ControllerGroup : ControlWindowCanvas addCanvas(ControlWindowCanvas)
skatolo.ControllerGroup : Controller getController(String)
skatolo.ControllerGroup : ControllerProperty getProperty(String)
skatolo.ControllerGroup : ControllerProperty getProperty(String, String)
skatolo.ControllerGroup : Label getCaptionLabel()
skatolo.ControllerGroup : Label getValueLabel()
skatolo.ControllerGroup : PVector getPosition()
skatolo.ControllerGroup : String getAddress()
skatolo.ControllerGroup : String getInfo()
skatolo.ControllerGroup : String getName()
skatolo.ControllerGroup : String getStringValue()
skatolo.ControllerGroup : String toString()
skatolo.ControllerGroup : Tab getTab()
skatolo.ControllerGroup : Textarea add(ControllerInterface)
skatolo.ControllerGroup : Textarea bringToFront()
skatolo.ControllerGroup : Textarea bringToFront(ControllerInterface)
skatolo.ControllerGroup : Textarea close()
skatolo.ControllerGroup : Textarea disableCollapse()
skatolo.ControllerGroup : Textarea enableCollapse()
skatolo.ControllerGroup : Textarea hide()
skatolo.ControllerGroup : Textarea moveTo(ControlWindow)
skatolo.ControllerGroup : Textarea moveTo(PApplet)
skatolo.ControllerGroup : Textarea open()
skatolo.ControllerGroup : Textarea registerProperty(String)
skatolo.ControllerGroup : Textarea registerProperty(String, String)
skatolo.ControllerGroup : Textarea remove(CDrawable)
skatolo.ControllerGroup : Textarea remove(ControllerInterface)
skatolo.ControllerGroup : Textarea removeCanvas(ControlWindowCanvas)
skatolo.ControllerGroup : Textarea removeProperty(String)
skatolo.ControllerGroup : Textarea removeProperty(String, String)
skatolo.ControllerGroup : Textarea setAddress(String)
skatolo.ControllerGroup : Textarea setArrayValue(float[])
skatolo.ControllerGroup : Textarea setColor(CColor)
skatolo.ControllerGroup : Textarea setColorActive(int)
skatolo.ControllerGroup : Textarea setColorBackground(int)
skatolo.ControllerGroup : Textarea setColorForeground(int)
skatolo.ControllerGroup : Textarea setColorLabel(int)
skatolo.ControllerGroup : Textarea setColorValue(int)
skatolo.ControllerGroup : Textarea setHeight(int)
skatolo.ControllerGroup : Textarea setId(int)
skatolo.ControllerGroup : Textarea setLabel(String)
skatolo.ControllerGroup : Textarea setMouseOver(boolean)
skatolo.ControllerGroup : Textarea setMoveable(boolean)
skatolo.ControllerGroup : Textarea setOpen(boolean)
skatolo.ControllerGroup : Textarea setPosition(PVector)
skatolo.ControllerGroup : Textarea setPosition(float, float)
skatolo.ControllerGroup : Textarea setStringValue(String)
skatolo.ControllerGroup : Textarea setUpdate(boolean)
skatolo.ControllerGroup : Textarea setValue(float)
skatolo.ControllerGroup : Textarea setVisible(boolean)
skatolo.ControllerGroup : Textarea setWidth(int)
skatolo.ControllerGroup : Textarea show()
skatolo.ControllerGroup : Textarea update()
skatolo.ControllerGroup : Textarea updateAbsolutePosition()
skatolo.ControllerGroup : boolean isCollapse()
skatolo.ControllerGroup : boolean isMouseOver()
skatolo.ControllerGroup : boolean isMoveable()
skatolo.ControllerGroup : boolean isOpen()
skatolo.ControllerGroup : boolean isUpdate()
skatolo.ControllerGroup : boolean isVisible()
skatolo.ControllerGroup : boolean setMousePressed(boolean)
skatolo.ControllerGroup : float getValue()
skatolo.ControllerGroup : float[] getArrayValue()
skatolo.ControllerGroup : int getHeight()
skatolo.ControllerGroup : int getId()
skatolo.ControllerGroup : int getWidth()
skatolo.ControllerGroup : void remove()
java.lang.Object : String toString()
java.lang.Object : boolean equals(Object)


*/
