package gui.fxml_controller.player;

import io.IOController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import model.units.Player;

import java.util.LinkedList;
import java.util.List;

public class PlayerPresetWindowController {

    //-------------------------------- FXML MEMBERS -----------------------------------

    @FXML
    Button selectButton, loadButton, saveButton, cancelButton;

    @FXML
    VBox PlayerBox;

    @FXML
    TextArea descriptionField;

    //FXML Controller
    @FXML
    private PlayerBoxController PlayerBoxController;
    private IOController ioController;
    private List<Player> playerList;

    //---------------------------------- MEMBERS ---------------------------------------


    //--------------------------------- FXML METHODS -----------------------------------

    @FXML
    public void initialize() {
        this.ioController = new IOController();
        this.playerList = new LinkedList<>();
    }

    @FXML
    public void selectButtonPressed() {
        System.out.println("Player : " + this.PlayerBoxController.getSelected() + " has been selected");
    }

    @FXML
    public void loadButtonPressed() {
        Player p = this.ioController.loadPlayer();
        if (!p.equals(new Player())) {
            addToPresetsCheckBoxes(p);
        } else {
            System.out.println("The loaded Player, is a default constructed player, so it will not be added to the selectable options.");
        }

    }

    @FXML
    public void saveButtonPressed() {
        String selectedCheckBox = this.PlayerBoxController.getSelected();
        System.out.println("saveButtonPressed : " + selectedCheckBox);

        for (Player p : this.getPlayerList()) {

            if (p.getName().equals(selectedCheckBox)) {

                this.ioController.savePlayer(p);
                break;
            }

        }

    }

    @FXML
    public void cancelButtonPressed() {
        this.cancelButton.getScene().getWindow().hide();
    }

    //--------------------------------- PRIVATE METHODS --------------------------------


    public void addToPresetsCheckBoxes(Player p) {
        this.PlayerBoxController.addToCheckboxes(p);
        this.playerList.add(p);
    }

    public void updateCheckboxes(List<Player> player) {

        if (this.PlayerBox == null) {
            System.out.println("vBox is null !");
            return;
        }
        this.playerList = player;
        this.PlayerBoxController.updateCheckboxes(player);
    }

    //--------------------------------- GETTER AND SETTER -------------------------------

    public List<Player> getPlayerList() {
        return playerList;
    }


}
