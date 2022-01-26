package unsw.gloriaromanus;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import unsw.gloriaromanus.mode.Goal;
import unsw.gloriaromanus.mode.Player;
import unsw.gloriaromanus.mode.Province;

public class goal1Controller extends MenuController{
    @FXML
    private Label playername;

    @FXML
    private Label provincenumber;
    public static Goal gamegoal = new Goal(0,0);
    public static Stage stage;

    public static SaveInfromation s1 = new SaveInfromation(CreatePlayerController.getPlayer1name(),
            CreatePlayer2Controller.getPlayer2name(), CreatePlayerController.getProvince1(),
            CreatePlayerController.getProvince2(), CreatePlayerController.getChooseCountry(),
            CreatePlayerController.getPlayer2Country());
    public static int startturn = 0;

    @FXML
    public void initialize() {
        Player p1 = s1.Player1();
        playername.setText("PlayerName: " + p1.getName());
    }
    @FXML
    public void information(ActionEvent e) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("player1_stat.fxml"));
        stage = new Stage();
        Parent root;
        root = loader.load();
        Scene scene = new Scene(root);
        stage = new Stage();
        stage.setTitle("Infromation");
        stage.setScene(scene);
        stage.show();
    }

    public static void close(){
        stage.close();
    }
}
