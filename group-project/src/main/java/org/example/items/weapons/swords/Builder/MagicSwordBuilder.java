package org.example.items.weapons.swords.Builder;

import org.example.Validation;
import org.example.items.weapons.Sword;

/**
 * Class that defines the methods to build a magic sword.
 *
 * @author Demont Kilian
 * @author Graf Calvin
 * @author Hutzli Boris
 * @author Sottile Alan
 * @version 1.0
 */
public class MagicSwordBuilder extends AbstractSwordBuilder {

    /**
     * Constructor of the class MagicSwordBuilder.
     *
     * @param sword The sword to build.
     */
    public MagicSwordBuilder(Sword sword) {
        super(sword);
    }

    @Override
    public Validation buildGasoline() {
        Validation v = super.buildGasoline();
        if(v.getState()) this.sword.setGasoline(true);
        return v;
    }

    @Override
    public Validation buildFire() {
        Validation v = super.buildFire();
        if(v.getState()) this.sword.setFire(true);
        return v;
    }

    @Override
    public Validation buildPixieDust() {
        Validation v = super.buildPixieDust();
        if(v.getState()) this.sword.setPixieDust(true);
        return v;
    }
}
