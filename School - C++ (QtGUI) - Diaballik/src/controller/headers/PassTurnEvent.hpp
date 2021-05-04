#ifndef PASSTURNEVENT_H
#define PASSTURNEVENT_H

#include "DiaballikEvent.hpp"
#include "src/model/headers/Diaballik.hpp"

namespace dblk
{

/**
 * @brief The PassTurnEvent class
 *
 * This class is an active event that will modify the game data.
 * It swaps the turn of the players.
 */
class PassTurnEvent : public DiaballikAbstractEvent
{

  private:

    /**
     * @brief model_
     *
     * The game data.
     */
    Diaballik & model_;

  public:

    /**
     * @brief PassTurnEvent
     *
     * Creates a new pass turn event.s
     *
     * @param model The game data.
     */
    explicit PassTurnEvent(Diaballik & model);

    /**
     * @brief execute
     *
     * Swaps the turn from the current player to its opponent.
     */
    virtual void execute() override;
};

}

#endif // PASSTURNEVENT_H
