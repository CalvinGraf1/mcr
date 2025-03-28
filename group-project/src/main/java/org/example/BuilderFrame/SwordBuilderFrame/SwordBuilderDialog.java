package org.example.BuilderFrame.SwordBuilderFrame;

import org.example.BuilderFrame.*;
import org.example.SwordType;
import org.example.Validation;
import org.example.items.weapons.Sword;
import org.example.items.weapons.swords.Builder.AbstractSwordBuilder;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static org.example.Utils.getImageIcon;
import static org.example.Utils.getRandom;
import static org.example.Utils.getPanelBorder;

/**
 * Dialog class for building a custom sword.
 * This class provides a GUI for selecting and customizing a sword with different attributes and upgrades.
 *
 * @since 10/06/2024
 * @author Demont Kilian, Calvin Graf, Hutzli Boris & Sottile Alan
 * @version 2.1
 */
public class SwordBuilderDialog extends JDialog {
    private static final int SCREEN_WIDTH = 900;
    private static final int SCREEN_HEIGHT = 750;

    private final SwordBuilderDialog mainFrame = this;
    private AbstractSwordBuilder builder;

    private final JLabel displayer;
    private final ItemStatPanel gasolineStatsPanel;
    private final ItemStatPanel fireStatsPanel;
    private final ItemStatPanel PixieDustStatsPanel;
    private final SwordStatsPanel swordStatsPanel;

    JLabel balanceLabel;
    private final SelectionRowPanel swordTypeSelection;
    private final SelectionRowPanel gasolineSelection;
    private final SelectionRowPanel fireSelection;
    private final SelectionRowPanel PixieDustSelection;

    private int currentBalance;
    private SwordType currentSwordType;
    private boolean currentGasoline;
    private boolean currentFire;
    private boolean currentPixieDust;

