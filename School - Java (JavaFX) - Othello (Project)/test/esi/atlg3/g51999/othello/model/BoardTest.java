/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.atlg3.g51999.othello.model;

import esi.atlg3.g51999.othello.model.datatype.Position;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Andre Pereira
 */
public class BoardTest {

    public BoardTest() {

    }

    /**
     * Test of iterator method, of class Board.
     */
    @Test
    public void testIterator() {
        System.out.println("iterator");
        Board instance = new Board();

        int cases = 0;
        int expCases = 64;
        int rows = 0;
        int expRows = 8;

        Position prec = new Position(-1, 0);
        for (Position pos : instance) {
            cases++;
            if (pos.getRow() != prec.getRow()) {
                rows++;
            }
            prec = pos;
        }
        assertEquals(expCases, cases);
        assertEquals(expRows, rows);
    }

    /**
     * Test of init method, of class Board.
     */
    @Test
    public void testInit() {
        System.out.println("init");
        Board instance = new Board();
        instance.init();
        Position pos1 = new Position(4, 3);
        Position pos2 = new Position(3, 4);
        Position pos3 = new Position(3, 3);
        Position pos4 = new Position(4, 4);
        assertEquals(new Piece(PlayerColor.BLACK, 1), instance.getPiece(pos1));
        assertEquals(new Piece(PlayerColor.BLACK, 1), instance.getPiece(pos2));
        assertEquals(new Piece(PlayerColor.WHITE, 1), instance.getPiece(pos3));
        assertEquals(new Piece(PlayerColor.WHITE, 1), instance.getPiece(pos4));
    }

    /**
     * Test of equals method, of class Board.
     */
    @Test
    public void testEqualsWhenTrue() {
        System.out.println("equals");
        Board obj = new Board();
        obj.init();
        Board instance = new Board();
        instance.init();
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Board.
     */
    @Test
    public void testEqualsWhenFalse() {
        System.out.println("equals");
        Board obj = new Board();
        obj.init();
        Board instance = new Board();
        instance.init();
        instance.put(new Position(2, 2), new Piece(PlayerColor.BLACK, 1));
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    @Test
    public void testEqualsWhenFalse3() {
        System.out.println("equals");
        String obj = "";
        Board instance = new Board();
        instance.init();
        instance.put(new Position(2, 2), new Piece(PlayerColor.BLACK, 1));
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    @Test
    public void testEqualsWhenFalse2() {
        System.out.println("equals");
        Board obj = null;
        Board instance = new Board();
        instance.init();
        instance.put(new Position(2, 2), new Piece(PlayerColor.BLACK, 1));
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of put method, of class Board.
     */
    @Test
    public void testPut() {
        System.out.println("put");
        Position pos = new Position(2, 2);
        Piece piece = new Piece(PlayerColor.WHITE, 2);
        Board instance = new Board();
        instance.init();
        instance.put(pos, piece);
        Piece expResult = new Piece(PlayerColor.WHITE, 2);
        Piece result = instance.getPiece(pos);
        assertEquals(expResult, result);
    }

    @Test(expected = NullPointerException.class)
    public void testPut2() {
        System.out.println("put");
        Position pos = new Position(2, 2);
        Piece piece = null;
        Board instance = new Board();
        instance.init();
        instance.put(pos, piece);
    }

    @Test(expected = NullPointerException.class)
    public void testPut3() {
        System.out.println("put");
        Position pos = null;
        Piece piece = new Piece(PlayerColor.WHITE, 2);
        Board instance = new Board();
        instance.init();
        instance.put(pos, piece);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPut4() {
        System.out.println("put");
        Position pos = new Position(9, 1);
        Piece piece = new Piece(PlayerColor.WHITE, 2);
        Board instance = new Board();
        instance.init();
        instance.put(pos, piece);
    }

    /**
     * Test of isInside method, of class Board.
     */
    @Test
    public void testIsInsideWhenTrue() {
        System.out.println("isInside");
        Position pos = new Position(6, 7);
        Board instance = new Board();
        boolean expResult = true;
        boolean result = instance.isInside(pos);
        assertEquals(expResult, result);
    }

    @Test
    public void testIsInsideWhenFalse1() {
        System.out.println("isInside");
        Position pos = new Position(0, 8);
        Board instance = new Board();
        boolean expResult = false;
        boolean result = instance.isInside(pos);
        assertEquals(expResult, result);
    }

    @Test
    public void testIsInsideWhenFalse2() {
        System.out.println("isInside");
        Position pos = new Position(8, 0);
        Board instance = new Board();
        boolean expResult = false;
        boolean result = instance.isInside(pos);
        assertEquals(expResult, result);
    }

    @Test
    public void testIsInsideWhenFalse3() {
        System.out.println("isInside");
        Position pos = new Position(-1, 0);
        Board instance = new Board();
        boolean expResult = false;
        boolean result = instance.isInside(pos);
        assertEquals(expResult, result);
    }

    @Test
    public void testIsInsideWhenFalse4() {
        System.out.println("isInside");
        Position pos = new Position(0, -1);
        Board instance = new Board();
        boolean expResult = false;
        boolean result = instance.isInside(pos);
        assertEquals(expResult, result);
    }

    @Test
    public void testHashCode() {
        Board b1 = new Board();
        Board b2 = new Board();
        assertTrue(b1.hashCode() == b2.hashCode());
    }

    @Test
    public void testToString() {
        Board board = new Board();
        String expResult = "Board: 8x8";
        String result = board.toString();
        assertTrue(result.equals(expResult));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsEmptyOutBounds() {
        Position pos = new Position(9, 9);
        Board b = new Board();
        b.isEmpty(pos);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetPieceOutBounds() {
        Position pos = new Position(9, 9);
        Board b = new Board();
        b.getPiece(pos);
    }

    /**
     * Test of getBonusPositions method, of class Board.
     */
    @Test
    public void testPutPieceInBonusPosition() {
        System.out.println("getBonusPositions");
        Board instance = new Board();
        instance.init();
        Piece piece = new Piece(PlayerColor.BLACK, 1);
        instance.put(instance.getBonusPositions().get(0), piece);
        assertTrue(piece.getValue() == 4);
        
    }

}
