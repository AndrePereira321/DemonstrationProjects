package atl.server.g51999.controller.commands;

import atl.server.g51999.server.GameServer;
import atl.server.g51999.view.ServerView;

/**
 *
 * @author andre
 */
public abstract class Command {

    protected final ServerView view;
    protected final GameServer server;

    Command(GameServer server, ServerView view) {
        this.view = view;
        this.server = server;
    }

    public abstract void execute();
}
