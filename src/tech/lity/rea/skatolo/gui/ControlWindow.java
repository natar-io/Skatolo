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
package tech.lity.rea.skatolo.gui;

import tech.lity.rea.skatolo.ControlFont;
import tech.lity.rea.skatolo.ControlKey;
import tech.lity.rea.skatolo.Skatolo;
import tech.lity.rea.skatolo.SkatoloBase;
import tech.lity.rea.skatolo.gui.controllers.Numberbox;
import tech.lity.rea.skatolo.gui.group.ControllerGroup;
import tech.lity.rea.skatolo.gui.controllers.Knob;
import tech.lity.rea.skatolo.gui.group.Tab;
import tech.lity.rea.skatolo.gui.controllers.Slider;
import tech.lity.rea.skatolo.gui.group.Textarea;
import tech.lity.rea.skatolo.gui.group.ListBox;
import tech.lity.rea.skatolo.gui.group.DropdownList;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;
import processing.event.KeyEvent;
import processing.event.MouseEvent;
import tech.lity.rea.skatolo.SkatoloBase.KeyCode;
import java.util.LinkedList;
import processing.core.PGraphics;

/**
 * All the controllers are within a ControlWindow, either the main one or in
 * separate window. the purpose of a control window is to shift controllers from
 * the main window into a separate window. to save cpu, a control window is not
 * updated when not active - in focus. for the same reason the framerate is set
 * to 15. To constantly update the control window, use
 * {@link ControlWindow#setUpdateMode(int)}
 *
 * @example controllers/skatolowindow
 */
public final class ControlWindow {

    protected Skatolo skatolo;

    // TODO: find the use of this ?
    protected Controller<?> isControllerActive;
    private final PVector autoPosition = new PVector(10, 30, 0);
    private final float tempAutoPositionHeight = 0;
    private final boolean rendererNotification = false;

    public int background = 0x00000000;
    protected CColor color = new CColor();
    private String name = "main";

    private final PApplet applet;
    private PGraphics graphics;

    protected ControllerList tabs;
    private CDrawable cDrawable;
    protected List<Canvas> canvasList;

    private boolean isVisible = true;
    private boolean isInit = false;
    private boolean isAutoDraw;
    private boolean isUpdated;
    private boolean isDrawingBackground = true;
    private boolean isUndecorated = false;
    private boolean focused = true;

    private final PVector positionOfTabs = new PVector(0, 0, 0);

    private int frameCount = 0;

    private boolean isUsingMouseForPointing = true;
    private int mouseWheelMoved = 0;

    private final List<ControllerInterface<?>> mouseoverList;
    private boolean isMouseOver;

    // Replace this with an appropriate structure 
    // Maybe Pointer. 
    // To get the Multi-Touch !
    // Easy way ? -> Fire all events for each pointer, changing mouseX & mouseY 
    // so that it is not necessary to change most of the code. 
//    public int mouseX;
//    public int mouseY;
//    public int pmouseX;
//    public int pmouseY;
    // Multi-Touch starts here... 
    private PointerList pointers;
    private Pointer mousePointer;
    private Pointer currentPointer;

//    protected boolean mousePressed;
    private static final int NB_KEYS = 525;   // Why ?!
    private char key;
    private int keyCode;
    private boolean[] keys = new boolean[NB_KEYS];
    private int numOfActiveKeys = 0;

    /**
     * @exclude
     */
    public ControlWindow(final Skatolo theskatolo, final PApplet theApplet) {
        mouseoverList = new ArrayList<ControllerInterface<?>>();
        skatolo = theskatolo;
        applet = theApplet;
        graphics = skatolo.getGraphics();
        isAutoDraw = true;
        init();
    }

