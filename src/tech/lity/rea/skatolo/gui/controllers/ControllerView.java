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

import processing.core.PApplet;
import processing.core.PGraphics;

/**
 * The interface ControllerView can be used to define custom displays for
 * controllers.
 * 
 * @see skatolo.draw(processing.core.PApplet)
 * @see skatolo.setView(ControlleView)
 * 
 * @example use/skatolocustomDisplay
 */
public interface ControllerView<T> {

	/**
	 * draws your custom controllers. display() will be called by a controller's
	 * draw() function and will pass a reference of PApplet as well as the
	 * Controller itself to your custom display class.
	 * 
	 * @param graphics
	 * @param theController
	 */
        public void display(PGraphics graphics, T theController);

}
