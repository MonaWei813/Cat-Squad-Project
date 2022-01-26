package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import unsw.gloriaromanus.mode.*;

/**
 * Trivial test for WealthGenerationBuilding
 */
public class WealthGenerationBuildingTest {
    WealthGenerationBuilding newBuilding = new WealthGenerationBuilding("farm");

    @Test
    public void getterTest(){
        assertEquals(0, newBuilding.getLevel()); 
        assertEquals(0, newBuilding.getIncome());
        
    }

    @Test
    public void setterTest(){
        newBuilding.updatelevel();
        newBuilding.setIncome();
        assertEquals( 3000, newBuilding.getIncome());    

        newBuilding.setLevel(2);
        assertEquals(2, newBuilding.getLevel());
    }

    @Test
    public void toStringTest() {
        assertEquals("WealthGenerationBuilding [income=0, level=0]", newBuilding.toString()); 
    }
}
