package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import unsw.gloriaromanus.mode.*;

public class PlayerTest {
    Province prov1 = new Province("prov1");
    Province prov2 = new Province("prov2");
    ArrayList<Province> provinces = new ArrayList<Province>();
    Player newPlayer = new Player(provinces, "pikemen", "Roman");

    @Test
    public void getterTest() {
        newPlayer.setName("player1");
        assertEquals("player1", newPlayer.getName());

        newPlayer.settreasury(50);
        assertEquals(50, newPlayer.getTreasury());

        newPlayer.addProvinces(prov1);
        assertEquals(1, provinces.size());
        newPlayer.removeProvinces(prov1);
        assertEquals(0, provinces.size());

        newPlayer.payingCost(5);
        assertEquals(45, newPlayer.getTreasury());

        newPlayer.setCountry("xx");
        assertEquals("xx", newPlayer.getCountry());

        newPlayer.setTurn(2);
        assertEquals(2, newPlayer.getTurn());

    }

    @Test
    public void updateTest() {
        WealthGenerationBuilding farm1 = new WealthGenerationBuilding("farm");
        farm1.setLevel(10);
        WealthGenerationBuilding port1 = new WealthGenerationBuilding("port");
        port1.setLevel(5);
        WealthGenerationBuilding mine1 = new WealthGenerationBuilding("main");
        mine1.setLevel(6);

        prov1.addInfrastructures(farm1);
        prov1.addInfrastructures(port1);
        prov1.addInfrastructures(mine1);
        prov1.updateTax();
        prov1.setSendTreasury();
        provinces.add(prov1);
        newPlayer.updateTreasury();
        assertEquals(946000, newPlayer.getTreasury());

        newPlayer.updateTurn();
        assertEquals(1, newPlayer.getTurn());
    }

    
}
