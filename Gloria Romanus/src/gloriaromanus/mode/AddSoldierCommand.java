package unsw.gloriaromanus.mode;

import java.io.IOException;

public class AddSoldierCommand implements Command{
    private String name;
    private String province;
    private Player player;
    private int turn;
    private int number;

    public AddSoldierCommand(String name, String province, Player player, int turn, int number) {
        this.name = name;
        this.province = province;
        this.player = player;
        this.turn = turn;
        this.number = number;
    }

    public void execute(){
        int count = 0;
        for(Province p : player.getProvinces()){
            if(p.getName().equals(province)){
                Unit unit1 = new Unit(turn, player, name, number, p);
                unit1.addSoldiers();
                System.out.println("this is training troop: " +p.getTraining());
                System.out.println("Success to add the sodiers in the" + province);
            }else{
                count ++; 
            }
        }
        if(count == player.getProvinces().size()){
            System.out.println("Sorry you choosen province is not belong to you");
        }
    }
}
