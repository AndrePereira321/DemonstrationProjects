package atl.server.g51999.server;

import atl.server.g51999.view.Observer;
import java.util.HashSet;
import java.util.Set;

/**
 * An observable object contains a set of observers that will be notified from a
 * change of state of the observable object.
 *
 * @author andre
 */
public abstract class Observable {

    private final Set<Observer> observers;

    /**
     * Initializates the observable attributes.
     */
    protected Observable() {
        this.observers = new HashSet();
    }

    /**
     * Adds a new observer.
     *
     * @param obs The observer element.
     */
    public void addObserver(Observer obs) {
        this.observers.add(obs);
    }

    /**
     * Removes the given observer.
     *
     * @param obs The observer to remove.
     */
    public void removeObsserver(Observer obs) {
        this.observers.remove(obs);
    }

    /**
     * Notify the observers from a change of state.
     *
     * @param arg An argument describing the change of state.
     */
    public void notifyObservers(Object arg) {
        for (Observer obs : this.observers) {
            obs.update(this, arg);
        }
    }
}
