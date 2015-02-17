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
package controlP5;

import processing.core.PApplet;
import processing.core.PGraphics;

/**
 * <p>
 * A button triggers an event after it has been release. Events can be linked to functions and
 * fields inside your program/sketch. for a full documentation of this controller see the
 * {@link Controller} class.
 * </p>
 * 
 * @example controllers/ControlP5button
 */

public class Button extends Controller<Button> {

	protected boolean isPressed;

	protected boolean isOn = false;

	public static int autoWidth = 69;

	public static int autoHeight = 19;

	protected int activateBy = RELEASE;

	protected boolean isSwitch = false;


	/**
	 * Convenience constructor to extend Button.
	 * 
	 * @example use/ControlP5extendController
	 * @param theControlP5
	 * @param theName
	 */
	public Button(ControlP5 theControlP5, String theName) {
		this(theControlP5, theControlP5.getDefaultTab(), theName, 0, 0, 0, autoWidth, autoHeight);
		theControlP5.register(theControlP5.papplet, theName, this);
	}


	protected Button(ControlP5 theControlP5, ControllerGroup<?> theParent, String theName, float theDefaultValue, int theX, int theY, int theWidth, int theHeight) {
		super(theControlP5, theParent, theName, theX, theY, theWidth, theHeight);
		_myValue = theDefaultValue;
		_myCaptionLabel.align(LEFT, CENTER);
	}


	/**
	 * @exclude
	 */
	public Button() {
		super(null, null, null, 0, 0, 1, 1);
	}


	@Override protected void onEnter() {
		isActive = true;
	}


	@Override protected void onLeave() {
		isActive = false;
	}


	/**
	 * @exclude
	 */
	@Override @ControlP5.Invisible public void mousePressed() {
		isActive = getIsInside();
		isPressed = true;
		if (activateBy == PRESSED) {
			activate();
		}
	}


	/**
	 * @exclude
	 */
	@Override @ControlP5.Invisible public void mouseReleased() {
		isPressed = false;
		if (activateBy == RELEASE) {
			activate();
		}
		isActive = false;
	}


	/**
	 * A button can be activated by a mouse PRESSED or mouse RELEASE. Default value is RELEASE.
	 * 
	 * @param theValue use ControlP5.PRESSED or ControlP5.RELEASE as parameter
	 * @return Button
	 */
	public Button activateBy(int theValue) {
		if (theValue == PRESSED) {
			activateBy = PRESSED;
		}
		else {
			activateBy = RELEASE;
		}
		return this;
	}


	protected void activate() {
		if (isActive) {
			isActive = false;
			if (getParent() instanceof Tab) {
				setIsInside(false);
			}
			isOn = !isOn;
			setValue(_myValue);

		}
	}


	/**
	 * @exclude
	 */
	@Override @ControlP5.Invisible public void mouseReleasedOutside() {
		mouseReleased();
	}


