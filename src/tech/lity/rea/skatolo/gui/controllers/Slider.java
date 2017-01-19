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
import tech.lity.rea.skatolo.gui.TickMark;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PGraphics;

/**
 * A slider is either used horizontally or vertically. when adding a slider to skatolo, the width
 * is compared against the height. if the width is bigger, you get a horizontal slider, is the
 * height bigger, you get a vertical slider. a slider can have a fixed slider handle (one end of the
 * slider is fixed to the left or bottom side of the controller), or a flexible slider handle (a
 * handle you can drag).
 * 
 * 
 * @example controllers/skatoloslider
 */
public class Slider extends Controller<Slider> {

	public final static int FIX = 1;

	public final static int FLEXIBLE = 0;

	protected int _mySliderMode = FIX;

	protected float _myValuePosition;

	protected int _myHandleSize = 0;

	protected int _myDefaultHandleSize = 10;

	protected int triggerId = PRESSED;

	protected ArrayList<TickMark> _myTickMarks = new ArrayList<TickMark>();

	protected boolean isShowTickMarks;

	protected boolean isSnapToTickMarks;

	public static int autoWidth = 99;

	public static int autoHeight = 9;

	protected float scrollSensitivity = 0.1f;

	protected int _myColorTickMark = 0xffffffff;

	private SliderView _myView;

	protected float _myMinReal = 0;

	protected float _myMaxReal = 1;

	protected float _myInternalValue = 0;

	/**
	 * Convenience constructor to extend Slider.
	 * 
	 * @example use/skatoloextendController
	 * @param theskatolo
	 * @param theName
	 */
	public Slider(Skatolo theskatolo, String theName) {
		this(theskatolo, theskatolo.getDefaultTab(), theName, 0, 100, 0, 0, 0, autoWidth, autoHeight);
		theskatolo.register(theskatolo.getObjectForIntrospection(), theName, this);
	}

	/**
	 * 
	 * @example skatoloslider
	 * 
	 * @param theSkatolo skatolo
	 * @param theParent ControllerGroup
	 * @param theName String
	 * @param theMin float
	 * @param theMax float
	 * @param theDefaultValue float
	 * @param theX int
	 * @param theY int
	 * @param theWidth int
	 * @param theHeight int
	 */
	public Slider(Skatolo theskatolo, ControllerGroup<?> theParent, String theName, float theMin, float theMax, float theDefaultValue, int theX, int theY, int theWidth, int theHeight) {
		super(theskatolo, theParent, theName, theX, theY, theWidth, theHeight);

		_myMin = 0;
		_myMax = 1;

		// with _myMinReal and _myMaxReal the range of values can now range 
		// from big to small (e.g. 255 to 0) as well as from small to big (e.g. 0 to 255)
		_myMinReal = theMin;
		_myMaxReal = theMax;

		_myValue = PApplet.map(theDefaultValue, _myMinReal, _myMaxReal, 0, 1);

		_myCaptionLabel = new Label(skatolo, theName).setColor(color.getCaptionLabel());
		_myValueLabel = new Label(skatolo, "" + getValue()).setColor(color.getValueLabel());
		setSliderMode(FIX);

	}

	@Override public void init() {
		// need to override init here since _myValue will only be a 
		// normalized value here but _myDefaultValue needs to be absolute.
		// by normalizing _myValue the range of values can be from 'big-to-small'
		// as well as from 'small-to-big'
		// in order not to break anything, init() will be overwritten here.

		_myDefaultValue = getValue();
		skatolo.getControlBroadcaster().plug(skatolo.getObjectForIntrospection(), this, name);
		initControllerValue();
		isInit = skatolo.isAutoInitialization;
		setValue(_myDefaultValue);
		isInit = true;
		updateDisplayMode(DEFAULT);
		
	}

