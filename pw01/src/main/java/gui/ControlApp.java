package gui;

import model.Chronometer;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

/**
 * ControlApp represents a graphical application for controlling a set of chronometers.
 *
 * @author Cavin Graf, Kilian Demont
 */
public class ControlApp extends JFrame {
    private final int chronometersCount;
    private final LinkedList<Chronometer> chronometerList = new LinkedList<>();

    /**
     * Constructs a ControlApp.
     *
     * @param chronometersCount The number of chronometers to control.
     */
    public ControlApp(int chronometersCount){
        this.chronometersCount = chronometersCount;

        setTitle("Panel de contrôle");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.WHITE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // Create control panels for each chronometer and add them to the frame
        for (int i = 0; i < chronometersCount; i++){
            Chronometer chronometer = new Chronometer();
            chronometerList.add(chronometer);
            getContentPane().add(new ControlPanel(chronometer));
        }

        // Create the panel containing buttons for dial types
        JPanel allPanel = new JPanel();
        JLabel allPanelTitle = new JLabel("Tous les chrono");

        JButton romanDialButton = new JButton("Cadran Romain");
        romanDialButton.addActionListener(e -> handleRomanDialButton());

        JButton arabicDialButton = new JButton("Cadran Arabe");
        arabicDialButton.addActionListener(e -> handleArabicDialButton());

        JButton numericButton = new JButton("Numéric");
        numericButton.addActionListener(e -> handleNumericButton());

        // Add buttons to the panel with some spacing
        allPanel.add(Box.createHorizontalGlue());
        allPanel.add(allPanelTitle);
        allPanel.add(Box.createHorizontalStrut(5));
        allPanel.add(romanDialButton);
        allPanel.add(Box.createHorizontalStrut(5));
        allPanel.add(arabicDialButton);
        allPanel.add(Box.createHorizontalStrut(5));
        allPanel.add(numericButton);
        allPanel.setLayout(new BoxLayout(allPanel, BoxLayout.X_AXIS));
        allPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 5));

        getContentPane().add(allPanel, BorderLayout.EAST);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    /**
     * Handles the click on the "Roman Dial" button.
     */
    private void handleRomanDialButton() {
        LinkedList<ChronoPanel> chronos = new LinkedList<>();
        for (int i = 0; i < chronometersCount; i++){
            DialPanel dialPanel = new RomanPanel(chronometerList.get(i));
            chronos.add(dialPanel);
            chronometerList.get(i).addObserver(dialPanel);
        }
        SwingUtilities.invokeLater(() -> new ChronoApp(chronos));
    }

    /**
     * Handles the click on the "Arabic Dial" button.
     */
    private void handleArabicDialButton() {
        LinkedList<ChronoPanel> chronos = new LinkedList<>();
        for (int i = 0; i < chronometersCount; i++) {
            DialPanel dialPanel = new ArabicPanel(chronometerList.get(i));
            chronos.add(dialPanel);
            chronometerList.get(i).addObserver(dialPanel);
        }
        SwingUtilities.invokeLater(() -> new ChronoApp(chronos));
    }

    /**
     * Handles the click on the "Numeric" button.
     */
    private void handleNumericButton() {
        LinkedList<ChronoPanel> chronos = new LinkedList<>();
        for (int i = 0; i < chronometersCount; i++){
            NumericPanel numericPanel = new NumericPanel(chronometerList.get(i));
            chronos.add(numericPanel);
            chronometerList.get(i).addObserver(numericPanel);
        }
        SwingUtilities.invokeLater(() -> new ChronoApp(chronos));
    }
}
