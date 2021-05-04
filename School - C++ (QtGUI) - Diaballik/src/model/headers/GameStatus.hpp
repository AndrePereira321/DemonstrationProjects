#ifndef GAMESTATUS_HPP
#define GAMESTATUS_HPP

namespace dblk
{

/**
 * @brief The GameStatus enum
 * Represents the current status of the game.
 * RUNNING = The game is not over.
 * OBJECTIVE_REACHED = One player won reaching the objective.
 * ANTI_GAME = The player won in an anti_game situation.
 */
enum class GameStatus
{
    RUNNING, OBJECTIVE_REACHED, ANTI_GAME
};

#endif // GAMESTATUS_HPP
