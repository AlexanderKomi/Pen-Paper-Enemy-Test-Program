package gui.fxml_controller.enemy;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import model.enemies.Enemy;
import model.player.Player;

import java.util.List;

public class EnemyPresetWindowController {

    //-------------------------------- FXML MEMBERS ---------------------------------

    @FXML
    Button selectButton, cancelButton;

    @FXML
    VBox EnemyBox;

    @FXML
    EnemyBoxController EnemyBoxController;

    @FXML
    TextArea descriptionField;

    //---------------------------------- MEMBERS -------------------------------------


    //--------------------------------- FXML METHODS -----------------------------------

    @FXML
    public void selectButtonPressed() {
    }

    @FXML
    public void cancelButtonPressed() {
    }

    //--------------------------------- PRIVATE METHODS --------------------------------


    public void addToPresetsCheckBoxes(Player p) {

        this.EnemyBoxController.addToCheckboxes(p);
    }

    public void updateCheckboxes(List<Enemy> enemy) {

        if (this.EnemyBox == null) {
            System.out.println("vBox is null !");
            return;
        }

        this.EnemyBoxController.updateCheckboxes(enemy);

    }


    //--------------------------------- GETTER AND SETTER -------------------------------


}
