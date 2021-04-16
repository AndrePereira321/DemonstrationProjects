/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.atlg3.g51999.othello.model;

import esi.atlg3.g51999.othello.model.datatype.Direction;
import esi.atlg3.g51999.othello.model.datatype.Position;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Andre Pereira
 */
public class DirectionTest {

    public DirectionTest() {
    }

    /**
     * Test of nextPos method, of class Direction.
     */
    @Test
    public void testNextPos() {
        System.out.println("nextPos");
        Position position = new Position(5, 5);
        Position[] expResult = {
            new Position(4, 5),
            new Position(4, 6),
            new Position(5, 6),
            new Position(6, 6),
            new Position(6, 5),
            new Position(6, 4),
            new Position(5, 4),
            new Position(4, 4)
        };

        Position[] result = new Position[8];
        int i = 0;
        for (Direction direction : Direction.values()) {
            result[i] = direction.nextPos(position);
            ++i;
        }
        assertArrayEquals(expResult, result);
        assertEquals(8, i);
    }

    /**
     * Test of nextPos method, of class Direction.
     */
    @Test(expected = NullPointerException.class)
    public void testNextPos2() {
        System.out.println("nextPos");
        Direction dir = Direction.LEFT;
        dir.nextPos(null);
    }
    
    @Test
    public void testValueOf() {
        Direction expResult = Direction.RIGHT;
        Direction result = Direction.valueOf("RIGHT");
        assertEquals(expResult, result);
    }
}
