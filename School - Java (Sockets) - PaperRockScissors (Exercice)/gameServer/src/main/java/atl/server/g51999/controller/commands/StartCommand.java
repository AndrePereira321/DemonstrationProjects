package atl.server.g51999.controller.commands;

import atl.server.g51999.server.GameServer;
import atl.server.g51999.view.ServerView;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andre
 */
public class StartCommand extends Command {
    
    public StartCommand(GameServer server, ServerView view) {
        super(server, view);
    }
    
    @Override
    public void execute() {
        try {
            this.server.listen();
        } catch (IOException ex) {
            this.view.reportProblem(ex);
        }
        
    }
}
