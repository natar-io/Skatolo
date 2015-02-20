/* 
 *  controlP5 is a processing gui library.
 * 
 * Copyright (C)  2006-2012 by Andreas Schlegel
 * Copyright (C)  2015 by Jeremy Laviole
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
package fr.inria.controlP5.gui.group;

import fr.inria.controlP5.events.ControlEvent;
import fr.inria.controlP5.events.ControlListener;
import fr.inria.controlP5.ControlP5;
import fr.inria.controlP5.ControlP5Constants;
import fr.inria.controlP5.gui.Label;
import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;
import processing.core.PGraphics;

/**
 * <p>
 * In previous versions you would use the ControlGroup class to bundle controllers in a group. Now
 * please use the Group class to do so.
 * </p>
 * <p>
 * ControlGroup extends ControllerGroup, for a list and documentation of available methods see the
 * {@link ControllerGroup} documentation.
 * </p>
 * 
 * @see controlP5.Group
 * @example controllers/ControlP5group
 */
public class ControlGroup<T> extends ControllerGroup<T> implements ControlListener {


	protected int _myBackgroundHeight = 0;

	protected int _myBackgroundColor = 0x00ffffff;

	protected boolean isEventActive = false;

	protected List<ControlListener> _myControlListener;
	
	/**
	 * Convenience constructor to extend ControlGroup.
	 * 
	 * @example use/ControlP5extendController
	 * @param theControlP5
	 * @param theName
	 */
	public ControlGroup(ControlP5 theControlP5, String theName) {
		this(theControlP5, theControlP5.getDefaultTab(), theName, 0, 0,100,9);
		theControlP5.register(theControlP5.papplet, theName, this);
	}

	public ControlGroup(ControlP5 theControlP5, ControllerGroup<?> theParent, String theName, int theX, int theY, int theW, int theH) {
		super(theControlP5, theParent, theName, theX, theY);
		_myControlListener = new ArrayList<ControlListener>();
		_myValueLabel = new Label(cp5, "");
		_myWidth = theW;
		_myHeight = theH;
	}

	public void mousePressed() {
		if (isBarVisible && isCollapse) {
			if (!cp5.isAltDown()) {
				isOpen = !isOpen;
				if (isEventActive) {
					final ControlEvent myEvent = new ControlEvent(this);
					cp5.getControlBroadcaster().broadcast(myEvent, ControlP5Constants.METHOD);
					for (ControlListener cl : _myControlListener) {
						cl.controlEvent(myEvent);
					}
				}
			}
		}
	}

	/**
	 * activates or deactivates the Event status of a ControlGroup.
	 * 
	 * @see Tab
	 * @param theFlag boolean
	 */
	public T activateEvent(boolean theFlag) {
		isEventActive = theFlag;
		return me;
	}

	
	public T setSize(int theWidth, int theHeight) {
		super.setSize(theWidth, theHeight);
		setBackgroundHeight(theHeight);
		return me;
	}
	
	/**
	 * get the height of the controlGroup's background.
	 * 
	 * @return
	 */
	public int getBackgroundHeight() {
		return _myBackgroundHeight;
	}

	/**
	 * set the height of the controlGroup's background.
	 * 
	 * @param theHeight
	 * @return ControlGroup
	 */
	public T setBackgroundHeight(int theHeight) {
		_myBackgroundHeight = theHeight;
		return me;
	}

	/**
	 * set the background color of a controlGroup.
	 * 
	 * @param theColor
	 * @return ControlGroup
	 */
	public T setBackgroundColor(int theColor) {
		_myBackgroundColor = theColor;
		return me;
	}

	/**
	 * set the height of the top bar (used to open/close and move a controlGroup).
	 * 
	 * @param theHeight
	 * @return ControlGroup
	 */
	public T setBarHeight(int theHeight) {
		_myHeight = theHeight;
		return me;
	}

	/**
	 * @return int
	 */
	public int getBarHeight() {
		return _myHeight;
	}

	@Override
	public T updateInternalEvents(PApplet theApplet) {
		if (isInside && isBarVisible) {
			cp5.getWindow().setMouseOverController(this);
		}
		return me;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see controlP5.ControllerGroup#preDraw(processing.core.PApplet)
	 */
	protected void preDraw(PGraphics graphics) {
		if (isOpen) {
			graphics.fill(_myBackgroundColor);
			graphics.rect(0, 0, _myWidth, _myBackgroundHeight - 1);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see controlP5.ControllerGroup#postDraw(processing.core.PApplet)
	 */
	protected void postDraw(PGraphics graphics) {
		if (isBarVisible) {
			graphics.fill(isInside ? color.getForeground() : color.getBackground());
			graphics.rect(0, -1, _myWidth, -_myHeight);
			_myLabel.draw(graphics, 0, -_myHeight-1, this);
			if (isCollapse && isArrowVisible) {
				graphics.fill(_myLabel.getColor());
				graphics.pushMatrix();
				graphics.translate(2,0);
				if (isOpen) {
					graphics.triangle(_myWidth - 10, -_myHeight / 2 - 3, _myWidth - 4, -_myHeight / 2 - 3, _myWidth - 7, -_myHeight / 2);
				} else {
					graphics.triangle(_myWidth - 10, -_myHeight / 2, _myWidth - 4, -_myHeight / 2, _myWidth - 7, -_myHeight / 2 - 3);
				}
				graphics.popMatrix();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see controlP5.ControlListener#controlEvent(controlP5.ControlEvent)
	 */
	public void controlEvent(ControlEvent theEvent) {
		if (theEvent.getController().getName().equals(getName() + "close")) {
			hide();
		}
	}

	/**
	 * !!! experimental, see ControllerGroup.value()
	 * 
	 * 
	 * @return String
	 */
	public String stringValue() {
		return Float.toString(_myValue);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return super.toString();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getInfo() {
		return "type:\tControlGroup\n" + super.getInfo();
	}

	/**
	 * adds a listener to the controller.
	 * 
	 * @param theListener ControlListener
	 * @return ControlGroup
	 */
	public T addListener(final ControlListener theListener) {
		_myControlListener.add(theListener);
		return me;
	}

	/**
	 * remove a listener from the controller.
	 * 
	 * @param theListener ControlListener
	 * @return ControlGroup
	 */
	public T removeListener(final ControlListener theListener) {
		_myControlListener.remove(theListener);
		return me;
	}

	/**
	 * @return int
	 */
	public int listenerSize() {
		return _myControlListener.size();
	}

}
