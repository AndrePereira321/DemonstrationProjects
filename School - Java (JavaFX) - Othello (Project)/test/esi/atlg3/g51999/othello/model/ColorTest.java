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
public class ColorTest {
    
    public ColorTest() {
    }

    /**
     * Test of values method, of class PlayerColor.
     */
    @Test
    public void testValues() {
        System.out.println("values");
        PlayerColor[] expResult = {PlayerColor.BLACK, PlayerColor.WHITE};
        PlayerColor[] result = PlayerColor.values();
        assertArrayEquals(expResult, result);
        
    }

    /**
     * Test of valueOf method, of class PlayerColor.
     */
    @Test
    public void testValueOf() {
        System.out.println("valueOf");
        String name = "BLACK";
        PlayerColor expResult = PlayerColor.BLACK;
        PlayerColor result = PlayerColor.valueOf(name);
        assertEquals(expResult, result);
    }
    
        /**
     * Test of valueOf method, of class PlayerColor.
     */
    @Test
    public void testValueOf2() {
        System.out.println("valueOf");
        String name = "WHITE";
        PlayerColor expResult = PlayerColor.WHITE;
        PlayerColor result = PlayerColor.valueOf(name);
        assertEquals(expResult, result);
    }
    
    
}
