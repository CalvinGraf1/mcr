package org.example.items;

import java.util.ArrayList;
import java.util.List;

import static org.example.items.weapons.Bow.getBows;

/**
 * This abstract class represents a ranged weapon, which is a type of ranged combat weapon.
 * A DistanceWeapon is always considered as a distance weapon.
 *
 * @author Demont Kilian
 * @author Graf Calvin
 * @author Hutzli Boris
 * @author Sottile Alan
 * @version 1.0
 */
public abstract class DistanceWeapon extends Weapon {
    public static List<DistanceWeapon> getDistanceWeapons() {
        return new ArrayList<>(getBows());
    }

    @Override
    public boolean isDistance() {
        return true;
    }
}