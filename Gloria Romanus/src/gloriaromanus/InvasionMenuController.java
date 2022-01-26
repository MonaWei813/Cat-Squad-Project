package unsw.gloriaromanus;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import unsw.gloriaromanus.mode.Province;

public class InvasionMenuController extends MenuController{
    @FXML
    private TextField invading_province;
    @FXML
    private TextField opponent_province;
    @FXML
    private TextArea output_terminal;
    @FXML
    private Button move1;
  
    @FXML 
    private Button merchat1;

    @FXML
    private Button Search;

    private static Stage merchantSatge;
    private static Stage moveStage;
    private static Stage searchStage;
 


    // https://stackoverflow.com/a/30171444
    @FXML
    private URL location; // has to be called location

    public void setInvadingProvince(String p) {
        invading_province.setText(p);
    }

    public void setOpponentProvince(String p) {
        opponent_province.setText(p);
    }

    public void appendToTerminal(String message) {
        output_terminal.appendText(message + "\n");
    }

 

    @FXML
    public void clickedInvadeButton(ActionEvent e) throws IOException {
        getParent().clickedInvadeButton(e);
    }

    @FXML
    public void handleMerchant(ActionEvent e) throws IOException{
        merchantSatge = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Merchant.fxml"));
        Pane rootmerchat = (Pane)loader.load();
        System.out.println(rootmerchat);
        Scene createScene = new Scene(rootmerchat); 
        System.out.println(createScene);
        merchantSatge.setTitle("Merchant");
        merchantSatge.setScene(createScene);
        merchantSatge.show();
    }
    
    @FXML
    public void handleMove(ActionEvent e) throws IOException{
        moveStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MoveView.fxml"));
        Pane rootmerchat = (Pane)loader.load();
        System.out.println(rootmerchat);
        Scene createScene = new Scene(rootmerchat); 
        System.out.println(createScene);
        moveStage.setTitle("Move");
        moveStage.setScene(createScene);
        moveStage.show();
    }

    @FXML 
    public void handleSearch(ActionEvent e) throws IOException{
        searchStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Search.fxml"));
        Pane rootmerchat = (Pane)loader.load();
        System.out.println(rootmerchat);
        Scene createScene = new Scene(rootmerchat); 
        System.out.println(createScene);
        searchStage .setTitle("Search");
        searchStage .setScene(createScene);
        searchStage .show();
    }
    FileChooser filechooser = new FileChooser();

    public static void ColseMerchantSatge() {
        InvasionMenuController.merchantSatge.close();
    }

    public static void ColseMoveStage() {
        InvasionMenuController.moveStage.close();
    }
    
    public static void ColseSearchStage() {
        InvasionMenuController.searchStage.close();
    }
    
}
