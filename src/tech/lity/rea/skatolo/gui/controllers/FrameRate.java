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
import tech.lity.rea.skatolo.gui.group.Tab;
import processing.core.PApplet;
import processing.core.PGraphics;

public class FrameRate extends Textlabel {

	private int _myInterval = 10;

	private float _myIntervalSum = 0;

	private int cnt = 0;


	public FrameRate(final Skatolo theskatolo, final Tab theParent, final String theValue, final int theX, final int theY) {
		super(theskatolo, theParent, "framerate", "-", theX, theY);
	}


	public FrameRate setInterval(int theValue) {
		_myInterval = theValue;
		return this;
	}


	@Override public void draw(PGraphics graphics) {
		if ((cnt++) % _myInterval == 0) {
			setText("" + PApplet.round(_myIntervalSum / _myInterval));
			_myIntervalSum = 0;
		}
		_myIntervalSum += skatolo.getPApplet().frameRate;
		super.draw(graphics);
	}

}
