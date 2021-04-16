package esi.atlg3.g51999.othello.view.graphics.composants;

import esi.atlg3.g51999.othello.model.datatype.ActionDetail;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

/**
 * This class represents the History List displaying all the Actions in Othello.
 * For displaying that list, it's used a TableView to contain all the
 * OthelloActions.
 *
 * @author Andre Pereira
 */
public class FxHistory extends VBox {

    private final TableView history;
    private final ObservableList<ActionDetail> data;

    /**
     * Creates the table for displaying the actions, and allows to save the
     * actions.
     */
    public FxHistory() {
        super();
        this.history = new TableView();
        this.data = FXCollections.observableArrayList();
    }

    /**
     * Initializates the FxHistory zone.
     */
    public void initFxHistory() {
        super.getChildren().add(history);
        this.initTable();
    }

    /**
     * Create the columns for the table, and associates the Table with the data
     * structure of the Action.
     */
    private void initTable() {
        TableColumn countColumn = new TableColumn("ActionID");
        countColumn.setCellValueFactory(
                new PropertyValueFactory<ActionDetail, String>("count"));

        TableColumn playerNameCol = new TableColumn("Player Name");
        playerNameCol.setCellValueFactory(
                new PropertyValueFactory<ActionDetail, String>("playerName"));

        TableColumn actionCol = new TableColumn("Action");
        actionCol.setCellValueFactory(
                new PropertyValueFactory<ActionDetail, String>("action"));

        TableColumn positionCol = new TableColumn("Position");
        positionCol.setCellValueFactory(
                new PropertyValueFactory<ActionDetail, String>("position"));

        TableColumn prisesCol = new TableColumn("Prises");
        prisesCol.setCellValueFactory(
                new PropertyValueFactory<ActionDetail, String>("prises"));

        this.history.getColumns().addAll(countColumn, playerNameCol, actionCol, positionCol, prisesCol);
        this.history.setItems(data);
    }

    /**
     * Adds an action to the Table.
     *
     * @param action A structure containing each field for the Table.
     */
    public void addOtthelloAction(ActionDetail action) {
        this.data.add(action);
    }
}
