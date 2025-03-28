package org.example;


/**
 * Abstract class representing an item.
 * This class provides the structure for items with an id, cost, and name.
 * Subclasses must implement all abstract methods.
 *
 * @author Demont Kilian
 * @author Graf Calvin
 * @author Hutzli Boris
 * @author Sottile Alan
 * @version 1.0
 */
public abstract class Item extends ItemEntity {

    /** represents the position of the item*/
    private Vector position;

    /**
     * Get the simple name of the class of this object.
     * Useful to find the path of associated images.
     *
     * For example, an asset image for a GoldenSword object would be named
     * "GoldenSword_1.png" (1 is the position at 90° degrees).
     * @return the simple name of the class of this object.
     */
    public String pathName() {
        return getClass().getSimpleName();
    }

    /**
     * Get the cost of the item.
     * @return the cost of the item.
     */
    public abstract int cost();

    /**
     * Get the name of the item.
     * @return the name of the item.
     */
    public abstract String name();

    @Override
    public String toString() {
        return name();
    }

    /**
     * Returns the position vector of this object.
     *
     * @return the position vector of this object
     */
    public Vector getPosition() {
        return this.position;
    }

    /**
     * Sets the position vector of this object to the specified vector.
     *
     * @param v the new position vector to set
     */
    public void setPosition(Vector v) {
        position = v;
    }
}