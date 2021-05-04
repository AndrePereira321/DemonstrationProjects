#include "headers/Position.hpp"

namespace dblk
{

//*************************//
//*Position Implementation*//
//*************************//

Position::Position(int row, int column) :
    row_{row}, column_{column} {}


int Position::getRow() const
{
    return this->row_;
}


int Position::getColumn() const
{
    return this->column_;
}


std::string Position::to_string() const
{
    using std::string, std::to_string;

    return string("( " + to_string(this->row_) + ", "
                  + to_string(this->column_) + " )");
}


Position Position::operator-(const Position & otherPos) const
{
    return Position(this->row_ - otherPos.getRow(),
                    this->column_ - otherPos.column_);
}


Position Position::operator+(const Position & otherPos) const
{
    return Position(this->row_ + otherPos.getRow(),
                    this->column_ + otherPos.column_);
}

bool Position::operator!=(const Position & otherPos) const
{
    return this->row_ != otherPos.row_ || this->column_ != otherPos.column_;
}

bool Position::operator==(const Position & otherPos) const
{
    return this->row_ == otherPos.row_ && this->column_ == otherPos.column_;
}



// Nom member methods.

std::ostream & operator<<(std::ostream & out, const Position & pos)
{
    out << pos.to_string();
    return out;
}


Position getDirection(Position result)
{
    //Horizontal alignement.
    if (result.getRow() == 0 )
    {
        if (result.getColumn() > 0) return Position(0, -1);
        else return Position(0, 1);
    }
    //Vertical allignement
    else if (result.getColumn() == 0)
    {
        if (result.getRow() > 0) return Position(-1, 0);
        else return Position(1, 0);
    }
    //Diagonal alignement
    else if (std::abs(result.getRow()) == std::abs(result.getColumn()))
    {
        if (result.getRow() < 0 && result.getColumn() < 0) return Position(1, 1);
        else if (result.getRow() < 0 && result.getColumn() > 0) return Position(1, -1);
        else if (result.getRow() > 0 && result.getColumn() > 0) return Position(-1, -1);
        else if (result.getRow() > 0 && result.getColumn() < 0) return Position(-1, 1);
    }
    //Not aligned.
    return Position(0, 0);
}


} // End namespace dblk
