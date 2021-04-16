package atl.client.g51999.client;

import atl.client.g51999.model.Games;
import atl.g51999.gameserverutils.users.*;
import atl.g51999.gameserverutils.messages.*;
import java.io.IOException;

/**
 * The <code> GameClient </code> contains all the methods necessary to set up a
 * gaming client.
 */
public class GameClient extends AbstractClient {

    private User mySelf;
    private Members members;
    private final Games games;

    /**
     * Constructs the client. Opens the connection with the server. Sends the
     * user name inside a <code> MessageProfile </code> to the server. Builds an
     * empty list of users.
     *
     * @param host the server's host name.
     * @param port the port number.
     * @param name the name of the user.
     * @param password the password needed to connect.
     */
    public GameClient(String host, int port, String name, String password) {
        super(host, port);
        members = new Members();
        mySelf = new User(0, name);
        this.games = new Games();
    }

    @Override
    protected void connectionEstablished() {
        this.notifyObservers(null);
    }

    @Override
    protected void connectionException(Exception exception) {
        this.notifyObservers(exception);
    }

    @Override
    protected void connectionClosed() {
        this.notifyObservers(null);
    }

    /**
     * Quits the client and closes all aspects of the connection to the server.
     *
     * @throws IOException if an I/O error occurs when closing.
     */
    public void quit() throws IOException {
        closeConnection();
    }

    /**
     * Return all the connected users.
     *
     * @return all the connected users.
     */
    public Members getMembers() {
        return members;
    }

    /**
     * Return the user with the given id.
     *
     * @param id of the user.
     * @return the user with the given id.
     */
    public User getUser(int id) {
        return members.getUser(id);
    }

    public User getUser() {
        return this.mySelf;
    }

    public Games getGames() {
        return this.games;
    }

    @Override
    protected void handleMessageFromServer(Object obj) {
        if (obj instanceof Message) {
            Message msg = (Message) obj;
            switch (msg.getType()) {
                case PROFILE:
                    this.identificateMySelf(msg);
                    break;
                case NEW_GAME:
                    this.gameCreated((MessageGame) msg);
                    break;
                case WINNER:
                    this.gameIsOver((MessageWinner) msg);
                    break;
                case MEMBERS:
                    this.updateMembers((MessageMembers) msg);
                    break;
                default:
                    throw new IllegalArgumentException("Unxpected Message!");
            }
        }
    }

    private void gameCreated(MessageGame msg) {
        this.games.addGame(msg.getGameID(), msg.getGameType(), msg.getOpponents());
        this.notifyObservers(msg);
    }

    private void identificateMySelf(Message msg) {
        this.mySelf = new User(msg.getUser().getId(), mySelf.getName());
        try {
            super.sendToServer(new MessageProfile(mySelf.getId(), mySelf.getName()));
        } catch (IOException ex) {
            this.connectionException(ex);
        }
        this.notifyObservers(msg);
    }

    private void gameIsOver(MessageWinner msg) {
        this.games.setResults(msg.getGameID(), msg.getResults());
        if (msg.getWinner() == mySelf.getId()) {
            this.games.setWon(msg.getGameID());
        }
        this.notifyObservers(msg);
    }

    private void updateMembers(MessageMembers msg) {
        this.members = msg.getMembers();
        this.notifyObservers(msg);
    }

}
