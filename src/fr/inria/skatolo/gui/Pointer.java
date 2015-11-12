package fr.inria.skatolo.gui;

import processing.core.PVector;

/**
 *
 * @author Jeremy Laviole <jeremy.laviole@inria.fr>
 */
public class Pointer {

    static public Pointer invalidPointer = new Pointer();

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

    private boolean enabled = true;

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

    public Pointer updatePosition(int x, int y, int px, int py) {
        this.x = x;
        this.y = y;
        this.px = px;
        this.py = py;
        return this;
    }

    private void updatePreviousPosition(int newX, int newY) {
        if (px == INVALID_LOCATION || py == INVALID_LOCATION) {
            px = newX;
            py = newY;
        } else {
            px = x;
            py = y;
        }
    }

    public void tick() {
        updatePosition(x, y);
    }

    public void eventSent() {
        if (this.status == Status.PRESSED) {
            this.status = Status.STILL_PRESSED;
        }
        if (this.status == Status.RELEASED) {
            this.status = Status.STILL_RELEASED;
        }
    }

    public boolean isPressed() {
        return this.status == Status.PRESSED;
    }

    public boolean isDragged() {
        return this.status == Status.STILL_PRESSED;
    }

    public boolean isMaintainedPressed() {
        return this.status == Status.STILL_PRESSED;
    }

    public boolean isReleased() {
        return this.status == Status.RELEASED;
    }

    public void setPressed() {
        this.status = Status.PRESSED;
    }

    public void setReleased() {
        this.status = Status.RELEASED;
    }

    public void resetPress() {
        this.status = Status.STILL_RELEASED;
    }

    public int getID() {
        return this.id;
    }

    public void enable() {
        this.enabled = true;
    }

    public void disable() {
        this.enabled = false;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public boolean isTouch() {
        return this.type == Type.TOUCH;
    }

    public boolean isHover() {
        return this.type == Type.HOVER;
    }

}
