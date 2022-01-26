package unsw.gloriaromanus.mode;

import java.io.IOException;

public class AddSaveCommand implements Command {
    private Player player1;
    private Player player2;
    private BasicCampaign newgame = new BasicCampaign(player1, player2);
    public AddSaveCommand(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }
    @Override
    public void execute(){
        Save save = new Save(newgame);
      
    
    }

}
