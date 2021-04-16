package atl.client.g51999.model;

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
public class GameInfo {

    private final GameType gameType;
    private GameShape playedShape;
    private GameStatus gameStatus;
    private Map<Integer, GameShape> playersStatus;

    public GameInfo(GameType gameType, Set<Integer> players) {
        this.gameType = gameType;
        this.playersStatus = new HashMap();
        this.playedShape = null;
        this.gameStatus = GameStatus.NOT_PLAYED;
        for (int player : players) {
            this.playersStatus.put(player, null);
        }
    }

    public Set<Integer> getOpponents() {
        return this.playersStatus.keySet();
    }

    public Map<Integer, GameShape> getResults() {
        return Collections.unmodifiableMap(this.playersStatus);
    }

    public GameType getGameType() {
        return this.gameType;
    }

    public GameShape getPlayedShape() {
        return this.playedShape;
    }

    public GameStatus getGameStatus() {
        return this.gameStatus;
    }

    public void setPlayedShape(GameShape playedShape) {
        this.playedShape = playedShape;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    void setResults(Map<Integer, GameShape> results) {
        this.playersStatus = results;
        this.gameStatus = GameStatus.OVER;
    }

    void setWon() {
        this.gameStatus = GameStatus.WON;
    }

}
