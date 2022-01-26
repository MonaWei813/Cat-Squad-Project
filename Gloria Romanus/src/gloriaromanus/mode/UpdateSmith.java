package unsw.gloriaromanus.mode;

public class UpdateSmith implements Instruction{
    private String name;
    private Province province;
    private Player player;

    public UpdateSmith(String name, Province province, Player player) {
        this.name = name;
        this.province = province;
        this.player = player;
    }
    @Override
    public void MakeInstruction(){
        int count = 0;
        for(Infrastructure f : province.getInfrastructures()){
            if(f instanceof Smiths){
                count++;
            }
        }
        if(count == 0){
            Smiths temp = new Smiths("Smiths");
            player.payingCost(temp.getUpdateprice());
            province.addInfrastructures(temp);
            temp.updatelevel();
        }else{
            for(Infrastructure f : province.getInfrastructures()){
                if(f instanceof Smiths && f.getName().equals("Smiths")){
                    Smiths w = (Smiths) f;
                    player.payingCost(w.getUpdateprice());
                    w.updatelevel();
                }
            }
        }

    }

    
}
