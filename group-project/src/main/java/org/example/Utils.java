package org.example;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * Utility class providing various static methods for common operations.
 *
 * @author Demont Kilian
 * @author Graf Calvin
 * @author Hutzli Boris
 * @author Sottile Alan
 * @version 1.0
 */
public class Utils {
    private static final Random random = new Random();

    /**
     * Retrieves the next item in the list relative to the current item.
     *
     * @param list       The list from which to retrieve the next item.
     * @param currentItem The current item to base the next item on.
     * @param <T>        The type of elements in the list.
     * @return The next item in the list.
     */
    public static <T> T getNext(List<T> list, T currentItem) {
        return list.get((list.indexOf(currentItem) + 1) % list.size());
    }

    /**
     * Retrieves the previous item in the list relative to the current item.
     *
     * @param list       The list from which to retrieve the previous item.
     * @param currentItem The current item to base the previous item on.
     * @param <T>        The type of elements in the list.
     * @return The previous item in the list.
     */
    public static <T> T getPrevious(List<T> list, T currentItem) {
        return list.get((list.indexOf(currentItem) - 1 + list.size()) % list.size());
    }

    /**
     * Retrieves a random item from the given list.
     *
     * @param list The list from which to retrieve a random item.
     * @param <T>  The type of elements in the list.
     * @return A randomly selected item from the list.
     */
    public static <T> T getRandomItem(List<T> list) {
        return list.get(random.nextInt(list.size()));
    }

    /**
     * Retrieves an ImageIcon from the specified resource path.
     *
     * @param path The path to the image resource.
     * @return ImageIcon loaded from the resource path.
     * @throws NullPointerException if the resource is not found.
     */
    public static ImageIcon getImageIcon(String path) {
        return new ImageIcon(Objects.requireNonNull(Utils.class.getClassLoader().getResource(path)));
    }

    /**
     * Creates a titled border for panels with the specified title.
     *
     * @param title The title of the border.
     * @return TitledBorder with etched border style and specified title.
     */
    public static Border getPanelBorder(String title) {
        return BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), title);
    }

    /**
     * Scales the given ImageIcon by the specified scale factor.
     *
     * @param icon  The ImageIcon to scale.
     * @param scale The scale factor (1.0 for original size).
     * @return Scaled ImageIcon.
     */
    public static ImageIcon scaleImageIcon(ImageIcon icon, double scale) {
        int width = (int) (icon.getIconWidth() * scale);
        int height = (int) (icon.getIconHeight() * scale);
        Image scaledImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }

    /**
     * Resizes the given ImageIcon to the specified height while maintaining aspect ratio.
     *
     * @param icon   The ImageIcon to resize.
     * @param height The desired height of the resized icon.
     * @return Resized ImageIcon.
     */
    public static ImageIcon resizeIcon(ImageIcon icon, int height) {
        int width = (int) (icon.getIconWidth() * ((double) height / icon.getIconHeight()));
        Image scaledImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }

    /**
     * Retrieves the instance of the Random number generator used in the class.
     *
     * @return Random instance.
     */
    public static Random getRandom() {
        return random;
    }
}
