package org.example;

import org.example.Character.Character;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * This class represents a Battle between teams.
 *
 * @author Demont Kilian
 * @author Graf Calvin
 * @author Hutzli Boris
 * @author Sottile Alan
 * @version 1.0
 */
public class Battle {

    /** List of teams participating in the battle */
    private final List<Team> teams = new LinkedList<>();

    /** Used for choosing random characters */
    private final Random rand = new Random();

    /** Team turn */
    private int turn = 0;

    /** Displayer to display on */
    private final Displayer displayer;

    /**
     * Constructor for the Battle class.
     * @param teams the teams participating in the battle
     * @param displayer the displayer to use
     */
    public Battle(Team[] teams, Displayer displayer) {
        this.teams.addAll(List.of(teams));
        this.displayer = displayer;
    }

    /**
     * Returns a list of all characters excluding the members of the given team.
     * @param toExclude the team to exclude
     * @return a list of all characters excluding the members of the given team
     */
    private List<Character> charactersExcludingTeam(Team toExclude) {
        return teams.stream()
                .filter(team -> team != toExclude)
                .flatMap(team -> team.getMembers().stream())
                .collect(Collectors.toList());
    }

    /**
     * Returns a random character from the given list.
     * @param characters the list of characters
     * @return a random character from the list
     */
    private Character randomCharacter(List<Character> characters) {
        return characters.get(rand.nextInt(characters.size()));
    }

    /**
     * Returns the team of the given character.
     * @param character the character
     * @return the team of the character
     */
    private Team findTeamOfCharacter(Character character) {
        return teams.stream()
                .filter(team -> team.getMembers().contains(character))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Character not found in any team"));
    }

    /**
     * Changes the turn to the next team.
     */
    private void changeTurn() {
        turn = (turn + 1) % teams.size();
    }

    /**
     * Manage the next assault of a team.
     */
    public void nextAssault() {
        Character attacker = randomCharacter(teams.get(turn).getMembers());
        Character defender = randomCharacter(charactersExcludingTeam(teams.get(turn)));

        Vector initial = new Vector(attacker.getPosition());
        if(!attacker.getWeapon().isDistance()) {
            displayer.displayMoveCharacter(attacker, defender.getPosition(), Constants.WALK_SPEED, 100);
            attacker.attack(defender);
            displayer.displayMeleeAttack(attacker.getWeapon());
            displayer.displayMoveCharacter(attacker, initial, Constants.WALK_SPEED, 0);
        } else {
            attacker.attack(defender);
            displayer.displayBowAttack(attacker, defender, Constants.ARROW_SPEED);
        }

        if (!defender.isAlive()) {
            findTeamOfCharacter(defender).removeMember(defender);
            displayer.displayDeath(defender);
        }

        changeTurn();
    }

    /**
     * Returns the team that has no members.
     * @return the team that has no members
     */
    public Team getEmptyTeam() {
        return teams.stream().filter(Team::isEmpty).findFirst().orElse(null);
    }
}
