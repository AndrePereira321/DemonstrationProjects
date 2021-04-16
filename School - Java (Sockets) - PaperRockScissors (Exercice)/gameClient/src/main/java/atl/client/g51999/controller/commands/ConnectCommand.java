package atl.client.g51999.controller.commands;

import atl.client.g51999.client.GameClient;
import atl.client.g51999.view.ClientView;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andre
 */
public class ConnectCommand extends Command {

    public ConnectCommand(GameClient client, ClientView view) {
        super(client, view);
    }

    @Override
    public void execute() {
        if (!client.isConnected()) {
            try {
                client.openConnection();
            } catch (IOException ex) {
                view.reportProblem(ex);
            }
        }

    }

}