    /**
     * Constructor to initialize the SwordBuilderDialog.
     *
     * @param parent  The parent JFrame of this dialog.
     * @param sword   The sword to be customized.
     * @param balance The current balance of the user.
     */
    public SwordBuilderDialog(JFrame parent, Sword sword, int balance) {
        super(parent, true);
        currentSwordType = SwordType.STANDARD;
        currentBalance = balance;

        setTitle("Sword Builder");

        // Main panel configuration
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBackground(Color.WHITE);
        GridBagConstraints c2 = new GridBagConstraints();

        // BALANCE PANEL
        JPanel balancePanel = new JPanel();
        balancePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        balancePanel.setOpaque(false);
        balancePanel.setOpaque(false);

        balanceLabel = new JLabel("Balance: " + currentBalance);
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 16));
        JLabel coinIconLabel = new JLabel(getImageIcon("icons/coin.png"));
        balancePanel.add(balanceLabel);
        balancePanel.add(coinIconLabel);

        swordStatsPanel = new SwordStatsPanel(sword);
        swordStatsPanel.setBorder(getPanelBorder("SWORD STATISTICS"));

        // Item display with stats
        JPanel itemPanel = new JPanel(new GridBagLayout());
        itemPanel.setOpaque(false);
        GridBagConstraints c1 = new GridBagConstraints();

        gasolineStatsPanel = new ItemStatPanel("Cost", "Damage boost");
        fireStatsPanel = new ItemStatPanel("Cost", "Damage boost");
        PixieDustStatsPanel = new ItemStatPanel("Cost", "Damage boost");

        displayer = new JLabel(getImageIcon("Character/Weapon/" + sword.pathName() + ".png")); // TODO : resize le bordel
        displayer.setBorder(getPanelBorder("SWORD PREVIEW"));
        displayer.setHorizontalAlignment(SwingConstants.CENTER);

        c1.gridx = 0;
        c1.gridy = 0;
        itemPanel.add(gasolineStatsPanel, c1);
        c1.gridy = 1;
        itemPanel.add(fireStatsPanel, c1);
        c1.gridy = 2;
        itemPanel.add(PixieDustStatsPanel, c1);

        initializeItemStats();

        // BUILDER PANEL
        JPanel builderPanel = new JPanel();
        builderPanel.setLayout(new GridLayout(1, 2));
        builderPanel.setBorder(getPanelBorder("SWORD COMPONENTS"));
        builderPanel.setOpaque(false);

        // Item selection display
        JPanel selectionPanel = new JPanel();
        selectionPanel.setOpaque(false);
        selectionPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // Add balance panel to the top right
        c.insets = new Insets(10, 10, 10, 10);
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.NORTHEAST;
        selectionPanel.add(balancePanel, c);

        // Sword type selection
        ActionListener swordTypeLeftButton = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentSwordType = getPreviousSwordType(currentSwordType);
                swordTypeSelection.updateLabel(currentSwordType.getName());
            }
        };

        ActionListener swordTypeRightButton = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentSwordType = getNextSwordType(currentSwordType);
                swordTypeSelection.updateLabel(currentSwordType.getName());
            }
        };

        ActionListener validateTypeButton = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add selected item to the character
                builder = currentSwordType.createBuilder(sword);
                swordStatsPanel.updateStats(builder.getSword());
                swordTypeSelection.disableRow();
                if (currentSwordType == SwordType.STANDARD) {
                    currentPixieDust = false;
                    PixieDustSelection.updateLabel("No pixie dust");
                    PixieDustStatsPanel.updatePanel(getImageIcon("Character/Weapon/specificities/noPixieDust.png"), 0, 0);
                    PixieDustSelection.disableRow();
                }
            }
        };

        swordTypeSelection = new SelectionRowPanel(currentSwordType.getName(), swordTypeLeftButton, swordTypeRightButton, validateTypeButton);

        // Gasoline selection
        ActionListener gasolineLeftRightButton = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentGasoline = !currentGasoline;
                if (currentGasoline) {
                    gasolineSelection.updateLabel("Gasoline");
                    gasolineStatsPanel.updatePanel(getImageIcon("Character/Weapon/specificities/gasoline.png"), sword.gasolineCost(), sword.gasolineBoost());
                } else {
                    gasolineSelection.updateLabel("No gasoline");
                    gasolineStatsPanel.updatePanel(getImageIcon("Character/Weapon/specificities/noGasoline.png"), 0, 0);
                }
            }
        };

        ActionListener buildGasolineToSwordButton = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (builder != null) {
                    if (currentGasoline) { // Check ici ???
                        if (currentBalance - sword.gasolineCost() < 0) {
                            JOptionPane.showMessageDialog(mainFrame, "You don't have enough money to buy this item!");
                            return;
                        }
                        Validation validation = builder.buildGasoline();
                        if (!validation.getState()) {
                            JOptionPane.showMessageDialog(mainFrame, validation.getErrorMessage());
                            return;
                        }
                        updateBalance(sword.gasolineCost());
                    } else {
                        if (currentSwordType != SwordType.MAGIC) {
                            currentFire = false;
                            fireSelection.updateLabel("No fire");
                            fireStatsPanel.updatePanel(getImageIcon("Character/Weapon/specificities/noFire.png"), 0, 0);
                            fireSelection.disableRow();
                        }
                    }
                    updatePreview();
                    gasolineSelection.disableRow();
                } else {
                    JOptionPane.showMessageDialog(mainFrame, "You have to chose a class before!");
                }
            }
        };

        gasolineSelection = new SelectionRowPanel("No gasoline", gasolineLeftRightButton, gasolineLeftRightButton, buildGasolineToSwordButton);

        // Fire selection
        ActionListener fireLeftRightButton = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentFire = !currentFire;
                if (currentFire) {
                    fireSelection.updateLabel("Fire");
                    fireStatsPanel.updatePanel(getImageIcon("Character/Weapon/specificities/fire.png"), sword.fireCost(), sword.fireBoost());
                } else {
                    fireSelection.updateLabel("No fire");
                    fireStatsPanel.updatePanel(getImageIcon("Character/Weapon/specificities/noFire.png"), 0, 0);
                }
            }
        };

        ActionListener buildFireToSwordButton = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (builder != null) {
                    if (currentFire) { // Check ici ???
                        if (currentBalance - sword.fireCost() < 0) {
                            JOptionPane.showMessageDialog(mainFrame, "You don't have enough money to fire up your sword!");
                            return;
                        }
                        Validation validation = builder.buildFire();
                        if (!validation.getState()) {
                            JOptionPane.showMessageDialog(mainFrame, validation.getErrorMessage());
                            return;
                        }
                        updateBalance(sword.fireCost());
                    }
                    updatePreview();
                    fireSelection.disableRow();
                } else {
                    JOptionPane.showMessageDialog(mainFrame, "You have to chose a class before!");
                }
            }
        };

        fireSelection = new SelectionRowPanel("No fire", fireLeftRightButton, fireLeftRightButton, buildFireToSwordButton);

        // Pixie dust selection
        ActionListener PixieDustLeftRightButton = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentPixieDust = !currentPixieDust;
                if (currentPixieDust) {
                    PixieDustSelection.updateLabel("Pixie dust");
                    PixieDustStatsPanel.updatePanel(getImageIcon("Character/Weapon/specificities/pixieDust.png"), sword.pixieDustCost(), sword.pixieDustBoost());
                } else {
                    PixieDustSelection.updateLabel("No pixie dust");
                    PixieDustStatsPanel.updatePanel(getImageIcon("Character/Weapon/specificities/noPixieDust.png"), 0, 0);
                }
            }
        };

        ActionListener buildPixieDustToSwordButton = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (builder != null) {
                    if (currentPixieDust) {
                        if (currentBalance - sword.pixieDustCost() < 0) {
                            JOptionPane.showMessageDialog(mainFrame, "You don't have enough money to fire up your sword!");
                            return;
                        }
                        Validation validation = builder.buildPixieDust();
                        if (!validation.getState()) {
                            JOptionPane.showMessageDialog(mainFrame, validation.getErrorMessage());
                            return;
                        }
                        updateBalance(sword.pixieDustCost());
                    }
                    updatePreview();
                    PixieDustSelection.disableRow();
                } else {
                    JOptionPane.showMessageDialog(mainFrame, "You have to chose a class before!");
                }
            }
        };

        PixieDustSelection = new SelectionRowPanel("No pixie dust", PixieDustLeftRightButton, PixieDustLeftRightButton, buildPixieDustToSwordButton);

        // Reset button
        FitIconButton resetButton = new FitIconButton("buttons/reset.png");
        resetButton.addActionListener(actionEvent -> {
            currentBalance = balance;
            balanceLabel.setText("Balance: " + currentBalance);
            builder = null;
            sword.reset();
            currentSwordType = SwordType.STANDARD;
            currentGasoline = false;
            currentFire = false;
            currentPixieDust = false;
            swordTypeSelection.updateLabel(currentSwordType.getName());
            swordTypeSelection.enableRow();
            gasolineSelection.updateLabel("No gasoline");
            gasolineSelection.enableRow();
            fireSelection.updateLabel("No fire");
            fireSelection.enableRow();
            PixieDustSelection.updateLabel("No pixie dust");
            PixieDustSelection.enableRow();
            initializeItemStats();
            displayer.setIcon(getImageIcon("Character/Weapon/" + sword.pathName() + ".png"));
            swordStatsPanel.updateStats(sword);
        });

        // Random sword button
        FitIconButton randomSwordButton = new FitIconButton("buttons/random.png");
        randomSwordButton.addActionListener(actionEvent -> {
            swordTypeSelection.enableRow();
            gasolineSelection.enableRow();
            fireSelection.enableRow();
            PixieDustSelection.enableRow();
            sword.reset();
            currentBalance = balance;

            if (currentBalance - sword.gasolineCost() < 0) {
                JOptionPane.showMessageDialog(mainFrame, "You are poor!");
                return;
            }

            if (getRandom().nextDouble() > 0.5) {
                currentSwordType = SwordType.MAGIC;
            } else {
                currentSwordType = SwordType.STANDARD;
            }
            builder = currentSwordType.createBuilder(sword);
            swordTypeSelection.updateLabel(currentSwordType.getName());
            swordTypeSelection.disableRow();

            if (getRandom().nextDouble() > 0.5) {
                currentGasoline = true;
                gasolineSelection.updateLabel("Gasoline");
                if (currentBalance - sword.gasolineCost() >= 0) {
                    Validation v = builder.buildGasoline();
                    if (v.getState()) {
                        updateBalance(sword.gasolineCost());
                    } else {
                        currentGasoline = false;
                    }
                    gasolineSelection.disableRow();
                }
            } else {
                currentGasoline = false;
                gasolineSelection.disableRow();
            }

            if (getRandom().nextDouble() > 0.5) {
                currentFire = true;
                fireSelection.updateLabel("Fire");
                if (currentBalance - sword.fireCost() >= 0) {
                    Validation v = builder.buildFire();
                    if (v.getState()) {
                        updateBalance(sword.fireCost());
                    } else {
                    currentFire = false;
                    }
                    fireSelection.disableRow();
                }
            } else {
                currentFire = false;
                fireSelection.disableRow();
            }


            if (getRandom().nextDouble() > 0.5 && currentSwordType == SwordType.MAGIC) {
                currentPixieDust = true;
                PixieDustSelection.updateLabel("Pixie dust");
                if (currentBalance - sword.pixieDustCost() >= 0) {
                    Validation v = builder.buildPixieDust();
                    if (v.getState()) {
                        updateBalance(sword.pixieDustCost());
                    } else {
                        currentPixieDust = false;
                    }
                    PixieDustSelection.disableRow();
                }
            } else {
                currentPixieDust = false;
                PixieDustSelection.disableRow();
            }

            if (currentGasoline) {
                gasolineSelection.updateLabel("Gasoline");
                gasolineStatsPanel.updatePanel(getImageIcon("Character/Weapon/specificities/gasoline.png"), sword.gasolineCost(), sword.gasolineBoost());
            } else {
                gasolineSelection.updateLabel("No gasoline");
                gasolineStatsPanel.updatePanel(getImageIcon("Character/Weapon/specificities/noGasoline.png"), 0, 0);
            }

            if (currentFire) {
                fireSelection.updateLabel("Fire");
                fireStatsPanel.updatePanel(getImageIcon("Character/Weapon/specificities/fire.png"), sword.fireCost(), sword.fireBoost());
            } else {
                fireSelection.updateLabel("No fire");
                fireStatsPanel.updatePanel(getImageIcon("Character/Weapon/specificities/noFire.png"), 0, 0);
            }

            if (currentPixieDust) {
                PixieDustSelection.updateLabel("Pixie dust");
                PixieDustStatsPanel.updatePanel(getImageIcon("Character/Weapon/specificities/pixieDust.png"), sword.pixieDustCost(), sword.pixieDustBoost());
            } else {
                PixieDustSelection.updateLabel("No pixie dust");
                PixieDustStatsPanel.updatePanel(getImageIcon("Character/Weapon/specificities/noPixieDust.png"), 0, 0);
            }

            swordStatsPanel.updateStats(builder.getSword());
            updatePreview();
        });

        // Build button
        FitIconButton buildSwordButton = new FitIconButton("buttons/build.png");
        buildSwordButton.addActionListener(actionEvent -> {
            if (builder != null) {
                mainFrame.dispose();
            } else {
                JOptionPane.showMessageDialog(mainFrame, "Hum you chose nothing to build...");
            }
        });

        c.insets = new Insets(3, 3, 3, 3); // padding
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 0.2;
        selectionPanel.add(swordTypeSelection, c);
        c.gridy = 2;
        c.weightx = 0.6;
        selectionPanel.add(gasolineSelection, c);
        c.gridy = 3;
        c.weightx = 0.2;
        selectionPanel.add(fireSelection, c);
        c.gridy = 4;
        c.weightx = 0.1;
        selectionPanel.add(PixieDustSelection, c);
        c.gridy = 5;
        selectionPanel.add(resetButton, c);
        c.gridy = 6;
        selectionPanel.add(randomSwordButton, c);
        c.gridy = 7;
        selectionPanel.add(buildSwordButton, c);

        builderPanel.add(itemPanel);
        builderPanel.add(selectionPanel);

        c2.insets = new Insets(3, 3, 3, 3);
        c2.fill = GridBagConstraints.BOTH;
        c2.anchor = GridBagConstraints.CENTER;

        // Displayer panel
        c2.gridx = 0;
        c2.gridy = 0;
        c2.gridwidth = 1;
        c2.weightx = 0.5;
        c2.weighty = 0.5;
        mainPanel.add(displayer, c2);

        // Stats panel
        c2.gridx = 1;
        c2.gridy = 0;
        c2.gridwidth = 1;
        mainPanel.add(swordStatsPanel, c2);

        // Item panel
        c2.gridx = 0;
        c2.gridy = 1;
        c2.gridwidth = 2;
        mainPanel.add(builderPanel, c2);

        // Add padding to the main panel
        Border padding = BorderFactory.createEmptyBorder(20, 20, 20, 20);
        mainPanel.setBorder(padding);
        add(mainPanel);

        // Resizing frame
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Update the balance of the user.
     *
     * @param cost The cost to be subtracted from the balance.
     */
    private void updateBalance(int cost) {
        currentBalance -= cost;
        balanceLabel.setText("Balance: " + currentBalance);
    }

    /**
     * Get the next sword type.
     *
     * @param currentType The current sword type.
     * @return The next sword type.
     */
    private SwordType getNextSwordType(SwordType currentType) {
        SwordType[] types = SwordType.values();
        int index = (currentType.ordinal() + 1) % types.length;
        return types[index];
    }

    /**
     * Get the previous sword type.
     *
     * @param currentType The current sword type.
     * @return The previous sword type.
     */
    private SwordType getPreviousSwordType(SwordType currentType) {
        SwordType[] types = SwordType.values();
        int index = (currentType.ordinal() - 1 + types.length) % types.length;
        return types[index];
    }

    /**
     * Update the preview image of the sword.
     */
    private void updatePreview() {
        swordStatsPanel.updateStats(builder.getSword());
        if (builder.getSword().hasPixieDust())
            displayer.setIcon(getImageIcon("Character/Weapon/magicSword.png"));
        else if (builder.getSword().hasFire())
            displayer.setIcon(getImageIcon("Character/Weapon/fireSword.png"));
        else if (builder.getSword().hasGasoline())
            displayer.setIcon(getImageIcon("Character/Weapon/gasolineSword.png"));
        else
            displayer.setIcon(getImageIcon("Character/Weapon/" + builder.getSword().pathName() + ".png"));
    }

    /**
     * Initialize item statistics.
     * Updates the gasoline, fire, and Pixie dust stats based on the sword.
     */
    private void initializeItemStats() {
        gasolineStatsPanel.updatePanel(getImageIcon("Character/Weapon/specificities/noGasoline.png"), 0, 0);
        fireStatsPanel.updatePanel(getImageIcon("Character/Weapon/specificities/noFire.png"), 0, 0);
        PixieDustStatsPanel.updatePanel(getImageIcon("Character/Weapon/specificities/noPixieDust.png"), 0, 0);
    }
}
