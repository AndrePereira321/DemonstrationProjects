/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.atlg3.g51999.othello.model;

import esi.atlg3.g51999.othello.model.datatype.Position;
import esi.atlg3.g51999.othello.model.exceptions.NoAvailablePutsException;
import esi.atlg3.g51999.othello.model.exceptions.OccupedSquareException;
import esi.atlg3.g51999.othello.model.exceptions.PutNotSurroundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Andre Pereira
 */
public class GameTest {

    public GameTest() {
    }

    /**
     * Test of getBoard method, of class Game.
     */
    @Test
    public void testGetBoard() {
        System.out.println("getBoard");
        Game instance = new Game();
        instance.initialize();
        Board expResult = new Board();
        expResult.init();
        Board result = instance.getBoard();
        assertEquals(expResult, result);
    }

    /**
     * Test of initialize method, of class Game.
     */
    @Test
    public void testInitialize() {
        System.out.println("initialize");
        Game instance = new Game();
        instance.initialize();
        assertEquals(new Piece(PlayerColor.BLACK, 1),
                instance.getBoard().getPiece(new Position(3, 4)));
        assertEquals(new Piece(PlayerColor.BLACK, 1),
                instance.getBoard().getPiece(new Position(4, 3)));
        assertEquals(new Piece(PlayerColor.WHITE, 1),
                instance.getBoard().getPiece(new Position(3, 3)));
        assertEquals(new Piece(PlayerColor.WHITE, 1),
                instance.getBoard().getPiece(new Position(4, 4)));
        assertEquals(null, instance.getWinner());
    }

    /**
     * Test of isOver method, of class Game.
     */
    @Test
    public void testIsOverWhenFalse() {
        System.out.println("isOver");
        Game instance = new Game();
        boolean expResult = false;
        instance.initialize();
        boolean result = instance.isOver();
        assertEquals(expResult, result);
    }

    /**
     * Test of isOver method, of class Game.
     */
    @Test
    public void testIsOverWhenFalse2()
            throws OccupedSquareException, PutNotSurroundException, NoAvailablePutsException {
        System.out.println("isOver");
        Game instance = new Game();
        boolean expResult = false;
        instance.getBoard().put(new Position(0, 0), new Piece(PlayerColor.BLACK, 1));
        instance.getBoard().put(new Position(0, 1), new Piece(PlayerColor.WHITE, 1));
        instance.getBoard().put(new Position(0, 3), new Piece(PlayerColor.WHITE, 1));
        instance.getBoard().put(new Position(1, 3), new Piece(PlayerColor.BLACK, 1));
        instance.playPiece(new Position(0, 2), new Piece(PlayerColor.BLACK, 1));
        boolean result = instance.isOver();
        assertEquals(expResult, result);
    }

