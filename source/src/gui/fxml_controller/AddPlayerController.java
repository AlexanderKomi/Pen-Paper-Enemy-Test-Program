package gui.fxml_controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import model.player.Player;


public class AddPlayerController extends BorderPane{

    @FXML
    TextField nameField, lifePointsField, damageField, attackChanceField, defenseField;

    @FXML
    TextArea bonusTextField;

    @FXML
    Button createPlayerButton, replacePlayerButton, loadPresetButton, savePresetButton, cancelButton;

    @FXML
    public void initialize(){
        initialize_TextFields();
    }

    @FXML
    public void savePresetButtonPressed(){}

    @FXML
    public void loadPresetButtonPressed(){}

    @FXML
    public void replacePlayerButtonPressed(){}

    @FXML
    public void cancelButtonPressed(){}

    @FXML
    public Player createPlayerButtonPressed(){
        Player p = new Player();
        try {
            p = new Player(
                    nameField.getText(),
                    Integer.parseInt(lifePointsField.getText()),
                    Integer.parseInt(damageField.getText()),
                    Integer.parseInt(attackChanceField.getText()),
                    Integer.parseInt(defenseField.getText())
            );
        }catch(Exception e){
            System.out.println("Problem to create new Player.");
            e.printStackTrace();
        }
        System.out.println("new Player : \n"+p+"\n");
        return p;
    }

    //--------------------------- PRIVATE METHODS --------------------------

    private void initialize_TextFields(){
        setFieldToOnlyNumbers(lifePointsField);
        setFieldToOnlyNumbers(damageField);
        setFieldToOnlyNumbers(attackChanceField);
        setFieldToOnlyNumbers(defenseField);
        lifePointsField.setText("0");
        damageField.setText("0");
        attackChanceField.setText("0");
        defenseField.setText("0");
    }

    private void setFieldToOnlyNumbers(TextField t){
        t.textProperty().addListener((observable, oldValue, newValue) -> {

            if (!newValue.matches("\\d*")) {
                t.setText(newValue.replaceAll("[^\\d]", ""));
            }
            if(t.getText().equals("")){
                t.setText("0");
            }

        });
    }

}
