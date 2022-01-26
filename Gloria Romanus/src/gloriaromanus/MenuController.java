package unsw.gloriaromanus;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public abstract class MenuController {
    private GloriaRomanusController parent;


    public void setParent(GloriaRomanusController parent) {
        if (parent == null){
            System.out.println("GOT NULL");
        }
        this.parent = parent;
    }

    public GloriaRomanusController getParent(){
        return parent;
    }

    @FXML
    public void handleendturn(ActionEvent e) throws Exception {
        parent.handleendturn();
    }
    @FXML
    public void handleendturn2(ActionEvent e) throws Exception {
        parent.handleendturn2();
    }
}
