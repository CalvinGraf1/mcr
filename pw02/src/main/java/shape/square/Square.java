package shape.square;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import shape.AbstractShape;

/**
 * Represents an abstract square shape.
 * This class provides the basic functionality and properties of a square shape.
 *
 * @author Calvin Graf, Killian Demont
 * @version 28/03/2024
 */
public abstract class Square extends AbstractShape {

    /**
     * Constructs a square with the specified parameters.
     *
     * @param x    the x-coordinate of the top-left corner of the bounding box of the square
     * @param y    the y-coordinate of the top-left corner of the bounding box of the square
     * @param size the size of the square (width and height)
     * @param dx   the horizontal velocity of the square
     * @param dy   the vertical velocity of the square
     */
    public Square(int x, int y, int size, int dx, int dy) {
        super(x, y, size, dx, dy);
    }

    /**
     * Gets the shape representing the square.
     *
     * @return the shape of the square
     */
    @Override
    public final Shape getShape() {
        return new Rectangle2D.Double(getX(), getY(), getSize(), getSize());
    }
}