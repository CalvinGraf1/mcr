package org.example.items;

import org.example.items.armors.BronzeArmor;
import org.example.items.armors.GoldenArmor;
import org.example.items.armors.IronArmor;

import java.util.List;

/**
 * This abstract class represents an Armor which is a type of ResistanceItem.
 * It provides a list of all available types of Armors.
 *
 * @author Demont Kilian
 * @author Graf Calvin
 * @author Hutzli Boris
 * @author Sottile Alan
 * @version 1.0
 */
public abstract class Armor extends ResistanceItem {
    private static final List<Armor> ARMORS = List.of(new BronzeArmor(), new IronArmor(), new GoldenArmor());

    /**
     * This method returns a list of all available Armors.
     * @return a list of Armors
     */
    public static List<Armor> getArmors() {
        return ARMORS;
    }
}