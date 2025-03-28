package org.example.BuilderFrame;

import javax.swing.*;
import java.awt.*;

import static org.example.Utils.getImageIcon;

/**
 * FitIconButton is a JButton that displays an ImageIcon and automatically adjusts its size
 * to match the dimensions of the ImageIcon.
 *
 * This button is designed to display icons without scaling them, maintaining their original size.
 *
 * @since 10/06/2024
 * @Author Demont Kilian, Calvin Graf, Hutzli Boris & Sottile Alan
 * @version 1.1
 */
public class FitIconButton extends JButton {
    /**
     * Constructs a FitIconButton with the specified image path.
     *
     * @param path The path to the image file.
     */
    public FitIconButton(String path) {
        this(getImageIcon(path));
    }

    /**
     * Constructs a FitIconButton with the specified ImageIcon.
     *
     * @param icon The ImageIcon to display.
     */
    private FitIconButton(ImageIcon icon) {
        super(icon);
        setButtonSize(icon);
    }

    /**
     * Sets the size of the button to match the dimensions of the specified ImageIcon.
     *
     * @param icon The ImageIcon to match the size to.
     */
    private void setButtonSize(ImageIcon icon) {
        int width = icon.getIconWidth();
        int height = icon.getIconHeight();
        Dimension size = new Dimension(width, height);
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
    }
}
