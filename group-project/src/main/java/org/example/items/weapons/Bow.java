package org.example.items.weapons;

import org.example.Arrow;
import org.example.Vector;
import org.example.items.DistanceWeapon;
import org.example.items.weapons.bows.BasicBow;
import org.example.items.weapons.bows.ForestBow;
import org.example.items.weapons.bows.JeweledBow;

import javax.swing.text.Position;
import java.util.List;

/**
 * This abstract class represents a Bow which is a type of DistanceWeapon.
 * It provides a list of all available types of Bows and a method to get the range of the bow.
 *
 * @author Demont Kilian
 * @author Graf Calvin
 * @author Hutzli Boris
 * @author Sottile Alan
 * @version 1.0
 */
public abstract class Bow extends DistanceWeapon {
    /** A list of all available Bows*/
    private static final List<Bow> BOWS = List.of(new BasicBow(), new ForestBow(), new JeweledBow());

    /** The arrow launched by the bow*/
    private Arrow arrow;

    /**
     * This method returns a list of all available Bows.
     * @return a list of Bows
     */
    public static List<Bow> getBows() {
        return BOWS;
    }

    @Override
    public int range() {
        return 100;
    }

    @Override
    public boolean isModifiable(){
        return false;
    }

    /**
     * This method is used to get the arrow of the bow.
     * @return the arrow of the bow
     */
    public Arrow getArrow() {
        return arrow;
    }

    /**
     * This method is used to set the arrow of the bow.
     * @param a the arrow to be set
     */
    public void setArrow(Arrow a){
        this.arrow = a;
    }
}