    protected void init() {
        pointers = new PointerList();
        mousePointer = pointers.getMousePointer();
        // default behaviour.
        currentPointer = mousePointer;

        canvasList = new ArrayList<Canvas>();

        tabs = new ControllerList();
        tabs.add(new Tab(skatolo, this, "global"));
        tabs.add(new Tab(skatolo, this, "default"));

        activateTab(getDefaultTab());
        /*
         * register a post event that will be called by processing after the draw method has been finished.
         */

        // processing pre 2.0 will not draw automatically if in P3D mode. in earlier versions of skatolo
        // this had been checked here and the user had been informed to draw skatolo manually by adding
        // skatolo.draw() to the sketch's draw function. with processing 2.0 and this version of skatolo
        // this notification does no longer exist.
        if (isInit == false) {
            applet.registerMethod("pre", this);
            applet.registerMethod("draw", this);
            if (!skatolo.isAndroid) {
                applet.registerMethod("keyEvent", this);
                applet.registerMethod("mouseEvent", this);
            }
        }
        isInit = true;
    }

    public Tab getGlobalTab() {
        return (Tab) tabs.get(0);
    }

    public Tab getDefaultTab() {
        return (Tab) tabs.get(1);
    }

    public Tab getCurrentTab() {
        for (int i = 1; i < tabs.size(); i++) {
            if (((Tab) tabs.get(i)).isActive()) {
                return (Tab) tabs.get(i);
            }
        }
        return null;
    }

    public ControlWindow activateTab(String theTab) {

        for (int i = 1; i < tabs.size(); i++) {
            if (((Tab) tabs.get(i)).getName().equals(theTab)) {
                if (!((Tab) tabs.get(i)).isActive) {
                    resetMouseOver();
                }
                activateTab((Tab) tabs.get(i));
            }
        }
        return this;
    }

    public ControlWindow removeTab(Tab theTab) {
        tabs.remove(theTab);
        return this;
    }

    public Tab add(Tab theTab) {
        tabs.add(theTab);
        return theTab;
    }

    public Tab addTab(String theTab) {
        return getTab(theTab);
    }

    public ControlWindow activateTab(Tab theTab) {
        for (int i = 1; i < tabs.size(); i++) {
            if (tabs.get(i) == theTab) {
                if (!((Tab) tabs.get(i)).isActive) {
                    resetMouseOver();
                }
                ((Tab) tabs.get(i)).setActive(true);
            } else {
                ((Tab) tabs.get(i)).setActive(false);
            }
        }
        return this;
    }

    public ControllerList getTabs() {
        return tabs;
    }

    public Tab getTab(String theTabName) {
        return skatolo.getTab(this, theTabName);
    }

    /**
     * Sets the position of the tab bar which is set to 0,0 by default. to move
     * the tabs to y-position 100, use skatolo.window().setPositionOfTabs(new
     * PVector(0,100,0));
     *
     * @param thePVector
     */
    public ControlWindow setPositionOfTabs(PVector thePVector) {
        positionOfTabs.set(thePVector);
        return this;
    }

    public ControlWindow setPositionOfTabs(int theX, int theY) {
        positionOfTabs.set(theX, theY, positionOfTabs.z);
        return this;
    }

    /**
     * Returns the position of the tab bar as PVector. to move the tabs to
     * y-position 100, use skatolo.window().getPositionOfTabs().y = 100; or
     * skatolo.window().setPositionOfTabs(new PVector(0,100,0));
     *
     * @return PVector
     */
    public PVector getPositionOfTabs() {
        return positionOfTabs;
    }

    void setAllignmentOfTabs(int theValue, int theWidth) {
        // TODO
    }

    void setAllignmentOfTabs(int theValue, int theWidth, int theHeight) {
        // TODO
    }

    void setAllignmentOfTabs(int theValue) {
        // TODO
    }

    public void remove() {
        if (tabs.size() > 0) {
            for (int i = tabs.size() - 1; i >= 0; i--) {
                ((Tab) tabs.get(i)).remove();
            }
        }
        tabs.clear();
        tabs.clearDrawable();
    }

    /**
     * clear the control window, delete all controllers from a control window.
     */
    public ControlWindow clear() {
        remove();
        return this;
    }

    public void updateFont(ControlFont theControlFont) {
        for (int i = 0; i < tabs.size(); i++) {
            ((Tab) tabs.get(i)).updateFont(theControlFont);
        }
    }

