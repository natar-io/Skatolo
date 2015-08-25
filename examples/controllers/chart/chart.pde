/**
* skatolo Chart
*
* find a list of public methods available for the Chart Controller
* at the bottom of this sketch.
*
* by Andreas Schlegel, 2012
* www.sojamo.de/libraries/skatolo
*
*/

import fr.inria.skatolo.*;
import fr.inria.skatolo.gui.controllers.*;

Skatolo skatolo;

Chart myChart;

void setup() {
  size(400, 700);
  smooth();
  skatolo = new Skatolo(this);
  skatolo.printPublicMethodsFor(Chart.class);
  myChart = skatolo.addChart("hello")
               .setPosition(50, 50)
               .setSize(200, 200)
               .setRange(-20, 20)
               .setView(Chart.BAR) // use Chart.LINE, Chart.PIE, Chart.AREA, Chart.BAR_CENTERED
               ;

  myChart.getColor().setBackground(color(255, 100));


  myChart.addDataSet("world");
  myChart.setColors("world", color(255,0,255),color(255,0,0));
  myChart.setData("world", new float[4]);

  myChart.setStrokeWeight(1.5);

  myChart.addDataSet("earth");
  myChart.setColors("earth", color(255), color(0, 255, 0));
  myChart.updateData("earth", 1, 2, 10, 3);

}


void draw() {
  background(0);
  // unshift: add data from left to right (first in)
  myChart.unshift("world", (sin(frameCount*0.01)*10));
  
  // push: add data from right to left (last in)
  myChart.push("earth", (sin(frameCount*0.1)*10));
}







