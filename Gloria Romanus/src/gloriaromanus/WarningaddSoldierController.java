package unsw.gloriaromanus;


import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


public class WarningaddSoldierController {
    @FXML
    private Button closed;

    @FXML
    public void handleclosed(ActionEvent event) throws IOException{
        MerchantController1.ClosedStage();
    }
    

}
