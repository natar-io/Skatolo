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
import java.util.ArrayList;
import java.util.logging.Level;

import tech.lity.rea.skatolo.gui.Controller;
import tech.lity.rea.skatolo.gui.group.ControllerGroup;
import tech.lity.rea.skatolo.gui.Label;
import tech.lity.rea.skatolo.gui.TickMark;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PVector;

/**
 * A range slider works just like a slider but can be adjusted on both ends.
 *
 * @see Slider
 * @example controllers/skatolorange
 * @nosuperclasses Controller Controller
 */
public class Range extends Controller<Range> {

    /*
     * TODO if range value is int, value labels do initialize as floats. first click makes them
     * display as ints without decimal point
     */
    protected static final int HORIZONTAL = 0;
    protected static final int VERTICAL = 1;

    protected int _myDirection;
    protected float _myValuePosition;

    protected boolean isDragging;
    protected boolean isDraggable = true;

    protected boolean isFirstClick;

    protected Label _myHighValueLabel;

    protected float _myValueRange;

    protected boolean isMinHandle;
    protected boolean isMaxHandle;

    protected int minHandle = 0;
    protected int maxHandle = 0;

    protected boolean isMoveHandle;
    protected float distanceHandle;
    protected int handleSize = 10;

    protected int mr = 0;

    protected final ArrayList<TickMark> _myTickMarks = new ArrayList<TickMark>();

    protected boolean isShowTickMarks;

    protected boolean isSnapToTickMarks;

    public static int autoWidth = 99;
    public static int autoHeight = 9;
    public static PVector autoSpacing = new PVector(0, 5, 0);

    public int alignValueLabel = CENTER;

    protected int _myColorTickMark = 0xffffffff;

    private int mode = -1;

    /**
     * Convenience constructor to extend Range.
     *
     * @example use/skatoloextendController
     * @param theskatolo
     * @param theName
     */
    public Range(Skatolo theskatolo, String theName) {
        this(theskatolo, theskatolo.getDefaultTab(), theName, 0, 100, 0, 100, 0, 0, 99, 9);
        theskatolo.register(theskatolo.getObjectForIntrospection(), theName, this);
    }

