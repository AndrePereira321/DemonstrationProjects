package esi.atlg3.g51999.othello.view.console;

import esi.atlg3.g51999.othello.model.datatype.Position;
import esi.atlg3.g51999.othello.model.*;
import java.util.HashMap;
import java.util.List;

/**
 * The UI of the game. It represents the data from the model and shows it to the
 * user.
 *
 * @author Andre Pereira
 */
public interface View {

    /**
     * Initialize the UI structures and displays the first screen.
     */
    void intialize();

    /**
     * Displays the board, separating each Square and shows the Pieces inside
     * it. Also shows the available possible puts to the current player.
     *
     * @param board The board of the game.
     * @param availablePuts A list containing the available puts positions to
     * inform the Player where he can play a Piece.
     */
    void displayBoard(Board board, List<Position> availablePuts);

    /**
     * Reads the user command.
     *
     * @return The user input command.
     */
    String readCommand();

    /**
     * Displays the score of the two players of the game.
     *
     * @param score An HashMap containing the score for the player WHITE and
     * BLACK.
     */
    void displayScore(HashMap<PlayerColor, Integer> score);

    /**
     * Displays an error message.
     *
     * @param errorMsg The message to be displayed.
     */
    void displayErrorMsg(String errorMsg);

    /**
     * Displays an documentation for each command.
     */
    void displayHelp();

    /**
     * Displays the color of the current player.
     *
     * @param player The player to be displayed.
     */
    void displayCurrentPlayer(Player player);

    /**
     * Display the player who must play the next Piece.
     *
     * @param player The player who must play the next Piece.
     */
    void displayNextTurn(Player player);

    /**
     * Display the winner of the game.
     *
     * @param player The winner of the game.
     */
    void displayWinner(Player player);

}
