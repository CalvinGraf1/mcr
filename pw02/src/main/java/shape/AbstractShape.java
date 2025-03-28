package shape;

import render.Renderer;
import display.Displayer;

/**
 * Represents an abstract shape that implements common functionality for shapes that can bounce within a graphical display.
 * Subclasses of this abstract class must provide implementations for the {@code getRenderer()} method.
 *
 * @author Calvin Graf, Killian Demont
 * @version 28/03/2024
 */
public abstract class AbstractShape implements Bouncable {
    private int x;
    private int y;
    private int size;
    private int dx;
    private int dy;

    /**
     * Constructs an abstract shape with the specified position, size, and velocity.
     *
     * @param x    the x-coordinate of the shape's position
     * @param y    the y-coordinate of the shape's position
     * @param size the size of the shape
     * @param dx   the velocity along the x-axis
     * @param dy   the velocity along the y-axis
     */
    public AbstractShape(int x, int y, int size, int dx, int dy) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Gets the x-coordinate of the shape's position.
     *
     * @return the x-coordinate of the shape's position
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the y-coordinate of the shape's position.
     *
     * @return the y-coordinate of the shape's position
     */
    public int getY() {
        return y;
    }

    /**
     * Gets the size of the shape.
     *
     * @return the size of the shape
     */
    public int getSize() {
        return size;
    }

    /**
     * Moves the shape according to its velocity, ensuring it stays within the bounds of the display.
     */
    public void move() {
        checkInBound();
        if(x + dx < 0 || x + dx > Displayer.getInstance().getWidth() - size) reverseXDirection();
        else x += dx;

        if(y + dy < 0 || y + dy > Displayer.getInstance().getHeight() - size) reverseYDirection();
        else y += dy;
    }

    /**
     * Ensures that the shape stays within the bounds of the display.
     */
    private void checkInBound() {
        if(x > Displayer.getInstance().getWidth() - size) x = Displayer.getInstance().getWidth() - size;
        else if (x < 0) x = 0;
        if(y > Displayer.getInstance().getHeight() - size) y = Displayer.getInstance().getHeight() - size;
        else if (y < 0) y = 0;
    }

    /**
     * Gets the renderer associated with this shape.
     *
     * @return the renderer associated with this shape
     */
    protected abstract Renderer getRenderer();

    /**
     * Draws the shape on the graphical display using the associated renderer.
     */
    @Override
    public final void draw() {
        getRenderer().display(
                Displayer.getInstance().getGraphics(),
                this
        );
    }

    /**
     * Reverses the direction of movement along the x-axis.
     */
    public void reverseXDirection() {
        dx = -dx;
    }

    /**
     * Reverses the direction of movement along the y-axis.
     */
    public void reverseYDirection() {
        dy = -dy;
    }
}
