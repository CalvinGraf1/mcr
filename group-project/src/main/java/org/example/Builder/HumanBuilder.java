package org.example.Builder;

import org.example.Character.Human;
import org.example.Validation;
import org.example.items.Weapon;
import org.example.items.Armor;
import org.example.items.Helmet;

import static org.example.Validation.*;

/**
 * Class that defines the methods to build a human.
 *
 * @author Demont Kilian
 * @author Graf Calvin
 * @author Hutzli Boris
 * @author Sottile Alan
 * @version 1.0
 */
public class HumanBuilder extends AbstractCharacterBuilder {

    /**
     * Constructor of the class HumanBuilder.
     */
    public HumanBuilder(){
        super(new Human());
    }

    @Override
    public Validation buildChainMail() {
        Validation v = super.buildChainMail();
        if(v.getState()) this.character.setChainMail(true);
        return v;
    }

    @Override
    public Validation buildArmor(Armor armor) {
        if(!this.character.hasChainMail()) return invalid("Chainmail must be equipped before");
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
        if(v.getState()) this.character.setWeapon(weapon);
        return v;
    }

    @Override
    public void newCharacter() {
        this.character = new Human();
    }
}
