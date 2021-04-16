package esi.atlg3.g51999.othello.view.graphics.composants;

import esi.atlg3.g51999.othello.model.Piece;
import esi.atlg3.g51999.othello.model.datatype.Position;
import esi.atlg3.g51999.othello.utils.Configs;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/**
 * This class will display a single Square inside of the Board. If the Square is
 * empty, it can have 2 BackGrounds: Green the squares where is impossible to
 * put a Piece, or Yellow if the current player can put a Piece in that square.
 * The Piece will be represented by a Cylinder, only will be show if the Square
 * contains a Piece.
 *
 * @author Andre Pereira
 */
public class FxSquare extends StackPane {

    private final FxPiece piece;
    private final Label pieceValue;

    /**
     * Creates a new Square, with a Transparent Piece and no background color.
     */
    public FxSquare() {
        super();
        this.piece = new FxPiece();
        this.pieceValue = new Label();
    }

    /**
     * Color the Square background and put the transparent Piece inside it.
     *
     */
    public void initSquareUI() {
        super.setPrefSize(Configs.SQUARE_SIZE, Configs.SQUARE_SIZE);
        super.setMaxSize(Configs.SQUARE_SIZE, Configs.SQUARE_SIZE);
        super.setStyle("-fx-background-color: " + Configs.SQUARE_COLOR + ";");
        super.setEffect(new DropShadow(20, Color.BLACK));

        this.piece.initFxPiece();

        StackPane.setAlignment(this.pieceValue, Pos.TOP_LEFT);
        this.pieceValue.setPadding((new Insets(3)));

        super.getChildren().addAll(piece, pieceValue);
    }

    /**
     * Draws the color of the Piece in the Square.
     *
     * @param dataPiece The Piece to be drawn.
     */
    public void drawPiece(Piece dataPiece) {
        this.pieceValue.setText(dataPiece.getValue() + "");
        this.piece.drawPiece(dataPiece);
    }

    /**
     * Converts an ID of an FxSquare into a Position.
     *
     * @return The position of the square with the given ID.
     */
    public Position getPosition() {
        String pos[] = this.getId().replaceAll("square", "").split("x");
        int row = Integer.parseInt(pos[0]);
        int column = Integer.parseInt(pos[1]);
        return new Position(row, column);
    }

    /**
     * Retrieves the Piece of the FxSquare, represented by a Cylinder.
     *
     * @return The piece of the Square.
     */
    public FxPiece getPiece() {
        return this.piece;
    }
}
