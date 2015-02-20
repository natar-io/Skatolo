/* 
 *  controlP5 is a processing gui library.
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
package controlP5;

import processing.core.PApplet;
import processing.core.PGraphics;

/**
 * Tickmarks are used by the Slider and Knob controller.
 */
public class TickMark implements CDrawable {

	protected Controller<?> _myParent;

	protected int _myLen = 4;

	protected Label _myLabel;

	protected boolean isLabel;

	public TickMark(Controller<?> theController) {
		_myParent = theController;
	}

	public void draw(PGraphics graphics) {
		draw(graphics, ControlP5Constants.HORIZONTAL);
	}

	public void draw(PGraphics graphics, int theDirection) {
		graphics.pushMatrix();
		switch (theDirection) {
		case (ControlP5Constants.HORIZONTAL):
			graphics.translate(0, _myLen);
			graphics.rect(0, 0, 1, _myLen);
			if (isLabel) {
				_myLabel.draw(graphics, 0, _myLen + 4, _myParent);
			}
			break;
		case (ControlP5Constants.VERTICAL):
			graphics.translate(-_myLen, 0);
			graphics.rect(0, 0, _myLen, 1);
			if (isLabel) {
				_myLabel.draw(graphics, -_myLabel.getWidth(), 0, _myParent);
			}
			break;
		}

		graphics.popMatrix();
	}

	public void setLength(int theLength) {
		_myLen = theLength;
	}

	public Label setLabel(String theLabeltext) {
		if (_myLabel == null) {
			_myLabel = new Label(_myParent.cp5,theLabeltext);
			isLabel = true;
		} else {
			_myLabel.set(theLabeltext);
		}
		return _myLabel;
	}

	public Label getLabel() {
		if (_myLabel == null) {
			setLabel("?");
		}
		return _myLabel;
	}

}
