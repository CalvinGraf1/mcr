package org.example;

import org.example.Builder.AbstractCharacterBuilder;
import org.example.Builder.ElfBuilder;
import org.example.Builder.HumanBuilder;
import org.example.Builder.OrcBuilder;
import org.example.items.Weapon;
import org.example.items.Armor;
import org.example.items.Helmet;

import java.util.List;

import static org.example.items.CloseCombatWeapon.getCloseCombatWeapons;
import static org.example.items.DistanceWeapon.getDistanceWeapons;
import static org.example.items.Weapon.getWeapons;
import static org.example.items.Helmet.getHelmets;
import static org.example.items.Armor.getArmors;

/**
 * This enum represents the different types of characters that can be created.
 *
 * @author Demont Kilian
 * @author Graf Calvin
 * @author Hutzli Boris
 * @author Sottile Alan
 * @version 1.0
 */
public enum CharacterType {
    ELF("Elf", new ElfBuilder(), getDistanceWeapons(), getHelmets(), getArmors()),
    HUMAN("Human", new HumanBuilder(), getWeapons(), getHelmets(), getArmors()),
    ORC("Orc", new OrcBuilder(), getCloseCombatWeapons(), getHelmets(), getArmors());

    private String name;
    private AbstractCharacterBuilder builder;
    private List<Weapon> weapons;
    private List<Helmet> helmets;
    private List<Armor> armors;

    /**
     * Constructor for the CharacterType enum.
     * @param name the name of the character type
     * @param builder the builder of the character type
     * @param weapons the weapons of the character type
     * @param helmets the helmets of the character type
     * @param armors the armors of the character type
     */
    CharacterType(String name, AbstractCharacterBuilder builder, List<? extends Weapon> weapons, List<? extends Helmet> helmets, List<? extends Armor> armors) {
        this.name = name;
        this.builder = builder;
        this.weapons = (List<Weapon>) weapons;
        this.helmets = (List<Helmet>) helmets;
        this.armors = (List<Armor>) armors;
    }

    /**
     * This method returns the name of the character type.
     * @return the name of the character type
     */
    public String getName() {
        return name;
    }

    /**
     * This method returns the builder of the character type.
     * @return the builder of the character type
     */
    public AbstractCharacterBuilder getCharacterBuilder() {
        return builder;
    }

    /**
     * This method returns the weapons of the character type.
     * @return the weapons of the character type
     */
    public List<Weapon> getCharacterWeapons() {
        return weapons;
    }

    /**
     * This method returns the helmets of the character type.
     * @return the helmets of the character type
     */
    public List<Helmet> getCharacterHelmets() {
        return helmets;
    }

    /**
     * This method returns the armors of the character type.
     * @return the armors of the character type
     */
    public List<Armor> getCharacterArmors() {
        return armors;
    }
}
