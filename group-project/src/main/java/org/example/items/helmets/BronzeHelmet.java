package org.example.items.helmets;

import org.example.items.Helmet;

/**
 * Class representing the bronze helmet.
 *
 * @author Demont Kilian
 * @author Graf Calvin
 * @author Hutzli Boris
 * @author Sottile Alan
 * @version 1.0
 */
public class BronzeHelmet extends Helmet {

    @Override
    public int cost() {
        return 30;
    }

    @Override
    public String name() {
        return "Bronze Helmet";
    }

    @Override
    public int resistance() {
        return 30;
    }

    @Override
    public String getModelPath() {
        return null;
    }
}
