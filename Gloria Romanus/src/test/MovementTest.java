package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import unsw.gloriaromanus.mode.*;


public class MovementTest {
    Province prov1 = new Province("Lugdunensis");
    Province prov2 = new Province("Narbonensis");

    ArrayList<Province> provList = new ArrayList<Province>();
        
    Player player1 = new Player(provList, "xx", "Roman"); 
    Unit unit1 = new Unit(1, player1, "xx", 2, prov1);
    Movement test1 = new Movement(player1, prov1, prov2, 1, unit1);

    Player player2 = new Player(provList, "cavalry", "Roman"); 
    Unit unit2 = new Unit(1, player2, "cavalry", 2, prov1);
    Movement test2 = new Movement(player2, prov1, prov2, 1, unit2);

    Player player3 = new Player(provList, "melee-cavalry", "Roman"); 
    Unit unit3 = new Unit(1, player3, "melee-cavalry", 2, prov1);
    Movement test3 = new Movement(player3, prov1, prov2, 1, unit3);

    Player player4 = new Player(provList, "Infantry", "Roman"); 
    Unit unit4 = new Unit(1, player4, "Infantry", 2, prov1);
    Movement test4 = new Movement(player4, prov1, prov2, 1, unit4);

    Player player5 = new Player(provList, "pikemen", "Roman"); 
    Unit unit5 = new Unit(1, player5, "pikemen", 2, prov1);
    Movement test5 = new Movement(player5, prov1, prov2, 1, unit5);

    Player player6 = new Player(provList, "hoplite", "Roman"); 
    Unit unit6 = new Unit(1, player6, "hoplite", 2, prov1);
    Movement test6 = new Movement(player6, prov1, prov2, 1, unit6);

    Player player7 = new Player(provList, "javelin-skirmisher", "Roman"); 
    Unit unit7 = new Unit(1, player7, "javelin-skirmisher", 2, prov1);
    Movement test7 = new Movement(player7, prov1, prov2, 1, unit7);

    Player player8 = new Player(provList, "melee infantry", "Roman"); 
    Unit unit8 = new Unit(1, player8, "melee infantry", 2, prov1);
    Movement test8 = new Movement(player8, prov1, prov2, 1, unit8);

    Player player9 = new Player(provList, "druid", "Roman"); 
    Unit unit9 = new Unit(1, player9, "druid", 2, prov1);
    Movement test9 = new Movement(player9, prov1, prov2, 1, unit9);



    @Test
    public void getMovementTest() throws Exception{
        
        test1.getMovement();
        assertEquals(1, test1.getArriveTurn());

        test2.getMovement();
        assertEquals(1, test2.getArriveTurn());

        test3.getMovement();
        assertEquals(1, test3.getArriveTurn());

        test4.getMovement();
        assertEquals(1, test4.getArriveTurn());

        test5.getMovement();
        assertEquals(1, test5.getArriveTurn());

        test6.getMovement();
        assertEquals(1, test6.getArriveTurn());

        test7.getMovement();
        assertEquals(1, test7.getArriveTurn());

        test8.getMovement();
        assertEquals(1, test8.getArriveTurn());

        test9.getMovement();
        assertEquals(1, test9.getArriveTurn());

    }

}
