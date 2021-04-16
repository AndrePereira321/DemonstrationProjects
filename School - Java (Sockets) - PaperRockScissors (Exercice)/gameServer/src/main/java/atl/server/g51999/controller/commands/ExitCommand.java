/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atl.server.g51999.controller.commands;

import atl.server.g51999.server.GameServer;
import atl.server.g51999.view.ServerView;

/**
 *
 * @author andre
 */
public class ExitCommand extends Command {

    public ExitCommand(GameServer server, ServerView view) {
        super(server, view);
    }

    @Override
    public void execute() {
        if(this.server.isListening()) {
            this.server.stopListening();
        }
        this.view.displayGoodByeMsg();
        System.exit(0);
    }
    
}
