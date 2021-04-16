package esi.atlg3.g51999.othello;

import esi.atlg3.g51999.othello.controller.ConsoleController;
import esi.atlg3.g51999.othello.controller.GraphicsController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Othello game is played on a Board of 64 boxes. We are referring to the boxes
 * thanks to a coordinate system: the lines numbered from top to bottom, from 1
 * to 8, the columns are indexed from left to right, from 'a' to 'h'. The two
 * players have 32 two-tone pawns, black on one side and white on the other.
 * Every Piece has one value, 0, 1, 2 or 3. The game objectif is to end the game
 * with the most elevated score.
 *
 * @author Andre Pereira
 */
public class Main extends Application {

    /**
     * The main entry for the game.
     *
     * @param args Allows to define if the game will be launched in console or
     * application mode.
     */
    public static void main(String[] args) {
        if (args.length != 0) {
            if (args[0].equals("-c") || args[0].equals("--console")) {
                //Launch othello in console.
                ConsoleController othello = new ConsoleController();
                othello.intialize();
                while (othello.gameIsActive()) {
                    othello.executeCommand();
                }
                othello.endGame();
                System.exit(0);
            } else {
                System.err.println("Invalid Argument: " + args[0]);
                launch(args);
            }
        } else {
            launch();
        }
    }

    /**
     * Starts the game with JavaFX. It will use the same Model than console, but
     * a different View and a little facade for the Controller.
     *
     * @param primaryStage The main stage for the app.
     * @throws Exception Avoids to shut the main window down.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        GraphicsController othello = new GraphicsController();
        Scene scene = new Scene(othello.getRoot(), 1000, 750);

        othello.intialize();
        othello.associateObservers();
        othello.defineEvents(scene);

        scene.getStylesheets().add("style.css");

        primaryStage.setTitle("Othello");
        try {
            primaryStage.getIcons().add(new Image("imgs\\icon.png"));
        } catch (Exception e) {
        }
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(false);
        primaryStage.setMaximized(false);
        primaryStage.setMaxWidth(1000);
        primaryStage.setMaxHeight(750);
        primaryStage.show();
    }
}
