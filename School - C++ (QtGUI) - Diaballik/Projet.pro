TEMPLATE = app
CONFIG += console # c++11
CONFIG += c++17

QT       += core gui
greaterThan(QT_MAJOR_VERSION, 4): QT += widgets

win32: RC_ICONS = Projet.ico
win64: RC_ICONS = Projet.ico
__APPLE__:ICON = Projet.icns

QMAKE_CXXFLAGS += -pedantic-errors -std=c++17 -Wall

SOURCES += \
        src/controller/EventManager.cpp \
        src/controller/NewGameEvent.cpp \
        src/controller/SquareClickedEvent.cpp \
        src/main.cpp \
        src/controller/Controller.cpp \
        src/controller/MoveEvent.cpp \
        src/controller/PassEvent.cpp \
        src/controller/PassTurnEvent.cpp \
        src/controller/SelectEvent.cpp \
        src/model/Board.cpp \
        src/model/Diaballik.cpp \
        src/model/Observable.cpp \
        src/model/Position.cpp \
        src/model/Piece.cpp \
        src/model/Team.cpp \
        src/view/gui/BoardUI.cpp \
        src/view/gui/SquareUI.cpp \
        src/view/gui/ViewUI.cpp

HEADERS += \
    src/controller/headers/DiaballikEvent.hpp \
    src/controller/headers/EventManager.hpp \
    src/controller/headers/EventType.hpp \
    src/controller/headers/NewGameEvent.hpp \
    src/controller/headers/SquareClickedEvent.hpp \
    src/view/gui/headers/BoardUI.hpp \
    src/view/gui/headers/SquareUI.hpp \
    src/view/gui/headers/ViewUI.hpp \
    src/view/headers/ErrorMessages.hpp \
    src/view/headers/Observer.hpp \
    src/view/headers/View.hpp \
    src/controller/headers/Controller.hpp \
    src/controller/headers/MoveEvent.hpp \
    src/controller/headers/PassEvent.hpp \
    src/controller/headers/PassTurnEvent.hpp \
    src/controller/headers/SelectEvent.hpp \
    src/model/headers/Board.hpp \
    src/controller/headers/Configs.hpp \
    src/model/headers/Diaballik.hpp \
    src/model/headers/Observable.hpp \
    src/model/headers/Piece.hpp \
    src/model/headers/Position.hpp \
    src/model/headers/Team.hpp

RESOURCES += \
    resources.qrc

FORMS += \
    src/view/gui/MainWindow.ui
