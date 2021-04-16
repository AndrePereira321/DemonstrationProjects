package esi.atlg3.g51999.othello.view.graphics.containers;

import esi.atlg3.g51999.othello.controller.events.StartGameHandler;
import esi.atlg3.g51999.othello.model.Model;
import esi.atlg3.g51999.othello.model.PlayerColor;
import java.util.HashMap;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

/**
 * This class represents the start layout when lauching the app. It will contain
 * some text input to read the name of the two players and a button to start the
 * game setting the players names.
 *
 * @author Andre Pereira
 */
public class FxStartMenu extends GridPane {

    private final TextField tfPlayer1Name;
    private final TextField tfPlayer2Name;
    private final RadioButton rbWantBot;
    private final Button btStartGame;

    /**
     * Creates the zones for the start menu.
     */
    public FxStartMenu() {
        super();
        this.tfPlayer1Name = new TextField("Player 1");
        this.tfPlayer2Name = new TextField("Player 2");
        this.btStartGame = new Button("Start Game!");
        this.rbWantBot = new RadioButton("Single Player (With Bot)");
    }

    /**
     * Initializes the styles of the zone.
     *
     * @param model The model to set the events for the buttons.
     */
    public void initStartMenu(Model model) {
        super.setAlignment(Pos.CENTER);
        super.setVgap(14);
        super.setHgap(30);
        super.getStyleClass().add("startMenuGrid");

        this.createHeader();
        this.createPlayer1Zone();
        this.createPlayer2Zone();
        this.initStartGameButton(model);
    }

    /**
     * Retrieves the names inputed by the players.
     *
     * @return The names inputed by the players.
     */
    public HashMap<PlayerColor, String> getPlayersNames() {
        HashMap<PlayerColor, String> names = new HashMap();
        names.put(PlayerColor.BLACK, tfPlayer1Name.getText());
        names.put(PlayerColor.WHITE, tfPlayer2Name.getText());
        return names;
    }

    /**
     * Initializes the header title.
     */
    private void createHeader() {
        Label title = new Label("Othello - Main Menu");
        title.setPadding(new Insets(5));
        title.getStyleClass().add("titleLabel");
        this.add(title, 0, 0, 1, 1);
        GridPane.setColumnSpan(title, 2);
        GridPane.setHalignment(title, HPos.CENTER);
    }

    /**
     * Initializes the first row for the player 1.
     */
    private void createPlayer1Zone() {
        Label lblPlayer1Name = new Label("Player 1 Name: ");
        lblPlayer1Name.setFont(Font.font("Cambria", 14));
        this.add(lblPlayer1Name, 0, 3);
        this.add(tfPlayer1Name, 1, 3);
    }

    /**
     * Initializes the second row for the player 2.
     */
    private void createPlayer2Zone() {
        Label lblPlayer2Name = new Label("Player 2 Name: ");
        lblPlayer2Name.setFont(Font.font("Cambria", 14));
        this.add(lblPlayer2Name, 0, 4);
        this.add(tfPlayer2Name, 1, 4);
        this.add(rbWantBot, 0, 5, 1, 1);
        GridPane.setColumnSpan(rbWantBot, 2);
        GridPane.setHalignment(rbWantBot, HPos.CENTER);
    }

    /**
     * Initializes the start game button.
     *
     * @param model The model to set the event of start game.
     */
    private void initStartGameButton(Model model) {
        this.add(this.btStartGame, 0, 6);
        btStartGame.setPadding(new Insets(5));
        btStartGame.setOnAction(new StartGameHandler(model, this.getPlayersNames(),
                this.rbWantBot));
        GridPane.setColumnSpan(this.btStartGame, 2);
        GridPane.setHalignment(this.btStartGame, HPos.CENTER);
    }

}
