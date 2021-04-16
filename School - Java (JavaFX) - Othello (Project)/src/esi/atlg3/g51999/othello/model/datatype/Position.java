package esi.atlg3.g51999.othello.model.datatype;

/**
 * This Data Class represents a Position in the Board. The upper left corner of
 * the Board is the Position (0, 0). And if we go left the column increase by
 * one, or if we go down, the row increase by one. The Othello positions where
 * the row starts at 1 and the columns are represented by letters are ignored in
 * the model.
 *
 * @author Andre Pereira
 */
public class Position {

    private final int row;
    private final int column;

    /**
     * Creates a new position with a row and column.
     *
     * @param row The row of the position.
     * @param column The column of the position.
     */
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Retrieves the row coordinate of this position.
     *
     * @return The row.
     */
    public int getRow() {
        return this.row;
    }

    /**
     * Retrieves the column coordinate of this position.
     *
     * @return The column.
     */
    public int getColumn() {
        return this.column;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Position) || obj == null) {
            return false;
        }
        Position otherPos = (Position) obj;
        return (this.row == otherPos.getRow()
                && this.column == otherPos.getColumn());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + this.row;
        hash = 43 * hash + this.column;
        return hash;
    }

    @Override
    public String toString() {
        char col = (char) (this.column + 65);
        return "( " + col + ", " + (this.row + 1) + " )";
    }

}
