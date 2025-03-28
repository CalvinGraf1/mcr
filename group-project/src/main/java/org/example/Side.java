package org.example;


/**
 * The Side enum represents the two sides in the game
 * with predefined positions for elves, humans, and orcs.
 *
 * @author Demont Kilian
 * @author Graf Calvin
 * @author Hutzli Boris
 * @author Sottile Alan
 * @version 1.0
 */
public enum Side {
    LEFT(0, Constants.PANEL_WIDTH / 6, Constants.PANEL_WIDTH / 6, Constants.PANEL_WIDTH / 3, Constants.PANEL_WIDTH / 3, Constants.PANEL_WIDTH / 2),
    RIGHT((5 * Constants.PANEL_WIDTH) / 6, Constants.PANEL_WIDTH - Constants.CHAR_BODY_WIDTH, (2 * Constants.PANEL_WIDTH) / 3, (5 * Constants.PANEL_WIDTH) / 6, Constants.PANEL_WIDTH / 2, (2 * Constants.PANEL_WIDTH) / 3);

    /**
     * The beginning position for elves.
     */
    private final int elfBeg;

    /**
     * The ending position for elves.
     */
    private final int elfEnd;

    /**
     * The beginning position for humans.
     */
    private final int humanBeg;

    /**
     * The ending position for humans.
     */
    private final int humanEnd;

    /**
     * The beginning position for orcs.
     */
    private final int orcBeg;

    /**
     * The ending position for orcs.
     */
    private final int orcEnd;

    /**
     * Constructs a new Side with the specified positions for elves, humans, and orcs.
     *
     * @param elfBeg    the beginning position for elves
     * @param elfEnd    the ending position for elves
     * @param humanBeg  the beginning position for humans
     * @param humanEnd  the ending position for humans
     * @param orcBeg    the beginning position for orcs
     * @param orcEnd    the ending position for orcs
     */
    Side(int elfBeg, int elfEnd, int humanBeg, int humanEnd, int orcBeg, int orcEnd) {
        this.elfBeg = elfBeg;
        this.elfEnd = elfEnd;
        this.humanBeg = humanBeg;
        this.humanEnd = humanEnd;
        this.orcBeg = orcBeg;
        this.orcEnd = orcEnd;
    }

    /**
     * Returns the beginning position for elves.
     *
     * @return the beginning position for elves
     */
    public int getElfBeg() {
        return elfBeg;
    }

    /**
     * Returns the ending position for elves.
     *
     * @return the ending position for elves
     */
    public int getElfEnd() {
        return elfEnd;
    }

    /**
     * Returns the beginning position for humans.
     *
     * @return the beginning position for humans
     */
    public int getHumanBeg() {
        return humanBeg;
    }

    /**
     * Returns the ending position for humans.
     *
     * @return the ending position for humans
     */
    public int getHumanEnd() {
        return humanEnd;
    }

    /**
     * Returns the beginning position for orcs.
     *
     * @return the beginning position for orcs
     */
    public int getOrcBeg() {
        return orcBeg;
    }

    /**
     * Returns the ending position for orcs.
     *
     * @return the ending position for orcs
     */
    public int getOrcEnd() {
        return orcEnd;
    }
}
