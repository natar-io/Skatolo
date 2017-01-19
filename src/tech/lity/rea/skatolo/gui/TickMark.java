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

import tech.lity.rea.skatolo.SkatoloConstants;
import processing.core.PGraphics;

/**
 * Tickmarks are used by the Slider and Knob controller.
 */
public class TickMark implements CDrawable {

    protected Controller<?> _myParent;

    protected Label label;
    protected boolean isLabel;

    protected int length = 4;

    public TickMark(Controller<?> theController) {
        _myParent = theController;
    }

    public void draw(PGraphics graphics) {
        draw(graphics, SkatoloConstants.HORIZONTAL);
    }

    public void draw(PGraphics graphics, int theDirection) {
        graphics.pushMatrix();
        switch (theDirection) {
            case (SkatoloConstants.HORIZONTAL):
                graphics.translate(0, length);
                graphics.rect(0, 0, 1, length);
                if (isLabel) {
                    label.draw(graphics, 0, length + 4, _myParent);
                }
                break;
            case (SkatoloConstants.VERTICAL):
                graphics.translate(-length, 0);
                graphics.rect(0, 0, length, 1);
                if (isLabel) {
                    label.draw(graphics, -label.getWidth(), 0, _myParent);
                }
                break;
        }

        graphics.popMatrix();
    }

    public void setLength(int theLength) {
        length = theLength;
    }

    public Label setLabel(String theLabeltext) {
        if (label == null) {
            label = new Label(_myParent.skatolo, theLabeltext);
            isLabel = true;
        } else {
            label.set(theLabeltext);
        }
        return label;
    }

    public Label getLabel() {
        if (label == null) {
            setLabel("?");
        }
        return label;
    }

}
