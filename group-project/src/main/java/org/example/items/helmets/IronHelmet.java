package org.example.items.helmets;

import org.example.items.Helmet;

/**
 * Class representing the iron helmet.
 *
 * @author Demont Kilian
 * @author Graf Calvin
 * @author Hutzli Boris
 * @author Sottile Alan
 * @version 1.0
 */
public class IronHelmet extends Helmet {


    @Override
    public int cost() {
        return 50;
    }

    @Override
    public String name() {
        return "Iron Helmet";
    }

    @Override
    public int resistance() {
        return 60;
    }

    @Override
    public String getModelPath() {
        return null;
    }
}
