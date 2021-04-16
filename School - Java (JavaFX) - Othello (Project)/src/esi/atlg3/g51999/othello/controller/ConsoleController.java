package esi.atlg3.g51999.othello.controller;

import esi.atlg3.g51999.othello.controller.commands.Command;
import esi.atlg3.g51999.othello.controller.commands.ConsoleCommandFactory;
import esi.atlg3.g51999.othello.model.Game;
import esi.atlg3.g51999.othello.model.Model;
import esi.atlg3.g51999.othello.view.ErrorMessages;
import esi.atlg3.g51999.othello.view.console.View;
import esi.atlg3.g51999.othello.view.console.Console;

/**
 * The main class who threats the user's input and modifiers the data according
 * the user input.
 *
 * @author Andre Pereira
 */
public class ConsoleController {

    private final Model model;
    private final View view;

    /**
     * Creates the game structures and the UI structures.
     */
    public ConsoleController() {
        this.model = new Game();
        this.view = new Console();
    }

    /**
     * Intializates the game according Othello rules, shows the start of the
     * app.
     */
    public void intialize() {
        model.initialize();
        view.intialize();
        view.displayBoard(model.getBoard(), model.getCurrentAvailablePuts());
        view.displayHelp();
    }

    /**
     * Checks if the game status is active (not over yet).
     *
     * @return True if the game is currently active.
     */
    public boolean gameIsActive() {
        return !this.model.isOver();
    }

    /**
     * Reads an user input command until the command is valid in Othello.
     *
     * @return The user command.
     */
    private Command createCommand() {
        Command cmd = null;
        while (cmd == null) {
            ConsoleCommandFactory cmdCreator = new ConsoleCommandFactory(
                    view.readCommand());
            try {
                cmd = cmdCreator.generateCommand();
                if (cmd == null) {
                    view.displayErrorMsg(ErrorMessages.INVALID_COMMAND_FORMAT);
                }
            } catch (ArrayIndexOutOfBoundsException notEnoughtArgs) {
                view.displayErrorMsg(ErrorMessages.NOT_ENOUGHT_ARGS_COMMAND);
            }
        }
        return cmd;
    }

    /**
     * Execute the generated command.
     */
    public void executeCommand() {
        Command cmd = this.createCommand();
        cmd.execute(this.model, this.view);
    }

    /**
     * Displays the winner of the game.
     */
    public void endGame() {
        view.displayWinner(model.getWinner());
    }

}
