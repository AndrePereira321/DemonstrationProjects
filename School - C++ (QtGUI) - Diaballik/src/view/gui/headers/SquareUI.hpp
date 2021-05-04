#ifndef SQUAREUI_HPP
#define SQUAREUI_HPP
#include <QLabel>
#include "src/model/headers/Piece.hpp"
#include "src/model/headers/Position.hpp"
#include "src/controller/headers/EventManager.hpp"

namespace dblk
{
/**
 * @brief The SquareUI class
 * This class represents the graphics implementation of a square of the Diaballik board.
 */
class SquareUI : public QLabel
{
    Q_OBJECT
  private:

    /**
    * @brief southPlayerImg
    * Path image representing the south player.
    */
    static const char * southPlayerImg;

    /**
    * @brief northPlayerImg
    * Path image representing the north player.
    */
    static const char * northPlayerImg;

    /**
    * @brief southPlayerBallImg
    * Path image representing south player with the ball.
    */
    static const char * southPlayerBallImg;

    /**
    * @brief northPlayerBallImg
    * Path image representing the north player with the ball.
    */
    static const char * northPlayerBallImg;

    /**
     * @brief squarePos_
     * Position of the square inside the board.
     */
    Position squarePos_;

    /**
    * @brief evntManager_
    * Allows to modify the model with the
    * actions allowed to the user.
    *
    * Event manager.
    */
    DiaballikEventManager * evnManager_;

  public:
    /**
     * @brief SquareUI
     * SquareUI.
     * @param squarePos Square position inside the board.
     * @param evnManager Event Manager factory.
     * @param size The size of the square.
     */
    SquareUI(Position & squarePos, DiaballikEventManager * evnManager, int size);

    /**
     * @brief refreshPiece
     * Refresh the piece Image.
     * If the piece in parameter has a value, refresh the piece.
     *
     * @param piece The optional Piece.
     */
    void refreshPiece(const std::optional<Piece> & piece);

    /**
     * @brief setInterectable
     * Set a squareUI style to interectable by the user in the board.
     */
    void setInterectable();

    /**
     * @brief setSelected
     * Set the square style to selected.
     */
    void setSelected();

    /**
     * @brief setDefaultBackground
     * Set default background style.
     */
    void setDefaultBackground();

  public slots:
    /**
     * @brief squareClicked
     * Do one of the follow actions depending on the circunstances:
     * - Select a Piece
     * - Move a piece
     * - Pass the Ball
     */
    void squareClicked();

    /**
     * @brief squareRightClicked
     *
     * Select a piece.
     */
    void squareRightClicked();
  signals:
    /**
     * @brief clicked
     *
     * Emited when left clicked.
     */
    void clicked();
    /**
     * @brief rightClicked
     *
     * Emited when right clicked.
     */
    void rightClicked();

  protected:
    /**
     * @brief mousePressEvent
     * Emit the clicked() and rightClicked() signals
     * when clicking in a square.
     *
     * @param event the mouse Event.
     */
    void mousePressEvent(QMouseEvent * event) override;

};

}

#endif // SQUAREUI_HPP
