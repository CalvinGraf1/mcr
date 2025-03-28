package org.example.items.weapons.swords;

import org.example.items.weapons.Sword;

/**
 * Class representing the Wooden Sword weapon.
 *
 * @author Demont Kilian
 * @author Graf Calvin
 * @author Hutzli Boris
 * @author Sottile Alan
 * @version 1.0
 */
public class WoodenSword extends Sword {

    @Override
    public int baseCost() {
        return 200;
    }

    @Override
    public String name() {
        return "Wooden Sword";
    }

    @Override
    public int baseDamage() {
        return 50;
    }

    @Override
    public String getModelPath() {
        return "src/main/resources/Character/Weapon/WoodenSword.png";
    }
}
