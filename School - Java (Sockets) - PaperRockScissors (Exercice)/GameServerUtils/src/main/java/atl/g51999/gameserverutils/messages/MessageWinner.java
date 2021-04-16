package atl.g51999.gameserverutils.messages;

import atl.g51999.gameserverutils.model.GameShape;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author andre
 */
public class MessageWinner extends Message {

    private final int gameID;
    private final Map<Integer, GameShape> results;

    public MessageWinner(int winner, int gameID, Map<Integer, GameShape> results) {
        super(Type.WINNER, null, winner);
        this.gameID = gameID;
        this.results = results;
    }

    public int getGameID() {
        return this.gameID;
    }

    public int getWinner() {
        return (int) this.getContent();
    }

    public Map<Integer, GameShape> getResults() {
        return this.results;
    }
}
