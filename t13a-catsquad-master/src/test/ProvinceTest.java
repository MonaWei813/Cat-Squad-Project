package test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import unsw.gloriaromanus.mode.*;

public class ProvinceTest {
    Province tester = new Province("x");
    Infrastructure mine = new WealthGenerationBuilding("mine");
    Infrastructure farm = new WealthGenerationBuilding("farm");
    

    @Test
    public void getterTest() {
        ArrayList<Infrastructure> newList = new ArrayList<Infrastructure>();
        newList.add(mine);
        newList.add(farm);
        tester.setInfrastructures(newList);
        assertEquals(2, tester.getInfrastructures().size());

        tester.addInfrastructures(farm);
        assertEquals(3, tester.getInfrastructures().size());

        assertEquals(10, tester.getTax());

        tester.setMorale(10);
        assertEquals(10, tester.getMorale());

        tester.setName("xx");
        assertEquals("xx", tester.getName());

        

        tester.setSendTreasury();
        assertEquals(0, tester.getSendTreasury());

    }

    @Test
    public void otherTest() {
        Province prov1 = new Province("Lugdunensis");
        Province prov2 = new Province("Narbonensis");
    
        ArrayList<Province> provList = new ArrayList<Province>();
        provList.add(prov1);
        provList.add(prov2);
        Player player1 = new Player(provList, "xx", "Roman"); 
        tester.setPlayer(player1);
        assertEquals(player1, tester.getPlayer());

        Player player2 = new Player(provList, "cavalry", "Roman"); 
        Unit unit2 = new Unit(1, player2, "cavalry", 2, prov1);

        Player player3 = new Player(provList, "melee-cavalry", "Roman"); 
        Unit unit3 = new Unit(1, player3, "melee-cavalry", 2, prov1);

        ArrayList<Unit> unitList = new ArrayList<Unit>();
        unitList.add(unit2);
        unitList.add(unit3);

        

        tester.setTraining(unitList);
        assertEquals(2, tester.getTraining().size());

        tester.removetraing(unit2);
        assertEquals(1, tester.getTraining().size());

        tester.addUnits(unit2);
        assertEquals(1, unitList.size());
       
        assertEquals(2, tester.getUnits().size());
        
        tester.removeUnits(unit2);
        assertEquals(1, unitList.size());

        tester.addOnRoad(unit3, 2);
        assertEquals(1, tester.getAddOnRoad().length());

        tester.RemoveOnRoad(2);
        assertEquals(0, tester.getAddOnRoad().length());


        assertEquals("Province [OnRoads=[], infrastructures=[], morale=0, name=x, player=Player [country=Roman, name=xx, provinces=[Province [OnRoads=[], infrastructures=[], morale=0, name=Lugdunensis, player=null, sendTreasury=0, tax=10, training=[], units=[], wealth=0], Province [OnRoads=[], infrastructures=[], morale=0, name=Narbonensis, player=null, sendTreasury=0, tax=10, training=[], units=[], wealth=0]], treasury=1000, turn=0], sendTreasury=0, tax=10, training=[Unit [armour=5, attack=6, defenseSkill=10, morale=10, name=melee-cavalry, numTroops=2, occurturn=0, player=Player [country=Roman, name=melee-cavalry, provinces=[Province [OnRoads=[], infrastructures=[], morale=0, name=Lugdunensis, player=null, sendTreasury=0, tax=10, training=[], units=[], wealth=0], Province [OnRoads=[], infrastructures=[], morale=0, name=Narbonensis, player=null, sendTreasury=0, tax=10, training=[], units=[], wealth=0]], treasury=1000, turn=0], province=Province [OnRoads=[], infrastructures=[], morale=0, name=Lugdunensis, player=null, sendTreasury=0, tax=10, training=[], units=[], wealth=0], range=1, shieldDefense=3, speed=10, trainingfee=2, trainingturn=0, turn=1]], units=[Unit [armour=5, attack=6, defenseSkill=10, morale=10, name=cavalry, numTroops=2, occurturn=0, player=Player [country=Roman, name=cavalry, provinces=[Province [OnRoads=[], infrastructures=[], morale=0, name=Lugdunensis, player=null, sendTreasury=0, tax=10, training=[], units=[], wealth=0], Province [OnRoads=[], infrastructures=[], morale=0, name=Narbonensis, player=null, sendTreasury=0, tax=10, training=[], units=[], wealth=0]], treasury=1000, turn=0], province=Province [OnRoads=[], infrastructures=[], morale=0, name=Lugdunensis, player=null, sendTreasury=0, tax=10, training=[], units=[], wealth=0], range=1, shieldDefense=3, speed=10, trainingfee=2, trainingturn=0, turn=1], Unit [armour=5, attack=6, defenseSkill=10, morale=10, name=melee-cavalry, numTroops=2, occurturn=0, player=Player [country=Roman, name=melee-cavalry, provinces=[Province [OnRoads=[], infrastructures=[], morale=0, name=Lugdunensis, player=null, sendTreasury=0, tax=10, training=[], units=[], wealth=0], Province [OnRoads=[], infrastructures=[], morale=0, name=Narbonensis, player=null, sendTreasury=0, tax=10, training=[], units=[], wealth=0]], treasury=1000, turn=0], province=Province [OnRoads=[], infrastructures=[], morale=0, name=Lugdunensis, player=null, sendTreasury=0, tax=10, training=[], units=[], wealth=0], range=1, shieldDefense=3, speed=10, trainingfee=2, trainingturn=0, turn=1]], wealth=0]", tester.toString());

    }
    
