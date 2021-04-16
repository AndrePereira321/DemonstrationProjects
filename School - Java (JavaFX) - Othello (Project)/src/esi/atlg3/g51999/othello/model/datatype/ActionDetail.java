package esi.atlg3.g51999.othello.model.datatype;

import esi.atlg3.g51999.othello.model.Player;
import javafx.beans.property.SimpleStringProperty;

/**
 * An ActionDetail is a structure that will contain some properties that will
 * describe the Othello Game Actions, like play one Piece. Its used to display
 * the history log in View.
 *
 * @author Andre Pereira
 */
public class ActionDetail {

    private final SimpleStringProperty count;
    private final SimpleStringProperty playerName;
    private final SimpleStringProperty action;
    private final SimpleStringProperty position;
    private final SimpleStringProperty prises;

    private final OthelloAction othelloAction;
    private final PutData putData;

    private static int staticCounter = 0;

    /**
     * Creates a new OthelloAction.
     *
     * @param action The categorie of the Action.
     * @param player The player who realised the Action.
     * @param putData A detailed information of the action if its a PLAY_PIECE
     * action.
     */
    public ActionDetail(OthelloAction action, Player player, PutData putData) {

        this.putData = putData;
        this.othelloAction = action;

        //Inits the attributes for the TableView
        this.count = new SimpleStringProperty("" + staticCounter);
        this.playerName = new SimpleStringProperty(player.getName());
        this.action = new SimpleStringProperty(action.getActionDescription());
        this.position = new SimpleStringProperty(
                (putData == null) ? "" : putData.getPutPosition().toString());
        this.prises = new SimpleStringProperty((putData == null) ? ""
                : "" + putData.getPiecesToTurn().size());

        ++staticCounter;
    }

    /**
     * Retrieves the action number.
     *
     * @return The action number.
     */
    public String getCount() {
        return this.count.get();
    }

    /**
     * Retrieves the player name.
     *
     * @return The name of the player.
     */
    public String getPlayerName() {
        return playerName.get();
    }

    /**
     * Retrieves the action description.
     *
     * @return The action description.
     */
    public String getAction() {
        return action.get();
    }

    /**
     * Retrieves the position of the putted piece.
     *
     * @return The position of the putted piece.
     */
    public String getPosition() {
        return position.get();
    }

    /**
     * The number of taken Pieces.
     *
     * @return The number of taken Pieces.
     */
    public String getPrises() {
        return prises.get();
    }

    /**
     * Retrieves the action categorie.
     *
     * @return Retrieves the action categorie.
     */
    public OthelloAction getOthelloAction() {
        return othelloAction;
    }

    /**
     * Retrieves the detailed information about a put.
     *
     * @return The information about a put.
     */
    public PutData getPutData() {
        return putData;
    }

}
