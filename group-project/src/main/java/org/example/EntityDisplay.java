package org.example;

import org.example.Character.Character;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

/**
 * Manages the display of characters on a JPanel
 *
 * @author Demont Kilian
 * @author Graf Calvin
 * @author Hutzli Boris
 * @author Sottile Alan
 * @version 1.0
 */
public class EntityDisplay {

    /**
     * The singleton instance of EntityDisplay.
     */
    private static EntityDisplay instance;

    /**
     * The JPanel where entities are displayed.
     */
    private JPanel panel;

    /**
     * A mapping of characters to arrays of JLabels representing their graphical components.
     */
    private final Map<Character, JLabel[]> characterLabels;


    /**
     * Private constructor to enforce singleton pattern.
     */
    private EntityDisplay() {
        this.characterLabels = new HashMap<>();
    }

    /**
     * Returns the singleton instance of EntityDisplay.
     *
     * @return the singleton instance of EntityDisplay
     */
    public static EntityDisplay getInstance() {
        if (instance == null) {
            instance = new EntityDisplay();
        }
        return instance;
    }

    /**
     * Sets the JPanel where entities will be displayed.
     *
     * @param panel the JPanel to set
     */
    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    /**
     * Displays the graphical representation of a character on the panel.
     *
     * @param character the character to display
     * @throws IllegalStateException if the panel has not been set
     */
    public void displayCharacter(Character character) {
        if (panel == null) {
            throw new IllegalStateException("Panel has not been set.");
        }
        displayHpLabel(character);
        displayHead(character);
        displayWeapon(character);
        displayBody(character);
    }

    /**
     * Creates a JLabel with the specified icon and position on the panel.
     * Flips the icon horizontally if the character's side is RIGHT.
     *
     * @param entity the entity associated with the icon
     * @param icon   the ImageIcon to display
     * @param posX   the x-coordinate position
     * @param posY   the y-coordinate position
     * @return the created JLabel
     */
    public JLabel createLabel(Entity entity, ImageIcon icon, int posX, int posY) {
        BufferedImage image = Displayer.ImageUtils.toBufferedImage(icon);
        if (entity.getSide() == Side.RIGHT) {
            image = Displayer.ImageUtils.flipImageHorizontally(image);
        }
        icon = new ImageIcon(image);
        JLabel label = new JLabel(icon);
        label.setBounds(posX, posY, icon.getIconWidth(), icon.getIconHeight());
        return label;
    }

    /**
     * Displays the body of the character on the panel.
     *
     * @param character the character whose body to display
     */
    private void displayBody(Character character) {
        ImageIcon bodyIcon = new ImageIcon(character.getBodyPath());
        bodyIcon = new ImageIcon(bodyIcon.getImage().getScaledInstance(Constants.CHAR_BODY_WIDTH, Constants.CHAR_BODY_HEIGHT, Image.SCALE_SMOOTH));
        JLabel bodyLabel = createLabel(character, bodyIcon, character.getPosX(), character.getPosY());
        panel.add(bodyLabel);
        addLabelToCharacter(character, bodyLabel);
    }

    /**
     * Displays the weapon of the character on the panel.
     *
     * @param character the character whose weapon to display
     */
    private void displayWeapon(Character character) {
        ImageIcon weaponIcon = new ImageIcon("src/main/resources/Character/Weapon/" + character.getWeapon().pathName() + ".png");
        JLabel weaponLabel = createLabel(character, weaponIcon, character.getWeapon().getPosition().getPosX(), character.getWeapon().getPosition().getPosY());
        panel.add(weaponLabel);
        addLabelToCharacter(character, weaponLabel);
    }

    /**
     * Displays the head of the character on the panel.
     *
     * @param character the character whose head to display
     */
    private void displayHead(Character character) {
        ImageIcon headIcon = new ImageIcon(character.getHeadPath());
        headIcon = new ImageIcon(headIcon.getImage().getScaledInstance(Constants.CHAR_HEAD_WIDTH, Constants.CHAR_HEAD_HEIGHT, Image.SCALE_SMOOTH));
        JLabel headLabel = createLabel(character, headIcon, character.getHeadPosX(), character.getHeadPosY());
        panel.add(headLabel);
        addLabelToCharacter(character, headLabel);
    }

    /**
     * Displays the HP label of the character on the panel.
     *
     * @param character the character whose HP label to display
     */
    private void displayHpLabel(Character character) {
        JLabel hpLabel = new JLabel(character.getHealth() + "/" + character.getMaxHealth());
        hpLabel.setForeground(Color.RED);
        hpLabel.setBounds(character.getPosX() + Constants.CHAR_HP_LABEL_OFFSET_X,
                character.getPosY() - Constants.CHAR_HP_LABEL_OFFSET_Y, Constants.CHAR_HP_LABEL_WIDTH, Constants.CHAR_HP_LABEL_HEIGHT);
        panel.add(hpLabel);
        addLabelToCharacter(character, hpLabel);
    }

    /**
     * Displays all characters in the given team on the panel.
     *
     * @param team the team whose characters to display
     */
    public void displayArmy(Team team) {
        for (Character character : team.getMembers()) {
            displayCharacter(character);
        }
    }

    /**
     * Clears the graphical representation of all characters in the given team from the panel.
     *
     * @param team the team whose characters to clear
     */
    public void clearArmy(Team team) {
        for (Character character : team.getMembers()) {
            removeCharacter(character);
        }
    }

    /**
     * Removes the graphical representation of a character from the panel.
     *
     * @param character the character to remove
     */
    public void removeCharacter(Character character) {
        JLabel[] labels = characterLabels.get(character);
        if (labels != null) {
            for (JLabel label : labels) {
                panel.remove(label);
            }
            panel.repaint();
        }
    }

    /**
     * Adds a JLabel representing a component of the character to the character's label array.
     *
     * @param character the character to associate with the label
     * @param label     the JLabel to add
     */
    private void addLabelToCharacter(Character character, JLabel label) {
        JLabel[] labels = characterLabels.getOrDefault(character, new JLabel[0]);
        JLabel[] newLabels = new JLabel[labels.length + 1];
        System.arraycopy(labels, 0, newLabels, 0, labels.length);
        newLabels[labels.length] = label;
        characterLabels.put(character, newLabels);
    }
}
