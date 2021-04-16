package atl.client.g51999.controller.commands;

import atl.client.g51999.client.GameClient;
import atl.client.g51999.view.ClientView;
import java.io.IOException;

/**
 *
 * @author andre
 */
public class DisconnectCommand extends Command {
    
    public DisconnectCommand(GameClient client, ClientView view) {
        super(client, view);
    }
    
    @Override
    public void execute() {
        try {
            this.client.closeConnection();
        } catch (IOException ex) {
            this.view.reportProblem(ex);
        }
    }
    
}
