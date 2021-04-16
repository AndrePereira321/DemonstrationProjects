package atl.client.g51999;

import atl.client.g51999.client.GameClient;
import atl.client.g51999.controller.Controller;
import atl.client.g51999.controller.DataLoader;
import atl.client.g51999.view.ClientView;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andre
 */
public class Main {

    private static DataLoader dataLoader;

    private static void loadConfigs(ClientView view) {
        try {
            dataLoader = new DataLoader();
        } catch (Exception ex) {
            view.printError("Problem starting the program!");
            view.printError("File: configs.properties not stable!");
            System.exit(-1);
        }
    }

    public static void main(String[] args) {
        ClientView view = new ClientView();
        view.displayLoadMsg();
        loadConfigs(view);
        String userName = view.displayWelcomeMsg();
        String pw = "";
        GameClient client = new GameClient(dataLoader.getHost(), dataLoader.getPort(), userName, pw);
        Controller ctrl = new Controller(client, view);
        ctrl.init();
        ctrl.interact();
    }
}
