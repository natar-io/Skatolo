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

import java.util.List;
import java.util.Vector;

/**
 * Stores objects of type ControllerInterface and CDrawable, mainly for internal
 * use.
 */
public class ControllerList {

	protected List<ControllerInterface<?>> controllers;
	protected List<CDrawable> drawables;

	public ControllerList() {
		controllers = new Vector<ControllerInterface<?>>();
		drawables = new Vector<CDrawable>();
	}

	public void add(ControllerInterface<?> theController) {
		if (controllers.indexOf(theController) < 0) {
			controllers.add(theController);
		}
	}

	public void remove(ControllerInterface<?> theController) {
		controllers.remove(theController);
	}

	public void addDrawable(CDrawable theController) {
		if (drawables.indexOf(theController) < 0) {
			drawables.add(theController);
		}
	}

	public void removeDrawable(CDrawable theController) {
		drawables.remove(theController);
	}

	public ControllerInterface<?> get(int theIndex) {
		return controllers.get(theIndex);
	}

	public List<ControllerInterface<?>> get() {
		return controllers;
	}

	public CDrawable getDrawable(int theIndex) {
		return drawables.get(theIndex);
	}

	public List<CDrawable> getDrawables() {
		return drawables;
	}

	public int sizeDrawable() {
		return drawables.size();
	}

	public int size() {
		return controllers.size();
	}

	public void clear() {
		controllers.clear();
	}

	public void clearDrawable() {
		drawables.clear();
	}

}