    /**
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
    public Range(Skatolo theskatolo, ControllerGroup<?> theParent, String theName, float theMin, float theMax, float theDefaultMinValue, float theDefaultMaxValue, int theX, int theY, int theWidth, int theHeight) {
        super(theskatolo, theParent, theName, theX, theY, theWidth, theHeight);

        _myArrayValue = new float[]{theDefaultMinValue, theDefaultMaxValue};

        _myMin = theMin;
        _myMax = theMax;
        _myValueRange = _myMax - _myMin;

        minHandle = (int) PApplet.map(theDefaultMinValue, _myMin, _myMax, handleSize, getWidth() - handleSize);
        maxHandle = (int) PApplet.map(theDefaultMaxValue, _myMin, _myMax, handleSize, getWidth() - handleSize);
        mr = maxHandle - minHandle;

        _myCaptionLabel = new Label(skatolo, theName).setColor(color.getCaptionLabel()).align(RIGHT_OUTSIDE, CENTER);
        _myValueLabel = new Label(skatolo, "" + adjustValue(_myMin)).setColor(color.getValueLabel()).set("" + adjustValue(theDefaultMinValue)).align(LEFT, CENTER);
        _myHighValueLabel = new Label(skatolo, adjustValue(_myMax)).setColor(color.getValueLabel()).set("" + adjustValue(theDefaultMaxValue)).align(RIGHT, CENTER);

        _myValue = theDefaultMinValue;

        _myDirection = HORIZONTAL;

        update();

    }

    @Override
    public Range setColorValueLabel(int theColor) {
        _myValueLabel.setColor(theColor);
        _myHighValueLabel.setColor(theColor);
        return this;
    }

    @Override
    public Range setColorCaptionLabel(int theColor) {
        _myCaptionLabel.setColor(theColor);
        return this;
    }

    public Range setHighValueLabel(final String theLabel) {
        _myHighValueLabel.set(theLabel);
        return this;
    }

    public Range setLowValueLabel(final String theLabel) {
        _myValueLabel.set(theLabel);
        return this;
    }

    public Range setSliderMode(int theMode) {
        return this;
    }

    public Range setHandleSize(int theSize) {
        handleSize = theSize;
        setLowValue(_myArrayValue[0], false);
        setHighValue(_myArrayValue[1], false);
        mr = maxHandle - minHandle;
        return this;
    }

    public Range updateInternalEvents(PApplet theApplet) {
        if (isVisible) {
            int c = controlWindow.getPointerX() - controlWindow.getPointerPrevX();
            if (c == 0) {
                return this;
            }
            if (isMousePressed() && !skatolo.isAltDown()) {
                switch (mode) {
                    case (LEFT):
                        minHandle = PApplet.max(handleSize, PApplet.min(maxHandle, minHandle + c));
                        break;
                    case (RIGHT):
                        maxHandle = PApplet.max(minHandle, PApplet.min(getWidth() - handleSize, maxHandle + c));
                        break;
                    case (CENTER):
                        minHandle = PApplet.max(handleSize, PApplet.min(getWidth() - mr - handleSize, minHandle + c));
                        maxHandle = PApplet.max(minHandle, PApplet.min(getWidth() - handleSize, minHandle + mr));
                        break;
                }
                update();
            }
        }
        return this;
    }

    @Override
    public void mousePressed() {

        final float posX = _myParent.getAbsolutePosition().x + position.x;
        final float posY = _myParent.getAbsolutePosition().y + position.y;

        if (controlWindow.getPointerY() < posY || controlWindow.getPointerY() > posY + getHeight()) {
            mode = -1;
            isMinHandle = isMaxHandle = false;
            return;
        }

        int x0 = (int) (posX + minHandle);
        int x1 = (int) (posX + maxHandle);

        if (controlWindow.getPointerX() >= x0 - handleSize && controlWindow.getPointerX() < x0) {
            mode = LEFT;
            isMinHandle = true;
        } else if (controlWindow.getPointerX() >= x1 && controlWindow.getPointerX() < x1 + handleSize) {
            mode = RIGHT;
            isMaxHandle = true;
        } else if (controlWindow.getPointerX() > x0 && controlWindow.getPointerX() < x1 && isDraggable) {
            mode = CENTER;
        }
    }

    /**
     * set the value of the range-slider. to set the low and high value, use
     * setLowValue and setHighValue or setRangeValues
     *
     * @see #setLowValue(float)
     * @see #setHighValue(float)
     * @see #setRangeValues(float, float)
     *
     * @param theValue float
     * @return Range
     */
    @Override
    public Range setValue(float theValue) {
        _myValue = theValue;
        broadcast(ARRAY);
        return this;
    }

    /**
     * @exclude
     */
    @Override
    public Range update() {
        _myArrayValue[0] = PApplet.map(minHandle, handleSize, getWidth() - handleSize, _myMin, _myMax);
        _myArrayValue[1] = PApplet.map(maxHandle, handleSize, getWidth() - handleSize, _myMin, _myMax);
        mr = maxHandle - minHandle;
        _myHighValueLabel.set(adjustValue(_myArrayValue[1]));
        _myValueLabel.set(adjustValue(_myArrayValue[0]));
        return setValue(_myValue);
    }

    public Range setDraggable(boolean theFlag) {
        isDraggable = theFlag;
        isDragging = (theFlag == false) ? false : isDragging;
        return this;
    }

    public float[] getArrayValue() {
        return _myArrayValue;
    }

    @Override
    public Range setArrayValue(float[] theArray) {
        setLowValue(theArray[0], false);
        setHighValue(theArray[1], false);
        return update();
    }

