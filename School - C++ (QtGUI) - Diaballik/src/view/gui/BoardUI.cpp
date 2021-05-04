#include "headers/BoardUI.hpp"
#include "src/controller/headers/Configs.hpp"

namespace dblk
{
    BoardUI::BoardUI(const Board &board,
                     DiaballikEventManager *evnManager)
        : squares_(board.getSize(), std::vector<SquareUI *>(board.getSize(), nullptr))
    {
        for (int row = 0; row < static_cast<int>(board.getSize()); row++)
        {
            for (int col = 0; col < static_cast<int>(board.getSize()); col++)
            {
                Position curPos{row, col};
                SquareUI *square = new SquareUI(curPos, evnManager, board.getSize() * - 5 + 100);
                square->refreshPiece(board.getPieceAt(curPos));
                connect(square, &SquareUI::clicked, square, &SquareUI::squareClicked);
                connect(square, &SquareUI::rightClicked, square, &SquareUI::squareRightClicked);
                squares_[row][col] = square;
                this->addWidget(square, row, col);
            }
        }
    }


    void BoardUI::refreshBoard(const Diaballik &game)
    {
        static std::optional<Position> oldSelected;

        for (int row = 0; row < static_cast<int>(game.getBoard().getSize()); row++)
        {
            for (int col = 0; col < static_cast<int>(game.getBoard().getSize()); col++)
            {
                Position curPos{row, col};
                SquareUI *square = squares_[row][col];
                refreshSquare(game, square, curPos);
            }
        }

        if (game.getSelected().has_value())
        {
            if (oldSelected.has_value())
            {
                refreshSquare(game, squares_[oldSelected->getRow()][oldSelected->getColumn()], oldSelected.value());
            }

            squares_[game.getSelected()->getRow()][game.getSelected()->getColumn()]->setSelected();
            oldSelected = game.getSelected();
        }
    }

    void BoardUI::refreshSquare(const Diaballik &game, SquareUI *square, Position &curPos)
    {
        if (game.getSelected().has_value() && game.getBoard().getPieceAt(curPos).has_value()
                        && game.getBoard().getPieceAt(game.getSelected().value())->hasTheBall())
        {
            if (game.canPass(curPos))
            {
                square->setInterectable();
            }
            else
            {
                square->setDefaultBackground();
            }
        }
        else if (game.getSelected().has_value())
        {
            if (game.canMove(curPos))
            {
                square->setInterectable();
            }
            else
            {
                square->setDefaultBackground();
            }
        }
        else
        {
            square->setDefaultBackground();
        }

        square->refreshPiece(game.getBoard().getPieceAt(curPos));
    }


}
