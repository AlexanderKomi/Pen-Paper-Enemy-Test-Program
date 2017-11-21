package gui.fxml_controller.player;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import model.units.Player;

import java.util.LinkedList;
import java.util.List;

public class RemovePlayerController {

    @FXML
    Button removeButton, cancelButton;
    @FXML
    VBox PlayerBox;
    private String removedPlayer = "";
    private List<CheckBox> checkboxes;

    @FXML
    public void initialize() {
        this.checkboxes = new LinkedList<>();
    }


    @FXML
    public void removeButtonPressed() {
        this.removedPlayer = this.getSelected();
    }

    @FXML
    public void cancelButtonPressed() {
        cancelButton.getScene().getWindow().hide();
    }

    public void addToCheckboxes(Player player) {
        if (!this.checkboxes.contains(new CheckBox(player.getName()))) {
            CheckBox c = new CheckBox(player.getName());
            c.setSelected(false);
            c.selectedProperty().addListener((observable, oldValue, newValue) -> {
                deselectOtherBoxes(c);
                c.setSelected(newValue);
            });
            this.checkboxes.add(c);
            PlayerBox.getChildren().add(c);
        } else {
            System.out.println("PlayerBoxController : addToCheckbox : Already contains this player.");
        }
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

    //--------------------------------- PRIVATE METHODS --------------------------------

    private void deselectOtherBoxes(CheckBox checky) {
        for (CheckBox c : this.getCheckboxes()) {
            if (!c.equals(checky)) {
                c.setSelected(false);
            }
        }
    }

    private boolean isCheckBoxSelectable(CheckBox checky) {

        for (CheckBox c : this.getCheckboxes()) {
            if (!c.equals(checky)) {
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
        for (CheckBox c : this.checkboxes) {
            if (c.isSelected()) {
                return c.getText();
            }
        }
        return null;
    }

    public String getRemovedPlayer() {
        return removedPlayer;
    }
}
