/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.atlg3.g51999.othello.model;

import esi.atlg3.g51999.othello.model.datatype.Position;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Andre Pereira
 */
public class PlayerTest {

    public PlayerTest() {
    }

    /**
     * Test of getColor method, of class Player.
     */
    @Test
    public void testGetColor() {
        System.out.println("getColor");
        Player instance = new Player(PlayerColor.WHITE);
        PlayerColor expResult = PlayerColor.WHITE;
        PlayerColor result = instance.getColor();
        assertEquals(expResult, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPlayerWithoutColor() {
        Player player = new Player(null);
    }

    /**
     * Test of equals method, of class Player.
     */
    @Test
    public void testEqualsWhenTrue() {
        System.out.println("equals");
        Object obj = new Player(PlayerColor.BLACK);
        Player instance = new Player(PlayerColor.BLACK);
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    @Test
    public void testEqualsWhenFalse() {
        System.out.println("equals");
        Object obj = new Player(PlayerColor.WHITE);
        Player instance = new Player(PlayerColor.BLACK);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }
    
        @Test
    public void testEqualsWhenFalse4() {
        System.out.println("equals");
        Object obj = new Player(PlayerColor.WHITE);
        Player instance = new Player(PlayerColor.WHITE);
        instance.addScore(1);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    @Test
    public void testEqualsWhenFalse2() {
        System.out.println("equals");
        Object obj = new Position(2, 3);
        Player instance = new Player(PlayerColor.BLACK);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    @Test
    public void testEqualsWhenFalse3() {
        System.out.println("equals");
        Object obj = null;
        Player instance = new Player(PlayerColor.BLACK);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of getScore method, of class Player.
     */
    @Test
    public void testGetScore() {
        System.out.println("getScore");
        Player instance = new Player(PlayerColor.BLACK);
        int expResult = 2;
        int result = instance.getScore();
        assertEquals(expResult, result);
    }

    /**
     * Test of getScore method, of class Player.
     */
    @Test
    public void testGetScore2() {
        System.out.println("getScore");
        Player instance = new Player(PlayerColor.BLACK);
        instance.addScore(10);
        int expResult = 12;
        int result = instance.getScore();
        assertEquals(expResult, result);
    }

    /**
     * Test of getScore method, of class Player.
     */
    @Test
    public void testGetScore3() {
        System.out.println("getScore");
        Player instance = new Player(PlayerColor.BLACK);
        instance.addScore(-2);
        int expResult = 0;
        int result = instance.getScore();
        assertEquals(expResult, result);
    }

    /**
     * Test of addScore method, of class Player.
     */
    @Test
    public void testAddScore() {
        System.out.println("addScore");
        int delta = 99;
        Player instance = new Player(PlayerColor.BLACK);
        instance.addScore(delta);
        int expResult = 101;
        int result = instance.getScore();
        assertEquals(expResult, result);
    }

    @Test
    public void testPlayPiece() {
        Player instance = new Player(PlayerColor.BLACK);
        instance.init();
        for (int i = 0; i < 30; i++) {
            System.out.println(instance.playPiece());
        }
    }

    @Test
    public void testPlayPiece2() {
        Player instance = new Player(PlayerColor.BLACK);
        instance.init();
        for (int i = 0; i < 32; i++) {
            System.out.println(instance.playPiece());
        }
        Piece expResult = null;
        Piece result = instance.playPiece();
        assertEquals(expResult, result);
    }

    @Test
    public void testHashCode() {
        Player player1 = new Player(PlayerColor.BLACK);
        Player player2 = new Player(PlayerColor.BLACK);
        assertEquals(player1.hashCode(), player2.hashCode());
    }

    @Test
    public void testToString() {
        Player player = new Player(PlayerColor.WHITE);
        String expResult = "Player: WHITE / Score: 2";
        String result = player.toString();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testSetName() {
        Player player = new Player(PlayerColor.BLACK);
        player.setName("OLAAA");
        assertTrue(player.getName().equals("OLAAA"));
    }

}
