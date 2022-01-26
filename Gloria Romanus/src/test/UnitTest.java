package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;

import unsw.gloriaromanus.mode.*;
/**
 * Test for the Unit class
 */
public class UnitTest{
    
    Province prov1 = new Province("prov1");
    Province prov2 = new Province("prov2");

    ArrayList<Province> provList = new ArrayList<Province>();
        
    Player newPlayer = new Player(provList, "p1", "Roman"); 

    Unit newUnit = new Unit(1, newPlayer, "xx", 2, prov1);

    @Test
    public void getterTest(){
        assertEquals(2, newUnit.getNumTroops());
        assertEquals(1, newUnit.getRange());
        assertEquals(5, newUnit.getArmour());
        assertEquals(10, newUnit.getMorale());
        assertEquals(1, newUnit.getTurn());
        assertEquals(10, newUnit.getSpeed());
        assertEquals(6, newUnit.getAttack());
        assertEquals(10, newUnit.getDefenseSkill());
        assertEquals(3, newUnit.getShieldDefense());
        assertEquals(0, newUnit.getTrainingturn());
        assertEquals(0, newUnit.getOccurturn());
        assertEquals( newPlayer, newUnit.getPlayer());
        assertEquals("xx", newUnit.getName());
        assertEquals(prov1, newUnit.getProvince());
        assertEquals(2, newUnit.getTrainingfee());

    }

    @Test
    public void setterTest() {

        newUnit.setArmour(7);
        assertEquals(7, newUnit.getArmour());

        newUnit.setMorale(15);
        assertEquals(15, newUnit.getMorale());

        newUnit.setSpeed(8);
        assertEquals(8, newUnit.getSpeed());

        newUnit.setAttack(9);
        assertEquals(9, newUnit.getAttack());

        newUnit.setDefenseSkill(9);
        assertEquals(9, newUnit.getDefenseSkill());

        newUnit.setShieldDefense(5);
        assertEquals(5, newUnit.getShieldDefense());

    }

    @Test
    public void addSoldiersTest() {
        Unit temp1 = new Unit(1, newPlayer, "pikemen", 2, prov1);
        temp1.addSoldiers();
        assertEquals(2, temp1.getTrainingturn());
        assertEquals(4, temp1.getTrainingfee());

        Unit temp2 = new Unit(1, newPlayer, "elephant", 2, prov1);
        temp2.addSoldiers();
        assertEquals(3, temp2.getTrainingturn());
        assertEquals(6, temp2.getTrainingfee());

        Unit temp3 = new Unit(1, newPlayer, "hoplite", 2, prov1);
        temp3.addSoldiers();
        assertEquals(2, temp3.getTrainingturn());
        assertEquals(4, temp3.getTrainingfee());

        Unit temp4 = new Unit(1, newPlayer, "druid", 2, prov1);
        temp4.addSoldiers();
        assertEquals(3, temp4.getTrainingturn());
        assertEquals(6, temp4.getTrainingfee());

        Unit temp5 = new Unit(1, newPlayer, "horse-archer", 2, prov1);
        temp5.addSoldiers();
        assertEquals(3, temp5.getTrainingturn());
        assertEquals(6, temp5.getTrainingfee());

        newUnit.addSoldiers();
        assertEquals(1, newUnit.getTrainingturn());
        assertEquals(2, newUnit.getTrainingfee());

        // Cannot figure out how to test the if branch
    }

    @Test
    public void toStringTest(){
        assertEquals("Unit [armour=5, attack=6, defenseSkill=10, morale=10, name=xx, numTroops=2, occurturn=0, player=Player [country=Roman, name=p1, provinces=[], treasury=1000, turn=0], province=Province [OnRoads=[], infrastructures=[], morale=0, name=prov1, player=null, sendTreasury=0, tax=10, training=[], units=[], wealth=0], range=1, shieldDefense=3, speed=10, trainingfee=2, trainingturn=0, turn=1]", newUnit.toString());
    }
    
}

