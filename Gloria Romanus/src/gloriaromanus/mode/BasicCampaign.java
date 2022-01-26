package unsw.gloriaromanus.mode;


import java.io.IOException;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import org.json.JSONObject;



public class BasicCampaign implements Subject {
    private Player player1;
    private Player player2;
    private int turn;
    private ArrayList<Command> commands;
    private ArrayList<Observer> observerList;

    public BasicCampaign(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.turn = 0;
        ArrayList<Command> commands = new ArrayList<>();
    }
    @Override
    public void subscribe(Observer obs) {
        observerList.add(obs);
    }

    @Override
    public void unsubscribe(Observer obs) {
        observerList.remove(obs);
    }
    @Override
    public void notifier(BasicCampaign game) {
        for(Observer o : observerList) {
            o.update(this, game);
        }
    }

    public static void main(String[] args) throws IOException{
        Scanner in = new Scanner(System.in);
        System.out.println("Choose the model of the game");
        String model = in.nextLine();
        
        if(model.equals("double")){
            in = new Scanner(System.in);
            System.out.println("Please enter the name of player1:");
            String name1 = in.nextLine();

            in = new Scanner(System.in);
            System.out.println("Please enter the name of player2:");
            String name2 = in.nextLine();

            in = new Scanner(System.in);
            System.out.println("Please choose the country for player1:");
            String country1 = in.nextLine();
            String country2 = "";
            ArrayList<Province> provinces1 = new ArrayList<>();
            ArrayList<Province> provinces2 = new ArrayList<>();
            if(country1.equals("Rome")){
                GetRomeProvince romeprovinces = new GetRomeProvince();
                provinces1 = romeprovinces.GetInitialProvinces();
                System.out.println("\n"+"The player1 has been choosen Rome, the player2 has automatical to choosen Gaul"+"\n");
                country2 = "Gaul";
                GetGaulProvince gualprovinces = new GetGaulProvince();
                provinces2 = gualprovinces.GetInitialProvinces();

            }else{
                GetGaulProvince gualprovinces = new GetGaulProvince();
                provinces1 = gualprovinces.GetInitialProvinces();
                System.out.println("\n"+"The player1 has been choosen Gaul, the player2 has automatical to choosen Rome"+"\n");
                country2 = "Rome";
                GetRomeProvince romeprovinces = new GetRomeProvince();
                provinces2 = romeprovinces.GetInitialProvinces();
            }

            Player player1 = new Player(provinces1,name1,country1);
            Player player2 = new Player(provinces2,name2,country2);

            System.out.println("Welcome to grand-strategy game !");

            Random random = new Random();
            int i = random.nextInt(1);
            int j = random.nextInt(1);
            Goal goal = new Goal(i,j);
            JSONObject g = goal.getgoal();

            BasicCampaign newgame = new BasicCampaign(player1, player2);
            while(!goal.WhetherGoal(g, player1, player2)){
                //player1
                newgame.checktraining(newgame.player1, newgame.turn);
                ArrayList<Command> commands = new ArrayList<>();
                System.out.println("Tunr: "+ newgame.turn);
                in = new Scanner(System.in);
                System.out.println("Please add Soldiers for player1:");
                while(player1.getTreasury() > 0){
                    System.out.println("name:");
                    String name = in.nextLine();
    
                    System.out.println("number:");
                    int num = in.nextInt();
    
                    in = new Scanner(System.in);
                    System.out.println("province:");
                    String prov = in.nextLine();

                    Command as = new AddSoldierCommand(name, prov, player1, newgame.turn, num);
                    commands.add(as);

                    in = new Scanner(System.in);
                    System.out.println("Continue or end step?");
                    String step = in.nextLine();

                    if(step.equals("END") ){
                        break;
                    }else{
                        continue;
                    }
                }

                System.out.println("Please add Soldiers for player2:");
                while(player1.getTreasury() > 0){
                    System.out.println("name:");
                    String name = in.nextLine();
    
                    System.out.println("number:");
                    int num = in.nextInt();
    
                    in = new Scanner(System.in);
                    System.out.println("province:");
                    String prov = in.nextLine();
                    Command as = new AddSoldierCommand(name, prov, player2, newgame.turn, num);
                    commands.add(as);

                    in = new Scanner(System.in);
                    System.out.println("Continue or end step?");
                    String step = in.nextLine();
        
                    if(step.equals("END") ){
                        break;
                    }else{
                        continue;
                    }
                }
                newgame.addturn(1);
                for(Command c : commands){
                    c.execute();
                }
                commands = new ArrayList<>();
                //newgame.notifier(newgame); // not correct here!!
            }
        }
    }
            
       
        

