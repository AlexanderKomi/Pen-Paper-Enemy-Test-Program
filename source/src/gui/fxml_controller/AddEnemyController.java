package gui.fxml_controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.enemies.Enemy;

import java.util.LinkedList;
import java.util.List;


public class AddEnemyController{

    @FXML
    TextField nameField, lifePointsField, damageField, attackChanceField, defenseField, armorField;

    @FXML
    TextArea bonusTextField;

    @FXML
    Button createEnemyButton, replaceEnemyButton, loadPresetButton, savePresetButton, cancelButton;



    private List<Enemy> enemyList;    // Contains all Enemies for the simulation

    @FXML
    public void initialize(){
        initialize_TextFields();
        this.enemyList = new LinkedList<>();
    }

    @FXML
    public Enemy createEnemyButtonPressed(){
        Enemy enemy = new Enemy();
        try{
            enemy = new Enemy(
                    this.nameField.getText(),
                    Integer.parseInt(lifePointsField.getText()),
                    Integer.parseInt(damageField.getText()),
                    Integer.parseInt(attackChanceField.getText()),
                    Integer.parseInt(defenseField.getText()),
                    Integer.parseInt(armorField.getText()),
                    this.bonusTextField.getText()
            );
        }catch(Exception e){
            System.out.println("Something went wrong, when creating the Enemy.");
            e.printStackTrace();
        }
        System.out.println("new Enemy : \n"+enemy+"\n");
        this.getEnemyList().add(enemy);
        return enemy;
    }

    @FXML
    public void replaceEnemyButtonPressed(){}

    @FXML
    public void loadPresetButtonPressed(){}

    @FXML
    public void savePresetButtonPressed(){}

    @FXML
    public void cancelButtonPressed(){}

    @FXML
    public void armorFieldChanged(){}

    //--------------------------- PRIVATE METHODS --------------------------

    private void initialize_TextFields(){
        setFieldToOnlyNumbers(lifePointsField);
        setFieldToOnlyNumbers(damageField);
        setFieldToOnlyNumbers(attackChanceField);
        setFieldToOnlyNumbers(defenseField);
        setFieldToOnlyNumbers(armorField);
        lifePointsField.setText("0");
        damageField.setText("0");
        attackChanceField.setText("0");
        defenseField.setText("0");
        armorField.setText("0");
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

    public String enemyListAsString(){
        StringBuilder s = new StringBuilder();

        for(Enemy e : this.enemyList){
            s.append(e.toString()).append("\n");
        }

        return s.toString();
    }


    public List<Enemy> getEnemyList() {
        return enemyList;
    }

    public void setEnemyList(List<Enemy> enemyList) {
        this.enemyList = enemyList;
    }

}
