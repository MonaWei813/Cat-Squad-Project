package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Test;

import unsw.gloriaromanus.mode.*;
/**
 * Test for class Wall
 */
public class TroopProductionBuildingTest {
    TroopProductionBuilding newBuilding = new TroopProductionBuilding("test");

    @Test
    public void updateLevelTest() {
        newBuilding.setLevel(1);
        newBuilding.updatelevel();
        assertEquals(2, newBuilding.getLevel());
        assertEquals(150, newBuilding.getUpdateprice());
    }
}
