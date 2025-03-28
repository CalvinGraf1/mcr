package gui;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

/**
 * The ChronoApp class represents a JFrame for displaying one or multiple chronometers.
 *
 * @author Cavin Graf, Kilian Demont
 */
public class ChronoApp extends JFrame {
    /**
     * Constructs a ChronoApp to display a single chronometer panel.
     *
     * @param chronoPanel The chronometer panel to display.
     */
    public ChronoApp(ChronoPanel chronoPanel) {
        setLayout(new FlowLayout());
        getContentPane().add(chronoPanel);
        setResizable(false);
        pack();
        initialize();
    }

    /**
     * Constructs a ChronoApp to display multiple chronometer panels.
     *
     * @param chronoPanels A list of chronometer panels to display.
     */
    public ChronoApp(LinkedList<ChronoPanel> chronoPanels) {
        if (chronoPanels.isEmpty())
            throw new RuntimeException("Pas de chronos à afficher");

        setLayout(new FlowLayout());

        // Add each chronometer panel to the content pane of the frame
        for (var chronoPanel : chronoPanels)
            getContentPane().add(chronoPanel);

        setMinimumSize(chronoPanels.getFirst().getMinimumSize());
        setResizable(true);
        pack();
        initialize();
    }

    /**
     * Initializes the frame with default settings.
     */
    private void initialize() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
