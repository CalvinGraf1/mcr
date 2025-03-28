package model;

import observator.Observer;
import observator.Subject;

import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The Chronometer class represents a timer that measures time in seconds.
 * It can be started, stopped, and reset. It also supports observing changes in its state.
 *
 * @author Cavin Graf, Kilian Demont
 */
public class Chronometer implements Subject {
    private final Timer timer = new Timer();
    private int seconds;
    private TimerTask currentTask;
    private boolean isRunning;
    private boolean hasChanged = false;
    private final LinkedList<Observer> observers = new LinkedList<>();
    private int id;
    private static int lastId;

    /**
     * Constructs a new Chronometer with initial state.
     */
    public Chronometer() {
        this.seconds = 0;
        this.isRunning = false;
        this.id = ++lastId;
    }

    /**
     * Gets the current elapsed time in seconds.
     *
     * @return The current elapsed time in seconds.
     */
    public int getSeconds() {
        return seconds;
    }

    /**
     * Sets the current elapsed time in seconds.
     *
     * @param seconds The new elapsed time in seconds.
     */
    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    /**
     * Checks if the chronometer is running.
     *
     * @return True if the chronometer is running, false otherwise.
     */
    public boolean getIsRunning() {
        return this.isRunning;
    }

    /**
     * Sets the running state of the chronometer.
     *
     * @param isRunning True to start the chronometer, false to stop it.
     */
    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    /**
     * Gets the unique identifier of the chronometer.
     *
     * @return The identifier of the chronometer.
     */
    public int getId() {
        return id;
    }

    /**
     * Starts the chronometer.
     * If the chronometer is already running, this method has no effect.
     */
    public void start() {

        if (getIsRunning()) return;

        setIsRunning(true);
        currentTask = new TimerTask() {
            @Override
            public void run() {
                setSeconds(getSeconds() + 1);
                setChanged();
                notifyObservers();
            }
        };
        timer.scheduleAtFixedRate(currentTask, 1000, 1000);
    }

    /**
     * Stops the chronometer.
     * If the chronometer is not running, this method has no effect.
     */
    public void stop() {
        if (getIsRunning()) {
            currentTask.cancel();
            setIsRunning(false);
        }
    }

    /**
     * Resets the chronometer to zero.
     * Notifies the observers after resetting.
     */
    public void reset() {
        setSeconds(0);
        setChanged(); // Marquer que l'état du Chronometer a changé
        notifyObservers(); // Informer les observateurs
    }

    /**
     * Adds an observer to the chronometer.
     *
     * @param o The observer to be added.
     */
    public void addObserver(Observer o) {
        observers.add(o);
    }

    /**
     * Removes an observer from the chronometer.
     *
     * @param o The observer to be removed.
     */
    public void deleteObserver(Observer o) {
        observers.remove(o);
    }

    /**
     * Removes all observers from the chronometer.
     */
    public void deleteObservers() {
        observers.clear();
    }

    /**
     * Marks the chronometer as having changed its state.
     */
    public void setChanged() {
        hasChanged = true;
    }

    /**
     * Clears the "changed" status of the chronometer.
     */
    public void clearChanged() {
        hasChanged = false;
    }

    /**
     * Checks if the chronometer's state has changed.
     *
     * @return True if the chronometer's state has changed, false otherwise.
     */
    public boolean hasChanged() {
        return hasChanged;
    }

    /**
     * Notifies all observers that the chronometer's state has changed.
     */
    public void notifyObservers() {
        if (hasChanged) {
            for (Observer o : observers) o.update();
            clearChanged();
        }
    }
}
