package esi.atlg3.g51999.othello.controller.bot;

import esi.atlg3.g51999.othello.model.datatype.Position;

/**
 * This interface represents an strategy used by the bot to auto play one piece.
 *
 * @author Andre Pereira
 */
public interface AbstractPlayStrategy {

    /**
     * Retrieves the position chosen by the strategy to play the piece.
     *
     * @return The Position to play the piece.
     */
    Position getPlayPosition();

}
