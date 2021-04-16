package atl.client.g51999.controller.commands;

import atl.client.g51999.client.GameClient;
import atl.client.g51999.view.ClientView;
import atl.g51999.gameserverutils.messages.MessagePlay;
import atl.g51999.gameserverutils.model.GameShape;
import java.io.IOException;

/**
 *
 * @author andre
 */
public class PlayCommand extends Command {

    private final int gameID;
    private final GameShape shape;

    public PlayCommand(GameClient client, ClientView view, int gameID, GameShape shape) {
        super(client, view);
        this.gameID = gameID;
        this.shape = shape;
    }

    @Override
    public void execute() {
        try {
            this.client.sendToServer(new MessagePlay(this.client.getUser(), gameID, shape));
        } catch (IOException ex) {
            this.view.reportProblem(ex);
        }
    }

}
