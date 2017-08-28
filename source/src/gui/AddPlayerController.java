package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class AddPlayerController {

    @FXML
    TextField nameField;

    @FXML
    TextField lifePointsField;

    @FXML
    TextField damageField;

    @FXML
    TextField attackChanceField;

    @FXML
    TextField defenseField;

    @FXML
    TextArea bonusTextField;

    @FXML
    Button createPlayerButton;

    @FXML
    Button replacePlayerButton;

    @FXML
    Button loadPresetButton;

    @FXML
    Button savePresetButton;

    @FXML
    Button cancelButton;


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
