package unsw.gloriaromanus;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import unsw.gloriaromanus.mode.Movement;
import unsw.gloriaromanus.mode.Province;
import unsw.gloriaromanus.mode.ShortestPath;
import unsw.gloriaromanus.mode.Unit;

public class MoveController1 {
    @FXML
    private ChoiceBox from;

    @FXML
    private ChoiceBox to;

    @FXML
    private ChoiceBox units;

    private static Stage stage;

    private ObservableList<String> provincelist1;
    private ObservableList<String> provincelist2;

    private ObservableList<String> unitlist;

    public void addprovince1() {
        provincelist1 = FXCollections.observableArrayList();
        for (Province p : goal1Controller.s1.Player1().getProvinces()) {
            provincelist1.add(p.getName());

        }
    }

    public void addprovince2() throws IOException {
        provincelist2 = FXCollections.observableArrayList();
        String province = (String) from.getValue();
        ShortestPath s = new ShortestPath(goal1Controller.s1.Player1(), null, null);
        for (Province p : goal1Controller.s1.Player1().getProvinces()) {
            s = new ShortestPath(goal1Controller.s1.Player1(), province, p.getName());
            if(s.movement()!= null && s.movement().size() > 0) {
                provincelist2.add(p.getName());
            }
        }
    }

    public void addunits() {
        unitlist = FXCollections.observableArrayList();
        String province = (String) from.getValue();
        for (Province p : goal1Controller.s1.Player1().getProvinces()) {
            if (p.getName().equals(province)) {
                unitlist = FXCollections.observableArrayList();
                for (Unit u : p.getUnits()) {
                    unitlist.add(u.toString());
                }
            }
        }
    }

    @FXML
    public void initialize() throws IOException {
        addprovince1();
        from.setItems(provincelist1);

    }
    
    @FXML
    public void handlecancel() {
        InvasionMenuController.ColseMoveStage();
    }
    @FXML
    public void handleConfirm(ActionEvent e) throws IOException{
        String p = (String) to.getValue();
        String i = (String) units.getValue();
        String f = (String) from.getValue();

        if(p.equals("No province connected!") || i.equals("No unit!")){
            stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("warningempty.fxml"));
            Pane rootmerchat = (Pane)loader.load();
            System.out.println(loader);
            Scene createScene = new Scene(rootmerchat); 
            System.out.println(createScene);
            stage.setTitle("Merchant");
            stage.setScene(createScene);
            stage.show();
        }
        else if(p == null ||i == null || f == null){
            stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("warningempty.fxml"));
            Pane rootmerchat = (Pane)loader.load();
            System.out.println(rootmerchat);
            Scene createScene = new Scene(rootmerchat); 
            System.out.println(createScene);
            stage.setTitle("Merchant");
            stage.setScene(createScene);
            stage.show();
        }else{
            Province fr = new Province(null);
            Province t1 = new Province(null);

            for(Province l : goal1Controller.s1.Player1().getProvinces()){
                if(l.getName().equals(f)){
                    fr = l;
                }
                else if(l.getName().equals(p)){
                    t1 = l;
                }
            }
            Unit ui = new Unit(0, null, null, 3, null);
            for(Unit u : fr.getUnits()){
                if(u.toString().equals(i)){
                    ui = u;
                }
            }
            Movement m = new Movement(goal1Controller.s1.Player1(), fr, t1, goal1Controller.startturn, ui);
            m.getMovement();
            InvasionMenuController.ColseMoveStage();
        }

    }
    @FXML
    public void handlecheck(ActionEvent e) throws IOException{
        addprovince2();
        if(provincelist2.size() > 0){
            to.setItems(provincelist2);
        }else{
            provincelist2.add("No province connected!");
            to.setItems(provincelist2);
        }
        addunits();
        if(unitlist.size() > 0){
            units.setItems(unitlist);
        }else{
            unitlist.add("No unit!");
            units.setItems(unitlist);
        }
    }
    @FXML
    public static void handlclose(ActionEvent e) throws IOException{
        stage.close();
    }
}

