#include "src/model/headers/Piece.hpp"

namespace dblk
{

// ************************* //
// **Piece Implementation*** //
// ************************* //

Piece::Piece(const Team & team, size_t objectiveRow): team_{team},
    haveBall_{false}, objectiveRow_{objectiveRow}  {}


bool Piece::hasTheBall() const
{
    return this->haveBall_;
}

size_t Piece::getObjectiveRow() const
{
    return this->objectiveRow_;
}


void Piece::givesTheBall()
{
    this->haveBall_ = true;
}

void Piece::removesTheBall()
{
    this->haveBall_ = false;
}


const Team & Piece::getTeam() const
{
    return team_;
}


std::string Piece::to_string() const
{

    using std::string, std::to_string;
    string str{""};
    str.append("(");
    str.push_back(dblk::to_string(this->team_)[0]);
    if (haveBall_)
    {
        str.append("*");
    }
    else
    {
        str.append(" ");
    }
    str.append(")");
    return str;
}

// Non member methods.

std::ostream & operator<<(std::ostream & out, const Piece & piece)
{
    out << piece.to_string();
    return out;
}

} // End namespace dblk
