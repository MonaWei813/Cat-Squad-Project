package unsw.gloriaromanus;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import unsw.gloriaromanus.mode.Player;
public class player_statsController2 {
    @FXML
    private Label wealth;
    @FXML
    private Label treasury;
    @FXML
    private Label provincenum;
    @FXML
    private Label turn;

    @FXML
    public void initialize() {
        Player p1 = goal1Controller.s1.Player2();
        treasury.setText(Integer.toString(p1.getTreasury()));
        
        provincenum.setText(Integer.toString(p1.getProvinces().size()));
        turn.setText(Integer.toString(goal1Controller.startturn));
        int count = goal1Controller.startturn * p1.getProvinces().size() * 50;
        wealth.setText(Integer.toString(count));
    }
    @FXML
    public void handleclose(ActionEvent e) throws Exception {
        goal2Controller.close();
    }
}
