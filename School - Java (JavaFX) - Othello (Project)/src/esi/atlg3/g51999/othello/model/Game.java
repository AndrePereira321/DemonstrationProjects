package esi.atlg3.g51999.othello.model;

import esi.atlg3.g51999.othello.model.datatype.Direction;
import esi.atlg3.g51999.othello.model.datatype.Position;
import static esi.atlg3.g51999.othello.model.PlayerColor.*;
import static esi.atlg3.g51999.othello.model.datatype.OthelloAction.*;
import esi.atlg3.g51999.othello.model.datatype.ActionDetail;
import esi.atlg3.g51999.othello.model.datatype.PutData;
import esi.atlg3.g51999.othello.model.exceptions.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Othello is a strategy game for two Players: Black and White. It is played on
 * a unicolored Board of 64 Squares, 8 of 8, called Othello board. These players
 * have 64 two-tone pawns, to put his Pieces. The goal of the game is to have
 * more Pieces than the opponent at the end of the game. This one ends when no
 * player can play legal kick. The
 *
 * @author Andre Pereira
 */
public class Game extends Observable implements Model {

    private final Board board;
    private Player current;
    private Player opponent;
    private int pieceCount;

    //Saves the list of available puts to not iterate the Board to many times 
    //each turn, and used in View (Refreshed in swapPlayer).
    private List<Position> currentAvailablePuts;
    private List<Position> opponentAvailablePuts;

    /**
     * Creates all the structures in the Game: Board, Current Player, Opponent
     * Player, and the lists of available puts for the two players.
     */
    public Game() {
        super();
        this.board = new Board();
        this.current = new Player(BLACK);
        this.opponent = new Player(WHITE);
        this.currentAvailablePuts = new ArrayList();
        this.opponentAvailablePuts = new ArrayList();
        this.pieceCount = 0;
    }

    /**
     * Starts the game according with the Othello rules.
     */
    @Override
    public void initialize() {
        this.board.init();
        this.current.init();
        this.opponent.init();
        this.currentAvailablePuts = this.availablePuts(this.current);
        this.opponentAvailablePuts = this.availablePuts(this.opponent);
        this.pieceCount = 4;
    }

    /**
     * Retrieves the Board of the Game.
     *
     * @return The Board of the Game.
     */
    @Override
    public Board getBoard() {
        return this.board;
    }

    /**
     * Retrieves the player who must play the next Piece.
     *
     * @return The player who must play the next Piece.
     */
    @Override
    public Player getCurrentPlayer() {
        return this.current;
    }

    /**
     * Retrieves the opponent player.
     *
     * @return The opponent player.
     */
    @Override
    public Player getOpponentPlayer() {
        return this.opponent;
    }

    /**
     * Gets the list of available puts for the current player.
     *
     * @return The list of available puts for the current player.
     */
    @Override
    public List<Position> getCurrentAvailablePuts() {
        return currentAvailablePuts;
    }

    /**
     * Retrieves the number of pieces in the board.
     *
     * @return The number of pieces in the board.
     */
    @Override
    public int getPieceCount() {
        return this.pieceCount;
    }

    @Override
    public void setPlayersNames(HashMap<PlayerColor, String> names) {
        for (PlayerColor color : names.keySet()) {
            if (this.current.getColor() == color) {
                this.current.setName(names.get(color));
            } else if (this.opponent.getColor() == color) {
                this.opponent.setName(names.get(color));
            }
        }
        this.notifyObservers(new ActionDetail(START_GAME, this.current, null));
    }

    @Override
    public HashMap<PlayerColor, Integer> getPlayerPieceCounter() {
        HashMap<PlayerColor, Integer> playersPieceCounter = new HashMap();
        playersPieceCounter.put(this.current.getColor(), this.current.getPutedPieceCounter());
        playersPieceCounter.put(this.opponent.getColor(), this.opponent.getPutedPieceCounter());
        return playersPieceCounter;
    }

    @Override
    public HashMap<PlayerColor, String> getPlayersNames() {
        HashMap<PlayerColor, String> playersPieceCounter = new HashMap();
        playersPieceCounter.put(this.current.getColor(), this.current.getName());
        playersPieceCounter.put(this.opponent.getColor(), this.opponent.getName());
        return playersPieceCounter;
    }

