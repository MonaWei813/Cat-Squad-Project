package unsw.gloriaromanus;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import unsw.gloriaromanus.mode.Province;

public class SearchController2 {
    @FXML
    private ChoiceBox province;

    @FXML
    private TextArea result;

    @FXML
    private Button close;
    
    private ObservableList<String> provincelist;

    public void addprovince() {
        provincelist = FXCollections.observableArrayList();
        for (Province p : goal1Controller.s1.Player2().getProvinces()) {
            provincelist.add(p.getName());
        }
    }
    @FXML
    public void initialize() {
        addprovince();
        province.setItems(provincelist);

    }

    @FXML
    public void handSearch(ActionEvent e) throws IOException{
        String temp = (String)province.getValue();
        for(Province p : goal1Controller.s1.Player2().getProvinces()){
            if(p.getName().equals(temp)){
                result.setText(p.toString());
            }
        }
    }
    @FXML
    public void handleClose(ActionEvent e) throws IOException{
        InvasionMenuController2.ColseSearchStage();
    }
}
