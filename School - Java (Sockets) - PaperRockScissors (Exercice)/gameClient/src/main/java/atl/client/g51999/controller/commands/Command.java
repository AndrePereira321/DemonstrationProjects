package atl.client.g51999.controller.commands;

import atl.client.g51999.client.GameClient;
import atl.client.g51999.view.ClientView;

/**
 *
 * @author andre
 */
public abstract class Command {

    protected final GameClient client;
    protected final ClientView view;

    Command(GameClient client, ClientView view) {
        this.client = client;
        this.view = view;
    }

    public abstract void execute();

}
