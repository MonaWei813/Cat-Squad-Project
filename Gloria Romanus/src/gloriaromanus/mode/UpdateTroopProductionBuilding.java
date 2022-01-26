package unsw.gloriaromanus.mode;

public class UpdateTroopProductionBuilding implements Instruction{
    private String name;
    private Province province;
    private Player player;

    public UpdateTroopProductionBuilding(String name, Province province, Player player) {
        this.name = name;
        this.province = province;
        this.player = player;
    }
    @Override
    public void MakeInstruction(){
        int count = 0;
        for(Infrastructure f : province.getInfrastructures()){
            if(f instanceof TroopProductionBuilding){
                count++;
            }
        }
        if(count == 0){
            TroopProductionBuilding temp = new TroopProductionBuilding("Troop production building");
            player.payingCost(temp.getUpdateprice());
            province.addInfrastructures(temp);
            temp.updatelevel();

        }else{
            for(Infrastructure f : province.getInfrastructures()){
                if( f instanceof TroopProductionBuilding &&
                f.getName().equals("Troop production building")){
                    TroopProductionBuilding t = (TroopProductionBuilding)f;
                    player.payingCost(t.getUpdateprice());
                    t.updatelevel();
                }
            }
        }
    }
    
}
