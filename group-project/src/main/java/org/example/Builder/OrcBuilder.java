package org.example.Builder;

import org.example.Character.Orc;
import org.example.Validation;
import org.example.items.Armor;
import org.example.items.Weapon;
import org.example.items.Helmet;

import static org.example.Validation.invalid;
import static org.example.items.CloseCombatWeapon.getCloseCombatWeapons;
import static org.example.items.DistanceWeapon.getDistanceWeapons;

/**
 * Class that defines the methods to build an orc.
 *
 * @author Demont Kilian
 * @author Graf Calvin
 * @author Hutzli Boris
 * @author Sottile Alan
 * @version 1.0
 */
public class OrcBuilder extends AbstractCharacterBuilder {

    /**
     * Constructor of the class OrcBuilder.
     */
    public OrcBuilder() {
        super(new Orc());
    }

    @Override
    public Validation buildChainMail() {
        Validation v = super.buildChainMail();
        if(v.getState()) this.character.setChainMail(true);
        return v;
    }

    @Override
    public Validation buildArmor(Armor armor) {
        if (!this.character.hasChainMail()) return invalid("Chainmail must be equipped before");
        Validation v = super.buildArmor(armor);
        if(v.getState()) this.character.setArmor(armor);
        return v;
    }

    @Override
    public Validation buildHelmet(Helmet helmet) {
        Validation v = super.buildHelmet(helmet);
        if(v.getState()) this.character.setHelmet(helmet);
        return v;
    }

    @Override
    public Validation buildWeapon(Weapon weapon) {
        Validation v = super.buildWeapon(weapon);
        if (!v.getState()) return v;
        if (weapon.isDistance()) return invalid("Orc can only use close combat weapons");
        this.character.setWeapon(weapon);
        return v;
    }

    @Override
    public void newCharacter() {
        this.character = new Orc();
    }
}
