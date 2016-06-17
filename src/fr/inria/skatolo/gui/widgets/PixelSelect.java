/* 
 *  skatolo is a processing gui library.
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
package fr.inria.skatolo.gui.widgets;

import fr.inria.skatolo.gui.controllers.*;
import fr.inria.skatolo.Skatolo;
import static fr.inria.skatolo.SkatoloConstants.BACKSPACE;
import static fr.inria.skatolo.SkatoloConstants.DEFAULT;
import static fr.inria.skatolo.SkatoloConstants.DELETE;
import static fr.inria.skatolo.SkatoloConstants.DOWN;
import static fr.inria.skatolo.SkatoloConstants.ENTER;
import static fr.inria.skatolo.SkatoloConstants.LEFT;
import static fr.inria.skatolo.SkatoloConstants.RIGHT;
import static fr.inria.skatolo.SkatoloConstants.UP;
import fr.inria.skatolo.gui.Controller;
import fr.inria.skatolo.gui.group.ControllerGroup;
import fr.inria.skatolo.gui.Label;
import java.util.HashMap;
import java.util.Map;
import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PVector;
import processing.event.KeyEvent;

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

    protected int cnt;

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
        cnt = 0;
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
        cnt = -3;
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

    @Override
    protected void mouseReleased() {
        cnt = -3;
        isActive = true;

        update();
    }

    @Override
    protected void mouseReleasedOutside() {
//        onLeave();
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
        }
    }

    class MoveRight implements PixelSelect.PixelSelectCommand {

        public void execute() {
            setPosition(getPosition().x + 1, getPosition().y);
        }
    }

    class MoveUp implements PixelSelect.PixelSelectCommand {

        public void execute() {
            setPosition(getPosition().x, getPosition().y - 1);
        }
    }

    class MoveDown implements PixelSelect.PixelSelectCommand {

        public void execute() {
            setPosition(getPosition().x, getPosition().y + 1);
        }
    }

    public void keyEvent(KeyEvent theKeyEvent) {
        if (isUserInteraction && isActive && theKeyEvent.getAction() == KeyEvent.PRESS) {
            if (keyMapping.containsKey(skatolo.getKeyCode())) {
                keyMapping.get(skatolo.getKeyCode()).execute();
            }
        }
    }

    private class PixelSelectView implements ControllerView<PixelSelect> {

        public void display(PGraphics graphics, PixelSelect theController) {

            graphics.noFill();
            graphics.strokeWeight(2);
            if (isActive) {
                graphics.stroke(color.getActive());
                graphics.strokeWeight(2);
            } else {
                graphics.stroke(color.getForeground());
            }

            if (cnt < 0) {
                graphics.stroke(color.getForeground());
                cnt++;
            }
            graphics.rect(0, 0, width, height);
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
