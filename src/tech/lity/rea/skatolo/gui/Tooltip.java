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

import tech.lity.rea.skatolo.Skatolo;
import tech.lity.rea.skatolo.gui.controllers.ControllerView;
import java.util.HashMap;
import java.util.Map;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PVector;

/**
 * A tooltip can be registered for individual controllers and is activated on
 * rollover.
 *
 * @example controllers/skatolotooltip
 *
 */
public class Tooltip {

    private final Skatolo skatolo;

    private Controller<?> controller;
    private ControllerView<?> controllerView;

    private Map<Controller<?>, String> map;
    
    private PVector position = new PVector();
    private PVector currentPosition = new PVector();
    private PVector previousPosition = new PVector();

    private PVector offset = new PVector();



    private int height = 20;

    private int backgroundColor = 0xffffffb4;
    private int color = 0x00000000;

    private long startTime = 0;
    private int currentAlpha = 0;
    private int maxAlpha = 255;
        private long delayInMillis = 500;
    private int mode = Skatolo.INACTIVE;


    private Label label;
    private boolean enabled = true;
    private int borderSize;

    private int alignH = Skatolo.RIGHT;

    public Tooltip(Skatolo theskatolo) {
        skatolo = theskatolo;
        position = new PVector(-1000, -1000);
        currentPosition = new PVector();
        previousPosition = new PVector();
        offset = new PVector(0, 24, 0);
        map = new HashMap<Controller<?>, String>();
        label = new Label(skatolo, "tooltip");
        label.setColor(color);
        label.setPadding(0, 0);
        setView(new TooltipView());
        setBorder(4);
    }

    /**
     * sets the border of the tooltip, the default border is 4px.
     *
     * @param theValue
     * @return Tooltip
     */
    public Tooltip setBorder(int theValue) {
        borderSize = theValue;
        label.getStyle().setMargin(borderSize, borderSize, borderSize, borderSize);
        return this;
    }

    /**
     * returns the value of the border
     *
     * @return
     */
    public int getBorder() {
        return borderSize;
    }

    /**
     * sets the transparency of the default background, default value is 200
     *
     * @param theValue
     * @return Tooltip
     */
    public Tooltip setAlpha(int theValue) {
        maxAlpha = theValue;
        return this;
    }

    private void updateText(String theText) {
        int n = 1;
        for (char c : theText.toCharArray()) {
            if (c == '\n') {
                n++;
            }
        }
        if (label.getHeight() != label.getLineHeight() * n) {
            label.setHeight(label.getLineHeight() * n);
        }
        label.set(theText);
    }

