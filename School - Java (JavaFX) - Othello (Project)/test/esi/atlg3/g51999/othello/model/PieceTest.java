/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.atlg3.g51999.othello.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Andre Pereira
 */
public class PieceTest {

    public PieceTest() {
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBadValuePiece1() {
        Piece piece = new Piece(PlayerColor.BLACK, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBadValuePiece2() {
        Piece piece = new Piece(PlayerColor.BLACK, 4);
    }

    /**
     * Test of getColor method, of class Piece.
     */
    @Test
    public void testGetColor() {
        System.out.println("getColor");
        Piece instance = new Piece(PlayerColor.BLACK, 1);
        PlayerColor expResult = PlayerColor.BLACK;
        PlayerColor result = instance.getColor();
        assertEquals(expResult, result);
    }

    /**
     * Test of swapColor method, of class Piece.
     */
    @Test
    public void testSwapColor() {
        System.out.println("swapColor");
        Piece instance = new Piece(PlayerColor.WHITE, 1);
        instance.swapColor();
        PlayerColor expResult = PlayerColor.BLACK;
        PlayerColor result = instance.getColor();
        assertEquals(expResult, result);
    }

    /**
     * Test of swapColor method, of class Piece.
     */
    @Test
    public void testSwapColor2() {
        System.out.println("swapColor");
        Piece instance = new Piece(PlayerColor.BLACK, 1);
        instance.swapColor();
        PlayerColor expResult = PlayerColor.WHITE;
        PlayerColor result = instance.getColor();
        assertEquals(expResult, result);
    }

    /**
     * Test of swapColor method, of class Piece.
     */
    @Test
    public void testSwapColor3() {
        System.out.println("swapColor");
        Piece instance = new Piece(PlayerColor.BLACK, 1);
        instance.swapColor();
        PlayerColor expResult = PlayerColor.WHITE;
        PlayerColor result = instance.getColor();
        assertEquals(expResult, result);
        expResult = PlayerColor.BLACK;
        instance.swapColor();
        result = instance.getColor();
        assertEquals(expResult, result);
    }

    /**
     * Test of swapColor method, of class Piece.
     */
    @Test(expected = IllegalStateException.class)
    public void testSwapColor4() {
        System.out.println("swapColor");
        Piece instance = new Piece(null, 0);
        instance.swapColor();
    }

    /**
     * Test of equals method, of class Piece.
     */
    @Test
    public void testEqualsWhenTrue() {
        System.out.println("equals");
        Object obj = new Piece(PlayerColor.WHITE, 1);
        Piece instance = new Piece(PlayerColor.WHITE, 1);
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Piece.
     */
    @Test
    public void testEqualsWhenFalse() {
        System.out.println("equals");
        Object obj = new Piece(PlayerColor.BLACK, 1);
        Piece instance = new Piece(PlayerColor.WHITE, 1);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Piece.
     */
    @Test
    public void testEqualsWhenFalse2() {
        System.out.println("equals");
        Object obj = null;
        Piece instance = new Piece(PlayerColor.WHITE, 1);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Piece.
     */
    @Test
    public void testEqualsWhenFalse3() {
        System.out.println("equals");
        String obj = "";
        Piece instance = new Piece(PlayerColor.WHITE, 1);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Piece.
     */
    @Test
    public void testEqualsWhenFalse4() {
        System.out.println("equals");
        Piece piece = new Piece(PlayerColor.WHITE, 2);
        Piece instance = new Piece(PlayerColor.WHITE, 1);
        boolean expResult = false;
        boolean result = instance.equals(piece);
        assertEquals(expResult, result);
    }

    /**
     * Test of getValue method, of class Piece.
     */
    @Test
    public void testGetValue() {
        System.out.println("getValue");
        Piece instance = new Piece(PlayerColor.WHITE, 3);
        int expResult = 3;
        int result = instance.getValue();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    @Test
    public void testHashCode1() {
        Piece p1 = new Piece(PlayerColor.WHITE, 2);
        Piece p2 = new Piece(PlayerColor.WHITE, 2);
        assertEquals(p1.hashCode(), p2.hashCode());
    }

    @Test
    public void testHashCode2() {
        Piece p1 = new Piece(PlayerColor.WHITE, 2);
        Piece p2 = new Piece(PlayerColor.WHITE, 1);
        assertFalse(p1.hashCode() == p2.hashCode());
    }

    /**
     * Test of setBonusValue method, of class Piece.
     */
    @Test
    public void testSetBonusValue() {
        System.out.println("setBonusValue");
        Piece instance = new Piece(PlayerColor.WHITE, 3);
        instance.setBonusValue();
        assertTrue(instance.getValue() == 6);
    }

   

}
