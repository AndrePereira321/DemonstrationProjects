package esi.atlg3.g51999.othello.model;

import esi.atlg3.g51999.othello.model.datatype.Position;
import esi.atlg3.g51999.othello.model.exceptions.NoAvailablePutsException;
import esi.atlg3.g51999.othello.model.exceptions.OccupedSquareException;
import esi.atlg3.g51999.othello.model.exceptions.PutNotSurroundException;
import java.util.HashMap;
import java.util.List;

/**
 * Othello is a strategy game for two Players: Black and White. It is played on
 * a unicolored Board of 64 Squares, 8 of 8, called Othello board. These players
 * have 64 two-tone pawns, to put his Pieces. The goal of the game is to have
 * more Pieces than the opponent at the end of the game. This one ends when no
 * player can play legal kick.
 *
 * @author Andre Pereira
 */
public interface Model {

    /**
     * Initializates the board according with the Othello rules. So, with 4
     * Pieces in the center and 32 Pieces for each Player.
     */
    void initialize();

    /**
     * Defines the new names of the players.
     *
     * @param names The new names for the player.
     */
    void setPlayersNames(HashMap<PlayerColor, String> names);

    /**
     * Checks if the game is over. The game is over when the two players have no
     * available moves, or when is no more empty squares.
     *
     * @return True if the game is over.
     */
    boolean isOver();

    /**
     * Retrieves the player who has win the game.
     *
     * @return The winner of the match.
     */
    Player getWinner();

    /**
     * Retrieves the current player who must put one Piece.
     *
     * @return The current Player.
     */
    Player getCurrentPlayer();

    /**
     * Retrieves the opponent player.
     *
     * @return The opponent Player.
     */
    Player getOpponentPlayer();

    /**
     * Retrieves the board structure.
     *
     * @return The board of the game.
     */
    Board getBoard();

    /**
     * Puts the given Piece in the given Position.To put one Piece in the board
     * the player must surround at least one of the opponent's piece.
     *
     * @param position The position to put the Piece.
     * @param piece The piece to put.
     * @throws OccupedSquareException Indicates that the position refers an
     * Occuped Square.
     * @throws PutNotSurroundException Indicates that the position don't
     * surround any enemy Piece.
     * @throws NoAvailablePutsException Indicates that the next player will not
     * have any available put.
     */
    void playPiece(Position position, Piece piece)
            throws OccupedSquareException, PutNotSurroundException, NoAvailablePutsException;

    /**
     * Gets the scores of each Player.
     *
     * @return An HashMap containing the number of pieces in the board.
     */
    HashMap<PlayerColor, Integer> getScores();

    /**
     * Retrieves the list of available positions for the current player.
     *
     * @return List of available positions for the current player.
     */
    List<Position> getCurrentAvailablePuts();

    /**
     * Swaps the turn of the current player.
     */
    void passTurn();

    /**
     * Retrieves the number of pieces in the board.
     *
     * @return The number of pieces in the board.
     */
    int getPieceCount();

    HashMap<PlayerColor, Integer> getPlayerPieceCounter();

    HashMap<PlayerColor, String> getPlayersNames();
}
