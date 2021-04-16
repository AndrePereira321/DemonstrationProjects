package esi.atlg3.g51999.othello.model.exceptions;

/**
 * Indicates that the user is trying to put a Piece in an occuped Square.
 *
 * @author Andre Pereira
 */
public class OccupedSquareException extends Exception {

    /**
     * Creates the exception with a detail message.
     *
     * @param msg The detail message for the exception.
     */
    public OccupedSquareException(String msg) {
        super(msg);
    }
}
