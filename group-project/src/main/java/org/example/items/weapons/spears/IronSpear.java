package org.example.items.weapons.spears;

import org.example.items.weapons.Spear;

public class IronSpear extends Spear {


    @Override
    public int cost() {
        return 600;
    }

    @Override
    public String name() {
        return "Iron Spear";
    }

    @Override
    public int damage() {
        return 200;
    }


    @Override
    public String getModelPath() {
        return "src/main/resources/Character/Weapon/IronSpear.png";
    }
}