    @Override
    public Range setMin(float theValue) {
        _myMin = theValue;
        _myValueRange = _myMax - _myMin;
        return setLowValue(_myArrayValue[0]);
    }

    @Override
    public Range setMax(float theValue) {
        _myMax = theValue;
        _myValueRange = _myMax - _myMin;
        return setHighValue(_myArrayValue[1]);
    }

    public float getLowValue() {
        return _myArrayValue[0];
    }

    public float getHighValue() {
        return _myArrayValue[1];
    }

    @Override
    public Range setWidth(int theValue) {
        width = theValue;
        return this;
    }

    @Override
    public Range setHeight(int theValue) {
        height = theValue;
        return this;
    }

    @Override
    public void mouseReleased() {
        isDragging = isMinHandle = isMaxHandle = isMoveHandle = false;
        mode = -1;
    }

    @Override
    public void mouseReleasedOutside() {
        mouseReleased();
    }

    @Override
    public void onLeave() {
        isMinHandle = false;
        isMaxHandle = false;
    }

    protected void setTickMarks() {
        System.out.println("Range Tickmarks not yet supported");
    }

    public Range setColorTickMark(int theColor) {
        _myColorTickMark = theColor;
        return this;
    }

    public Range showTickMarks(boolean theFlag) {
        isShowTickMarks = theFlag;
        return this;
    }

    public Range snapToTickMarks(boolean theFlag) {
        isSnapToTickMarks = theFlag;
        System.out.println("Range Tickmarks not yet supported");
        return this;
    }

    public TickMark getTickMark() {
        System.out.println("Range Tickmarks not yet supported");
        return null;
    }

    public ArrayList<TickMark> getTickMarks() {
        return _myTickMarks;
    }

    public Range setNumberOfTickMarks(int theNumber) {
        System.out.println("Range Tickmarks not yet supported");
        _myTickMarks.clear();
        if (theNumber > 0) {
            for (int i = 0; i < theNumber; i++) {
                _myTickMarks.add(new TickMark(this));
            }
            showTickMarks(true);
            snapToTickMarks(true);
        } else {
            showTickMarks(false);
            snapToTickMarks(false);
        }
        _myUnit = (_myMax - _myMin) / ((width > height) ? width - 1 : height - 1);
        setLowValue(_myArrayValue[0], false);
        setHighValue(_myArrayValue[1], false);
        return update();
    }

    public Range setRange(float theMinValue, float theMaxValue) {
        setMin(theMinValue);
        setMax(theMaxValue);
        return this;
    }

    public Range setRangeValues(float theLowValue, float theHighValue) {
        return setArrayValue(new float[]{theLowValue, theHighValue});
    }

    private Range setLowValue(float theValue, boolean isUpdate) {
        _myArrayValue[0] = PApplet.max(_myMin, snapValue(theValue));
        minHandle = (int) PApplet.map(_myArrayValue[0], _myMin, _myMax, handleSize, getWidth() - handleSize);
        return (isUpdate) ? update() : this;
    }

    public Range setLowValue(float theValue) {
        return setLowValue(theValue, true);
    }

    private Range setHighValue(float theValue, boolean isUpdate) {
        _myArrayValue[1] = PApplet.min(_myMax, snapValue(theValue));
        maxHandle = (int) PApplet.map(_myArrayValue[1], _myMin, _myMax, handleSize, getWidth() - handleSize);
        return (isUpdate) ? update() : this;
    }

    public Range setHighValue(float theValue) {
        return setHighValue(theValue, true);
    }

    protected float snapValue(float theValue) {
        if (isMousePressed()) {
            return theValue;
        }
        if (isSnapToTickMarks) {
            _myValuePosition = ((theValue - _myMin) / _myUnit);
            float n = PApplet.round(PApplet.map(_myValuePosition, 0, (_myDirection == HORIZONTAL) ? getWidth() : getHeight(), 0, _myTickMarks.size() - 1));
            theValue = PApplet.map(n, 0, _myTickMarks.size() - 1, _myMin, _myMax);
        }
        return theValue;
    }

