#include "headers/NewGameEvent.hpp"
#include "src/model/headers/Diaballik.hpp"

namespace dblk
{

NewGameEvent::NewGameEvent(Diaballik & model, int boardSize, bool variantMode) :
    model_{model},
    boardSize_{boardSize},
    variant_{variantMode}
{}

void NewGameEvent::execute()
{
    this->model_.init(boardSize_, variant_);
}

}
