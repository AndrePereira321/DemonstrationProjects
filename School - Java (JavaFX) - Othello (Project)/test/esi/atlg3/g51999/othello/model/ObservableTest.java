/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.atlg3.g51999.othello.model;

import esi.atlg3.g51999.othello.view.graphics.Observer;
import esi.atlg3.g51999.othello.view.graphics.ViewUI;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Andre Pereira
 */
public class ObservableTest {

    public ObservableTest() {
    }

    /**
     * Test of addObserver method, of class Observable.
     */
    @Test
    public void testObserver() {
        System.out.println("addObserver");
        Obs obs = new Obs();
        Observable instance = new ObservableImpl();
        instance.addObserver(obs);
        instance.notifyObservers(null);
        assertTrue(obs.updated);
    }


    class ObservableImpl extends Observable {
    }

    class Obs implements Observer {

        public boolean updated = false;

        @Override
        public void update(Object arg) {
            updated = true;
            System.out.println("UPDATED");
        }
    }

}
