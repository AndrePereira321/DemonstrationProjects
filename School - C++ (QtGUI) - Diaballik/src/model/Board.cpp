#include "headers/Board.hpp"
#include <exception>

namespace dblk
{


//******************************//
//* Implementation Classe Board*//
//******************************//

Board::Board(): pieces_{}, size_{0} {}

void Board::init(bool variant, size_t size)
{
    pieces_ = std::vector(size, std::vector<std::optional<Piece>>(size, std::optional<Piece>()));
    size_ = size;
    for (unsigned j{0}; j < size_; j++)
    {
        this->pieces_[0][j] = Piece(NORTH, size_ - 1);
        this->pieces_[size_ - 1][j] = Piece(SOUTH, 0);
    }
    this->pieces_[0][size_ / 2]->givesTheBall();
    this->pieces_[size_ - 1][size_ / 2]->givesTheBall();
    if (variant)
    {
        this->pieces_[0][1] = Piece{SOUTH, size_ - 1};
        this->pieces_[0][size_ - 2] = Piece{SOUTH, size_ - 1};
        this->pieces_[size_ - 1][1] = Piece{NORTH, 0};
        this->pieces_[size_ - 1][size_ - 2] = Piece{NORTH, 0};
    }
}

size_t Board::getSize() const
{
    return this->size_;
}


const std::optional<Piece> & Board::getPieceAt(const Position & position) const
{
    if (!this->isInside(position))
    {
        throw std::invalid_argument("Position out bounds of the board");
    }
    else
    {
        return this->pieces_[static_cast<unsigned>(position.getRow())][static_cast<unsigned>
                (position.getColumn())];
    }
}


bool Board::isFree(const Position & position) const
{
    return !this->getPieceAt(position).has_value();
}


bool Board::isInside(const Position & position) const
{
    return position.getRow() >= 0 && position.getRow() < static_cast<int>(size_) &&
           position.getColumn() >= 0 && position.getColumn() < static_cast<int>(size_);
}


int Board::movePiece(const Position & startPos, const Position & endPos)
{
    const int flag{this->checkMove(startPos, endPos)};
    if (flag > 0)
    {
        pieces_[static_cast<unsigned>(startPos.getRow())][static_cast<unsigned>(startPos.getColumn())].swap(
            pieces_[static_cast<unsigned>(endPos.getRow())][static_cast<unsigned>(endPos.getColumn())]);
    }
    return flag;
}

bool Board::checksAntiGame(Team antiGameVictim) const
{
    for (unsigned i{0}; i < size_; i++)
    {
        Position currentLine(static_cast<int>(i), 0);
        if (!this->isFree(currentLine) && this->getPieceAt(currentLine)->getTeam() != antiGameVictim)
        {
            if (this->verifyLineAntiGame(currentLine, 0, antiGameVictim))
            {
                return true;
            }
        }
    }
    return false;
}


bool Board::checksGameIsFinsh(std::optional<Team> & winner) const
{
    for (unsigned i{0}; i < size_; i++)
    {
        Position top(0, static_cast<int>(i)), bottom(static_cast<int>(size_) - 1, static_cast<int>(i));
        if (this->achievedObjective(bottom))
        {
            winner = this->getPieceAt(bottom)->getTeam();
            return true;
        }
        else if (this->achievedObjective(top))
        {
            winner = this->getPieceAt(top)->getTeam();
            return true;
        }
    }
    return false;
}


int Board::passBall(Team team, const Position & startPos, const Position & endPos)
{
    int flag{checkThrow(team, startPos, endPos)};
    if (flag > 0)
    {
        this->pieces_[static_cast<unsigned>(startPos.getRow())][static_cast<unsigned>
                (startPos.getColumn())]->removesTheBall();
        this->pieces_[static_cast<unsigned>(endPos.getRow())][static_cast<unsigned>
                (endPos.getColumn())]->givesTheBall();
    }
    return flag;
}



int Board::checkMove(const Position & startPos, const Position & endPos) const
{
    //The rules conditions for the startPos are verified when selecting the piece.
    if (!isInside(endPos)) return -3;        //Out bounds(console)
    if (!isFree(endPos)) return -4;        //Occuped(console)
    if (getPieceAt(startPos).value().hasTheBall()) return -6; //Has the ball, no move possible

    Position direction{getDirection(startPos - endPos)};
    if (direction.getRow() != direction.getColumn())
    {
        Position currentPos{startPos + direction};
        while (currentPos != endPos)
        {
            if (!isFree(currentPos))
                return -5;      //Obstacle
            currentPos = currentPos + direction;
        }
    }
    else
    {
        if (!this->isFree(Position(startPos.getRow(), startPos.getColumn() + direction.getColumn()))
                && !this->isFree(Position(startPos.getRow() + direction.getRow(), startPos.getColumn())))
        {
            return -5; //Obstacle
        }
    }
    return 1;       //Is ok
}


int Board::checkThrow(Team team, const Position & startPos,
                      const Position & endPos) const
{
    //The rules conditions for the startPos are verified when selecting the piece.
    if (!isInside(endPos)) return -4;         //Out board bounds
    if (isFree(endPos)) return -5;        //No piece at end position.
    if (getPieceAt(endPos).value().getTeam() != team) return -6;      //Is opponent piece.
    if (startPos == endPos) return -7;
    Position direction{getDirection(startPos - endPos)}, currentPos{startPos + direction};

    if (direction.getRow() == 0 && direction.getColumn() == 0) return -7;       //Not aligned
    while (currentPos != endPos)
    {
        if (isInside(currentPos) && !isFree(currentPos))
            if (getPieceAt(currentPos)->getTeam() != team)
                return -8;      //Obstacle
        currentPos = currentPos + direction;
    }
    return 1; //Is ok
}

//PRIVATE METHODS

bool Board::verifyLineAntiGame(const Position & currentPos, unsigned blockCount,
                               Team antiGameVictim) const
{
    if (!isInside(currentPos) || isFree(currentPos))
    {
        return false;
    }
    else if (getPieceAt(currentPos)->getTeam() == antiGameVictim)
    {
        return false;
    }
    else if (checkLineBreak(currentPos, antiGameVictim))
    {
        return false;
    }
    else
    {
        countBlockedOpponents(blockCount, currentPos, antiGameVictim);
        if (currentPos.getColumn() == static_cast<int>(getSize() - 1))
        {
            return blockCount >= 3;
        }
        else
        {
            return verifyLineAntiGame(Position(currentPos.getRow() - 1, currentPos.getColumn() + 1),
                                      blockCount, antiGameVictim)
                   || verifyLineAntiGame(Position(currentPos.getRow(), currentPos.getColumn() + 1),
                                         blockCount, antiGameVictim)
                   || verifyLineAntiGame(Position(currentPos.getRow() + 1, currentPos.getColumn() + 1),
                                         blockCount, antiGameVictim);
        }
    }
}


void Board::countBlockedOpponents(unsigned & blockCount, const Position & currentColumn,
                                  Team team) const
{
    Position top{currentColumn.getRow() - 1, currentColumn.getColumn()},
             bottom{currentColumn.getRow() + 1, currentColumn.getColumn()};
    if (isBlockedByLine(top, team, size_ - 1))
    {
        blockCount++;
    }
    if (isBlockedByLine(bottom, team, 0))
    {
        blockCount++;
    }
}

bool Board::isBlockedByLine(const Position & position, Team antiGameVictim,
                            size_t objectiveRow) const
{
    return this->belongsTo(position, antiGameVictim)
           && getPieceAt(position)->getObjectiveRow() == objectiveRow;
}

bool Board::checkLineBreak(const Position & curentColumn, Team antiGameVictim) const
{
    Position up{-1, 0}, down{1, 0};
    Position top{curentColumn + up}, bottom{curentColumn + down};
    if (this->hasDepassedLine(top, up, antiGameVictim, 0)
            || this->hasDepassedLine(bottom, down, antiGameVictim, static_cast<int>(size_) - 1))
    {
        return true;
    }
    return false;
}

bool Board::hasDepassedLine(Position & currentLine, const Position & dir,
                            Team antiGameVictim, int objectiveRow) const
{
    while (isInside(currentLine))
    {
        if (this->belongsTo(currentLine, antiGameVictim)
                &&  static_cast<int>(getPieceAt(currentLine)->getObjectiveRow()) == objectiveRow)
        {
            return true;
        }
        currentLine = currentLine + dir;
    }
    return false;
}


bool Board::achievedObjective(const Position & currentPosition) const
{
    return !isFree(currentPosition)
           && static_cast<int>(getPieceAt(currentPosition)->getObjectiveRow()) == currentPosition.getRow()
           && getPieceAt(currentPosition)->hasTheBall();
}


bool Board::belongsTo(const Position & position, Team player) const
{
    return isInside(position) && !isFree(position)
           && getPieceAt(position)->getTeam() == player;
}
} // End namespace dblk