    /**
     * @exclude
     */
    public void updateEvents() {

        // Global is always there. 
        ((ControllerInterface<?>) getGlobalTab()).continuousUpdateEvents();
        ((ControllerInterface<?>) getGlobalTab()).updateEvents();

        for (int i = 0; i < tabs.size(); i++) {

            // Continuous update is for all elements.
            ((Tab) tabs.get(i)).continuousUpdateEvents();

            // Update the active & visible tabs.        
            if (((Tab) tabs.get(i)).isActive() && ((Tab) tabs.get(i)).isVisible()) {
                ((ControllerInterface<?>) tabs.get(i)).updateEvents();
            }
        }
    }

    /**
     * returns true if the mouse is inside a controller. !!! doesnt work for
     * groups yet.
     */
    public boolean isMouseOver() {
        // TODO doesnt work for all groups yet, only ListBox and DropdownList.
        if (frameCount + 1 < applet.frameCount) {
            resetMouseOver();
        }
        return isVisible ? isMouseOver : false;
    }

    public boolean isMouseOver(ControllerInterface<?> theController) {
        return mouseoverList.contains(theController);
    }

    public void resetMouseOver() {
        isMouseOver = false;
        for (int i = mouseoverList.size() - 1; i >= 0; i--) {
            mouseoverList.get(i).setPointerOver(false);
        }
        mouseoverList.clear();
    }

    public ControllerInterface<?> getFirstFromMouseOverList() {
        if (getMouseOverList().isEmpty()) {
            return null;
        } else {
            return getMouseOverList().get(0);
        }
    }

    /**
     * A list of controllers that are registered with a mouseover.
     */
    public List<ControllerInterface<?>> getMouseOverList() {
        return mouseoverList;
    }

    // TODO: check if this is useful ?
    private ControlWindow handleMouseOver() {
        for (int i = mouseoverList.size() - 1; i >= 0; i--) {
            if (!mouseoverList.get(i).isPointerOver() || !isVisible) {
                mouseoverList.remove(i);
            }
        }
        isMouseOver = mouseoverList.size() > 0;
        return this;
    }

    public ControlWindow removeMouseOverFor(ControllerInterface<?> theController) {
        mouseoverList.remove(theController);
        return this;
    }

    public ControlWindow setMouseOverController(ControllerInterface<?> theController) {
        if (!mouseoverList.contains(theController) && isVisible && theController.isVisible()) {
            mouseoverList.add(theController);
        }
        isMouseOver = true;
        return this;
    }

    /**
     * updates all controllers inside the control window if update is enabled.
     *
     * @exclude
     */
    public void update() {
        ((ControllerInterface<?>) getGlobalTab()).update();
        for (int i = 1; i < tabs.size(); i++) {
            ((Tab) tabs.get(i)).update();
        }
    }

    /**
     * enable or disable the update function of a control window.
     */
    public void setUpdated(boolean theFlag) {
        isUpdated = theFlag;
        for (int i = 0; i < tabs.size(); i++) {
            ((ControllerInterface<?>) tabs.get(i)).setUpdate(theFlag);
        }
    }

    /**
     * check the update status of a control window.
     */
    public boolean isUpdate() {
        return isUpdated;
    }

    public ControlWindow addCanvas(Canvas theCanvas) {
        canvasList.add(theCanvas);
        theCanvas.setControlWindow(this);
        theCanvas.setup(applet);
        return this;
    }

    public ControlWindow removeCanvas(Canvas theCanvas) {
        canvasList.remove(theCanvas);
        return this;
    }

    private boolean isReset = false;

    public ControlWindow pre() {

        // Check for focus.
        if (frameCount + 1 < applet.frameCount) {
            if (isReset) {
                resetMouseOver();
                isReset = false;
            }
        } else {
            isReset = true;
        }

        if (papplet().focused != focused) {
            clearKeys();
            mousePointer.resetPress();
            focused = papplet().focused;
        }

        return this;
    }

    boolean pmouseReleased; // Android
    boolean pmousePressed; // Android

