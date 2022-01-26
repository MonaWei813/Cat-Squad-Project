package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import unsw.gloriaromanus.mode.*;
public class AnalysisofbattleTest {
    Province prov1 = new Province("prov1");
    Province prov2 = new Province("prov2");
    ArrayList<Province> provinces = new ArrayList<Province>();
    Player newPlayer1 = new Player(provinces, "pikemen", "Rome");
    Player newPlayer2 = new Player(provinces, "melee-cavalry", "Gaul");
    Player newPlayer3 = new Player(provinces, "javelin-skirmisher", "Gaul");
    Unit newUnit1 = new Unit(1, newPlayer1, "pikemen", 2, prov1);
    Unit newUnit2 = new Unit(1, newPlayer2, "melee-cavalry", 3, prov2);
    Unit newUnit3 = new Unit(1, newPlayer3, "javelin-skirmisher", 1, prov2);
    Analysisofbattle tester = new Analysisofbattle(newPlayer1, newUnit1, newPlayer2, newUnit2);
    Analysisofbattle tester2 = new Analysisofbattle(newPlayer2, newUnit2, newPlayer3, newUnit3);

    @Test
    public void getterTest() {
        tester.analysisarmy(newPlayer1, newUnit1, newPlayer2, newUnit2, 5, 10, 3, 6, 7, 7, 3, 15, 6, 6, 10, 5);
        assertEquals(5, tester.getArmy_armour());
        assertEquals(6, tester.getArmy_attack());
        assertEquals(10, tester.getArmy_defenseSkill());
        assertEquals(10, tester.getArmy_morale());
        assertEquals(3, tester.getArmy_shieldDefense());
        assertEquals(10, tester.getArmy_speed());
        assertEquals(5, tester.getEnemy_armour());
        assertEquals(6, tester.getEnemy_attack());
        assertEquals(10, tester.getEnemy_morale());
        assertEquals(10, tester.getEnemy_speed());
        assertEquals(10, tester.getEnemy_defenseSkill());
        assertEquals(3, tester.getEnemy_shieldDefense());

        tester2.analysisarmy(newPlayer2, newUnit2,newPlayer3, newUnit3, 5, 10, 3, 6, 7, 7, 3, 15, 6, 6, 10, 5);
        assertEquals(5, tester2.getArmy_armour());
        assertEquals(6, tester2.getArmy_attack());
        assertEquals(10, tester2.getArmy_defenseSkill());
        assertEquals(10, tester2.getArmy_morale());
        assertEquals(3, tester2.getArmy_shieldDefense());
        assertEquals(10, tester2.getArmy_speed());
        assertEquals(5, tester2.getEnemy_armour());
        assertEquals(6, tester2.getEnemy_attack());
        assertEquals(10, tester2.getEnemy_morale());
        assertEquals(10, tester2.getEnemy_speed());
        assertEquals(10, tester2.getEnemy_defenseSkill());
        assertEquals(3, tester2.getEnemy_shieldDefense());

    }
}
