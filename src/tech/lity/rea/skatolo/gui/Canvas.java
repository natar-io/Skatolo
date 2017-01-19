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

import processing.core.PApplet;
import processing.core.PGraphics;

/**
 * Use a Canvas to draw custom graphics into a control window or the default sketch window.
 * 
 * The Canvas is an abstract class and must be extended by your custom Canvas class, see the
 * skatolocanvas example for details.
 * 
 * @example controllers/skatolocanvas
 * 
 */

public abstract class Canvas {

	public final static int PRE = 0;
	public final static int POST = 1;

        protected ControlWindow _myControlWindow;
	protected int _myMode = PRE;

	public void setup(PApplet theApplet) {
	}

        /**
	 * controlWindowCanvas is an abstract class and therefore needs to be extended by your class.
	 * draw(PApplet theApplet) is the only method that needs to be overwritten.
	 */
	public abstract void draw(PGraphics graphics);


	/**
	 * move a canvas to another controlWindow
	 * 
	 * @param theControlWindow
	 */
	public void moveTo(ControlWindow theControlWindow) {
		if (_myControlWindow != null) {
			_myControlWindow.removeCanvas(this);
		}
		theControlWindow.addCanvas(this);
	}


	/**
	 * get the drawing mode of a Canvas. this can be PRE or POST.
	 * 
	 * @return
	 */
	public final int mode() {
		return _myMode;
	}


	/**
	 * set the drawing mode to PRE. PRE is the default.
	 */
	public final void pre() {
		setMode(PRE);
	}


	/**
	 * set the drawing mode to POST.
	 */
	public final void post() {
		setMode(POST);
	}


	/**
	 * 
	 * @param theMode
	 */
	public final void setMode(int theMode) {
		if (theMode == PRE) {
			_myMode = PRE;
		}
		else {
			_myMode = POST;
		}
	}


	protected final void setControlWindow(ControlWindow theControlWindow) {
		_myControlWindow = theControlWindow;
	}


	public final ControlWindow window() {
		return _myControlWindow;
	}
}
