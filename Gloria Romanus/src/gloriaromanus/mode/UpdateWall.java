package unsw.gloriaromanus.mode;

public class UpdateWall implements Instruction{
    private String name;
    private Province province;
    private Player player;

    public UpdateWall(String name, Province province, Player player) {
        this.name = name;
        this.province = province;
        this.player = player;
    }
    @Override
    public void MakeInstruction(){
        int count = 0;
        for(Infrastructure f : province.getInfrastructures()){
            if(f instanceof Wall){
                count++;
            }
        }
        if(count == 0){
            Wall temp = new Wall("Wall");
            player.payingCost(temp.getUpdateprice());
            province.addInfrastructures(temp);
            temp.updatelevel();
        }else{
            for(Infrastructure f : province.getInfrastructures()){
                if(f instanceof Wall && f.getName().equals("wall")){
                    Wall w = (Wall) f;
                    player.payingCost(w.getUpdateprice());
                    w.updatelevel();
                }
            }
        }

    }
}
