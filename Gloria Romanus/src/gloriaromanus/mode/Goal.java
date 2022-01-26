package unsw.gloriaromanus.mode;

import org.json.JSONArray;
import org.json.JSONObject;

public class Goal {
    private int goal1;
    private int goal2;

    public Goal(int goal1, int goal2) {
        this.goal1 = goal1;
        this.goal2 = goal2;
    }
    
    public JSONObject getgoal(){
        JSONObject result = new JSONObject();
        if(goal1 == 0){
            result.put("goal", "AND");
            JSONObject j = new JSONObject().put("goal", "TREASURY");
            if(goal2 == 0){
                JSONObject m = new JSONObject().put("goal", "CONQUEST");
                JSONObject n = new JSONObject().put("goal", "WEALTH");
                JSONArray a = new JSONArray().put(m).put(n);
                JSONObject i = new JSONObject().put("goal", "AND").put("subgoals", a);
                JSONArray d = new JSONArray().put(j).put(i);
                result.put("subgoals", d);
                System.out.println(result);
                return result;
            }else{
                JSONObject m = new JSONObject().put("goal", "CONQUEST");
                JSONObject n = new JSONObject().put("goal", "WEALTH");
                JSONArray a = new JSONArray().put(m).put(n);
                JSONObject i = new JSONObject().put("goal", "OR").put("subgoals", a);
                JSONArray d = new JSONArray().put(j).put(i);
                result.put("subgoals", d);
                System.out.println(result);
                return result;
            }
        }else{
            result.put("goal", "OR");
            JSONObject j = new JSONObject().put("goal", "TREASURY");
            if(goal2 == 0){
                JSONObject m = new JSONObject().put("goal", "CONQUEST");
                JSONObject n = new JSONObject().put("goal", "WEALTH");
                JSONArray a = new JSONArray().put(m).put(n);
                JSONObject i = new JSONObject().put("goal", "AND").put("subgoals", a);
                JSONArray d = new JSONArray().put(j).put(i);
                result.put("subgoals", d);
                System.out.println(result);
                return result;
            }else{
                JSONObject m = new JSONObject().put("goal", "CONQUEST");
                JSONObject n = new JSONObject().put("goal", "WEALTH");
                JSONArray a = new JSONArray().put(m).put(n);
                JSONObject i = new JSONObject().put("goal", "OR").put("subgoals", a);
                JSONArray d = new JSONArray().put(j).put(i);
                result.put("subgoals", d);
                System.out.println(result);
                return result;
            }
        }

    }
    public int checkGoal(JSONObject goal){
        if(goal.get("goal").equals("AND")){
            JSONArray a = goal.getJSONArray("subgoals");
            JSONObject o = a.getJSONObject(1);
            if(o.get("goal").equals("AND")){
                return 1;
            }else{
                return 2;
            }
        }else{
            JSONArray a = goal.getJSONArray("subgoals");
            JSONObject o = a.getJSONObject(1);
            if(o.get("goal").equals("AND")){
                return 3;
            }else{
                return 4;
            }
        }
    }
    
    public boolean WhetherGoal(JSONObject goal, Player player1, Player player2){
        int i = checkGoal(goal);
        if(i == 1){
            if(player1.getProvinces().isEmpty()){
                int totalwealth = 0;
                for(Province p : player2.getProvinces()){
                    totalwealth += p.getWealth();
                }
                if(player2.getTreasury() >= 100000 &&  totalwealth >= 400000){
                    System.out.println("player2 is success ful this game");
                    return true;
                }
            }
            else if(player2.getProvinces().isEmpty()){
                int totalwealth = 0;
                for(Province p : player1.getProvinces()){
                    totalwealth += p.getWealth();
                }
                if(player1.getTreasury() >= 100000 &&  totalwealth >= 400000){
                    System.out.println("player1 is success ful this game");
                    return true;
                }
            }
            else{
                return false;
            }
        }
        else if(i == 2){
            if(player2.getTreasury() >= 100000 ){
                int totalwealth = 0;
                for(Province p : player2.getProvinces()){
                    totalwealth += p.getWealth();
                }
                if(player1.getProvinces().isEmpty() || totalwealth >= 400000){
                    System.out.println("player2 is success ful this game");
                    return true;
                }
            }
            else if(player1.getTreasury() >= 100000){
                int totalwealth = 0;
                for(Province p : player1.getProvinces()){
                    totalwealth += p.getWealth();
                }
                if(player2.getProvinces().isEmpty() ||  totalwealth >= 400000){
                    System.out.println("player1 is success ful this game");
                    return true;
                }
            }
            else{
                return false;
            }
        }
        else if(i == 3){
            int totalwealth2 = 0;
            for(Province p : player2.getProvinces()){
                totalwealth2 += p.getWealth();
            }
            int totalwealth1 = 0;
            for(Province p : player1.getProvinces()){
                totalwealth1 += p.getWealth();
            }
            if(player2.getTreasury() >= 100000 ||(player1.getProvinces().isEmpty() &&  totalwealth2 >= 400000)){
                System.out.println("player2 is success ful this game");
                return true;
            }
            else if(player1.getTreasury() >= 100000||(player2.getProvinces().isEmpty() &&  totalwealth1 >= 400000)){
                System.out.println("player1 is success ul this game");
                return true;
            }
            else{
                return false;
            }
        }else{
            int totalwealth2 = 0;
            for(Province p : player2.getProvinces()){
                totalwealth2 += p.getWealth();
            }
            int totalwealth1 = 0;
            for(Province p : player1.getProvinces()){
                totalwealth1 += p.getWealth();
            }
            if(totalwealth1 > 400000 || player2.getProvinces().isEmpty() ||(player1.getTreasury() >= 100000)){
                return true;
            }
            else if(totalwealth2 > 400000 || player1.getProvinces().isEmpty() ||(player2.getTreasury() >= 100000)){
                return true;
            }else{
                return false;
            }
        }
        return false;

    }


}
