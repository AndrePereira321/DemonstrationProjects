#ifndef _POSITION_H
#define _POSITION_H

#include <string>
#include <ostream>

namespace dblk
{

/**
 * @brief The Position class
 *
 * This class will represent the coordinates to manipulate the board.
 */
class Position
{

  private:
    /**
     * @brief row
     *
     * The row, so the index for the first vector in the Board.
     */
    int row_;

    /**
     * @brief column
     *
     * The column, so the index for the second vector in the Board.
     */
    int column_;

  public:
    /**
     * @brief Position
     *
     * Creates a new Position.
     *
     * @param row The row coordinate.
     * @param column The column coordinate.
     */
    Position(int row, int column);

    /**
     * @brief getRow
     *
     * Retrieves the row of the position.
     *
     * @return The row of the position.
     */
    int getRow() const;

    /**
     * @brief getColumn
     *
     * Retrieves the row of the position.
     *
     * @return The row of the position.
     */
    int getColumn() const;

    /**
     * @brief to_string
     *
     * Creates a string describing the position in form of
     * ( row, column )
     *
     * @return A string describing the position.
     */
    std::string to_string() const;

    /**
     * @brief operator -
     *
     * Subtrack 2 positions.
     *
     * @param otherPos The right operand.
     * @return The difference between the 2 Positons.
     */
    Position operator-(const Position & otherPos) const;

    /**
     * @brief operator +
     *
     * Adds 2 positions.
     *
     * @param otherPos The right operand.
     * @return The sum between the 2 Positons.
     */
    Position operator+(const Position & otherPos) const;

    /**
     * @brief operator !=
     *
     * Verify if two positions are different. Must have different column
     * or different row.
     *
     * @param otherPos The right position of the operator.
     * @return True if they have the same row and column.
     */
    bool operator!=(const Position & otherPos) const;

    /**
     * @brief operator ==
     *
     * Verify if two positions are identical. Must have identical column
     * and row.
     *
     * @param otherPos The right position of the operator.
     * @return True if they are identical.
     */
    bool operator==(const Position & otherPos) const;

};

/**
* @brief operator <<
*
* Allows to inject the Position in the standard output.
*
* @param out An output stream.
* @param pos The Position to inject.
* @return The same output stream.
*/
std::ostream & operator<<(std::ostream & out, const Position & pos);


/**
 * @brief getDirection
 *
 * Returns a position representing the direction needed to achieve a piece
 * if they're aligned. If they re not aligned it returns the (0, 0) Position.
 *
 * @param result The difference of the start position and the end position.
 * @return The direction needed, or (0, 0) if they're not aligned.
 */
Position getDirection(Position result);

}

#endif //_POSITION_H
