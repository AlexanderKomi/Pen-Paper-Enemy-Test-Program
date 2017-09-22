package gui.fxml_controller.enemy;

import io.IOController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import model.Units.Enemy;

import java.util.LinkedList;
import java.util.List;

public class EnemyPresetWindowController {

    //-------------------------------- FXML MEMBERS ---------------------------------

    @FXML
    Button selectButton, cancelButton, loadButton, saveButton;

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
    public void loadButtonPressed(){
        Enemy e = this.ioController.loadEnemy();
        addToPresetsCheckBoxes(e);
    }

    @FXML
    public void saveButtonPressed(){
        String selectedCheckBox = this.EnemyBoxController.getSelected();
        System.out.println("saveButtonPressed : " + selectedCheckBox);

        for(Enemy e : this.enemyList){

            if(e.getName().equals(selectedCheckBox)){

                this.ioController.saveEnemy(e);
                break;
            }

        }
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
