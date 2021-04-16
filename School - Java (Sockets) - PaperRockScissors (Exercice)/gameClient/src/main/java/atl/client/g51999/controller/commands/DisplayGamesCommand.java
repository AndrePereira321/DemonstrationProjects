package atl.client.g51999.controller.commands;

import atl.client.g51999.client.GameClient;
import atl.client.g51999.view.ClientView;

/**
 *
 * @author andre
 */
public class DisplayGamesCommand extends Command {
    
    public DisplayGamesCommand(GameClient client, ClientView view) {
        super(client, view);
    }
    
    @Override
    public void execute() {
        if (client.getGames().getRunningGames().isEmpty()) {
            this.view.printError("No running games!");
        } else {
            this.view.displayGames(client.getGames());
        }
    }
    
}
