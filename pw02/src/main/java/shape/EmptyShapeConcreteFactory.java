package shape;

import shape.circle.Circle;
import shape.circle.EmptyCircle;
import shape.square.EmptySquare;
import shape.square.Square;

/**
 * Concrete implementation of the ShapeAbstractFactory interface for creating empty circles and squares.
 * This factory creates shapes without fill.
 *
 * @author Calvin Graf, Killian Demont
 * @version 28/03/2024
 */
public class EmptyShapeConcreteFactory implements ShapeAbstractFactory {

    /**
     * Creates an empty circle with the specified parameters.
     *
     * @param x    the x-coordinate of the circle's position
     * @param y    the y-coordinate of the circle's position
     * @param size the size of the circle
     * @param dx   the velocity along the x-axis
     * @param dy   the velocity along the y-axis
     * @return a new instance of an empty circle
     */
    @Override
    public Circle createCircle(int x, int y, int size, int dx, int dy) {
        return new EmptyCircle(x, y, size, dx, dy);
    }

    /**
     * Creates an empty square with the specified parameters.
     *
     * @param x    the x-coordinate of the square's position
     * @param y    the y-coordinate of the square's position
     * @param size the size of the square
     * @param dx   the velocity along the x-axis
     * @param dy   the velocity along the y-axis
     * @return a new instance of an empty square
     */
    @Override
    public Square createSquare(int x, int y, int size, int dx, int dy) {
        return new EmptySquare(x, y, size, dx, dy);
    }
}