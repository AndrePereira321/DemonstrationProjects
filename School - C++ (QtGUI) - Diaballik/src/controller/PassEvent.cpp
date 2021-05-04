#include "headers/PassEvent.hpp"
#include "src/view/headers/ErrorMessages.hpp"


namespace dblk
{

/* PassEvent Class Implementation */

PassEvent::PassEvent(Diaballik & model, View & view, const Position endPos) : model_{model}, view_{view},
    endPos_{endPos} {}

void PassEvent::execute()
{
    int flag = model_.throwBall(endPos_);
    if (flag < 0) this->view_.displayError(errorMessageThrow[abs(flag) - 1]);
}

} //End namespace dblk
