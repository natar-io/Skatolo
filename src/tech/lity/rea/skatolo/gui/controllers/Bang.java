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
package tech.lity.rea.skatolo.gui.controllers;


import tech.lity.rea.skatolo.Skatolo;
import tech.lity.rea.skatolo.gui.Controller;
import tech.lity.rea.skatolo.gui.group.ControllerGroup;
import tech.lity.rea.skatolo.gui.Label;
import processing.core.PApplet;
import processing.core.PGraphics;

/**
 * <p>
 * The Bang controller triggers an event when pressed. A bang can only be assigned to a function in
 * your program but not to a field like other controllers. Bang extends superclass Controller, for a
 * full documentation see the {@link Controller} reference.
 * </p>
 * 
 * @example controllers/skatolobang
 */
@Skatolo.Layout public class Bang extends Controller<Bang> {

	protected int cnt;

	protected int triggerId = PRESSED;


	/**
	 * Convenience constructor to extend Bang.
	 * 
	 * @example use/skatoloextendController
	 * @param theskatolo
	 * @param theName
	 */
	public Bang(Skatolo theskatolo, String theName) {
		this(theskatolo, theskatolo.getDefaultTab(), theName, 0, 0, 20, 20);
		theskatolo.register(theskatolo.getObjectForIntrospection(), theName, this);
	}


	public Bang(Skatolo theskatolo, ControllerGroup<?> theParent, String theName, float theX, float theY, int theWidth, int theHeight) {
		super(theskatolo, theParent, theName, theX, theY, theWidth, theHeight);
		_myCaptionLabel.setPadding(0, Label.defaultPaddingY).align(LEFT, BOTTOM_OUTSIDE);
		_myValue = 1;
	}


	@Override protected void onEnter() {
		cnt = 0;
		isActive = true;
	}


	@Override public void onLeave() {
		isActive = false;
	}


	@Override protected void mousePressed() {
		if (triggerId == PRESSED) {
			cnt = -3;
			isActive = true;
			update();
		}
	}


	@Override protected void mouseReleased() {
		if (triggerId == RELEASE) {
			cnt = -3;
			isActive = true;
			update();
		}
	}


	@Override protected void mouseReleasedOutside() {
		onLeave();
	}


	/**
	 * By default a bang is triggered when the mouse is pressed. use setTriggerEvent(Bang.PRESSED)
	 * or setTriggerEvent(Bang.RELEASE) to define the action for triggering a bang. currently only
	 * Bang.PRESSED and Bang.RELEASE are supported.
	 * 
	 * @param theEventID
	 * @return Bang
	 */
	@Skatolo.Layout public Bang setTriggerEvent(int theEventID) {
		triggerId = theEventID;
		return this;
	}


	@Skatolo.Layout public int getTriggerEvent() {
		return triggerId;
	}


	/**
	 * Sets the value of the bang controller. since bang can be true or false, false=0 and true=1
	 * 
	 * @param theValue float
	 * @return Bang
	 */
	@Override public Bang setValue(float theValue) {
		_myValue = theValue;
		broadcast(FLOAT);
		return this;
	}


	/**
	 * @exclude
	 */
	@Override public Bang update() {
		return setValue(_myValue);
	}


	/**
	 * @exclude
	 */
	@Override public Bang updateDisplayMode(int theMode) {
		updateViewMode(theMode);
		return this;
	}


	/**
	 * @exclude
	 */
	public Bang updateViewMode(int theMode) {
		_myDisplayMode = theMode;
		switch (theMode) {
		case (DEFAULT):
			_myControllerView = new BangView();
			break;
		case (IMAGE):
			_myControllerView = new BangImageView();
			break;
		case (CUSTOM):
		default:
			break;
		}
		return this;
	}


	private class BangView implements ControllerView<Bang> {

		public void display(PGraphics graphics, Bang theController) {
			if (isActive) {
				graphics.fill(color.getActive());
			}
			else {
				graphics.fill(color.getForeground());
			}

			if (cnt < 0) {
				graphics.fill(color.getForeground());
				cnt++;
			}
			graphics.rect(0, 0, width, height);
			if (isLabelVisible) {
				_myCaptionLabel.draw(graphics, 0, 0, theController);
			}
		}
	}

	private class BangImageView implements ControllerView<Bang> {

		public void display(PGraphics graphics, Bang theController) {
			if (isActive) {
				graphics.image((availableImages[ACTIVE] == true) ? images[ACTIVE] : images[DEFAULT], 0, 0);
			}
			else {
				graphics.image((availableImages[OVER] == true) ? images[OVER] : images[DEFAULT], 0, 0);
			}
			if (cnt < 0) {
				graphics.image((availableImages[OVER] == true) ? images[OVER] : images[DEFAULT], 0, 0);
				cnt++;
			}
			if (!isActive && cnt >= 0) {
				graphics.image(images[DEFAULT], 0, 0);
			}
		}
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @exclude
	 */
	@Override public String getInfo() {
		return "type:\tBang\n" + super.getInfo();
	}


	/**
	 * @exclude {@inheritDoc}
	 */
	@Override public String toString() {
		return super.toString() + " [ " + getValue() + " ] " + "Bang" + " (" + this.getClass().getSuperclass() + ")";
	}

}
