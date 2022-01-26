package unsw.gloriaromanus;

import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import unsw.gloriaromanus.mode.Player;


public class CreatePlayer2Controller {
    @FXML
    private Label message;

    @FXML
    private TextField player2name;

    @FXML
    private Button entergame;

    private static String Player2name;
    
    private Stage stage;
    private static MediaPlayer mediaplayer;
    
    @FXML
    public void handleEntergame(ActionEvent event) throws IOException {
        String path = "music/ingame.mp3";
        Media media = new Media(new File(path).toURI().toString());
        mediaplayer = new MediaPlayer(media);
        mediaplayer.play();
        StartApplication.setMediaplayerclose();
        Player2name = player2name.getText();
        stage = new Stage();
        stage = StartApplication.getPrimaryStage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
    
        // set up the stage
        stage.setTitle("Gloria Romanus");
        stage.setWidth(1277);
        stage.setHeight(720);
        stage.setScene(scene);
        stage.show();

    }
    
    public static String getPlayer2name() {
        return Player2name;
    }

    public static void Mediaplayerplay() {
        mediaplayer.play();
    }
    public static void Mediaplayerclosed() {
        mediaplayer.pause();
    }

    

}
