package atl.server.g51999.server;

import atl.server.g51999.model.*;
import atl.g51999.gameserverutils.messages.*;
import atl.g51999.gameserverutils.users.Members;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andre
 */
public class GameServer extends AbstractServer {

    private int connectionIndexer;
    private int gameIndexer;

    private final Members members;
    private final Games games;

    public GameServer() {
        super(12345);
        this.connectionIndexer = 0;
        this.gameIndexer = 0;
        this.members = new Members();
        this.games = new Games();
    }

    /**
     * Return the list of connected users.
     *
     * @return the list of connected users.
     */
    public Members getMembers() {
        return members;
    }

    /**
     * Return the server IP address.
     *
     * @return the server IP address.
     */
    public String getIP() {
        if (getLocalAddress() == null) {
            return "Unknown, may use: localhost";
        }
        return getLocalAddress().getHostAddress();
    }

    /**
     * Return the number of connected users.
     *
     * @return the number of connected users.
     */
    public int getNbConnected() {
        return super.getNumberOfClients();
    }

    @Override
    protected void clientConnected(ConnectionToClient client) {
        connectionIndexer++;
        members.add(connectionIndexer, client.getName(), client.getInetAddress());
        client.setInfo("ID", connectionIndexer);
        MessageMembers updateMessage = new MessageMembers(members);
        this.sendToAllClients(updateMessage);
        try {
            client.sendToClient(new MessageProfile(connectionIndexer, ""));
        } catch (IOException ex) {
            this.listeningException(ex);
        }
        this.notifyObservers(client);

    }

    @Override
    protected synchronized void clientDisconnected(ConnectionToClient client) {
        members.remove((int) client.getInfo("ID"));
        MessageMembers updateMessage = new MessageMembers(members);
        this.sendToAllClients(updateMessage);
        this.notifyObservers(client);
    }

    @Override
    protected void listeningException(Throwable exception) {
        this.notifyObservers(exception);
    }

    @Override
    protected void serverStarted() {
        this.notifyObservers(null);
    }

    @Override
    protected void serverStopped() {
        this.notifyObservers(null);
    }

    @Override
    protected void serverClosed() {
        this.notifyObservers(null);
    }

    @Override
    protected void handleMessageFromClient(Object content, ConnectionToClient client) {
        Message msg;
        if (content instanceof Message) {
            msg = (Message) content;
            switch (msg.getType()) {
                case PROFILE:
                    this.updateUserProfile(msg, client);
                    break;
                case NEW_GAME:
                    this.createNewGame(msg, client);
                    break;
                case PLAY:
                    this.playShape(msg, client);
                    break;
                case MEMBERS:
                    break;
                default:
                    throw new IllegalStateException("Invalid msg type!");
            }
        } else {
            throw new IllegalArgumentException("Invalid content msg!");
        }
    }

    private static InetAddress getLocalAddress() {
        try {
            Enumeration<NetworkInterface> b = NetworkInterface.getNetworkInterfaces();
            while (b.hasMoreElements()) {
                for (InterfaceAddress f : b.nextElement().getInterfaceAddresses()) {
                    if (f.getAddress().isSiteLocalAddress()) {
                        return f.getAddress();
                    }
                }
            }
        } catch (SocketException e) {
            Logger.getLogger(GameServer.class.getName()).log(Level.SEVERE, "NetworkInterface error", e);
        }
        return null;
    }

    private final void updateUserProfile(Message msg, ConnectionToClient client) {
        MessageProfile userProfile = (MessageProfile) msg;
        client.setInfo("name", userProfile.getUser().getName());
        this.members.changeName(userProfile.getUser().getName(), (int) client.getInfo("ID"));
        this.sendToAllClients(new MessageMembers(members));
        this.notifyObservers(userProfile);
    }

    private final void createNewGame(Message msg, ConnectionToClient client) {
        MessageGame gameData = (MessageGame) msg;
        switch (gameData.getGameType()) {
            case VERSUS:
                this.createVersusGame(gameData);
                break;
            case TRAINING:
                this.createTrainingGame(gameData);
                break;
            default:
                throw new IllegalStateException("Unknown game type!");
        }
        this.notifyObservers(gameData);
    }

    private void createVersusGame(MessageGame gameData) {
        gameIndexer++;
        verifyRequirements(gameData);
        this.games.addGame(new VersusGame(gameIndexer, gameData.getUser().getId(), (int) gameData.getOpponents().toArray()[0]));
        gameData.setGameID(gameIndexer);
        gameData.getOpponents().add(gameData.getUser().getId());
        this.sendToClients(gameData.getOpponents(), gameData);
    }

    private void createTrainingGame(MessageGame gameData) {
        gameIndexer++;
        this.games.addGame(new TrainingGame(gameData.getUser().getId(), gameIndexer));
        gameData.setGameID(gameIndexer);
        gameData.getOpponents().add(gameData.getUser().getId());
        this.sendToClient(gameData.getUser().getId(), gameData);
    }

    private void verifyRequirements(MessageGame gameData) {
        for (int player : gameData.getOpponents()) {
            if (members.getUser(player) == null) {
                throw new IllegalStateException("The opponent " + player + "is not connected!");
            }
        }
        if (gameData.getOpponents().size() != 1) {
            throw new IllegalStateException("Must have 2 players to create a Versus game.");
        }
        if (gameData.getOpponents().contains(gameData.getUser().getId())) {
            throw new IllegalStateException("The user included himself with the opponents");
        }
    }

    private void playShape(Message msg, ConnectionToClient client) {
        MessagePlay playData = (MessagePlay) msg;
        AbstractGame game = this.games.getGame(playData.getGameID());
        try {
            game.play(playData.getUser().getId(), playData.getShape());
            this.notifyObservers(playData);
            if (game.isOver()) {
                MessageWinner winMsg = new MessageWinner(game.getWinner(), game.getGameID(), game.getResults());
                this.sendToClients(game.getPlayers(), winMsg);
                this.games.removeGame(game.getGameID());
                this.notifyObservers(winMsg);
            }
        } catch (GameException ex) {
            this.listeningException(ex);
        }
    }

    public void sendToClient(int clientID, Message msg) {
        for (ConnectionToClient connection : super.threads) {
            if ((int) connection.getInfo("ID") == clientID) {
                try {
                    connection.sendToClient(msg);
                } catch (IOException ex) {
                    this.listeningException(ex);
                }
            }
        }
    }

    public void sendToClients(Set<Integer> clients, Message msg) {
        for (ConnectionToClient connection : super.threads) {
            if (clients.contains(connection.getInfo("ID"))) {
                try {
                    connection.sendToClient(msg);
                } catch (IOException ex) {
                    this.listeningException(ex);
                }
            }
        }
    }

    public Games getGames() {
        return this.games;
    }
}
