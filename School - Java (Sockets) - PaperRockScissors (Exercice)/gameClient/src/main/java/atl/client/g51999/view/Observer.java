package atl.client.g51999.view;

import atl.client.g51999.model.Observable;

/**
 * An Observer object will be updated when one of his observables changes their
 * state.
 *
 * @author andre
 */
public interface Observer {

    /**
     * Updates the observer after the change of state of one Observable.
     *
     * @param obj The object who changed his state.
     * @param arg An argument describing the situation.
     */
    public void update(Observable obj, Object arg);

}
