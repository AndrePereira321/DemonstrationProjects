/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atl.server.g51999;

import atl.server.g51999.controller.Controller;
import atl.server.g51999.server.GameServer;
import atl.server.g51999.view.ServerView;

/**
 *
 * @author andre
 */
public class Main {
    
    public static void main(String[] args) {
        GameServer server = new GameServer();
        ServerView view = new ServerView();
        Controller ctrl = new Controller(server, view);
        ctrl.init();
        ctrl.interact();
    }
    
}
