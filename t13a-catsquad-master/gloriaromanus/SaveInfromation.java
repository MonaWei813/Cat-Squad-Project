package unsw.gloriaromanus;

import java.util.ArrayList;

import unsw.gloriaromanus.mode.Player;
import unsw.gloriaromanus.mode.Province;

public class SaveInfromation {
    private String name1;
    private String name2;
    private ArrayList<Province> p1;
    private ArrayList<Province> p2;
    private String country1;
    private String country2;
    private Player player1;
    private Player player2;

    public SaveInfromation(String name1, String name2, ArrayList<Province> p1, ArrayList<Province> p2, String country1,
            String country2) {
        this.name1 = name1;
        this.name2 = name2;
        this.p1 = p1;
        this.p2 = p2;
        this.country1 = country1;
        this.country2 = country2;
        this.player1 = new Player(p1, name1, country1);
        this.player2 = new Player(p2, name2, country2);
    }

    public Player Player1(){
        return this.player1;
    }
    public Player Player2(){
        return this.player2;
    }

    
}
