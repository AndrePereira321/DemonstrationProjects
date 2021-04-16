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
public class PositionTest {

    public PositionTest() {
    }

    /**
     * Test of getRow method, of class Position.
     */
    @Test
    public void testGetRow() {
        System.out.println("getRow");
        Position instance = new Position(5, 10);
        int expResult = 5;
        int result = instance.getRow();
        assertEquals(expResult, result);
    }

    /**
     * Test of getColumn method, of class Position.
     */
    @Test
    public void testGetColumn() {
        System.out.println("getColumn");
        Position instance = new Position(5, 10);
        int expResult = 10;
        int result = instance.getColumn();
        assertEquals(expResult, result);
    }

    @Test
    public void testEqualsWhenTrue() {
        System.out.println("equals");
        Object obj = new Position(2, 2);
        Position instance = new Position(2, 2);
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    @Test
    public void testEqualsWhenFalse1() {
        System.out.println("equals");
        Object obj = new Position(1, 2);
        Position instance = new Position(2, 2);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    @Test
    public void testEqualsWhenFalse2() {
        System.out.println("equals");
        Object obj = new Position(2, 1);
        Position instance = new Position(2, 2);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    @Test
    public void testEqualsWhenFalse3() {
        System.out.println("equals");
        Object obj = null;
        Position instance = new Position(2, 2);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    @Test
    public void testEqualsWhenFalse4() {
        System.out.println("equals");
        String obj = "";
        Position instance = new Position(2, 2);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    @Test
    public void testHashCode1() {
        Position pos1 = new Position(3, 6);
        Position pos2 = new Position(3, 6);
        assertEquals(pos1.hashCode(), pos2.hashCode());
    }

    @Test
    public void testHashCode2() {
        Position pos1 = new Position(4, 6);
        Position pos2 = new Position(3, 6);
        assertFalse(pos1.hashCode() == pos2.hashCode());
    }

    @Test
    public void testToString() {
        Position pos = new Position(1, 3);
        String expResult = "( D, 2 )";
        String result = pos.toString();
        assertEquals(expResult, result);
    }

}
