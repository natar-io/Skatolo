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

import tech.lity.rea.skatolo.Hacks;
import tech.lity.rea.skatolo.ControlFont;
import tech.lity.rea.skatolo.Skatolo;
import tech.lity.rea.skatolo.gui.Controller;
import tech.lity.rea.skatolo.gui.Label;
import tech.lity.rea.skatolo.gui.group.Tab;
import java.util.Arrays;
import java.util.List;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PGraphics;

/**
 * @example controllers/skatolotextlabel
 * @nosuperclasses Controller Controller Textarea
 */
public class Textlabel extends Controller<Textlabel> {

    protected int _myLetterSpacing = 0;

    private boolean disabled;

    /**
     *
     * @param theSkatolo skatolo
     * @param theParent Tab
     * @param theName String
     * @param theValue String
     * @param theX int
     * @param theY int
     */
    public Textlabel(final Skatolo theskatolo, final Tab theParent, final String theName, final String theValue, final int theX,
            final int theY) {
        super(theskatolo, theParent, theName, theX, theY, 200, 20);
        _myStringValue = theValue;
        setup();
    }

    /**
     *
     * @param theValue String
     * @param theX int
     * @param theY int
     */
    public Textlabel(final String theValue, final int theX, final int theY) {
        super("", theX, theY);
        _myStringValue = theValue;
        setup();
    }

    public Textlabel(final String theValue, final int theX, final int theY, final int theW, final int theH, final int theColor) {
        super("", theX, theY);
        _myStringValue = theValue;
        _myValueLabel = new Label(skatolo, _myStringValue, theW, theH, theColor);

        _myValueLabel.setFont(skatolo.controlFont == skatolo.defaultFont ? skatolo.defaultFontForText : skatolo.controlFont);

        _myValueLabel.setMultiline(false);
        _myValueLabel.toUpperCase(false);
    }

    /**
     * @param theskatolo
     * @param theValue
     * @param theX
     * @param theY
     */
    public Textlabel(Skatolo theskatolo, final String theValue, final int theX, final int theY) {
        super("", theX, theY);

        skatolo = theskatolo;

        _myStringValue = theValue;

        _myValueLabel = new Label(skatolo, _myStringValue, 10, 10, 0xffffffff);
        _myValueLabel.setFont(skatolo.controlFont == skatolo.defaultFont ? skatolo.defaultFontForText : skatolo.controlFont);
        _myValueLabel.set(_myStringValue);
        _myValueLabel.setMultiline(false);
        _myValueLabel.toUpperCase(false);
    }

    /**
     *
     * @param theComponent
     * @param theValue
     * @param theX
     * @param theY
     * @param theW
     * @param theH
     */
    public Textlabel(Skatolo theskatolo, final String theValue, final int theX, final int theY, final int theW, final int theH) {
        super("", theX, theY);
        skatolo = theskatolo;
        _myStringValue = theValue;
        _myValueLabel = new Label(skatolo, _myStringValue, theW, theH, 0xffffffff);
        _myValueLabel.setFont(skatolo.controlFont == skatolo.defaultFont ? skatolo.defaultFontForText : skatolo.controlFont);
        _myValueLabel.setMultiline(false);
        _myValueLabel.toUpperCase(false);
    }

    protected void setup() {
        _myValueLabel = new Label(skatolo, _myStringValue);
        _myValueLabel.setFont(skatolo.controlFont == skatolo.defaultFont ? skatolo.defaultFontForText : skatolo.controlFont);
        _myValueLabel.setMultiline(false);
        _myValueLabel.toUpperCase(false);
    }

    @Override
    public Textlabel setWidth(int theValue) {
        _myValueLabel.setWidth(theValue);
        return this;
    }

    public Textlabel setHeight(int theValue) {
        _myValueLabel.setHeight(theValue);
        return this;
    }

    /**
     * draw the textlabel.
     *
     * @param theApplet PApplet
     */
    public void draw(final PGraphics graphics) {
        if (!disabled) {
            graphics.pushMatrix();
            graphics.translate(position.x, position.y);
            _myValueLabel.draw(graphics, 0, 0, this);
            graphics.popMatrix();
        }
    }

    public void draw() {
        if (skatolo.pgraphics == null) {
            draw(skatolo.getPApplet().g);
        } else {
            draw(skatolo.pgraphics);
        }
    }

    public void draw(int theX, int theY) {
        if (skatolo.pgraphics == null) {
            skatolo.getPApplet().pushMatrix();
            skatolo.getPApplet().translate(theX, theY);
            draw(skatolo.getPApplet().g);
            skatolo.getPApplet().popMatrix();
        } else {
            skatolo.pgraphics.pushMatrix();
            skatolo.pgraphics.translate(theX, theY);
            draw(skatolo.pgraphics);
            skatolo.pgraphics.popMatrix();
        }
    }

    /**
     *
     * @param theValue float
     */
    public Textlabel setValue(float theValue) {
        return this;
    }

    public Textlabel setText(final String theText) {
        return setValue(theText);
    }

