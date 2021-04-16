package atl.g51999.gameserverutils.messages;


import atl.g51999.gameserverutils.model.GameShape;
import atl.g51999.gameserverutils.users.User;


/**
 *
 * @author andre
 */
public class MessagePlay extends Message {

    private final int gameID;

    public MessagePlay(User author, int gameID, GameShape shape) {
        super(Type.PLAY, author, shape);
        this.gameID = gameID;
    }
    
    public GameShape getShape() {
        return (GameShape)this.getContent();
    }
    
    public int getGameID() {
        return this.gameID;
    }

}