	/**
	 * {@inheritDoc}
	 */
	@Override public Button setValue(float theValue) {
		_myValue = theValue;
		broadcast(FLOAT);
		return this;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override public Button update() {
		return setValue(_myValue);
	}


	/**
	 * Turns a button into a switch, or use a Toggle instead.
	 * 
	 * @see controlP5.Toggle
	 * @param theFlag turns the button into a switch when true
	 * @return Button
	 */
	public Button setSwitch(boolean theFlag) {
		isSwitch = theFlag;
		return this;
	}


	/**
	 * If the button acts as a switch, setOn will turn on the switch. Use
	 * {@link controlP5.Button#setSwitch(boolean) setSwitch} to turn a Button into a Switch.
	 * 
	 * @return Button
	 */
	public Button setOn() {
		if (isSwitch) {
			isOn = false;
			isActive = true;
			activate();
		}
		return this;
	}


	/**
	 * If the button acts as a switch, setOff will turn off the switch. Use
	 * {@link controlP5.Button#setSwitch(boolean) setSwitch} to turn a Button into a Switch.
	 * 
	 * @return Button
	 */
	public Button setOff() {
		if (isSwitch) {
			isOn = true;
			isActive = true;
			activate();
		}
		return this;
	}


	/**
	 * @return boolean
	 */
	public boolean isOn() {
		return isOn;
	}


	/**
	 * @return boolean
	 */
	public boolean isPressed() {
		return isPressed;
	}


	/**
	 * Returns true or false and indicates the switch state of the button. {@link setSwitch(boolean)
	 * setSwitch} should have been set before.
	 * 
	 * @see controlP5.Button#setSwitch(boolean)
	 * @return boolean
	 */
	public boolean getBooleanValue() {
		return isOn;
	}


	/**
	 * @exclude
	 */
	@Override @ControlP5.Invisible public Button updateDisplayMode(int theMode) {
		return updateViewMode(theMode);
	}


	/**
	 * @exclude
	 */
	@ControlP5.Invisible public Button updateViewMode(int theMode) {
		_myDisplayMode = theMode;
		switch (theMode) {
		case (DEFAULT):
			_myControllerView = new ButtonView();
			break;
		case (IMAGE):
			_myControllerView = new ButtonImageView();
			break;
		case (CUSTOM):
		default:
			break;

		}
		return this;
	}


	private class ButtonView implements ControllerView<Button> {

		public void display(PApplet theApplet, Button theController) {
			theApplet.noStroke();
			if (isOn && isSwitch) {
				theApplet.fill(color.getActive());
			}
			else {
				if (getIsInside()) {
					if (isPressed) {
						theApplet.fill(color.getActive());
					}
					else {
						theApplet.fill(color.getForeground());
					}
				}
				else {
					theApplet.fill(color.getBackground());
				}
			}
			theApplet.rect(0, 0, width, height);
			if (isLabelVisible) {
				_myCaptionLabel.draw(theApplet, 0, 0, theController);
			}
		}
                
		public void display(PGraphics graphics, Button theController) {
			graphics.noStroke();
			if (isOn && isSwitch) {
				graphics.fill(color.getActive());
			}
			else {
				if (getIsInside()) {
					if (isPressed) {
						graphics.fill(color.getActive());
					}
					else {
						graphics.fill(color.getForeground());
					}
				}
				else {
					graphics.fill(color.getBackground());
				}
			}
			graphics.rect(0, 0, width, height);
			if (isLabelVisible) {
				_myCaptionLabel.draw(graphics, 0, 0, theController);
			}
		}
	}

	private class ButtonImageView implements ControllerView<Button> {

		public void display(PApplet theApplet, Button theController) {
			if (isOn && isSwitch) {
				theApplet.image((availableImages[HIGHLIGHT] == true) ? images[HIGHLIGHT] : images[DEFAULT], 0, 0);
				return;
			}
			if (getIsInside()) {
				if (isPressed) {
					theApplet.image((availableImages[ACTIVE] == true) ? images[ACTIVE] : images[DEFAULT], 0, 0);
				}
				else {
					theApplet.image((availableImages[OVER] == true) ? images[OVER] : images[DEFAULT], 0, 0);
				}
			}
			else {
				theApplet.image(images[DEFAULT], 0, 0);
			}
		}
                
		public void display(PGraphics graphics, Button theController) {
			if (isOn && isSwitch) {
				graphics.image((availableImages[HIGHLIGHT] == true) ? images[HIGHLIGHT] : images[DEFAULT], 0, 0);
				return;
			}
			if (getIsInside()) {
				if (isPressed) {
					graphics.image((availableImages[ACTIVE] == true) ? images[ACTIVE] : images[DEFAULT], 0, 0);
				}
				else {
					graphics.image((availableImages[OVER] == true) ? images[OVER] : images[DEFAULT], 0, 0);
				}
			}
			else {
				graphics.image(images[DEFAULT], 0, 0);
			}
		}
	}


	/**
	 * @exclude
	 */
	@Override public String getInfo() {
		return "type:\tButton\n" + super.getInfo();
	}


	/**
	 * @exclude
	 */
	@Override public String toString() {
		return super.toString() + " [ " + getValue() + " ] " + "Button" + " (" + this.getClass().getSuperclass() + ")";
	}


	/**
	 * @deprecated
	 * @exclude
	 */
	@Deprecated public boolean booleanValue() {
		return isOn;
	}
}
