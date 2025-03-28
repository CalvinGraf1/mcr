package org.example.items.weapons.bows;

import org.example.items.weapons.Bow;

public class BasicBow extends Bow {

    @Override
    public int cost() {
        return 200;
    }

    @Override
    public String name() {
        return "Basic Bow";
    }

    @Override
    public int damage() {
        return 30;
    }

    @Override
    public String getModelPath() {
        return "src/main/resources/Character/Weapon/BasicBow.png";
    }
}
