#ifndef BOARDUI_HPP
#define BOARDUI_HPP
#include <QGridLayout>
#include "src/model/headers/Diaballik.hpp"
#include "SquareUI.hpp"



namespace dblk
{

/**
 * @brief The BoardUI class
 * This class represents the graphics implementation
 * of the Diaballik board.
 */
class BoardUI : public QGridLayout
{

    /**
     * @brief squares_
     *  All squares with graphical representation of the board Diaballik.
     */
    std::vector<std::vector<SquareUI *>> squares_;

  public:

    /**
     * @brief BoardUI
     * Creates a new board depending on the game data.
     *
     * @param board the board of the game.
     * @param evnManager Event manager factory.
     */
    BoardUI(const dblk::Board & board, DiaballikEventManager * evnManager);

    /**
     * @brief refreshBoard
     * Refresh the board game.
     * @param game the game data.
     */
    void refreshBoard(const Diaballik & game);

  private:
    /**
     * @brief refreshSquare
     * Refresh a square of the game board.
     * @param game the game data.
     * @param square the square to refresh.
     * @param curPos Position of the square.
     */
    void refreshSquare(const Diaballik & game, SquareUI * square, Position & curPos);

};


}

#endif // BOARDUI_HPP
