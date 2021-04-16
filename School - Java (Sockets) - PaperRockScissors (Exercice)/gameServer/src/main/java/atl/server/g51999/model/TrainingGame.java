package atl.server.g51999.model;

import atl.g51999.gameserverutils.model.GameShape;
import atl.g51999.gameserverutils.model.GameType;
import static atl.g51999.gameserverutils.model.GameShape.*;

/**
 *
 * @author andre
 */
public class TrainingGame extends AbstractGame {

    private final int player;

    public TrainingGame(int player, int gameID) {
        super(GameType.TRAINING, gameID, player, 0);
        this.player = player;
        super.players.put(0, getRandomShape());
    }

    @Override
    public int getWinner() throws GameException {
        if (!super.isOver()) {
            throw new GameException("The game isn't over!");
        }
        GameShape playerShape = super.players.get(this.player);
        GameShape serverShape = super.players.get(0);
        if (playerShape == serverShape) {
            return -1;
        } else if ((playerShape.getValue() + 1) % 3 == serverShape.getValue()) {
            return this.player;
        }
        return 0;
    }

}
