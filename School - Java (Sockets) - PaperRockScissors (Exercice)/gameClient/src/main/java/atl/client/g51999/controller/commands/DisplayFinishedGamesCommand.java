package atl.client.g51999.controller.commands;

import atl.client.g51999.client.GameClient;
import atl.client.g51999.view.ClientView;

/**
 *
 * @author andre
 */
public class DisplayFinishedGamesCommand extends Command {

    public DisplayFinishedGamesCommand(GameClient client, ClientView view) {
        super(client, view);
    }

    @Override
    public void execute() {
        if (this.client.getGames().getFinishedGames().keySet().size() == 0) {
            this.view.printError("No games finished yet!");
        } else {
            this.view.displayFinishedGames(this.client.getGames());
        }
    }

}
