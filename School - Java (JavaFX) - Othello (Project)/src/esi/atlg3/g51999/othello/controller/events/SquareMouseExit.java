package esi.atlg3.g51999.othello.controller.events;

import esi.atlg3.g51999.othello.model.Model;
import esi.atlg3.g51999.othello.utils.Configs;
import esi.atlg3.g51999.othello.view.graphics.composants.FxSquare;
import java.util.Arrays;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 * This EventHandler will modify the square when the mouse exits the square.
 *
 * @author Andre Pereira
 */
public class SquareMouseExit implements EventHandler<MouseEvent> {

    private final FxSquare square;
    private final Model model;

    /**
     * Creates a new SquareMouseExit handler.
     *
     * @param square The Square from the View.
     * @param model The model to read the square status.
     */
    public SquareMouseExit(FxSquare square, Model model) {
        this.square = square;
        this.model = model;
    }

    /**
     * Sets the square to his default color.
     *
     * @param event Not used.
     */
    @Override
    public void handle(MouseEvent event) {
        if (model.getBoard().getBonusPositions().contains(square.getPosition())) {
            if (model.getCurrentAvailablePuts().contains(square.getPosition())) {
                square.setStyle("-fx-background-color: darkgoldenrod;");
            } else {
                square.setStyle("-fx-background-color: blue;");
            }
        } else if (!model.getCurrentAvailablePuts().contains(square.getPosition())) {
            square.setStyle("-fx-background-color: " + Configs.SQUARE_COLOR + ";");
        } else {
            square.getPiece().initFxPiece();
        }
    }

}
