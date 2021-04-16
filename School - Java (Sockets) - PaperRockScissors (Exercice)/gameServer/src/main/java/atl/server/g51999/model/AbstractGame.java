package atl.server.g51999.model;

import atl.g51999.gameserverutils.model.GameShape;
import atl.g51999.gameserverutils.model.GameType;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author andre
 */
public abstract class AbstractGame {

    protected final HashMap<Integer, GameShape> players;
    private final GameType type;
    private final int id;

    public AbstractGame(GameType type, int gameID, int... players) {
        this.type = type;
        this.id = gameID;
        this.players = new HashMap(4);
        for (int player : players) {
            this.players.put(player, null);
        }
    }

    public final Set<Integer> getPlayers() {
        return this.players.keySet();
    }

    public final int getGameID() {
        return this.id;
    }

    public final Map<Integer, GameShape> getResults() {
        return Collections.unmodifiableMap(players);
    }

    public boolean isOver() {
        for (int key : this.players.keySet()) {
            if (this.players.get(key) == null) {
                return false;
            }
        }
        return true;
    }

    public void play(int playerID, GameShape shape) throws GameException {
        if (!this.players.containsKey(playerID)) {
            throw new GameException("The given player is not from this game! Player: " + playerID);
        } else if (this.players.get(playerID) != null) {
            throw new GameException("The given player already played!");
        }
        this.players.put(playerID, shape);
    }

    public final GameType getGameType() {
        return this.type;
    }

    public abstract int getWinner() throws GameException;

}
