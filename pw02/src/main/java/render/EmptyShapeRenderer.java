package render;

import shape.Bouncable;

import java.awt.*;

/**
 * Renderer implementation for rendering empty shapes with borders.
 * This renderer draws shapes with an empty fill and a specified border thickness.
 * It implements the Renderer interface.
 *
 * @author Calvin Graf, Killian Demont
 * @version 29/03/2024
 */
public class EmptyShapeRenderer implements Renderer {

    // Constant for the thickness of the shape border
    private static final int BORDER_THICKNESS = 2;

    // Singleton instance
    private static class InstanceEmpty {
        static final EmptyShapeRenderer instance = new EmptyShapeRenderer();
    }

    /**
     * Private constructor to prevent instantiation from outside.
     */
    private EmptyShapeRenderer() {}

    /**
     * Retrieves the singleton instance of EmptyShapeRenderer.
     *
     * @return the singleton instance of EmptyShapeRenderer
     */
    public static EmptyShapeRenderer getInstance() {
        return InstanceEmpty.instance;
    }


    /**
     * Renders the given Bouncable object as an empty shape with a border.
     *
     * @param g the Graphics2D object to render the shape
     * @param b the Bouncable object representing the shape to render
     */
    @Override
    public void display(Graphics2D g, Bouncable b) {
        g.setColor(b.getColor());
        g.setStroke(new BasicStroke(BORDER_THICKNESS));
        g.draw(b.getShape());
    }
}
