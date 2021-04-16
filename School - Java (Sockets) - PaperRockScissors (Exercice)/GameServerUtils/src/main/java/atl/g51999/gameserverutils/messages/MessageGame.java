package atl.g51999.gameserverutils.messages;

import atl.g51999.gameserverutils.model.GameType;
import atl.g51999.gameserverutils.users.User;
import java.util.Set;

/**
 *
 * @author andre
 */
public class MessageGame extends Message{

    private final GameType gameType;
    
    private int gameID;

    public MessageGame(GameType gameType, User user, Set<Integer> opponents) {
        super(Type.NEW_GAME, user, opponents);
        this.gameType = gameType;
    }
    
    public void setGameID(int id) {
        this.gameID = id;
    }
    
    public int getGameID() {
        return this.gameID;
    }

    public GameType getGameType() {
        return this.gameType;
    }

    public Set<Integer> getOpponents() {
        return (Set<Integer>)this.getContent();
    }

}
