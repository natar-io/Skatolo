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

import java.util.HashMap;

/**
 * Pointer List handle multi-touch and mouse as input. To get the mouse, you can
 * get get
 *
 * @author Jeremy Laviole <laviole@rea.lity.tech>
 */
public class PointerList extends HashMap<Integer, Pointer> {

    static final int MOUSE = -128;

    public PointerList() {
        super();
        Pointer mousePointer = new Pointer().setType(Pointer.Type.MOUSE);
        this.put(MOUSE, mousePointer);
    }

    public Pointer addPointer(int id) {
        Pointer p = new Pointer();
        this.put(id, p);
        return p;
    }

    public void removePointer(int id) {
        this.remove(id);
    }

    public void updatePointer(int id, int x, int y) throws IllegalArgumentException {
        if (this.containsKey(id)) {
            Pointer p = this.get(id);
            p.updatePosition(x, y);
        } else {
            throw new IllegalArgumentException("Pointer ID not found");
        }
    }

    public void updatePointer(int id, boolean pressed) throws IllegalArgumentException {
        if (this.containsKey(id)) {
            Pointer p = this.get(id);
            if (pressed) {
                p.setPressed();
            } else {
                p.setReleased();
            }
        } else {
            throw new IllegalArgumentException("Pointer ID not found");
        }
    }

    public void updateMousePointer(int x, int y) {
        updatePointer(MOUSE, x, y);
    }

    public Pointer getMousePointer() {
        return get(MOUSE);
    }

}
