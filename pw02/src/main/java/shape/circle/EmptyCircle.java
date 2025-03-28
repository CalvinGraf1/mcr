package shape.circle;

import render.EmptyShapeRenderer;
import render.Renderer;

import java.awt.*;

/**
 * Represents an empty circle shape, extending the Circle class.
 * This class defines the appearance and rendering of an empty circle.
 *
 * @author Calvin Graf, Killian Demont
 * @version 28/03/2024
 */
public class EmptyCircle extends Circle {

    /**
     * Constructs an empty circle with the specified parameters.
     *
     * @param x    the x-coordinate of the top-left corner of the bounding box of the circle
     * @param y    the y-coordinate of the top-left corner of the bounding box of the circle
     * @param size the size of the circle (diameter)
     * @param dx   the horizontal velocity of the circle
     * @param dy   the vertical velocity of the circle
     */
    public EmptyCircle(int x, int y, int size, int dx, int dy) {
        super(x, y, size, dx, dy);
    }

    /**
     * Gets the color of the empty circle.
     *
     * @return the color of the empty circle (green)
     */
    @Override
    public Color getColor() {
        return Color.GREEN;
    }

    /**
     * Gets the renderer for the empty circle.
     *
     * @return the renderer for the empty circle
     */
    @Override
    protected Renderer getRenderer() {
        return EmptyShapeRenderer.getInstance();
    }
}
