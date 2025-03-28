package org.example;


/**
 * Represents an arrow item entity in the game
 *
 * @author Demont Kilian
 * @author Graf Calvin
 * @author Hutzli Boris
 * @author Sottile Alan
 * @version 1.0
 */
public class Arrow extends ItemEntity{

    /**
     * Constructor for the Arrow class
     * @param p position of the arrow
     * @param s side of the arrow
     */
    public Arrow(Vector p, Side s){
        this.position = p;
        this.side = s;
    }

    @Override
    public String getModelPath() {
        return "src/main/resources/arrow.png";
    }
}
