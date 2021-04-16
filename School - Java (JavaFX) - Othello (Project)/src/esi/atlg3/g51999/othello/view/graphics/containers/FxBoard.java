package esi.atlg3.g51999.othello.view.graphics.containers;

import esi.atlg3.g51999.othello.controller.events.SquareClick;
import esi.atlg3.g51999.othello.controller.events.SquareMouseEnter;
import esi.atlg3.g51999.othello.controller.events.SquareMouseExit;
import esi.atlg3.g51999.othello.model.Board;
import esi.atlg3.g51999.othello.model.Model;
import esi.atlg3.g51999.othello.model.Piece;
import esi.atlg3.g51999.othello.model.datatype.Position;
import esi.atlg3.g51999.othello.utils.Configs;
import esi.atlg3.g51999.othello.view.graphics.composants.FxSquare;
import java.util.LinkedList;
import java.util.List;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 * A Board will be a GridPane containing FxSquares. The squares can be
 * identified by their positions, with the id in form: "square" + row + "x" +
 * column, where row and column are integers.
 *
 * @author Andre Pereira
 */
public class FxBoard extends GridPane {

    private final FxSquare[][] squares;
    private final List<Position> availablePuts;
    private Board board;

    /**
     * Creates the GridPane.
     */
    public FxBoard() {
        super();
        this.squares = new FxSquare[Configs.BOARD_SIZE][Configs.BOARD_SIZE];
        this.availablePuts = new LinkedList();
    }

    /**
     * Initializates the board creating each square and giving them an id in the
     * form "square" + row + "x" + column, where row and column are integer
     * desbribing the positions.Its also sets the default spacings and effects.
     *
     * @param model The data of the game, to init the board.
     */
    public void initBoardUI(Model model) {
        this.initIndexSquares();
        this.initFxSquares(model);
        super.getStyleClass().add("board");
        this.board = model.getBoard();

    }

    /**
     * Initializes the indexers for the rows and the columns.
     */
    private void initIndexSquares() {
        FxSquare rowSquare, colSquare;
        Label rowLabel, colLabel;
        for (int i = 0; i < Configs.BOARD_SIZE; i++) {
            rowSquare = new FxSquare();
            colSquare = new FxSquare();
            rowLabel = new Label("" + (i + 1));
            colLabel = new Label("" + (char) (65 + i));
            colSquare.setPrefSize(Configs.SQUARE_SIZE, Configs.SQUARE_SIZE);
            rowSquare.setPrefSize(Configs.SQUARE_SIZE, Configs.SQUARE_SIZE);
            rowSquare.getStyleClass().add("indexSquare");
            colSquare.getStyleClass().add("indexSquare");
            rowSquare.getChildren().add(rowLabel);
            colSquare.getChildren().add(colLabel);
            super.add(rowSquare, 0, i + 1);
            super.add(colSquare, i + 1, 0);
        }
    }

    /**
     * Creates the squares giving them the ID and the events.
     */
    private void initFxSquares(Model model) {
        FxSquare square;
        for (Position pos : model.getBoard()) {
            square = new FxSquare();
            square.initSquareUI();
            square.setId("square" + pos.getRow() + "x" + pos.getColumn());

            square.setOnMouseEntered(new SquareMouseEnter(square, model));
            square.setOnMouseExited(new SquareMouseExit(square, model));
            square.setOnMouseClicked(new SquareClick(square, model));

            if (!model.getBoard().isEmpty(pos)) {
                square.drawPiece(model.getBoard().getPiece(pos));
            } else if (model.getCurrentAvailablePuts().contains(pos)) {
                if (model.getBoard().getBonusPositions().contains(pos)) {
                    square.setStyle("-fx-background-color: darkgoldenrod;");
                } else {
                    square.setStyle("-fx-background-color: yellow;");
                }
                this.availablePuts.add(pos);
            } else if (model.getBoard().getBonusPositions().contains(pos)) {
                square.setStyle("-fx-background-color: blue;");
            }

            super.add(square, pos.getRow() + 1, pos.getColumn() + 1);
            this.squares[pos.getRow()][pos.getColumn()] = square;
        }
    }

    /**
     * Draws the given Piece in the Board at the given position.
     *
     * @param pos The position to draw the piece.
     * @param piece The Piece to be drawn.
     */
    public void putPieceFX(Position pos, Piece piece) {
        this.retrieveSquare(pos).drawPiece(piece);
    }

    /**
     * Rotates all the pieces that were turned in the Model.
     *
     * @param toTurn A List of pieces to rotate.
     */
    public void rotatePieces(List<Position> toTurn) {
        toTurn.forEach(pos -> this.retrieveSquare(pos).getPiece().rotatePiece());
    }

    /**
     * Refreshes the color of the squares were the player is able to play a
     * Piece.
     *
     * @param availablePuts The refreshed list of available puts.
     */
    public void refreshAvailablePuts(List<Position> availablePuts) {
        this.availablePuts.forEach(pos -> {
            if (this.board.getBonusPositions().contains(pos)) {
                this.retrieveSquare(pos).setStyle("-fx-background-color: blue;");
            } else {
                this.retrieveSquare(pos).setStyle("-fx-background-color: "
                        + Configs.SQUARE_COLOR + ";");
            }
        });
        this.availablePuts.clear();
        availablePuts.forEach(pos -> {
            if (this.board.getBonusPositions().contains(pos)) {
                this.retrieveSquare(pos).setStyle("-fx-background-color: darkgoldenrod;");
            } else {
                this.retrieveSquare(pos).setStyle("-fx-background-color: yellow;");
            }
            this.availablePuts.add(pos);
        });
    }

    /**
     * Retrieves the FxSquare at the given position.
     *
     * @param pos The position of the Square.
     * @return T
     */
    private FxSquare retrieveSquare(Position pos) {
        return this.squares[pos.getRow()][pos.getColumn()];
    }
}
