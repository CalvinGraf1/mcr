package org.example.items;

import java.util.ArrayList;
import java.util.List;

import static org.example.items.weapons.Spear.getSpears;
import static org.example.items.weapons.Sword.getSwords;

/**
 * This abstract class represents a CloseCombatWeapon which is a type of Weapon.
 * A CloseCombatWeapon is always considered as a close combat weapon.
 *
 * @author Demont Kilian
 * @author Graf Calvin
 * @author Hutzli Boris
 * @author Sottile Alan
 * @version 1.0
 */
public abstract class CloseCombatWeapon extends Weapon {
    public static List<CloseCombatWeapon> getCloseCombatWeapons() {
        List<CloseCombatWeapon> closeCombatWeapons = new ArrayList<>(getSwords());
        closeCombatWeapons.addAll(getSpears());
        return closeCombatWeapons;
    }

    @Override
    public boolean isDistance() {
        return false;
    }
}