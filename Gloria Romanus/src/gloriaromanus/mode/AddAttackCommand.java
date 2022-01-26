package unsw.gloriaromanus.mode;

import java.util.ArrayList;

public class AddAttackCommand implements Command{
    private Player player_army;
    private Player player_enemy;
    private ArrayList<Unit> armys;
    private ArrayList<Unit> enemys;

    public AddAttackCommand(Player player_army, ArrayList<Unit> armys, Player player_enemy, ArrayList<Unit> enemys) {
        this.player_army = player_army;
        this.player_enemy = player_enemy;
        this.armys = armys;
        this.enemys = enemys;

    }
    public void execute(){
        Attack temp = new Attack(player_army, player_enemy, armys, enemys);
        temp.attackresult();
    }
}
