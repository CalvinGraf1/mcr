package org.example;

import org.example.Character.Character;
import org.example.items.weapons.Bow;
import org.example.items.Weapon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import org.example.BuilderFrame.CharacterBuilderFrame;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.util.concurrent.CountDownLatch;

/**
 * Panel that displays the background image
 *
 * @author Demont Kilian
 * @author Graf Calvin
 * @author Hutzli Boris
 * @author Sottile Alan
 * @version 1.0
 */
class BackgroundPanel extends JPanel {
    /** background image */
    private final Image backgroundImage;

    /**
     * Constructs a BackgroundPanel with the specified image path
     *
     * @param imagePath the path of the background image
     */
    public BackgroundPanel(String imagePath) {
        backgroundImage = new ImageIcon(imagePath).getImage();
        setLayout(null);
    }

    /**
     * Paints the background image on the panel
     *
     * @param g the Graphics object
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}

/**
 * The main class responsible for displaying the game interface and handling events
 */
public class Displayer {
    /** background panel */
    private BackgroundPanel panel;

    /** The two teams displayed */
    private Team team1, team2;

    /** button to start the simulation */
    private JButton startButton;

    /** button to access to the character builder */
    private JButton buildTeamButton;

    /** frame to build a character */
    private CharacterBuilderFrame builderChar;

    /** class that specializes in displaying entity  */
    private EntityDisplay entityDisplay;

    /**
     * Sets the first team.
     *
     * @param team the first team
     */
    public void setFirstTeam(Team team) {
        team1 = team;
    }

    /**
     * Sets the second team.
     *
     * @param team the second team
     */
    public void setSecondTeam(Team team) {
        team2 = team;
    }

