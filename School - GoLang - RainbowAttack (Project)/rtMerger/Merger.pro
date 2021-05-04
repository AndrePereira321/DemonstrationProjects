TEMPLATE = app
CONFIG += console c++17
CONFIG -= app_bundle
CONFIG -= qt

QMAKE_CXXFLAGS += -std=c++17 -Ofast

SOURCES += \
        main.cpp

HEADERS += \
    TableFile.hpp
