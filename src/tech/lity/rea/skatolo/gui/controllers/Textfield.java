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

import tech.lity.rea.skatolo.ControlFont;
import tech.lity.rea.skatolo.Skatolo;
import tech.lity.rea.skatolo.gui.Controller;
import tech.lity.rea.skatolo.gui.group.ControllerGroup;
import tech.lity.rea.skatolo.gui.Label;
import processing.event.KeyEvent;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PGraphics;

/**
 * A singleline input textfield, use arrow keys to go back and forth, use backspace to delete
 * characters. Using the up and down arrows lets you cycle through the history of the textfield.
 * 
 * This is the best you can get. Font handling, font switching, measuring, left align, right align,
 * etc. was giving me a big headache. not perfect, i think this is a good compromise.
 * 
 * @example controllers/skatolotextfield
 * @nosuperclasses Controller Controller
 */
public class Textfield extends Controller<Textfield> {

    /*
     * TODO textspacing does not work properly for bitfonts sometimes first row of pixels in a
     * bitfont texture gets cut off
     */

    protected boolean isTexfieldActive;

    protected boolean isKeepFocus;

    protected StringBuffer _myTextBuffer = new StringBuffer();

    protected int _myTextBufferIndex = 0;

    protected int _myTextBufferOverflow = 0;

    protected int _myTextBufferIndexPosition = 0;

    public static int cursorWidth = 1;

    protected Map<Integer, TextfieldCommand> keyMapping;

    protected InputFilter _myInputFilter = InputFilter.BITFONT;

    protected List<Integer> ignorelist;

    protected LinkedList<String> _myHistory;

    protected int _myHistoryIndex;

    protected boolean changed;

    protected int len = 0;

    protected int offset = 2;

    protected int margin = 2;

    protected boolean isPasswordMode;

    protected boolean autoclear = true;

    protected int _myColorCursor = 0x88ffffff;

