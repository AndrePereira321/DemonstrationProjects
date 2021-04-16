package atl.server.g51999.view;

import atl.g51999.gameserverutils.messages.*;
import atl.g51999.gameserverutils.users.Members;
import atl.g51999.gameserverutils.users.User;
import atl.server.g51999.model.AbstractGame;
import atl.server.g51999.model.Games;
import atl.server.g51999.server.ConnectionToClient;
import atl.server.g51999.server.GameServer;
import atl.server.g51999.server.Observable;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author andre
 */
public class ServerView implements Observer {

    private final Scanner kb;

    public ServerView() {
        this.kb = new Scanner(System.in);
    }

    public static void print(String msg) {
        System.out.println("[" + LocalTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME)
                + " - UPDATE] $ " + msg);
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

    public String askCommand() {
        print("Insert a command: ");
        return readInput();
    }

    private String readInput() {
        String s = kb.nextLine();
        while (s.length() == 0) {
            printError("No Input was Given! Try again: ");
            s = kb.nextLine();
        }
        return s;
    }

    public void displayMembers(Members members) {
        print("|   ID   | |      NAME      | |      IP      |");
        for (User user : members) {
            String usr = "";
            usr += String.format("|%8d| ", user.getId());
            usr += String.format("|%16s| ", user.getName());
            usr += String.format("|%14s|", user.getAddress());
            print(usr);
        }
    }

    public void displayGames(Games games) {
        print("|  GameID  | | GameType | |  GamePlayers  | | GameStatus |");
        for (AbstractGame game : games.getGames()) {
            String description = "";
            description += String.format("|%10d| ", game.getGameID());
            description += String.format("|%10s| ", game.getGameType());
            description += String.format("|%12s| ", Arrays.toString(game.getPlayers().toArray()));
            description += String.format("|%12s| ", game.isOver() ? "Over" : "Running");
            print(description);
        }
    }

    public void displayWelcomeMessage() {
        print("Welcome to the GameServer Manager!");
        print("Write <help> for more help!");
    }

    public void displayHelp() {
        print("************ HELP ***********");
        print("<command> - Description");
        print("start - start the server listener!");
        print("stop - stops the server lsitener!");
        print("list - display the connected members when listenning");
        print("games - display the running games");
        print("exit - quits the app");
    }

    public void displayGoodByeMsg() {
        print("The app will be closed!");
    }

    @Override
    public void update(Observable obj, Object arg) {
        if (arg == null) {
            if (obj != null && obj instanceof GameServer) {
                this.informServerStatus((GameServer) obj);
            }
        } else {
            if (arg instanceof Message) {
                this.messageReceived((Message) arg);
            } else if (arg instanceof ConnectionToClient) {
                this.informConnection((ConnectionToClient) arg);
            } else if (arg instanceof Throwable) {
                this.reportProblem((Throwable) arg);
            }
        }
    }

    private void messageReceived(Message msg) {
        switch (msg.getType()) {
            case PROFILE:
                this.userIdentified((MessageProfile) msg);
                break;
            case NEW_GAME:
                this.gameCreated((MessageGame) msg);
                break;
            case PLAY:
                this.piecePlayed((MessagePlay) msg);
                break;
            case WINNER:
                this.gameOver((MessageWinner) msg);
        }
    }

    private void userIdentified(MessageProfile msg) {
        print("The user " + msg.getUser().getId()
                + " identified itself as " + msg.getUser().getName());
    }

    private void gameCreated(MessageGame msg) {
        String opponents = "[";
        for (int player : msg.getOpponents()) {
            opponents += player + " ";
        }
        opponents += "]";
        print("Game Created by " + msg.getUser().getName() + " with " + opponents);
        print("Game Type: " + msg.getGameType());
    }

    private void piecePlayed(MessagePlay msg) {
        print("Le joueur " + msg.getUser().getId() + " - " + msg.getUser().getName()
                + " a jouée: " + msg.getShape() + " dans le jeu " + msg.getGameID());
    }

    private void gameOver(MessageWinner messageWinner) {
        print("Le jeu " + messageWinner.getGameID() + " est terminé. Vainqueur: " + messageWinner.getWinner());
    }

    private void informServerStatus(GameServer server) {
        if (server.isClosed()) {
            print("****** SERVER  CLOSED! ********");
        } else if (server.isListening()) {
            print("****** SERVER STARTED! ********");
        } else {
            print("****** SERVER STOPPED! ********");
        }
    }

    private void informConnection(ConnectionToClient client) {
        if (client.isConnected()) {
            this.clientConnected(client);
        } else {
            this.clientDisconnected(client);
        }
    }

    private void clientDisconnected(ConnectionToClient client) {
        print("Client " + client.getInfo("ID") + " disconnected!");
    }

    private void clientConnected(ConnectionToClient client) {
        print("Client " + client.getInfo("ID") + " - "
                + client.getInetAddress().getHostAddress() + " connected!");
    }
}
