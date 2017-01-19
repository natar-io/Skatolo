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
 * The Slider2D allows to control a handle within a 2D area. This controller returns an arrayValue with the current xy position of its
 * handle.
 * 
 * @author andreas schlegel
 * 
 * @example controllers/skatoloslider2D
 * 
 */
public class Slider2D extends Controller<Slider2D> {

	protected int cursorWidth = 10, cursorHeight = 10;

	protected float cursorX, cursorY;

	protected float _myMinX, _myMinY;

	protected float _myMaxX, _myMaxY;

	public boolean isCrosshairs = true;

	private String _myValueLabelSeparator = ",";

	/**
	 * Convenience constructor to extend Slider2D.
	 * 
	 * @example use/skatoloextendController
	 * @param theskatolo
	 * @param theName
	 */
	public Slider2D(Skatolo theskatolo, String theName) {
		this(theskatolo, theskatolo.getDefaultTab(), theName, 0, 0, 99, 9);
		theskatolo.register(theskatolo.getObjectForIntrospection(), theName, this);
	}

	public Slider2D(Skatolo theskatolo, ControllerGroup<?> theParent, String theName, int theX, int theY, int theWidth, int theHeight) {
		super(theskatolo, theParent, theName, theX, theY, theWidth, theHeight);
		_myArrayValue = new float[] { 0.0f, 0.0f };
		_myMinX = 0;
		_myMinY = 0;
		_myMaxX = theWidth;
		_myMaxY = theHeight;
		getCaptionLabel().setPadding(0, Label.defaultPaddingY).align(LEFT, BOTTOM_OUTSIDE);
		getValueLabel().setPadding(0, Label.defaultPaddingY).align(RIGHT, BOTTOM_OUTSIDE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see skatolo.Controller#updateInternalEvents(processing.core.PApplet)
	 */
	 public Slider2D updateInternalEvents(PApplet theApplet) {
		if (isPointerOver()) {
			if (!skatolo.isAltDown()) {
				float tX = PApplet.constrain(controlWindow.getPointerX() - (_myParent.getAbsolutePosition().x + position.x), 0, width - cursorWidth);
				float tY = PApplet.constrain(controlWindow.getPointerY() - (_myParent.getAbsolutePosition().y + position.y), 0, height - cursorHeight);
				if (isMousePressed()) {
					cursorX = tX;
					cursorY = tY;
					updateValue();
				}
			}
		}
		return this;
	}

	public Slider2D updateValue() {
		return setValue(0);
	}

	/**
	 * sets the minimum value for the x-axis
	 * 
	 * @param theMinX
	 * @return Slider2D
	 */
	public Slider2D setMinX(float theMinX) {
		_myMinX = theMinX;
		return updateValue();
	}

	/**
	 * sets the minimum value for the y-axis
	 * 
	 * @param theMinY
	 * @return Slider2D
	 */
	public Slider2D setMinY(float theMinY) {
		_myMinY = theMinY;
		return updateValue();
	}

	/**
	 * sets the maximum value for the x-axis
	 * 
	 * @param theMaxX
	 * @return Slider2D
	 */
	public Slider2D setMaxX(float theMaxX) {
		_myMaxX = theMaxX;
		return updateValue();
	}

	/**
	 * sets the maximum value for the y-axis
	 * 
	 * @param theMaxY
	 * @return Slider2D
	 */
	public Slider2D setMaxY(float theMaxY) {
		_myMaxY = theMaxY;
		return updateValue();
	}

	public float getMinX() {
		return _myMinX;
	}

	public float getMinY() {
		return _myMinY;
	}

	public float getMaxX() {
		return _myMaxX;
	}

	public float getMaxY() {
		return _myMaxY;
	}

	public float getCursorX() {
		return cursorX;
	}

	public float getCursorY() {
		return cursorY;
	}

	public float getCursorWidth() {
		return cursorWidth;
	}

	public float getCursorHeight() {
		return cursorHeight;
	}

	public Slider2D disableCrosshair() {
		isCrosshairs = false;
		return this;
	}

	public Slider2D enableCrosshair() {
		isCrosshairs = false;
		return this;
	}

	/*
	 * (non-Javadoc) TODO see https://forum.processing.org/topic/skatolo-slider2d-questions
	 * 
	 * @see skatolo.Controller#setArrayValue(float[])
	 */
	@Override public Slider2D setArrayValue(float[] theArray) {
		_myArrayValue = theArray;
		float rX = (width - cursorWidth) / (float) (_myMaxX - _myMinX);
		float rY = (height - cursorHeight) / (float) (_myMaxY - _myMinY);
		cursorX = PApplet.constrain(theArray[0] * rX, 0, width - cursorWidth);
		cursorY = PApplet.constrain(theArray[1] * rY, 0, height - cursorHeight);
		return updateValue();
	}

	public float[] getArrayValue() {
		return _myArrayValue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see skatolo.Controller#setValue(float)
	 */
	public Slider2D setValue(float theValue) {
		_myArrayValue[0] = cursorX / ((float) (width - cursorWidth) / (float) width);
		_myArrayValue[1] = cursorY / ((float) (height - cursorHeight) / (float) height);
		_myArrayValue[0] = PApplet.map(_myArrayValue[0], 0, width, _myMinX, _myMaxX);
		_myArrayValue[1] = PApplet.map(_myArrayValue[1], 0, height, _myMinY, _myMaxY);
		_myValueLabel.set(adjustValue(_myArrayValue[0], 0) + _myValueLabelSeparator + adjustValue(_myArrayValue[1], 0));
		broadcast(FLOAT);
		return this;
	}

	/**
	 * assigns a random value to the controller.
	 */
	public Slider2D shuffle() {
		float rX = (float) Math.random();
		float rY = (float) Math.random();
		_myArrayValue[0] = rX * width;
		_myArrayValue[0] = rY * height;
		return setValue(0);
	}

	public void setValueLabelSeparator(String theSeparator) {
		_myValueLabelSeparator = theSeparator;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override public Slider2D updateDisplayMode(int theMode) {
		_myDisplayMode = theMode;
		switch (theMode) {
		case (DEFAULT):
			_myControllerView = new Slider2DView();
			break;
		case (IMAGE):
		case (SPRITE):
		case (CUSTOM):
		default:
			break;
		}
		return this;
	}

	class Slider2DView implements ControllerView<Slider2D> {

		public void display(PGraphics graphics, Slider2D theController) {

			graphics.noStroke();

			if (theController.isPointerOver()) {
				graphics.fill(theController.getColor().getForeground());
			} else {
				graphics.fill(theController.getColor().getBackground());
			}

			graphics.rect(0, 0, getWidth(), getHeight());

			if (isCrosshairs) {
				if (theController.isPointerOver()) {
					graphics.fill(theController.getColor().getBackground());
				} else {
					graphics.fill(theController.getColor().getForeground());
				}
				graphics.rect(0, (int) (getCursorY() + getCursorHeight() / 2), (int) getWidth(), 1);
				graphics.rect((int) (getCursorX() + getCursorWidth() / 2), 0, 1, (int) getHeight());
			}

			graphics.fill(theController.getColor().getActive());
			graphics.rect((int) getCursorX(), (int) getCursorY(), (int) getCursorWidth(), (int) getCursorHeight());

			getCaptionLabel().draw(graphics, 0, 0, theController);
			getValueLabel().draw(graphics, 0, 0, theController);
		}

	}
}
