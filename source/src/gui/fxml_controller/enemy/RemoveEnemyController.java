package gui.fxml_controller.enemy;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import model.Units.Enemy;

import java.util.LinkedList;
import java.util.List;

public class RemoveEnemyController {

    private String removedEnemy = "";

    @FXML
    Button removeButton, cancelButton;

    @FXML
    VBox EnemyBox;

    private List<CheckBox> checkboxes;

    @FXML
    public void initialize() {
        this.checkboxes = new LinkedList<>();
    }


    @FXML
    public void removeButtonPressed(){
        this.removedEnemy = this.getSelected();
    }

    @FXML
    public void cancelButtonPressed(){
        cancelButton.getScene().getWindow().hide();
    }

    public void addToCheckboxes(Enemy enemy) {
        if(!this.checkboxes.contains(new CheckBox(enemy.getName()))){
            CheckBox c = new CheckBox(enemy.getName());
            c.setSelected(false);
            c.selectedProperty().addListener((observable, oldValue, newValue) -> {
                deselectOtherBoxes(c);
                c.setSelected(newValue);
            });
            this.checkboxes.add(c);
            EnemyBox.getChildren().add( c);
        }
        else{
            System.out.println("EnemyBoxController : addToCheckbox : Already contains this enemy.");
        }
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
        EnemyBox.getChildren().addAll( checkboxes);

    }

    //--------------------------------- PRIVATE METHODS --------------------------------

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

    //--------------------------------- GETTER AND SETTER -------------------------------

    public List<CheckBox> getCheckboxes() {
        return checkboxes;
    }

    public void setCheckboxes(List<CheckBox> checkboxes) {
        this.checkboxes = checkboxes;
    }

    private String getSelected() {
        for(CheckBox c : this.checkboxes){
            if(c.isSelected()){
                return c.getText();
            }
        }
        return null;
    }

    public String getRemovedEnemy() {
        return removedEnemy;
    }
}
