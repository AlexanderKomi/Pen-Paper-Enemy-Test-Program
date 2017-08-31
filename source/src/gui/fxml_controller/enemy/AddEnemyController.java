package gui.fxml_controller.enemy;

import gui.fxml_controller.player.PlayerPresetWindowController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.enemies.Enemy;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


public class AddEnemyController {


    @FXML TextField nameField, lifePointsField, damageField, attackChanceField, defenseField, armorField;

    @FXML TextArea bonusTextField;

    @FXML Button createEnemyButton, presetsButton, cancelButton;



    private Stage presetWindow;
    @FXML private EnemyPresetWindowController presetWindowCon;

    private List<Enemy> enemyList;    // Contains all Enemies for the simulation


    @FXML
    public void initialize() {
        initialize_TextFields();
        this.enemyList = new LinkedList<>();
        createPresetWindow();
    }

    @FXML
    public Enemy createEnemyButtonPressed() {
        Enemy enemy = new Enemy();
        try {
            enemy = new Enemy(
                    this.nameField.getText(),
                    Integer.parseInt(lifePointsField.getText()),
                    Integer.parseInt(damageField.getText()),
                    Integer.parseInt(attackChanceField.getText()),
                    Integer.parseInt(defenseField.getText()),
                    Integer.parseInt(armorField.getText()),
                    this.bonusTextField.getText()
            );
        } catch (Exception e) {
            System.out.println("Something went wrong, when creating the Enemy.");
            e.printStackTrace();
        }
        System.out.println("new Enemy : \n" + enemy + "\n");
        this.getEnemyList().add(enemy);
        return enemy;
    }

    @FXML
    public void presetsButtonPressed() {
        presetWindow.show();
        presetWindowCon.updateCheckboxes(this.getEnemyList());
    }

    @FXML
    public void cancelButtonPressed() {cancelButton.getScene().getWindow().hide();}

    @FXML
    public void armorFieldChanged() {
    }

    //--------------------------- PRIVATE METHODS --------------------------

    private void initialize_TextFields() {
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

    private void setFieldToOnlyNumbers(TextField t) {
        t.textProperty().addListener((observable, oldValue, newValue) -> {

            if (!newValue.matches("\\d*")) {
                t.setText(newValue.replaceAll("[^\\d]", ""));
            }
            if (t.getText().equals("")) {
                t.setText("0");
            }

        });
    }

    private void createPresetWindow() {
        FXMLLoader loader = new FXMLLoader(EnemyPresetWindowController.class.getResource("../../fxml/enemy/EnemyPresetWindow.fxml"));

        try {
            Parent root = loader.load();
            Scene s = new Scene(root);
            this.presetWindowCon = loader.getController();

            this.presetWindow = new Stage();
            this.presetWindow.setScene(s);

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        this.presetWindow.setTitle("Choose a preset");

        /*
        stage.setOnHiding((WindowEvent event) -> {
            Platform.runLater(() -> {
                System.out.println("Player stage is only hiding, not exiting . ");
                stage.hide();
            });
        });
        */

    }

    public String enemyListAsString() {
        StringBuilder s = new StringBuilder();

        for (Enemy e : this.enemyList) {
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
