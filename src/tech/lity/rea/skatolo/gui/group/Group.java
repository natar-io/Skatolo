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
package tech.lity.rea.skatolo.gui.group;

import tech.lity.rea.skatolo.Skatolo;

public class Group extends ControlGroup<Group> {

	/**
	 * Convenience constructor to extend Group.
	 * 
	 * @example use/skatoloextendController
	 * @param theskatolo
	 * @param theName
	 */
	public Group(Skatolo theskatolo, String theName) {
		this(theskatolo, theskatolo.getDefaultTab(), theName, 0, 0, 99, 9);
		theskatolo.register(theskatolo.getObjectForIntrospection(), theName, this);
	}
	
	public Group(Skatolo theskatolo, ControllerGroup<?> theParent, String theName, int theX, int theY, int theW, int theH) {
		super(theskatolo, theParent, theName, theX, theY, theW, theH);
	}

}
