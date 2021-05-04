#ifndef ERRORMESSAGES_HPP
#define ERRORMESSAGES_HPP

#include <vector>
#include <string>

namespace dblk
{

    /**
    * @brief errorMessagesMove
    * All error messages possible of a move that cannot be executed.
    *
    **/
    static std::vector<std::string> errorMessagesMove =
    {
        "No position has been selected",
        "Not enought moves available",
        "Out bounds of the board",
        "Position already occupied",
        "Obstacle met",
        "Impossible to move the piece who has the ball"
    };

    /**
    * @brief errorMessageThrow
    * All error messages possible of a throw that cannot be executed.
    *
    **/
    static std::vector<std::string> errorMessageThrow =
    {
        "No position has been selected",
        "You already passed the ball this turn",
        "The selected piece has no ball",
        "Out bounds of the board",
        "No piece at the end position to pass the ball",
        "Piece at the end position is from the opponent team",
        "You must be aligned to pass the ball",
        "Obstacle met"
    };

    /**
    * @brief errorMessageSelect
    * All error messages possible of a selection that cannot be executed.
    *
    **/
    static std::vector<std::string> errorMessageSelect =
    {
        "No piece at the given position",
        "Position out bounds of the board",
        "Piece of the opponent team"
    };



} // end namespace dblk

#endif // ERRORMESSAGES_HPP
