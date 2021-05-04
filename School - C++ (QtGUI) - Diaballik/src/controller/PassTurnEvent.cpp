#include "headers/PassTurnEvent.hpp"

namespace dblk
{

PassTurnEvent::PassTurnEvent(Diaballik & model): model_{model} {}

void PassTurnEvent::execute()
{
    model_.passTurn();
}

} // End namespace dblk
