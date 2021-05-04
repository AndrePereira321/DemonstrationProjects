#ifndef VIEW_HPP
#define VIEW_HPP

#include <iostream>
#include "src/model/headers/Diaballik.hpp"
#include "src/view/headers/ErrorMessages.hpp"
#include "src/view/headers/Observer.hpp"

namespace dblk
{

    /**
     * @brief The View class
     * This class represents the View facade with all graphics implementation.
     */
    class View: public Observer
    {

        public:
            /**
             * @brief displayOptionsMenu
             *
             * Display the options menu of the Diaballik game.
             *
             */
            virtual void displayOptionsMenu() = 0;

            /**
             * @brief displayWelcomeMessage
             *
             * Displays a welcome message to the players
             */
            virtual void displayWelcomePage() = 0;

            /**
             * @brief displayBoard
             *
             * Displays the boards of the Diaballik game
             */
            virtual void displayBoard(const Diaballik &diaballik) = 0;

            /**
             * @brief displayHelp
             *
             * Displays the Help
             */
            virtual void displayHelp() = 0;

            /**
             * @brief displayCurrentPlayer
             *
             * Displays the current player of a game of Diaballik
             *
             * @param the team of the current player
             */
            virtual void displayCurrentPlayer(const Team &team) = 0;

            /**
             * @brief displayCounters
             *
             * Displays the counters of the current players
             * Counter of movements available
             * And possibility to pass the ball
             *
             * @param moveCounter the number of moves availables of the current Player
             * @param canPass possibility to throw the ball of the current player
             */
            virtual void displayCounters(unsigned moveCounter, bool canPass) = 0;

            /**
             * @brief displayWinner
             *
             * Displays the winner of the game Diaballik
             *
             * @param team the optinal winner of the game Diaballik.
             * @param antiGame Describes if the winner won by antigame
             */
            virtual void displayWinner(const std::optional<Team> &team, bool antiGame) = 0;

            /**
             * @brief displayError
             *
             * Display an error message
             *
             * @param flagError flag of the error
             */
            virtual void displayError(std::string errorMsg) = 0;

            /**
             * @brief displayGoodByeMessage
             *
             * Display farewell message
             */
            virtual void displayGoodByeMessage() = 0;

            /**
             * @brief displaySelected
             *
             * Display the position of the piece selected
             *
             * @param position position of the piece.
             */
            virtual void displaySelected(const std::optional<Position> position) = 0;


            /**
             * @brief displayRulesPage
             * Displays the rules page to the players.
             */
            virtual void displayRulesPage() = 0;

    }; //End class View

} // End namespace dblk


#endif // VIEW_HPP
