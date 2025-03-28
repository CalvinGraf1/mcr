package display;

/**
 * Singleton class to manage the display of the shapes
 *
 * @author Calvin Graf, Kilian Demont
 * @version 05/04/2024
 */
public final class DisplayerSingleton {

    private static Displayer instance;

    /**
     * Private constructor to prevent instantiation
     */
    private DisplayerSingleton() {}

    /**
     * Register the instance of the Displayer
     * @param impl the class of the Displayer
     * @throws Exception if the instance is already registered
     */
    public static void registerInstance(Class<? extends Displayer> impl) throws Exception {
        if (instance != null) {
            throw new Exception("Instance already registered");
        }

        instance = impl.getDeclaredConstructor().newInstance();
    }

    /**
     * Retrieve the instance of the Displayer
     * @return the instance of the Displayer
     * @throws IllegalStateException if the instance is not registered
     */
    public static Displayer getInstance() {
        if (instance == null) {
            throw new IllegalStateException("Instance not registered");
        }
        return instance;
    }
}
