#ifndef _BOARD_H
#define _BOARD_H

#include <vector>
#include <optional>
#include "Piece.hpp"
#include "Position.hpp"
#include "Team.hpp"

namespace dblk
{

/**
 * @brief The Board class
 *
 * This class represents the Board where the game will be played.
 * It will contain a vector of vectors containing the Pieces of the players.
 */
class Board
{

  private:

    /**
     * @brief pieces
     *
     * The main container for the Pieces in the board.
     */
    std::vector<std::vector<std::optional<Piece>>> pieces_;

    /**
     * @brief size
     *
     * The size of the board.
     */
    size_t size_;


  public:

    Board();

    /**
     * @brief init
     *
     * Puts the Pieces inside of the Board according the
     * Diaballik rules. The position of the initial pieces will
     * change according the variant rule.
     *
     * @param variant Is the variant rule applied or not.
     *     * @param size The size of the board.
     */
    void init(bool variant, const size_t size);

    /**
     * @brief getSize
     *
     * Retrieves the size of the board.
     *
     * @return The size of the board.
     */
    size_t getSize() const;

    /**
     * @brief getPieceAt
     *
     * Retrieves the Piece at the given Position.
     *
     * @param position The position to retrieve the piece.
     * @return The piece at the given position, or an empty optional.
     */
    const std::optional<Piece> & getPieceAt(const Position & position)
    const;

    /**
     * @brief isFree
     *
     * Verifies if the position has a piece.
     *
     * @param pos The position to verify.
     * @exception If the position is out bounds.
     * @return True if there is a piece.
     */
    bool isFree(const Position & position) const;

    /**
     * @brief isFree
     *
     * Verifies if the position is inside of the board.
     *
     * @param pos The position to verify.
     * @return True if is inside of the board.
     */
    bool isInside(const Position & position) const;

    /**
     * @brief movePiece
     *
     * Moves the Piece of the start Position to the end Position.
     * It is considered invalid to move to a already occuped position,
     * or out the bonds of the board.
     *
     * @param startPos The start Position of the Piece.
     * @param endPos The end Position of the Piece.
     * @return 1 if the Piece was moved or a negative flag in case of error.
     */
    int movePiece(const Position & startPos, const Position & endPos);


    /**
     * @brief checksAntiGame
     *
     * Verifies if the given Player faces a situation of anti game.
     *
     * @param team The player to verify.
     * @return True in case of anti game situation.
     */
    bool checksAntiGame(Team antiGameVictim) const;

    /**
     * @brief checksGameIsFinsh
     *
     * Verifies if the game is over.
     *
     * @param team An empty optional that will change according the winner.
     * @return True if the game is over.
     */
    bool checksGameIsFinsh(std::optional<Team> & winner) const;

    /**
     * @brief passBall
     *
     * Passes the ball between two pieces.
     * To pass the ball the end position must have a Piece from
     * the same color of the piece in the start Position, and must be
     * aligned without any opponent piece blocking the path.
     *
     * @param team The team that will pass the ball.
     * @param startPos The start position.
     * @param endPos The end position.
     * @return 1 if the ball has been passed or a negative flag if not.
     */
    int passBall(Team team, const Position & startPos,
                 const Position & endPos);

    /**
     * @brief checkMove
     *
     * Verifies if it's possible to move a Piece with
     * the given positions.
     *
     * @param startPos The start position.
     * @param endPos The end position.
     * @return 1 if it's allowed, negatif flag if not.
     */
    int checkMove(const Position & startPos,
                  const Position & endPos) const;

    /**
     * @brief checkThrow
     *
     * Verifies if it's possible to pass a Piece with
     * the given positions.
     *
     * @param team The theam that will pass the ball.
     * @param startPos The start position.
     * @param endPos The end position.
     * @return True if it's allowed.
     */
    int checkThrow(Team team, const Position & startPos,
                   const Position & endPos) const;

  private:

    /**
     * @brief verifyLineAntiGame
     *
     * Verifiy if there is a line of anti game
     *
     * @param currentColumn the current column
     * @param blockCount the blockcount
     * @param antiGameVictim the team victim of the anti game.
     * @return true if there is a line of anti game.
     */
    bool verifyLineAntiGame(const Position & currentPos,
                            unsigned blockCount,
                            Team antiGameVictim) const;

    /**
     * @brief countBlockedOpponents
     *
     * Count the number of opponent pieces blocked by one piece. Used by verifyLineAntiGame.
     *
     * @param blockCount A reference to the number of opponent pieces blocked.
     * @param curentPos The current position.
     * @param team The victim of anti-game line.
     */
    void countBlockedOpponents(unsigned & blockCount,
                               const Position & currentColumn,
                               Team team) const;

    /**
     * @brief isBlockedByLine
     *
     * Checks if the given position is blocked by a line of opponent.
     *
     * @param position the position of the piece.
     * @param antiGameVictim the anti game victim
     * @param objectiveRow the objective row of the piece
     * @return true if the position is blocked by opponent.
     */
    bool isBlockedByLine(const Position & position, Team antiGameVictim,
                         size_t objectiveRow) const;

    /**
     * @brief checkLineBreak
     *
     * Checks if a line has been breaked by the antiGameVictim
     *
     * @param curentColumn the current column position of the piece.
     * @param antiGameVictim the anti game victim team
     * @return true if the line on the board is breaked
     */
    bool checkLineBreak(const Position & curentColumn,
                        Team antiGameVictim) const;

    /**
     * @brief hasDepassedLine
     *
     * Checks if a piece in the currentLine has depassed the line of potential anti game.
     *
     * @param currentLine the current line position of the piece
     * @param dir the direction position
     * @param antiGameVictim the team potential anti game victim
     * @param objectiveRow the objective row of the piece.
     * @return true if it has depassed line.
     */
    bool hasDepassedLine(Position & currentLine, const Position & dir,
                         Team antiGameVictim,
                         int objectiveRow) const;

    /**
     * @brief achievedObjective
     *
     * Verifies if the given position inside the board, contains a piece
     * who reached the objective with the ball.
     *
     * @param board The board of the game.
     * @param position The given position.
     * @return True if the board contains a piece who reached the objective.
     */
    bool achievedObjective(const Position & currentPosition) const;



    /**
     * @brief belongsTo
     *
     * Checks if a piece in that position inside the board belongs to the team given.
     *
     * @param position the position of the piece.
     * @param player the player of the team concerned.
     * @return true if it belongs to the team given.
     */
    bool belongsTo(const Position & position, Team player) const;



};

}
#endif //_BOARD_H
