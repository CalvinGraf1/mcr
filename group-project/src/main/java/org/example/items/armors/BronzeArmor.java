package org.example.items.armors;

import org.example.items.Armor;

/**
 * Class representing the bronze armor.
 *
 * @author Demont Kilian
 * @author Graf Calvin
 * @author Hutzli Boris
 * @author Sottile Alan
 * @version 1.0
 */
public class BronzeArmor extends Armor {


    @Override
    public int cost() {
        return 300;
    }

    @Override
    public String name() {
        return "Bronze Armor";
    }

    @Override
    public int resistance() {
        return 50;
    }

    @Override
    public String getModelPath() {
        return null;
    }
}
