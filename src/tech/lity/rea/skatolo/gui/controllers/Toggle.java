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
import tech.lity.rea.skatolo.gui.Label;
import tech.lity.rea.skatolo.gui.group.Tab;
import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PVector;

/**
 * a toggle can have two states, true and false, where true has the value 1 and false is 0.
 * 
 * @example controllers/skatolotoggle
 * @nosuperclasses Controller Controller
 */
public class Toggle extends Controller<Toggle> {

	protected int cnt;

	public boolean isOn = false;

	protected float internalValue = -1;

	public static int autoWidth = 39;

	public static int autoHeight = 19;

	public PVector autoSpacing = new PVector(10, 20, 0);


	/**
	 * Convenience constructor to extend Toggle.
	 * 
	 * @example use/skatoloextendController
	 * @param theskatolo
	 * @param theName
	 */
	public Toggle(Skatolo theskatolo, String theName) {
		this(theskatolo, theskatolo.getDefaultTab(), theName, 0, 0, 0, autoWidth, autoHeight);
		theskatolo.register(theskatolo.getObjectForIntrospection(), theName, this);
	}


	/**
	 * 
	 * @param theSkatolo skatolo
	 * @param theParent Tab
	 * @param theName String
	 * @param theValue float
	 * @param theX float
	 * @param theY float
	 * @param theWidth int
	 * @param theHeight int
	 */
	public Toggle(Skatolo theskatolo, Tab theParent, String theName, float theValue, float theX, float theY, int theWidth, int theHeight) {
		super(theskatolo, theParent, theName, theX, theY, theWidth, theHeight);
		_myValue = theValue;
		_myCaptionLabel.align(LEFT, BOTTOM_OUTSIDE).setPadding(0, Label.defaultPaddingY);
	}


	/**
	 * 
	 * @param graphics PApplet
	 */
	public void draw(PGraphics graphics) {
		graphics.pushMatrix();
		graphics.translate(position.x, position.y);
		_myControllerView.display(graphics, this);
		graphics.popMatrix();
	}


	protected void onEnter() {
		isActive = true;
	}


	public void onLeave() {
		isActive = false;
	}


	/**
	 * {@inheritDoc}
	 */
	public void mousePressed() {
		setState(!isOn);
		isActive = false;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override public Toggle setValue(float theValue) {
		if (theValue == 0) {
			setState(false);
		}
		else {
			setState(true);
		}
		return this;
	}


	/**
	 * @param theValue
	 */
	public Toggle setValue(boolean theValue) {
		setValue((theValue == true) ? 1 : 0);
		return this;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override public Toggle update() {
		return setValue(_myValue);
	}


	/**
	 * sets the state of the toggle, this can be true or false.
	 * 
	 * @param theFlag boolean
	 */
	public Toggle setState(boolean theFlag) {
		isOn = theFlag;
		_myValue = (isOn == false) ? 0 : 1;
		broadcast(FLOAT);
		return this;
	}


	/**
	 * 
	 * @return
	 */
	public boolean getState() {
		return isOn;
	}


	public void deactivate() {
		isOn = false;
		_myValue = (isOn == false) ? 0 : 1;
	}


	public void activate() {
		isOn = true;
		_myValue = (isOn == false) ? 0 : 1;
	}


	/**
	 * switch the state of a toggle.
	 */
	public Toggle toggle() {
		if (isOn) {
			setState(false);
		}
		else {
			setState(true);
		}
		return this;
	}


	/**
	 * set the visual mode of a Toggle. use setMode(skatolo.DEFAULT) or setMode(skatolo.SWITCH)
	 * 
	 * @param theMode
	 */
	public Toggle setMode(int theMode) {
		updateDisplayMode(theMode);
		return this;
	}


	/**
	 * by default a toggle returns 0 (for off) and 1 (for on). the internal value variable can be
	 * used to store an additional value for a toggle event.
	 * 
	 * @param theInternalValue
	 */
	public void setInternalValue(float theInternalValue) {
		internalValue = theInternalValue;
	}


	public float internalValue() {
		return internalValue;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override public Toggle linebreak() {
		skatolo.linebreak(this, true, autoWidth, autoHeight, autoSpacing);
		return this;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override public Toggle updateDisplayMode(int theState) {
		_myDisplayMode = theState;
		switch (theState) {
		case (DEFAULT):
			_myControllerView = new ToggleView();
			break;
		case (IMAGE):
			_myControllerView = new ToggleImageView();
			break;
		case (SWITCH):
			_myControllerView = new ToggleSwitchView();
			break;
		case (CUSTOM):
		default:
			break;
		}
		return this;
	}


	class ToggleView implements ControllerView<Toggle> {

		public void display(PGraphics graphics, Toggle theController) {
			if (isActive) {
				graphics.fill(isOn ? color.getActive() : color.getForeground());
			}
			else {
				graphics.fill(isOn ? color.getActive() : color.getBackground());
			}
			graphics.rect(0, 0, width, height);
			if (isLabelVisible) {
				_myCaptionLabel.draw(graphics, 0, 0, theController);
			}
		}
	}

	class ToggleImageView implements ControllerView<Toggle> {

		public void display(PGraphics graphics, Toggle theController) {
			if (isActive) {
				graphics.image((availableImages[ACTIVE] == true) ? images[ACTIVE] : images[DEFAULT], 0, 0);
			}
			else {
				if (isOn) {
					graphics.image((availableImages[ACTIVE] == true) ? images[ACTIVE] : images[DEFAULT], 0, 0);
				}
				else {
					graphics.image(images[DEFAULT], 0, 0);
				}
			}
			graphics.rect(0, 0, width, height);
		}
	}

	class ToggleSwitchView implements ControllerView<Toggle> {

		public void display(PGraphics graphics, Toggle theController) {
			graphics.fill(color.getBackground());
			graphics.rect(0, 0, width, height);
			graphics.fill(color.getActive());
			if (isOn) {
				graphics.rect(0, 0, width / 2, height);
			}
			else {
				graphics.rect((width % 2 == 0 ? 0 : 1) + width / 2, 0, width / 2, height);
			}
			if (isLabelVisible) {
				_myCaptionLabel.draw(graphics, 0, 0, theController);
			}
		}
	}
}
