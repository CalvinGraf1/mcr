package shape;

import shape.circle.Circle;
import shape.circle.FilledCircle;
import shape.square.FilledSquare;
import shape.square.Square;

/**
 * Concrete implementation of the ShapeAbstractFactory interface for creating filled circles and squares.
 * This factory creates shapes with fill.
 *
 * @author Calvin Graf, Killian Demont
 * @version 28/03/2024
 */
public class FilledShapeConcreteFactory implements ShapeAbstractFactory {

    /**
     * Creates a filled circle with the specified parameters.
     *
     * @param x    the x-coordinate of the circle's position
     * @param y    the y-coordinate of the circle's position
     * @param size the size of the circle
     * @param dx   the velocity along the x-axis
     * @param dy   the velocity along the y-axis
     * @return a new instance of a filled circle
     */
    @Override
    public Circle createCircle(int x, int y, int size, int dx, int dy) {
        return new FilledCircle(x, y, size, dx, dy);
    }

    /**
     * Creates a filled square with the specified parameters.
     *
     * @param x    the x-coordinate of the square's position
     * @param y    the y-coordinate of the square's position
     * @param size the size of the square
     * @param dx   the velocity along the x-axis
     * @param dy   the velocity along the y-axis
     * @return a new instance of a filled square
     */
    @Override
    public Square createSquare(int x, int y, int size, int dx, int dy) {
        return new FilledSquare(x, y, size, dx, dy);
    }
}