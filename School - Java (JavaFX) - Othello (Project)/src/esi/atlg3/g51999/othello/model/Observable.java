package esi.atlg3.g51999.othello.model;

import esi.atlg3.g51999.othello.view.graphics.Observer;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents an observable object, who will report to the Observer
 * items any change of states. An observable object can have one or more
 * observers. An observer may be any object that implements interface Observer.
 *
 * @author Andre Pereira
 */
public abstract class Observable {

    protected final List<Observer> observers;

    /**
     * Creates an Observable object.
     */
    public Observable() {
        this.observers = new ArrayList();
    }

    /**
     * Adds an observer to be notified of any change of state of this observable
     * Object.
     *
     * @param obs The observer object.
     */
    public void addObserver(Observer obs) {
        this.observers.add(obs);
    }

    /**
     * Reports a change of state of the observable object.
     *
     * @param arg An argument describing the update.
     */
    public void notifyObservers(Object arg) {
        for (Observer obs : this.observers) {
            obs.update(arg);
        }
    }
}
