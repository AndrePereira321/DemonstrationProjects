#include "src/model/headers/Diaballik.hpp"
#include "src/controller/headers/Configs.hpp"

namespace dblk
{


//**********************************//
//* Implementation Classe Diaballik*//
//**********************************//


Diaballik::Diaballik():
    board_{}, currentPlayer_{Team::NORTH}, winner_{}, selected_{},
    moveCount_{DEFAULT_MOVES},
    canThrowBall_{true}
{}

void Diaballik::init(size_t size, bool variant)
{
    this->board_.init(variant, size);
    currentPlayer_ = Team::NORTH;
    winner_ = std::optional<Team>();
    selected_ = std::optional<Position>();
    moveCount_ = DEFAULT_MOVES;
    this->notifyObservers(EventType::NEW_GAME);
}

const Team & Diaballik::getCurrentPlayer() const
{
    return this->currentPlayer_;
}

unsigned Diaballik::getMoveCount() const
{
    return this->moveCount_;
}

const std::optional<Position> & Diaballik::getSelected() const
{
    return this->selected_;
}

const Board & Diaballik::getBoard() const
{
    return this->board_;
}


void Diaballik::passTurn()
{
    // Reset data game of currentPlayer
    this->currentPlayer_ = !this->currentPlayer_;
    this->selected_.reset();
    this->moveCount_ = DEFAULT_MOVES;
    this->canThrowBall_ = true;
    this->notifyObservers(EventType::PASS_TURN);
}


bool Diaballik::canPass() const
{
    return this->canThrowBall_;
}

bool Diaballik::canPass(const Position & pos) const
{
    if (!this->selected_.has_value() || !this->canPass()) return false;
    if (!this->board_.getPieceAt(this->selected_.value())->hasTheBall()) return false;
    return this->board_.checkThrow(currentPlayer_, this->selected_.value(), pos) == 1;
}

bool Diaballik::canMove(const Position & pos) const
{
    if (!this->selected_.has_value()) return false;
    if (this->board_.getPieceAt(this->selected_.value())->hasTheBall()) return false;
    if (checksEnoughMovesAvailable(pos) == -1) return false;
    return this->board_.checkMove(this->selected_.value(), pos) == 1;
}


int Diaballik::movePiece(const Position & pos)
{
    if (!this->selected_.has_value()) return -1; // select pos needed

    int steps = checksEnoughMovesAvailable(pos);
    if (steps < 0) return -2; //Not enought steps

    int flag = this->board_.movePiece(this->selected_.value(), pos);
    if (flag > 0)
    {
        this->moveCount_ -= static_cast<unsigned>(steps);
        this->selected_.reset();
        this->notifyObservers(EventType::MOVE);
        return steps;
    }
    return flag;
}


int Diaballik::throwBall(const Position & pos)
{
    if (!this->selected_.has_value()) return -1; // select pos needed
    else if (!this->canThrowBall_) return -2; //Cant pass
    else if (!this->board_.getPieceAt(
                 this->selected_.value())->hasTheBall()) return -3; //No ball

    int flag{this->board_.passBall(this->currentPlayer_, this->selected_.value(), pos)};
    if (flag > 0)
    {
        this->canThrowBall_ = false;
        this->selected_.reset();
        this->notifyObservers(EventType::PASS);
        return 1;
    }
    return flag;
}

bool Diaballik::isOver()
{
    return this->board_.checksGameIsFinsh(this->winner_) ? true : this->checksAntiGame();
}


bool Diaballik::checksAntiGame()
{
    return this->board_.checksAntiGame(this->currentPlayer_);
}

int Diaballik::select(const Position & pos)
{
    if (this->board_.isFree(pos)) return -1; //No Piece
    if (!this->board_.isInside(pos)) return -2; //Out bounds
    if (this->board_.getPieceAt(pos)->getTeam() != this->currentPlayer_)
        return -3; //Opponent Piece

    this->selected_ = pos;
    this->notifyObservers(EventType::SELECT);
    return 1;
}


std::optional<Team> Diaballik::getWinner() const
{
    return this->winner_;
}

int Diaballik::checksEnoughMovesAvailable(const Position & pos) const
{
    Position diff = pos - this->selected_.value();
    int steps = abs(diff.getRow()) + abs(
                    diff.getColumn());  // The number of moves done to achieve the final position
    if (static_cast<unsigned>(steps) > this->moveCount_) return -1;
    else return steps;

}

} // End namespace dblk
