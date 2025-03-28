package org.example.BuilderFrame;

import org.example.Character.Character;
import org.example.Displayer;
import org.example.Entity;
import org.example.Side;
import org.example.Vector;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * CharacterDisplayer is a custom JPanel used to visually display a character
 * and its components such as body, head, and weapon.
 *
 * @since 14/05/2024
 * @Author Demont Kilian, Calvin Graf, Hutzli Boris & Sottile Alan
 * @version 2.1
 */
public class CharacterDisplayer extends JPanel {
    private Character character;

    private static final int PANEL_WIDTH = 200;
    private static final int PANEL_HEIGHT = 200;

    /**
     * Constructs a CharacterDisplayer with default settings.
     */
    public CharacterDisplayer() {
        setOpaque(false);
        setLayout(null);
        setFixedSize(PANEL_WIDTH, PANEL_HEIGHT);
    }

    /**
     * Sets a fixed size for the panel.
     *
     * @param width The width of the panel.
     * @param height The height of the panel.
     */
    private void setFixedSize(int width, int height) {
        Dimension size = new Dimension(width, height);
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
    }

    /**
     * Sets the character to be displayed.
     *
     * @param character The character to be displayed.
     */
    public void setCharacter(Character character) {
        this.character = character;
        character.setPosition(new Vector(50, 75));
    }


    /**
     * Sets the position of the weapon in the character's display.
     */
    public void setWeaponPosition() {
        character.getWeapon().setPosition(new Vector(160, 50));
    }

    /**
     * Displays a part of the character using the given image path and position.
     *
     * @param imagePath The path to the image.
     * @param posX The x-coordinate position.
     * @param posY The y-coordinate position.
     */
    private void displayPart(String imagePath, int posX, int posY) {
        ImageIcon icon = new ImageIcon(new ImageIcon(imagePath).getImage());
        JLabel label = createLabel(character, icon, posX, posY);
        this.add(label);
    }

    /**
     * Displays a part of the character using the given image path, position, and size.
     *
     * @param imagePath The path to the image.
     * @param posX The x-coordinate position.
     * @param posY The y-coordinate position.
     * @param width The width of the image.
     * @param height The height of the image.
     */
    private void displayPart(String imagePath, int posX, int posY, int width, int height) {
        ImageIcon icon = new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
        JLabel label = createLabel(character, icon, posX, posY);
        this.add(label);
    }

    /**
     * Displays the body of the character.
     */
    public void displayBody() {
        displayPart(character.getBodyPath(), character.getPosX(), character.getPosY(), 100, 100);
    }

    /**
     * Displays the head of the character.
     */
    public void displayHead() {
        displayPart(character.getHeadPath(), character.getHeadPosX(), character.getHeadPosY(), 80, 80);
    }

    /**
     * Displays the weapon of the character.
     */
    public void displayWeapon() {
        String weaponPath = "src/main/resources/Character/Weapon/" + character.getWeapon().pathName() + ".png";
        setWeaponPosition();
        displayPart(weaponPath, 160, 50); // Adjust size as needed
    }

    /**
     * Creates a JLabel for a given entity with the specified icon and position.
     *
     * @param entity The entity for which the label is created.
     * @param icon The icon to be displayed in the label.
     * @param posX The x-coordinate position.
     * @param posY The y-coordinate position.
     * @return A JLabel with the specified properties.
     */
    private JLabel createLabel(Entity entity, ImageIcon icon, int posX, int posY) {
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
     * Displays the character along with its components such as body, head, and weapon.
     */
    public void displayCharacter() {
        removeAll();
        displayHead();
        displayBody();
        if (character.getWeapon() != null)
            displayWeapon();
        refreshDisplay();
    }

    /**
     * Clears the display panel.
     */
    public void clearDisplay() {
        removeAll();
        refreshDisplay();
    }

    /**
     * Refreshes the display by revalidating and repainting the panel.
     */
    private void refreshDisplay() {
        revalidate();
        repaint();
    }
}
