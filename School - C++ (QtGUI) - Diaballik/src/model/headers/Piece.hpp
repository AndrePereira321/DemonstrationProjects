#ifndef _PIECE_H
#define _PIECE_H

#include "Team.hpp"

namespace dblk
{

/**
 * @brief The Piece class
 * This class represents a Piece of the board. The pieces will be contained by the Board class.
 */
class Piece
{

    /**
     * @brief team
     *
     * The team owner of the piece.
     */
    Team team_;

    /**
     * @brief haveBall
     *
     * Indicates if this piece has the ball.
     */
    bool haveBall_;

    /**
     * @brief objectiveRow
     *
     * Indicates the objective row to win.
     * Usefull in the variant mode rule.
     */
    size_t objectiveRow_;

  public:

    /**
     * @brief Piece
     *
     * Creates a new Piece.
     *
     * @param team The team owner of the Piece.
     * @param size_t the objectiveRow of the piece of given team.
     */
    Piece(const Team & team, size_t objectiveRow);

    /**
     * @brief hasTheBall
     *
     * Verifies if this piece has the ball.
     *
     * @return True if the piece has the ball.
     */
    bool hasTheBall() const;

    /**
     * @brief getObjectiveRow
     *
     * Retrieve the objective row of the Piece.
     *
     * @return The objective row of the Piece.
     */
    size_t getObjectiveRow() const;

    /**
     * @brief givesTheBall
     *
     * Gives the ball to the Piece.
     */
    void givesTheBall();

    /**
     * @brief removesTheBall
     *
     * Removes the ball to the Piece.
     */
    void removesTheBall();

    /**
     * @brief getTeam
     *
     * Retrieves the team owner of the Piece.
     *
     * @return The team owner of the Piece.
     */
    const Team & getTeam() const;

    /**
     * @brief to_string
     *
     * Creates a string describing the Piece.
     *
     * @return A text description of the Piece.
     */
    std::string to_string() const;


};

/**
 * @brief operator <<
 *
 * Allows to inject the Piece in the standard output.
 *
 * @param out An output stream.
 * @param piece The piece to inject.
 * @return The same output stream.
 */
std::ostream & operator<<(std::ostream & out, const Piece & piece);
}

#endif //_PIECE_HF
