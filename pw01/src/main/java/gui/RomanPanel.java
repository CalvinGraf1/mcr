package gui;

import model.Chronometer;

import java.awt.*;

/**
 * The RomanPanel class represents a JPanel displaying the time in a Roman numeral format.
 * It extends the DialPanel abstract class.
 *
 * @author Cavin Graf, Kilian Demont
 */
public class RomanPanel extends DialPanel{
    /**
     * Constructs a RomanPanel with the specified chronometer.
     *
     * @param chronometer The chronometer to display the time from.
     */
    public RomanPanel(Chronometer chronometer){
        super(chronometer, Toolkit.getDefaultToolkit().getImage(RomanPanel.class.getResource("/cadran_chiffres_romains.jpg")),
                Color.BLACK, Color.GRAY, Color.YELLOW);
    }
}