    @Test
    public void testIsOverWhenTrue() {
        System.out.println("isOverFalse");
        Game instance = new Game();
        boolean expResult = true;
        instance.getBoard().put(new Position(3, 3), new Piece(PlayerColor.WHITE, 1));
        instance.getBoard().put(new Position(4, 4), new Piece(PlayerColor.WHITE, 1));
        boolean result = instance.isOver();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCurrentPlayer method, of class Game.
     */
    @Test
    public void testGetCurrentPlayer() {
        System.out.println("getCurrentPlayer");
        Game instance = new Game();
        instance.initialize();
        Player expResult = new Player(PlayerColor.BLACK);
        Player result = instance.getCurrentPlayer();
        assertEquals(expResult, result);
    }

    /**
     * Test of putPiece method, of class Game.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testPutPieceOutBounds()
            throws OccupedSquareException, PutNotSurroundException, NoAvailablePutsException {
        System.out.println("putPiece");
        Position position = new Position(8, 4);
        Piece piece = new Piece(PlayerColor.BLACK, 1);
        Game instance = new Game();
        instance.initialize();
        instance.playPiece(position, piece);
    }

    /**
     * Test of putPiece method, of class Game.
     */
    @Test(expected = PutNotSurroundException.class)
    public void testPutPieceNotSurround()
            throws OccupedSquareException, PutNotSurroundException, NoAvailablePutsException {
        System.out.println("putPiece");
        Position position = new Position(1, 4);
        Piece piece = new Piece(PlayerColor.BLACK, 1);
        Game instance = new Game();
        instance.initialize();
        instance.playPiece(position, piece);
    }

    /**
     * Test of putPiece method, of class Game.
     */
    @Test
    public void testPutPiece1()
            throws OccupedSquareException, PutNotSurroundException, NoAvailablePutsException {
        System.out.println("putPiece");
        Position position = new Position(3, 2);
        Piece piece = new Piece(PlayerColor.BLACK, 1);
        Game instance = new Game();
        instance.initialize();
        instance.playPiece(position, piece);
        Position turnedPiece = new Position(3, 3);
        assertEquals(new Piece(PlayerColor.BLACK, 1), instance.getBoard().getPiece(position));
        assertEquals(new Piece(PlayerColor.BLACK, 1), instance.getBoard().getPiece(turnedPiece));
        assertEquals(1, instance.getCurrentPlayer().getScore());
    }

    /**
     * Test of putPiece method, of class Game.
     */
    @Test
    public void testPutManyPieces1()
            throws OccupedSquareException, PutNotSurroundException, NoAvailablePutsException {
        System.out.println("putPiece");
        Position position = new Position(2, 3);
        Piece piece = new Piece(PlayerColor.BLACK, 1);
        Game instance = new Game();
        instance.initialize();
        instance.playPiece(position, piece);
        Position turnedPiece = new Position(3, 3);
        assertEquals(new Piece(PlayerColor.BLACK, 1), instance.getBoard().getPiece(position));
        assertEquals(new Piece(PlayerColor.BLACK, 1), instance.getBoard().getPiece(turnedPiece));
        assertEquals(1, instance.getCurrentPlayer().getScore());

        instance.playPiece(new Position(2, 2), new Piece(PlayerColor.WHITE, 1));
        Position turnedPiece2 = new Position(3, 3);
        assertEquals(new Piece(PlayerColor.WHITE, 1), instance.getBoard().getPiece(new Position(2, 2)));
        assertEquals(new Piece(PlayerColor.WHITE, 1), instance.getBoard().getPiece(turnedPiece2));
        assertEquals(3, instance.getCurrentPlayer().getScore());
        assertEquals(false, instance.isOver());
    }

    /**
     * Test of putPiece method, of class Game.
     */
    @Test
    public void testPutManyPieces2()
            throws OccupedSquareException, PutNotSurroundException, NoAvailablePutsException {
        System.out.println("putPiece");
        Position position = new Position(2, 3);
        Piece piece = new Piece(PlayerColor.BLACK, 1);
        Game instance = new Game();
        instance.initialize();
        instance.playPiece(position, piece);
        Position turnedPiece = new Position(3, 3);
        assertEquals(new Piece(PlayerColor.BLACK, 1), instance.getBoard().getPiece(position));
        assertEquals(new Piece(PlayerColor.BLACK, 1), instance.getBoard().getPiece(turnedPiece));
        assertEquals(1, instance.getCurrentPlayer().getScore());

        instance.playPiece(new Position(2, 4), new Piece(PlayerColor.WHITE, 1));
        Position turnedPiece2 = new Position(3, 4);
        assertEquals(new Piece(PlayerColor.WHITE, 1), instance.getBoard().getPiece(new Position(2, 4)));
        assertEquals(new Piece(PlayerColor.WHITE, 1), instance.getBoard().getPiece(turnedPiece2));
        assertEquals(3, instance.getCurrentPlayer().getScore());
        assertEquals(false, instance.isOver());
    }

    @Test
    public void testPutManyPiecesRefreshScore()
            throws OccupedSquareException, PutNotSurroundException, NoAvailablePutsException {
        System.out.println("putPiece");
        Position position = new Position(2, 3);
        Piece piece = new Piece(PlayerColor.BLACK, 3);
        Game instance = new Game();
        instance.initialize();
        instance.playPiece(position, piece);
        Position turnedPiece = new Position(3, 3);
        assertEquals(new Piece(PlayerColor.BLACK, 3), instance.getBoard().getPiece(position));
        assertEquals(new Piece(PlayerColor.BLACK, 1), instance.getBoard().getPiece(turnedPiece));
        assertEquals(1, instance.getCurrentPlayer().getScore());
        instance.playPiece(new Position(2, 2), new Piece(PlayerColor.WHITE, 2));
        Position turnedPiece2 = new Position(3, 3);
        assertEquals(new Piece(PlayerColor.WHITE, 2), instance.getBoard().getPiece(new Position(2, 2)));
        assertEquals(new Piece(PlayerColor.WHITE, 1), instance.getBoard().getPiece(turnedPiece2));
        assertEquals(5, instance.getCurrentPlayer().getScore());
        assertEquals(false, instance.isOver());
        Player expWinner = new Player(PlayerColor.BLACK);
        expWinner.addScore(3);
        assertEquals(expWinner, instance.getWinner());
    }

    @Test
    public void testGetScores()
            throws OccupedSquareException, PutNotSurroundException, NoAvailablePutsException {
        System.out.println("getScores");
        Position position = new Position(2, 3);
        Piece piece = new Piece(PlayerColor.BLACK, 3);
        Game instance = new Game();
        instance.initialize();
        instance.playPiece(position, piece);
        HashMap<PlayerColor, Integer> expResult = new HashMap();
        expResult.put(PlayerColor.BLACK, 6);
        expResult.put(PlayerColor.WHITE, 1);
        HashMap<PlayerColor, Integer> result = instance.getScores();
        assertEquals(expResult, result);
        Player expWinner = new Player(PlayerColor.BLACK);
        expWinner.addScore(4);
        assertEquals(expWinner, instance.getWinner());
    }

    @Test(expected = OccupedSquareException.class)
    public void testPutOccupedSquare()
            throws OccupedSquareException, PutNotSurroundException, NoAvailablePutsException {
        Game instance = new Game();
        instance.initialize();
        instance.playPiece(new Position(3, 3), new Piece(PlayerColor.WHITE, 2));
    }

    @Test
    public void testPutOpponentPiece()
            throws OccupedSquareException, PutNotSurroundException, NoAvailablePutsException {
        Game instance = new Game();
        instance.initialize();
        instance.playPiece(new Position(3, 2), null);
    }

    /**
     * Test of putPiece method, of class Game.
     */
    @Test(expected = NoAvailablePutsException.class)
    public void SwapPlayer()
            throws OccupedSquareException, PutNotSurroundException, NoAvailablePutsException {
        System.out.println("putPiece");
        Position position = new Position(0, 2);
        Piece piece = new Piece(PlayerColor.BLACK, 1);
        Game instance = new Game();
        instance.getBoard().put(new Position(0, 1), new Piece(PlayerColor.WHITE, 1));
        instance.getBoard().put(new Position(0, 0), new Piece(PlayerColor.BLACK, 1));
        instance.getBoard().put(new Position(1, 2), new Piece(PlayerColor.WHITE, 1));
        instance.playPiece(position, piece);
    }

    @Test
    public void testGetAvailablePuts()
            throws OccupedSquareException, PutNotSurroundException, NoAvailablePutsException {
        Game instance = new Game();
        instance.initialize();
        List<Position> expResult = new ArrayList();
        expResult.add(new Position(2, 3));
        expResult.add(new Position(3, 2));
        expResult.add(new Position(4, 5));
        expResult.add(new Position(5, 4));
        List<Position> result = instance.getCurrentAvailablePuts();
        assertEquals(expResult, result);
    }

    @Test(expected = NoAvailablePutsException.class)
    public void testNoPuts()
            throws OccupedSquareException, PutNotSurroundException, NoAvailablePutsException {
        Game instance = new Game();
        instance.getBoard().put(new Position(0, 1), new Piece(PlayerColor.WHITE, 1));
        instance.getBoard().put(new Position(0, 2), new Piece(PlayerColor.BLACK, 1));
        instance.getBoard().put(new Position(1, 1), new Piece(PlayerColor.WHITE, 1));
        instance.playPiece(new Position(0, 0), new Piece(PlayerColor.BLACK, 1));
    }

    @Test
    public void testSetPlayersNames() {
        Game instance = new Game();
        instance.initialize();
        HashMap<PlayerColor, String> names = new HashMap();
        names.put(PlayerColor.BLACK, "BLACK");
        names.put(PlayerColor.WHITE, "WHITE");
        instance.setPlayersNames(names);
        assertTrue(instance.getCurrentPlayer().getName().equals("BLACK"));
        assertTrue(instance.getOpponentPlayer().getName().equals("WHITE"));
    }

    @Test
    public void testPassTurn() {
        Game instance = new Game();
        instance.initialize();
        instance.passTurn();
        assertTrue(instance.getCurrentPlayer().getColor() == PlayerColor.WHITE);
    }

    @Test
    public void testPieceCount1() {
        Game instance = new Game();
        instance.initialize();
        assertTrue(instance.getPieceCount() == 4);
    }

    @Test
    public void testPieceCount2() throws Exception {
        Game instance = new Game();
        instance.initialize();
        instance.playPiece(new Position(2, 3), instance.getCurrentPlayer().playPiece());
        instance.playPiece(new Position(2, 2), instance.getCurrentPlayer().playPiece());
        assertTrue(instance.getPieceCount() == 6);
    }
}
