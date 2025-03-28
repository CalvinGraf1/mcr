import display.*;
import shape.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import javax.swing.*;

/**
 * The Main class serves as the entry point for the shape moving application. It initializes
 * the application, creates shapes, handles user input, and manages the main execution loop.
 *
 * @author Calvin Graf, Killian Demont
 * @version 07/04/2024
 */
public class Main {
    private static final int NB_SHAPES = 10;
    private static final int SIZE_MIN_SHAPE = 10;
    private static final int SIZE_DELTA_SHAPE = 45;
    private static final int SPEED_MAX_SHAPE = 3;
    private static final int SPEED_MIN_SHAPE = -3;
    private static final int DELAY = 10;

    private final List<Bouncable> bouncers = new LinkedList<>();

    /**
     * Constructs a new Main instance and initializes the shape moving application.
     *
     * @throws Exception if there is an error initializing the application
     */
    public Main() throws Exception {
        DisplayerSingleton.registerInstance(ShapeMovingApp.class);
        Displayer.getInstance().addKeyListener(new BouncableActions());
    }

    /**
     * Starts the execution of the shape moving application.
     */
    public void run() {
        Displayer.getInstance().setTitle("Bouncers");
        new Timer(DELAY, event -> {
            for (Bouncable bouncer : bouncers) {
                bouncer.move();
                bouncer.draw();
            }
            Displayer.getInstance().repaint();
        }).start();
    }

    /**
     * Creates shapes using the specified factory and adds them to the list of bouncers.
     *
     * @param factory the factory used to create shapes
     */
    private void createShapes(ShapeAbstractFactory factory) {
        int size, x, y, dx, dy;
        Random rdm = new Random();
        for (int i = 0; i < NB_SHAPES * 2; i++) {
            size = rdm.nextInt(SIZE_DELTA_SHAPE) + SIZE_MIN_SHAPE;
            x = rdm.nextInt(Displayer.getInstance().getWidth() - 2 * size) + size;
            y = rdm.nextInt(Displayer.getInstance().getHeight() - 2 * size) + size;
            dx = randomNonZero(SPEED_MIN_SHAPE, SPEED_MAX_SHAPE);
            dy = randomNonZero(SPEED_MIN_SHAPE, SPEED_MAX_SHAPE);
            if(i % 2 == 0) bouncers.add(factory.createCircle(x, y, size, dx, dy));
            else bouncers.add(factory.createSquare(x, y, size, dx, dy));
        }
    }

    /**
     * Generates a random non-zero integer within the specified range.
     *
     * @param min the minimum value (inclusive)
     * @param max the maximum value (exclusive)
     * @return a random non-zero integer
     */
    private int randomNonZero(int min, int max) {
        Random random = new Random();
        int result;
        do {
            result = random.nextInt(min, max);
        } while (result == 0);
        return result;
    }

    /**
     * The BouncableActions class defines key event handling for the shape moving application.
     * It listens for key presses and performs corresponding actions such as clearing the screen,
     * creating shapes, or exiting the application.
     */
    private class BouncableActions extends KeyAdapter {

        private final FilledShapeConcreteFactory fillFactory = new FilledShapeConcreteFactory();
        private final EmptyShapeConcreteFactory emptyFactory = new EmptyShapeConcreteFactory();

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyChar()) {
                case 'e':
                    bouncers.clear();
                    break;
                case 'b':
                    createShapes(emptyFactory);
                    break;
                case 'f':
                    createShapes(fillFactory);
                    break;
                case 'q':
                    System.exit(0);
                    break;
            }
        }
    }

    /**
     * The main method creates an instance of Main and invokes its run method.
     *
     * @param args command line arguments (unused)
     * @throws Exception if there is an error during application execution
     */
    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeLater(new Main()::run);
    }
}