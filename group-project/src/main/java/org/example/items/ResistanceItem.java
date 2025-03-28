package org.example.items;

import org.example.Item;

/**
 * This abstract class represents a ResistanceItem which is a type of Item
 * which has a defence statistic.
 *
 * @author Demont Kilian
 * @author Graf Calvin
 * @author Hutzli Boris
 * @author Sottile Alan
 * @version 1.0
 */
public abstract class ResistanceItem extends Item {

    /**
     * Get the resistance value of the item.
     * @return the resistance value of the item.
     */
    public abstract int resistance();
}