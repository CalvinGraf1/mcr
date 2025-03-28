package observator;

/**
 * The Subject interface represents an object being observed by one or more observers.
 * It defines methods for managing observers and notifying them of changes.
 *
 * @author Cavin Graf, Kilian Demont
 */
public interface Subject {

    /**
     * Adds an observer to the list of observers.
     *
     * @param o the observer to be added
     */
    void addObserver(Observer o);

    /**
     * Deletes an observer from the list of observers.
     *
     * @param o the observer to be removed
     */
    void deleteObserver(Observer o);

    /**
     * Deletes all observers from the list of observers.
     */
    void deleteObservers();

    /**
     * Marks the subject as changed, indicating that its state has changed.
     */
    void setChanged();

    /**
     * Clears the changed status of the subject, indicating that no change has occurred.
     */
    void clearChanged();

    /**
     * Checks if the subject's state has changed.
     *
     * @return true if the subject's state has changed, false otherwise
     */
    boolean hasChanged();

    /**
     * Notifies all observers that the subject's state has changed.
     */
    void notifyObservers();
}
