package unsw.gloriaromanus;


import java.io.IOException;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class StartController {
    @FXML
    private Button NewGame;

    @FXML
    private Button history;

    Stage stage;
    
    @FXML
    public void handleNewgame(ActionEvent event) throws IOException {
        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("CreatePlayerView.fxml"));
        Parent root2;
        root2 = loader2.load();
        Scene CreateScene = new Scene(root2);
        stage = new Stage();
        stage = StartApplication.getPrimaryStage();
        stage.setTitle("Create Player");
        stage.setScene(CreateScene);
        stage.show();
    }
}
