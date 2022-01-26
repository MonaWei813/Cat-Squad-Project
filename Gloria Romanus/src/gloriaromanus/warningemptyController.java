package unsw.gloriaromanus;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class warningemptyController {
    @FXML
    public void handleclose(ActionEvent e) throws IOException{
        MoveController1.handlclose(e);
    }
}
