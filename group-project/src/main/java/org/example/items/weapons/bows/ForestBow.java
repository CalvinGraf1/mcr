package org.example.items.weapons.bows;

import org.example.items.weapons.Bow;

public class ForestBow extends Bow {

        @Override
        public int cost() {
            return 400;
        }

        @Override
        public String name() {
            return "Forest Bow";
        }

        @Override
        public int damage() {
            return 100;
        }


    @Override
    public String getModelPath() {
        return "src/main/resources/Character/Weapon/ForestBow.png";
    }
}