    public Textlabel setLineHeight(int theValue) {
        _myValueLabel.setLineHeight(theValue);
        return this;
    }

    public int getLineHeight() {
        return _myValueLabel.getLineHeight();
    }

    public Textlabel append(String theText, int max) {
        String str = _myStringValue + theText;

        if (max == -1) {
            return setText(str);
        }

        List<String> strs = Arrays.asList(str.split("\n"));
        return setText(Hacks.join(strs.subList(Math.max(0, strs.size() - max), strs.size()), "\n"));
    }

    @Override
    public Textlabel setStringValue(String theValue) {
        return setValue(theValue);
    }

    public Textlabel setMultiline(final boolean theFlag) {
        _myValueLabel.setMultiline(true);
        return this;
    }

    /**
     * set the text of the textlabel.
     *
     * @param theText String
     */
    public Textlabel setValue(final String theText) {
        _myStringValue = theText;
        _myValueLabel.set(theText);
        width = _myValueLabel.getWidth();
        height = _myValueLabel.getHeight();
        return this;
    }

    public Textlabel setColor(int theColor) {
        _myValueLabel.setColor(theColor, true);
        return this;
    }

    /**
     * set the letter spacing of the font.
     *
     * @param theValue int
     * @return Textlabel
     */
    public Textlabel setLetterSpacing(final int theValue) {
        _myLetterSpacing = theValue;
        _myValueLabel.setLetterSpacing(_myLetterSpacing);
        return this;
    }

    public Textlabel setFont(ControlFont theControlFont) {
        getValueLabel().setFont(theControlFont);
        return this;
    }

    public Textlabel setFont(PFont thePFont) {
        getValueLabel().setFont(thePFont);
        return this;
    }

    protected boolean computeIsInside() {
        return (controlWindow.getPointerX() > position.x + _myParent.getAbsolutePosition().x
                && controlWindow.getPointerX() < position.x + _myParent.getAbsolutePosition().x + _myValueLabel.getWidth()
                && controlWindow.getPointerY() > position.y + _myParent.getAbsolutePosition().y && controlWindow.getPointerY() < position.y
                + _myParent.getAbsolutePosition().y + _myValueLabel.getHeight());
    }

    public Label get() {
        return _myValueLabel;
    }

    private void printConstructorError(String theValue) {
        Skatolo
                .logger()
                .severe("The Textlabel constructor you are using has been deprecated, please use constructor\nnew Textlabel(skatolo,String,int,int) or\nnew Textlabel(skatolo,String,int,int,int,int) or\nnew Textlabel(skatolo,String,int,int,int,int,int,int)\ninstead. The Textlabel with value '"
                        + theValue + "' is disabled and will not be rendered.");
    }

    @Deprecated
    public Label valueLabel() {
        return _myValueLabel;
    }

    @Deprecated
    public Textlabel(final PApplet theComponent, final String theValue, final int theX, final int theY, final int theW,
            final int theH, final int theColor, final int theFont) {
        super("", theX, theY);
        disabled = true;
        printConstructorError(theValue);
    }

    @Deprecated
    public Textlabel(final PApplet theComponent, final String theValue, final int theX, final int theY) {
        super("", theX, theY);
        disabled = true;
        printConstructorError(theValue);
    }

    @Deprecated
    public Textlabel(Skatolo theskatolo, final String theValue, final int theX, final int theY, final int theW,
            final int theH, final int theColor, final int theFont) {
        super("", theX, theY);
        skatolo = theskatolo;
        _myStringValue = theValue;
        _myValueLabel = new Label(skatolo, _myStringValue, theW, theH, theColor);
        // _myValueLabel.setFont(theFont);
        _myValueLabel.setFont(skatolo.controlFont == skatolo.defaultFont ? skatolo.defaultFontForText : skatolo.controlFont);
        _myValueLabel.setMultiline(false);
        _myValueLabel.toUpperCase(false);
    }

    @Deprecated
    public Textlabel(final PApplet theComponent, final String theValue, final int theX, final int theY, final int theW,
            final int theH) {
        super("", theX, theY);
        disabled = true;
        printConstructorError(theValue);
    }

    /**
     * a textlabel is an image containing text rendered from a bitfont source
     * image. available bit fonts are: standard56, standard58, synt24, grixel.
     * the font of a textlabel can be changed by using setFont(int theFontIndex)
     * theFontIndex is of type int and available indexes are stored in the
     * constants skatolo.standard56, skatolo.standard58, skatolo.synt24,
     * skatolo.grixel available characters for each pixelfont range from ascii
     * code 32-126
     *
     * @shortdesc set the Pixel-Font-Family of the Textlabel.
     * @param theFont int
     */
    @Deprecated
    public Textlabel setFont(final int theFont) {
        getValueLabel().setFont(theFont);
        return this;
    }

    /**
     * @deprecated @exclude
     */
    @Deprecated
    public Textlabel setControlFont(ControlFont theControlFont) {
        return setFont(theControlFont);
    }
}
