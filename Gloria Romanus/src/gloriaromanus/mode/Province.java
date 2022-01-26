package unsw.gloriaromanus.mode;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;


public class Province {
    private String name;    //the name of province
    private int wealth;     //the number of wealth
    private int morale;     // the number of morale    //the nember of soldier
    private Player player;
    private ArrayList<Infrastructure> infrastructures;
    private int tax;
    private int sendTreasury;
    private ArrayList<Unit> training;
    private ArrayList<Unit> units;
    private JSONArray OnRoads;


    public Province(String name) {
        this.name = name;
        this.wealth = 0;
        this.morale = 0;
        this.infrastructures = new ArrayList<Infrastructure>();
        this.OnRoads = new JSONArray();
        this.training = new ArrayList<Unit>(2);
        this.units = new ArrayList<Unit>();
        this.tax = 10;
        this.sendTreasury = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMorale() {
        return morale;
    }

    public void setMorale(int morale) {
        this.morale = morale;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ArrayList<Infrastructure> getInfrastructures() {
        return infrastructures;
    }

    public void setInfrastructures(ArrayList<Infrastructure> infrastructures) {
        this.infrastructures = infrastructures;
    }

    public void addInfrastructures(Infrastructure i){
        this.infrastructures.add(i);
    }

    public int getTax() {
        updateTax();
        return this.tax;
    }

 //caculate the tax according to the wealth building
    public void updateTax() {          
        int totalaccount = 0;
        for(Infrastructure i : infrastructures){
            if(i instanceof WealthGenerationBuilding){
                WealthGenerationBuilding w = (WealthGenerationBuilding) i;
                totalaccount += w.getIncome();
            }
        }
        if(totalaccount > 50000 && totalaccount <=150000){
            this.tax = 15;
        }
        else if(totalaccount > 150000 && totalaccount <= 300000){
            this.tax = 20;
        }
        else if(totalaccount > 300000){
            this.tax = 25;
        }
        
    }

    public void updateWealth() {
        updateTax();
        int totalaccount = 0;
        for(Infrastructure i : infrastructures){
            if(i instanceof WealthGenerationBuilding){
                WealthGenerationBuilding w = (WealthGenerationBuilding) i;
                totalaccount += w.getIncome();
            }
        }
        if(!infrastructures.isEmpty()){
            this.wealth = totalaccount * (100-this.tax)/100;
        }
    }

    public int getWealth() {
        updateWealth();
        return this.wealth;
    }
    public void setWealth(int wealth) {
        this.wealth = wealth;
    }

    public void setSendTreasury() {     //caculate the treasury to send it to the treasury
        updateTax();
        int totalaccount = 0;
        for(Infrastructure i : infrastructures){
            if(i instanceof WealthGenerationBuilding){
                WealthGenerationBuilding w = (WealthGenerationBuilding) i;
                totalaccount += w.getIncome();
            }
        }
        this.sendTreasury = totalaccount * tax;
    }

    public int getSendTreasury() {
        setSendTreasury();
        return sendTreasury;
    }


    public void addtraing(Unit i){
        if(training.size() >= 2){
            System.out.println("the province unittraining is full we cannot add the training");
        }else{
            training.add(i);
        }
    }
    public void removetraing(Unit i){
        units.add(i);
        training.remove(i);
    }

    public ArrayList<Unit> getTraining() {
        return training;
    }

    public void setTraining(ArrayList<Unit> training) {
        this.training = training;
    }

    public ArrayList<Unit> getUnits() {
        return units;
    }
    public void addUnits(Unit unit){
        units.add(unit);
    }

    public void removeUnits(Unit unit){
        units.remove(unit);
    }
    public JSONArray getAddOnRoad() {
        return OnRoads;
    }
    public void addOnRoad(Unit unit, int turn){
        JSONObject temp = new JSONObject();
        temp.put("unit", unit);
        temp.put("turn", turn);
        OnRoads.put(temp);
    }
    public void RemoveOnRoad(int turn){
        for(int i = 0; i < OnRoads.length(); i++){
            if(OnRoads.getJSONObject(i).getInt("turn") == turn){
                Object temp = OnRoads.getJSONObject(i).get("unit");
                Unit t = (Unit)temp;
                units.add(t);
                OnRoads.remove(i);
            }
        }
    }

    @Override
    public String toString() {
        return "OnRoads: " + OnRoads + "\n" +"training: " + training +"\n"+ "units: " + units +"\n"
        + "wealth: " + wealth;
    }

 




    
}
