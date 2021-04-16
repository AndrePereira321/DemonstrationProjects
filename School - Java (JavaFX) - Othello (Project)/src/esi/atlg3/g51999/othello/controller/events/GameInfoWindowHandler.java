package esi.atlg3.g51999.othello.controller.events;

import esi.atlg3.g51999.othello.model.Game;
import esi.atlg3.g51999.othello.model.Model;
import esi.atlg3.g51999.othello.view.graphics.windows.InfoWindow;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 *
 * @author Andre Pereira
 */
public class GameInfoWindowHandler implements EventHandler<ActionEvent> {

    private final Model model;

    public GameInfoWindowHandler(Model model) {
        this.model = model;
    }

    @Override
    public void handle(ActionEvent event) {
        InfoWindow infoWindow = new InfoWindow(this.model);
        infoWindow.initInfoWindow();
        ((Game) this.model).addObserver(infoWindow);
        infoWindow.show();
    }

}