            /*in = new Scanner(System.in);
            System.out.println("Please choose create buildings:");
            String building = in.nextLine();
            String buidprovince = in.nextLine();
            for(Province p : provinces1){
                if(p.getName().equals(buidprovince)){
                    UpdateWealthInstruction instruction = new UpdateWealthInstruction(building, p,player1);
                    instruction.MakeInstruction();
                    System.out.println(p.getInfrastructures());
                    System.out.println(player1.getTreasury());
                }
            }
            in = new Scanner(System.in);
            System.out.println("Please choose update buildings:");
            building = in.nextLine();
            buidprovince = in.nextLine();
            for(Province p : provinces1){
                if(p.getName().equals(buidprovince)){
                    UpdateWealthInstruction instruction = new UpdateWealthInstruction(building, p, player1);
                    instruction.MakeInstruction();
                    System.out.println(p.getInfrastructures());
                    System.out.println(player1.getTreasury());
                }
            }*/


            /*BasicCampaign newgame = new BasicCampaign(player1, player2);
            in = new Scanner(System.in);
            System.out.println("Start point: ");
            String start =  in.nextLine();
            System.out.println("dest point: ");
            String dest = in.nextLine();
            ShortestPath d = new ShortestPath(player1, start, dest);
            d.movement();*/

 

    
    private void processCommand1(JSONObject json,ArrayList<Command> commands) throws IOException {
        switch (json.getString("command")) {
            case "AddSoldier":
                String name = json.getString("name");
                int number = json.getInt("number");
                String province = json.getString("province");
                //System.out.println( name + number + province);
                Command temp = new AddSoldierCommand(name, province, player1, this.turn, number);
                commands.add(temp);
                break;
            
            case "AddMovement":
                String fromp = json.getString("from");
                int unitnumber = json.getInt("unitid");
                String top = json.getString("to");
                Province from = player1.getProvinces().get(0);
                Province to = player1.getProvinces().get(0);
                Unit unit = player1.getProvinces().get(0).getUnits().get(0);
                for(Province p : player1.getProvinces()){
                    if(p.getName().equals(fromp)){
                        unit = p.getUnits().get(unitnumber);
                        from = p;
                    }
                    else if(p.getName().equals(top)){
                        to = p;
                    }
                }
                temp = new AddMoveMentCommand(player1, from, to, this.turn, unit);
                commands.add(temp);
                break;
            
            case "AddAttack":
                String attackprovince = json.getString("from");
                String enemyprovince = json.getString("to");
                from = player1.getProvinces().get(0);
                to = player2.getProvinces().get(0);
                for(Province p : player1.getProvinces()){
                    if(p.getName().equals(attackprovince)){
                        from = p;
                    }
                }
                for(Province p : player2.getProvinces()){
                if(p.getName().equals(enemyprovince)){
                        to = p;
                    }
                }
                temp = new AddAttackCommand(this.player1, from.getUnits(), this.player2, to.getUnits());
                commands.add(temp);
                break;

            case "Save":
                break;

        }
        


    }
    private void processCommand2(JSONObject json) throws IOException {
        switch (json.getString("command")) {
            case "AddSoldier":
                String name = json.getString("name");
                int number = json.getInt("number");
                String province = json.getString("province");
                Command temp = new AddSoldierCommand(name, province, player2, this.turn, number);
                this.commands.add(temp);
                break;
            
            case "AddMovement":
                String fromp = json.getString("from");
                int unitnumber = json.getInt("unitid");
                String top = json.getString("to");
                Province from = player2.getProvinces().get(0);
                Province to = player2.getProvinces().get(0);
                Unit unit = player2.getProvinces().get(0).getUnits().get(0);
                for(Province p : player2.getProvinces()){
                    if(p.getName().equals(fromp)){
                        unit = p.getUnits().get(unitnumber);
                        from = p;
                    }
                    else if(p.getName().equals(top)){
                        to = p;
                    }
                }
                temp = new AddMoveMentCommand(player2, from, to, this.turn, unit);
                this.commands.add(temp);
                break;
            
            case "AddAttack":
                String attackprovince = json.getString("from");
                String enemyprovince = json.getString("to");
                from = player2.getProvinces().get(0);
                to = player1.getProvinces().get(0);
                for(Province p : player2.getProvinces()){
                    if(p.getName().equals(attackprovince)){
                        from = p;
                    }
                }
                for(Province p : player2.getProvinces()){
                if(p.getName().equals(enemyprovince)){
                        to = p;
                    }
                }
                temp = new AddAttackCommand(this.player2, from.getUnits(), this.player1, to.getUnits());
                this.commands.add(temp);
                break;

        }


    }
    public void addturn(int i){
        this.turn += i;
    }
    public void checktraining(Player player, int turn){
        for(int i = 0; i < player.getProvinces().size(); i++){
            ArrayList<Unit> temp = player.getProvinces().get(i).getTraining();

            for(int j = 0; j < temp.size(); j++){
                if(temp.get(j).getOccurturn() == turn){
                    player.getProvinces().get(i).removetraing(temp.get(j));
                    System.out.println("This is Units: " +player.getProvinces().get(i).getUnits());
                }
            }
        }
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public ArrayList<Command> getCommands() {
        return commands;
    }

    public void setCommands(ArrayList<Command> commands) {
        this.commands = commands;
    }

}
