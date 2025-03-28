package shape;

import java.awt.*;

/**
 * Represents a shape that can bounce within a graphical display.
 * Implementations of this interface define methods for drawing the shape, moving it,
 * retrieving its color, and obtaining its geometric shape.
 *
 * @author Calvin Graf, Killian Demont
 * @version 28/03/2024
 */
public interface Bouncable {

    /**
     * Draws the shape on the graphical display.
     */
    void draw();

    /**
     * Moves the shape according to its velocity.
     */
    void move();

    /**
     * Gets the color of the shape.
     *
     * @return the color of the shape
     */
    Color getColor();

    /**
     * Gets the geometric shape representing the shape.
     *
     * @return the geometric shape of the shape
     */
    Shape getShape();
}