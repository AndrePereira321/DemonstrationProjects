package atl.server.g51999.model;

import atl.g51999.gameserverutils.users.User;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author andre
 */
public class Games {

    private final List<AbstractGame> games;

    public Games() {
        this.games = new ArrayList();
    }

    public void addGame(AbstractGame game) {
        this.games.add(game);
    }

    public List<AbstractGame> getGames() {
        return Collections.unmodifiableList(games);
    }
    
    public AbstractGame getGame(int gameID) {
        for (AbstractGame game : this.games) {
            if (game.getGameID() == gameID) {
                return game;
            }
        }
        return null;
    }

    public List<AbstractGame> removeGame(User player) {
        List<AbstractGame> canceledGames = new ArrayList();
        for (int i = 0; i < games.size(); i++) {
            if (games.get(i).getPlayers().contains(player.getId())) {
                canceledGames.add(games.get(i));
                games.remove(i);
            }
        }
        return canceledGames;
    }

    public void removeGame(int game) {
        for (int i = 0; i < games.size(); i++) {
            if (games.get(i).getGameID() == game) {
                games.remove(i);
                return;
            }
        }
    }

}
