package org.example.items;

import org.example.items.helmets.BronzeHelmet;
import org.example.items.helmets.IronHelmet;
import org.example.items.helmets.GoldenHelmet;

import java.util.List;

/**
 * This abstract class represents a Helmet which is a type of ResistanceItem.
 * It provides a list of all available types of Helmets.
 *
 * @author Demont Kilian
 * @author Graf Calvin
 * @author Hutzli Boris
 * @author Sottile Alan
 * @version 1.0
 */
public abstract class Helmet extends ResistanceItem {
    private static final List<Helmet> HELMETS = List.of(new BronzeHelmet(), new IronHelmet(), new GoldenHelmet());

    /**
     * This method returns a list of all available Helmets.
     * @return a list of Helmets
     */
    public static List<Helmet> getHelmets() {
        return HELMETS;
    }
}