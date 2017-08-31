package gui.fxml_controller.player;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import model.player.Player;

import java.util.LinkedList;
import java.util.List;

public class PlayerBoxController extends VBox {

    private List<CheckBox> checkboxes;

    @FXML
    VBox PlayerBox;


    public PlayerBoxController() {

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

    public void addToCheckboxes(Player player) {
        this.checkboxes.add(new CheckBox(player.getName()));
    }

    public void updateCheckboxes(List<Player> list) {
        this.checkboxes = new LinkedList<>();

        for (Player p : list) {
            CheckBox c = new CheckBox(p.getName());
            c.setSelected(false);
            c.selectedProperty().addListener((observable, oldValue, newValue) -> {
                deselectOtherBoxes(c);
                c.setSelected(newValue);
            });

            this.checkboxes.add(c);
        }
        PlayerBox.getChildren().clear();
        PlayerBox.getChildren().addAll(checkboxes);

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
