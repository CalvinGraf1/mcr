package org.example.Builder;

import org.example.Validation;
import org.example.items.Armor;
import org.example.items.Weapon;
import org.example.items.Helmet;
import org.example.Character.Elf;

import static org.example.Validation.invalid;
import static org.example.items.DistanceWeapon.getDistanceWeapons;

/**
 * Class that defines the methods to build an elf.
 *
 * @author Demont Kilian
 * @author Graf Calvin
 * @author Hutzli Boris
 * @author Sottile Alan
 * @version 1.0
 */
public class ElfBuilder extends AbstractCharacterBuilder {

    /**
     * Constructor of the class ElfBuilder.
     */
    public ElfBuilder(){
        super(new Elf());
    }

    @Override
    public Validation buildArmor(Armor armor) {
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
        if (!weapon.isDistance()) return invalid("Elf can only use distance weapons");
        this.character.setWeapon(weapon);
        return v;
    }

    @Override
    public void newCharacter() {
        this.character = new Elf();
    }
}
