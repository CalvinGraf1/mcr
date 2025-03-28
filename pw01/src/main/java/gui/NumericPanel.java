package gui;

import model.Chronometer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * The NumericPanel class represents a JPanel displaying the numeric representation of a chronometer.
 * It extends the ChronoPanel abstract class.
 *
 * @author Cavin Graf, Kilian Demont
 */
public class NumericPanel extends ChronoPanel {
    private final JLabel chronoLabel;

    /**
     * Constructs a NumericPanel with the specified chronometer.
     *
     * @param chronometer The chronometer to display information from.
     */
    public NumericPanel(Chronometer chronometer)  {
        super(chronometer);

        setLayout(new BorderLayout());

        chronoLabel = new JLabel();
        chronoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        updateChronometer();
        chronoLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        add(chronoLabel, BorderLayout.CENTER);
    }

    @Override
    protected void updateChronometer() {
        int hours = hours();
        int minutes = minutes();
        int seconds = seconds();
        chronoLabel.setText("Chrono #" + id() + ": "
                + (hours > 9 ? hours : ("0" + hours))  + "h "
                + (minutes > 9 ? minutes : ("0" + minutes)) + "m "
                + (seconds > 9 ? seconds : ("0" + seconds)) + "s");
    }
}
