import gui.ControlApp;

import javax.swing.*;

/**
 * The Main class is the entry point for the application.
 * It creates an instance of ControlApp to manage the chronometers.
 *
 * @author Cavin Graf, Kilian Demont
 */
public class Main {
    /**
     * The main method is the entry point for the application.
     * It creates an instance of ControlApp with the specified number of chronometers.
     *
     * @param args The command line arguments. The first argument should be the number of chronometers (1-9).
     */
    public static void main(String[] args) {
        int chronometersCount = 0;
        // Check if a command-line argument is provided
        if (args.length > 0 && (Integer.parseInt(args[0]) >= 1 && Integer.parseInt(args[0]) <= 9)) {
            try {
                // Convert the argument to an integer to set the number of chronometers
                chronometersCount = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                // Handle conversion errors
                System.err.println("Le nombre de chronomètre doit être un entier valide.");
                System.exit(1); // Exit with error code
            }

            // Create an instance of ControlApp with the specified number of chronometers
            int finalTimersCount = chronometersCount;
            SwingUtilities.invokeLater(() -> new ControlApp(finalTimersCount));
        }
        // Throw an exception if the number of chronometers is not within the valid range
        else throw new RuntimeException("Le nombre de chronomètre doit être compris entre 1 et 9.");

    }
}