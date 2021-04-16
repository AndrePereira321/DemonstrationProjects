package esi.atlg3.g51999.othello.controller.events;

import esi.atlg3.g51999.othello.controller.bot.Bot;
import esi.atlg3.g51999.othello.controller.bot.Difficult;
import esi.atlg3.g51999.othello.model.Game;
import esi.atlg3.g51999.othello.model.Model;
import esi.atlg3.g51999.othello.model.PlayerColor;
import java.util.HashMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.RadioButton;

/**
 * This EventHandler starts the game according with the user settings.
 *
 * @author Andre Pereira
 */
public class StartGameHandler implements EventHandler<ActionEvent> {

    private final Model model;
    private final HashMap<PlayerColor, String> names;
    private final RadioButton rbWithBot;

    /**
     * Creates a new StartGameHandler for handler the start of the game.
     *
     * @param model The game data.
     * @param names The names inputed by the players.
     * @param rbWithBot The node containing the bot chosen by the player.
     */
    public StartGameHandler(Model model, HashMap<PlayerColor, String> names,
            RadioButton rbWithBot) {
        this.model = model;
        this.names = names;
        this.rbWithBot = rbWithBot;
    }

    /**
     * By setting the players names in the model, the model will inform the view
     * that the game has started. If the player has chosen some bot options a
     * bot will be created observing the game and waiting for his turn to play.
     *
     * @param event Not used.
     */
    @Override
    public void handle(ActionEvent event) {
        model.setPlayersNames(names);
        if (this.rbWithBot.isSelected()) {
            Bot bot = new Bot(model, Difficult.EASY);
            ((Game) this.model).addObserver(bot);
        }
    }

}
