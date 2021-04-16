package esi.atlg3.g51999.othello.controller.bot;

import esi.atlg3.g51999.othello.model.Model;
import esi.atlg3.g51999.othello.model.PlayerColor;
import esi.atlg3.g51999.othello.model.datatype.ActionDetail;
import esi.atlg3.g51999.othello.model.datatype.OthelloAction;
import esi.atlg3.g51999.othello.model.exceptions.NoAvailablePutsException;
import esi.atlg3.g51999.othello.model.exceptions.OccupedSquareException;
import esi.atlg3.g51999.othello.model.exceptions.PutNotSurroundException;
import esi.atlg3.g51999.othello.view.graphics.Observer;

/**
 * The Bot will observe the game and wait for the correct occasion to play his
 * Piece. According with his difficult it can have different strategies to
 * choose the position to play. The bot will take the place of the white player.
 *
 * @author Andre Pereira
 */
public class Bot implements Observer {

    private final AbstractPlayStrategy playStrategy;
    private final Model model;

    /**
     * Creates a new bot.
     *
     * @param model The model to read the status of the game.
     * @param difficult The difficult of the bot.
     */
    public Bot(Model model, Difficult difficult) {
        this.model = model;
        switch (difficult) {
            case EASY:
                this.playStrategy = new RandomPlayStrategy(model);
                break;
            default:
                throw new IllegalStateException("Difficult not recognized!");
        }
    }

    /**
     * Auto play a piece according with the chosen strategy.
     */
    void autoPlayPiece() {
        try {
            if (!model.getCurrentAvailablePuts().isEmpty()) {
                model.playPiece(playStrategy.getPlayPosition(), model.getCurrentPlayer().playPiece());
            }
        } catch (NoAvailablePutsException | OccupedSquareException | PutNotSurroundException e) {
                model.passTurn();
        }
    }

    /**
     * Wait for the turn of the white player, and play his piece right after the
     * black player has putted his piece.
     *
     * @param arg An argument describing the game status.
     */
    @Override
    public void update(Object arg) {
        if (model.getCurrentPlayer().getColor() == PlayerColor.WHITE && !model.isOver()) {
            if (arg != null) {
                if (arg instanceof ActionDetail) {
                    ActionDetail action = (ActionDetail) arg;
                    if (action.getOthelloAction() == OthelloAction.PLAY_PIECE) {
                        this.autoPlayPiece();
                    }
                }
            }
        }

    }
}
