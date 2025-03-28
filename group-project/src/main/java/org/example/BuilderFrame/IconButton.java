package org.example.BuilderFrame;

import javax.swing.*;

import static org.example.Utils.getImageIcon;
import static org.example.Utils.scaleImageIcon;

/**
 * IconButton is a JButton that displays an ImageIcon, optionally scaled to a specified size.
 *
 * This button can display icons either in their original size or scaled based on a provided scale factor.
 *
 *  @since 10/06/2024
 *  @Author Demont Kilian, Calvin Graf, Hutzli Boris & Sottile Alan
 *  @version 1.1
 */
public class IconButton extends JButton {

    /**
     * Constructs an IconButton with the ImageIcon loaded from the specified path.
     *
     * @param path The path to the image file for the icon.
     */
    public IconButton(String path) {
        super(getImageIcon(path));
    }

    /**
     * Constructs an IconButton with the ImageIcon loaded from the specified path,
     * scaled by the provided factor.
     *
     * @param path  The path to the image file for the icon.
     * @param scale The scaling factor to apply to the icon (1.0 for original size).
     */
    public IconButton(String path, double scale) {
        super(scaleImageIcon(getImageIcon(path), scale));
    }
}
