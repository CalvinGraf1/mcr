package gui;

import model.Chronometer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * The DialPanel class represents an abstract JPanel that displays a dial-like interface for a chronometer.
 * It provides functionality to draw clock hands (hour, minute, second) and update them based on the chronometer's elapsed time.
 *
 * @author Cavin Graf, Kilian Demont
 */
public abstract class DialPanel extends ChronoPanel {
    Image image;
    private int hourAngle, minuteAngle, secondAngle;
    private final Color hourHandColor, minuteHandColor, secondHandColor;

    /**
     * Constructs a DialPanel with the specified parameters.
     *
     * @param chronometer      The chronometer associated with this dial panel.
     * @param image            The image to be displayed as the background of the dial panel.
     * @param hourHandColor    The color of the hour hand.
     * @param minuteHandColor  The color of the minute hand.
     * @param secondHandColor  The color of the second hand.
     */
    DialPanel(Chronometer chronometer, Image image, Color hourHandColor, Color minuteHandColor, Color secondHandColor){
        super(chronometer);
        this.image = image;
        this.hourHandColor = hourHandColor;
        this.minuteHandColor = minuteHandColor;
        this.secondHandColor = secondHandColor;

        setLayout(new BorderLayout());

        // Create a custom JPanel to draw the image and text
        JPanel imagePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Draw the background image
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);

                // Draw the clock hands
                drawSecondHand(g);
                drawMinuteHand(g);
                drawHourHand(g);

                //  Draw the text in the center of the image
                FontMetrics fm = g.getFontMetrics();
                int stringWidth = fm.stringWidth("Chrono #" + chronometer.getId());
                int stringHeight = fm.getHeight();
                int x = (getWidth() - stringWidth) / 2;
                int y = (getHeight() + stringHeight) / 2 + 20;
                g.drawString("Chrono #" + chronometer.getId(), x, y);
            }
        };
        add(imagePanel, BorderLayout.CENTER);
    }

    // Methods to draw clock hands

    /**
     * Draws the hour hand on the dial panel.
     *
     * @param g The graphics context.
     */
    private void drawHourHand(Graphics g) {
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int handLength = Math.min(centerX, centerY) - 55;
        int handThickness = 4;

        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setStroke(new BasicStroke(handThickness));

        int x = (int) (centerX + handLength * Math.cos(Math.toRadians(hourAngle - 90)));
        int y = (int) (centerY + handLength * Math.sin(Math.toRadians(hourAngle - 90)));

        g2d.setColor(hourHandColor);
        g2d.drawLine(centerX, centerY, x, y);

        g2d.dispose();
    }

    /**
     * Draws the minute hand on the dial panel.
     *
     * @param g The graphics context.
     */
    private void drawMinuteHand(Graphics g) {
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int handLength = Math.min(centerX, centerY) - 35;
        int handThickness = 3;

        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setStroke(new BasicStroke(handThickness));

        int x = (int) (centerX + handLength * Math.cos(Math.toRadians(minuteAngle - 90)));
        int y = (int) (centerY + handLength * Math.sin(Math.toRadians(minuteAngle - 90)));

        g2d.setColor(minuteHandColor);
        g2d.drawLine(centerX, centerY, x, y);

        g2d.dispose();
    }

    /**
     * Draws the second hand on the dial panel.
     *
     * @param g The graphics context.
     */
    private void drawSecondHand(Graphics g) {
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int handLength = Math.min(centerX, centerY) - 20;
        int handThickness = 2;

        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setStroke(new BasicStroke(handThickness));

        int x = (int) (centerX + handLength * Math.cos(Math.toRadians(secondAngle - 90)));
        int y = (int) (centerY + handLength * Math.sin(Math.toRadians(secondAngle - 90)));

        g2d.setColor(secondHandColor);
        g2d.drawLine(centerX, centerY, x, y);

        g2d.dispose();
    }

    @Override
    protected void updateChronometer() {
        secondAngle = seconds() * 6;
        minuteAngle = minutes() * 6;
        hourAngle = (hours() % 12) * 30 + (minuteAngle / 12);
        repaint();
    }
}
