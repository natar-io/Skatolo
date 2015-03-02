package fr.inria.controlP5.gui;

/**
 *
 * @author Jeremy Laviole <jeremy.laviole@inria.fr>
 */
public class Pointer {


    public enum Type {
        MOUSE, TOUCH, HOVER
    }

    public enum Status {
        NOT_PRESSED, PRESSED, TOUCH, HOVER
    }

    static final int INVALID_LOCATION = Integer.MIN_VALUE;

    private Type type;
    private Status status;
    
    private int x = INVALID_LOCATION, y = INVALID_LOCATION;
    private int px = INVALID_LOCATION, py = INVALID_LOCATION;
    private int id;

    public Pointer() {
    }

    public Pointer setType(Type type) {
        this.type = type;
        return this;
    }
    
    public Type getType(){
        return this.type;
    }
    
    public Pointer setStatus(Status status){
        this.status = status;
        return this;
    }
    
    public Status getStatus(){
        return this.status;
    }

    public int getX(){
        return this.x;
    }
  
    public int getY(){
        return this.y;
    }
    
    public int getPX(){
        return this.px;
    }
  
    public int getPY(){
        return this.py;
    }

    public Pointer updatePosition(int x, int y) {
        updatePreviousPosition(x, y);
        this.x = x;
        this.y = y;

        return this;
    }

    private void updatePreviousPosition(int newX, int newY) {
        if (px == INVALID_LOCATION || py == INVALID_LOCATION) {
            px = x;
            py = y;
        } else {
            px = newX;
            py = newY;
        }
    }

    public int getID(){
        return this.id;
    }
    
}
