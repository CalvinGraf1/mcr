package org.example.items.armors;

import org.example.items.Armor;

/**
 * Class representing the iron body.
 *
 * @author Demont Kilian
 * @author Graf Calvin
 * @author Hutzli Boris
 * @author Sottile Alan
 * @version 1.0
 */
public class IronArmor extends Armor {

    @Override
    public int cost() {
        return 500;
    }

    @Override
    public String name() {
        return "Iron Armor";
    }

    @Override
    public int resistance() {
        return 100;
    }

    @Override
    public String getModelPath() {
        return null;
    }
}
