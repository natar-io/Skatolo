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
package tech.lity.rea.skatolo.gui.widgets;

import tech.lity.rea.skatolo.gui.controllers.*;
import tech.lity.rea.skatolo.Skatolo;
import static tech.lity.rea.skatolo.SkatoloConstants.BACKSPACE;
import static tech.lity.rea.skatolo.SkatoloConstants.DEFAULT;
import static tech.lity.rea.skatolo.SkatoloConstants.DELETE;
import static tech.lity.rea.skatolo.SkatoloConstants.DOWN;
import static tech.lity.rea.skatolo.SkatoloConstants.ENTER;
import static tech.lity.rea.skatolo.SkatoloConstants.LEFT;
import static tech.lity.rea.skatolo.SkatoloConstants.RIGHT;
import static tech.lity.rea.skatolo.SkatoloConstants.UP;
import tech.lity.rea.skatolo.gui.Controller;
import tech.lity.rea.skatolo.gui.group.ControllerGroup;
import tech.lity.rea.skatolo.gui.Label;
import java.util.HashMap;
import java.util.Map;
import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PVector;
import processing.event.KeyEvent;
import static tech.lity.rea.skatolo.SkatoloConstants.ARRAY;

/**
 * <p>
 * The PixelSelect controller can be dragged to select a location. A PixelSelect
 * extends superclass Controller, for a full documentation see the
 * {@link Controller} reference.
 * </p>
 *
 * @example controllers/pixelSelect
 */
@Skatolo.Layout
public class PixelSelect extends Controller<PixelSelect> {

    protected int triggerId = PRESSED;
    public static int autoWidth = 20;
    public static int autoHeight = 20;

    protected Map<Integer, PixelSelect.PixelSelectCommand> keyMapping;

    /**
     * Convenience constructor to extend PixelSelect.
     *
     * @example use/skatoloextendController
     * @param theskatolo
     * @param theName
     */
    public PixelSelect(Skatolo theskatolo, String theName) {
        this(theskatolo, theskatolo.getDefaultTab(), theName, 0, 0, 20, 20);
        theskatolo.register(theskatolo.getObjectForIntrospection(), theName, this);
    }

    public PixelSelect(Skatolo theskatolo, ControllerGroup<?> theParent, String theName, float theX, float theY, int theWidth, int theHeight) {
        super(theskatolo, theParent, theName, theX, theY, theWidth, theHeight);
        _myCaptionLabel.setPadding(0, Label.defaultPaddingY).align(LEFT, BOTTOM_OUTSIDE);
        _myArrayValue = new float[]{0.0f, 0.0f};

        keyMapping = new HashMap<Integer, PixelSelect.PixelSelectCommand>();
        keyMapping.put(LEFT, new PixelSelect.MoveLeft());
        keyMapping.put(RIGHT, new PixelSelect.MoveRight());
        keyMapping.put(UP, new PixelSelect.MoveUp());
        keyMapping.put(DOWN, new PixelSelect.MoveDown());

    }

    @Override
    protected void onEnter() {
        isActive = true;
    }

    @Override
    public void onLeave() {
        isActive = false;
    }

    @Override
    protected void mousePressed() {
        this._myArrayValue[0] = this.currentPointer.getX();
        this._myArrayValue[1] = this.currentPointer.getY();
        isActive = true;
        update();
    }

    @Override
    protected void onDrag() {
        isActive = true;
//        this.setPosition(this.currentPointer.getX(), this.currentPointer.getY());

        this._myArrayValue[0] = this.currentPointer.getX();
        this._myArrayValue[1] = this.currentPointer.getY();
        update();
    }

    protected boolean isKeyboardControlled = false;

    public void setKeyboardControlled(boolean control) {
        isKeyboardControlled = control;
    }

    @Override
    protected void mouseReleased() {
        isActive = true;
        update();
    }

    @Override
    protected void mouseReleasedOutside() {
        onLeave();
    }

    /**
     * By default a bang is triggered when the mouse is pressed. use
     * setTriggerEvent(PixelSelect.PRESSED) or
     * setTriggerEvent(PixelSelect.RELEASE) to define the action for triggering
     * a bang. currently only PixelSelect.PRESSED and PixelSelect.RELEASE are
     * supported.
     *
     * @param theEventID
     * @return PixelSelect
     */
    @Skatolo.Layout
    public PixelSelect setTriggerEvent(int theEventID) {
        triggerId = theEventID;
        return this;
    }