    /**
     * when in Android mode, call mouseEvent(int, int, boolean).
     *
     * @param theX
     * @param theY
     * @param pressed
     */
    public void mouseEvent(int theX, int theY, boolean pressed) {

        System.out.println("Android mouseEvent. Support stopped for now.");
//        mousePointer.updatePosition(theX, theY);
//
//        if (pressed && !pmousePressed) {
//            updateEvents();
//            mousePressedEvent();
//            pmousePressed = true;
//            pmouseReleased = false;
//        } else if (!pressed && !pmouseReleased) {
//            updateEvents();
//            mouseReleasedEvent();
//            for (ControllerInterface c : mouseoverList) {
//                if (c instanceof Controller) {
//                    ((Controller) c).onLeave();
//                    ((Controller) c).onRelease();
//                } else if (c instanceof ControllerGroup) {
//                    ((ControllerGroup) c).mouseReleased();
//                }
//            }
//            resetMouseOver();
//            pmousePressed = false;
//            pmouseReleased = true;
//
//        }
    }

    /**
     * @exclude @param theMouseEvent MouseEvent
     */
    public void mouseEvent(MouseEvent theMouseEvent) {
//        mousePointer.updatePosition(
//                theMouseEvent.getX(),
//                theMouseEvent.getY());

        if (theMouseEvent.getAction() == MouseEvent.PRESS) {
            mousePointer.setStatus(Pointer.Status.PRESSED);
        }
        if (theMouseEvent.getAction() == MouseEvent.RELEASE) {
            mousePointer.setStatus(Pointer.Status.RELEASED);
        }

    }

    public boolean isPointerPressed() {
        return currentPointer.isMaintainedPressed();
    }

    public int getPointerX() {
        return currentPointer.getX();
    }

    public int getPointerY() {
        return currentPointer.getY();
    }

    public int getPointerPrevX() {
        return currentPointer.getPX();
    }

    public int getPointerPrevY() {
        return currentPointer.getPY();
    }

    public int getMouseX() {
        return mousePointer.getX();
    }

    public int getMouseY() {
        return mousePointer.getY();
    }

    public int getPMouseX() {
        return mousePointer.getPX();
    }

    public int getPMouseY() {
        return mousePointer.getPY();
    }

    public void keyEvent(KeyEvent theKeyEvent) {

        int eventKeyCode = theKeyEvent.getKeyCode();
        if (eventKeyCode >= NB_KEYS || eventKeyCode < 0) {
            // invalid keyCode for reasons. 
            return;
        }

        if (theKeyEvent.getAction() == KeyEvent.PRESS) {
            // allow special keys such as backspace, arrow left,
            // arrow right to pass test when active
            if (keys[eventKeyCode] && eventKeyCode != 8 && eventKeyCode != 37 && eventKeyCode != 39) {
                return;
            }
            pressEvent(theKeyEvent);
        }

        if (theKeyEvent.getAction() == KeyEvent.RELEASE) {
            releaseEvent(theKeyEvent);

        }
        skatolo.updateKeyModifiers(theKeyEvent.getModifiers());
        checkShortcuts(theKeyEvent);

        // Let the controllers handle the KeyEvent by themselves. 
        handleKeyEvent(theKeyEvent);
    }

    private void pressEvent(KeyEvent theKeyEvent) {
        keys[theKeyEvent.getKeyCode()] = true;
        numOfActiveKeys++;
        key = theKeyEvent.getKey();
        keyCode = theKeyEvent.getKeyCode();
    }

    private void releaseEvent(KeyEvent theKeyEvent) {
        keys[theKeyEvent.getKeyCode()] = false;
        numOfActiveKeys--;
    }

    private void checkShortcuts(KeyEvent theKeyEvent) {

        if (theKeyEvent.getAction() == KeyEvent.PRESS && skatolo.areShortcutsEnabled()) {
            int n = 0;
            for (boolean b : keys) {
                n += b ? 1 : 0;
            }
            char[] c = new char[n];
            n = 0;
            for (int i = 0; i < keys.length; i++) {
                if (keys[i]) {
                    c[n++] = ((char) i);
                }
            }
            SkatoloBase.KeyCode code = new SkatoloBase.KeyCode(c);

            if (skatolo.keymap.containsKey(code)) {
                for (ControlKey ck : skatolo.keymap.get(code)) {
                    ck.keyEvent();
                }
            }
        }

    }

