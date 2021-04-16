package esi.atlg3.g51999.othello.controller.commands;

import esi.atlg3.g51999.othello.model.Model;
import esi.atlg3.g51999.othello.view.console.View;

/**
 * Displays help to the user about the commands.
 *
 * @author Andre Pereira
 */
public class Help implements Command {

    /**
     * Displays a list of all the command with a small documentation about how
     * to use it.
     *
     * @param model The data of the game.
     * @param view The UI of the game.
     */
    @Override
    public void execute(Model model, View view) {
        view.displayHelp();
    }

}
