package unsw.gloriaromanus;

import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class mainmenuController {
    @FXML
    public void mainmenu(ActionEvent e) throws Exception {
        Stage stage = StartApplication.getPrimaryStage();
        stage.close();
        StartApplication s = new StartApplication();
        Stage primaryStage = new Stage();
        s.start(primaryStage);
    }
}
