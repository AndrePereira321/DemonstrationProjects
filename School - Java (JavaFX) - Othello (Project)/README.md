# Othello

Project Othello for the course Atelier Logiciel I (ATLG3) in HE2B - ESI.

## Authors

De Sousa Pereira, André Filipe - 51999 - C111


## Description

Othello, also known as Reversi is a board strategy game. The game is played in a board of 8x8 squares. There are two players, one of color WHITE and another of color BLACK, with 32 Pieces of his own colour. Each turn a player puts one Piece in the board, and each put must surround at least one Piece of the opponent. The surrounded pieces will change to the color of the player who surrounded that piece. In this version of Othello, each Piece have his own value with 0, 1, 2 or 3 points. The game is over when no player can apply a put, and the winner is the player with the most elevated pieces points number. In the graphics version the player is able to play with a machine bot.


## Installation and Execution

1. Create dist/ directory in root if it not exists. 
2. Open your shell and go to the src/ folder and execute:
    1. $ javac $(find . -name '*.java')
    2. $ cd ../dist
    3. $ java esi.atlg3.g51999.othello.Main
        1. Use -c or --console to launch in console.


## Project Status

First Playable Version in Console


## Built With

- Java

- NetBeans 9.0