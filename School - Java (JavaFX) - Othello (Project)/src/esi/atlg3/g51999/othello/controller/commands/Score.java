package esi.atlg3.g51999.othello.controller.commands;

import esi.atlg3.g51999.othello.model.Model;
import esi.atlg3.g51999.othello.view.console.View;

/**
 * Displays the score of each Player. The score if the sum of the values of each
 * Piece.
 *
 * @author Andre Pereira
 */
class Score implements Command {

    /**
     * Displays the score.
     *
     * @param game The data of the game.
     * @param view The UI of the game.
     */
    @Override
    public void execute(Model game, View view) {
        view.displayScore(game.getScores());
        view.displayCurrentPlayer(game.getCurrentPlayer());
    }

}
