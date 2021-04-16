package esi.atlg3.g51999.othello.view.graphics.containers;

import esi.atlg3.g51999.othello.model.Model;
import esi.atlg3.g51999.othello.model.Piece;
import esi.atlg3.g51999.othello.model.PlayerColor;
import esi.atlg3.g51999.othello.model.datatype.Position;
import esi.atlg3.g51999.othello.utils.Configs;
import java.util.HashMap;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * The PlayZone is the zone where the user can interact with the game. It will
 * contain the FxBoard to be able to play a piece, and some buttons to interact
 * with the app.
 *
 * @author Andre Pereira
 */
public class FxPlayZone extends VBox {

    private final FxBoard fxBoard;

    private final Button btQuit;
    private final Button btNext;
    private final Button btRestart;

    private final ProgressBar progressBar;
    private final ProgressIndicator progressIndicator;

    /**
     * Creates the PlayZone.
     */
    public FxPlayZone() {
        super(8);
        this.fxBoard = new FxBoard();
        this.btQuit = new Button("Quit");
        this.btNext = new Button("Next");
        this.btRestart = new Button("Restart");
        this.progressBar = new ProgressBar(4 / Configs.BOARD_SIZE * Configs.BOARD_SIZE);
        this.progressIndicator = new ProgressIndicator(0.5);
    }

    /**
     * Initializates the PlayZone, initalizating the Board and the ButtonZone.
     *
     * @param model Only to init the board and set the events.
     */
    public void initFxPlayZone(Model model) {
        HBox buttonZone = new HBox();
        GridPane progressIndicators = this.initProgressIndicators();
        this.progressIndicator.setMinSize(35, 35);

        super.setAlignment(Pos.CENTER);
        buttonZone.getStyleClass().add("buttonZone");

        this.fxBoard.initBoardUI(model);

        this.btNext.setOnAction((event) -> model.passTurn());
        this.btQuit.setOnAction((event) -> System.exit(0));

        buttonZone.getChildren().addAll(btQuit, btNext, btRestart);
        super.getChildren().addAll(fxBoard, progressIndicators, buttonZone);
    }

    /**
     * Initializes the zone for the progress indicators of the the game.
     *
     * @return The zone containing the progresses indicators.
     */
    private GridPane initProgressIndicators() {
        GridPane progressIndicators = new GridPane();
        progressIndicators.getStyleClass().add("progressIndicators");
        progressIndicators.add(new Label("Game Progress:"), 0, 1);
        progressIndicators.add(new Label("Score Status: "), 0, 0);
        progressIndicators.add(progressBar, 1, 1);
        progressIndicators.add(progressIndicator, 1, 0);
        return progressIndicators;
    }

    /**
     * Retrieves the graphical board.
     *
     * @return The FxBoard.
     */
    public FxBoard getFxBoard() {
        return this.fxBoard;
    }

    /**
     * Retrieves the button to restart the game.
     *
     * @return The button to restart the game.
     */
    public Button getRestartButton() {
        return this.btRestart;
    }

    /**
     * Draws a the given piece in the board at the given position.
     *
     * @param pos The position to draw the piece.
     * @param piece The piece to be drawn.
     */
    public void putPieceFX(Position pos, Piece piece) {
        this.fxBoard.putPieceFX(pos, piece);
    }

    /**
     * Sets the score percentage of the players.
     *
     * @param scores An HashMap containing the scores for each color.
     */
    public void setScorePercentage(HashMap<PlayerColor, Integer> scores) {
        this.progressIndicator.setProgress(scores.get(PlayerColor.BLACK)
                / (double) (scores.get(PlayerColor.BLACK) + scores.get(PlayerColor.WHITE)));
    }

    /**
     * Sets the game progress (the number of pieces of the board).
     *
     * @param nbPieces The number of pieces currently in the board.
     */
    public void setGameProgress(int nbPieces) {
        this.progressBar.setProgress((double) nbPieces / (Configs.BOARD_SIZE * Configs.BOARD_SIZE));
    }
}
