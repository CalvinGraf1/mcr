package org.example;

import org.example.Character.Character;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a team of characters.
 *
 * @author Demont Kilian
 * @author Graf Calvin
 * @author Hutzli Boris
 * @author Sottile Alan
 * @version 1.0
 */
public class Team {
    /** represents a list of all the team members*/
    private List<Character> members = new ArrayList<>();

    /** represents the name of the team*/
    private String name;

    /** represents which side the team is playing*/
    private Side side;


    /**
     * Constructor for the Team class.
     * @param name the name of the team
     * @param side the side of the team
     */
    public Team(String name, Side side) {
        this.name = name;
        this.side = side;
    }

    /**
     * Returns the name of the team.
     * @return the name of the team
     */
    public String getName() {
        return name;
    }

    /**
     * Return a list of all the members of the team.
     * @return a list of all the members of the team
     */
    public List<Character> getMembers() {
        return members;
    }

    /**
     * Clears the team of all its members.
     */
    public void clear() {
        members.clear();
    }

    /**
     * Returns whether the team is empty.
     * @return true if the team is empty, false otherwise
     */
    public boolean isEmpty() {
        return members.isEmpty();
    }

    /**
     * Removes the given member from the team.
     * @param member the member to remove
     */
    public void removeMember(Character member) {
        members.remove(member);
    }

    /**
     * Adds a member to the team.
     * @param member the member to add
     */
    public void addMember(Character member) {
        member.generateInitialPos(side);
        members.add(member);
    }
}