/*
a list of all methods available for the Chart Controller
use skatolo.printPublicMethodsFor(Chart.class);
to print the following list into the console.

You can find further details about class Chart in the javadoc.

Format:
ClassName : returnType methodName(parameter type)


skatolo.Chart : Chart addData(ChartData) 
skatolo.Chart : Chart addData(ChartDataSet, float) 
skatolo.Chart : Chart addData(String, ChartData) 
skatolo.Chart : Chart addData(String, float) 
skatolo.Chart : Chart addData(float) 
skatolo.Chart : Chart addDataSet(String) 
skatolo.Chart : Chart addFirst(String, float) 
skatolo.Chart : Chart addFirst(float) 
skatolo.Chart : Chart removeData(ChartData) 
skatolo.Chart : Chart removeData(String, ChartData) 
skatolo.Chart : Chart removeData(String, int) 
skatolo.Chart : Chart removeData(int) 
skatolo.Chart : Chart removeDataSet(String) 
skatolo.Chart : Chart removeLast() 
skatolo.Chart : Chart removeLast(String) 
skatolo.Chart : Chart setData(String, int, ChartData) 
skatolo.Chart : Chart setData(int, ChartData) 
skatolo.Chart : Chart setDataSet(ChartDataSet) 
skatolo.Chart : Chart setDataSet(String, ChartDataSet) 
skatolo.Chart : Chart setRange(float, float) 
skatolo.Chart : Chart setResolution(int) 
skatolo.Chart : Chart setStrokeWeight(float) 
skatolo.Chart : Chart setValue(float) 
skatolo.Chart : Chart setView(int) 
skatolo.Chart : Chart unshift(String, float) 
skatolo.Chart : Chart unshift(float) 
skatolo.Chart : ChartData getData(String, int) 
skatolo.Chart : ChartDataSet getDataSet(String) 
skatolo.Chart : LinkedHashMap getDataSet() 
skatolo.Chart : String getInfo() 
skatolo.Chart : String toString() 
skatolo.Chart : float getStrokeWeight() 
skatolo.Chart : float[] getValuesFrom(String) 
skatolo.Chart : int getResolution() 
skatolo.Chart : int size() 
skatolo.Chart : void onEnter() 
skatolo.Chart : void onLeave() 
skatolo.Controller : CColor getColor() 
skatolo.Controller : Chart addCallback(CallbackListener) 
skatolo.Controller : Chart addListener(ControlListener) 
skatolo.Controller : Chart align(int, int, int, int) 
skatolo.Controller : Chart bringToFront() 
skatolo.Controller : Chart bringToFront(ControllerInterface) 
skatolo.Controller : Chart hide() 
skatolo.Controller : Chart linebreak() 
skatolo.Controller : Chart listen(boolean) 
skatolo.Controller : Chart lock() 
skatolo.Controller : Chart plugTo(Object) 
skatolo.Controller : Chart plugTo(Object, String) 
skatolo.Controller : Chart plugTo(Object[]) 
skatolo.Controller : Chart plugTo(Object[], String) 
skatolo.Controller : Chart registerProperty(String) 
skatolo.Controller : Chart registerProperty(String, String) 
skatolo.Controller : Chart registerTooltip(String) 
skatolo.Controller : Chart removeBehavior() 
skatolo.Controller : Chart removeCallback() 
skatolo.Controller : Chart removeCallback(CallbackListener) 
skatolo.Controller : Chart removeListener(ControlListener) 
skatolo.Controller : Chart removeProperty(String) 
skatolo.Controller : Chart removeProperty(String, String) 
skatolo.Controller : Chart setArrayValue(float[]) 
skatolo.Controller : Chart setArrayValue(int, float) 
skatolo.Controller : Chart setBehavior(ControlBehavior) 
skatolo.Controller : Chart setBroadcast(boolean) 
skatolo.Controller : Chart setCaptionLabel(String) 
skatolo.Controller : Chart setColor(CColor) 
skatolo.Controller : Chart setColorActive(int) 
skatolo.Controller : Chart setColorBackground(int) 
skatolo.Controller : Chart setColorCaptionLabel(int) 
skatolo.Controller : Chart setColorForeground(int) 
skatolo.Controller : Chart setColorValueLabel(int) 
skatolo.Controller : Chart setDecimalPrecision(int) 
skatolo.Controller : Chart setDefaultValue(float) 
skatolo.Controller : Chart setHeight(int) 
skatolo.Controller : Chart setId(int) 
skatolo.Controller : Chart setImages(PImage, PImage, PImage) 
skatolo.Controller : Chart setImages(PImage, PImage, PImage, PImage) 
skatolo.Controller : Chart setLabelVisible(boolean) 
skatolo.Controller : Chart setLock(boolean) 
skatolo.Controller : Chart setMax(float) 
skatolo.Controller : Chart setMin(float) 
skatolo.Controller : Chart setMouseOver(boolean) 
skatolo.Controller : Chart setMoveable(boolean) 
skatolo.Controller : Chart setPosition(PVector) 
skatolo.Controller : Chart setPosition(float, float) 
skatolo.Controller : Chart setSize(PImage) 
skatolo.Controller : Chart setSize(int, int) 
skatolo.Controller : Chart setStringValue(String) 
skatolo.Controller : Chart setUpdate(boolean) 
skatolo.Controller : Chart setValueLabel(String) 
skatolo.Controller : Chart setView(ControllerView) 
skatolo.Controller : Chart setVisible(boolean) 
skatolo.Controller : Chart setWidth(int) 
skatolo.Controller : Chart show() 
skatolo.Controller : Chart unlock() 
skatolo.Controller : Chart unplugFrom(Object) 
skatolo.Controller : Chart unplugFrom(Object[]) 
skatolo.Controller : Chart unregisterTooltip() 
skatolo.Controller : Chart update() 
skatolo.Controller : Chart updateSize() 
skatolo.Controller : ControlBehavior getBehavior() 
skatolo.Controller : ControlWindow getControlWindow() 
skatolo.Controller : ControlWindow getWindow() 
skatolo.Controller : ControllerProperty getProperty(String) 
skatolo.Controller : ControllerProperty getProperty(String, String) 
skatolo.Controller : Label getCaptionLabel() 
skatolo.Controller : Label getValueLabel() 
skatolo.Controller : List getControllerPlugList() 
skatolo.Controller : PImage setImage(PImage) 
skatolo.Controller : PImage setImage(PImage, int) 
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