	/**
	 * use the slider mode to set the mode of the slider bar, which can be Slider.FLEXIBLE or
	 * Slider.FIX
	 * 
	 * @param theMode int
	 */
	public Slider setSliderMode(int theMode) {
		_myView = (width > height) ? new SliderViewH() : new SliderViewV();
		_myControllerView = (width > height) ? new SliderViewH() : new SliderViewV();
		_mySliderMode = theMode;
		if (_mySliderMode == FLEXIBLE) {
			_myHandleSize = (_myDefaultHandleSize >= getHeight() / 2) ? _myDefaultHandleSize / 2 : _myDefaultHandleSize;
		} else {
			_myHandleSize = 0;
		}
		_myView.updateUnit();
		setValue(PApplet.map(_myValue, 0, 1, _myMinReal, _myMaxReal));
		return this;
	}

	public int getSliderMode() {
		return _mySliderMode;
	}

	/**
	 * sets the size of the Slider handle, by default it is set to either the width or height of the
	 * slider.
	 * 
	 * @param theSize
	 */
	public Slider setHandleSize(int theSize) {
		_myDefaultHandleSize = theSize;
		setSliderMode(_mySliderMode);
		return this;
	}

	public int getHandleSize() {
		return _myHandleSize;
	}

	/**
	 * @see ControllerInterface.updateInternalEvents
	 * 
	 */
	public Slider updateInternalEvents(PApplet theApplet) {
		if (isVisible) {
			if (isMousePressed() && !skatolo.isAltDown()) {
				_myView.updateInternalEvents(theApplet);
			}
		}
		return this;
	}

	/**
	 * the trigger event is set to Slider.PRESSED by default but can be changed to Slider.RELEASE so
	 * that events are triggered when the slider is released.
	 * 
	 * @param theEventID
	 */
	public Slider setTriggerEvent(int theEventID) {
		triggerId = theEventID;
		return this;
	}

	/**
	 * returns the current trigger event which is either Slider.PRESSED or Slider.RELEASE
	 * 
	 * @return int
	 */
	public int getTriggerEvent() {
		return triggerId;
	}

	@Override protected void mouseReleasedOutside() {
		mouseReleased();
	}

	@Override protected void mouseReleased() {
		if (triggerId == RELEASE) {
			_myView.update();
			broadcast(FLOAT);
		}
	}

	protected Slider snapValue(float theValue) {
		if (isSnapToTickMarks) {
			_myValuePosition = ((_myValue - _myMin) / _myUnit);
			_myView.setSnapValue();
		}
		return this;
	}

	public float getValuePosition() {
		return _myValuePosition;
	}

	/**
	 * set the value of the slider.
	 * 
	 * @param theValue float
	 */
	@Override public Slider setValue(float theValue) {
		if (isMousePressed() && theValue == getValue()) {
			return this;
		}
		_myInternalValue = theValue;
		_myValue = PApplet.map(theValue, _myMinReal, _myMaxReal, 0, 1);
		snapValue(_myValue);
		_myValue = (_myValue <= _myMin) ? _myMin : _myValue;
		_myValue = (_myValue >= _myMax) ? _myMax : _myValue;
		_myValuePosition = ((_myValue - _myMin) / _myUnit);
		_myValueLabel.set(adjustValue(getValue()));
		if (triggerId == PRESSED) {
			broadcast(FLOAT);
		}
		return this;
	}

	@Override public float getValue() {
		return PApplet.map(_myValue, 0, 1, _myMinReal, _myMaxReal);
	}

	/**
	 * assigns a random value to the slider.
	 */
	public Slider shuffle() {
		float r = (float) Math.random();
		setValue(PApplet.map(r, 0, 1, _myMinReal, _myMaxReal));
		return this;
	}

	/**
	 * sets the sensitivity for the scroll behavior when using the mouse wheel or the scroll
	 * function of a multi-touch track pad. The smaller the value (closer to 0) the higher the
	 * sensitivity. by default this value is set to 0.1
	 * 
	 * @param theValue
	 * @return Slider
	 */
	public Slider setScrollSensitivity(float theValue) {
		scrollSensitivity = theValue;
		return this;
	}

