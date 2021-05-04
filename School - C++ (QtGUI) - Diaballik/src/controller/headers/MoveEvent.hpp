#ifndef MOVEEVENT_HPP
#define MOVEEVENT_HPP

#include "DiaballikEvent.hpp"
#include "src/model/headers/Diaballik.hpp"
#include "src/view/headers/View.hpp"

namespace dblk
{

/**
 * @brief The MoveEvent class
 *
 * This class is an active event that will modify the game data.
 * It moves a piece of the board of the game.
 */
class MoveEvent : public DiaballikAbstractEvent
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
     * @brief endPos_
     *
     * The end position to move the selected piece.
     */
    const Position endPos_;

  public:

    /**
     * @brief MoveEvent
     *
     * Creates a new move event.
     *
     * @param model The game data.
     * @param view The UI.
     * @param endPos The end position.
     */
    MoveEvent(Diaballik & model, View & view, const Position endPos);

    /**
     * @brief execute
     *
     * Try to move the selected piece to the end position.
     * If the piece can't be moved, or there is another problem, displays an error message.
     */
    virtual void execute() override;
};

}

#endif // MOVEEVENT_HPP
