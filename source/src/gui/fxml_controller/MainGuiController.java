package gui.fxml_controller;

import gui.fxml_controller.enemy.AddEnemyController;
import gui.fxml_controller.player.AddPlayerController;
import io.IOController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.battle.Battle;

import java.io.IOException;

public class MainGuiController {

    //---------------------------------------- FXML MEMBERS ----------------------------------------


    @FXML Button addPlayerButton, playerPresetsButton, startButton, addEnemyButton, enemyPresetsButton;

    @FXML TextField iterationsField;

    @FXML TextArea playerListField, enemyListField, summaryField;

    //FXML Controller
    @FXML private AddPlayerController playerCon;
    @FXML private AddEnemyController enemyCon;

    //------------------------------------------ MEMBERS ------------------------------------------

    private Battle battle; // Contains the simulation

    private Stage addPlayerStage;
    private Stage addEnemyStage;


    //---------------------------------------- FXML METHODS ----------------------------------------

    @FXML
    private void initialize() {
        createStages();
        initialize_TextFields();
    }

    //----------------


    @FXML
    public void startButtonClicked() {

        if (this.playerCon.getPlayerList().isEmpty()) {
            System.out.println("Player list is empty, so simulation has not been started.");
            return;
        }
        if (this.enemyCon.getEnemyList().isEmpty()) {
            System.out.println("Enemy list is empty, so simulation has not been started.");
            return;
        }

        this.setBattle(new Battle(
                this.playerCon.getPlayerList(),
                this.enemyCon.getEnemyList(),
                Integer.parseInt(this.iterationsField.getText())
        ));

        //System.out.println("Starting Battle : " + this.getBattle());
        this.getBattle().run();
        this.summaryField.setText(this.getBattle().getSummary());
    }

    @FXML
    public void addPlayerButtonClicked() {
        this.addPlayerStage.show();
    }

    @FXML
    public void addEnemyButtonClicked() {
        this.addEnemyStage.show();
    }


    //---------------------------------------- PRIVATE METHODS -----------------------------------------


    // --------------------------------------- INITIALIZE AND CREATE ----------------------------------

    private void createStages() {
        createPlayerStage();
        createEnemyStage();
    }

    private void createPlayerStage() {
        FXMLLoader loader = new FXMLLoader(AddPlayerController.class.getResource("../../fxml/player/addPlayer.fxml"));

        try {
            Parent root = loader.load();
            playerCon = (AddPlayerController) loader.getController();
            Scene s = new Scene(root);
            addPlayerStage = new Stage();
            addPlayerStage.setScene(s);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        addPlayerStage.setTitle("Create a new Player");


        addPlayerStage.setOnHiding((WindowEvent event) -> {
                    Platform.runLater(() -> {
                                playerListField.setText(
                                        playerCon.playerListAsString()
                                );
                            }
                    );
                }
        );


    }

    private void createEnemyStage() {
        FXMLLoader loader = new FXMLLoader(AddEnemyController.class.getResource("../../fxml/enemy/addEnemy.fxml"));

        try {
            Parent root = loader.load();
            enemyCon = loader.getController();
            Scene s = new Scene(root);
            addEnemyStage = new Stage();
            addEnemyStage.setScene(s);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        addEnemyStage.setTitle("Create a new Enemy");

        addEnemyStage.setOnHiding((WindowEvent event) -> {
                    Platform.runLater(() -> {
                                enemyListField.setText(
                                        enemyCon.enemyListAsString()
                                );
                            }
                    );
                }
        );
    }

    private void initialize_TextFields() {
        this.setFieldToOnlyNumbers(this.iterationsField);
        this.iterationsField.setText("0");
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

    //---------------------------------------- GETTER AND SETTER ----------------------------------------

    public Battle getBattle() {
        return battle;
    }

    public void setBattle(Battle battle) {
        this.battle = battle;
    }
}
