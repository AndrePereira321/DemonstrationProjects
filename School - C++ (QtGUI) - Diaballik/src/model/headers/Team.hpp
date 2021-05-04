#ifndef _TEAM_H
#define _TEAM_H

#include <string>
#include <ostream>

namespace dblk
{

/**
 * @brief The Team enum
 *
 * Allows to distinguish the two players of the game.
 */
enum Team
{
    NORTH, SOUTH
};

/**
 * @brief operator !
 *
 * Retrieves the opponent team from the given team.
 *
 * @param team The team to swap.
 * @return The opponent team.
 */
Team operator!(const Team & team);

/**
 * @brief to_string
 *
 * Creates a string describing the team.
 *
 * @return A string describing the team.
 */
std::string to_string(const Team & team);



/**
* @brief operator <<
*
* Allows to inject the Team in the standard output.ds
*
* @param out An output stream.
* @param team The Team to inject.
* @return The same output stream.
*/
std::ostream & operator<<(std::ostream & out, const Team & team);


}


#endif //_TEAM_H
