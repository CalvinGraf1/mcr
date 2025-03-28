package org.example.Builder;

import org.example.items.Armor;
import org.example.Character.Character;
import org.example.items.Weapon;
import org.example.items.Helmet;
import org.example.Validation;

import static org.example.Validation.*;

/**
 * Abstract class that defines the methods to build a character.
 *
 * @author Demont Kilian
 * @author Graf Calvin
 * @author Hutzli Boris
 * @author Sottile Alan
 * @version 1.0
 */
abstract public class AbstractCharacterBuilder {
    protected Character character;

    /**
     * Constructor of the class AbstractCharacterBuilder.
     *
     * @param character The character to build.
     */
    protected AbstractCharacterBuilder(Character character) {
        this.character = character;
    }

    /**
     * Method to build a chain mail for the character.
     * @return A validation object that contains the result of the operation.
     */
    public Validation buildChainMail() {
        if(this.character.hasChainMail()) return invalid("Impossible to put on chain mail if another chain mail is already equipped");
        if(this.character.hasHelmet()) return invalid("Impossible to put on chain mail if helmet is already equipped");
        if(this.character.hasWeapon()) return invalid("Impossible to put on chain mail if weapon is already equipped");
        this.character.setChainMail(false);
        return valid();
    }

    /**
     * Method to build an armor for the character.
     * @return A validation object that contains the result of the operation.
     */
    public Validation buildArmor(Armor armor) {
        if(this.character.hasArmor())  return invalid("Impossible to put on armor if another armor is already equipped");
        if(this.character.hasHelmet()) return invalid("Impossible to put on armor if helmet is already equipped");
        if(this.character.hasWeapon()) return invalid("Impossible to put on armor if weapon is already equipped");
        this.character.setArmor(null);
        return valid();
    }

    /**
     * Method to build a helmet for the character.
     * @return A validation object that contains the result of the operation.
     */
    public Validation buildHelmet(Helmet helmet) {
        if(this.character.hasHelmet()) return invalid("Impossible to put on helmet if another helmet is already equipped");
        if(this.character.hasWeapon()) return invalid("Impossible to put on helmet if weapon is already equipped");
        this.character.setHelmet(null);
        return valid();
    }

    /**
     * Method to build a weapon for the character.
     * @return A validation object that contains the result of the operation.
     */
    public Validation buildWeapon(Weapon weapon) {
        if(this.character.hasWeapon()) return invalid("Impossible to equip a weapon if another weapon is already equipped");
        this.character.setWeapon(null);
        return valid();
    }

    /**
     * Method to get the character.
     * @return The character.
     */
    public Character getCharacter() {
        return this.character;
    }

    /**
     * Method to create a new character.
     */
    public abstract void newCharacter();
}
