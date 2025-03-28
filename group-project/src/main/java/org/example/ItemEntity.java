package org.example;

/**
 * This class represents entities that have a fixed model
 *
 * @author Demont Kilian
 * @author Graf Calvin
 * @author Hutzli Boris
 * @author Sottile Alan
 * @version 1.0
 */
public abstract class ItemEntity extends Entity{

    /**
     * Returns the model path of the item.
     * @return the model path as a String
     */
    public abstract String getModelPath();
}