    /**
     * @param theWindow
     */
    void draw(ControlWindow theWindow) {
        // System.out.println(previousPosition+"\t"+currentPosition+"\t"+position);
        if (enabled) {

            if (mode >= Skatolo.WAIT) {

                previousPosition.set(currentPosition);
                currentPosition.set(theWindow.getPointerX(), theWindow.getPointerY(), 0);

                if (controller != null) {
                    if (controller.getControlWindow().equals(theWindow)) {
                        switch (mode) {
                            case (Skatolo.WAIT):
                                if (moved()) {
                                    startTime = System.nanoTime();
                                }

                                if (System.nanoTime() > startTime + (delayInMillis * 1000000)) {

                                    position.set(currentPosition);
                                    alignH = Skatolo.RIGHT;
                                    if (position.x > (controller.getControlWindow().papplet().width - (getWidth() + 20))) {
                                        position.sub(new PVector(getWidth(), 0, 0));
                                        alignH = Skatolo.LEFT;
                                    }
                                    mode = Skatolo.FADEIN;
                                    startTime = System.nanoTime();
                                    currentAlpha = 0;
                                }
                                break;
                            case (Skatolo.FADEIN):
                                float t1 = System.nanoTime() - startTime;
                                currentAlpha = (int) PApplet.map(t1, 0, 200 * 1000000, 0, maxAlpha);
                                if (currentAlpha >= 250) {
                                    mode = Skatolo.IDLE;
                                    currentAlpha = 255;
                                }
                                break;
                            case (Skatolo.IDLE):
                                break;
                            case (Skatolo.FADEOUT):
                                float t2 = System.nanoTime() - startTime;
                                currentAlpha = (int) PApplet.map(t2, 0, 200 * 1000000, maxAlpha, 0);
                                if (currentAlpha <= 0) {
                                    mode = Skatolo.DONE;
                                }
                                break;
                            case (Skatolo.DONE):
                                controller = null;
                                mode = Skatolo.INACTIVE;
                                position.set(-1000, -1000, 0);
                        }

                        currentAlpha = PApplet.max(0, PApplet.min(currentAlpha, maxAlpha));

                        if (mode >= Skatolo.WAIT) {
                            currentAlpha = (mode == Skatolo.WAIT) ? 0 : currentAlpha;
                            theWindow.graphics().pushMatrix();
                            theWindow.graphics().translate(position.x, position.y);
                            theWindow.graphics().translate(offset.x, offset.y);
                            controllerView.display(theWindow.graphics(), null); // TODO: Warning HERE !
                            theWindow.graphics().popMatrix();
                        }
                        if (mode < Skatolo.FADEOUT) {
                            if (moved()) {
                                deactivate(0);
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean moved() {
        return PApplet.abs(PApplet.dist(previousPosition.x, previousPosition.y, currentPosition.x, currentPosition.y)) > 1;
    }

    /**
     * A tooltip is activated when entered by the mouse, after a given delay
     * time the Tooltip starts to fade in. Use setDelay(long) to adjust the
     * default delay time of 1000 millis.
     *
     * @param theMillis
     * @return Tooltip
     */
    public Tooltip setDelay(long theMillis) {
        delayInMillis = theMillis;
        return this;
    }

    /**
     * a Tooltip is activated when the mouse enters a controller.
     *
     * @param theController
     */
    protected void activate(Controller<?> theController) {
        if (map.containsKey(theController)) {
            startTime = System.nanoTime();
            controller = theController;
            currentPosition.set(theController.getControlWindow().getPointerX(), theController.getControlWindow().getPointerY(), 0);
            updateText(map.get(controller));
            mode = Skatolo.WAIT;
        }
    }

    protected void deactivate() {
        deactivate(1);
    }

    protected void deactivate(int theNum) {
        if (theNum == 0) {
            if (mode >= Skatolo.IDLE) {
                if (mode < Skatolo.FADEOUT) {
                    startTime = System.nanoTime();
                }
                mode = Skatolo.FADEOUT;
            }
        } else {
            mode = (mode >= Skatolo.IDLE) ? Skatolo.FADEOUT : Skatolo.DONE;
        }
    }

    /**
     * A custom view can be set for a Tooltip. The default view class can be
     * found at the bottom of the Tooltip source.
     *
     * @see skatolo.ControllerView
     * @param theDisplay
     * @return Tooltip
     */
    public Tooltip setView(ControllerView<?> theDisplay) {
        controllerView = theDisplay;
        return this;
    }

    /**
     * registers a controller with the Tooltip, when activating the tooltip for
     * a particular controller, the registered text (second parameter) will be
     * displayed.
     *
     * @param theController
     * @param theText
     * @return Tooltip
     */
    public Tooltip register(Controller<?> theController, String theText) {
        map.put(theController, theText);
        theController.registerProperty("setTooltipEnabled", "isTooltipEnabled");
        return this;
    }

    public Tooltip register(String theControllerName, String theText) {
        Controller<?> c = skatolo.getController(theControllerName);
        if (c == null) {
            return this;
        }
        map.put(c, theText);
        c.registerProperty("setTooltipEnabled", "isTooltipEnabled");
        return this;
    }

    /**
     * removes a controller from the tooltip
     *
     * @param theController
     * @return Tooltip
     */
    public Tooltip unregister(Controller<?> theController) {
        map.remove(theController);
        theController.removeProperty("setTooltipEnabled", "isTooltipEnabled");
        return this;
    }

    public Tooltip unregister(String theControllerName) {
        Controller<?> c = skatolo.getController(theControllerName);
        if (c == null) {
            return this;
        }
        return unregister(c);
    }

    /**
     * with the default display, the width of the tooltip is set automatically,
     * therefore setWidth() does not have any effect without changing the
     * default display to a custom ControllerView.
     *
     * @see skatolo.ControllerView
     * @see skatolo.Tooltip#setDisplay(ControllerView)
     * @return Tooltip
     */
    public Tooltip setWidth(int theWidth) {
        // TODO
        // _myWidth = theWidth;
        return this;
    }

    public int getWidth() {
        return label.getWidth();
    }

    /**
     * @see skatolo.Tooltip#setWidth(int)
     * @param theHeight
     * @return Tooltip
     */
    public Tooltip setHeight(int theHeight) {
        Skatolo.logger().warning("Tooltip.setHeight is disabled with this version");
        height = theHeight;
        return this;
    }

    /**
     * adds an offset to the position of the controller relative to the mouse
     * cursor's position. default offset is (10,20)
     *
     * @param theX
     * @param theY
     * @return Tooltip
     */
    public Tooltip setPositionOffset(float theX, float theY) {
        offset.x = theX;
        offset.y = theY;
        return this;
    }

    /**
     * disables the Tooltip on a global level, when disabled, tooltip will not
     * respond to any registered controller. to disable a tooltip for
     * aparticular controller, used unregister(Controller)
     *
     * @see skatolo.Tooltip#unregister(Controller)
     * @return Tooltip
     */
    public Tooltip disable() {
        enabled = false;
        return this;
    }

    /**
     * in case the tooltip is disabled, use enable() to turn the tooltip back
     * on.
     *
     * @return Tooltip
     */
    public Tooltip enable() {
        enabled = true;
        return this;
    }

    /**
     * check if the tooltip is enabled or disabled
     *
     * @return boolean
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * sets the Label to a custom label and replaces the default label.
     *
     * @param theLabel
     * @return Tooltip
     */
    public Tooltip setLabel(Label theLabel) {
        label = theLabel;
        return this;
    }

    /**
     * returns the current Label
     *
     * @return Label
     */
    public Label getLabel() {
        return label;
    }

    /**
     * sets the background color of the tooltip, the default color is a dark
     * grey
     *
     * @param theColor
     * @return Tooltip
     */
    public Tooltip setColorBackground(int theColor) {
        backgroundColor = theColor;
        return this;
    }

    /**
     * sets the text color of the tooltip's label, the default color is a white
     *
     * @param theColor
     * @return Tooltip
     */
    public Tooltip setColorLabel(int theColor) {
        color = theColor;
        label.setColor(theColor);
        return this;
    }

    class TooltipView implements ControllerView<Controller<?>> {

        public void display(PGraphics graphics, Controller<?> theController) {
            height = label.getHeight();
            graphics.fill(backgroundColor, currentAlpha);
            graphics.rect(0, 0, getWidth() + borderSize * 2, height + borderSize * 2);
            graphics.pushMatrix();
            if (alignH == Skatolo.RIGHT) {
                graphics.translate(6, 0);
            } else {
                graphics.translate(getWidth() - 6, 0);
            }
            graphics.triangle(0, 0, 4, -4, 8, 0);
            graphics.popMatrix();
            int a = (int) (PApplet.map(currentAlpha, 0, maxAlpha, 0, 255));
            label.setColor(a << 24 | (color >> 16) << 16 | (color >> 8) << 8 | (color >> 0) << 0);
            label.draw(graphics, 0, 0, theController);
        }
    }
}
