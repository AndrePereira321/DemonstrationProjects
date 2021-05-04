#include "headers/ViewUI.hpp"
#include "ui_MainWindow.h"
#include <QFile>
#include <QKeyEvent>
#include <QMessageBox>
#include <qprocess.h>

namespace dblk
{

ViewUI::ViewUI(QWidget * parent):
    QMainWindow(parent),
    mainWindow_{new Ui::MainWindow},
            evntManager_{nullptr},
            boardUI_{nullptr}
{
    mainWindow_->setupUi(this);
    setWindowIcon(QIcon(":/code/Projet.icns"));
    this->setFixedSize(QSize(1280, 720));

    QFile file(":/resources/qss/stylesheet.qss");

    if (file.exists())
    {
        file.open(QFile::ReadOnly);
        QString style = QLatin1String(file.readAll());
        qApp->setStyleSheet(style);
    }

    mainWindow_->movesAvailableNumber->setPalette(QColor("#ff9100"));
    mainWindow_->throwAvailableNumber->setPalette(QColor("#ff9100"));
    initSlots();
}

void ViewUI::setEventManager(DiaballikEventManager * evntManager)
{
    this->evntManager_ = evntManager;
}

void ViewUI::initSlots()
{
    connect(mainWindow_->btnWelcome_play, &QAbstractButton::clicked,
            this, &ViewUI::displayOptionsMenu);
    connect(mainWindow_->btnWelcome_exit, &QAbstractButton::clicked,
            this,  &ViewUI::displayGoodByeMessage);
    connect(mainWindow_->btnWelcome_showRules, &QAbstractButton::clicked,
            this, &ViewUI::displayRulesPage);
    connect(mainWindow_->btnGameOptions_mainMenu, &QAbstractButton::clicked,
            this, &ViewUI::displayWelcomePage);
    connect(mainWindow_->btnGameOptions_play, &QAbstractButton::clicked,
            this, &ViewUI::initGame);
    connect(mainWindow_->btnGamePage_passTurn, &QAbstractButton::clicked,
            this, &ViewUI::passTurnGame);
    connect(mainWindow_->btnGamePage_restart, &QAbstractButton::clicked,
            this, &ViewUI::restartGame);
    connect(mainWindow_->btnGamePage_help, &QAbstractButton::clicked,
            this, &ViewUI::displayHelp);
    connect(mainWindow_->btnRules_back, &QAbstractButton::clicked,
            this, &ViewUI::displayWelcomePage);
    connect(mainWindow_->btnWinnerPage_exit, &QAbstractButton::clicked,
            this, &ViewUI::displayGoodByeMessage);
    connect(mainWindow_->btnWinnerPage_mainMenu, &QAbstractButton::clicked,
            this, &ViewUI::restartGame);
}

void ViewUI::initGame()
{
    size_t boardSize = mainWindow_->boardSizeInput->currentText().toInt();
    bool variant = mainWindow_->gameVariantInput->isChecked();
    this->evntManager_->executeEvent(EventType::NEW_GAME, boardSize, variant);
}


void ViewUI::passTurnGame()
{
    this->evntManager_->executeEvent(EventType::PASS_TURN, 0, 0);
}

void ViewUI::restartGame()
{
    QMessageBox::StandardButton reply;
    reply = QMessageBox::warning(this,
                                 "Exit", "Are you sure you want to leave the game to the main menu?",
                                 QMessageBox::Yes | QMessageBox::No);

    if (reply == QMessageBox::Yes)
    {
        // Restart application
        QProcess process;
        process.startDetached("Projet", QStringList());
        qApp->quit();
    }
}


void ViewUI::displayWelcomePage()
{
    this->mainWindow_->stackedWidget->setCurrentIndex(0);
}

void ViewUI::displayRulesPage()
{
    this->mainWindow_->stackedWidget->setCurrentIndex(4);
}

void  ViewUI::displayOptionsMenu()
{
    this->mainWindow_->stackedWidget->setCurrentIndex(1);
}

void ViewUI::displayBoard(const dblk::Diaballik & diaballik)
{
    this->boardUI_->refreshBoard(diaballik);
}

void ViewUI::displayHelp()
{
    QMessageBox help;
    help.setBaseSize(QSize(650, 200));
    help.setText("How to play");
    help.setIcon(QMessageBox::Information);
    help.setInformativeText("- Select a piece : Right click on a piece. \n"
                            "- Move a piece : After selected a piece left click on a empty box. \n"
                            "- Pass the ball : After selected the piece who has the ballon left click to piece wanted to pass. \n"
                            "- Pass turn : 'P' key or click on the button passTurn. \n"
                            "- Leave : Click on te button Leave.");
    help.exec();
}

void ViewUI::displayCurrentPlayer(const dblk::Team & team)
{
    this->mainWindow_->textTurnTeam->setText(
        QString::fromStdString(to_string(team)));
}

void ViewUI::displayCounters(unsigned moveCounter, bool canPass)
{
    this->mainWindow_->movesAvailableNumber->display(
        QString::fromStdString(std::to_string(moveCounter)));
    this->mainWindow_->throwAvailableNumber->display(
        QString::fromStdString(std::to_string(canPass)));
}

void ViewUI::displayWinner(const std::optional<dblk::Team> & team, bool antiGame)
{
    if (team.has_value())
    {
        this->mainWindow_->stackedWidget->setCurrentIndex(3);
        this->mainWindow_->teamWinnerText->setText(
            QString::fromStdString(to_string(team.value())));

        if (antiGame)
        {
            this->mainWindow_->winReason->setText("Won by Anti-Game!");
        }
        else
        {
            this->mainWindow_->winReason->setText("Achieved the objective row!");
        }
    }
}

void ViewUI:: displayError(std::string errorMsg)
{
    this->mainWindow_->textActionGame->setText("[ERROR] : " + QString::fromStdString(errorMsg));
    std::cout << "ERROR: " << errorMsg << std::endl;
}


void ViewUI::displayGoodByeMessage()
{
    QMessageBox exitBox;
    exitBox.setBaseSize(QSize(350, 150));
    exitBox.setText("Exit");
    exitBox.setInformativeText("Are you sure you want to exit Diaballik");
    exitBox.setIcon(QMessageBox::Question);
    exitBox.setStandardButtons(QMessageBox::Yes | QMessageBox::No);
    int r = exitBox.exec();

    if (r == QMessageBox::Yes)
    {
        qApp->exit();
    }
}

void ViewUI::displaySelected(const std::optional<dblk::Position>
                             position)
{
    if (position.has_value())
    {
        this->mainWindow_->textActionGame->setText("[SELECTION] : Piece selected in "
                + QString::fromStdString(position.value().to_string()));
    }
}

void ViewUI::keyPressEvent(QKeyEvent * event)
{
    if (event->key() == Qt::Key_P && this->mainWindow_->stackedWidget->currentIndex() == 2)
    {
        evntManager_->executeEvent(EventType::PASS_TURN, 0, 0);
    }
}


void ViewUI::update(const dblk::Observable * observable,
                    EventType type)
{
    const Diaballik * game = reinterpret_cast<const Diaballik *>
                             (observable);
    this->mainWindow_->textActionGame->clear();

    switch (type)
    {
        case EventType::MOVE:
            this->displayCounters(game->getMoveCount(), game->canPass());
            this->displayBoard(*game);
            break;

        case EventType::SELECT:
            this->displaySelected(game->getSelected());
            this->displayBoard(*game);
            break;

        case EventType::PASS:
            this->displayCounters(game->getMoveCount(), game->canPass());
            this->displayBoard(*game);
            break;

        case EventType::NEW_GAME:
            if (boardUI_ != nullptr)
                delete boardUI_;
            boardUI_ = new BoardUI(game->getBoard(), evntManager_);
            this->mainWindow_->boardZone->setLayout(boardUI_);
            this->displayCurrentPlayer(game->getCurrentPlayer());
            this->displayCounters(game->getMoveCount(), game->canPass());
            this->mainWindow_->stackedWidget->setCurrentIndex(2);
            this->displayHelp();
            break;

        case EventType::PASS_TURN:
            this->displayCurrentPlayer(game->getCurrentPlayer());
            this->displayCounters(game->getMoveCount(), game->canPass());
            break;

        default:
            break;
    }
}

ViewUI::~ViewUI()
{
    delete mainWindow_;
    delete boardUI_;
}

}