    public void clearKeys() {
        keys = new boolean[NB_KEYS];
        numOfActiveKeys = 0;
    }

    private void updatePointerEvents() {
        if (skatolo.isAndroid) {

            //TODO: Multi-Touch  on Android. 
            mouseEvent(skatolo.getPApplet().mouseX, skatolo.getPApplet().mouseY, skatolo.getPApplet().mousePressed);
            // updateEvents is called within mouseEvent(...)
        } else {

            if (mousePointer.isEnabled()) {
                mousePointer.updatePosition(
                        papplet().mouseX,
                        papplet().mouseY,
                        papplet().pmouseX,
                        papplet().pmouseY);
            }

            int nbPointers = 0;

            for (Pointer p : pointers.values()) {
                if (!p.isEnabled()) {
                    continue;
                }

                currentPointer = p;
                
                if(p.isTouch() || p.isHover()){
                    // touch and hover do not have a "click" 
                    
                    
                } else {
                
                    if (p.isPressed()) {
                    p.eventSent();
                    mousePressedEvent();
                }
                if (p.isReleased()) {
                    p.eventSent();
                    mouseReleasedEvent();
                }
                    
                    
                }

                //***//
                updateEvents();
                //***//

                // FIXME: I really think it is useless.
                //handleMouseOver();
                handleMouseWheelMoved();

                nbPointers++;
            }

            // No pointers ?!
            if (nbPointers == 0) {

                // FIXME: also useless ?
                resetMouseOver();
            }

        }
    }

    /**
     * @exclude draw content.
     */
    public void draw() {

        frameCount = applet.frameCount;

        updatePointerEvents();

        if (isVisible) {

            // TODO save stroke, noStroke, fill, noFill, strokeWeight
            // parameters and restore after drawing skatolo elements.
            int myRectMode = graphics.rectMode;

            int myEllipseMode = graphics.ellipseMode;

            int myImageMode = graphics.imageMode;

            graphics.pushStyle();
            graphics.rectMode(PConstants.CORNER);
            graphics.ellipseMode(PConstants.CORNER);
            graphics.imageMode(PConstants.CORNER);
            graphics.noStroke();

            if (cDrawable != null) {
                cDrawable.draw(graphics);
            }

            for (int i = 0; i < canvasList.size(); i++) {
                if ((canvasList.get(i)).mode() == Canvas.PRE) {
                    (canvasList.get(i)).draw(graphics);
                }
            }

            graphics.noStroke();
            graphics.noFill();
            int myOffsetX = (int) getPositionOfTabs().x;
            int myOffsetY = (int) getPositionOfTabs().y;
            int myHeight = 0;
            if (tabs.size() > 0) {
                for (int i = 1; i < tabs.size(); i++) {
                    if (((Tab) tabs.get(i)).isVisible()) {
                        if (myHeight < ((Tab) tabs.get(i)).height()) {
                            myHeight = ((Tab) tabs.get(i)).height();
                        }

                        // conflicts with Android, getWidth not found TODO
                        // if (myOffsetX > (papplet().getWidth()) - ((Tab) _myTabs.get(i)).width()) {
                        // myOffsetY += myHeight + 1;
                        // myOffsetX = (int) getPositionOfTabs().x;
                        // myHeight = 0;
                        // }
                        ((Tab) tabs.get(i)).setOffset(myOffsetX, myOffsetY);

                        if (((Tab) tabs.get(i)).isActive()) {
                            ((Tab) tabs.get(i)).draw(graphics);
                        }

                        if (((Tab) tabs.get(i)).updateLabel()) {
                            ((Tab) tabs.get(i)).drawLabel(graphics);
                        }
                        myOffsetX += ((Tab) tabs.get(i)).width();
                    }
                }
                ((ControllerInterface<?>) getGlobalTab()).draw(graphics);
            }
            for (int i = 0; i < canvasList.size(); i++) {
                if ((canvasList.get(i)).mode() == Canvas.POST) {
                    (canvasList.get(i)).draw(graphics);
                }
            }

            // draw Tooltip here.
            skatolo.getTooltip().draw(this);
            graphics.rectMode(myRectMode);
            graphics.ellipseMode(myEllipseMode);
            graphics.imageMode(myImageMode);
            graphics.popStyle();
        }

    }