	/**
	 * changes the value of the slider when hovering and using the mouse wheel or the scroll
	 * function of a multi-touch track pad.
	 * 
	 * @param theRotationValue
	 * @return Slider
	 */
	public Slider scrolled(int theRotationValue) {
		if (isVisible) {
			float f = _myValue;
			float steps = isSnapToTickMarks ? (1.0f / getNumberOfTickMarks()) : scrollSensitivity * 0.1f;
			f += (_myMax - _myMin) * (-theRotationValue * steps);
			setValue(PApplet.map(f, 0, 1, _myMinReal, _myMaxReal));
			if (triggerId == RELEASE) {
				broadcast(FLOAT);
			}
		}
		return this;
	}

	@Override public Slider update() {
		return setValue(PApplet.map(_myValue, 0, 1, _myMinReal, _myMaxReal));
	}

	/**
	 * sets the minimum value of the slider.
	 * 
	 * @param theValue float
	 */
	@Override public Slider setMin(float theValue) {
		float f = getValue();
		_myMinReal = theValue;
		_myValue = PApplet.map(f, _myMinReal, _myMaxReal, 0, 1);
		setSliderMode(_mySliderMode);
		return this;
	}

	/**
	 * set the maximum value of the slider.
	 * 
	 * @param theValue float
	 */
	@Override public Slider setMax(float theValue) {
		float f = getValue();
		_myMaxReal = theValue;
		_myValue = PApplet.map(f, _myMinReal, _myMaxReal, 0, 1);
		setSliderMode(_mySliderMode);
		return this;
	}

	@Override public float getMin() {
		return _myMinReal;
	}

	@Override public float getMax() {
		return _myMaxReal;
	}

	public Slider setRange(float theMin, float theMax) {
		float f = _myInternalValue;
		_myMinReal = theMin;
		_myMaxReal = theMax;
		_myValue = PApplet.map(f, _myMinReal, _myMaxReal, 0, 1);
		setSliderMode(_mySliderMode);
		return this;
	}

	/**
	 * set the width of the slider.
	 * 
	 * @param theValue int
	 */
	@Override public Slider setWidth(int theValue) {
		width = theValue;
		setSliderMode(_mySliderMode);
		return this;
	}

	/**
	 * set the height of the slider.
	 * 
	 * @param theValue int
	 */
	@Override public Slider setHeight(int theValue) {
		height = theValue;
		setSliderMode(_mySliderMode);
		return this;
	}

	@Override public Slider setSize(int theWidth, int theHeight) {
		setWidth(theWidth);
		setHeight(theHeight);
		_myView = (width > height) ? new SliderViewH() : new SliderViewV();
		return this;
	}
	
		
	/*
	 * TODO new implementations follow: http://www.ibm.com/developerworks/java/library/j-dynui/ take
	 * interface builder as reference
	 */
	protected Slider setTickMarks() {
		return this;
	}

	/**
	 * sets the number of tickmarks for a slider, by default tick marks are turned off.
	 * 
	 * @param theNumber
	 */
	public Slider setNumberOfTickMarks(int theNumber) {
		_myTickMarks.clear();
		if (theNumber > 0) {
			for (int i = 0; i < theNumber; i++) {
				_myTickMarks.add(new TickMark(this));
			}
			showTickMarks(true);
			snapToTickMarks(true);
			setHandleSize(20);
		} else {
			showTickMarks(false);
			snapToTickMarks(false);
			setHandleSize(_myDefaultHandleSize);
		}
		setValue(PApplet.map(_myValue, 0, 1, _myMinReal, _myMaxReal));
		return this;
	}

	/**
	 * returns the amount of tickmarks available for a slider
	 * 
	 * @return int
	 */
	public int getNumberOfTickMarks() {
		return _myTickMarks.size();
	}

	/**
	 * shows or hides tickmarks for a slider
	 * 
	 * @param theFlag
	 * @return Slider
	 */
	public Slider showTickMarks(boolean theFlag) {
		isShowTickMarks = theFlag;
		return this;
	}

	/**
	 * enables or disables snap to tick marks.
	 * 
	 * @param theFlag
	 * @return Slider
	 */
	public Slider snapToTickMarks(boolean theFlag) {
		isSnapToTickMarks = theFlag;
		return this;
	}

