package org.example.items.weapons.bows;

import org.example.items.weapons.Bow;

public class JeweledBow extends Bow {

    @Override
    public int cost() {
        return 600;
    }

    @Override
    public String name() {
        return "Jeweled Bow";
    }

    @Override
    public int damage() {
        return 150;
    }

    @Override
    public String getModelPath() {
        return "src/main/resources/Character/Weapon/JeweledBow.png";
    }
}
