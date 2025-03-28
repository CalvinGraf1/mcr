package org.example.Character;

import org.example.Constants;
import org.example.Side;
import org.example.Team;
import org.example.Vector;
import org.example.items.Armor;
import org.example.items.Helmet;
import org.example.items.Weapon;

/**
 * Class representing an Elf in the game.
 *
 * @author Demont Kilian
 * @author Graf Calvin
 * @author Hutzli Boris
 * @author Sottile Alan
 * @version 1.0
 */
public class Elf extends Character {
    private static final int ELF_HEALTH = 400;

    /**
     * Constructor for the Elf class.
     */
    public Elf(){
        super(ELF_HEALTH);
    }

    @Override
    public double attackMultiplier() {
        return 1;
    }

    @Override
    public double resistanceMultiplier() {
        return 0.8;
    }

    @Override
    public int getMaxHealth() {
        return ELF_HEALTH;
    }

    @Override
    public int getHeadPosX() {
        if (side == Side.RIGHT) {
            return getPosX() + Constants.ELF_HEAD_POS_X_OFFSET_RIGHT;
        }
        return getPosX();
    }

    @Override
    public int getHeadPosY() {
        return getPosY() - Constants.ELF_HEAD_POS_Y_OFFSET;
    }

    @Override
    public int getSpawnMin() {
       return side.getElfBeg();
    }

    @Override
    public int getSpawnMax() {
        return side.getElfEnd();
    }
}
