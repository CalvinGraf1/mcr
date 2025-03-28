package observator;

/**
 * The Observer interface represents an object that observes changes in the subject it is attached to.
 * It defines a method to be called by the subject when a change occurs.
 *
 * @author Cavin Graf, Kilian Demont
 */
public interface Observer {
    /**
     * This method is called by the subject when a change occurs in the observed subject.
     * Implementing classes should define their specific behavior to react to the change.
     */
    void update();
}
