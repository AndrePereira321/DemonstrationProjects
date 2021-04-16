package atl.client.g51999.view;

import atl.client.g51999.client.GameClient;
import atl.client.g51999.model.GameInfo;
import atl.client.g51999.model.Games;
import atl.client.g51999.model.Observable;
import atl.g51999.gameserverutils.messages.*;
import atl.g51999.gameserverutils.users.Members;
import atl.g51999.gameserverutils.users.User;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author andre
 */
public class ClientView implements Observer {

    private final Scanner kb;

    public ClientView() {
        this.kb = new Scanner(System.in);
    }

    public void reportProblem(Throwable ex) {
        System.err.println("[" + LocalTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME)
                + " - ERROR] - " + ex.getMessage());
        System.err.println("");
    }

    public void printError(String msg) {
        System.err.println("[" + LocalTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME)
                + " - ERROR] - " + msg);
    }

    public void println(String msg) {
        System.out.println("[" + LocalTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME)
                + " - UPDATE] $ " + msg);
    }

    public void print(String msg) {
        System.out.print("[" + LocalTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME)
                + " - UPDATE] $ " + msg);
    }

    public void displayLoadMsg() {
        println("Nous chargons les information nécessaires.");
    }

    public String displayWelcomeMsg() {
        println("************ Welcome to Paper/Scissors/Rock! ****************");
        println("This is an online game where you can play with other players!");
        println("Write <help> for more help");
        print("You may enter your name: ");
        return readInput();
    }

    public void displayHelp() {
        println("***************  HELP  ****************");
        println("Command <arg1> <arg2> ... - Description");
        println("connect - Connect to server.");
        println("list - Display the connected members.");
        println("create <gameType> <opponents ...> - Requests a new game.");
        println("---> GameTypes: Train, Versus");
        println("play <gameID> <gameShape> - Sends a new shape to the game.");
        println("---> GameShapes: Rock, Scissors, Paper");
        println("displaygames - Display all the running games.");
        println("displayfinished - Display the games over.");
        println("status - Display connection status.");
        println("disconnect - Disconnect from the server.");
        println("exit - Quits the app");
        println("help - For help.");
        println("****************************************");
    }

    public String askCommand() {
        println("Insert a command: ");
        return readInput();
    }

    public void displayGames(Games games) {
        HashMap<Integer, GameInfo> runningGames = games.getRunningGames();
        println("Liste de Jeux en Cours: ");
        println("|  Game ID  | |   Type   | |     Players     |");
        for (int gameID : runningGames.keySet()) {
            String gameDescription = "";
            String opponents = "";
            gameDescription += String.format("|%11d| ", gameID);
            gameDescription += String.format("|%10s| ", runningGames.get(gameID).getGameType());
            for (int player : runningGames.get(gameID).getOpponents()) {
                opponents += player + " ";
            }
            gameDescription += String.format("|%15s|", opponents);
            println(gameDescription);
        }
    }

    public void displayFinishedGames(Games games) {
        HashMap<Integer, GameInfo> finishedGames = games.getFinishedGames();
        println("Liste de Jeux Terminées: ");
        println("|  Game ID  | |   Type   | |     Players     | |   Status   |");
        for (int gameID : finishedGames.keySet()) {
            String gameDescription = "";
            String opponents = "";
            gameDescription += String.format("|%11d| ", gameID);
            gameDescription += String.format("|%10s| ", finishedGames.get(gameID).getGameType());
            for (int player : finishedGames.get(gameID).getOpponents()) {
                opponents += player + " ";
            }
            gameDescription += String.format("|%15s|", opponents);
            gameDescription += String.format("|%12s|", finishedGames.get(gameID).getGameStatus());
            println(gameDescription);
        }
    }

    public void displayStatus(GameClient client) {
        println("UserName: " + client.getUser().getName());
        if (client.isConnected()) {
            println("Connected to: " + client.getHost());
            println("ConnectionID: " + client.getUser().getId());
        } else {
            println("Not connected to server!");
        }
    }

    private String readInput() {
        String s = kb.nextLine();
        while (s.length() == 0) {
            printError("No Input was Given! Try again: ");
            s = kb.nextLine();
        }
        return s;
    }

    @Override
    public void update(Observable obj, Object arg) {
        if (arg == null) {
            if (obj instanceof GameClient) {
                this.informClientStatus((GameClient) obj);
            }
        } else {
            if (arg instanceof Message) {
                this.messageReceived((Message) arg);
            }
        }
    }

    private void informClientStatus(GameClient client) {
        if (client.isConnected()) {
            println("Connected to: " + client.getHost() + " !");;
        } else {
            println("Disconnected from server!");
        }
    }

    private void messageReceived(Message msg) {
        switch (msg.getType()) {
            case PROFILE:
                this.idReceived((MessageProfile) msg);
                break;
            case NEW_GAME:
                this.gameCreated((MessageGame) msg);
                break;
            case WINNER:
                this.gameOver((MessageWinner) msg);
                break;
            case MEMBERS:
                break;
        }
    }

    private void idReceived(MessageProfile msg) {
        println("Receiveid my own ID: " + msg.getUser().getId());
    }

    private void gameCreated(MessageGame msg) {
        String players = "";
        for (int player : msg.getOpponents()) {
            players += " " + player;
        }
        println("****** Game Created *******");
        println("Game ID: " + msg.getGameID());
        println("Game Type: " + msg.getGameType());
        println("Game Creator: " + msg.getUser().getId());
        println("Game Players:" + players);
    }

    private void gameOver(MessageWinner msg) {
        String players = "";
        String shapes = "";
        for (int player : msg.getResults().keySet()) {
            players += String.format("|%10s|", player);
            shapes += String.format("|%10s|", msg.getResults().get(player));

        }
        println("******** Game Over *********");
        println("Game ID: " + msg.getGameID());
        println("Game Winner: " + msg.getWinner());
        println("-Results: ");
        println(players);
        println(shapes);
    }

    public void displayMembers(Members members) {
        println("|   ID   | |      NAME      | |      IP      |");
        for (User user : members) {
            String usr = "";
            usr += String.format("|%8d| ", user.getId());
            usr += String.format("|%16s| ", user.getName());
            usr += String.format("|%14s|", user.getAddress());
            println(usr);
        }
    }

    public void displayGoodByeMsg() {
        println("The app will be closed!");
        println("Hope see you next time!");
    }

}
