package org.example.items.helmets;

import org.example.items.Helmet;

/**
 * Class representing the golden helmet.
 *
 * @author Demont Kilian
 * @author Graf Calvin
 * @author Hutzli Boris
 * @author Sottile Alan
 * @version 1.0
 */
public class GoldenHelmet extends Helmet {

    @Override
    public int cost() {
        return 80;
    }

    @Override
    public String name() {
        return "Golden Helmet";
    }

    @Override
    public int resistance() {
        return 100;
    }

    @Override
    public String getModelPath() {
        return null;
    }
}
