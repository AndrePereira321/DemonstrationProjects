package atl.server.g51999.controller;

import atl.server.g51999.controller.commands.Command;
import atl.server.g51999.controller.commands.ExitCommand;
import atl.server.g51999.server.GameServer;
import atl.server.g51999.view.ServerView;

/**
 *
 * @author andre
 */
public class Controller {

    private final GameServer server;
    private final ServerView view;

    public Controller(GameServer server, ServerView view) {
        this.server = server;
        this.view = view;
    }

    public void init() {
        this.server.addObserver(view);
        this.view.displayWelcomeMessage();
    }

    public void interact() {
        CommandFactory factory = new CommandFactory(server, view);
        Command command = null;
        while (!(command instanceof ExitCommand)) {
            if ((command = factory.generateCommand(view.askCommand())) != null) {
                command.execute();
            }
        }
    }
}
