package org.example.items.weapons.spears;

import org.example.items.weapons.Spear;

public class GoldenSpear extends Spear {


    @Override
    public int cost() {
        return 470;
    }

    @Override
    public String name() {
        return "Golden Spear";
    }

    @Override
    public int damage() {
        return 150;
    }

    @Override
    public String getModelPath() {
        return "src/main/resources/Character/Weapon/GoldenSpear.png";
    }
}
