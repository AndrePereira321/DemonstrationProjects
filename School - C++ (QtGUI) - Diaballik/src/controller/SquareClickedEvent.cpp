#include "headers/SquareClickedEvent.hpp"
#include "headers/MoveEvent.hpp"
#include "headers/SelectEvent.hpp"
#include "headers/PassEvent.hpp"

namespace dblk
{

SquareClickedEvent::SquareClickedEvent(Diaballik & model,
                                       View & view, int row, int col) :
    model_{model}, view_{view}, squarePos_{row, col} {}


void SquareClickedEvent::execute()
{
    if (model_.getSelected().has_value())
    {
        if (model_.getBoard().getPieceAt(model_.getSelected().value())->hasTheBall()
                && model_.getBoard().getPieceAt(squarePos_).has_value() && model_.canPass())
        {
            PassEvent(model_, view_, squarePos_).execute();
        }
        else if (!model_.getBoard().getPieceAt(squarePos_).has_value())
        {
            MoveEvent(model_, view_, squarePos_).execute();
        }
        else
        {
            SelectEvent(model_, view_, squarePos_).execute();
        }
    }
    else
    {
        SelectEvent(model_, view_, squarePos_).execute();
    }
}

}

