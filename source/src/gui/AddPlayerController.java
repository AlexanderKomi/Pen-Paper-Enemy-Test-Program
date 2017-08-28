package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class AddPlayerController{

    @FXML
    TextField nameField, lifePointsField, damageField, attackChanceField, defenseField;

    @FXML
    TextArea bonusTextField;

    @FXML
    Button createPlayerButton, replacePlayerButton, loadPresetButton, savePresetButton, cancelButton;


    @FXML
    public void savePresetButtonPressed(){}

    @FXML
    public void loadPresetButtonPressed(){}

    @FXML
    public void replacePlayerButtonPressed(){}

    @FXML
    public void cancelButtonPressed(){}

    @FXML
    public void createPlayerButtonPressed(){}
}
