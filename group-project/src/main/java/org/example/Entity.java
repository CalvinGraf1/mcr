package org.example;

/**
 * Represents a moveable object in the game.
 *
 * @author Demont Kilian
 * @author Graf Calvin
 * @author Hutzli Boris
 * @author Sottile Alan
 * @version 1.0
 */
public abstract class Entity {
    /**
     * The position vector of the entity.
     */
    protected Vector position;

    /**
     * The side that the entity belongs to.
     */
    protected Side side;

    /**
     * Returns the x-coordinate of the entity's position.
     *
     * @return the x-coordinate of the entity's position
     */
    public int getPosX() {
        return this.position.getPosX();
    }

    /**
     * Returns the y-coordinate of the entity's position.
     *
     * @return the y-coordinate of the entity's position
     */
    public int getPosY() {
        return this.position.getPosY();
    }

    /**
     * Returns the side that the entity belongs to.
     *
     * @return the side of the entity as a Side enum value
     */
    public Side getSide() {
        return this.side;
    }

    /**
     * Sets the x-coordinate of the entity's position.
     *
     * @param x the new x-coordinate to set
     */
    public void setPosX(int x) {
        this.position.setPosX(x);
    }

    /**
     * Sets the y-coordinate of the entity's position.
     *
     * @param y the new y-coordinate to set
     */
    public void setPosY(int y) {
        this.position.setPosY(y);
    }

    /**
     * Returns the position vector of the entity.
     *
     * @return the position vector of the entity
     */
    public Vector getPosition() {
        return position;
    }

    /**
     * Sets the position vector of the entity to the specified vector.
     *
     * @param v the new position vector to set
     */
    public void setPosition(Vector v) {
        position = v;
    }
}
