package unsw.gloriaromanus;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class WarningViewController {
    @FXML
    private Button closed;
    private Stage stage;


    @FXML
    public void handleclosed(ActionEvent event) throws IOException{
        stage = CreatePlayerController.getStage();
        stage.close();
    }


}
