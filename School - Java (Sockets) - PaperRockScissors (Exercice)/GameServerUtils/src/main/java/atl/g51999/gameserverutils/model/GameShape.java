package atl.g51999.gameserverutils.model;

import java.io.Serializable;

/**
 * This enum represents the different shapes of the game.
 *
 * @author g51999
 */
public enum GameShape implements Serializable {
    PAPER(0), ROCK(1), SCISSORS(2);

    private final int value;

    GameShape(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    /**
     * Retrieves a random Shape between all shapes.
     *
     * @return A random Shape.
     */
    public static GameShape getRandomShape() {
        return values()[(int) (Math.random() * values().length)];
    }
}
