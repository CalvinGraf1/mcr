package org.example.BuilderFrame;

import javax.swing.*;
import java.awt.*;

/**
 * ItemStatPanel is a JPanel that displays item statistics including an icon,
 * and two numeric features with their corresponding names.
 *
 * It provides methods to update the panel with new data, including resizing
 * and setting the icon, and updating the text labels for the features.
 *
 *  @since 08/05/2024
 *  @Author Demont Kilian, Calvin Graf, Hutzli Boris & Sottile Alan
 *  @version 1.3
 */
public class ItemStatPanel extends JPanel {
    private JLabel iconLabel;
    private JLabel feature1;
    private JLabel feature2;

    private String feature1Name;
    private String feature2Name;

    private static final int MAX_IMAGE_SIZE = 150;
    private static final int LABEL_WIDTH = 150;
    private static final int LABEL_HEIGHT = 30;

    /**
     * Constructs an ItemStatPanel with the specified feature names.
     *
     * @param feature1Name The name of the first feature.
     * @param feature2Name The name of the second feature.
     */
    public ItemStatPanel(String feature1Name, String feature2Name) {
        this.feature1Name = feature1Name;
        this.feature2Name = feature2Name;

        this.setLayout(new GridBagLayout());
        this.setOpaque(false);
        GridBagConstraints c = new GridBagConstraints();

        c.insets = new Insets(10, 10, 10, 10);

        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 2;
        iconLabel = new JLabel();
        this.add(iconLabel, c);

        c.gridx = 1;
        c.gridy = 0;
        c.gridheight = 1;
        feature1 = new JLabel();
        feature1.setFont(new Font("Arial", Font.BOLD, 16));
        setFixedSize(feature1);
        this.add(feature1, c);

        c.gridx = 1;
        c.gridy = 1;
        c.gridheight = 1;
        feature2 = new JLabel();
        feature2.setFont(new Font("Arial", Font.BOLD, 16));
        setFixedSize(feature2);
        this.add(feature2, c);
    }

    /**
     * Sets the fixed size (width and height) for a JLabel.
     *
     * @param label The JLabel to set the size for.
     */
    private void setFixedSize(JLabel label) {
        Dimension size = new Dimension(ItemStatPanel.LABEL_WIDTH, ItemStatPanel.LABEL_HEIGHT);
        label.setPreferredSize(size);
        label.setMinimumSize(size);
        label.setMaximumSize(size);
    }

    /**
     * Sets the icon label with the specified ImageIcon, resizing it to fit within
     * the maximum allowed image size while preserving its aspect ratio.
     *
     * @param icon The ImageIcon to set as the icon label.
     */
    private void setIconLabel(ImageIcon icon) {
        // Get original dimensions
        int originalWidth = icon.getIconWidth();
        int originalHeight = icon.getIconHeight();

        // Calculate the scaling factor to preserve aspect ratio
        double aspectRatio = (double) originalWidth / originalHeight;
        int newWidth = MAX_IMAGE_SIZE;
        int newHeight = (int) (MAX_IMAGE_SIZE / aspectRatio);

        // If new height exceeds max height, recalculate based on max height
        if (newHeight > MAX_IMAGE_SIZE) {
            newHeight = MAX_IMAGE_SIZE;
            newWidth = (int) (MAX_IMAGE_SIZE * aspectRatio);
        }

        // Scale the image
        Image scaledImage = icon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        iconLabel.setIcon(new ImageIcon(scaledImage));
        iconLabel.setPreferredSize(new Dimension(MAX_IMAGE_SIZE, MAX_IMAGE_SIZE));
        iconLabel.setMinimumSize(new Dimension(MAX_IMAGE_SIZE, MAX_IMAGE_SIZE));
        iconLabel.setMaximumSize(new Dimension(MAX_IMAGE_SIZE, MAX_IMAGE_SIZE));
    }

    /**
     * Updates the panel with new data, including the icon, and the values for feature1 and feature2.
     *
     * @param icon        The ImageIcon to set as the icon label.
     * @param feature1Value The value of the first feature.
     * @param feature2Value The value of the second feature.
     */
    public void updatePanel(ImageIcon icon, int feature1Value, int feature2Value) {
        setIconLabel(icon);
        this.feature1.setText(feature1Name + ": " + feature1Value);
        this.feature2.setText(feature2Name + ": " + feature2Value);
    }
}
