package gui;

import model.Chronometer;
import observator.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * The ChronoPanel class represents an abstract JPanel that displays information about a chronometer.
 * Subclasses of ChronoPanel are responsible for defining how the chronometer information is displayed.
 *
 * @author Cavin Graf, Kilian Demont
 */
public abstract class ChronoPanel extends JPanel implements Observer {
    private static final int WIDTH = 200;
    private static final int HEIGHT = 200;
    private final Chronometer chronometer;

    /**
     * Constructs a ChronoPanel with the specified chronometer.
     *
     * @param chronometer The chronometer to display information from.
     */
    public ChronoPanel(Chronometer chronometer) {
        this.chronometer = chronometer;

        setLayout(new FlowLayout());
        setSize(WIDTH, HEIGHT);
        setPreferredSize(getSize());

        // Start or stop the timer when clicking on the panel
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(chronometer.getIsRunning()) chronometer.stop();
                else chronometer.start();
            }
        });
    }

    /**
     * Updates the displayed information of the chronometer.
     */
    protected abstract void updateChronometer();

    /**
     * Gets the ID of the chronometer associated with this panel.
     *
     * @return The ID of the chronometer.
     */
    protected int id(){
        return chronometer.getId();
    }

    /**
     * Gets the hours part of the chronometer's elapsed time.
     *
     * @return The hours part of the elapsed time.
     */
    public int hours() {
        return chronometer.getSeconds() / 3600;
    }

    /**
     * Gets the minutes part of the chronometer's elapsed time.
     *
     * @return The minutes part of the elapsed time.
     */
    public int minutes() {
        return chronometer.getSeconds() % 3600 / 60;
    }

    /**
     * Gets the seconds part of the chronometer's elapsed time.
     *
     * @return The seconds part of the elapsed time.
     */
    public int seconds(){
        return chronometer.getSeconds() % 3600 % 60;
    }

    @Override
    public void update() {
        updateChronometer();
    }
}