    @Override
    public Range updateDisplayMode(int theMode) {
        _myDisplayMode = theMode;
        switch (theMode) {
            case (DEFAULT):
                _myControllerView = new RangeView();
                break;
            case (SPRITE):
                _myControllerView = new RangeSpriteView();
                break;
            case (IMAGE):
                _myControllerView = new RangeImageView();
                break;
            case (CUSTOM):
            default:
                break;
        }
        return this;
    }

    class RangeSpriteView implements ControllerView<Range> {

        public void display(PGraphics graphics, Range theController) {
            Skatolo.logger().log(Level.INFO, "RangeSpriteDisplay not available.");
        }
    }

    class RangeView implements ControllerView<Range> {

        public void display(PGraphics graphics, Range theController) {

            int high = mode;

            final float posX = _myParent.getAbsolutePosition().x + position.x;
            int x0 = (int) (posX + minHandle);
            int x1 = (int) (posX + maxHandle);

            if (isPointerOver() && high < 0) {
                if (controlWindow.getPointerX() >= x0 - handleSize && controlWindow.getPointerX() < x0) {
                    high = LEFT;
                } else if (controlWindow.getPointerX() >= x1 && controlWindow.getPointerX() < x1 + handleSize) {
                    high = RIGHT;
                } else if (controlWindow.getPointerX() > x0 && controlWindow.getPointerX() < x1 && isDraggable) {
                    high = CENTER;
                }
            }

            graphics.pushMatrix();

            graphics.fill(color.getBackground());

            graphics.noStroke();

            graphics.rect(0, 0, width, height);

            graphics.fill(high == CENTER ? color.getActive() : color.getForeground());

            if (isShowTickMarks) {
                int n = handleSize / 2;
                graphics.rect(minHandle - n, 0, mr + handleSize, height);
                graphics.fill((isMinHandle || high == LEFT) ? color.getActive() : color.getForeground());
                graphics.triangle(minHandle - handleSize, 0, minHandle, 0, minHandle - n, height);
                graphics.fill((isMaxHandle || high == RIGHT) ? color.getActive() : color.getForeground());
                graphics.triangle(maxHandle, 0, maxHandle + handleSize, 0, maxHandle + n, height);
            } else {
                graphics.rect(minHandle, 0, mr, height);
                graphics.fill((isMinHandle || high == LEFT) ? color.getActive() : color.getForeground());
                graphics.rect((minHandle - handleSize), 0, handleSize, height);
                graphics.fill((isMaxHandle || high == RIGHT) ? color.getActive() : color.getForeground());
                graphics.rect(maxHandle, 0, handleSize, height);

            }

            if (isLabelVisible) {
                _myCaptionLabel.draw(graphics, 0, 0, theController);
                _myValueLabel.draw(graphics, 0, 0, theController);
                _myHighValueLabel.draw(graphics, 0, 0, theController);
            }

            graphics.popMatrix();

            if (isShowTickMarks) {
                graphics.pushMatrix();
                float x = (getWidth() - handleSize) / (getTickMarks().size() - 1);
                graphics.translate(handleSize / 2, getHeight());
                graphics.fill(_myColorTickMark);
                for (TickMark tm : getTickMarks()) {
                    tm.draw(graphics);
                    graphics.translate(x, 0);
                }
                graphics.popMatrix();
            }
        }
    }

    class RangeImageView implements ControllerView<Range> {

        public void display(PGraphics graphics, Range theController) {
            Skatolo.logger().log(Level.INFO, "RangeImageDisplay not implemented.");
        }
    }

    @Override
    public String toString() {
        return "type:\tRange\n" + super.toString();
    }

    @Deprecated
    public float lowValue() {
        return getLowValue();
    }

    @Deprecated
    public float highValue() {
        return getHighValue();
    }

    @Deprecated
    public float[] arrayValue() {
        return _myArrayValue;
    }

}