    /**
     * @exclude draw content.
     */
    public void draw(PGraphics graphics) {
        this.graphics = graphics;
        draw();
    }

    public PGraphics graphics() {
        return this.graphics;
    }

    /**
     * Adds a custom context to a ControlWindow. Use a custom class which
     * implements the CDrawable interface
     *
     * @see skatolo.CDrawable
     * @param theDrawable CDrawable
     */
    public ControlWindow setContext(CDrawable theDrawable) {
        cDrawable = theDrawable;
        return this;
    }

    /**
     * returns the name of the control window.
     */
    public String name() {
        return name;
    }

    private void mousePressedEvent() {
        if (isVisible) {
            for (int i = 0; i < tabs.size(); i++) {
                if (((ControllerInterface<?>) tabs.get(i)).setMousePressed(true)) {
                    return;
                }
            }
        }
    }

    private void mouseReleasedEvent() {
        if (isVisible) {
            for (int i = 0; i < tabs.size(); i++) {
                ((ControllerInterface<?>) tabs.get(i)).setMousePressed(false);
            }
        }
    }

    public void setMouseWheelRotation(int theRotation) {
        if (isMouseOver()) {
            mouseWheelMoved = theRotation;
        }
    }

    // TODO: Not good code here, These scrolled should 
    // be in their own classes or anyway not here. 
    @SuppressWarnings("unchecked")
    private void handleMouseWheelMoved() {
        if (mouseWheelMoved != 0) {
            CopyOnWriteArrayList<ControllerInterface<?>> mouselist = new CopyOnWriteArrayList<ControllerInterface<?>>(mouseoverList);
            for (ControllerInterface<?> c : mouselist) {
                if (c.isVisible()) {
                    if (c instanceof Controller) {
                        ((Controller) c).onScroll(mouseWheelMoved);
                    }
                    if (c instanceof ControllerGroup) {
                        ((ControllerGroup) c).onScroll(mouseWheelMoved);
                    }
                    if (c instanceof Slider) {
                        ((Slider) c).scrolled(mouseWheelMoved);
                    } else if (c instanceof Knob) {
                        ((Knob) c).scrolled(mouseWheelMoved);
                    } else if (c instanceof Numberbox) {
                        ((Numberbox) c).scrolled(mouseWheelMoved);
                    } else if (c instanceof ListBox) {
                        ((ListBox) c).scrolled(mouseWheelMoved);
                    } else if (c instanceof DropdownList) {
                        ((DropdownList) c).scrolled(mouseWheelMoved);

                    } else if (c instanceof Textarea) {
                        ((Textarea) c).scrolled(mouseWheelMoved);
                    }
                    break;
                }
            }
        }
        mouseWheelMoved = 0;
    }

    public boolean isMousePressed() {
        return mousePointer.isPressed();
    }

    /**
     * @exclude @param theKeyEvent KeyEvent
     */
    public void handleKeyEvent(KeyEvent theKeyEvent) {
        for (int i = 0; i < tabs.size(); i++) {
            ((ControllerInterface<?>) tabs.get(i)).keyEvent(theKeyEvent);
        }
    }

    /**
     * set the color for the controller while active.
     */
    public ControlWindow setColorActive(int theColor) {
        color.setActive(theColor);
        for (int i = 0; i < getTabs().size(); i++) {
            ((Tab) getTabs().get(i)).setColorActive(theColor);
        }
        return this;
    }

    /**
     * set the foreground color of the controller.
     */
    public ControlWindow setColorForeground(int theColor) {
        color.setForeground(theColor);
        for (int i = 0; i < getTabs().size(); i++) {
            ((Tab) getTabs().get(i)).setColorForeground(theColor);
        }
        return this;
    }

    /**
     * set the background color of the controller.
     */
    public ControlWindow setColorBackground(int theColor) {
        color.setBackground(theColor);
        for (int i = 0; i < getTabs().size(); i++) {
            ((Tab) getTabs().get(i)).setColorBackground(theColor);
        }
        return this;
    }

