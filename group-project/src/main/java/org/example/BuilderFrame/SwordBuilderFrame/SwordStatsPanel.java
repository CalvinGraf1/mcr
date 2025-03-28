package org.example.BuilderFrame.SwordBuilderFrame;

import javax.swing.*;
import java.awt.*;

import org.example.items.weapons.Sword;

import static org.example.Utils.getImageIcon;
import static org.example.Utils.resizeIcon;


/**
 * SwordStatsPanel is a custom JPanel that displays the statistics of a given sword.
 * It provides a visual representation of the sword's attack damage.
 *
 * @since 10/06/2024
 * @Author Demont Kilian, Calvin Graf, Hutzli Boris & Sottile Alan
 * @version 1.2
 *
 */
public class SwordStatsPanel extends JPanel {
    private final JLabel attackLabel;

    /**
     * Constructs a SwordStatsPanel with the given sword.
     *
     * @param sword The sword whose stats will be displayed.
     */
    public SwordStatsPanel(Sword sword) {
        setLayout(new BorderLayout());
        setOpaque(false);

        Font labelFont = new Font("Arial", Font.BOLD, 16);
        int iconHeight = labelFont.getSize();

        ImageIcon attackIcon = resizeIcon(getImageIcon("icons/attack.png"), iconHeight);

        attackLabel = createLabel();

        updateStats(sword);

        JPanel statPanel = createStatPanel(attackIcon, attackLabel);
        JPanel centeredPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        centeredPanel.setOpaque(false);
        centeredPanel.add(statPanel);

        add(centeredPanel, BorderLayout.CENTER);
    }

    /**
     * Updates the stats displayed in the panel based on the given sword.
     *
     * @param sword The sword whose stats will be displayed.
     */
    public void updateStats(Sword sword) {
        attackLabel.setText("Attack: " + sword.damage());
    }

    /**
     * Creates a panel to hold the stat icon and label.
     *
     * @param icon The icon representing the stat.
     * @param label The label displaying the stat value.
     * @return A JPanel containing the icon and label.
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
     * Creates a label for displaying stats.
     *
     * @return A JLabel configured for displaying stats.
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
