package shape.circle;

import java.awt.*;
import java.awt.geom.Ellipse2D;

import shape.AbstractShape;

/**
 * This abstract class represents a circle shape.
 * It extends the AbstractShape class and implements methods specific to circles.
 *
 * @author Calvin Graf, Killian Demont
 * @version 28/03/2024
 */
public abstract class Circle extends AbstractShape {

    /**
     * Constructs a circle with the specified parameters.
     *
     * @param x    the x-coordinate of the top-left corner of the bounding box of the circle
     * @param y    the y-coordinate of the top-left corner of the bounding box of the circle
     * @param size the size of the circle (diameter)
     * @param dx   the horizontal velocity of the circle
     * @param dy   the vertical velocity of the circle
     */
    public Circle(int x, int y, int size, int dx, int dy) {
        super(x, y, size, dx, dy);
    }

    /**
     * Gets the shape representing the circle.
     *
     * @return the Ellipse2D shape representing the circle
     */
    @Override
    public final Shape getShape() {
        return new Ellipse2D.Double(getX(), getY(), getSize(), getSize());
    }
}
