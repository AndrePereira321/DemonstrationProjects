package atl.client.g51999.controller;

import atl.client.g51999.controller.commands.HelpCommand;
import atl.client.g51999.controller.commands.*;
import atl.client.g51999.client.GameClient;
import atl.client.g51999.view.ClientView;
import atl.g51999.gameserverutils.model.GameShape;
import atl.g51999.gameserverutils.model.GameType;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author andre
 */
public class CommandFactory {

    private final GameClient client;
    private final ClientView view;

    public CommandFactory(GameClient client, ClientView view) {
        this.client = client;
        this.view = view;
    }

    public Command generateCommand(String input) {
        try {
            return this.createCommand(input);
        } catch (IllegalArgumentException ex) {
            this.view.printError(ex.getMessage());
            return null;
        }
    }

    private Command createCommand(String input) {
        input = input.toLowerCase();
        String[] cmdArgs = input.split("\\s+");
        switch (cmdArgs[0]) {
            case "connect":
                return new ConnectCommand(client, view);
            case "list":
                return new ListCommand(client, view);
            case "displaygames":
                return new DisplayGamesCommand(client, view);
            case "displayfinished":
                return new DisplayFinishedGamesCommand(client, view);
            case "status":
                return new StatusCommand(client, view);
            case "exit":
                return new ExitCommand(client, view);
            case "disconnect":
                return new DisconnectCommand(client, view);
            case "help":
                return new HelpCommand(client, view);
            case "create":
            case "creategame":
            case "new":
                GameType gameType = this.retrieveGameType(cmdArgs);
                Set<Integer> opponents = (gameType == GameType.TRAINING) ? new HashSet() : this.retrieveOpponents(cmdArgs);
                return new CreateGameCommand(client, view, gameType, opponents);
            case "play":
                return new PlayCommand(client, view,
                        this.retrieveGameID(cmdArgs), this.retrieveGameShape(cmdArgs));
            default:
                throw new IllegalArgumentException("Unknown command");
        }
    }

    private GameType retrieveGameType(String[] cmdArgs) {
        if (cmdArgs.length < 2) {
            throw new IllegalArgumentException("No game type has been given!");
        }
        switch (cmdArgs[1]) /*Can throw IndexOutBounds*/ {
            case "train":
            case "trainning":
                return GameType.TRAINING;
            case "versus":
            case "vs":
                return GameType.VERSUS;
            default:
                throw new IllegalArgumentException("Unknown game type!");
        }
    }

    private Set<Integer> retrieveOpponents(String[] cmdArgs) {
        if (cmdArgs.length < 3) {
            throw new IllegalArgumentException("You don't give any opponent!");
        }
        Set<Integer> players = new HashSet();
        for (int i = 2; i < cmdArgs.length; i++) {
            try {
                players.add(Integer.parseInt(cmdArgs[i]));
            } catch (NumberFormatException ex) {
                throw new IllegalArgumentException("The opponent must be a number!");
            }
        }
        return players;
    }

    private int retrieveGameID(String[] cmdArgs) {
        if (cmdArgs.length < 2) {
            throw new IllegalArgumentException("You don't give the game ID!");
        }
        try {
            return Integer.parseInt(cmdArgs[1]);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("The game ID must be a number");
        }
    }

    private GameShape retrieveGameShape(String[] cmdArgs) {
        if (cmdArgs.length < 3) {
            throw new IllegalArgumentException("You don't give the shape to play!");
        }
        switch (cmdArgs[2]) {
            case "paper":
                return GameShape.PAPER;
            case "rock":
                return GameShape.ROCK;
            case "scissors":
                return GameShape.SCISSORS;
            default:
                throw new IllegalArgumentException("Unrecognized Shape!");
        }
    }

}
