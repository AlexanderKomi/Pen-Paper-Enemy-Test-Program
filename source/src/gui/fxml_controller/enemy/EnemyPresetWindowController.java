package gui.fxml_controller.enemy;

import io.IOController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import model.enemies.Enemy;

import java.util.LinkedList;
import java.util.List;

public class EnemyPresetWindowController {

    //-------------------------------- FXML MEMBERS ---------------------------------

    @FXML
    Button selectButton, cancelButton;

    @FXML
    VBox EnemyBox;

    @FXML
    TextArea descriptionField;

    @FXML
    EnemyBoxController EnemyBoxController;
    private IOController ioController;
    private List<Enemy> enemyList;

    //---------------------------------- MEMBERS -------------------------------------


    //--------------------------------- FXML METHODS -----------------------------------

    @FXML
    public void initialize(){
        this.ioController = new IOController();
        this.enemyList = new LinkedList<>();
    }

    @FXML
    public void selectButtonPressed() {
        System.out.println("Enemy : " + this.EnemyBoxController.getSelected() + " has been selected");
    }

    @FXML
    public void cancelButtonPressed() {this.cancelButton.getScene().getWindow().hide();}

    //--------------------------------- PRIVATE METHODS --------------------------------


    public void addToPresetsCheckBoxes(Enemy e) {
        this.EnemyBoxController.addToCheckboxes(e);
        this.enemyList.add(e);
    }

    public void updateCheckboxes(List<Enemy> enemy) {

        if (this.EnemyBox == null) {
            System.out.println("vBox is null !");
            return;
        }
        this.enemyList = enemy;
        this.EnemyBoxController.updateCheckboxes(enemy);

    }


    //--------------------------------- GETTER AND SETTER -------------------------------



}
