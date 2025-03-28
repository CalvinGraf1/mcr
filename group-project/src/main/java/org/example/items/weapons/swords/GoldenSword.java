package org.example.items.weapons.swords;

import org.example.items.weapons.Sword;

/**
 * Class that represents a golden sword.
 *
 * @author Demont Kilian
 * @author Graf Calvin
 * @author Hutzli Boris
 * @author Sottile Alan
 * @version 1.0
 */
public class GoldenSword extends Sword {

    @Override
    public int baseCost() {
        return 470;
    }

    @Override
    public String name() {
        return "Golden Sword";
    }

    @Override
    public int baseDamage() {
        return 150;
    }

    @Override
    public String getModelPath() {
        return "src/main/resources/Character/Weapon/goldenSword.png";
    }
}
