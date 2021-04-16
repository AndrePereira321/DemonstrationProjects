package esi.atlg3.g51999.othello.model.exceptions;

/**
 * Indicates that the current player has no available puts to play his Piece.
 *
 * @author Andre Pereira
 */
public class NoAvailablePutsException extends Exception {

    /**
     * Creates the exception with a defined message.
     *
     * @param msg The message of the exception.
     */
    public NoAvailablePutsException(String msg) {
        super(msg);
    }
}
