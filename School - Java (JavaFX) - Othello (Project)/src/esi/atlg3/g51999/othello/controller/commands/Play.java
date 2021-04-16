package esi.atlg3.g51999.othello.controller.commands;

import esi.atlg3.g51999.othello.model.exceptions.OccupedSquareException;
import esi.atlg3.g51999.othello.model.exceptions.NoAvailablePutsException;
import esi.atlg3.g51999.othello.model.exceptions.PutNotSurroundException;
import esi.atlg3.g51999.othello.model.Model;
import esi.atlg3.g51999.othello.model.datatype.Position;
import esi.atlg3.g51999.othello.view.console.View;
import esi.atlg3.g51999.othello.view.ErrorMessages;

/**
 * The play command allows the user to put a Piece in the board and changes the
 * current player. It's the main command to play the game.
 *
 * @author Andre Pereira
 */
public class Play implements Command {

    private final int row;
    private final int column;

    /**
     * Receives the row and the column to put the piece.
     *
     * @param i The row to put the Piece.
     * @param j The column to put the Piece.
     */
    public Play(int i, int j) {
        this.row = i;
        this.column = j;
    }

    /**
     * Puts one piece in the game, and displays the Board with the putted Piece.
     * It will display an error message if the user try to put a Piece in a
     * Position out of the Board, if the user try to put a Piece without
     * surrouding the any enemy Piece or in a occuped Square. After the user put
     * one Piece, if the next player don't have any available put, it will be
     * the turn of the previous player.
     *
     * @param game The data of the game.
     * @param view The UI of the game.
     */
    @Override
    public void execute(Model game, View view) {
        try {
            Position position = new Position(row, column);
            game.playPiece(position, game.getCurrentPlayer().playPiece());
            view.displayBoard(game.getBoard(), game.getCurrentAvailablePuts());
            view.displayNextTurn(game.getCurrentPlayer());
        } catch (IllegalArgumentException e) {
            view.displayErrorMsg(ErrorMessages.INVALID_COORDINATES);
        } catch (PutNotSurroundException e) {
            view.displayErrorMsg(ErrorMessages.NOT_SURROUND_ENEMY_PIECE);
        } catch (OccupedSquareException e) {
            view.displayErrorMsg(ErrorMessages.OCCUPED_SQUARE);
        } catch (NoAvailablePutsException e) {
            view.displayErrorMsg(ErrorMessages.NO_AVAILABLE_PUTS
                    + game.getCurrentPlayer().getColor());
            view.displayBoard(game.getBoard(), game.getCurrentAvailablePuts());
        }
    }

}
