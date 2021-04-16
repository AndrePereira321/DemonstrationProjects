package esi.atlg3.g51999.othello.model;

import java.util.Objects;

/**
 * One Piece of Othello has 2 sides: One black and another White, if one piece
 * is surrounded by 2 adverse Pieces from opposites sides (diagonal too), that
 * piece must change color. It also contains one value between [0 and 3].
 *
 * @author Andre Pereira
 */
public class Piece {

    private PlayerColor color;
    private int value;

    /**
     * Creates a new Piece with the given color.
     *
     * @param color The color for the Piece.
     * @param value The value of the piece, between [0, 3].
     * @exception IllegalArgumentException If the value is not between [0, 3].
     */
    Piece(PlayerColor color, int value) {
        if (value < 0 || value > 3) {
            throw new IllegalArgumentException(
                    "The value of the piece is not accepted! Value: " + value);
        }
        this.color = color;
        this.value = value;
    }

    /**
     * Retrieves the color of the Piece.
     *
     * @return The color of the Piece.
     */
    public PlayerColor getColor() {
        return color;
    }

    /**
     * Retrieves the value of the Piece.
     *
     * @return The value of the Piece.
     */
    public int getValue() {
        return value;
    }

    public void setBonusValue() {
        this.value += 3;
    }

    /**
     * Swaps the color of the Piece, white to black or black to white.
     *
     * @throws IllegalStateException If the color has not been defined or if we
     * add another color from another enum PlayerColor.
     */
    void swapColor() {
        if (this.color == null) {
            throw new IllegalStateException("The color has not been defined!");
        }
        switch (this.color) {
            case WHITE:
                this.color = PlayerColor.BLACK;
                break;
            case BLACK:
                this.color = PlayerColor.WHITE;
                break;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Piece)) {
            return false;
        }
        Piece other = (Piece) obj;
        return this.color == other.getColor() && this.value == other.getValue();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.color);
        hash = 79 * hash + this.value;
        return hash;
    }

    @Override
    public String toString() {
        return "Color: " + this.color + " Value: " + this.value;
    }

}
