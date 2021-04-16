package esi.atlg3.g51999.othello.view.graphics;

import esi.atlg3.g51999.othello.controller.events.GameInfoWindowHandler;
import esi.atlg3.g51999.othello.model.Model;
import esi.atlg3.g51999.othello.model.PlayerColor;
import esi.atlg3.g51999.othello.model.datatype.ActionDetail;
import esi.atlg3.g51999.othello.model.datatype.PutData;
import esi.atlg3.g51999.othello.view.graphics.containers.FxEndGame;
import esi.atlg3.g51999.othello.view.graphics.containers.FxPlayZone;
import esi.atlg3.g51999.othello.view.graphics.containers.FxStartMenu;
import esi.atlg3.g51999.othello.view.graphics.containers.FxStatsZone;
import java.util.HashMap;
import javafx.animation.FadeTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * This class represents the View facade for the display with JavaFX. The root
 * attribute will contain the parents, that will be displayed in the primary
 * stage.
 *
 * @author Andre Pereira
 */
public class ViewUI implements Observer {

    private final Model model;

    // The root for the primary stage.
    private final VBox root;
    private final HBox mainContainer;

    private final MenuItem menuInfos;
    private final MenuBar menuBar;

    private final FxStartMenu startMenu;

    private final FxPlayZone playZone;
    private final FxStatsZone statsZone;

    private final FxEndGame endGameUI;

    private final FadeTransition fadeTransition;

    /**
     * Creates a new facade for the view using JavaFX.
     *
     * @param model The model to read information about the game status.
     */
    public ViewUI(Model model) {
        this.model = model;

        this.root = new VBox();
        this.mainContainer = new HBox(15);

        this.playZone = new FxPlayZone();
        this.statsZone = new FxStatsZone(model);
        this.startMenu = new FxStartMenu();
        this.endGameUI = new FxEndGame();
        this.fadeTransition = new FadeTransition(Duration.millis(2000), this.mainContainer);

        this.menuInfos = new MenuItem("Game Info");
        this.menuBar = new MenuBar();
    }

    /**
     * Initializes all the attributes of the View and sets their styles.
     */
    public void intialize() {
        this.mainContainer.setAlignment(Pos.CENTER);
        this.mainContainer.setPadding(new Insets(15));
        this.fadeTransition.setFromValue(0.0);
        this.fadeTransition.setToValue(1.0);

        this.initMenu();

        this.startMenu.initStartMenu(model);
        this.playZone.initFxPlayZone(model);
        this.statsZone.initStatsZone();
        this.endGameUI.initEndGameUI();

        this.root.getChildren().add(this.mainContainer);
        VBox.setVgrow(this.mainContainer, Priority.ALWAYS);
        this.mainContainer.getChildren().add(startMenu);
        this.mainContainer.getStyleClass().add("startMenu");
    }

    private void initMenu() {
        Menu gameMenu = new Menu("Game");
        gameMenu.getItems().add(this.menuInfos);
        menuBar.getMenus().add(gameMenu);
        this.menuInfos.setOnAction(new GameInfoWindowHandler(this.model));
    }

    /**
     * Retrieves the root node for the primary stage.
     *
     * @return The root node.
     */
    public VBox getRoot() {
        return this.root;
    }

    /**
     * Retrieves the restart button to correctly restart a new game.
     *
     * @return The restart button.
     */
    public Button getRestartButton() {
        return this.playZone.getRestartButton();
    }

    /**
     * Inherited from Observer, this method updates the display when the game
     * status is changed by the Controller. It will update the content depending
     * of what is passed in argument. For now, only updates in case of an
     * ActionDetail structure that describes the action realised in the Model.
     *
     * @param arg An argument informing the update type in the Model.
     */
    @Override
    public void update(Object arg) {
        if (arg != null) {
            if (arg instanceof ActionDetail) {
                ActionDetail detail = (ActionDetail) arg;
                this.statsZone.addOthelloAction(detail);
                switch (detail.getOthelloAction()) {
                    case START_GAME:
                        this.initGame(this.startMenu.getPlayersNames());
                        break;
                    case PASS_TURN:
                        this.refreshScoreZone();
                        this.playZone.getFxBoard().refreshAvailablePuts(model.getCurrentAvailablePuts());
                        break;
                    case PLAY_PIECE:
                        this.refreshAfterPutPiece(detail.getPutData());
                        this.refreshScoreZone();
                        break;
                    case WIN_GAME:
                        this.endGame();
                        break;
                }
            }
        }
    }

    /**
     * Removes the start menu zone and inserts the play zone. Updates the name
     * of the players.
     *
     * @param names The names of the players to be displayed in the stats zone.
     */
    private void initGame(HashMap<PlayerColor, String> names) {
        this.root.getChildren().clear();
        this.root.getChildren().addAll(menuBar, mainContainer);
        this.statsZone.setNames(names);
        this.mainContainer.getChildren().clear();
        this.mainContainer.getChildren().addAll(this.playZone, this.statsZone);
        this.mainContainer.getStyleClass().remove("startMenu");
        this.mainContainer.getStyleClass().add("playing");
        this.fadeTransition.play();
    }

    /**
     * Refresh the board when one player plays one piece in the board.
     *
     * @param putData A structure containing informations of the put action.
     */
    private void refreshAfterPutPiece(PutData putData) {
        this.playZone.setGameProgress(model.getPieceCount());
        this.playZone.putPieceFX(putData.getPutPosition(), putData.getPutPiece());
        this.playZone.getFxBoard().refreshAvailablePuts(model.getCurrentAvailablePuts());
        this.playZone.getFxBoard().rotatePieces(putData.getPiecesToTurn());
    }

    /**
     * Refreshes all the elements displaying the score, and the style for the
     * current player in the stats zone.
     */
    private void refreshScoreZone() {
        HashMap<PlayerColor, Integer> scores = this.model.getScores();
        this.statsZone.setScores(scores);
        this.playZone.setScorePercentage(scores);
        this.playZone.setGameProgress(this.model.getPieceCount());
    }

    /**
     * Removes the play zone and displays a message for the winner. Gives the
     * possibility to restart the game with the same players or quit the app.
     */
    private void endGame() {
        this.root.getChildren().clear();
        this.root.getChildren().addAll(mainContainer);
        this.endGameUI.addRestartButton(this.getRestartButton());
        this.mainContainer.getChildren().clear();
        this.mainContainer.getChildren().add(endGameUI);
        this.endGameUI.setWinner(model.getWinner().getName());
        this.fadeTransition.play();
    }
}
