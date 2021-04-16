package esi.atlg3.g51999.othello.view.console;

import esi.atlg3.g51999.othello.model.datatype.Position;
import static esi.atlg3.g51999.othello.view.console.AinsiColors.*;

import esi.atlg3.g51999.othello.model.*;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * A first implementation of the UI to test the Model. The user will be able to
 * play the game in the shell, inputing the commands with text from the
 * keyboard.
 *
 * @author Andre Pereira
 */
public class Console implements View {

    private final Scanner kb;

    /**
     * Initializates the standard input from the system.
     */
    public Console() {
        this.kb = new Scanner(System.in);
    }

    /**
     * Displays the first screen.
     */
    @Override
    public void intialize() {
        this.displayWelcomeMessage();
    }

    /**
     * Displays the title of the game.
     */
    public void displayWelcomeMessage() {
        System.out.println("===============================================");
        System.out.println("               WELCOME TO OTHELLO              ");
        System.out.println("===============================================");
    }

    /**
     * Displays a documentation for each command.
     */
    @Override
    public void displayHelp() {
        System.out.println("---------------------HELP ----------------------");
        System.out.println(ANSI_GREEN + "play [columnLetter] [rowNumber]"
                + COLOR_RESET + " - To play a Piece.");
        System.out.println(ANSI_GREEN + "score "
                + COLOR_RESET + " - To see the players scores!");
        System.out.println(ANSI_GREEN + "show "
                + COLOR_RESET + " - To display the board.");
        System.out.println(ANSI_GREEN + "help "
                + COLOR_RESET + " - To display help.");
    }

    /**
     * Displays the color of the current player.
     *
     * @param player The player to be displayed.
     */
    @Override
    public void displayCurrentPlayer(Player player) {
        System.out.println("Current Player: " + player.getColor());
    }

    /**
     * Displays the board, separating each Square and shows the Pieces inside
     * it. The default background for the empty squares is GREEN, and the
     * squares containing Pieces will have a background with the same color of
     * the Piece. The Pieces will be displayed with a letter "W" for White or
     * "B" for Black and with the value of that Piece. The yellow background
     * will represent the squares where the current player can input one Piece.
     *
     * @param board The board of the game.
     * @param availablePuts A list containing the available puts positions to
     * inform the current Player where he can play a Piece.
     */
    @Override
    public void displayBoard(Board board, List<Position> availablePuts) {
        Position lineBreak = new Position(-1, 0);
        this.displayBoardColumns();
        for (Position pos : board) {
            if (pos.getRow() != lineBreak.getRow()) {
                this.boardRowBreak(pos.getRow() + 1);
            }
            this.defineSquareBackground(pos, availablePuts);
            if (!board.isEmpty(pos)) {
                this.printPiece(board, pos);
            } else {
                System.out.print("  ");
            }
            System.out.print(COLOR_RESET + " ");
            lineBreak = pos;
        }
        System.out.println("|");

    }

    /**
     * Display the columns of the game.
     */
    private void displayBoardColumns() {
        System.out.print("|   | A  | B  | C  | D  | E  | F  | G  | H  ");
    }

    /**
     * Displays the line number.
     *
     * @param row The row number.
     */
    private void boardRowBreak(int row) {
        System.out.println("|");
        System.out.print("| " + row + " ");
    }

    /**
     * Defines the background of the Square, Yellow if the current player can
     * put a Piece in that Square, or Green if not.
     *
     * @param position The position of the Square.
     * @param availablePuts The list of positions where the current player can
     * put one Piece.
     */
    private void defineSquareBackground(Position position, List<Position> availablePuts) {
        if (availablePuts.contains(position)) {
            System.out.print("| " + ANSI_YELLOW_BACKGROUND);
        } else {
            System.out.print("| " + ANSI_GREEN_BACKGROUND);
        }
    }

    /**
     * Prints the Piece in the board with the value of the Piece. The Square
     * background will be in the same color of the Piece.
     *
     * @param board The board of the game.
     * @param pos The position of the Piece.
     */
    private void printPiece(Board board, Position pos) {
        System.out.print(ANSI_RED);
        int val = board.getPiece(pos).getValue();
        switch (board.getPiece(pos).getColor()) {
            case WHITE:
                System.out.print(ANSI_WHITE_BACKGROUND + "W" + val);
                break;
            case BLACK:
                System.out.print(ANSI_BLACK_BACKGROUND + "B" + val);
        }
    }

    /**
     * Displays the player who must put the next Piece.
     *
     * @param player The player who must play the next Piece.
     */
    @Override
    public void displayNextTurn(Player player) {
        System.out.println("It's your turn player " + player.getColor());
    }

    /**
     * Asks the user to input one command.
     *
     * @return The user input.
     */
    @Override
    public String readCommand() {
        System.out.print(ANSI_BLACK_BACKGROUND + ANSI_RED
                + "Insert one command: " + COLOR_RESET);
        return kb.nextLine();
    }

    /**
     * Displays an error message in red.
     *
     * @param errorMsg The message of the error.
     */
    @Override
    public void displayErrorMsg(String errorMsg) {
        System.out.println(ANSI_RED + errorMsg + COLOR_RESET);
    }

    /**
     * Displays the score of the two players.
     *
     * @param score An HashMap containing the score for the color WHITE and
     * BLACK.
     */
    @Override
    public void displayScore(HashMap<PlayerColor, Integer> score) {
        System.out.println("Black Color: " + score.get(PlayerColor.BLACK));
        System.out.println("White Color: " + score.get(PlayerColor.WHITE));
    }

    /**
     * Displays an congratulations message for the winner of the game.
     *
     * @param player The winner of the game.
     */
    @Override
    public void displayWinner(Player player) {
        if (player != null) {
            System.out.println("Congratulations!");
            System.out.println("The winner is the player " + player.getColor());
        } else {
            System.out.println("The match ended in equality!");
            System.out.println("There is no winner!");
        }
    }

}
