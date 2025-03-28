package org.example.items.weapons.swords.Builder;

import org.example.Validation;
import org.example.items.weapons.Sword;

import static org.example.Validation.invalid;

/**
 * Abstract class representing a character in the game.
 *
 * @author Demont Kilian
 * @author Graf Calvin
 * @author Hutzli Boris
 * @author Sottile Alan
 * @version 1.0
 */
public class NormalSwordBuilder extends AbstractSwordBuilder {

    /**
     * Constructor of the class NormalSwordBuilder.
     *
     * @param sword The sword to build.
     */
    public NormalSwordBuilder(Sword sword) {
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
        if(!this.sword.hasGasoline()) return invalid("Impossible to ignite a sword not coated with gasoline");
        Validation v = super.buildFire();
        if(v.getState()) this.sword.setFire(true);
        return v;
    }
}
