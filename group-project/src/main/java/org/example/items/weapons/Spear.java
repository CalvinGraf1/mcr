package org.example.items.weapons;

import org.example.items.CloseCombatWeapon;
import org.example.items.weapons.spears.GoldenSpear;
import org.example.items.weapons.spears.IronSpear;
import org.example.items.weapons.spears.WoodenSpear;

import java.util.List;


/**
 * This abstract class represents a Spear which is a type of CloseCombatWeapon.
 * It provides a list of all available types of Spears and a method to get the range of the spear.
 *
 * @author Demont Kilian
 * @author Graf Calvin
 * @author Hutzli Boris
 * @author Sottile Alan
 * @version 1.0
 */
public abstract class Spear extends CloseCombatWeapon {
    /** A list of all available Spears*/
    private static final List<Spear> SPEARS = List.of(new WoodenSpear(), new GoldenSpear(), new IronSpear());

    /**
     * This method returns a list of all available Spears.
     * @return a list of Spears
     */
    public static List<Spear> getSpears() {
        return SPEARS;
    }

    @Override
    public int range() {
        return 15;
    }

    @Override
    public boolean isModifiable(){
        return false;
    }
}