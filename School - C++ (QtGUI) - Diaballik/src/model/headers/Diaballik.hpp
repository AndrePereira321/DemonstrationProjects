#ifndef _DIABILIK_H
#define _DIABILIK_H

#include "Board.hpp"
#include "Team.hpp"
#include "Observable.hpp"


//! Diaballik implementations.
namespace dblk
{

    /**
     * @brief The Diabilik class
     *
     * This class represents the main facade for the game.
     * It contains all the necessary methods to play the game.
     */
    class Diaballik : public Observable
    {

        private:
            /**
             * @brief board
             *
             * The main container of the Board of the Game.
             */
            Board board_;

            /**
             * @brief currentPlayer
             *
             * The player who must move or pass.
             */
            Team currentPlayer_;


            /**
             * @brief winner_
             *
             * The winner of the game.
             */
            std::optional<Team> winner_;

            /**
             * @brief selected
             *
             * The selected Piece to move or pass.
             */
            std::optional<Position> selected_;

            /**
             * @brief moveCount
             *
             * The remaining moves in the current round.
             */
            unsigned moveCount_;

            /**
             * @brief canPass
             *
             * Indicates if it's allowed to pass the ball in
             * the current round.
             */
            bool canThrowBall_;

        public:

            Diaballik();

            /**
             * @brief init
             *
             * Initializates the game data
             *
             * @param size The size of the Board.
             * @param variant Indicates if the variant rule is applied.
             */
            void init(size_t size, bool variant);

            /**
             * @brief getCurrentPlayer
             *
             * Retrieves the current player.
             *
             * @return The current player.
             */
            const Team &getCurrentPlayer() const;


            /**
             * @brief getSelected
             *
             * Retrieves the selected position in the current round, or empty value if not selected.
             *
             * @return the selected position in the current round of the game Diaballik.
             */
            const std::optional<Position> &getSelected() const;

            /**
             * @brief getMoveCount
             *
             * Retrieves the number of available moves in the current round.
             *
             * @return The number of available moves.
             */
            unsigned getMoveCount() const;

            /**
             * @brief passTurn
             *
             * Swaps the current player for his opponent, and reset the available moves.
             */
            void passTurn();

            /**
             * @brief canPass
             *
             * Verifies if the current player can pass his ball.
             *
             * @return True if he can pass his ball.
             */
            bool canPass() const;

            /**
             * @brief canPass
             * Verifies if the selected piece can pass the ball to the position.
             *
             * @param pos The given position.
             * @return True if there is an selected piece who can pass to the given postion.
             */
            bool canPass(const Position &pos) const;


            /**
             * @brief canMove
             * Verifies if the selected piece can move to the given position.
             *
             * @param pos The given position.
             * @return True if there is an selected piece who move to the given postion.
             */
            bool canMove(const Position &pos) const;


            /**
             * @brief moveSupport
             *
             * Moves the selected Piece to the given position.
             * The number of steps to achieve the final position
             * must not depass the number of available moves, and
             * if the piece has been moves it must refresh the available moves attribute.
             *
             * @param pos The position to move the piece.
             * @return The number of steps to achieve the position, -1 if the piece
             * has not been moved, -2 not enough moves availabes
             */
            int movePiece(const Position &pos);


            /**
             * @brief throwBall
             *
             * If the selected piece has the ball, pass the ball to the given position.
             *
             * @param pos The position to pass the ball.
             * @return 1 if the ball has been passed, -1 position not selected, -2 any throw available, -3 the piece has not the ball
             */
            int throwBall(const Position &pos);

            /**
             * @brief isOver
             *
             * Verifies if the game is over.
             *
             * @return True if the game is over, false if not.
             */
            bool isOver();

            /**
             * @brief checksAntiGame
             *
             * Verifies if the current player is in a situation of anti-game.
             *
             * @return True if the current player faces a situation of anti-game.
             */
            bool checksAntiGame();

            /**
             * @brief select
             *
             * Selects a piece in the given position. To select the piece, in the board the
             * given position must contain a Piece of the same color of the current player.
             *
             * @param pos The position to select the Piece.
             * @return 1 if a piece has been selected, -1 not a piece selected, -2 out of the board, -3 opponent piece.
             */
            int select(const Position &pos);

            /**
             * @brief getBoard
             *
             * Gives the size of the board of the game Diaballik
             *
             * @return the size of the board
             */
            const Board &getBoard() const;

            /**
             * @brief getWinner
             *
             * Gives the winner of the game diaballik.
             *
             * @return the winner Diaballik, if there is not yet a winner empty value returned
             */
            std::optional<Team> getWinner() const;

            /**
             * @brief checkMovePosition
             *
             * Checks if a move to a position given is achievable by the number of moves available.
             *
             * @return the number of steps if the move is possible, -1 else.
             */
            int checksEnoughMovesAvailable(const Position &pos) const;


    }; //End diaballik class

} //End namespace dblk

#endif //_DIABILIK_H
