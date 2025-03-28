package org.example;

import org.example.items.weapons.Sword;
import org.example.items.weapons.swords.Builder.AbstractSwordBuilder;
import org.example.items.weapons.swords.Builder.NormalSwordBuilder;
import org.example.items.weapons.swords.Builder.MagicSwordBuilder;

/**
 * Enum representing the different types of swords.
 *
 * @author Demont Kilian
 * @author Graf Calvin
 * @author Hutzli Boris
 * @author Sottile Alan
 * @version 1.0
 */
public enum SwordType {
    STANDARD("Standard") {
        @Override
        public AbstractSwordBuilder createBuilder(Sword sword) {
            return new NormalSwordBuilder(sword);
        }
    },
    MAGIC("Magic") {
        @Override
        public AbstractSwordBuilder createBuilder(Sword sword) {
            return new MagicSwordBuilder(sword);
        }
    };

    private String name;

    /**
     * Constructor for the SwordType enum.
     * @param name the name of the sword type
     */
    SwordType(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the sword type.
     * @return the name of the sword type
     */
    public String getName() {
        return name;
    }

    /**
     * Creates a new sword builder based on the sword type.
     * @param sword the sword to build
     * @return a new sword builder
     */
    public abstract AbstractSwordBuilder createBuilder(Sword sword);
}
