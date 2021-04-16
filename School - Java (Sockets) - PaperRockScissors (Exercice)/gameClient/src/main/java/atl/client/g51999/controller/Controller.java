package atl.client.g51999.controller;

import atl.client.g51999.controller.commands.ExitCommand;
import atl.client.g51999.controller.commands.Command;
import atl.client.g51999.client.GameClient;
import atl.client.g51999.view.ClientView;
import java.io.IOException;

/**
 *
 * @author andre
 */
public class Controller {

    private final GameClient client;
    private final ClientView view;

    public Controller(GameClient client, ClientView view) {
        this.client = client;
        this.view = view;
    }

    public void init() {
        this.client.addObserver(view);
    }

    public void interact() {
        CommandFactory factory = new CommandFactory(client, view);
        Command command = null;
        while (!(command instanceof ExitCommand)) {
            if ((command = factory.generateCommand(view.askCommand())) != null) {
                command.execute();
            }
        }
    }

}
