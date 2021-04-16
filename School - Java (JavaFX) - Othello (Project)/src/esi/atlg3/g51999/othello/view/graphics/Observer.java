package esi.atlg3.g51999.othello.view.graphics;

/**
 * An Observer class will be updated when an Observable Object changes of state.
 * We must add the Observer instance in the Observable list of Observers.
 *
 * @author Andre Pereira
 */
public interface Observer {

    /**
     * Updates the observer.
     *
     * @param arg An argument informing the update type in the Model.
     */
    public void update(Object arg);

}
