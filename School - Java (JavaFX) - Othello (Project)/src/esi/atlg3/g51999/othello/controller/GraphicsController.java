package esi.atlg3.g51999.othello.controller;

import esi.atlg3.g51999.othello.model.Game;
import esi.atlg3.g51999.othello.model.PlayerColor;
import esi.atlg3.g51999.othello.view.graphics.ViewUI;
import java.util.HashMap;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

/**
 * The graphics controller facade will manage the View and the Model.
 *
 * @author Andre Pereira
 */
public class GraphicsController {

    private Game model;
    private ViewUI view;

    /**
     * Creates a new graphical game interface.
     */
    public GraphicsController() {
        this.model = new Game();
        this.view = new ViewUI(this.model);
    }

    /**
     * Retrieves the root node for the primary stage.
     *
     * @return The root node for the primary stage.
     */
    public VBox getRoot() {
        return this.view.getRoot();
    }

    /**
     * Intializates the game according Othello rules, shows the start of the
     * app.
     */
    public void intialize() {
        this.model.initialize();
        this.view.intialize();
    }

    /**
     * Associates the observers of the app.
     */
    public void associateObservers() {
        this.model.addObserver(this.view);
    }

    /**
     * Defines the main events for the app.
     *
     * @param scene The main scene of the primary stage.
     */
    public void defineEvents(Scene scene) {
        this.view.getRestartButton().setOnAction((event) -> this.restartGame(scene));
    }

    /**
     * Restarts the game with the sames players.
     *
     * @param scene The main scene of the primary stage.
     */
    private void restartGame(Scene scene) {
        HashMap<PlayerColor, String> names = new HashMap();
        names.put(model.getCurrentPlayer().getColor(), model.getCurrentPlayer().getName());
        names.put(model.getOpponentPlayer().getColor(), model.getOpponentPlayer().getName());
        this.model = new Game();
        this.view = new ViewUI(this.model);
        this.intialize();
        this.defineEvents(scene);
        this.associateObservers();
        this.model.setPlayersNames(names);
        scene.setRoot(this.view.getRoot());
    }
}
