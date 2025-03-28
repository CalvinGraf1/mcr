package shape.square;

import render.FilledShapeRenderer;
import render.Renderer;

import java.awt.*;

/**
 * Represents a filled square shape.
 * This class extends the Square class and defines the appearance of a filled square.
 *
 * @author Calvin Graf, Killian Demont
 * @version 28/03/2024
 */
public class FilledSquare extends Square {

    /**
     * Constructs a filled square with the specified parameters.
     *
     * @param x    the x-coordinate of the top-left corner of the bounding box of the square
     * @param y    the y-coordinate of the top-left corner of the bounding box of the square
     * @param size the size of the square (width and height)
     * @param dx   the horizontal velocity of the square
     * @param dy   the vertical velocity of the square
     */
    public FilledSquare(int x, int y, int size, int dx, int dy) {
        super(x, y, size, dx, dy);
    }

    /**
     * Gets the color of the filled square.
     *
     * @return the color of the filled square
     */
    @Override
    public Color getColor() {
        return Color.ORANGE;
    }

    /**
     * Gets the renderer for the filled square.
     *
     * @return the renderer for the filled square
     */
    @Override
    protected Renderer getRenderer() {
        return FilledShapeRenderer.getInstance();
    }
}
