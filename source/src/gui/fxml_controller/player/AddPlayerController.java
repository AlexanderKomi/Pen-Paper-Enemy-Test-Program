package gui.fxml_controller.player;

import gui.util.GuiUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.units.Player;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


public class AddPlayerController {

    //------------------------------------ FXML MEMBERS -------------------------------------

    @FXML TextField nameField, lifePointsField, damageField, attackChanceField, defenseField;

    @FXML TextArea descriptionTextField;

    @FXML Button createPlayerButton, presetButton, cancelButton;

    //FXML Controller
    @FXML private PlayerPresetWindowController presetWindowCon;

    //------------------------------------------ MEMBERS ------------------------------------------

    private Stage presetWindow;

    private static List<Player> playerList = new LinkedList<>();  // Contains all Players for the simulation

    //----------------------------------- FXML METHODS -------------------------------------

    @FXML
    public void initialize() {
        createPresetWindow();
        GuiUtils.initialize_NumberFields(
                lifePointsField,
                damageField,
                attackChanceField,
                defenseField
        );
    }

    @FXML
    public void presetButtonPressed() {
        presetWindow.show();
        presetWindowCon.updateCheckboxes(this.getPlayerList());
    }

    @FXML
    public void cancelButtonPressed() {
        cancelButton.getScene().getWindow().hide();
    }

    @FXML
    public Player createPlayerButtonPressed() {
        Player p = new Player();

        if(this.nameField.getText().equals("")){
            return null;
        }

        if(this.defenseField.getText().equals("")) {

            try {
                p = new Player(
                        nameField.getText(),
                        Integer.parseInt(lifePointsField.getText()),
                        Integer.parseInt(damageField.getText()),
                        Integer.parseInt(attackChanceField.getText()),
                        Integer.parseInt(defenseField.getText())
                );
            } catch (Exception e) {
                System.out.println("Problem to create new Player.");
                e.printStackTrace();
            }
        }
        else{
            try {
                p = new Player(
                        nameField.getText(),
                        Integer.parseInt(lifePointsField.getText()),
                        Integer.parseInt(damageField.getText()),
                        Integer.parseInt(attackChanceField.getText()),
                        Integer.parseInt(defenseField.getText()),
                        descriptionTextField.getText()
                );
            } catch (Exception e) {
                System.out.println("Problem to create new Player.");
                e.printStackTrace();
            }
        }


        if (playerList.contains(p)) {
            return null;
        }


        try {
            playerList.add(p);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        System.out.println("new Player : \n" + p + "\n");

        return p;
    }

    //--------------------------- PRIVATE METHODS --------------------------

    private void createPresetWindow() {
        FXMLLoader loader = new FXMLLoader(PlayerPresetWindowController.class.getResource("../../fxml/player/PlayerPresetWindow.fxml"));

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

    //----------------------------------GETTER AND SETTER ----------------------------------


    public String playerListAsString() {
        StringBuilder s = new StringBuilder();

        for (Player p : playerList) {
            s.append(p.toString()).append("\n");
        }

        return s.toString();
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        AddPlayerController.playerList = playerList;
    }

}
