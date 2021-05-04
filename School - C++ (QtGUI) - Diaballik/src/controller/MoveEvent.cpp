#include "headers/MoveEvent.hpp"
#include "src/view/headers/ErrorMessages.hpp"

namespace dblk
{


MoveEvent::MoveEvent(Diaballik & model, View & view, const Position endPos) :
    model_{model}, view_{view}, endPos_{endPos} {}

void MoveEvent::execute()
{
    int flag = model_.movePiece(endPos_);
    if (flag < 0) this->view_.displayError(errorMessagesMove[static_cast<size_t>(abs(flag) - 1)]);

}

} //End namespace dblk