    /**
     * Initializes and displays the main game interface.
     *
     * @param firstTeam  the first team
     * @param secondTeam the second team
     */
    public void startDisplay(Team firstTeam, Team secondTeam) {
        setFirstTeam(firstTeam);
        setSecondTeam(secondTeam);

        JFrame frame = new JFrame();
        panel = new BackgroundPanel(Constants.BACKGROUND_IMAGE_PATH);
        panel.setPreferredSize(new Dimension(Constants.PANEL_WIDTH, Constants.PANEL_HEIGHT));
        panel.setLayout(null);

        this.entityDisplay = EntityDisplay.getInstance();
        this.entityDisplay.setPanel(panel);

        redraw();
        addBuilderButton();
        addStartBattleButton();

        frame.getContentPane().add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Displays the character builder panel
     */
    private void displayCharacterBuilderPanel() {
        if (builderChar == null || !builderChar.isShowing()) {
            disableButtons();
            builderChar = new CharacterBuilderFrame(this);
            builderChar.setLocationRelativeTo(null);
            builderChar.setVisible(true);

            builderChar.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                    enableButtons();
                }
            });
        }
    }

    /**
     * Adds the team building button
     */
    public void addBuilderButton() {
        JButton button = new JButton("Build your team");
        button.setBounds(Constants.PANEL_WIDTH - Constants.DISTANCE_BUILDER_BUTTON_FROM_RIGHT,
                Constants.DISTANCE_BUTTON_FROM_TOP, Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT);
        button.addActionListener(e -> displayCharacterBuilderPanel());
        panel.add(button);
        buildTeamButton = button;
    }

    /**
     * Adds the start button
     */
    public void addStartBattleButton() {
        JButton button = new JButton("Start battle");
        button.setBounds(Constants.PANEL_WIDTH - Constants.DISTANCE_ADD_BUTTON_FROM_RIGHT,
                Constants.DISTANCE_BUTTON_FROM_TOP, Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT);
        button.addActionListener(e -> {
            if (team1 == null || team1.isEmpty() || team2 == null || team2.isEmpty()) {
                JOptionPane.showMessageDialog(panel, "Can't start battle without a team.");
                return;
            }

            if (GameController.getInstance().battleRunning()) {
                JOptionPane.showMessageDialog(panel, "Battle already started.");
                return;
            }

            disableButtons();

            SwingWorker<Void, Void> battleWorker = new SwingWorker<>() {
                private Team loserTeam;

                @Override
                protected Void doInBackground() {
                    loserTeam = GameController.getInstance().startBattle();
                    return null;
                }

                @Override
                protected void done() {
                    entityDisplay.clearArmy(team1);
                    entityDisplay.clearArmy(team2);
                    team1.clear();
                    team2.clear();
                    String message = loserTeam == team1 ? "You lost." : "You won!";
                    JOptionPane.showMessageDialog(panel, "The battle is over! " + message);
                    GameController.getInstance().generateEnemyTeam();
                    redraw();
                    enableButtons();
                }
            };
            battleWorker.execute();
        });
        panel.add(button);
        startButton = button;
    }

    /**
     * Utility class for image operations.
     */
    public static class ImageUtils {

        /**
         * Flips the given image horizontally.
         *
         * @param image the image to flip
         * @return the flipped image
         */
        public static BufferedImage flipImageHorizontally(BufferedImage image) {
            AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
            tx.translate(-image.getWidth(), 0);
            AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
            return op.filter(image, null);
        }

        /**
         * Converts an ImageIcon to a BufferedImage.
         *
         * @param icon the ImageIcon to convert
         * @return the resulting BufferedImage
         */
        public static BufferedImage toBufferedImage(ImageIcon icon) {
            BufferedImage bufferedImage = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = bufferedImage.createGraphics();
            icon.paintIcon(null, g2d, 0, 0);
            g2d.dispose();
            return bufferedImage;
        }
    }

    /**
     * Redraws the panel, clearing and displaying both teams
     */
    public void redraw() {
        entityDisplay.clearArmy(team1);
        entityDisplay.clearArmy(team2);
        entityDisplay.displayArmy(team1);
        entityDisplay.displayArmy(team2);
    }

    /**
     * Moves an entity towards a destination at a specified speed.
     *
     * @param entity      the entity to move
     * @param destination the destination to move towards
     * @param speed       the speed of movement
     */
    public void moveEntity(Entity entity, Vector destination, int speed) {
        int deltaX = destination.getPosX() - entity.getPosX();
        int deltaY = destination.getPosY() - entity.getPosY();

        int moveX = (int) Math.signum(deltaX) * Math.min(speed, Math.abs(deltaX));
        int moveY = (int) Math.signum(deltaY) * Math.min(speed, Math.abs(deltaY));

        entity.setPosX(entity.getPosX() + moveX);
        entity.setPosY(entity.getPosY() + moveY);
    }

    /**
     * moves a character towards a destination
     *
     * @param character               the character to move
     * @param destination             the destination
     * @param speed                   the speed of movement
     * @param distanceToTargetVector  the distance within which the character stops moving
     */
    public void displayMoveCharacter(Character character, Vector destination, int speed, int distanceToTargetVector) {
        CountDownLatch latch = new CountDownLatch(1);

        Timer moveTimer = new Timer(0, e -> {
            moveEntity(character, destination, speed);

            redraw();

            if (Math.abs(character.getPosX() - destination.getPosX()) <= distanceToTargetVector
                    && Math.abs(character.getPosY() - destination.getPosY()) <= distanceToTargetVector) {
                ((Timer) e.getSource()).stop();
                latch.countDown();
            }
        });
        moveTimer.start();

        try {
            latch.await();
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Animates a melee attack with a weapon.
     *
     * @param weapon the weapon that does the animation
     */
    public void displayMeleeAttack(Weapon weapon) {
        CountDownLatch latch = new CountDownLatch(1);

        Timer moveTimer = new Timer(10, new ActionListener() {

            int elapsedTime = 0;
            int direction = 1;

            @Override
            public void actionPerformed(ActionEvent e) {
                weapon.getPosition().setPosX(weapon.getPosition().getPosX() + direction * Constants.MELEE_WEAPON_HIT_DISTANCE);

                redraw();

                elapsedTime += 30;
                direction *= -1;

                if (elapsedTime >= Constants.MELEE_WEAPON_ANIMATION_TIME) {
                    ((Timer) e.getSource()).stop();
                    latch.countDown();
                }
            }
        });
        moveTimer.start();

        try {
            latch.await();
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Animates an arrow moving from one character to another.
     *
     * @param c1     the character attacking
     * @param c2     the target character
     * @param speed  the speed of the animation
     */
    public void displayBowAttack(Character c1, Character c2, int speed) {
        CountDownLatch latch = new CountDownLatch(1);

        if (!c1.getWeapon().isDistance())
            throw new RuntimeException("Distance attack from melee weapon");

        Bow bow = (Bow) c1.getWeapon();

        bow.setArrow(new Arrow(new Vector(bow.getPosition().getPosX(), bow.getPosition().getPosY()), c1.getSide()));

        ImageIcon arrowIcon = new ImageIcon(bow.getArrow().getModelPath());
        JLabel arrowLabel = entityDisplay.createLabel(bow.getArrow(), arrowIcon, bow.getArrow().getPosX(), bow.getArrow().getPosY());
        panel.add(arrowLabel);
        panel.repaint();

        Timer moveTimer = new Timer(30, e -> {
            moveEntity(bow.getArrow(), c2.getPosition(), speed);

            arrowLabel.setLocation(bow.getArrow().getPosX(), bow.getArrow().getPosY());

            panel.repaint();

            if (Math.abs(bow.getArrow().getPosX() - c2.getPosition().getPosX()) <= speed
                    && Math.abs(bow.getArrow().getPosY() - c2.getPosition().getPosY()) <= speed) {
                ((Timer) e.getSource()).stop();
                latch.countDown();
                panel.remove(arrowLabel);
                panel.repaint();
            }
        });
        moveTimer.start();
        try {
            latch.await();
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        entityDisplay.removeCharacter(c2);
        entityDisplay.displayCharacter(c2);
    }

    /**
     * Enables the buttons on the panel.
     */
    public void enableButtons() {
        buildTeamButton.setEnabled(true);
        startButton.setEnabled(true);
    }

    /**
     * Disables the buttons on the panel.
     */
    public void disableButtons() {
        buildTeamButton.setEnabled(false);
        startButton.setEnabled(false);
    }

    /**
     * Displays the death of a character.
     *
     * @param c the character that died
     */
    public void displayDeath(Character c) {
        entityDisplay.removeCharacter(c);
    }
}
