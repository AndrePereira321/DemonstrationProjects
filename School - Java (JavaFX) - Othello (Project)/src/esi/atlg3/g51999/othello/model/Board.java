package esi.atlg3.g51999.othello.model;

import esi.atlg3.g51999.othello.model.datatype.Position;
import java.util.Arrays;
import java.util.Iterator;
import esi.atlg3.g51999.othello.utils.Configs;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class represents the Board of the game. The board is constitued of 8
 * lines and 8 columns. All the squares are equals, and they can contain one
 * Piece or not.
 *
 * @author Andre Pereira
 */
public class Board implements Iterable<Position> {

    private final Piece[][] board;
    private final List<Position> bonusCases;

    /**
     * Creates a new Board without Pieces.
     */
    Board() {
        this.board = new Piece[Configs.BOARD_SIZE][Configs.BOARD_SIZE];
        this.bonusCases = new ArrayList();
    }

    /**
     * Initializes the Board according with the Othello rules.
     */
    void init() {
        this.put(new Position(4, 3), new Piece(PlayerColor.BLACK, 1));
        this.put(new Position(3, 4), new Piece(PlayerColor.BLACK, 1));
        this.put(new Position(4, 4), new Piece(PlayerColor.WHITE, 1));
        this.put(new Position(3, 3), new Piece(PlayerColor.WHITE, 1));
        for (int i = 0; i < 3; ++i) {
            this.bonusCases.add(this.getRandomPosition());
        }
    }

    public List<Position> getBonusPositions() {
        return this.bonusCases;
    }

    /**
     * Retrieves the Square in the given Position.
     *
     * @param pos The position of the Square.
     * @return The Square at the given Position.
     * @exception IllegalArgumentException If the position is out bounds.
     */
    public Piece getPiece(Position pos) {
        if (!this.isInside(pos)) {
            throw new IllegalArgumentException("The given position is out bounds!");
        }
        return this.board[pos.getRow()][pos.getColumn()];
    }

    /**
     * Verifies if the the square at the given Position is empty.
     *
     * @param pos The position of the Square.
     * @return True if the square have no Piece.
     */
    public boolean isEmpty(Position pos) {
        if (!this.isInside(pos)) {
            throw new IllegalArgumentException("The given position is out bounds!");
        }
        return this.board[pos.getRow()][pos.getColumn()] == null;
    }

    /**
     * Puts one piece in the Square. After putting the piece the piece can't be
     * removed.
     *
     * @param pos The position too put the piece.
     * @param piece The piece to put.
     * @exception NullPointerException If the Piece value is null.
     * @exception IllegalArgumentException If the Position is out bounds.
     */
    void put(Position pos, Piece piece) {
        if (!this.isInside(pos)) {
            throw new IllegalArgumentException("The given position is out bounds!");
        } else if (piece == null) {
            throw new NullPointerException("You can't empty a Square!");
        }
        if (this.bonusCases.contains(pos)) {
            piece.setBonusValue();
        }
        this.board[pos.getRow()][pos.getColumn()] = piece;
    }

    /**
     * Verifies if the given position is inside of the Board.
     *
     * @param pos The position to check.
     * @return True if it is inside of the Board.
     */
    public boolean isInside(Position pos) {
        return pos.getRow() >= 0 && pos.getColumn() >= 0
                && pos.getRow() < Configs.BOARD_SIZE
                && pos.getColumn() < Configs.BOARD_SIZE;
    }

    private Position getRandomPosition() {
        Position pos = new Position(
                (int) (Math.random() * Configs.BOARD_SIZE - 1), (int) (Math.random() * Configs.BOARD_SIZE - 1));
        if (this.isEmpty(pos)) {
            return pos;
        } else {
            return this.getRandomPosition();
        }
    }

    /**
     * Iterates over the board retrieving all the positions inside of the Board.
     * It iterates the Positions to distinguish the differents rows and columns.
     *
     * @return An iterator who allows to iterate all the Positions of the Board.
     */
    @Override
    public Iterator<Position> iterator() {
        return new Iterator<Position>() {

            private int row = 0;
            private int column = 0;

            @Override
            public boolean hasNext() {
                return row <= Configs.BOARD_SIZE - 1 && column <= Configs.BOARD_SIZE - 1;
            }

            @Override
            public Position next() {
                Position pos = new Position(row, column);
                column = (column + 1) % Configs.BOARD_SIZE;
                if (column == 0) {
                    row++;
                }
                return pos;
            }
        };

    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Board)) {
            return false;
        }
        Board other = (Board) obj;
        return Arrays.deepEquals(this.board, other.board);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Arrays.deepHashCode(this.board);
        return hash;
    }

    @Override
    public String toString() {
        return "Board: " + Configs.BOARD_SIZE + "x" + Configs.BOARD_SIZE;
    }

}
