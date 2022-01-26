package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import unsw.gloriaromanus.mode.*;
public class Goaltest {
    @Test
    public void ANDtest(){
        int i = 0;
        int j = 0;
        Goal goal = new Goal(i,j);
        JSONObject temp = goal.getgoal();
        int result = goal.checkGoal(temp);
        assertEquals(1, result);

        i = 0;
        j = 1;
        goal = new Goal(i,j);
        temp = goal.getgoal();
        result = goal.checkGoal(temp);
        assertEquals(2, result);
    }
    @Test
    public void ORtest(){
        int i = 1;
        int j = 0;
        Goal goal = new Goal(i,j);
        JSONObject temp = goal.getgoal();
        int result = goal.checkGoal(temp);
        assertEquals(3, result);

        i = 1;
        j = 1;
        goal = new Goal(i,j);
        temp = goal.getgoal();
        result = goal.checkGoal(temp);
        assertEquals(4, result);
    }
    @Test
    public void getgoaltest1(){
        int i = 0;
        int j = 0;
        Goal goal = new Goal(i,j);
        JSONObject temp = goal.getgoal();
        ArrayList<Province> provinces1 = new ArrayList<>();
        ArrayList<Province> provinces2 = new ArrayList<>();
        Player player1 = new Player(provinces1, "Silvia", "rome");
        Player player2 = new Player(provinces2, "tom", "rome");
        assertEquals(false , goal.WhetherGoal(temp, player1, player2));

        player1.settreasury(10000000);
        Province p = new Province("dd");
        p.setWealth(40000000);
        provinces1.add(p);
        assertEquals(true , goal.WhetherGoal(temp, player1, player2));

    }
    @Test
    public void getgoaltest2(){
        int i = 0;
        int j = 1;
        Goal goal = new Goal(i,j);
        JSONObject temp = goal.getgoal();
        ArrayList<Province> provinces1 = new ArrayList<>();
        ArrayList<Province> provinces2 = new ArrayList<>();
        Player player1 = new Player(provinces1, "Silvia", "rome");
        Player player2 = new Player(provinces2, "tom", "rome");
        assertEquals(false , goal.WhetherGoal(temp, player1, player2));

        player1.settreasury(10000000);
        Province p = new Province("dd");
        provinces1.add(p);
        assertEquals(true , goal.WhetherGoal(temp, player1, player2));

    }
    @Test
    public void getgoaltest3(){
        int i = 1;
        int j = 0;
        Goal goal = new Goal(i,j);
        JSONObject temp = goal.getgoal();
        ArrayList<Province> provinces1 = new ArrayList<>();
        ArrayList<Province> provinces2 = new ArrayList<>();
        Player player1 = new Player(provinces1, "Silvia", "rome");
        Player player2 = new Player(provinces2, "tom", "rome");
        assertEquals(false , goal.WhetherGoal(temp, player1, player2));

        player1.settreasury(10000000);
        Province p = new Province("dd");
        Province p2 = new Province("cc");
        p.setWealth(40000000);
        provinces1.add(p);
        provinces2.add(p2);
        assertEquals(true , goal.WhetherGoal(temp, player1, player2));

    }
    @Test
    public void getgoaltest4(){
        int i = 1;
        int j = 0;
        Goal goal = new Goal(i,j);
        JSONObject temp = goal.getgoal();
        ArrayList<Province> provinces1 = new ArrayList<>();
        ArrayList<Province> provinces2 = new ArrayList<>();
        Player player1 = new Player(provinces1, "Silvia", "rome");
        Player player2 = new Player(provinces2, "tom", "rome");
        assertEquals(false , goal.WhetherGoal(temp, player1, player2));

        player1.settreasury(10000000);
        Province p = new Province("dd");
        Province p2 = new Province("cc");
        p.setWealth(40000000);
        provinces1.add(p);
        provinces2.add(p2);
        assertEquals(true , goal.WhetherGoal(temp, player1, player2));

    }

}
