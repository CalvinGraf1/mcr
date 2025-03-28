package org.example.items.weapons.spears;

import org.example.items.weapons.Spear;

public class WoodenSpear extends Spear {

    @Override
    public int cost() {
        return 200;
    }

    @Override
    public String name() {
        return "Wooden Spear";
    }

    @Override
    public int damage() {
        return 50;
    }

    @Override
    public String getModelPath() {
        return "src/main/resources/Character/Weapon/WoodenSpear.png";
    }
}