    /**
     * Changes the current player with the opponent.
     */
    @Override
    public void passTurn() {
        Player tmp = this.current;
        this.current = this.opponent;
        this.opponent = tmp;
        this.currentAvailablePuts = this.availablePuts(this.current);
        this.opponentAvailablePuts = this.availablePuts(this.opponent);
        this.notifyObservers(new ActionDetail(PASS_TURN, this.opponent, null));
    }

    /**
     * Checks if the game is over. The game is over when the two players have no
     * available moves.
     *
     * @return True if the game is over.
     */
    @Override
    public boolean isOver() {
        return this.currentAvailablePuts.isEmpty() && this.opponentAvailablePuts.isEmpty();
    }

    /**
     * Retrieves the player who has win the game.
     *
     * @return The winner of the game, or null if the two players have the same
     * score.
     */
    @Override
    public Player getWinner() {
        if (this.current.getScore() > this.opponent.getScore()) {
            return this.current;
        } else if (this.current.getScore() < this.opponent.getScore()) {
            return this.opponent;
        } else {
            return null;
        }
    }

    /**
     * Gets the number of Pieces of each PlayerColor in the Board.
     *
     * @return An HashMap containing the number of pieces in the board.
     */
    @Override
    public HashMap<PlayerColor, Integer> getScores() {
        HashMap<PlayerColor, Integer> counter = new HashMap();
        counter.put(this.current.getColor(), this.current.getScore());
        counter.put(this.opponent.getColor(), this.opponent.getScore());
        return counter;
    }

    /**
     * Puts the given Piece in the given Position.To put one Piece in the board
     * the player must surround at least one of the opponent's piece.
     *
     * @param position The position to put the Piece.
     * @param piece The piece to put.
     * @exception IllegalArgumentException If the given position is out bounds.
     * @throws OccupedSquareException Indicates that the position refers an
     * occuped square.
     * @throws PutNotSurroundException Indicates that the put will not surround
     * any enemy Piece.
     * @throws NoAvailablePutsException Indicates that the next Player don't
     * have any available put.
     */
    @Override
    public void playPiece(Position position, Piece piece)
            throws OccupedSquareException, PutNotSurroundException, NoAvailablePutsException {

        this.validatePos(piece, position); //Exceptions
        if (piece == null) {
            piece = this.opponent.playPiece();
            piece.swapColor();
        }

        //Receives the pieces of the opponent to turn
        //Modified after checkPut.
        List<Position> piecesToTurn = new ArrayList();

        if (checkPut(position, piecesToTurn, this.current)) {
            this.putPiece(position, piece, piecesToTurn);
            this.swapPlayer();
            ++this.pieceCount;
            if (this.currentAvailablePuts.isEmpty() && !this.opponentAvailablePuts.isEmpty()) {
                //Auto swap the player if the next player dont have any available puts.
                this.swapPlayer();
                this.notifyObservers(new ActionDetail(PLAY_PIECE, this.current,
                        new PutData(piecesToTurn, position, piece)));
                throw new NoAvailablePutsException("There is no available puts.");
            }
            this.notifyObservers(new PutData(piecesToTurn, position, piece));

        } else {
            this.current.addPiece(piece);
            throw new PutNotSurroundException(
                    "You don't surround any enemy Piece at the given position!");
        }
    }

    /**
     * Validates the position. Must be inside of the board, and can not refer an
     * occuped square.
     *
     * @param position The position to validate.
     * @throws OccupedSquareException If the position refers an occuped square.
     * @exception IllegalArgumentException If the position is not inside of the
     * board.
     */
    private void validatePos(Piece piece, Position position) throws OccupedSquareException {
        if (!this.board.isInside(position)) {
            throw new IllegalArgumentException(
                    "The given Position it's out bounds!"
            );
        } else if (!this.board.isEmpty(position)) {
            this.current.addPiece(piece);
            throw new OccupedSquareException(
                    "This square is already taken by the player: "
                    + this.board.getPiece(position).getColor()
            );
        }
    }

