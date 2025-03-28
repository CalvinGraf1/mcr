package render;

import shape.Bouncable;
import java.awt.*;

/**
 * The Renderer interface defines a method for displaying a Bouncable object using Graphics2D.
 * Implementing classes are responsible for rendering shapes based on the provided Bouncable object.
 * This interface is designed to support various rendering strategies for shapes.
 *
 * @author Calvin Graf, Killian Demont
 * @version 28/03/2024
 */
public interface Renderer {

    /**
     * Displays the given Bouncable object using the provided Graphics2D object.
     *
     * @param g the Graphics2D object used for rendering
     * @param b the Bouncable object to be displayed
     */
    void display(Graphics2D g, Bouncable b);
}