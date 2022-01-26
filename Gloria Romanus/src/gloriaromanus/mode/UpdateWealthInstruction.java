package unsw.gloriaromanus.mode;

public class UpdateWealthInstruction implements Instruction{
    private String name;
    private Province province;
    private Player player;

    public UpdateWealthInstruction(String name, Province province, Player player) {
        this.name = name;
        this.province = province;
        this.player = player;
    }
    
    @Override
    public void MakeInstruction(){
        int countmarket = 0;
        int countfarms = 0;
        int countports = 0;
        int countmines = 0;
        int count = 0;
        for(Infrastructure f : province.getInfrastructures()){
            if(f instanceof WealthGenerationBuilding){
                if(f.getName().equals("market")){
                    countmarket += 1;
                }
                else if(f.getName().equals("farm")){
                    countfarms += 1;
                }
                else if(f.getName().equals("port")){
                    countports += 1;
                }
                else if(f.getName().equals("mine")){
                    countmines += 1;
                }
                else if(f.getName().equals("Wealth generation buildings")){
                    count += 1;
                }
            }
        }
        if(name.equals("Wealth generation buildings")){
            if(count == 0){
                WealthGenerationBuilding temp = new WealthGenerationBuilding("Wealth generation buildings");
                player.payingCost(temp.getUpdateprice());
                province.addInfrastructures(temp);
                temp.updatelevel();
            }else{
                for(Infrastructure f : province.getInfrastructures()){
                    if( f instanceof WealthGenerationBuilding &&
                    f.getName().equals("Wealth generation buildings")){
                        WealthGenerationBuilding d = (WealthGenerationBuilding)f;
                        player.payingCost(d.getUpdateprice());
                        d.updatelevel();
                    }
                }     
            }
        }

        if(name.equals("market")){
            if(countmarket == 0){
                WealthGenerationBuilding temp = new WealthGenerationBuilding("market");
                player.payingCost(temp.getUpdateprice());
                province.addInfrastructures(temp);
                temp.updatelevel();
            }else{
                for(Infrastructure f : province.getInfrastructures()){
                    if( f instanceof WealthGenerationBuilding &&
                    f.getName().equals("market")){
                        WealthGenerationBuilding d = (WealthGenerationBuilding)f;
                        player.payingCost(d.getUpdateprice());
                        d.updatelevel();
                    }
                } 
            }

        }
        else if(name.equals("farm")){
            if(countfarms == 0){
                WealthGenerationBuilding temp = new WealthGenerationBuilding("farm");
                player.payingCost(temp.getUpdateprice());
                province.addInfrastructures(temp);
                temp.updatelevel();
            }else{
                for(Infrastructure f : province.getInfrastructures()){
                    if( f instanceof WealthGenerationBuilding &&
                    f.getName().equals("farm")){
                        WealthGenerationBuilding d = (WealthGenerationBuilding)f;
                        player.payingCost(d.getUpdateprice());
                        d.updatelevel();
                    }
                } 
            }
        }
        else if(name.equals("port")){
            if(countports == 0){
                WealthGenerationBuilding temp = new WealthGenerationBuilding("port");
                player.payingCost(temp.getUpdateprice());
                province.addInfrastructures(temp);
                temp.updatelevel();
            }else{
                for(Infrastructure f : province.getInfrastructures()){
                    if( f instanceof WealthGenerationBuilding &&
                    f.getName().equals("port")){
                        WealthGenerationBuilding d = (WealthGenerationBuilding)f;
                        player.payingCost(d.getUpdateprice());
                        d.updatelevel();
                    }
                } 
            }
        }
        else if(name.equals("mine")){
            if(countmines == 0){
                WealthGenerationBuilding temp = new WealthGenerationBuilding("mine");
                player.payingCost(temp.getUpdateprice());
                province.addInfrastructures(temp);
                temp.updatelevel();
            }else{
                for(Infrastructure f : province.getInfrastructures()){
                    if( f instanceof WealthGenerationBuilding &&
                    f.getName().equals("mine")){
                        WealthGenerationBuilding d = (WealthGenerationBuilding)f;
                        player.payingCost(d.getUpdateprice());
                        d.updatelevel();
                    }
                } 
            }
        }

    }


}
