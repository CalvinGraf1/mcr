package gui;

import model.Chronometer;

import javax.swing.*;
import java.awt.*;

/**
 * ControlPanel represents a panel for controlling a single chronometer.
 *
 * @author Cavin Graf, Kilian Demont
 */
public class ControlPanel extends JPanel {
    private final Chronometer chronometer;

    /**
     * Constructs a ControlPanel for a given chronometer.
     *
     * @param chronometer The chronometer to control.
     */
    public ControlPanel(Chronometer chronometer) {
        this.chronometer = chronometer;

        setLayout(new FlowLayout());


        JLabel title = new JLabel("Chrono #" + chronometer.getId());
        add(title);

        // Add buttons for controlling the chronometer
        addButton("Démarrer", this::handleStartButton);
        addButton("Arrêter", this::handleStopButton);
        addButton("Réinitialiser", this::handleInitialiseButton);
        addButton("Cadran Romain", this::handleRomanDialButton);
        addButton("Cadran Arabe", this::handleArabicDialButton);
        addButton("Numéric", this::handleNumericButton);
    }

    /**
     * Utility method to add a button with the specified text and action listener.
     *
     * @param text     The text to display on the button.
     * @param listener The action listener for the button click event.
     */
    private void addButton(String text, Runnable listener) {
        JButton button = new JButton(text);
        button.addActionListener(e -> listener.run());
        add(button);
    }

    // Methods to handle button click events

    /**
     * Handles the click event for the start button.
     */
    private void handleStartButton() {
        chronometer.start();
    }

    /**
     * Handles the click event for the stop button.
     */
    private void handleStopButton() {
        chronometer.stop();
    }

    /**
     * Handles the click event for the reset button.
     */
    private void handleInitialiseButton() {
        chronometer.reset();
    }

    /**
     * Handles the click event for the Roman dial button.
     */
    private void handleRomanDialButton(){
        DialPanel dialPanel = new RomanPanel(chronometer);
        chronometer.addObserver(dialPanel);
        SwingUtilities.invokeLater(() -> new ChronoApp(dialPanel));
    }

    /**
     * Handles the click event for the Arabic dial button.
     */
    private void handleArabicDialButton() {
        DialPanel dialPanel = new ArabicPanel(chronometer);
        chronometer.addObserver(dialPanel);
        SwingUtilities.invokeLater(() -> new ChronoApp(dialPanel));
    }

    /**
     * Handles the click event for the Numeric dial button.
     */
    private void handleNumericButton() {
        NumericPanel numericPanel = new NumericPanel(chronometer);
        chronometer.addObserver(numericPanel);
        SwingUtilities.invokeLater(() -> new ChronoApp(numericPanel));
    }
}
