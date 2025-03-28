package gui;

import model.Chronometer;

import java.awt.*;

/**
 * The ArabicPanel class represents a JPanel displaying the time in Arabic numerals.
 * It extends the DialPanel abstract class.
 *
 * @author Cavin Graf, Kilian Demont
 */
public class ArabicPanel extends DialPanel {
    /**
     * Constructs an ArabicPanel with the specified chronometer.
     *
     * @param chronometer The chronometer to display the time from.
     */
    public ArabicPanel(Chronometer chronometer){
        super(chronometer, Toolkit.getDefaultToolkit().getImage(ArabicPanel.class.getResource("/cadran_chiffres_arabes.jpg")),
                Color.BLACK, Color.BLUE, Color.RED);
    }
}
