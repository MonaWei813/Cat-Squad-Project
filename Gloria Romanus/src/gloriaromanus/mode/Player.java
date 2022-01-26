package unsw.gloriaromanus.mode;

import java.util.ArrayList;

public class Player {
    private String name;
    private int treasury; //cost to building and troop
    private int turn;   //it represent the year
    private ArrayList<Province> provinces;
    private String country;

    public Player(ArrayList<Province> provinces, String name, String country) {
        this.treasury = 1000;
        this.turn = 0;
        this.provinces = provinces;
        this.name = name;
        this.country = country;

    }
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
    }
    
    public int getTreasury() {
        return treasury;
    }
    public void settreasury(int treasury){
        this.treasury = treasury;
    }
    public void payingCost(int cost) {
        this.treasury -= cost;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public void updateTurn(){
        this.turn += 1;
    }

    public ArrayList<Province> getProvinces() {
        return provinces;
    }

    public void addProvinces(Province province) {
        this.provinces.add(province) ;
    }
    public void removeProvinces(Province province) {
        this.provinces.remove(province) ;
    }
    //update the treasury, get it from every province
    public void updateTreasury(){
        for(Province p : provinces){
            this.treasury += p.getSendTreasury();
        }
    }
    
    public void paytrainingfee(int fee){
        this.treasury -= fee;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Player [country=" + country + ", name=" + name + ", provinces=" + provinces + ", treasury=" + treasury
                + ", turn=" + turn + "]";
    }


}
