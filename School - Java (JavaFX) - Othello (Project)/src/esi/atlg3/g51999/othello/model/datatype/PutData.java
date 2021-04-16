package esi.atlg3.g51999.othello.model.datatype;

import esi.atlg3.g51999.othello.model.Piece;
import java.util.List;

/**
 * This structure contains some information about a put move like the put
 * position, the piece that has been putted and a list of pieces that have been
 * turned.
 *
 * @author Andre Pereira
 */
public class PutData {

    private final List<Position> piecesToTurn;
    private final Position putPosition;
    private final Piece putPiece;

    /**
     * Creates a new put data.
     *
     * @param piecesToTurn A List of Pieces that have been turned.
     * @param putPosition The position of the putted piece.
     * @param putPiece The putted piece.
     */
    public PutData(List<Position> piecesToTurn, Position putPosition, Piece putPiece) {
        this.piecesToTurn = piecesToTurn;
        this.putPosition = putPosition;
        this.putPiece = putPiece;
    }

    /**
     * Retrieves a list of the pieces that have been turned.
     *
     * @return A list of the pieces that have been turned.
     */
    public List<Position> getPiecesToTurn() {
        return piecesToTurn;
    }

    /**
     * Retrieves the position of the putted piece.
     *
     * @return The position of the putted piece.
     */
    public Position getPutPosition() {
        return putPosition;
    }

    /**
     * Retrieves the putted piece.
     *
     * @return The putted piece.
     */
    public Piece getPutPiece() {
        return putPiece;
    }

}
