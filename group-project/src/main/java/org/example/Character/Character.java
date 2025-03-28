package org.example.Character;

import org.example.*;
import org.example.items.Armor;
import org.example.items.Weapon;
import org.example.items.Helmet;

import java.util.Random;

/**
 * Abstract class representing a character in the game.
 *
 * @author Demont Kilian
 * @author Graf Calvin
 * @author Hutzli Boris
 * @author Sottile Alan
 * @version 1.0
 */
public abstract class Character extends Entity {
    private int health;
    private boolean chainMail;
    private Armor armor;
    private Helmet helmet;
    private Weapon weapon;

    /**
     * Constructor for the Character class.
     *
     * @param health The health of the character.
     */
    protected Character(int health) {
        this.health = health;
        this.chainMail = false;
        this.armor = null;
        this.helmet = null;
        this.weapon = null;
    }

    /**
     * Sets the health of the character.
     * @param health The health of the character.
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Sets the chainmail of the character.
     * @param chainMail If the character has a chainmail.
     */
    public void setChainMail(boolean chainMail) { this.chainMail = chainMail; }

    /**
     * Sets the armor of the character.
     * @param armor The armor of the character.
     */
    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    /**
     * Sets the helmet of the character.
     * @param helmet The helmet of the character.
     */
    public void setHelmet(Helmet helmet) { this.helmet = helmet; }

    /**
     * Sets the weapon of the character.
     * @param weapon The weapon of the character.
     */
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    /**
     * Returns if the character has a chainmail.
     * @return True if the character has a chainmail, false otherwise.
     */
    public boolean hasChainMail() {
        return this.chainMail;
    }

    /**
     * Returns if the character has an armor.
     * @return True if the character has an armor, false otherwise.
     */
    public boolean hasArmor() {
        return this.armor != null;
    }

    /**
     * Returns if the character has a helmet.
     * @return True if the character has a helmet, false otherwise.
     */
    public boolean hasHelmet() {
        return this.helmet != null;
    }

    /**
     * Returns if the character has a weapon.
     * @return True if the character has a weapon, false otherwise.
     */
    public boolean hasWeapon() {
        return this.weapon != null;
    }

    /**
     * Get the attack multiplier of the character.
     * @return The attack multiplier of the character.
     */
    public abstract double attackMultiplier();

    /**
     * Get the resistance multiplier of the character.
     * @return The resistance multiplier of the character.
     */
    public abstract double resistanceMultiplier();

    /**
     * Calculate the defense of the character based on the armor, helmet , chainmail and
     * @return The defense of the character.
     */
    public double getDefense() {
        return ((this.armor != null ? this.armor.resistance() : 0 ) + (this.helmet != null ? this.helmet.resistance() : 0) + (chainMail ? Constants.CHAIN_MAIL_DEFENSE : 0)) * resistanceMultiplier();
    }

    /**
     * Calculate the attack of the character based on the weapon and the attack multiplier.
     * @return The attack of the character.
     */
    public double getAttack() {
        return (this.weapon != null ? this.weapon.damage() : 0) * attackMultiplier();
    }

    /**
     * Get the health of the character.
     * @return The health of the character.
     */
    public int getHealth() {
        return this.health;
    }

    /**
     * Get the height of the character.
     * @return The height of the character.
     */
    public int getHeight(){
        return Constants.CHAR_BODY_HEIGHT;
    }

    /**
     * Get the weapon of the character.
     * @return The weapon of the character.
     */
    public Weapon getWeapon() {
        return this.weapon;
    }

    /**
     * Calculate damage inflicted on another character
     * @param attack The attack of the character attacking.
     * @param defence The defense of the character being attacked.
     * @return The damage inflicted.
     */
    private static double calculateDamage(double attack, double defence) {
        double attackFactor = attack / (attack + defence);
        return attack * attackFactor;
    }

    /**
     * Attack a character.
     * @param character The character to attack.
     */
    public void attack(Character character) {
        double damage = calculateDamage(this.getAttack(), character.getDefense());
        character.setHealth((int) Math.floor((character.getHealth() - damage)));
    }

    /**
     * Check if the character is alive.
     * @return True if the character is alive, false otherwise.
     */
    public boolean isAlive() {
        return this.health > 0;
    }

    /**
     * Generate the initial position of the character.
     * @param side The side of the character.
     */
    public void generateInitialPos(Side side) {
        this.side = side;
        Random rand = new Random();
        int posX = rand.nextInt(getSpawnMax() - getSpawnMin()) + getSpawnMin();
        int posY = rand.nextInt(Constants.PANEL_HEIGHT - getHeight());

        this.position = new Vector(posX, posY);

        changeWeaponPosition();
    }

    /**
     * Change the position of the weapon.
     */
    public void changeWeaponPosition(){
        int weaponPosX;
        int weaponPosY;
        if (side == Side.LEFT) {
            weaponPosX = getPosX() + Constants.WEAPON_LEFT_OFFSET_X;
        } else {
            weaponPosX = getPosX() - weapon.getWeaponWidth() + Constants.WEAPON_RIGHT_OFFSET_X;
        }

        weaponPosY = getPosY() - Constants.WEAPON_OFFSET_Y;
        this.weapon.setPosition(new Vector(weaponPosX,weaponPosY));
    }

    @Override
    public void setPosX(int x) {
        super.setPosX(x);
        changeWeaponPosition();
    }

    @Override
    public void setPosY(int y) {
        super.setPosY(y);
        changeWeaponPosition();
    }

    /**
     * Get the path of the body of the character.
     * @return The path of the body of the character.
     */
    public String getBodyPath() {
        StringBuilder path = new StringBuilder();
        path.append(Constants.CHARACTER_BASE_BODY_PATH);
        path.append(this.getClass().getSimpleName()).append("/");
        if (this.armor == null) {
            if (this.chainMail) {
                path.append("Chainmail");
            } else {
                path.append("Naked");
            }
        } else {
            path.append(this.armor.pathName());
        }

        path.append(".png");
        return path.toString();
    }

    /**
     * Get the path of the head of the character.
     * @return The path of the head of the character.
     */
    public String getHeadPath() {
        StringBuilder path = new StringBuilder();
        path.append(Constants.CHARACTER_BASE_HEAD_PATH);
        path.append(this.getClass().getSimpleName()).append("/");
        if (this.helmet == null) {
            path.append("Naked");
        } else {
            path.append(this.helmet.pathName());
        }

        path.append(".png");
        return path.toString();
    }

    /**
     * Get the X position of the head of the character.
     * @return The X position of the head of the character.
     */
    public abstract int getHeadPosX();

    /**
     * Get the Y position of the head of the character.
     * @return The Y position of the head of the character.
     */
    public abstract int getHeadPosY();

    /**
     * Get the minimum spawn position of the character.
     * @return The minimum spawn position of the character.
     */
    public abstract int getSpawnMin();

    /**
     * Get the maximum spawn position of the character.
     * @return The maximum spawn position of the character.
     */
    public abstract int getSpawnMax();

    /**
     * Get the maximum health of the character.
     * @return The maximum health of the character.
     */
    public abstract int getMaxHealth();
}
