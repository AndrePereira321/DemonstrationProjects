package esi.atlg3.g51999.othello.controller.commands;

import esi.atlg3.g51999.othello.model.Model;
import esi.atlg3.g51999.othello.view.console.View;

/**
 * Displays the board.
 *
 * @author Andre Pereira
 */
public class Show implements Command {

    /**
     * Displays the board and the player who must play the next Piece.
     *
     * @param game The data of the game.
     * @param view The UI of the game.
     */
    @Override
    public void execute(Model game, View view) {
        view.displayBoard(game.getBoard(), game.getCurrentAvailablePuts());
        view.displayCurrentPlayer(game.getCurrentPlayer());
    }
}
