/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class Games {

    private final HashMap<Integer, GameInfo> games;

    public Games() {
        this.games = new HashMap(10);
    }

    public void addGame(int gameID, GameType gameType, Set<Integer> players) {
        this.games.put(gameID, new GameInfo(gameType, players));
    }

    public Set<Integer> getGames() {
        return this.games.keySet();
    }

    public void playShape(int gameID, GameShape shape) {
        GameInfo info = this.games.get(gameID);
        if (info != null) {
            throw new IllegalStateException("Invalid Game ID!");
        }
        if (info.getGameStatus() != GameStatus.NOT_PLAYED) {
            throw new IllegalStateException("You already played for this game!");
        }
        info.setPlayedShape(shape);
        info.setGameStatus(GameStatus.PLAYED);
    }

    public Set<Integer> getPlayers(int gameID) {
        return this.games.get(gameID).getOpponents();
    }

    public Map<Integer, GameShape> getResults(int gameID) {
        return this.games.get(gameID).getResults();
    }

    public void setResults(int gameID, Map<Integer, GameShape> results) {
        this.games.get(gameID).setResults(results);
    }

    public GameType getGameType(int gameID) {
        return this.games.get(gameID).getGameType();
    }

    public GameShape getPlayedShape(int gameID) {
        return this.games.get(gameID).getPlayedShape();
    }

    public GameStatus getGameStatus(int gameID) {
        return this.games.get(gameID).getGameStatus();
    }

    public void setWon(int gameID) {
        this.games.get(gameID).setWon();
    }

    public HashMap<Integer, GameInfo> getRunningGames() {
        HashMap<Integer, GameInfo> runningGames = new HashMap();
        for (int gameID : this.games.keySet()) {
            if (this.games.get(gameID).getGameStatus() == GameStatus.NOT_PLAYED) {
                runningGames.put(gameID, games.get(gameID));
            }
        }
        return runningGames;
    }

    public HashMap<Integer, GameInfo> getFinishedGames() {
        HashMap<Integer, GameInfo> runningGames = new HashMap();
        for (int gameID : this.games.keySet()) {
            if (this.games.get(gameID).getGameStatus() == GameStatus.OVER
                    || this.games.get(gameID).getGameStatus() == GameStatus.WON) {
                runningGames.put(gameID, games.get(gameID));
            }
        }
        return runningGames;
    }
}
