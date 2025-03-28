package org.example.items.weapons;

import org.example.items.CloseCombatWeapon;
import org.example.items.weapons.swords.GoldenSword;
import org.example.items.weapons.swords.IronSword;
import org.example.items.weapons.swords.WoodenSword;

import java.util.List;



/**
 * This abstract class represents a Sword which is a type of CloseCombatWeapon.
 * It provides a list of all available types of Swords and a method to get the range of the sword.
 *
 * @author Demont Kilian
 * @author Graf Calvin
 * @author Hutzli Boris
 * @author Sottile Alan
 * @version 1.0
 */
public abstract class Sword extends CloseCombatWeapon {
    private boolean gasoline;
    private boolean fire;
    private boolean pixieDust;

    /** A list of all available Swords */
    private static final List<Sword> SWORDS = List.of(new WoodenSword(), new GoldenSword(), new IronSword());

    /**
     * Sets the gasoline attribute to the given value.
     * @param gasoline True if the sword has gasoline, false otherwise.
     */
    public void setGasoline(boolean gasoline) {
        this.gasoline = gasoline;
    }

    /**
     * Sets the fire attribute to the given value.
     * @param fire True if the sword is on fire, false otherwise.
     */
    public void setFire(boolean fire) {
        this.fire = fire;
    }

    /**
     * Sets the pixie dust attribute to the given value.
     * @param pixieDust True if the sword has pixie dust, false otherwise.
     */
    public void setPixieDust(boolean pixieDust) {
        this.pixieDust = pixieDust;
    }

    /**
     * This method is used to check if the sword has gasoline.
     * @return True if the sword has gasoline, false otherwise.
     */
    public boolean hasGasoline() {
        return gasoline;
    }

    /**
     * This method is used to check if the sword is on fire.
     * @return True if the sword is on fire, false otherwise.
     */
    public boolean hasFire() {
        return fire;
    }

    /**
     * This method is used to check if the sword has pixie dust.
     * @return True if the sword has pixie dust, false otherwise.
     */
    public boolean hasPixieDust() {
        return pixieDust;
    }

    /**
     * This method returns a list of all available Swords.
     * @return a list of Swords
     */
    public static List<Sword> getSwords() {
        return SWORDS;
    }

    @Override
    public int range() {
        return 10;
    }

    @Override
    public boolean isModifiable(){ // interface ?
        return true;
    }

    /**
     * This method is used to get the cost of gasoline.
     * @return the cost of gasoline
     */
    public int gasolineCost(){
        return 10;
    }

    /**
     * This method is used to get the cost of fire.
     * @return the cost of fire
     */
    public int fireCost(){
        return 20;
    }

    /**
     * This method is used to get the cost of pixie dust.
     * @return the cost of pixie dust
     */
    public int pixieDustCost(){
        return 30;
    }

    /**
     * This method is used to get the boost of gasoline.
     * @return the boost of gasoline
     */
    public int gasolineBoost(){
        return 10;
    }

    /**
     * This method is used to get the boost of fire.
     * @return the boost of fire
     */
    public int fireBoost(){
        return 20;
    }

    /**
     * This method is used to get the boost of pixie dust.
     * @return the boost of pixie dust
     */
    public int pixieDustBoost(){
        return 30;
    }

    /**
     * This method is used to get the total damage to the sword.
     * @return the total domage to the sword
     */
    public int damage(){
        return baseDamage() + (gasoline ? gasolineBoost() : 0) + (fire ? fireBoost() : 0) + (pixieDust ? pixieDustBoost() : 0);
    }

    /**
     * This method is used to get the total cost of the sword.
     * @return the total cost of the sword
     */
    public int cost(){
        return baseCost() + (gasoline ? gasolineCost() : 0) + (fire ? fireCost() : 0) + (pixieDust ? pixieDustCost() : 0);
    }

    /**
     * This method is used to get the base cost of the sword.
     * @return the base cost of the sword
     */
    public abstract int baseCost();

    /**
     * This method is used to get the base damage to the sword.
     * @return the base damage to the sword
     */
    protected abstract int baseDamage();

    /**
     * This method is used to reset the sword.
     */
    public void reset(){
        gasoline = false;
        fire = false;
        pixieDust = false;
    }

    /**
     * This method is used to get the path name of the sword.
     * @return the path name of the sword
     */
    public String pathName() {
        if (pixieDust) {
            return "magicSword";
        } else if (fire) {
            return "fireSword";
        } else if (gasoline) {
            return "gasolineSword";
        }
        return getClass().getSimpleName();
    }

}
