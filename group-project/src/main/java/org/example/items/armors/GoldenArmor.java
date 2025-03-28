package org.example.items.armors;

import org.example.items.Armor;

/**
 * Class representing the golden body.
 *
 * @author Demont Kilian
 * @author Graf Calvin
 * @author Hutzli Boris
 * @author Sottile Alan
 * @version 1.0
 */
public class GoldenArmor extends Armor {


    @Override
    public int cost() {
        return 300;
    }

    @Override
    public String name() {
        return "Golden Armor";
    }

    @Override
    public int resistance() {
        return 200;
    }

    @Override
    public String getModelPath() {
        return null;
    }
}
