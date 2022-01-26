package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Test;

import unsw.gloriaromanus.mode.*;
/**
 * Test for class Wall
 */
public class SmithTest {
    Smiths newSmiths = new Smiths("test");

    @Test
    public void updateLevelTest() {
        newSmiths.setLevel(1);
        newSmiths.updatelevel();
        assertEquals(2, newSmiths.getLevel());
        assertEquals(150, newSmiths.getUpdateprice());
    }
}
