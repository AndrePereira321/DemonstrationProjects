package esi.atlg3.g51999.othello.controller.bot;

import esi.atlg3.g51999.othello.model.Model;
import esi.atlg3.g51999.othello.model.datatype.Position;

/**
 * The RandomPlayStrategy will auto retrieve a random position from the
 * available puts position.
 *
 * @author Andre Pereira
 */
public class RandomPlayStrategy implements AbstractPlayStrategy {

    private final Model model;

    /**
     * Creates a new random play strategy.
     *
     * @param model The model of the game.
     */
    public RandomPlayStrategy(Model model) {
        this.model = model;
    }

    /**
     * Retrieves a random position from the available puts position.
     *
     * @return A random position from the available puts position.
     */
    @Override
    public Position getPlayPosition() {
        return model.getCurrentAvailablePuts().get(randomIndex(model.getCurrentAvailablePuts().size()));
    }

    /**
     * Retrieves a random index from a collection size.
     *
     * @param size The size of the collection.
     * @return A random index between [0, size - 1].
     */
    private static int randomIndex(int size) {
        return (int) (Math.random() * size);
    }

}
