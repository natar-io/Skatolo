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
public class HoverButton extends Controller<HoverButton> {

    public static int autoWidth = 69;
    public static int autoHeight = 19;

    protected int activateBy = ACTION_ENTER;

    protected boolean isOn = false;
    protected boolean isSwitch = false;

    /**
     * Convenience constructor to extend Button.
     *
     * @example use/skatoloextendController
     * @param theskatolo
     * @param theName
     */
    public HoverButton(Skatolo theskatolo, String theName) {
        this(theskatolo, theskatolo.getDefaultTab(), theName, 0, 0, 0, autoWidth, autoHeight);
        theskatolo.register(theskatolo.getObjectForIntrospection(), theName, this);
    }

    public HoverButton(Skatolo theskatolo, ControllerGroup<?> theParent, String theName, float theDefaultValue, int theX, int theY, int theWidth, int theHeight) {
        super(theskatolo, theParent, theName, theX, theY, theWidth, theHeight);
        _myValue = theDefaultValue;
        _myCaptionLabel.align(LEFT, CENTER);
    }

    /**
     * @exclude
     */
    public HoverButton() {
        super(null, null, null, 0, 0, 1, 1);
    }

    /**
     * A hover button can be activated by a pointer ENTER or LEAVE. Default
     * value is ACTION_ENTER.
     *
     * @param theValue use skatolo.ACTION_ENTER or skatolo.ACTION_LEAVE as
     * parameter
     * @return Button
     */
    public HoverButton activateBy(int theValue) {
        if (theValue == ACTION_ENTER) {
            activateBy = ACTION_ENTER;
        } else {
            activateBy = ACTION_LEAVE;
        }
        return this;
    }

    @Override
    protected void onEnter() {
        isActive = true;

        if (activateBy == ACTION_ENTER) {
            activate();
        }
    }

    @Override
    public void onLeave() {
        isActive = false;
        if (activateBy == ACTION_LEAVE) {
            activate();
        }
    }

    /**
     * @exclude
     */
    @Override
    public void mousePressed() {
        System.out.println("MousePressed/Released is not working with Hover Button.");
    }

    /**
     * @exclude
     */
    @Override
    public void mouseReleased() {
    }

    protected void activate() {
//        isActive = false;
        if (getParent() instanceof Tab) {
            setPointerOver(false);
        }
        isOn = !isOn;
        // This triggers the events ?
        setValue(_myValue);
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
    public HoverButton setValue(float theValue) {
        _myValue = theValue;
        broadcast(FLOAT);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HoverButton update() {
        return setValue(_myValue);
    }

    /**
     * Turns a button into a switch, or use a Toggle instead.
     *
     * @see skatolo.Toggle
     * @param theFlag turns the button into a switch when true
     * @return Button
     */
    public HoverButton setSwitch(boolean theFlag) {
        isSwitch = theFlag;
        return this;
    }

    /**
     * If the button acts as a switch, setOn will turn on the switch. Use
     * {@link skatolo.Button#setSwitch(boolean) setSwitch} to turn a Button into
     * a Switch.
     *
     * @return Button
     */
    public HoverButton setOn() {
        if (isSwitch) {
            isOn = false;
            isActive = true;
            activate();
        }
        return this;
    }

    /**
     * If the button acts as a switch, setOff will turn off the switch. Use
     * {@link skatolo.Button#setSwitch(boolean) setSwitch} to turn a Button into
     * a Switch.
     *
     * @return Button
     */
    public HoverButton setOff() {
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
    public HoverButton updateDisplayMode(int theMode) {
        return updateViewMode(theMode);
    }

    /**
     * @exclude
     */
    public HoverButton updateViewMode(int theMode) {
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

    private class ButtonView implements ControllerView<HoverButton> {

        public void display(PGraphics graphics, HoverButton theController) {
            graphics.noStroke();
            if (isOn && isSwitch) {
                graphics.fill(color.getActive());
            } else {
                if (getMouseOver()) {
                    graphics.fill(color.getActive());
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

    private class ButtonImageView implements ControllerView<HoverButton> {

        public void display(PGraphics graphics, HoverButton theController) {
            if (isOn && isSwitch) {
                graphics.image((availableImages[HIGHLIGHT] == true) ? images[HIGHLIGHT] : images[DEFAULT], 0, 0, width, height);
                return;
            }
            if (getMouseOver()) {
                graphics.image((availableImages[ACTIVE] == true) ? images[ACTIVE] : images[DEFAULT], 0, 0, width, height);
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
