package display;

import java.awt.*;
import java.awt.event.KeyListener;

/**
 * The Displayer interface defines methods for interacting with a display window,
 * such as getting its dimensions, obtaining the graphics context, repainting the window,
 * setting its title, and adding a KeyListener.
 * Implementing classes are responsible for providing the functionality to manage and display graphical content.
 * This interface also provides a static method to obtain the singleton instance of the Displayer.
 *
 * @author Calvin Graf, Killian Demont
 * @version 05/04/2024
 */
public interface Displayer {

    /**
     * Returns the width of the display window.
     *
     * @return the width of the display window
     */
    int getWidth();

    /**
     * Returns the height of the display window.
     *
     * @return the height of the display window
     */
    int getHeight();

    /**
     * Returns the Graphics2D object used for rendering on the display window.
     *
     * @return the Graphics2D object used for rendering
     */
    Graphics2D getGraphics();

    /**
     * Repaints the display window to update its contents.
     */
    void repaint();

    /**
     * Sets the title of the display window.
     *
     * @param title the new title of the display window
     */
    void setTitle(String title);

    /**
     * Adds a KeyListener to the display window to handle keyboard input.
     *
     * @param ka the KeyListener to be added
     */
    void addKeyListener(KeyListener ka);

    /**
     * Returns the singleton instance of the Displayer.
     *
     * @return the singleton instance of the Displayer
     */
    static Displayer getInstance() {
        return DisplayerSingleton.getInstance();
    }
}