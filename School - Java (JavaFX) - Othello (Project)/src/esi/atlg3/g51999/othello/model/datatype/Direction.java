package esi.atlg3.g51999.othello.model.datatype;

/**
 * This enum represents how must a position must deplace to go in a certain
 * Direction. If we add a certain Position to the position of this Direction
 * value, we obtain the next position of the Direction.
 *
 * @author Andre Pereira
 */
public enum Direction {
    UP(new Position(-1, 0)),
    UPPER_RIGHT(new Position(-1, 1)),
    RIGHT(new Position(0, 1)),
    DOWN_RIGHT(new Position(1, 1)),
    DOWN(new Position(1, 0)),
    DOWN_LEFT(new Position(1, -1)),
    LEFT(new Position(0, -1)),
    UPPER_LEFT(new Position(-1, -1));

    private final Position direction;

    /**
     * Creates a new Direction.
     *
     * @param direction The direction Position.
     */
    Direction(Position direction) {
        this.direction = direction;
    }

    /**
     * Creates a new position adding the coordinates.
     *
     * @param position The position to add.
     * @return The new position created.
     * @exception NullPointerException If the given position has null value.
     */
    public Position nextPos(Position position) {
        if (position == null) {
            throw new NullPointerException("The given position has no value!");
        }
        return new Position(this.direction.getRow() + position.getRow(),
                this.direction.getColumn() + position.getColumn());
    }

}
