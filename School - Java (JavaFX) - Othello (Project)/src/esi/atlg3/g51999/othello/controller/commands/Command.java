package esi.atlg3.g51999.othello.controller.commands;

import esi.atlg3.g51999.othello.model.Model;
import esi.atlg3.g51999.othello.view.console.View;

/**
 * A Command class will execute a certain command without worring about the
 * command encapsulation.
 *
 * @author Andre Pereira
 */
public interface Command {

    /**
     * Execute the command.
     * @param model The data of the game.
     * @param view The UI of the game.
     */
    void execute(Model model, View view);

}
