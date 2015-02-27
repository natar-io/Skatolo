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
package fr.inria.controlP5.events;

import fr.inria.controlP5.gui.Controller;

/**
 * <p>
 * A CallbackEvent is send when a controller action such as enter, leave, press, etc has occurs.
 * </p>
 * 
 * @example use/ControlP5callback
 */
public class CallbackEvent {

	private final int _myAction;
	private final Controller<?> _myController;

	public CallbackEvent(Controller<?> theController, int theAction) {
		_myController = theController;
		_myAction = theAction;
	}


	/**
	 * 
	 * @return int Returns an int value of either one of the following static variables
	 *         ControlP5.ACTION_PRESSED, ControlP5.ACTION_ENTER, ControlP5.ACTION_LEAVE,
	 *         ControlP5.ACTION_RELEASED, ControlP5.ACTION_RELEASEDOUTSIDE,
	 *         ControlP5.ACTION_BROADCAST
	 */
	public int getAction() {
		return _myAction;
	}


	/**
	 * Returns the Controller that triggered the Callback Event.
	 * 
	 * @return Controller
	 */
	public Controller<?> getController() {
		return _myController;
	}

}
