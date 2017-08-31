package gui.fxml_controller.enemy;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import model.enemies.Enemy;
import model.player.Player;

import java.util.LinkedList;
import java.util.List;

public class EnemyBoxController extends VBox {

    private List<CheckBox> checkboxes;

    @FXML
    VBox EnemyBox;


    public EnemyBoxController() {
        this.checkboxes = new LinkedList<>();
    }

    @FXML
    public void initialize() {
        this.checkboxes = new LinkedList<>();
    }


    public List<CheckBox> getCheckboxes() {
        return checkboxes;
    }

    public void setCheckboxes(List<CheckBox> checkboxes) {
        this.checkboxes = checkboxes;
    }

    public void addToCheckboxes(Player enemy) {
        this.checkboxes.add(new CheckBox(enemy.getName()));
    }

    public void updateCheckboxes(List<Enemy> list) {
        this.checkboxes = new LinkedList<>();

        for (Enemy e : list) {
            CheckBox c = new CheckBox(e.getName());
            c.setSelected(false);
            c.selectedProperty().addListener((observable, oldValue, newValue) -> {
                deselectOtherBoxes(c);
                c.setSelected(newValue);
            });

            this.checkboxes.add(c);
        }
        EnemyBox.getChildren().clear();
        EnemyBox.getChildren().addAll(checkboxes);

    }

    private void deselectOtherBoxes(CheckBox checky){
        for(CheckBox c : this.getCheckboxes()){
            if(!c.equals(checky)){
                c.setSelected(false);
            }
        }
    }


    private boolean isCheckBoxSelectable(CheckBox checky){

        for(CheckBox c : this.getCheckboxes()){
            if(!c.equals(checky)) {
                if (c.isSelected()) {
                    return false;
                }
            }
        }

        return true;
    }

}
