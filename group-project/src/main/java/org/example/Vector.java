package org.example;

/**
 * Represents a 2D vector with x and y coordinates.
 *
 * @author Demont Kilian
 * @author Graf Calvin
 * @author Hutzli Boris
 * @author Sottile Alan
 * @version 1.0
 */

public class Vector {

    /** x component */
    private int posX;

    /** y component */
    private int posY;

    /**
     * Constructs a new Vector with the specified x and y coordinates.
     *
     * @param posX the x coordinate of the vector
     * @param posY the y coordinate of the vector
     */
    public Vector(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    /**
     * Constructs a new Vector by copying the coordinates of another vector.
     *
     * @param v the vector to copy
     */
    public Vector(Vector v) {
        this.posX = v.posX;
        this.posY = v.posY;
    }

    /**
     * Returns the x coordinate of the vector.
     *
     * @return the x coordinate
     */
    public int getPosX() {
        return posX;
    }

    /**
     * Sets the x coordinate of the vector.
     *
     * @param posX the new x-coordinate
     */
    public void setPosX(int posX) {
        this.posX = posX;
    }

    /**
     * Returns the y coordinate of the vector.
     *
     * @return the y coordinate
     */
    public int getPosY() {
        return posY;
    }

    /**
     * Sets the y coordinate of the vector.
     *
     * @param posY the new y coordinate
     */
    public void setPosY(int posY) {
        this.posY = posY;
    }
}
