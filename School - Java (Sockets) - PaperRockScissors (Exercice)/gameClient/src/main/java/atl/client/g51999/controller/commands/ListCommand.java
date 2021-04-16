package atl.client.g51999.controller.commands;

import atl.client.g51999.client.GameClient;
import atl.client.g51999.view.ClientView;

/**
 *
 * @author andre
 */
public class ListCommand extends Command {

    public ListCommand(GameClient client, ClientView view) {
        super(client, view);
    }

    @Override
    public void execute() {
        this.view.displayMembers(client.getMembers());
    }

}
