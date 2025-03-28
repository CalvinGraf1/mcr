package org.example.Character;

import org.example.Constants;
import org.example.Side;
import org.example.Team;
import org.example.Vector;
import org.example.items.Armor;
import org.example.items.Helmet;
import org.example.items.Weapon;

/**
 * Class representing a Human in the game
 *
 * @author Demont Kilian
 * @author Graf Calvin
 * @author Hutzli Boris
 * @author Sottile Alan
 * @version 1.0
 */
public class Human extends Character {
    private static final int HUMAN_HEALTH = 500;

    /**
     * Constructor for the Human class
     */
    public Human() {
        super(HUMAN_HEALTH);
    }

    @Override
    public double attackMultiplier() {
        return 1;
    }

    @Override
    public double resistanceMultiplier() {
        return 1;
    }

    @Override
    public int getMaxHealth() {
        return HUMAN_HEALTH;
    }

    @Override
    public int getHeadPosX() {
        if (side == Side.RIGHT) {
            return getPosX() + Constants.HUMAN_HEAD_POS_X_OFFSET_RIGHT;
        } else {
            return getPosX() + Constants.HUMAN_HEAD_POS_X_OFFSET_LEFT;
        }
    }

    @Override
    public int getHeadPosY() {
        return getPosY() - Constants.HUMAN_HEAD_POS_Y_OFFSET;
    }

    @Override
    public int getSpawnMin() {
        return side.getHumanBeg();
    }

    @Override
    public int getSpawnMax() {
        return side.getHumanEnd();
    }
}
