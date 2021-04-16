/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atl.client.g51999.controller.commands;

import atl.client.g51999.client.GameClient;
import atl.client.g51999.controller.commands.Command;
import atl.client.g51999.view.ClientView;

/**
 *
 * @author andre
 */
public class HelpCommand extends Command {

    public HelpCommand(GameClient client, ClientView view) {
        super(client, view);
    }

    @Override
    public void execute() {
        this.view.displayHelp();
    }

}
