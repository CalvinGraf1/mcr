package display;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

/**
 * The ShapeMovingApp class implements the Displayer interface and provides a simple graphical
 * application for displaying shapes and handling user input. It consists of a JFrame containing
 * a GraphicsPanel, which serves as the canvas for drawing shapes.
 * <p>
 * This class allows users to set the title of the application window, obtain the dimensions of
 * the canvas, retrieve the graphics context for rendering shapes, repaint the canvas, and add
 * KeyListeners for handling keyboard input.
 *
 * @author Calvin Graf, Killian Demont
 * @version 07/04/2024
 */
public class ShapeMovingApp implements Displayer {

    private final JFrame frame;
    private final GraphicsPanel panel;

    /**
     * Constructs a new ShapeMovingApp instance.
     */
    public ShapeMovingApp() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new GraphicsPanel();
        frame.add(panel, BorderLayout.CENTER);

        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Retrieves the width of the canvas.
     *
     * @return the width of the canvas
     */
    @Override
    public int getWidth() {
        return panel.getWidth();
    }

    /**
     * Retrieves the height of the canvas.
     *
     * @return the height of the canvas
     */
    @Override
    public int getHeight() {
        return panel.getHeight();
    }

    /**
     * Retrieves the graphics context for rendering shapes on the canvas.
     *
     * @return the graphics context for rendering shapes
     */
    @Override
    public Graphics2D getGraphics() {
        return (Graphics2D) panel.img.getGraphics();
    }

    /**
     * Repaints the canvas to update its contents.
     */
    @Override
    public void repaint() {
        panel.repaint();
    }

    /**
     * Sets the title of the application window.
     *
     * @param title the new title of the application window
     */
    @Override
    public void setTitle(String title) {
        frame.setTitle(title);
    }

    /**
     * Adds a KeyListener to the application window for handling keyboard input.
     *
     * @param ka the KeyListener to be added
     */
    @Override
    public void addKeyListener(KeyListener ka) {
        frame.addKeyListener(ka);
    }

    private static class GraphicsPanel extends JPanel {

        private static final int DEFAULT_WIDTH = 500;
        private static final int DEFAULT_HEIGHT = 500;

        // This images serves as a buffer to handle content updates on a frame-by-frame basis.
        private Image img;

        public GraphicsPanel() {
            setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
            setPreferredSize(getSize());
            newImg();
            addComponentListener(new ResizeListener());
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            img.getGraphics().fillRect(0, 0, getWidth(), getHeight());
            g.dispose();
        }

        private void newImg() {
            img = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        }

        private class ResizeListener extends ComponentAdapter {
            @Override
            public void componentResized(ComponentEvent e) {
                newImg();
            }
        }
    }
}