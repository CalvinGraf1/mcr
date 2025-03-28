package org.example.items.weapons.swords.Builder;

import org.example.Validation;
import static org.example.Validation.*;
import org.example.items.weapons.Sword;

/**
 * Abstract class that defines the methods to build a sword.
 *
 * @author Demont Kilian
 * @author Graf Calvin
 * @author Hutzli Boris
 * @author Sottile Alan
 * @version 1.0
 */
public class AbstractSwordBuilder {
    protected Sword sword;

    /**
     * Constructor of the class AbstractSwordBuilder.
     *
     * @param sword The sword to build.
     */
    protected AbstractSwordBuilder(Sword sword) {
        this.sword = sword;
    }

    /**
     * Method to build gasoline for the sword.
     * @return A validation object that contains the result of the operation.
     */
    public Validation buildGasoline() {
        if(this.sword.hasGasoline()) return invalid("Impossible to put gasoline on a sword that already has some");
        if(this.sword.hasFire()) return invalid("Impossible to put gasoline on a sword that already on fire");
        if(this.sword.hasPixieDust()) return invalid("Impossible to put gasoline on a sword that already has snake oil on it");
        this.sword.setGasoline(false);
        return valid();
    }

    /**
     * Method to build fire for the sword.
     * @return A validation object that contains the result of the operation.
     */
    public Validation buildFire() {
        if(this.sword.hasFire()) return invalid("Impossible to set fire to a sword that's already on fire");
        if(this.sword.hasPixieDust()) return invalid("Impossible to set fire a sword that already has snake oil on it");
        this.sword.setFire(false);
        return valid();
    }

    /**
     * Method to build pixie dust for the sword.
     * @return A validation object that contains the result of the operation.
     */
    public Validation buildPixieDust() {
        if(this.sword.hasFire()) return invalid("You can't put pixie dust on a sword that's already on fire");
        if(this.sword.hasPixieDust()) return invalid("Impossible to put pixie dust on a sword that already has it");
        this.sword.setPixieDust(false);
        return valid();
    }

    /**
     * Method to get the sword.
     * @return The sword.
     */
    public Sword getSword() {
        return this.sword;
    }
}
