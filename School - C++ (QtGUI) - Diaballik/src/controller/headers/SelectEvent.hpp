#ifndef SELECTEVENT_HPP
#define SELECTEVENT_HPP

#include "DiaballikEvent.hpp"
#include "src/view/headers/View.hpp"
#include "src/model/headers/Diaballik.hpp"

namespace dblk
{

/**
 * @brief The SelectEvent class
 *
 * This class is an active event that will modify the game data.
 * It selects a piece in the game, to move or pass the ball.
 */
class SelectEvent : public DiaballikAbstractEvent
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

    /**
     * @brief selectPos_
     *
     * The position to select.
     */
    const Position selectPos_;

  public:

    /**
     * @brief SelectEvent
     *
     * Creates a new select event.
     *
     * @param model The game data.
     * @param view The UI.
     * @param selectPos The position to be selected.
     */
    SelectEvent(Diaballik & model, View & view, const Position selectPos);

    /**
     * @brief execute
     *
     * Trys to select the position.
     * Displays an error message if the given position can't be selected!
     */
    virtual void execute() override;

};

}

#endif // SELECTEVENT_HPP
