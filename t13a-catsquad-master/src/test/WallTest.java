package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Test;

import unsw.gloriaromanus.mode.*;
/**
 * Test for class Wall
 */
public class WallTest {
    Wall newWall = new Wall("test");

    @Test
    public void updateLevelTest() {
        newWall.setLevel(1);
        newWall.updatelevel();
        assertEquals(2, newWall.getLevel());
        assertEquals(150, newWall.getUpdateprice());
    }
}
