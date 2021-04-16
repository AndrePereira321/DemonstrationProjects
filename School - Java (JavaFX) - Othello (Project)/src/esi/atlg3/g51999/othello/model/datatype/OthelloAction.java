package esi.atlg3.g51999.othello.model.datatype;

/**
 * This enumeration represents a categorie of an ActionDetail.
 *
 * @author Andre Pereira
 */
public enum OthelloAction {
    START_GAME("Started the game."),
    PASS_TURN("Passed his turn."),
    PLAY_PIECE("Play's one Piece."),
    WIN_GAME("Wins the game.");

    private final String action;

    /**
     * Creates the action categorie.
     *
     * @param action The description of the action.
     */
    OthelloAction(String action) {
        this.action = action;
    }

    /**
     * Retrieves the action description.
     *
     * @return The description of the action.
     */
    public String getActionDescription() {
        return this.action;
    }

}
