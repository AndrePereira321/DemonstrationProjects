package esi.atlg3.g51999.othello.controller.events;

import esi.atlg3.g51999.othello.model.Model;
import esi.atlg3.g51999.othello.view.graphics.composants.FxSquare;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;

/**
 * This EventHandler will give some visual information for the player telling if
 * he can put or not a Piece in the square entered with the mouse.
 *
 * @author Andre Pereira
 */
public class SquareMouseEnter implements EventHandler<MouseEvent> {

    private final FxSquare square;
    private final Model model;

    /**
     * Creates a new SquareMouseEnterEvent.
     *
     * @param square The square from the view.
     * @param model The model to read the status of the square.
     */
    public SquareMouseEnter(FxSquare square, Model model) {
        this.square = square;
        this.model = model;
    }

    /**
     * Changes the color of the square to red if the player cant put a piece in
     * that square. Or displays a temporary piece from the same color of the
     * player if the player can put a piece in that square.
     *
     * @param event Not used.
     */
    @Override
    public void handle(MouseEvent event) {
        if (!model.getCurrentAvailablePuts().contains(square.getPosition())) {
            square.setStyle("-fx-background-color: red");
        } else {
            PhongMaterial tmpBackground = new PhongMaterial();
            switch (model.getCurrentPlayer().getColor()) {
                case BLACK:
                    tmpBackground.setDiffuseColor(Color.BLACK);
                    break;
                case WHITE:
                    tmpBackground.setDiffuseColor(Color.WHITE);
                    break;
            }
            square.getPiece().setMaterial(tmpBackground);
        }
    }

}
