package atl.client.g51999.controller.commands;

import atl.client.g51999.client.GameClient;
import atl.client.g51999.view.ClientView;
import atl.g51999.gameserverutils.messages.MessageGame;
import atl.g51999.gameserverutils.model.GameType;
import atl.g51999.gameserverutils.users.User;
import java.io.IOException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andre
 */
public class CreateGameCommand extends Command {

    private final GameType gameType;
    private final Set<Integer> opponents;

    public CreateGameCommand(GameClient client, ClientView view, GameType type, Set<Integer> opponents) {
        super(client, view);
        this.gameType = type;
        this.opponents = opponents;
    }

    @Override
    public void execute() {
        try {
            client.sendToServer(
                    new MessageGame(this.gameType, client.getUser(), this.opponents));
        } catch (IOException ex) {
            view.reportProblem(ex);
        }
    }

}
