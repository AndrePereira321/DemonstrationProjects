package esi.atlg3.g51999.othello.model;

import java.util.Collections;
import java.util.Objects;
import java.util.Stack;

/**
 * This class will represent a Player. We can distinguish players by his colour
 * and score. The score represents the number of Pieces in the Board.
 *
 * @author g51999
 */
public class Player {

    private final PlayerColor color;
    private String name;
    private final Stack<Piece> pieces;
    private int score;
    private int putedPiecesCounter;

    /**
     * Creates a new Player with a PlayerColor(Black or White) and 0 Pieces.
     *
     * @param color
     */
    Player(PlayerColor color) {
        if (color == null) {
            throw new IllegalArgumentException("The player must have one color!");
        }
        this.color = color;
        this.pieces = new Stack();
        this.score = 2;
        this.putedPiecesCounter = 2;
    }

    /**
     * Starts the pieces of the Player. The player has: 1 piece with value 0, 20
     * pieces with value 1, 10 pieces with value 2, and 1 piece with value 3.
     */
    void init() {
        this.pieces.push(new Piece(this.color, 0));
        for (int i = 0; i < 20; i++) { //Already 2 Pieces with value 1 at start of the board.
            this.pieces.push(new Piece(this.color, 1));
        }
        for (int i = 0; i < 10; i++) {
            this.pieces.push(new Piece(this.color, 2));
        }
        this.pieces.push(new Piece(this.color, 3));
        Collections.shuffle(this.pieces);
    }

    /**
     * Retrieves the color of the Player.
     *
     * @return The color of the Player.
     */
    public PlayerColor getColor() {
        return color;
    }

    public void addPutedPiece(int delta) {
        this.putedPiecesCounter += delta;
    }
    
    public int getPutedPieceCounter() {
        return this.putedPiecesCounter;
    }

    /**
     * Retrieves the score of the player, that represents the number of Pieces
     * in the Board.
     *
     * @return The score of the player.
     */
    int getScore() {
        return score;
    }

    /**
     * Sets a new name for the player and notify his observers.
     *
     * @param name The new name for the player.
     */
    void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the name of the Player.
     *
     * @return The name of the Player.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Increases the score with the given montant. If the montant is negative,
     * the score will decrease.
     *
     * @param delta The montant to increase the score.
     */
    void addScore(int delta) {
        this.score += delta;
    }

    /**
     * Plays a Piece with a random value.
     *
     * @return The piece to play.
     */
    public Piece playPiece() {
        if (this.pieces.isEmpty()) {
            return null;
        }
        return this.pieces.pop();
    }

    /**
     * Adds one piece to the player.
     *
     * @param piece The piece to be added.
     */
    void addPiece(Piece piece) {
        this.pieces.push(piece);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Player)) {
            return false;
        }
        Player other = (Player) obj;
        return this.color == other.color && this.score == other.score;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + Objects.hashCode(this.color);
        hash = 31 * hash + this.score;
        return hash;
    }

    @Override
    public String toString() {
        return "Player: " + this.color + " / Score: " + this.score;
    }

}
