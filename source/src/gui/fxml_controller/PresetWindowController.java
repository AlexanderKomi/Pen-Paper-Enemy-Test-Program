package gui.fxml_controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import model.player.Player;

public class PresetWindowController {

    //-------------------------------- FXML MEMBERS ---------------------------------

    @FXML
    Button selectButton, cancelButton;

    @FXML
    VBox PlayerBox;

    @FXML
    TextArea descriptionField;

    @FXML
    CheckBox playerNameCheckBox;

    //---------------------------------- MEMBERS -------------------------------------


    //--------------------------------- FXML METHODS -----------------------------------

    @FXML
    public void playerNameSelected(){}

    @FXML
    public void selectButtonPressed(){}

    @FXML
    public void cancelButtonPressed(){}

    //--------------------------------- PRIVATE METHODS --------------------------------

    public void addToPresetsCheckBoxes(Player p){
        VBox v = this.PlayerBox;

        CheckBox c = new CheckBox(p.getName());

        this.PlayerBox.getChildren().addAll(c);
    }

    //--------------------------------- GETTER AND SETTER -------------------------------


}
