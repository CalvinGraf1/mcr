package org.example.BuilderFrame;

import org.example.*;
import org.example.Builder.AbstractCharacterBuilder;
import org.example.BuilderFrame.SwordBuilderFrame.SwordBuilderDialog;
import org.example.items.Armor;
import org.example.items.Helmet;
import org.example.items.Weapon;
import org.example.items.weapons.Sword;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static org.example.Utils.*;

/**
 * CharacterBuilderFrame is a graphical user interface for building characters in the game.
 * It allows users to select character types, armor, helmets, weapons, and displays the character's stats.
 *
 * @since 18/04/2024
 * @author Demont Kilian, Calvin Graf, Hutzli Boris & Sottile Alan
 * @version 3.1
 */
public class CharacterBuilderFrame extends JFrame {
    private static final int SCREEN_WIDTH = 950;
    private static final int SCREEN_HEIGHT = 800;

    private final CharacterBuilderFrame mainFrame = this;
    private AbstractCharacterBuilder builder;

    private final CharacterDisplayer displayer;
    private final CharacterStatsPanel characterStatsPanel;

    private List<Armor> currentArmorsList;
    private List<Helmet> currentHelmetsList;
    private List<Weapon> currentWeaponsList;

    private int currentBalance;
    private CharacterType currentCharacterType;
    private boolean currentChainMail;
    private Armor currentArmor;
    private Helmet currentHelmet;
    private Weapon currentWeapon;

    private final JLabel balanceLabel;
    private final SelectionRowPanel characterTypeSelection;
    private final SelectionRowPanel chainMailSelection;
    private final SelectionRowPanel armorSelection;
    private final SelectionRowPanel helmetSelection;
    private final SelectionRowPanel weaponSelection;

    private final ItemStatPanel weaponStatsPanel;
    private final ItemStatPanel helmetStatsPanel;
    private final ItemStatPanel armorStatsPanel;

