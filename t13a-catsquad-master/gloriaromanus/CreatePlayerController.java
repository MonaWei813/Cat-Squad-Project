package unsw.gloriaromanus;


import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import unsw.gloriaromanus.mode.GetGaulProvince;
import unsw.gloriaromanus.mode.GetRomeProvince;
import unsw.gloriaromanus.mode.Province;

public class CreatePlayerController {
    @FXML
    private TextField playername;

    @FXML
    private RadioButton rome;

    @FXML
    private RadioButton gaul;

    @FXML
    private Button next;

    @FXML
    private ToggleGroup country;

    private static Stage stage;
    private static String Player1Country = null;
    private static String Player1name = null;
    private static String Player2Country = null;
    private static ArrayList<Province> province1 = new ArrayList<Province>();
    private static ArrayList<Province> province2 = new ArrayList<Province>();

    @FXML
    public void handleNext(ActionEvent event) throws IOException {
        Player1name = playername.getText(); //get the player1 name

        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("CreatePlayer2.fxml"));
        Parent root2;
        root2 = loader2.load();
        Scene CreateScene = new Scene(root2);

        if(rome.isSelected()){          //get the player1 country
            Player1Country = "Rome";
            Player2Country = "Gaul";
            System.out.println(Player1Country);
            GetRomeProvince romeprovince = new GetRomeProvince();
            province1 = romeprovince.GetInitialProvinces();
            System.out.println(province1);
            GetGaulProvince gaulprovince = new GetGaulProvince();
            province2 = gaulprovince.GetInitialProvinces();
            stage = new Stage();
            stage = StartApplication.getPrimaryStage();
            stage.setTitle("Create Player");
            stage.setScene(CreateScene);
            stage.show();
        }
        else if (gaul.isSelected()){
            Player1Country = "Gaul";
            Player2Country = "Rome";
            System.out.println(Player1Country);
            GetRomeProvince romeprovince = new GetRomeProvince();
            GetGaulProvince gaulprovince = new GetGaulProvince();
            province1 = gaulprovince.GetInitialProvinces();
            province2 = romeprovince.GetInitialProvinces();
            stage = new Stage();
            stage = StartApplication.getPrimaryStage();
            stage.setTitle("Create Player");
            stage.setScene(CreateScene);
            stage.show();
        }
        else if(!gaul.isSelected() && !rome.isSelected()){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("WarningView.fxml"));
            Parent root;
            root = loader.load();
            CreateScene = new Scene(root);
            stage = new Stage();
            stage.setTitle("Warning");
            stage.setScene(CreateScene);
            stage.show();
        }

    }

    public static String getChooseCountry() {
        return Player1Country;
    }

    public static Stage getStage() {
        return stage;
    }

    public static String getPlayer1name() {
        return Player1name;
    }

    public static void setPlayer1name(String player1name) {
        Player1name = player1name;
    }

    public static String getPlayer2Country() {
        return Player2Country;
    }

    public static void setPlayer2Country(String player2Country) {
        Player2Country = player2Country;
    }

    public static ArrayList<Province> getProvince1() {
        return province1;
    }

    public static ArrayList<Province> getProvince2() {
        return province2;
    }

}
