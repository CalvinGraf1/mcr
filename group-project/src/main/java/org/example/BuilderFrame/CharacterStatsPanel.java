package org.example.BuilderFrame;

import javax.swing.*;
import java.awt.*;

import org.example.Character.Character;

import static org.example.Utils.resizeIcon;
import static org.example.Utils.getImageIcon;

/**
 * CharacterStatsPanel is a JPanel that displays health, defense, and attack stats
 * of a character using JLabels with corresponding icons.
 *
 * @since 10/05/2024
 * @Author Demont Kilian, Calvin Graf, Hutzli Boris & Sottile Alan
 * @version 2.0
 */
public class CharacterStatsPanel extends JPanel {
    private final JLabel healthLabel;
    private final JLabel defenseLabel;
    private final JLabel attackLabel;

    /**
     * Constructs a CharacterStatsPanel with default settings.
     */
    public CharacterStatsPanel() {
        setLayout(new GridLayout(3, 1));
        setOpaque(false);

        Font labelFont = new Font("Arial", Font.BOLD, 16);
        int iconHeight = labelFont.getSize();

        ImageIcon healthIcon = resizeIcon(getImageIcon("icons/health.png"), iconHeight);
        ImageIcon defenseIcon = resizeIcon(getImageIcon("icons/defense.png"), iconHeight);
        ImageIcon attackIcon = resizeIcon(getImageIcon("icons/attack.png"), iconHeight);

        healthLabel = createLabel();
        defenseLabel = createLabel();
        attackLabel = createLabel();

        reinitializeStats();

        add(createStatPanel(healthIcon, healthLabel));
        add(createStatPanel(defenseIcon, defenseLabel));
        add(createStatPanel(attackIcon, attackLabel));
    }

    /**
     * Updates the displayed stats with the provided character's stats.
     *
     * @param character The character whose stats are to be displayed.
     */
    public void updateStats(Character character) {
        healthLabel.setText("Health: " + character.getHealth());
        defenseLabel.setText("Defense: " + character.getDefense());
        attackLabel.setText("Attack: " + character.getAttack());
    }

    /**
     * Reinitializes the stats labels to display default values.
     */
    public void reinitializeStats() {
        healthLabel.setText("Health: 0");
        defenseLabel.setText("Defense: 0");
        attackLabel.setText("Attack: 0");
    }

    /**
     * Creates a JPanel with a JLabel displaying the provided icon and text label.
     *
     * @param icon The icon to be displayed.
     * @param label The JLabel to display the text.
     * @return JPanel containing the icon and label.
     */
    private JPanel createStatPanel(ImageIcon icon, JLabel label) {
        JPanel statPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statPanel.setOpaque(false);
        JLabel iconLabel = new JLabel(icon);
        statPanel.add(iconLabel);
        statPanel.add(label);
        return statPanel;
    }

    /**
     * Creates a JLabel with default font and size settings.
     *
     * @return JLabel with default font and size.
     */
    private JLabel createLabel() {
        JLabel label = new JLabel();
        label.setFont(new Font("Arial", Font.BOLD, 16));
        Dimension size = new Dimension(150, 30);
        label.setPreferredSize(size);
        label.setMinimumSize(size);
        label.setMaximumSize(size);
        return label;
    }
}
