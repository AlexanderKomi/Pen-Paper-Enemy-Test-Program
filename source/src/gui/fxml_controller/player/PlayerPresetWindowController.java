package gui.fxml_controller.player;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import model.player.Player;

import java.util.List;

public class PlayerPresetWindowController {

    //-------------------------------- FXML MEMBERS ---------------------------------

    @FXML
    Button selectButton, cancelButton;

    @FXML
    VBox PlayerBox;

    @FXML
    gui.fxml_controller.player.PlayerBoxController PlayerBoxController;

    @FXML
    TextArea descriptionField;

    //---------------------------------- MEMBERS -------------------------------------


    //--------------------------------- FXML METHODS -----------------------------------

    @FXML
    public void playerNameSelected() {
    }

    @FXML
    public void selectButtonPressed() {
    }

    @FXML
    public void cancelButtonPressed() {
    }

    //--------------------------------- PRIVATE METHODS --------------------------------


    public void addToPresetsCheckBoxes(Player p) {

        this.PlayerBoxController.addToCheckboxes(p);
    }

    public void updateCheckboxes(List<Player> player) {

        if (this.PlayerBox == null) {
            System.out.println("vBox is null !");
            return;
        }

        this.PlayerBoxController.updateCheckboxes(player);

    }


    //--------------------------------- GETTER AND SETTER -------------------------------


}
