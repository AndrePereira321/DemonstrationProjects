package atl.server.g51999.controller.commands;

import atl.server.g51999.server.GameServer;
import atl.server.g51999.view.ServerView;

/**
 *
 * @author andre
 */
public class ListCommand extends Command {
    
    public ListCommand(GameServer server, ServerView view) {
        super(server, view);
    }
    
    @Override
    public void execute() {
        this.view.displayMembers(this.server.getMembers());
    }
    
}
