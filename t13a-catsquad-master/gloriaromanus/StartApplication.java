package unsw.gloriaromanus;

import java.io.File;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class StartApplication extends Application {

    public static Stage pStage;
    public static MediaPlayer mediaplayer;

    @Override
    public void start(Stage primaryStage) throws Exception {
        String path = "music/Main.mp3";
        Media media = new Media(new File(path).toURI().toString());
        mediaplayer = new MediaPlayer(media);
        StartApplication.Mediaplayerplay();
        setPrimaryStage(primaryStage);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("StartView.fxml"));
        Parent root = loader.load();
        Scene startScene = new Scene(root);

        primaryStage.setTitle("GLORIA ROMANUS");
        primaryStage.setScene(startScene);
        primaryStage.show();
    }

    public static Stage getPrimaryStage() {
        return pStage;
    }

    private void setPrimaryStage(Stage pStage) {
        StartApplication.pStage = pStage;
    }
    public static void Mediaplayerplay() {
        mediaplayer.play();
    }

    public static void setMediaplayerclose() {
        mediaplayer.pause();
    }
    
    public static void main(String[] args) {
        launch(args);
    }






}
