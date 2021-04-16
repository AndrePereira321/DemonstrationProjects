package esi.atlg3.g51999.othello.model.exceptions;

/**
 * Indicates that the user is trying to put a Piece without surrouding any enemy
 * Piece.
 *
 * @author Andre Pereira
 */
public class PutNotSurroundException extends Exception {

    /**
     * Creates the exception with a detail message.
     *
     * @param msg The detail message for the exception.
     */
    public PutNotSurroundException(String msg) {
        super(msg);
    }
}
