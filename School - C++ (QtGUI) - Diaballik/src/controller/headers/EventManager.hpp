#ifndef EVENTFACTORY_H
#define EVENTFACTORY_H

#include <string>
#include "src/controller/headers/DiaballikEvent.hpp"
#include "EventType.hpp"

namespace dblk
{

class Diaballik;
class View;


/**
 * @brief The DiaballikEventFactory class
 *
 * This factory is associated with the game data and the user interface.
 * It parses a string input from the user to generate a DiaballikEvent
 * that will manipulate the game or the UI.
 */
class DiaballikEventManager
{

  private:

    /**
     * @brief model_
     *
     * The game data.
     */
    Diaballik & model_;

    /**
     * @brief view_
     *
     * The user interface.
     */
    View & view_;

  public:

    /**
     * @brief DiaballikEventFactory
     *
     * Creates a new factory.
     *
     * @param model The game data.
     * @param view The UI.
     */
    DiaballikEventManager(Diaballik & model, View & view);

    /**
     * @brief generateEvent
     *
     * Generates a new DiaballikEvent from the user input.
     * The event is stored in dynamic memory, it must be deleted when it's used.
     * It throws runtime_exception if the command is not known.
     * It throws invalid_argument if there is not enought arguments for the command.
     *
     * @param input The user input.
     * @return A new DiaballikEvent.
     */
    void executeEvent(EventType type, int arg1, int arg2);

};

}


#endif // EVENTFACTORY_H