	/**
	 * returns an instance of a tickmark by index.
	 * 
	 * @see TickMark
	 * @param theIndex
	 * @return
	 */
	public TickMark getTickMark(int theIndex) {
		if (theIndex >= 0 && theIndex < _myTickMarks.size()) {
			return _myTickMarks.get(theIndex);
		} else {
			return null;
		}
	}

	/**
	 * returns an ArrayList of available tick marks for a slider.
	 * 
	 * @return ArrayList<TickMark>
	 */
	public ArrayList<TickMark> getTickMarks() {
		return _myTickMarks;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override public Slider linebreak() {
		skatolo.linebreak(this, true, autoWidth, autoHeight, autoSpacing);
		return this;
	}

	/**
	 * sets the color of tick marks if enabled. by default the color is set to white.
	 * 
	 * @param theColor
	 * @return Slider
	 */
	public Slider setColorTickMark(int theColor) {
		_myColorTickMark = theColor;
		return this;
	}

	public int getDirection() {
		return (_myView instanceof SliderViewH) ? HORIZONTAL : VERTICAL;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override public Slider updateDisplayMode(int theMode) {
		_myDisplayMode = theMode;
		switch (theMode) {
		case (DEFAULT):
			_myControllerView = (width > height) ? new SliderViewH() : new SliderViewV();
			break;
		case (IMAGE):
			// TODO
			break;
		case (SPRITE):
			// TODO
			break;
		case (CUSTOM):
		default:
			break;
		}
		return this;
	}

	private abstract class SliderView implements ControllerView<Slider> {

		abstract void updateInternalEvents(PApplet theApplet);

		abstract void update();

		abstract void updateUnit();

		abstract void setSnapValue();

	}

	private class SliderViewV extends SliderView {

		SliderViewV() {
			_myCaptionLabel.align(LEFT, BOTTOM_OUTSIDE).setPadding(0, Label.defaultPaddingY);
			_myValueLabel.set("" + adjustValue(getValue())).align(RIGHT_OUTSIDE, TOP);
		}

		void setSnapValue() {
			float n = PApplet.round(PApplet.map(_myValuePosition, 0, getHeight(), 0, _myTickMarks.size() - 1));
			_myValue = PApplet.map(n, 0, _myTickMarks.size() - 1, _myMin, _myMax);
		}

		void updateUnit() {
			_myUnit = (_myMax - _myMin) / (height - _myHandleSize);
		}

		void update() {
			float f = _myMin + (-(controlWindow.getPointerY() - (_myParent.getAbsolutePosition().y + position.y) - height)) * _myUnit;
			setValue(PApplet.map(f, 0, 1, _myMinReal, _myMaxReal));
		}

		void updateInternalEvents(PApplet theApplet) {
			float f = _myMin + (-(controlWindow.getPointerY() - (_myParent.getAbsolutePosition().y + position.y) - height)) * _myUnit;
			setValue(PApplet.map(f, 0, 1, _myMinReal, _myMaxReal));
		}

		public void display(PGraphics graphics, Slider theController) {
			graphics.fill(getColor().getBackground());
			graphics.noStroke();
			if ((getColor().getBackground() >> 24 & 0xff) > 0) {
				graphics.rect(0, 0, getWidth(), getHeight());
			}
			graphics.fill(getMouseOver() ? getColor().getActive() : getColor().getForeground());
			if (getSliderMode() == FIX) {
				graphics.rect(0, getHeight(), getWidth(), -getValuePosition());
			} else {
				if (isShowTickMarks) {
					graphics.triangle(getWidth(), getHeight() - getValuePosition(), getWidth(), getHeight() - getValuePosition() - getHandleSize(), 0, getHeight() - getValuePosition()
							- getHandleSize() / 2);
				} else {
					graphics.rect(0, getHeight() - getValuePosition() - getHandleSize(), getWidth(), getHandleSize());
				}
			}

			if (isLabelVisible) {
				getCaptionLabel().draw(graphics, 0, 0, theController);
				graphics.pushMatrix();
				graphics.translate(0, (int) PApplet.map(_myValue, _myMax, _myMin, 0, getHeight() - _myValueLabel.getHeight()));
				getValueLabel().draw(graphics, 0, 0, theController);
				graphics.popMatrix();
			}

			if (isShowTickMarks) {
				graphics.pushMatrix();
				graphics.pushStyle();
				graphics.translate(-4, (getSliderMode() == FIX) ? 0 : getHandleSize() / 2);
				graphics.fill(_myColorTickMark);
				float x = (getHeight() - ((getSliderMode() == FIX) ? 0 : getHandleSize())) / (getTickMarks().size() - 1);
				for (TickMark tm : getTickMarks()) {
					tm.draw(graphics, getDirection());
					graphics.translate(0, x);
				}
				graphics.popStyle();
				graphics.popMatrix();
			}
		}
	}

	private class SliderViewH extends SliderView {

		SliderViewH() {
			_myCaptionLabel.align(RIGHT_OUTSIDE, CENTER);
			_myValueLabel.set("" + adjustValue(getValue())).align(LEFT, CENTER);
		}

		void setSnapValue() {
			float n = PApplet.round(PApplet.map(_myValuePosition, 0, getWidth(), 0, _myTickMarks.size() - 1));
			_myValue = PApplet.map(n, 0, _myTickMarks.size() - 1, _myMin, _myMax);
		}

		void updateUnit() {
			_myUnit = (_myMax - _myMin) / (width - _myHandleSize);
		}

		void update() {
			float f = _myMin + (controlWindow.getPointerX()- (_myParent.getAbsolutePosition().x + position.x)) * _myUnit;
			setValue(PApplet.map(f, 0, 1, _myMinReal, _myMaxReal));
		}

		void updateInternalEvents(PApplet theApplet) {
			float f = _myMin + (controlWindow.getPointerX() - (_myParent.getAbsolutePosition().x + position.x)) * _myUnit;
			setValue(PApplet.map(f, 0, 1, _myMinReal, _myMaxReal));
		}

		public void display(PGraphics graphics, Slider theController) {
			graphics.fill(getColor().getBackground());
			graphics.noStroke();
			if ((getColor().getBackground() >> 24 & 0xff) > 0) {
				graphics.rect(0, 0, getWidth(), getHeight());
			}
			graphics.fill(getMouseOver() ? getColor().getActive() : getColor().getForeground());
			if (getSliderMode() == FIX) {
				graphics.rect(0, 0, getValuePosition(), getHeight());
			} else {
				if (isShowTickMarks) {
					graphics.triangle(getValuePosition(), 0, getValuePosition() + getHandleSize(), 0, getValuePosition() + _myHandleSize / 2, getHeight());
				} else {
					graphics.rect(getValuePosition(), 0, getHandleSize(), getHeight());
				}

			}
			graphics.fill(255);

			if (isLabelVisible) {
				getValueLabel().draw(graphics, 0, 0, theController);
				getCaptionLabel().draw(graphics, 0, 0, theController);
			}

			if (isShowTickMarks) {
				graphics.pushMatrix();
				graphics.pushStyle();
				graphics.translate((getSliderMode() == FIX) ? 0 : getHandleSize() / 2, getHeight());
				graphics.fill(_myColorTickMark);
				graphics.noStroke();
				float x = (getWidth() - ((getSliderMode() == FIX) ? 0 : getHandleSize())) / (getTickMarks().size() - 1);
				for (TickMark tm : getTickMarks()) {
					tm.draw(graphics, getDirection());
					graphics.translate(x, 0);
				}
				graphics.popStyle();
				graphics.popMatrix();
			}
		}
	}

	@Deprecated public void setSliderBarSize(int theSize) {
		_myDefaultHandleSize = theSize;
		setSliderMode(_mySliderMode);
	}

	/**
	 * @see skatolo.Slider#setScrollSensitivity(float)
	 * 
	 * @param theValue
	 * @return Slider
	 */
	@Deprecated public Slider setSensitivity(float theValue) {
		return setScrollSensitivity(theValue);
	}

}
