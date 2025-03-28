package org.example.BuilderFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * SelectionRowPanel is a JPanel that represents a row of controls including
 * a left arrow button, a label, a right arrow button, and a validate button.
 * Each button can have an ActionListener assigned for interaction.
 *
 *  @since 08/05/2024
 *  @Author Demont Kilian, Calvin Graf, Hutzli Boris & Sottile Alan
 *  @version 1.3
 */
public class SelectionRowPanel extends JPanel {
    private IconButton leftButton;
    private JLabel label;
    private IconButton rightButton;
    private IconButton validateButton;

    /**
     * Constructs a SelectionRowPanel with the specified label and ActionListeners
     * for each button.
     *
     * @param labelValue            The initial text value of the label.
     * @param leftButtonListener    ActionListener for the left arrow button.
     * @param rightButtonListener   ActionListener for the right arrow button.
     * @param validateButtonListener ActionListener for the validate button.
     */
    public SelectionRowPanel(String labelValue, ActionListener leftButtonListener, ActionListener rightButtonListener, ActionListener validateButtonListener) {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        this.setOpaque(false);

        c.insets = new Insets(3, 3, 3, 3); // padding
        c.anchor = GridBagConstraints.CENTER;

        leftButton = new IconButton("buttons/left-arrow.png");
        leftButton.addActionListener(leftButtonListener);
        c.gridx = 0;
        c.weightx = 0.2;
        this.add(leftButton, c);

        label = new JLabel(labelValue);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        Dimension labelSize = new Dimension(200, label.getPreferredSize().height); // Adjust the width as needed
        label.setPreferredSize(labelSize);
        label.setMinimumSize(labelSize);
        c.gridx = 1;
        c.weightx = 0.6;
        this.add(label, c);

        rightButton = new IconButton("buttons/right-arrow.png");
        rightButton.addActionListener(rightButtonListener);
        c.gridx = 2;
        c.weightx = 0.2;
        this.add(rightButton, c);

        validateButton = new IconButton("buttons/check-mark-icon.png", 0.3);
        validateButton.addActionListener(validateButtonListener);
        c.gridx = 3;
        c.weightx = 0.1;
        this.add(validateButton, c);
    }

    /**
     * Updates the text value of the label.
     *
     * @param labelValue The new text value of the label.
     */
    public void updateLabel(String labelValue) {
        label.setText(labelValue);
    }

    /**
     * Disables all components in the row panel.
     */
    public void disableRow() {
        leftButton.setEnabled(false);
        rightButton.setEnabled(false);
        validateButton.setEnabled(false);
        label.setEnabled(false);
    }

    /**
     * Enables all components in the row panel.
     */
    public void enableRow() {
        leftButton.setEnabled(true);
        rightButton.setEnabled(true);
        validateButton.setEnabled(true);
        label.setEnabled(true);
    }
}
