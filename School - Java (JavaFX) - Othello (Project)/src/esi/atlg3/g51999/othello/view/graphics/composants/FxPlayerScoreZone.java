package esi.atlg3.g51999.othello.view.graphics.composants;

import esi.atlg3.g51999.othello.model.Model;
import esi.atlg3.g51999.othello.model.PlayerColor;
import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * A score zone will be a GridPane containing all elements relatives to the
 * player, like his name, piece color and score.
 *
 * @author Andre Pereira
 */
public class FxPlayerScoreZone extends GridPane {

    private final PlayerColor color;
    private final Circle piece;
    private final Label lblPlayerName;
    private final Label lblPlayerScore;

    private final Model model;

    /**
     * Creates the ScoreZone with the given Color.
     *
     * @param color The color of the player.
     * @param model The model to read the current player of the game.
     */
    public FxPlayerScoreZone(PlayerColor color, Model model) {
        super();
        this.color = color;
        this.piece = new Circle(5, Color.TRANSPARENT);
        this.lblPlayerName = new Label();
        this.lblPlayerScore = new Label("2");
        this.model = model;
    }

    /**
     * Initializates the score zone according the color.
     */
    public void initFxScoreZone() {
        this.defineStyleClass();
        this.definePieceColor();
        this.createTitles();
        this.definePlayerDataZone();
        super.getChildren().forEach((node)
                -> GridPane.setHalignment(node, HPos.CENTER));
    }

    /**
     * Creates the title of each field.
     */
    private void createTitles() {
        Label lblNom = new Label("Name");
        Label lblPion = new Label("Pawn");
        Label lblScore = new Label("Score");
        super.add(lblNom, 0, 0);
        super.add(lblPion, 1, 0);
        super.add(lblScore, 2, 0);
    }

    /**
     * Puts the fields into the grid.
     */
    private void definePlayerDataZone() {
        super.add(this.lblPlayerName, 0, 1);
        super.add(this.piece, 1, 1);
        super.add(this.lblPlayerScore, 2, 1);
    }

    /**
     * Defines the style class for the player zone.
     */
    private void defineStyleClass() {
        super.getStyleClass().add("playerZone");
        switch (this.color) {
            case BLACK:
                super.getStyleClass().addAll("blackPlayerZone", "currentZone");
                break;
            case WHITE:
                super.getStyleClass().add("whitePlayerZone");
                break;
        }
    }

    /**
     * Sets the color of the Player Piece.
     */
    private void definePieceColor() {
        if (this.color == PlayerColor.BLACK) {
            this.piece.setFill(Color.BLACK);
        } else {
            this.piece.setFill(Color.WHITE);
        }

    }

    /**
     * Sets a new score to be displayed.
     *
     * @param score The score of the player.
     */
    public void setScore(int score) {
        this.lblPlayerScore.setText("" + score);
        if (this.model.getCurrentPlayer().getColor() == this.color) {
            this.getStyleClass().add("currentZone");
        } else {
            this.getStyleClass().remove("currentZone");
        }
    }

    /**
     * Sets the name of the player to be displayed.
     *
     * @param name The name of the player.
     */
    public void setName(String name) {
        if (name != null && !name.equals("")) {
            this.lblPlayerName.setText(name);
        }
    }
}