    /**
     * Puts the given piece in the given position, and swaps the color from the
     * surrounded enemy Pieces. Must be called after validate the position with
     * validatePos(Position) and checkPut(Position, List(Piece), Player).
     *
     * @param position The position to put the piece.
     * @param piece The piece to put.
     * @param piecesToTurn The list of surrounded pieces to swap color.
     */
    private void putPiece(Position position, Piece piece, List<Position> piecesToTurn) {
        this.board.put(position, piece);
        this.current.addScore(piece.getValue());
        this.current.addPutedPiece(1);
        piecesToTurn.forEach((pos) -> {
            Piece oppPiece = this.board.getPiece(pos);
            oppPiece.swapColor();
            this.current.addPutedPiece(1);
            this.current.addScore(oppPiece.getValue());
            this.opponent.addScore(-oppPiece.getValue());
            this.opponent.addPutedPiece(-1);
        });
    }

    /**
     * Verifies if the current player can put one Piece in the given position,
     * surrounding at least any enemy Piece. A second parameter, a list of
     * pieces, is used to know what are the pieces that must be turned. This
     * function will consider all the possible directions, including diagonal.
     *
     * @param position The position to check if the current player can put one
     * piece.
     * @param toTurn An output parameter that allows in the exterior of this
     * method to know what are the pieces who must swap color. This list will be
     * modified, and will have no element if this method return false.
     * @return True if the current player can put one piece is the given
     * Position.
     */
    private boolean checkPut(Position position, List<Position> toTurn, Player player) {
        toTurn.clear();
        for (Direction direction : Direction.values()) {
            List<Position> checkToTurn = new ArrayList();
            if (verifyDirectionSurround(position, direction, checkToTurn, player)) {
                toTurn.addAll(checkToTurn);
            }
        }
        return !toTurn.isEmpty();
    }

    /**
     * Verifies if the player puts one Piece in the given Position, if that will
     * surround any opponent Piece in the given direction.
     *
     * @param start The start position to verify.
     * @param direction The direction to verify if have any opponent Piece to
     * surround.
     * @param checkToTurn An output parameter that will conserve in the exterior
     * of this method the pieces that must swap color in this direction. Even if
     * the current player can't surround any enemy Piece, this list may not be
     * empty.
     * @param player The player who wants to play a piece.
     * @return True if the current player will surround any enemy Piece at this
     * direction.
     */
    private boolean verifyDirectionSurround(Position start, Direction direction,
            List<Position> checkToTurn, Player player) {

        Position nextPos = direction.nextPos(start);
        if (!this.board.isInside(nextPos)) {
            return false;
        }
        if (this.board.isEmpty(nextPos)) {
            return false;
        } else if (this.board.getPiece(nextPos).getColor() == player.getColor()) {
            return !checkToTurn.isEmpty() && true;
        } else {
            checkToTurn.add(nextPos);
            return verifyDirectionSurround(nextPos, direction, checkToTurn, player);
        }
    }

    /**
     * Retrieves a list of the available positions for the player puts his
     * Piece.
     *
     * @param player The player to verify the available puts position.
     *
     * @return A list of the available positions.
     */
    private List<Position> availablePuts(Player player) {
        List<Position> availablePositions = new ArrayList();
        for (Position position : this.board) {
            if (!this.board.isEmpty(position)) {
                continue;
            }
            for (Direction direction : Direction.values()) {
                if (verifyDirectionSurround(position, direction, new ArrayList(), player)) {
                    availablePositions.add(position);
                    break;
                }
            }
        }
        return availablePositions;
    }

    /**
     * This particular notifyObservers will notify the Observers when a Piece is
     * putted. In case of the end game it will notify the end of the game.
     *
     * @param putData The detailed information about the putted piece.
     * @throws NoAvailablePutsException If the next player has no available
     * puts.
     */
    private void notifyObservers(PutData putData) throws NoAvailablePutsException {
        this.notifyObservers(new ActionDetail(PLAY_PIECE, this.opponent, putData));
        if (this.isOver()) {
            this.notifyObservers(new ActionDetail(WIN_GAME, this.getWinner(), null));
        }
    }

    /**
     * Swaps the current player after player the piece. In difference with
     * passTurn() method this is automatically, so not need to notify.
     */
    private void swapPlayer() {
        Player tmp = this.current;
        this.current = this.opponent;
        this.opponent = tmp;
        this.currentAvailablePuts = this.availablePuts(this.current);
        this.opponentAvailablePuts = this.availablePuts(this.opponent);
    }
}
