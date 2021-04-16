package esi.atlg3.g51999.othello.view.graphics.windows;

import esi.atlg3.g51999.othello.model.Model;
import esi.atlg3.g51999.othello.model.PlayerColor;
import esi.atlg3.g51999.othello.view.graphics.Observer;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 *
 * @author Andre Pereira
 */
public class InfoWindow extends Stage implements Observer {

    private final Model model;

    private final Scene infoScene;

    private final HBox infoRoot;

    private final GridPane blackPlayerZone;
    private final GridPane whitePlayerZone;

    private final Label blackPlayerScore;
    private final Label whitePlayerScore;

    private final Label whitePieceCounter;
    private final Label blackPieceCounter;

    public InfoWindow(Model model) {
        this.model = model;
        this.infoRoot = new HBox(8);
        this.infoScene = new Scene(this.infoRoot);

        this.blackPlayerZone = createPlayerZone();
        this.whitePlayerZone = createPlayerZone();

        this.blackPieceCounter = new Label();
        this.whitePieceCounter = new Label();
        this.blackPlayerScore = new Label();
        this.whitePlayerScore = new Label();
    }

    private GridPane createPlayerZone() {
        GridPane playerZone = new GridPane();
        Label lblScore = new Label("Score:");
        Label lblPieces = new Label("Pieces:");
        Label lblName = new Label("Name:");
        playerZone.add(lblName, 0, 0);
        playerZone.add(lblScore, 0, 1);
        playerZone.add(lblPieces, 0, 2);
        return playerZone;
    }

    public void initInfoWindow() {
        this.infoRoot.setPadding(new Insets(8));
        this.initPlayerZones();

        this.infoRoot.getChildren().addAll(this.blackPlayerZone, this.whitePlayerZone);
       
        this.infoScene.getStylesheets().add("style.css");
        this.infoRoot.getStyleClass().add("playing");
        this.blackPlayerZone.getStyleClass().addAll("playerZone", "blackPlayerZone");
        this.whitePlayerZone.getStyleClass().addAll("playerZone", "whitePlayerZone");

        this.setScene(this.infoScene);
        this.update(null);
    }

    private void initPlayerZones() {
        this.blackPlayerZone.add(this.blackPieceCounter, 1, 2);
        this.blackPlayerZone.add(this.blackPlayerScore, 1, 1);
        this.whitePlayerZone.add(this.whitePieceCounter, 1, 2);
        this.whitePlayerZone.add(this.whitePlayerScore, 1, 1);
        this.blackPlayerZone.add(new Label(model.getPlayersNames().get(PlayerColor.BLACK)), 1, 0);
        this.whitePlayerZone.add(new Label(model.getPlayersNames().get(PlayerColor.WHITE)), 1, 0);

    }

    @Override
    public void update(Object arg) {
        this.blackPieceCounter.setText("" + this.model.getPlayerPieceCounter().get(PlayerColor.BLACK));
        this.whitePieceCounter.setText("" + this.model.getPlayerPieceCounter().get(PlayerColor.WHITE));
        this.blackPlayerScore.setText("" + this.model.getScores().get(PlayerColor.BLACK));
        this.whitePlayerScore.setText(("" + this.model.getScores().get(PlayerColor.WHITE)));
    }

}
