package unsw.gloriaromanus.mode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONObject;

public class GetAttack {
    private Player player_army;
    private Player player_enemy;
    private Province from;
    private Province to;

    public GetAttack(Player player_army, Player player_enemy, Province from, Province to) {
        this.player_army = player_army;
        this.player_enemy = player_enemy;
        this.from = from;
        this.to = to;
    }

    public Boolean DoingAttack() throws IOException{
        String content = Files.readString(Paths.get("src/unsw/gloriaromanus/province_adjacency_matrix_fully_connected.json"));
        JSONObject jsonobject = new JSONObject(content);
        JSONObject findI =jsonobject.getJSONObject(from.getName());
        Boolean result = findI.getBoolean(to.getName());
        if(result == false){
            System.out.println("This two province is not adjacency, you cannot attack");
        }else{
            Attack a = new Attack(player_army, player_enemy, from.getUnits(), to.getUnits());
            if(a.attackresult() == true){
                player_army.addProvinces(to);
                player_enemy.removeProvinces(to);
                return true;
            }else{
                return false;
            }
        }
        return false;
    }
    
}
