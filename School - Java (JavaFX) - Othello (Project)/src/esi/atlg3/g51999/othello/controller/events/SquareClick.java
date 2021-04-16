package esi.atlg3.g51999.othello.controller.events;

import esi.atlg3.g51999.othello.model.Model;
import esi.atlg3.g51999.othello.model.exceptions.NoAvailablePutsException;
import esi.atlg3.g51999.othello.model.exceptions.OccupedSquareException;
import esi.atlg3.g51999.othello.model.exceptions.PutNotSurroundException;
import esi.atlg3.g51999.othello.view.graphics.composants.FxSquare;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

/**
 * Trys to put a Piece in the Board if the player clicks one FxSquare. If the
 * Square is invalid it will vibrate.
 *
 * @author Andre Pereira
 */
public class SquareClick implements EventHandler<MouseEvent> {

    private final FxSquare square;
    private final Model model;
    private final TranslateTransition invalidAnimation;

    /**
     * Gives acces to the FxSquare and the data of the game.
     *
     * @param square The rendered Square.
     * @param model The data of the game.
     */
    public SquareClick(FxSquare square, Model model) {
        this.square = square;
        this.model = model;
        this.invalidAnimation = new TranslateTransition(Duration.millis(80), square);
    }

    /**
     * Trys to put a piece in the square, if the player can't put a piece in
     * that Square an animation will vibrate that square. If the next player has
     * no available puts an alert will be displayed.
     *
     * @param event Not used.
     */
    @Override
    public void handle(MouseEvent event) {
        this.invalidAnimation.setByX(5);
        this.invalidAnimation.setByY(0);
        this.invalidAnimation.setAutoReverse(true);
        this.invalidAnimation.setCycleCount(8);
        try {
            model.playPiece(square.getPosition(), model.getCurrentPlayer().playPiece());
        } catch (OccupedSquareException | PutNotSurroundException ex) {
            this.invalidAnimation.play();
        } catch (NoAvailablePutsException ex) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setHeaderText("No available puts!");
            alert.setTitle("No available puts!");
            alert.setContentText(
                    "The player " + model.getOpponentPlayer().getName()
                    + " has no available puts! \nIt's the turn of: "
                    + model.getCurrentPlayer().getName());
            alert.show();
        }
    }

}
