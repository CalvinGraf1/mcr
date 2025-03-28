package org.example.items;

import org.example.Item;

import java.util.ArrayList;
import java.util.List;

import static org.example.items.CloseCombatWeapon.getCloseCombatWeapons;
import static org.example.items.DistanceWeapon.getDistanceWeapons;

/**
 * Abstract class representing a weapon.
 * This class extends the Item class and provides additional properties for weapons such as damage and range.
 *
 * @author Demont Kilian
 * @author Graf Calvin
 * @author Hutzli Boris
 * @author Sottile Alan
 * @version 1.0
 */
public abstract class Weapon extends Item implements Cloneable {

    /**
     * This method returns a list of all available weapons.
     * @return a list of weapons
     */
    public static List<Weapon> getWeapons() {
        List<Weapon> result = new ArrayList<>();
        result.addAll(getDistanceWeapons());
        result.addAll(getCloseCombatWeapons());

        return result;
    }


    /**
     * Get the cost of the weapon.
     * @return the cost of the weapon.
     */
    public abstract int cost();

    /**
     * Get the name of the weapon.
     * @return the name of the weapon.
     */
    public abstract String name();

    /**
     * Get the damage value of the weapon.
     * @return the damage value of the weapon.
     */
    public abstract int damage();

    /**
     * Get the range of the weapon.
     * @return the range of the weapon.
     */
    public abstract int range();

    /**
     * Check if the weapon is a distance weapon.
     * @return true if the weapon is a distance weapon, false otherwise.
     */
    public abstract boolean isDistance();

    /**
     * Check if the weapon is modifiable.
     * @return true if the weapon is modifiable, false otherwise.
     */
    public abstract boolean isModifiable();

    /**
     * Get the width of the weapon.
     * @return the width of the weapon.
     */
    public int getWeaponWidth(){
        return 150;
    }

    @Override
    public Weapon clone() {
        try {
            return (Weapon) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}