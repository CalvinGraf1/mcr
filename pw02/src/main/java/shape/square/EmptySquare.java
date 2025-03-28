package shape.square;

import render.EmptyShapeRenderer;
import render.Renderer;

import java.awt.*;

/**
 * Represents an empty square shape.
 * This class extends the Square class and defines the appearance of an empty square.
 *
 * @author Calvin Graf, Killian Demont
 * @version 28/03/2024
 */
public class EmptySquare extends Square {

    /**
     * Constructs an empty square with the specified parameters.
     *
     * @param x    the x-coordinate of the top-left corner of the bounding box of the square
     * @param y    the y-coordinate of the top-left corner of the bounding box of the square
     * @param size the size of the square (width and height)
     * @param dx   the horizontal velocity of the square
     * @param dy   the vertical velocity of the square
     */
    public EmptySquare(int x, int y, int size, int dx, int dy) {
        super(x, y, size, dx, dy);
    }

    /**
     * Gets the color of the empty square.
     *
     * @return the color of the empty square
     */
    @Override
    public Color getColor() {
        return Color.RED;
    }

    /**
     * Gets the renderer for the empty square.
     *
     * @return the renderer for the empty square
     */
    @Override
    protected Renderer getRenderer() {
        return EmptyShapeRenderer.getInstance();
    }
}