    /**
     * set the color of the text label of the controller.
     */
    public ControlWindow setColorLabel(int theColor) {
        color.setCaptionLabel(theColor);
        for (int i = 0; i < getTabs().size(); i++) {
            ((Tab) getTabs().get(i)).setColorLabel(theColor);
        }
        return this;
    }

    /**
     * set the color of the values.
     */
    public ControlWindow setColorValue(int theColor) {
        color.setValueLabel(theColor);
        for (int i = 0; i < getTabs().size(); i++) {
            ((Tab) getTabs().get(i)).setColorValue(theColor);
        }
        return this;
    }

    /**
     * set the background color of the control window.
     */
    public ControlWindow setBackground(int theValue) {
        background = theValue;
        return this;
    }

    /**
     * get the papplet instance of the ControlWindow.
     */
    public PApplet papplet() {
        return applet;
    }

    /**
     * sets the frame rate of the control window.
     *
     * @param theFrameRate
     * @return ControlWindow
     */
    public ControlWindow frameRate(int theFrameRate) {
        applet.frameRate(theFrameRate);
        return this;
    }

    public ControlWindow show() {
        isVisible = true;
        return this;
    }

    /**
     * by default the background of a controlWindow is filled with a background
     * color every frame. to enable or disable the background from drawing, use
     * setDrawBackgorund(true/false).
     *
     * @param theFlag
     * @return ControlWindow
     */
    public ControlWindow setDrawBackground(boolean theFlag) {
        isDrawingBackground = theFlag;
        return this;
    }

    public boolean isDrawBackground() {
        return isDrawingBackground;
    }

    public boolean isVisible() {
        return isVisible;
    }

    // TODO: find the use of this 
    protected boolean isControllerActive(Controller<?> theController) {
        if (isControllerActive == null) {
            return false;
        }
        return isControllerActive.equals(theController);
    }

    protected ControlWindow setControllerActive(Controller<?> theController) {
        isControllerActive = theController;
        return this;
    }

    public ControlWindow toggleUndecorated() {
        setUndecorated(!isUndecorated());
        return this;
    }

    public ControlWindow setUndecorated(boolean theFlag) {
        if (theFlag != isUndecorated()) {
            isUndecorated = theFlag;
            applet.frame.removeNotify();
            applet.frame.setUndecorated(isUndecorated);

            // TODO: Might not work in Processing3
            applet.setSize(applet.width, applet.height);
            applet.frame.setBounds(0, 0, applet.width, applet.height);
            applet.frame.setSize(applet.width, applet.height);
            applet.frame.addNotify();
        }
        return this;
    }

    public boolean isUndecorated() {
        return isUndecorated;
    }

    public ControlWindow setPosition(int theX, int theY) {
        return setLocation(theX, theY);
    }

    public ControlWindow setLocation(int theX, int theY) {
        applet.frame.setLocation(theX, theY);
        return this;
    }

    public void setAutoDraw(boolean theFlag) {
        this.isAutoDraw = theFlag;
    }

    public ControlWindow enablePointer() {
        isUsingMouseForPointing = false;
        return this;
    }

    public ControlWindow disablePointer() {
        isUsingMouseForPointing = true;
        return this;
    }

    public boolean isPointerEnabled() {
        return !isUsingMouseForPointing;
    }

    /**
     * hide the controllers and tabs of the ControlWindow.
     */
    public ControlWindow hide() {
        isVisible = false;
        isMouseOver = false;
        return this;
    }

    public PointerList getPointers() {
        return pointers;
    }

    public char getKey() {
        return this.key;
    }

    public int getKeyCode() {
        return this.keyCode;
    }

    public boolean isIsVisible() {
        return isVisible;
    }

    public boolean isAutoDraw() {
        return isAutoDraw;
    }

    public Pointer getMousePointer() {
        return this.mousePointer;
    }

    public Pointer getCurrentPointer() {
        return this.currentPointer;
    }

    public void delete() {
    
        this.applet.unregisterMethod("pre", this);
        this.applet.unregisterMethod("draw", this);
        this.applet.unregisterMethod("keyEvent", this);
        this.applet.unregisterMethod("mouseEvent", this);
    }
    

}
