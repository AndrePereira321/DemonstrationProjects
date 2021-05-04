#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>
#include "src/view/headers/View.hpp"
#include "src/controller/headers/EventManager.hpp"
#include "src/model/headers/Diaballik.hpp"
#include "BoardUI.hpp"

//! user interface.
namespace Ui
{

class MainWindow;
}

struct EventType;

namespace dblk
{

/**
 * @brief The ViewUI class
 * This facade represents the graphics implementation
 * of the View facade. It will implement all the main
 * methods of the Main Window with the GUI Form.
 */
class ViewUI : public QMainWindow, public View
{
    Q_OBJECT

  private:
    /**
    * @brief mainWindow_
    * The Mainwindow.
    */
    Ui::MainWindow * mainWindow_;

    /**
    * @brief evntManager_
    * Allows to modify the model with the
    * actions allowed to the user.
    *
    * Event manager.
    */
    DiaballikEventManager * evntManager_;

    /**
    * @brief boardUI_
    * The boardUI.
    */
    BoardUI * boardUI_;
  public:

    /**
     * @brief ViewUI
     *
     * Creates a new VewUI.
     */
    explicit ViewUI(QWidget * parent = nullptr);

    /**
     * @brief setEventManager
     * Sets the event manager that allows to modify
     * the data according the game rules.
     */
    void setEventManager(DiaballikEventManager * evntManager);

    /**
     * @brief displayCurrentPlayer
     *
     * Displays the current player of a game of Diaballik
     *
     * @param the team of the current player
     */
    virtual void displayCurrentPlayer(const dblk::Team & team)  override;


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
    virtual void displayCounters(unsigned moveCounter,
                                 bool canPass) override;

    /**
     * @brief displayWinner
     *
     * Displays the winner of the game Diaballik
     *
     * @param team the optinal winner of the game Diaballik.
     * @param antiGame Describes if the winner won by antigame
     */
    virtual void displayWinner(const std::optional<dblk::Team> & team, bool antiGame)
    override;

    /**
     * @brief displaySelected
     *
     * Display the position of the selected piece
     *
     * @param piece The optional piece position.
     */
    virtual void displaySelected(const std::optional<dblk::Position>
                                 position)
    override;

    /**
     * @brief displayBoard
     *
     * Displays the boards of the Diaballik game
     */
    virtual void displayBoard(const dblk::Diaballik & diaballik) override;


    virtual void update(const dblk::Observable * observable,
                        EventType type) override;

    ~ViewUI();

  public slots:

    /**
     * @brief displayHelp
     *
     * Displays the Help
     */
    virtual void displayHelp()  override;

    /**
     * @brief displayError
     *
     * Display an error message
     *
     * @param flagError flag of the error
     * @param typeError integer that represent the type error occured (select(1), move(2), pass(3))
     */
    virtual void displayError(std::string errorMsg)  override;

    /**
     * @brief displayGoodByeMessage
     *
     * Display farewell message
     */
    virtual void displayGoodByeMessage()  override;

    /**
     * @brief displayMainMenu
     *
     * Display the main menu of the Diaballik game.
     *
     * @return the size of the game and variant.
     */
    virtual void displayOptionsMenu() override;

    /**
     * @brief displayWelcomeMessage
     *
     * Displays a welcome message to the players
     */
    virtual void displayWelcomePage() override;


    /**
     * @brief displayRulesPage
     * Displays the rules page to the players.
     */
    virtual void displayRulesPage() override;

  protected:
    /**
     * @brief keyPressEvent
     *
     * Pass the game turn when 'P' is pressed.
     *
     * @param event The key pressed event.
     */
    void keyPressEvent(QKeyEvent * event) override;

  private:
    /**
     * @brief initSlots
     * Associate the all the necessary slots to the signals.
     */
    void initSlots();

  private slots:
    /**
     * @brief initGame
     * Inits the game according to the user input.
     */
    void initGame();
    /**
     * @brief passTurnGame
     * Pass the player turn.
     */
    void passTurnGame();
    /**
     * @brief restartGame
     * Restart the app.
     */
    void restartGame();

};

}

#endif // MAINWINDOW_H
