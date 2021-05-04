#ifndef PASSEVENT_H
#define PASSEVENT_H

#include "DiaballikEvent.hpp"
#include "src/view/headers/View.hpp"
#include "src/model/headers/Diaballik.hpp"

namespace dblk
{

    /**
     * @brief The PassEvent class
     *
     * This class is an active event that will modify the game data.
     * It passes the ball from a piece to another piece.
     */
    class PassEvent : public DiaballikAbstractEvent
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
             * @brief endPos_
             *
             * The position to pass the ball.
             */
            const Position endPos_;

        public:

            /**
             * @brief PassEvent
             *
             * Creates a new pass event.
             *
             * @param model The game data.
             * @param view The User interface
             * @param endPos The end position.
             */
            PassEvent(Diaballik &model, View &view, const Position endPos);

            /**
             * @brief execute
             *
             * Try to pass the ball from the selected piece to the end position.
             * If there is a problem and the ball isn't passed it displays an error message.
             */
            virtual void execute() override;

    };

}

#endif // PASSEVENT_H