    @Skatolo.Layout
    public int getTriggerEvent() {
        return triggerId;
    }

    /**
     * Sets the value of the controller.
     *
     * @param theValue float
     * @return PixelSelect
     */
    @Override
    public PixelSelect setArrayValue(float[] theValue) {
        _myArrayValue = theValue;
        this.setPosition(theValue[0] - width / 2, theValue[1] - height / 2);
        broadcast(ARRAY);
        return this;
    }

    /**
     * @exclude
     */
    @Override
    public PixelSelect update() {
        return setArrayValue(_myArrayValue);
    }

    /**
     * @exclude
     */
    @Override
    public PixelSelect updateDisplayMode(int theMode) {
        updateViewMode(theMode);
        return this;
    }

    /**
     * @exclude
     */
    public PixelSelect updateViewMode(int theMode) {
        _myDisplayMode = theMode;
        switch (theMode) {
            case (DEFAULT):
                _myControllerView = new PixelSelectView();
                break;
            case (IMAGE):
                _myControllerView = new PixelSelectImageView();
                break;
            case (CUSTOM):
            default:
                break;
        }
        return this;
    }

    interface PixelSelectCommand {

        void execute();
    }

    class MoveLeft implements PixelSelect.PixelSelectCommand {

        public void execute() {
            setPosition(getPosition().x - 1, getPosition().y);
            _myArrayValue[0] = _myArrayValue[0] - 1;
            broadcast(ARRAY);
        }
    }

    class MoveRight implements PixelSelect.PixelSelectCommand {

        public void execute() {
            setPosition(getPosition().x + 1, getPosition().y);
            _myArrayValue[0] = _myArrayValue[0] + 1;
            broadcast(ARRAY);

        }
    }

    class MoveUp implements PixelSelect.PixelSelectCommand {

        public void execute() {
            setPosition(getPosition().x, getPosition().y - 1);
            _myArrayValue[1] = _myArrayValue[1] - 1;
            broadcast(ARRAY);
        }
    }

    class MoveDown implements PixelSelect.PixelSelectCommand {

        public void execute() {
            setPosition(getPosition().x, getPosition().y + 1);
            _myArrayValue[1] = _myArrayValue[1] + 1;
            broadcast(ARRAY);
        }
    }

    public void keyEvent(KeyEvent theKeyEvent) {
        if (isUserInteraction && (isActive || isKeyboardControlled) && theKeyEvent.getAction() == KeyEvent.PRESS) {
            if (keyMapping.containsKey(skatolo.getKeyCode())) {
                keyMapping.get(skatolo.getKeyCode()).execute();
            }
        }
    }

    private class PixelSelectView implements ControllerView<PixelSelect> {

        public void display(PGraphics graphics, PixelSelect theController) {

            graphics.noFill();
            graphics.strokeWeight(1);
            if (isActive) {
                graphics.stroke(color.getActive());
            } else {
                graphics.stroke(color.getForeground());
            }

            if (isKeyboardControlled) {
                graphics.strokeWeight(2);
                graphics.stroke(color.getActive());
            }
            graphics.rect(0, 0, width, height);

            graphics.strokeWeight(1);
            graphics.ellipse(width / 4, height / 4, width / 2, height / 2);
            if (isLabelVisible) {
                _myCaptionLabel.draw(graphics, 0, 0, theController);
            }
        }
    }

    private class PixelSelectImageView implements ControllerView<PixelSelect> {

        public void display(PGraphics graphics, PixelSelect theController) {
            if (isActive) {
                graphics.image((availableImages[ACTIVE] == true) ? images[ACTIVE] : images[DEFAULT], 0, 0);
            } else {
                graphics.image((availableImages[OVER] == true) ? images[OVER] : images[DEFAULT], 0, 0);
            }
            if (!isActive) {
                graphics.image(images[DEFAULT], 0, 0);
            }
        }
    }

    /**
     * {@inheritDoc}
     *
     * @exclude
     */
    @Override
    public String getInfo() {
        return "type:\tPixelSelect\n" + super.getInfo();
    }

    /**
     * @exclude {@inheritDoc}
     */
    @Override
    public String toString() {
        return super.toString() + " [ " + getValue() + " ] " + "PixelSelect" + " (" + this.getClass().getSuperclass() + ")";
    }

}
