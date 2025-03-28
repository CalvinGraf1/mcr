package org.example;

import org.example.Builder.AbstractCharacterBuilder;
import org.example.items.Armor;
import org.example.items.Helmet;
import org.example.items.Weapon;
import org.example.Character.Character;
import java.util.List;

/**
 * Singleton managing the game and the interactions between the player and the enemy.
 *
 * @author Demont Kilian
 * @author Graf Calvin
 * @author Hutzli Boris
 * @author Sottile Alan
 * @version 1.0
 */
public class GameController {

    /** Initial player budget value */
    public final static int TEAM_BUDGET = 3000;

    /** Player team */
    private final Team player = new Team("Player team", Side.LEFT);

    /** Enemy team */
    private final Team enemy = new Team("Enemy team", Side.RIGHT);

    /** Determines whether a battle is currently taking place */
    private Boolean battleRunning = false;

    /** Displayer */
    private final Displayer displayer = new Displayer();

    /** Player budget */
    private int playerBudget = TEAM_BUDGET;

    /** Current GameController instance */
    private static GameController instance = null;

    /**
     * Get the singleton instance of the game controller.
     * @return the singleton instance of GameController
     */
    public static GameController getInstance() {
        if (instance == null) {
            instance = new GameController();
        }
        return instance;
    }

    /**
     * Private constructor to enforce singleton pattern.
     */
    private GameController() {}

    /**
     * Run the game.
     */
    public void run() {
        generateEnemyTeam();
        displayer.startDisplay(player, enemy);
    }

    /**
     * Get the budget of the player.
     * @return the budget of the player
     */
    public int getPlayerBudget() {
        return playerBudget;
    }

    /**
     * Set the budget of the player.
     * @param value the new budget of the player
     */
    public void setPlayerBudget(int value) {
        playerBudget = value;
    }

    /**
     * Determines if a battle is taking place
     * @return true if a battle is taking place
     */
    public Boolean battleRunning() {
        return battleRunning;
    }

    /**
     * Start a battle between the player's team and the enemy's team.
     * @return the team that lost the battle
     */
    public Team startBattle() {
        Battle battle = new Battle(new Team[]{player, enemy}, displayer);
        battleRunning = true;
        Team loser = null;

        while (loser == null) {
            battle.nextAssault();
            loser = battle.getEmptyTeam();
        }
        setPlayerBudget(TEAM_BUDGET);
        battleRunning = false;
        return loser;
    }

    /**
     * Add a member to the player's team.
     * @param character the character to add
     */
    public void addPlayerTeamMember(Character character) {
        player.addMember(character);
        displayer.redraw();
    }

    /**
     * Main method of the game.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GameController.getInstance().run();
    }

    /**
     * Generates the enemy team.
     */
    public void generateEnemyTeam() {
        for (int i = 0; i < 4; ++i) {
            enemy.addMember(generateRandomCharacter());
        }
    }

    /**
     * Generate a random character.
     * @return the generated character
     */
    private Character generateRandomCharacter() {
        CharacterType[] characterTypes = CharacterType.values();
        CharacterType currentCharacterType = characterTypes[(int) (Math.random() * characterTypes.length)];

        List<Armor> currentArmorsList = currentCharacterType.getCharacterArmors();
        List<Helmet> currentHelmetsList = currentCharacterType.getCharacterHelmets();
        List<Weapon> currentWeaponsList = currentCharacterType.getCharacterWeapons();

        boolean currentChainMail;
        Armor currentArmor;
        Helmet currentHelmet;
        Weapon currentWeapon;

        AbstractCharacterBuilder builder = currentCharacterType.getCharacterBuilder();
        builder.newCharacter();

        if (currentCharacterType != CharacterType.ELF) {
            currentChainMail = Math.random() < 0.75;
        } else {
            currentChainMail = false;
        }

        currentArmor = currentArmorsList.get((int) (Math.random() * currentArmorsList.size()));
        currentHelmet = currentHelmetsList.get((int) (Math.random() * currentHelmetsList.size()));
        currentWeapon = currentWeaponsList.get((int) (Math.random() * currentWeaponsList.size()));

        if (currentChainMail){
            builder.buildChainMail();
        } else if (currentCharacterType == CharacterType.ELF) {
            currentArmor = null;
        }

        if (currentChainMail || currentCharacterType == CharacterType.ELF) {
            builder.buildArmor(currentArmor);
        }

        builder.buildHelmet(currentHelmet);
        builder.buildWeapon(currentWeapon);
        return builder.getCharacter();
    }
}