    public enum InputFilter {
            INTEGER(Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9')), FLOAT(Arrays.asList('0', '1', '2',
                            '3', '4', '5', '6', '7', '8', '9', '.')), BITFONT(Arrays.asList('\n', '\r', ' ', '!', '"', '#', '$',
                            '%', '&', '\'', '(', ')', '*', '+', ',', '-', '.', '/', '0', '1', '2', '3', '4', '5', '6', '7', '8',
                            '9', ':', ';', '<', '=', '>', '?', '@', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
                            'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '[', '\\', ']', '^', '_', '`',
                            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
                            'u', 'v', 'w', 'x', 'y', 'z', '{', '|', '}', '~')), DEFAULT(new LinkedList<Character>());

            final List<Character> allowed;

            InputFilter(List<Character> theList) {
                    allowed = theList;
            }

            protected boolean apply(char theCharater) {
                    if (allowed.isEmpty()) {
                            return true;
                    } else {
                            return allowed.contains(theCharater);
                    }
            }

    }

    /**
     * Convenience constructor to extend Textfield.
     * 
     * @example use/skatoloextendController
     * @param theskatolo
     * @param theName
     */
    public Textfield(Skatolo theskatolo, String theName) {
            this(theskatolo, theskatolo.getDefaultTab(), theName, "", 0, 0, 199, 19);
            theskatolo.register(theskatolo.getObjectForIntrospection(), theName, this);
    }

    public Textfield(Skatolo theskatolo, ControllerGroup<?> theParent, String theName, String theDefaultValue,
                    int theX, int theY, int theWidth, int theHeight) {
            super(theskatolo, theParent, theName, theX, theY, theWidth, theHeight);
            _myCaptionLabel = new Label(skatolo, theName.toUpperCase(), 0, 0, color.getCaptionLabel());

            _myValueLabel.setFont(skatolo.controlFont == skatolo.defaultFont ? skatolo.defaultFontForText : skatolo.controlFont);

            _myCaptionLabel.align(Skatolo.LEFT, Skatolo.BOTTOM_OUTSIDE);
            _myCaptionLabel.setPaddingX(0);

            _myBroadcastType = STRING;
            _myValueLabel.setFixedSize(true);
            _myValueLabel.set("");
            _myValueLabel.setWidth(width - margin * 2);
            _myValueLabel.setPadding(0, 0);
            _myValueLabel.align(LEFT, CENTER);
            _myValueLabel.setColor(color.getValueLabel());
            _myValueLabel.toUpperCase(false);

            _myValueLabel.setLabeltype(_myValueLabel.new SinglelineTextfield());

            _myHistory = new LinkedList<String>();
            _myHistory.addFirst("");

            keyMapping = new HashMap<Integer, TextfieldCommand>();
            keyMapping.put(ENTER, new Enter());
            keyMapping.put(DEFAULT, new InsertCharacter());
            keyMapping.put(DELETE, new DeleteCharacter());
            keyMapping.put(BACKSPACE, new DeleteCharacter());
            keyMapping.put(LEFT, new MoveLeft());
            keyMapping.put(RIGHT, new MoveRight());
            keyMapping.put(UP, new MoveUp());
            keyMapping.put(DOWN, new MoveDown());

            ignorelist = new LinkedList<Integer>();
            ignorelist.add(SHIFT);
            ignorelist.add(ALT);
            ignorelist.add(CONTROL);
            ignorelist.add(TAB);
            ignorelist.add(COMMANDKEY);

            setInputFilter(DEFAULT);
            changed = true;

    }

    @Override public Textfield setWidth(int theWidth) {
            _myValueLabel.setWidth(theWidth);
            return super.setWidth(theWidth);
    }

    @Override public Textfield setHeight(int theHeight) {
            _myValueLabel.setHeight(theHeight);
            return super.setHeight(theHeight);
    }

    public Textfield setFocus(boolean theValue) {
            isTexfieldActive = isActive = theValue;
            changed = true;
            return this;
    }

    /**
     * check if the textfield is active and in focus.
     * 
     * @return boolean
     */
    public boolean isFocus() {
            return isTexfieldActive;
    }

    public Textfield keepFocus(boolean theValue) {
            isKeepFocus = theValue;
            if (isKeepFocus) {
                    setFocus(true);
            }
            return this;
    }

    public Textfield setFont(PFont thePFont) {
            getValueLabel().setFont(thePFont);
            return this;
    }

    public Textfield setFont(ControlFont theFont) {
            getValueLabel().setFont(theFont);
            return this;
    }

    public Textfield setFont(int theFont) {
            getValueLabel().setFont(theFont);
            return this;
    }

    public Textfield setPasswordMode(boolean theFlag) {
            isPasswordMode = theFlag;
            return this;
    }

    public Textfield setInputFilter(int theInputType) {
            switch (theInputType) {
            case (INTEGER):
                    _myInputFilter = InputFilter.INTEGER;
                    break;
            case (FLOAT):
                    _myInputFilter = InputFilter.FLOAT;
                    break;
            case (BITFONT):
                    _myInputFilter = InputFilter.BITFONT;
                    break;
            default:
                    _myInputFilter = InputFilter.DEFAULT;
                    break;
            }
            return this;
    }

    @Override public Textfield setValue(float theValue) {
            // use setText(String) instead
            return this;
    }

    @Override public void updateFont(ControlFont theControlFont) {
            super.updateFont(theControlFont);
            changed = true;
    }

    public Textfield setValue(String theText) {
            _myTextBuffer = new StringBuffer(theText);
            setIndex(_myTextBuffer.length());
            changed = true;
            return this;
    }

    public Textfield setText(String theText) {
            return setValue(theText);
    }

    public Textfield clear() {
            // create a new text buffer
            _myTextBuffer = new StringBuffer();
            // reset the buffer index
            setIndex(0);
            return this;
    }

    public Textfield setAutoClear(boolean theValue) {
            autoclear = theValue;
            return this;
    }

    public boolean isAutoClear() {
            return autoclear;
    }

    @Override protected void mousePressed() {
            if (isActive) {
                    // TODO System.out.println("adjust cursor");
            }
            int x = (int) (getControlWindow().getPointerX() - getAbsolutePosition().x);
            int y = (int) (getControlWindow().getPointerY() - getAbsolutePosition().y);

            // TODO System.out.println(x + ":" + y);
            setFocus(true);
    }

    @Override protected void mouseReleasedOutside() {
            if (isKeepFocus == false) {
                    isTexfieldActive = isActive = false;
            }
    }

    public int getIndex() {
            return _myTextBufferIndex;
    }

    public String getText() {
            return _myTextBuffer.toString();
    }

    public Textfield setColor(int theColor) {
            getValueLabel().setColor(theColor);
            return this;
    }

    public Textfield setColorCursor(int theColor) {
            _myColorCursor = theColor;
            return this;
    }

    @Override public void draw(PGraphics graphics) {
            if (changed) {
                    updateLabel(graphics);
                    changed = false;
            }
            graphics.pushStyle();
            graphics.fill(color.getBackground());
            graphics.pushMatrix();
            graphics.translate(position.x, position.y);
            graphics.rect(0, 0, width, height);
            graphics.noStroke();

            graphics.fill(_myColorCursor);
            graphics.pushMatrix();
            graphics.pushStyle();

            if (_myTextBufferIndexPosition > len - offset) {
                    // theApplet.textAlign(PApplet.RIGHT);
                    _myValueLabel.textAlign = PApplet.RIGHT;
                    graphics.translate(getWidth() - margin, 0);
                    if (isTexfieldActive) {
                            graphics.rect(0, 0, cursorWidth, height);
                    }
            } else {
                    // theApplet.textAlign(PApplet.LEFT);
                    _myValueLabel.textAlign = PApplet.LEFT;
                    graphics.translate(margin, 0);
                    if (isTexfieldActive) {
                            graphics.rect(PApplet.max(0, PApplet.min(_myTextBufferIndexPosition, getWidth() - margin)), 0,
                                            cursorWidth, height);
                    }
            }

            _myValueLabel.draw(graphics, 0, 0, this);
            graphics.popStyle();
            graphics.popMatrix();

            graphics.fill(isTexfieldActive ? color.getActive() : color.getForeground());
            graphics.rect(0, 0, width, 1);
            graphics.rect(0, height - 1, width, 1);
            graphics.rect(-1, 0, 1, height);
            graphics.rect(width, 0, 1, height);
            _myCaptionLabel.draw(graphics, 0, 0, this);
            graphics.popMatrix();
            graphics.popStyle();
    }

    private void updateLabel(PGraphics graphics) {
            if (_myInputFilter == InputFilter.BITFONT) {
                    setInputFilter(DEFAULT);
            }
            String str = passCheck(getText());
            String t1 = str;
            int off = margin * 2;
            int ww = ControlFont.getWidthFor(str, _myValueLabel, graphics);
            if ((ww < getWidth() - off)) {
                    _myTextBufferIndexPosition = ControlFont.getWidthFor(t1.substring(0, _myTextBufferIndex), _myValueLabel,
                                    graphics);
                    len = getWidth();
            } else {
                    char[] c = str.toCharArray();
                    int mx = 0;
                    int n = 0;
                    for (int i = 0; i < c.length; i++) {
                            n += graphics.textWidth(c[i]);
                            if (n > _myValueLabel.getWidth() - off) {
                                    break;
                            }
                            len = n;
                            mx++;
                    }
                    t1 = "";
                    n = 0;
                    // this is messed up an occasionally throws ArrayIndexOutOfBounds Exceptions
                    // sometimes the beginning of the text is not in order when text length exceeds
                    // length of textfield.
                    // needs fixing. TODO
                    for (int i = PApplet.max(mx, _myTextBufferIndex - 1); i >= 0; i--) {
                            try {
                                    n += graphics.textWidth(c[i]);
                            } catch (Exception e) {
                            }
                            t1 = c[i] + t1;
                            if (n >= _myValueLabel.getWidth() - off - 4) {
                                    _myTextBufferOverflow = str.indexOf(t1);
                                    break;
                            }
                    }
                    int strn = PApplet.max(0, PApplet.min(t1.length(), _myTextBufferIndex - _myTextBufferOverflow));
                    _myTextBufferIndexPosition = ControlFont.getWidthFor(t1.substring(0, strn), _myValueLabel, graphics);
            }
            _myValueLabel.setText(t1);
            changed = false;
    }
    
    private String passCheck(String label) {
            if (!isPasswordMode) {
                    return label;
            }
            String newlabel = "";
            for (int i=0;i<label.length();i++) {
                    newlabel += "*";
            }
            return newlabel;
    }

    public void keyEvent(KeyEvent theKeyEvent) {
            if (isUserInteraction && isTexfieldActive && isActive && theKeyEvent.getAction() == KeyEvent.PRESS) {
                    if (ignorelist.contains(skatolo.getKeyCode())) {
                            return;
                    }
                    if (keyMapping.containsKey(skatolo.getKeyCode())) {
                            keyMapping.get(skatolo.getKeyCode()).execute();
                    } else {
                            keyMapping.get(DEFAULT).execute();
                    }
            }
    }

    /**
     * make the controller execute a return event. submit the current content of the texfield.
     * 
     */
    public Textfield submit() {
            keyMapping.get(ENTER).execute();
            return this;
    }

    public String[] getTextList() {
            String[] s = new String[_myHistory.size()];
            _myHistory.toArray(s);
            return s;
    }

    private Textfield setIndex(int theIndex) {
            _myTextBufferIndex = theIndex;
            changed = true;
            return this;
    }

    interface TextfieldCommand {

            void execute();
    }

    class InsertCharacter implements TextfieldCommand {

            public void execute() {
                    if ((int) (skatolo.getKey()) == 65535) {
                            return;
                    }

                    if (_myInputFilter.apply(skatolo.getKey())) {
                            _myTextBuffer.insert(_myTextBufferIndex, (char) skatolo.getKey());
                            setIndex(_myTextBufferIndex + 1);
                    }
            }
    }

    class Enter implements TextfieldCommand {

            public void execute() {
                    setStringValue(_myTextBuffer.toString());
                    broadcast();
                    // update current buffer with the last item inside the input history
                    _myHistory.set(_myHistory.size() - 1, _myTextBuffer.toString());
                    // set the history index to our last item
                    _myHistoryIndex = _myHistory.size();
                    // add a new and empty buffer to the history
                    _myHistory.add("");
                    if (autoclear) {
                            clear();
                    }
            }
    }

    class DeleteCharacter implements TextfieldCommand {

            public void execute() {
                    if (_myTextBuffer.length() > 0 && _myTextBufferIndex > 0) {
                            _myTextBuffer.deleteCharAt(_myTextBufferIndex - 1);
                            setIndex(_myTextBufferIndex - 1);
                    }
            }
    }

    class MoveLeft implements TextfieldCommand {

            public void execute() {
                    setIndex((skatolo.isMetaDown()) ? 0 : PApplet.max(0, _myTextBufferIndex - 1));
            }
    }

    class MoveRight implements TextfieldCommand {

            public void execute() {
                    setIndex((skatolo.isMetaDown()) ? _myTextBuffer.length() : PApplet.min(_myTextBuffer.length(),
                                    _myTextBufferIndex + 1));
            }
    }

    class MoveUp implements TextfieldCommand {

            public void execute() {
                    if (_myHistoryIndex == 0) {
                            return;
                    }
                    _myHistoryIndex = PApplet.max(0, --_myHistoryIndex);
                    _myTextBuffer = new StringBuffer(_myHistory.get(_myHistoryIndex));
                    setIndex(_myTextBuffer.length());
            }
    }

    class MoveDown implements TextfieldCommand {

            public void execute() {
                    if (_myHistoryIndex >= _myHistory.size() - 1) {
                            return;
                    }

                    _myHistoryIndex = PApplet.min(_myHistory.size() - 1, ++_myHistoryIndex);
                    _myTextBuffer = new StringBuffer(_myHistory.get(_myHistoryIndex));
                    setIndex(_myTextBuffer.length());
            }
    }

}