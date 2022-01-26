package unsw.gloriaromanus;

import java.io.IOException;
import java.util.Observable;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import unsw.gloriaromanus.mode.Player;
import unsw.gloriaromanus.mode.Province;
import unsw.gloriaromanus.mode.Unit;

public class MerchantController1 {
    @FXML
    private Button confirm;

    @FXML
    private Button cancel;

    @FXML
    private ChoiceBox numbersoldier;

    @FXML
    private ChoiceBox listprovinces;

    @FXML
    private ToggleGroup groupsoldier;

    @FXML
    private RadioButton soldier1;
    @FXML
    private RadioButton soldier2;
    @FXML
    private RadioButton soldier3;
    @FXML
    private RadioButton soldier4;
    @FXML
    private RadioButton soldier5;
    @FXML
    private RadioButton soldier6;
  
    private ObservableList<Integer> numberlist;
    private ObservableList<String> provincelist;
    private static Stage stage;

    public void addprovince() {
        provincelist = FXCollections.observableArrayList();
        for (Province p : goal1Controller.s1.Player1().getProvinces()) {
            if (p.getTraining().size() < 2) {
                provincelist.add(p.getName());
            }
        }
    }

    public void addnum() {
        numberlist = FXCollections.observableArrayList();
        for (int i = 1; i < 15; i++) {
            numberlist.add(i);
        }

    }

    @FXML
    public void initialize() {
        addprovince();
        listprovinces.setItems(provincelist);
        addnum();
        numbersoldier.setItems(numberlist);
    }

    @FXML
    public void handlecancel() {
        InvasionMenuController.ColseMerchantSatge();
    }

    @FXML
    public void handleConfirm(ActionEvent e) throws IOException {
        if (listprovinces.getValue() == null || numbersoldier.getValue() == null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("WarningaddSoldiers.fxml"));
            stage = new Stage();
            Parent root;
            root = loader.load();
            Scene scene = new Scene(root);
            stage = new Stage();
            stage.setTitle("Warning");
            stage.setScene(scene);
            stage.show();
        } else {
            String addprovince = (String) listprovinces.getValue();
            int number = (int) numbersoldier.getValue();
            Player player = goal1Controller.s1.Player1();

            if (soldier1.isSelected()) {
                String soldiername = soldier1.getText();
                for (Province p : player.getProvinces()) {
                    if (p.getName().equals(addprovince)) {
                        Unit unit = new Unit(goal1Controller.startturn, player, soldiername, number, p);
                        unit.addSoldiers();
                        System.out.println("training: " + p.getTraining());
                        InvasionMenuController.ColseMerchantSatge();
                    }
                }
            }

            else if (soldier2.isSelected()) {
                String soldiername = soldier2.getText();
                for (Province p : player.getProvinces()) {
                    if (p.getName().equals(addprovince)) {
                        Unit unit = new Unit(goal1Controller.startturn, player, soldiername, number, p);
                        unit.addSoldiers();
                        System.out.println("training: " + p.getTraining());
                        InvasionMenuController.ColseMerchantSatge();
                    }

                }
            }

            else if (soldier3.isSelected()) {
                String soldiername = soldier3.getText();
                for (Province p : player.getProvinces()) {
                    if (p.getName().equals(addprovince)) {
                        Unit unit = new Unit(goal1Controller.startturn, player, soldiername, number, p);
                        unit.addSoldiers();
                        System.out.println("training: " + p.getTraining());
                        InvasionMenuController.ColseMerchantSatge();

                    }
                }
            }

            else if (soldier4.isSelected()) {
                String soldiername = soldier4.getText();
                for (Province p : player.getProvinces()) {
                    if (p.getName().equals(addprovince)) {
                        Unit unit = new Unit(goal1Controller.startturn, player, soldiername, number, p);
                        unit.addSoldiers();
                        System.out.println("training: " + p.getTraining());
                        InvasionMenuController.ColseMerchantSatge();

                    }
                }
            }

            else if (soldier5.isSelected()) {
                String soldiername = soldier5.getText();
                for (Province p : player.getProvinces()) {
                    if (p.getName().equals(addprovince)) {
                        Unit unit = new Unit(goal1Controller.startturn, player, soldiername, number, p);
                        unit.addSoldiers();
                        System.out.println("training: " + p.getTraining());
                        InvasionMenuController.ColseMerchantSatge();

                    }
                }
            }

            else if (soldier6.isSelected()){
                String soldiername = soldier6.getText();
                for (Province p : player.getProvinces()) {
                    if (p.getName().equals(addprovince)) {
                        Unit unit = new Unit(goal1Controller.startturn, player, soldiername, number, p);
                        unit.addSoldiers();
                        System.out.println("training: " + p.getTraining());
                        InvasionMenuController.ColseMerchantSatge();

                    }
                }
            }
            else{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("WarningaddSoldiers.fxml"));
                stage = new Stage();
                Parent root;
                root = loader.load();
                Scene scene = new Scene(root);
                stage = new Stage();
                stage.setTitle("Warning");
                stage.setScene(scene);
                stage.show();
            }

        }
    }

    public static void ClosedStage() {
        stage.close();
    }
     

}


