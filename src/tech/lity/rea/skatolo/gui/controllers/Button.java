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
import tech.lity.rea.skatolo.gui.group.Tab;
import processing.core.PApplet;
import processing.core.PGraphics;

/**
 * <p>
 * A button triggers an event after it has been release. Events can be linked to
 * functions and fields inside your program/sketch. for a full documentation of
 * this controller see the {@link Controller} class.
 * </p>
 *
 * @example controllers/skatolobutton
 */
public class Button extends Controller<Button> {

    public static int autoWidth = 69;
    public static int autoHeight = 19;

    protected int activateBy = RELEASE;

    protected boolean isOn = false;
    protected boolean isPressed;
    protected boolean isSwitch = false;

    /**
     * Convenience constructor to extend Button.
     *
     * @example use/skatoloextendController
     * @param theskatolo
     * @param theName
     */
    public Button(Skatolo theskatolo, String theName) {
        this(theskatolo, theskatolo.getDefaultTab(), theName, 0, 0, 0, autoWidth, autoHeight);
        theskatolo.register(theskatolo.getObjectForIntrospection(), theName, this);
    }

    public Button(Skatolo theskatolo, ControllerGroup<?> theParent, String theName, float theDefaultValue, int theX, int theY, int theWidth, int theHeight) {
        super(theskatolo, theParent, theName, theX, theY, theWidth, theHeight);
        _myValue = theDefaultValue;
        _myCaptionLabel.align(LEFT, CENTER);
    }

    /**
     * @exclude
     */
    public Button() {
        super(null, null, null, 0, 0, 1, 1);
    }

    @Override
    protected void onEnter() {
        isActive = true;
    }

    @Override
    public void onLeave() {
        isActive = false;
    }

    /**
     * @exclude
     */
    @Override
    public void mousePressed() {
        isActive = getMouseOver();
        isPressed = true;
        if (activateBy == PRESSED) {
            activate();
        }
    }

    /**
     * @exclude
     */
    @Override
    public void mouseReleased() {
        isPressed = false;
        if (activateBy == RELEASE) {
            activate();
        }
        isActive = false;
    }

    /**
     * A button can be activated by a mouse PRESSED or mouse RELEASE. Default
     * value is RELEASE.
     *
     * @param theValue use skatolo.PRESSED or skatolo.RELEASE as parameter
     * @return Button
     */
    public Button activateBy(int theValue) {
        if (theValue == PRESSED) {
            activateBy = PRESSED;
        } else {
            activateBy = RELEASE;
        }
        return this;
    }

    protected void activate() {
        if (isActive) {
            isActive = false;
            if (getParent() instanceof Tab) {
                setPointerOver(false);
            }
            isOn = !isOn;
            setValue(_myValue);
        }
    }

    /**
     * @exclude
     */
    @Override
    public void mouseReleasedOutside() {
        mouseReleased();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Button setValue(float theValue) {
        _myValue = theValue;
        broadcast(FLOAT);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Button update() {
        return setValue(_myValue);
    }

    /**
     * Turns a button into a switch, or use a Toggle instead.
     *
     * @see skatolo.Toggle
     * @param theFlag turns the button into a switch when true
     * @return Button
     */
    public Button setSwitch(boolean theFlag) {
        isSwitch = theFlag;
        return this;
    }

    /**
     * If the button acts as a switch, setOn will turn on the switch. Use
     * {@link skatolo.Button#setSwitch(boolean) setSwitch} to turn a Button
     * into a Switch.
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
     * {@link skatolo.Button#setSwitch(boolean) setSwitch} to turn a Button
     * into a Switch.
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
     * @see skatolo.Button#setSwitch(boolean)
     * @return boolean
     */
    public boolean getBooleanValue() {
        return isOn;
    }

    /**
     * @exclude
     */
    @Override
    public Button updateDisplayMode(int theMode) {
        return updateViewMode(theMode);
    }

    /**
     * @exclude
     */
    public Button updateViewMode(int theMode) {
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

        public void display(PGraphics graphics, Button theController) {
            graphics.noStroke();
            if (isOn && isSwitch) {
                graphics.fill(color.getActive());
            } else {
                if (getMouseOver()) {
                    if (isPressed) {
                        graphics.fill(color.getActive());
                    } else {
                        graphics.fill(color.getForeground());
                    }
                } else {
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

        public void display(PGraphics graphics, Button theController) {
            if (isOn && isSwitch) {
                graphics.image((availableImages[HIGHLIGHT] == true) ? images[HIGHLIGHT] : images[DEFAULT], 0, 0, width, height);
                return;
            }
            if(getMouseOver()) {
                if (isPressed) {
                    graphics.image((availableImages[ACTIVE] == true) ? images[ACTIVE] : images[DEFAULT], 0, 0, width, height);
                } else {
                    graphics.image((availableImages[OVER] == true) ? images[OVER] : images[DEFAULT], 0, 0, width, height);
                }
            } else {
                graphics.image(images[DEFAULT], 0, 0, width, height);
            }
        }
    }

    /**
     * @exclude
     */
    @Override
    public String getInfo() {
        return "type:\tButton\n" + super.getInfo();
    }

    /**
     * @exclude
     */
    @Override
    public String toString() {
        return super.toString() + " [ " + getValue() + " ] " + "Button" + " (" + this.getClass().getSuperclass() + ")";
    }

    /**
     * @deprecated @exclude
     */
    @Deprecated
    public boolean booleanValue() {
        return isOn;
    }
}
