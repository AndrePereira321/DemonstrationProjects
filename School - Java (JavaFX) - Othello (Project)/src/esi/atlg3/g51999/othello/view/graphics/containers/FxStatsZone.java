package esi.atlg3.g51999.othello.view.graphics.containers;

import esi.atlg3.g51999.othello.model.Model;
import esi.atlg3.g51999.othello.model.PlayerColor;
import esi.atlg3.g51999.othello.model.datatype.ActionDetail;
import esi.atlg3.g51999.othello.view.graphics.composants.FxHistory;
import esi.atlg3.g51999.othello.view.graphics.composants.FxPlayerScoreZone;
import java.util.HashMap;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * The Stats Zone will be constantly displaying the game status, like the
 * players scores and the history log of the actions.
 *
 * @author Andre Pereira
 */
public class FxStatsZone extends VBox {

    private final HBox scoreZone;
    private final FxPlayerScoreZone blackPlayerScoreZone;
    private final FxPlayerScoreZone whitePlayerScoreZone;
    private final FxHistory historyZone;

    /**
     * Creates the zone for displaying the game stats.
     *
     * @param model To init the score zones.
     */
    public FxStatsZone(Model model) {
        super(10);
        this.scoreZone = new HBox(10);
        this.blackPlayerScoreZone = new FxPlayerScoreZone(PlayerColor.BLACK, model);
        this.whitePlayerScoreZone = new FxPlayerScoreZone(PlayerColor.WHITE, model);
        this.historyZone = new FxHistory();
    }

    /**
     * Initializates the player score zone, and the history log zone.
     */
    public void initStatsZone() {
        Label titleLabel = new Label("Stats Zone!");
        titleLabel.getStyleClass().add("titleLabel");
        super.setAlignment(Pos.CENTER);
        this.scoreZone.setAlignment(Pos.CENTER);

        this.blackPlayerScoreZone.initFxScoreZone();
        this.whitePlayerScoreZone.initFxScoreZone();
        this.historyZone.initFxHistory();

        this.scoreZone.getChildren().addAll(blackPlayerScoreZone, whitePlayerScoreZone);
        super.getChildren().addAll(titleLabel, scoreZone, historyZone);
        super.getStyleClass().add("statsZone");
    }

    /**
     * Set the new score of the both players.
     *
     * @param scores An HashMap containing the score for the WHITE and BLACK
     * player.
     */
    public void setScores(HashMap<PlayerColor, Integer> scores) {
        this.blackPlayerScoreZone.setScore(scores.get(PlayerColor.BLACK));
        this.whitePlayerScoreZone.setScore(scores.get(PlayerColor.WHITE));
    }

    /**
     * Set the names of the players.
     *
     * @param names An HashMap containing the names for the WHITE and BLACK
     * player.
     */
    public void setNames(HashMap<PlayerColor, String> names) {
        this.blackPlayerScoreZone.setName(names.get(PlayerColor.BLACK));
        this.whitePlayerScoreZone.setName(names.get(PlayerColor.WHITE));
    }

    /**
     * Adds a new game action to the history log.
     *
     * @param action An action to be added to the log.
     */
    public void addOthelloAction(ActionDetail action) {
        this.historyZone.addOtthelloAction(action);
    }

}
