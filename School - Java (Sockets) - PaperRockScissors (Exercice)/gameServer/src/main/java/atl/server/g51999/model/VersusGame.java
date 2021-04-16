package atl.server.g51999.model;

import atl.g51999.gameserverutils.model.GameShape;
import atl.g51999.gameserverutils.model.GameType;
import java.util.Set;

/**
 *
 * @author andre
 */
public class VersusGame extends AbstractGame {

    private final int player1;
    private final int player2;

    public VersusGame(int gameID, int player1, int player2) {
        super(GameType.VERSUS, gameID, player1, player2);
        this.player1 = player1;
        this.player2 = player2;
    }

    @Override
    public int getWinner() throws GameException {
        if (!this.isOver()) {
            throw new GameException("The game isn't over!");
        }
        GameShape playerShape = super.players.get(this.player1);
        GameShape serverShape = super.players.get(this.player2);
        if (playerShape == serverShape) {
            return -1;
        } else if ((playerShape.getValue() + 1) % 3 == serverShape.getValue()) {
            return this.player1;
        }
        return this.player2;
    }

}
