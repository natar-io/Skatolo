package fr.inria.controlP5.gui;

/**
 *
 * @author Jeremy Laviole <jeremy.laviole@inria.fr>
 */
public class Pointer {

    static Pointer invalidPointer = new Pointer();
    
    public enum Type {

        MOUSE, TOUCH, HOVER
    }

    public enum Status {

        STILL_RELEASED, RELEASED, STILL_PRESSED, PRESSED, TOUCH, HOVER
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

    public Type getType() {
        return this.type;
    }

    public Pointer setStatus(Status status) {
        this.status = status;
        return this;
    }

    public Status getStatus() {
        return this.status;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getPX() {
        return this.px;
    }

    public int getPY() {
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
    
    public void eventSent(){
        if(this.status == Status.PRESSED){
            this.status = Status.STILL_PRESSED;
        }
        if(this.status == Status.RELEASED){
            this.status = Status.STILL_RELEASED;
        }
    }

    public boolean isPressed() {
        return this.status == Status.PRESSED;
    }

    public boolean isReleased() {
        return this.status == Status.RELEASED;
    }

    public void setPressed() {
        this.status = Status.PRESSED;
    }
 
    public void resetPress() {
        this.status = Status.STILL_RELEASED;
    }

    public int getID() {
        return this.id;
    }

}
