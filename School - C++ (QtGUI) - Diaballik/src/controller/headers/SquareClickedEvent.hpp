#ifndef SQUARECLICKEDEVENT_H
#define SQUARECLICKEDEVENT_H

#include "DiaballikEvent.hpp"
#include "src/model/headers/Diaballik.hpp"
#include "src/view/headers/View.hpp"

namespace dblk
{


    /**
     * @brief The SquareClickedEvent class
     *
     * This class is an active event that will modify the game data.
     * It manages the click of the user on a square and determines the type of action (Move, selection or a throw).
     */
    class SquareClickedEvent : public DiaballikAbstractEvent
    {
        private:
            /**
             * @brief model_
             *
             * The game data.
             */
            Diaballik &model_;

            /**
             * @brief view_
             *
             * The user interface.
             */
            View &view_;

            /**
             * @brief squarePos_
             *
             * The position of the square in the board.
             */
            Position squarePos_;
        public:
            /**
             * @brief SquareClickedEvent
             * @param model the game data.
             * @param view the user interface.
             * @param row row of the square clicked.
             * @param col column of the square clicked.
             */
            SquareClickedEvent(Diaballik &model, View &view, int row, int col);

            /**
             * @brief execute
             * Manages the click and determines the type of action and execute it.
             */
            void execute() override;
    };

}
#endif // SQUARECLICKEDEVENT_H
