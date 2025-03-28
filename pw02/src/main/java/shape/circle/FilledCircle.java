package shape.circle;

import render.FilledShapeRenderer;
import render.Renderer;

import java.awt.*;

/**
 * Represents a filled circle shape, extending the Circle class.
 * This class defines the appearance and rendering of a filled circle.
 *
 * @author Calvin Graf, Killian Demont
 * @version 28/03/2024
 */
public class FilledCircle extends Circle {

    /**
     * Constructs a filled circle with the specified parameters.
     *
     * @param x    the x-coordinate of the top-left corner of the bounding box of the circle
     * @param y    the y-coordinate of the top-left corner of the bounding box of the circle
     * @param size the size of the circle (diameter)
     * @param dx   the horizontal velocity of the circle
     * @param dy   the vertical velocity of the circle
     */
    public FilledCircle(int x, int y, int size, int dx, int dy) {
        super(x, y, size, dx, dy);
    }

    /**
     * Gets the color of the filled circle.
     *
     * @return the color of the filled circle (blue)
     */
    @Override
    public Color getColor() {
        return Color.BLUE;
    }

    /**
     * Gets the renderer for the filled circle.
     *
     * @return the renderer for the filled circle
     */
    @Override
    protected Renderer getRenderer() {
        return FilledShapeRenderer.getInstance();
    }
}
