package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Test;

import unsw.gloriaromanus.mode.*;

/**
 * Trivial test for Infrastructure
 */
public class infrastructureTest {
    Infrastructure newStructure = new Infrastructure("road");

    @Test
    public void setterTest() {
        newStructure.setName("highway");
        assertEquals("highway", newStructure.getName());
    }

    @Test
    public void toStringTest(){
        assertEquals("Infrastructure [name=road, updateprice=50]", newStructure.toString());
    }

}
