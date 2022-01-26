package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import unsw.gloriaromanus.mode.*;
import org.junit.jupiter.api.Test;


public class AttackTest {
/*
    Player player_army = new Player(provinces, name, country);
    Player player_enemy = new Player(provinces, name, country);
    private ArrayList<Unit> armys;
    private ArrayList<Unit> enemys;
    Attack newAttack = new Attack(player_army, player_enemy, armys, enemys);
*/
    ArrayList<Province> provinces1 = new ArrayList<>();
    ArrayList<Province> provinces2 = new ArrayList<>();

    Player player_army = new  Player(provinces1, "Silbia", "Rome");
    Player player_enemy = new  Player(provinces2, "dd", "Gaul");

    ArrayList<Unit> armys = new ArrayList<>();
    ArrayList<Unit> enemys = new ArrayList<>();
    Province p = new Province("Lugdunensis");
    Unit u = new Unit(1, player_army, "artillery", 10, p);
    Unit u1 = new Unit(1, player_army, "melee-cavalry", 10, p);
    Attack newAttack = new Attack(player_army, player_enemy, armys, enemys);
    @Test
    public void testRange(){

        assertEquals(true, newAttack.CheckRanged(u)); 
        assertEquals(false, newAttack.CheckRanged(u1)); 

    }
    @Test
    public void booleanTest() {
        assertFalse(newAttack.attackresult()); 
        assertFalse(newAttack.UnitAttack(u, u1));
        assertTrue(newAttack.breaking(u1, 1, u, 1));

    }


}
