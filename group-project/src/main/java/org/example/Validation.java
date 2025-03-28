package org.example;

/**
 * This class represents a validation object that can be either valid or invalid.
 * If the validation is invalid, an error message is provided.
 *
 * @author Demont Kilian
 * @author Graf Calvin
 * @author Hutzli Boris
 * @author Sottile Alan
 * @version 1.0
 */
public class Validation {
    private boolean state;

    private String error;

    /**
     * Constructor for the Validation class.
     * @param state the state of the validation
     * @param error the error message
     */
    private Validation(boolean state, String error) {
        this.state = state;
        this.error = error;
    }

    /**
     * Returns a new valid validation object.
     * @return a new valid validation object
     */
    public static Validation valid(){
        return new Validation(true, "");
    }

    /**
     * Returns a new invalid validation object with the given error message.
     * @param error the error message
     * @return a new invalid validation object
     */
    public static Validation invalid(String error){
        return new Validation(false, error);
    }

    /**
     * Returns whether the validation is valid.
     * @return true if the validation is valid, false otherwise
     */
    public boolean getState(){
        return state;
    }

    /**
     * Returns the error message of the validation.
     * @return the error message of the validation
     */
    public String getErrorMessage() {
        return error;
    }
}
