package atl.server.g51999.controller;

import atl.server.g51999.controller.commands.*;
import atl.server.g51999.server.GameServer;
import atl.server.g51999.view.ServerView;

/**
 *
 * @author andre
 */
public class CommandFactory {

    private final GameServer server;
    private final ServerView view;

    public CommandFactory(GameServer server, ServerView view) {
        this.server = server;
        this.view = view;
    }

    public Command generateCommand(String input) {
        input = input.toLowerCase();
        String[] cmdArgs = input.split("\\s+");
        switch (cmdArgs[0]) {
            case "start":
                return new StartCommand(server, view);
            case "stop":
                return new StopCommand(server, view);
            case "games":
                return new GamesCommand(server, view);
            case "list":
                return new ListCommand(server, view);
            case "exit":
                return new ExitCommand(server, view);
            case "help":
                return new HelpCommand(server, view);
            default:
                this.view.printError("Unknown Command!");
                return null;
        }
    }

}
