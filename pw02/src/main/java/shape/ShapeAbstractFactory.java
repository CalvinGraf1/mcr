package shape;

import shape.circle.Circle;
import shape.square.Square;

/**
 * Interface for a shape abstract factory that provides methods for creating circles and squares.
 * Implementations of this interface should provide concrete implementations for creating specific types of shapes.
 *
 * @author Calvin Graf, Killian Demont
 * @version 28/03/2024
 */
public interface ShapeAbstractFactory {

    /**
     * Creates a circle with the specified parameters.
     *
     * @param x    the x-coordinate of the circle's position
     * @param y    the y-coordinate of the circle's position
     * @param size the size of the circle
     * @param dx   the velocity along the x-axis
     * @param dy   the velocity along the y-axis
     * @return a new instance of a circle
     */
    Circle createCircle(int x, int y, int size, int dx, int dy);


    /**
     * Creates a square with the specified parameters.
     *
     * @param x    the x-coordinate of the square's position
     * @param y    the y-coordinate of the square's position
     * @param size the size of the square
     * @param dx   the velocity along the x-axis
     * @param dy   the velocity along the y-axis
     * @return a new instance of a square
     */
    Square createSquare(int x, int y, int size, int dx, int dy);
}