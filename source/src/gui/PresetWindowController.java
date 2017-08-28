package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.player.Player;

public class PresetWindowController {

    //-------------------------------- FXML MEMBERS ---------------------------------

    @FXML
    Button selectButton, cancelButton;

    @FXML
    VBox playerBox;

    @FXML
    TextArea textArea;

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

    public void addToPresetsCheckBoxes(Player p ){
        VBox v = this.playerBox;

        CheckBox c = new CheckBox(p.getName());

        this.playerBox.getChildren().addAll(c);

    }

    //--------------------------------- GETTER AND SETTER -------------------------------


}
