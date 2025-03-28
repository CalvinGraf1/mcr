package render;

import shape.Bouncable;

import java.awt.*;

/**
 * Renderer implementation for rendering filled shapes
 * It implements the Renderer interface.
 *
 * @author Calvin Graf, Killian Demont
 * @version 29/03/2024
 */
public class FilledShapeRenderer implements Renderer {

    // Singleton instance
    private static class InstanceFilled {
        static final FilledShapeRenderer instance = new FilledShapeRenderer();
    }

    /**
     * Private constructor to prevent instantiation from outside.
     */
    private FilledShapeRenderer() {}

    /**
     * Retrieves the singleton instance of FilledShapeRenderer.
     *
     * @return the singleton instance of FilledShapeRenderer
     */
    public static FilledShapeRenderer getInstance() {
        return InstanceFilled.instance;
    }

    /**
     * Renders the given Bouncable object as a filled shape.
     *
     * @param g the Graphics2D object to render the shape
     * @param b the Bouncable object representing the shape to render
     */
    @Override
    public void display(Graphics2D g, Bouncable b) {
        g.setColor(b.getColor());
        g.fill(b.getShape());
    }
}
