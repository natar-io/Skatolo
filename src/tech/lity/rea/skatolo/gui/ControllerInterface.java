/* 
 *  skatolo is a processing gui library.
 * 
 * Copyright (C)  2017 by RealityTechSASU
 * Copyright (C)  2015-2016 by Jeremy Laviole
 * Copyright (C)  2006-2012 by Andreas Schlegel
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 * 
 * 
 */
package tech.lity.rea.skatolo.gui;

import tech.lity.rea.skatolo.Skatolo;
import tech.lity.rea.skatolo.file.ControllerProperty;
import tech.lity.rea.skatolo.events.ControlListener;
import tech.lity.rea.skatolo.gui.group.ControllerGroup;
import tech.lity.rea.skatolo.gui.group.Tab;
import processing.event.KeyEvent;
import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PVector;

/**
 * 
 * The ControllerInterface is inherited by all ControllerGroup and Controller
 * classes.
 * 
 */
public interface ControllerInterface<T> {

	
	public void init();

	public int getWidth();
	public int getHeight();

	public T setValue(float theValue);
	public float getValue();

	public T setStringValue(String theValue);
	public String getStringValue();

	public float[] getArrayValue();
	public float getArrayValue(int theIndex);
	
	public T setArrayValue(int theIndex, float theValue);
	public T setArrayValue(float[] theArray);
	
	public int getId();

        public PVector getPosition();
	public T setPosition(float theX, float theY);
	public T setPosition(PVector thePVector);

	public PVector getAbsolutePosition();
	public T setAbsolutePosition(PVector thePVector);
	public T updateAbsolutePosition();

	public ControllerInterface<?> getParent();
	
        public T bringToFront();
	public T bringToFront(ControllerInterface<?> theController);

        
	public T update();
	public T setUpdate(boolean theFlag);
	public boolean isUpdate();

        public T updateEvents();
	public void continuousUpdateEvents();

	/**
	 * a method for putting input events like e.g. mouse or keyboard events and
	 * queries. this has been taken out of the draw method for better
	 * overwriting capability.
	 * 
	 * 
	 */
	public T updateInternalEvents(PApplet theApplet);

        
	public void draw(PGraphics graphics);

	public T add(ControllerInterface<?> theElement);
	public T remove(ControllerInterface<?> theElement);
	public void remove();

	public String getName();
	public String getAddress();
	public ControlWindow getWindow();
	public Tab getTab();

	public boolean setMousePressed(boolean theStatus);

	public void keyEvent(KeyEvent theEvent);

	public T setAddress(String theAddress);

	public T setId(int theValue);
	public T setLabel(String theString);
	public T setColorActive(int theColor);
	public T setColorForeground(int theColor);
	public T setColorBackground(int theColor);
	public T setColorLabel(int theColor);
	public T setColorValue(int theColor);
	public T setColor(CColor theColor);
	public CColor getColor();
	
	public T show();
	public T hide();
	public boolean isVisible();

	public T moveTo(ControllerGroup<?> theGroup, Tab theTab, ControlWindow theWindow);
	public T moveTo(ControllerGroup<?> theGroup);

	public int getPickingColor();

	public ControllerInterface<?> parent();

	public ControllerProperty getProperty(String thePropertyName);
	public ControllerProperty getProperty(String theSetter, String theGetter);

	public T registerProperty(String thePropertyName);
	public T registerProperty(String theSetter, String theGetter);
	public T removeProperty(String thePropertyName);
	public T removeProperty(String theSetter, String theGetter);

	public boolean isPointerOver();
	public T setPointerOver(boolean theFlag);
	public T addListener(ControlListener theListener);
	public T setCaptionLabel(String theValue);
	
}
