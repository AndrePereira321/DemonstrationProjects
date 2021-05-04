#include "src/model/headers/Team.hpp"

namespace dblk
{

// ************************* //
// **Team Implementation*** //
// ************************* //

// No member methods.

Team operator!(const Team & team)
{
    return (team == NORTH) ? SOUTH : NORTH;
}


std::string to_string(const Team & team)
{
    return (team == NORTH) ? "NORTH" : "SOUTH";
}


std::ostream & operator<<(std::ostream & out, const Team & team)
{
    out << to_string(team);
    return out;
}


} //End namespace dblk
