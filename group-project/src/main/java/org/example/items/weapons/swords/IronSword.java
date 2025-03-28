package org.example.items.weapons.swords;

import org.example.items.weapons.Sword;

/**
 * Class representing the Iron Sword weapon.
 *
 * @author Demont Kilian
 * @author Graf Calvin
 * @author Hutzli Boris
 * @author Sottile Alan
 * @version 1.0
 */
public class IronSword extends Sword {

    @Override
    public int baseCost() {
        return 600;
    }

    @Override
    public String name() {
        return "Iron Sword";
    }

    @Override
    public int baseDamage() {
        return 200;
    }

    @Override
    public String getModelPath() {
        return "src/main/resources/Character/Weapon/IronSword.png";
    }
}
