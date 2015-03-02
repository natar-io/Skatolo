package fr.inria.controlP5.gui;

import java.util.HashMap;

/**
 * Pointer List handle multi-touch and mouse as input. To get the mouse, you can
 * get get
 *
 * @author Jeremy Laviole <jeremy.laviole@inria.fr>
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

    public void removePointer(int id){
        this.remove(id);
    }
    
    public void updatePointer(int id, int x, int y) throws IllegalArgumentException{
        if (this.containsKey(id)) {
            Pointer p = this.get(id);
            p.updatePosition(x, y);
        } else {
            throw new IllegalArgumentException("Pointer ID not found");
        }
    }
    
    public void updateMousePointer(int x, int y){
        updatePointer(MOUSE, x, y);
    }
    
    public Pointer getMousePointer() {
        return get(MOUSE);
    }

}
