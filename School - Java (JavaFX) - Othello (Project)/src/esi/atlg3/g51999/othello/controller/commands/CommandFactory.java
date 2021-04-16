package esi.atlg3.g51999.othello.controller.commands;

/**
 * Takes the user inputs and generate an Othello command.
 *
 * @author Andre Pereira
 */
public interface CommandFactory {

    /**
     * Parses the user input and generates a command.
     *
     * @return An Othello command, or null in case of invalid command.
     */
    Command generateCommand();

}
