package unsw.gloriaromanus.mode;

import java.io.IOException;
import java.util.ArrayList;

public class Movement{
    private Player player;
    private Province from;
    private Province to;
    private int turn;
    private int arriveturn;
    private Unit unit;

    public Movement(Player player, Province from, Province to, int turn, Unit unit) {
        this.player = player;
        this.from = from;
        this.to = to;
        this.turn = turn;
        this.arriveturn = turn;
        this.unit = unit;
    }

    public void getMovement() throws IOException {
        ShortestPath s = new ShortestPath(player, from.getName(), to.getName());
        if(s.movement() != null){
            ArrayList<String> temp = s.movement();
            int count = temp.size();
            if(unit.getName().equals("cavalry") || unit.getName().equals("melee-cavalry")){
                if(count < 3){
                    arriveturn = turn + 1;
                    to.addOnRoad(unit, arriveturn);
                }else{
                    int a = count % 3;
                    arriveturn = (count-a)/3 +turn +1;
                    to.addOnRoad(unit, arriveturn);
                }
            }
            else if(unit.getName().equals("Infantry") || unit.getName().equals("pikemen")
            || unit.getName().equals("hoplite") || unit.getName().equals("javelin-skirmisher")||
            unit.getName().equals("melee infantry") ||unit.getName().equals("druid") ){
                if(count < 2){
                    arriveturn = turn + 1;
                    to.addOnRoad(unit, arriveturn);
                }else{
                    int a = count % 2;
                    arriveturn = (count-a)/2 +turn +1;
                    to.addOnRoad(unit, arriveturn);
                }

            }
            else{
                arriveturn = turn + count;
                to.addOnRoad(unit, arriveturn);
            }

        }
    }

    public int getArriveTurn() {
        return arriveturn;
    }
}