    @Test
    public void updateTaxTest() {
        WealthGenerationBuilding farm1 = new WealthGenerationBuilding("farm");
        farm1.setLevel(10);
        WealthGenerationBuilding port1 = new WealthGenerationBuilding("port");
        port1.setLevel(5);
        WealthGenerationBuilding mine1 = new WealthGenerationBuilding("main");
        mine1.setLevel(6);

        tester.addInfrastructures(farm1);
        tester.addInfrastructures(port1);
        tester.addInfrastructures(mine1);
        tester.updateTax();
        assertEquals(15, tester.getTax());
    

        WealthGenerationBuilding farm2 = new WealthGenerationBuilding("farm");
        farm2.setLevel(20);
        WealthGenerationBuilding port2 = new WealthGenerationBuilding("port");
        port2.setLevel(10);
        WealthGenerationBuilding mine2 = new WealthGenerationBuilding("main");
        mine2.setLevel(20);

        tester.addInfrastructures(farm2);
        tester.addInfrastructures(port2);
        tester.addInfrastructures(mine2);
        tester.updateTax();
        assertEquals(20, tester.getTax());

        WealthGenerationBuilding farm3 = new WealthGenerationBuilding("farm");
        farm3.setLevel(20);
        WealthGenerationBuilding port3 = new WealthGenerationBuilding("port");
        port3.setLevel(10);
        WealthGenerationBuilding mine3 = new WealthGenerationBuilding("main");
        mine3.setLevel(20);

        tester.addInfrastructures(farm3);
        tester.addInfrastructures(port3);
        tester.addInfrastructures(mine3);
        tester.updateTax();
        assertEquals(25, tester.getTax());
    }

    @Test
    public void updateWealthTest() {
        WealthGenerationBuilding farm1 = new WealthGenerationBuilding("farm");
        farm1.setLevel(10);
        WealthGenerationBuilding port1 = new WealthGenerationBuilding("port");
        port1.setLevel(5);
        WealthGenerationBuilding mine1 = new WealthGenerationBuilding("main");
        mine1.setLevel(6);

        tester.addInfrastructures(farm1);
        tester.addInfrastructures(port1);
        tester.addInfrastructures(mine1);
        tester.updateTax();
        tester.updateWealth();
        assertEquals(53550, tester.getWealth());
    }

}
