#include "headers/Controller.hpp"

namespace dblk
{


Controller::Controller(Diaballik & model, ViewUI & view): model_{model}, view_{view}, evntManager_{model, view} {}


void Controller::init()
{
    this->model_.registerObserver(&this->view_);
    this->view_.displayWelcomePage();
    this->view_.setEventManager(&evntManager_);
    this->view_.show();
}

} //End namespace dblk
