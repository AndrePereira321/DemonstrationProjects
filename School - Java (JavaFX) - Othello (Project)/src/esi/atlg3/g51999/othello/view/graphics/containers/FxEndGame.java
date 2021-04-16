package esi.atlg3.g51999.othello.view.graphics.containers;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * This layout will display a congratulations message to the winner of the game.
 *
 * @author Andre Pereira
 */
public class FxEndGame extends BorderPane {

    private final Label winMessage;

    private final HBox buttonZone;
    private final Button btQuit;

    private final StackPane centerImage;

    /**
     * Creates all the zones for the layout.
     */
    public FxEndGame() {
        super();
        this.buttonZone = new HBox(15);
        this.winMessage = new Label();
        this.btQuit = new Button("Quit");
        this.centerImage = new StackPane();
    }

    /**
     * Initializates the texts and the styles.
     */
    public void initEndGameUI() {
        VBox top = new VBox(8);

        Label title = new Label("The game is Over!");
        btQuit.setOnAction(event -> System.exit(0));

        title.getStyleClass().add("titleLabel");
        this.centerImage.getStyleClass().add("endGameGif");
        top.getStyleClass().add("statsZone");
        this.buttonZone.getStyleClass().add("statsZone");
        top.setAlignment(Pos.CENTER);
        this.buttonZone.setAlignment(Pos.CENTER);

        this.centerImage.setPrefSize(500, 500);
        this.centerImage.setMinSize(500, 500);
        this.centerImage.setMaxSize(500, 500);

        top.getChildren().addAll(title, this.winMessage);
        this.buttonZone.getChildren().addAll(btQuit);

        super.setTop(top);
        super.setCenter(this.centerImage);
        super.setBottom(buttonZone);
    }

    /**
     * Sets the winner message for the winner of the game.
     *
     * @param playerName The name of the winner.
     */
    public void setWinner(String playerName) {
        this.winMessage.setText("The winner is: " + playerName);
    }

    /**
     * The button to restart the game.
     *
     * @param button The button to be added.
     */
    public void addRestartButton(Button button) {
        try {
            this.buttonZone.getChildren().add(button);
        } catch (Exception e) {
            //Dont allow to add a second button if the 
            //game has already been restarted.
        }
    }
}