    /**
     * Constructs the CharacterBuilderFrame with the given mainDisplayer (caller).
     *
     * @param mainDisplayer the main displayer for the character
     */
    public CharacterBuilderFrame(Displayer mainDisplayer) {
        // Initializing default values
        currentCharacterType = CharacterType.values()[0];
        builder = null;
        currentBalance = GameController.getInstance().getPlayerBudget();

        reinitializeItemsLists();

        setTitle("Character Builder");

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBackground(Color.WHITE);
        GridBagConstraints c2 = new GridBagConstraints();


        displayer = new CharacterDisplayer();
        displayer.setBorder(getPanelBorder("CHARACTER PREVIEW"));


        characterStatsPanel = new CharacterStatsPanel();
        characterStatsPanel.setBorder(getPanelBorder("CHARACTER STATISTICS"));

        // initialize the balance display
        JPanel balancePanel = new JPanel();
        balancePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        balancePanel.setOpaque(false);

        balanceLabel = new JLabel("Balance: " + currentBalance);
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 16));
        JLabel coinIconLabel = new JLabel(new ImageIcon(CharacterBuilderFrame.class.getClassLoader().getResource("icons/coin.png")));
        balancePanel.add(balanceLabel);
        balancePanel.add(coinIconLabel);

        // Initialise item with stats display
        JPanel itemPanel = new JPanel(new GridBagLayout());
        itemPanel.setOpaque(false);
        GridBagConstraints c1 = new GridBagConstraints();

        armorStatsPanel = new ItemStatPanel("Cost", "Protection");
        helmetStatsPanel = new ItemStatPanel("Cost", "Protection");
        weaponStatsPanel = new ItemStatPanel("Cost", "Damage");

        // Add item stat panels to itemPanel
        c1.gridx = 0;
        c1.gridy = 0;
        itemPanel.add(armorStatsPanel, c1);
        c1.gridy = 1;
        itemPanel.add(helmetStatsPanel, c1);
        c1.gridy = 2;
        itemPanel.add(weaponStatsPanel, c1);


        // Initialise the builder panel
        JPanel builderPanel = new JPanel();
        builderPanel.setLayout(new GridLayout(1, 2));
        builderPanel.setBorder(getPanelBorder("CHARACTER COMPONENTS"));
        builderPanel.setOpaque(false);

        // Item selection display
        JPanel selectionPanel = new JPanel();
        selectionPanel.setOpaque(false);
        selectionPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // Add balance panel to the top right
        c.insets = new Insets(10, 10, 10, 10);
        c.gridx = 2;
        c.gridy = 0;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.NORTHEAST;
        selectionPanel.add(balancePanel, c);
        c.gridwidth = 1;


        // Character type selection
        ActionListener leftButtonCharacterAction = e -> {
            currentCharacterType = getPreviousCharacterType();
            reinitializeItemsLists();
            updateAllSelectionsDisplay();
        };

        ActionListener rightButtonCharacterAction = e -> {
            currentCharacterType = getNextCharacterType();
            reinitializeItemsLists();
            updateAllSelectionsDisplay();
        };

        ActionListener validateCharacterAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add selected item to the character
                builder = currentCharacterType.getCharacterBuilder();
                builder.newCharacter();
                characterStatsPanel.updateStats(builder.getCharacter());
                characterTypeSelection.disableRow();
                if (currentCharacterType == CharacterType.ELF) {
                    currentChainMail = false;
                    updateChainmailDisplay();
                    chainMailSelection.disableRow();
                }
                displayer.setCharacter(builder.getCharacter());
                displayer.displayCharacter();
            }
        };

        characterTypeSelection = new SelectionRowPanel(currentCharacterType.getName(), leftButtonCharacterAction, rightButtonCharacterAction, validateCharacterAction);


        // Chain mail selection
        ActionListener chaimailRightLeftAction = e -> {
            currentChainMail = !currentChainMail;
            updateChainmailDisplay();
        };

        ActionListener validateChainmailAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (builder != null) {
                    if (currentChainMail) { // Check ici ???
                        Validation validation = builder.buildChainMail();
                        if (!validation.getState()) {
                            JOptionPane.showMessageDialog(mainFrame, validation.getErrorMessage());
                            return;
                        }
                    } else {
                        currentArmor = null;
                        updateArmorDisplay();
                        armorSelection.disableRow();
                    }
                    displayer.displayCharacter();
                    characterStatsPanel.updateStats(builder.getCharacter());
                    chainMailSelection.disableRow();
                } else {
                    JOptionPane.showMessageDialog(mainFrame, "You have to chose a class before!");
                }
            }
        };

        chainMailSelection = new SelectionRowPanel("Chain mail", chaimailRightLeftAction, chaimailRightLeftAction, validateChainmailAction);


        // Armor selection
        ActionListener leftButtonArmorAction = e -> {
            currentArmor = getPrevious(currentArmorsList, currentArmor);
            updateArmorDisplay();
        };

        ActionListener rightButtonArmorAction = e -> {
            currentArmor = getNext(currentArmorsList, currentArmor);
            updateArmorDisplay();
        };

        ActionListener validateButtonArmorAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (builder != null) {
                    if (currentBalance - currentArmor.cost() < 0) {
                        JOptionPane.showMessageDialog(mainFrame, "You don't have enough money to buy this armor!");
                        return;
                    }
                    // Add selected item to the character
                    Validation validation = builder.buildArmor(currentArmor);
                    if (!validation.getState()) {
                        JOptionPane.showMessageDialog(mainFrame, validation.getErrorMessage());
                        return;
                    }
                    updateBalance(currentArmor.cost());
                    displayer.displayCharacter();
                    characterStatsPanel.updateStats(builder.getCharacter());
                    armorSelection.disableRow();
                } else {
                    JOptionPane.showMessageDialog(mainFrame, "You have to chose a class before!");
                }
            }
        };

        armorSelection = new SelectionRowPanel(currentArmor.name(), leftButtonArmorAction, rightButtonArmorAction, validateButtonArmorAction);


        // Helmet selection
        ActionListener leftButtonHelmetAction = e -> {
            currentHelmet = getPrevious(currentHelmetsList, currentHelmet);
            updateHelmetDisplay();
        };

        ActionListener rightButtonHelmetAction = e -> {
            currentHelmet = getNext(currentHelmetsList, currentHelmet);
            updateHelmetDisplay();
        };

        ActionListener validateButtonHelmetAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (builder != null) {
                    if (currentBalance - currentHelmet.cost() < 0) {
                        JOptionPane.showMessageDialog(mainFrame, "You don't have enough money to buy this helmet!");
                        return;
                    }
                    Validation validation = builder.buildHelmet(currentHelmet);
                    if (!validation.getState()) {
                        JOptionPane.showMessageDialog(mainFrame, validation.getErrorMessage());
                        return;
                    }
                    updateBalance(currentHelmet.cost());
                    displayer.displayCharacter();
                    characterStatsPanel.updateStats(builder.getCharacter());
                    helmetSelection.updateLabel(currentHelmet.name());
                    helmetSelection.disableRow();
                } else {
                    JOptionPane.showMessageDialog(mainFrame, "You have to chose a class before!");
                }
            }
        };

        helmetSelection = new SelectionRowPanel(currentHelmet.name(), leftButtonHelmetAction, rightButtonHelmetAction, validateButtonHelmetAction);


        // Weapon selection
        ActionListener weaponLeftButtonAction = e -> {
            currentWeapon = getPrevious(currentWeaponsList, currentWeapon);
            updateWeaponDisplay();
        };

        ActionListener weaponRightButtonAction = e -> {
            currentWeapon = getNext(currentWeaponsList, currentWeapon);
            updateWeaponDisplay();
        };

        ActionListener weaponValidateButtonAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (builder != null) {
                    if (currentBalance - currentWeapon.cost() < 0) {
                        JOptionPane.showMessageDialog(mainFrame, "You don't have enough money to buy this item!");
                        return;
                    }

                    Validation validation = builder.buildWeapon(currentWeapon.clone());
                    if (!validation.getState()) {
                        JOptionPane.showMessageDialog(mainFrame, validation.getErrorMessage());
                        return;
                    }
                    updateBalance(builder.getCharacter().getWeapon().cost());
                    if (builder.getCharacter().getWeapon().isModifiable()) {
                        new SwordBuilderDialog(mainFrame, (Sword) builder.getCharacter().getWeapon(), currentBalance);
                        updateBalance(builder.getCharacter().getWeapon().cost() - ((Sword) builder.getCharacter().getWeapon()).baseCost());
                    }
                    displayer.displayCharacter();
                    characterStatsPanel.updateStats(builder.getCharacter());
                    weaponSelection.disableRow();
                } else {
                    JOptionPane.showMessageDialog(mainFrame, "You have to chose a class before!");
                }
            }
        };

        weaponSelection = new SelectionRowPanel(currentWeapon.name(), weaponLeftButtonAction, weaponRightButtonAction, weaponValidateButtonAction);

        // reset button initialization
        FitIconButton resetButton = new FitIconButton("buttons/reset.png");
        resetButton.addActionListener(actionEvent -> {
            // Reset current selections to initial state
            currentCharacterType = CharacterType.values()[0];
            builder = null;
            reinitializeItemsLists();
            currentBalance = GameController.getInstance().getPlayerBudget();

            // Update selection labels and item display
            updateAllSelectionsDisplay();
            updateBalanceDisplay();

            // Enable all buttons and labels that might have been disabled
            enableAllSelections();

            // Repaint and update the stats
            characterStatsPanel.reinitializeStats();
            displayer.clearDisplay();
        });

        // random button initialization
        FitIconButton randomCharacterButton = new FitIconButton("buttons/random.png");
        randomCharacterButton.addActionListener(actionEvent -> {
            enableAllSelections();
            displayer.clearDisplay();
            currentBalance = GameController.getInstance().getPlayerBudget();

            selectRandomCharacterType();
            reinitializeItemsLists();
            selectRandomItems();
            updateAllSelectionsDisplay();

            if (currentChainMail) {
                builder.buildChainMail();
            }
            chainMailSelection.disableRow();

            int totalCost = currentArmor.cost() + currentHelmet.cost() + currentWeapon.cost();

            if (totalCost > currentBalance) {
                if ((totalCost -= currentArmor.cost()) > currentBalance) {
                    if ((totalCost -= currentHelmet.cost()) > currentBalance) {
                        Weapon affordableWeapon = getAffordableWeapon();
                        if (affordableWeapon == null) {
                            JOptionPane.showMessageDialog(mainFrame, "You don't have enough money to buy this weapon!");
                            return;
                        } else {
                            totalCost = affordableWeapon.cost();
                            currentWeapon = affordableWeapon;
                            updateWeaponDisplay();
                        }
                    }
                } else {
                    builder.buildHelmet(currentHelmet);
                    helmetSelection.disableRow();
                }
            } else {
                if (currentChainMail || currentCharacterType == CharacterType.ELF) {
                    builder.buildArmor(currentArmor);
                } else {
                    currentArmor = null;
                    updateArmorDisplay();
                }
                armorSelection.disableRow();
                builder.buildHelmet(currentHelmet);
                helmetSelection.disableRow();
            }

            builder.buildWeapon(currentWeapon.clone());
            weaponSelection.disableRow();

            updateBalance(totalCost);
            characterStatsPanel.updateStats(builder.getCharacter());
            displayer.displayCharacter();
        });

        // build button initialization
        FitIconButton buildCharacterButton = new FitIconButton("buttons/build.png");
        buildCharacterButton.addActionListener(actionEvent -> {
            if (builder != null) {
                if (builder.getCharacter().getWeapon() == null) {
                    JOptionPane.showMessageDialog(mainFrame, "You have to chose an weapon before!");
                    return;
                }
                GameController.getInstance().setPlayerBudget(currentBalance);
                GameController.getInstance().addPlayerTeamMember(builder.getCharacter());
                mainDisplayer.enableButtons();
                mainFrame.dispose();
            } else {
                JOptionPane.showMessageDialog(mainFrame, "Hum you chose nothing to build...");
            }
        });

        // Adding selection panels to selectionPanel
        c.insets = new Insets(3, 3, 3, 3);
        c.anchor = GridBagConstraints.CENTER;
        c.gridy = 1;
        selectionPanel.add(characterTypeSelection, c);
        c.gridy = 2;
        selectionPanel.add(chainMailSelection, c);
        c.gridy = 3;
        selectionPanel.add(armorSelection, c);
        c.gridy = 4;
        selectionPanel.add(helmetSelection, c);
        c.gridy = 5;
        selectionPanel.add(weaponSelection, c);
        c.gridy = 6;
        c.gridx = 1;
        c.gridwidth = 2;
        selectionPanel.add(resetButton, c);
        c.gridy = 7;
        c.gridx = 1;
        c.gridwidth = 2;
        selectionPanel.add(randomCharacterButton, c);
        c.gridy = 8;
        c.gridx = 1;
        selectionPanel.add(buildCharacterButton, c);

        // initialize the menu display
        updateBalanceDisplay();
        updateAllSelectionsDisplay();

        builderPanel.add(itemPanel);
        builderPanel.add(selectionPanel);

        // Add all panels to the main panel
        c2.insets = new Insets(3, 3, 3, 3); // padding
        c2.fill = GridBagConstraints.BOTH;
        c2.anchor = GridBagConstraints.CENTER;
        c2.gridy = 0;
        c2.gridx = 0;
        c2.weightx = 0.6;
        c.weighty = 0.8;
        mainPanel.add(displayer, c2);
        c2.gridx = 1;
        c2.weightx = 0.2;
        mainPanel.add(characterStatsPanel, c2);
        c2.gridx = 0;
        c2.gridy = 1;
        c2.gridwidth = 2;
        mainPanel.add(builderPanel, c2);

        // add padding to the main panel
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
     * Updates the display for character type selection based on current selection.
     */
    private void updateCharacterTypeDisplay() {
        characterTypeSelection.updateLabel(currentCharacterType.getName());
    }

    /**
     * Updates the display for chainmail selection based on current selection.
     */
    private void updateChainmailDisplay() {
        chainMailSelection.updateLabel(currentChainMail ? "Chain mail" : "No chain mail");
    }

    /**
     * Updates the display for armor selection based on current selection.
     */
    private void updateArmorDisplay(){
        if (currentArmor == null) {
            armorSelection.updateLabel("Naked");
            armorStatsPanel.updatePanel(getImageIcon("Character/Body/" + currentCharacterType.getName() + "/Naked.png"), 0, 0);
        } else {
            armorSelection.updateLabel(currentArmor.name());
            armorStatsPanel.updatePanel(getImageIcon("Character/Body/" + currentCharacterType.getName() + "/" + currentArmor.pathName() + ".png"), currentArmor.cost(), currentArmor.resistance());
        }
    }

    /**
     * Updates the display for helmet selection based on current selection.
     */
    private void updateHelmetDisplay(){
        helmetSelection.updateLabel(currentHelmet.name());
        helmetStatsPanel.updatePanel(getImageIcon("Character/Head/" + currentCharacterType.getName() + "/" + currentHelmet.pathName() + ".png"), currentHelmet.cost(), currentHelmet.resistance());
    }

    /**
     * Updates the display for all selection rows based on current selections.
     */
    private void updateAllSelectionsDisplay(){
        updateCharacterTypeDisplay();
        updateChainmailDisplay();
        updateArmorDisplay();
        updateHelmetDisplay();
        updateWeaponDisplay();
    }

    /**
     * Updates the display for weapon selection based on current selection.
     */
    private void updateWeaponDisplay() {
        weaponSelection.updateLabel(currentWeapon.name());
        weaponStatsPanel.updatePanel(getImageIcon("Character/Weapon/" + currentWeapon.pathName() + ".png"), currentWeapon.cost(), currentWeapon.damage());
    }

    /**
     * Updates the balance display.
     */
    private void updateBalanceDisplay() {
        balanceLabel.setText("Balance: " + currentBalance);
    }

    /*
     * Returns the next character type in the enum.
     * @return the next character type
     */
    private CharacterType getNextCharacterType() {
        CharacterType[] types = CharacterType.values();
        int index = (currentCharacterType.ordinal() + 1) % types.length;
        return types[index];
    }

    /*
     * Returns the previous character type in the enum.
     * @return the previous character type
     */
    private CharacterType getPreviousCharacterType() {
        CharacterType[] types = CharacterType.values();
        int index = (currentCharacterType.ordinal() - 1 + types.length) % types.length;
        return types[index];
    }

    /**
     * Reinitialize the lists of available armors, helmets, and weapons for the current character type.
     */
    private void reinitializeItemsLists() {
        currentArmorsList = currentCharacterType.getCharacterArmors();
        currentHelmetsList = currentCharacterType.getCharacterHelmets();
        currentWeaponsList = currentCharacterType.getCharacterWeapons();
        currentChainMail = false;
        currentArmor = currentArmorsList.getFirst();
        currentHelmet = currentHelmetsList.getFirst();
        currentWeapon = currentWeaponsList.getFirst();
    }

    /**
     * Enables all selection rows.
     */
    private void enableAllSelections() {
        characterTypeSelection.enableRow();
        chainMailSelection.enableRow();
        armorSelection.enableRow();
        helmetSelection.enableRow();
        weaponSelection.enableRow();
    }

    /**
     * Select a random character type and initialize the builder.
     */
    private void selectRandomCharacterType() {
        currentCharacterType = CharacterType.values()[getRandom().nextInt(CharacterType.values().length)];
        builder = currentCharacterType.getCharacterBuilder();
        builder.newCharacter();
        displayer.setCharacter(builder.getCharacter());
        characterTypeSelection.disableRow();
    }

    /**
     * Select random items for the character.
     */
    private void selectRandomItems() {
        currentChainMail = (currentCharacterType != CharacterType.ELF) && getRandom().nextDouble() < 0.75;
        currentArmor = getRandomItem(currentArmorsList);
        currentHelmet = getRandomItem(currentHelmetsList);
        currentWeapon = getRandomItem(currentWeaponsList);
    }

    /**
     * Returns the first weapon in the list that is affordable with the current balance.
     *
     * @return the first affordable weapon in the list, or null if none are affordable
     */
    private Weapon getAffordableWeapon() {
        return currentWeaponsList.stream()
                .filter(weapon -> weapon.cost() <= currentBalance)
                .findFirst()
                .orElse(null);
    }

    /**
     * Updates the current balance by subtracting the specified amount and updates the balance label.
     *
     * @param cost the amount to subtract from the current balance
     */
    private void updateBalance(int cost) {
        currentBalance -= cost;
        updateBalanceDisplay();
    }
}
