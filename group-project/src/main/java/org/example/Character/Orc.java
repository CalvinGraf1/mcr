package org.example.Character;

import org.example.Constants;
import org.example.Side;
import org.example.Team;
import org.example.Vector;
import org.example.items.Armor;
import org.example.items.Helmet;
import org.example.items.Weapon;

/**
 * Class representing an Orc in the game.
 *
 * @author Demont Kilian
 * @author Graf Calvin
 * @author Hutzli Boris
 * @author Sottile Alan
 * @version 1.0
 */
public class Orc extends Character {
    private static final int ORC_HEALTH = 600;

    /**
     * Constructor for the Orc class.
     */
    public Orc() {
        super(ORC_HEALTH);
    }

    @Override
    public double attackMultiplier() {
        return 1.2;
    }

    @Override
    public double resistanceMultiplier() {
        return 1.5;
    }

    @Override
    public int getMaxHealth() {
        return ORC_HEALTH;
    }

    @Override
    public int getHeadPosX() {
        if (side == Side.RIGHT) {
            return getPosX() + Constants.ORC_HEAD_POS_X_OFFSET_RIGHT;
        } else {
            return getPosX() + Constants.ORC_HEAD_POS_X_OFFSET_LEFT;
        }
    }

    @Override
    public int getHeadPosY() {
        return getPosY() - Constants.ORC_HEAD_POS_Y_OFFSET;
    }

    @Override
    public int getSpawnMin() {
        return side.getOrcBeg();
    }

    @Override
    public int getSpawnMax() {
        return side.getOrcEnd();
    }
}
