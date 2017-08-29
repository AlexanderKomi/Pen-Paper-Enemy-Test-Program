package gui.fxml_controller;

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

    @FXML
    Button addPlayerButton, playerPresetsButton,  startButton, addEnemyButton, enemyPresetsButton;
    @FXML
    TextField iterationsField;
    @FXML
    TextArea playerListField,enemyListField,summaryField;

    //------------------------------------------ MEMBERS ------------------------------------------

    private Battle battle;


    //FXML Controller
    public AddPlayerController playerCon;
    public AddEnemyController enemyCon;
    public PresetWindowController presetWindowCon;

    //TODO : Stages are here only used for showing the gui. The Controllers must be mapped to this file, to work.
    private Stage addPlayerStage ; //Pops up, when a Player should be added
    private Stage addEnemyStage ;
    private Stage presetWindow;


    //---------------------------------------- FXML METHODS ----------------------------------------

    @FXML
    public void initialize(){
        createStages();
        initialize_TextFields();
        initialize_TextAreas();
    }

    //----------------

    @FXML
    public void addPlayerButtonClicked(){
        addPlayerStage.show();
    }

    @FXML
    public void playerPresetsButtonClicked(){
        presetWindow.show();
    }

    @FXML
    public void startButtonClicked(){

        if(this.playerCon.getPlayerList().isEmpty()){
            System.out.println("Player list is empty, so simulation has not been started.");
            return;
        }
        if(this.enemyCon.getEnemyList().isEmpty()){
            System.out.println("Enemy list is empty, so simulation has not been started.");
            return;
        }

        this.setBattle(new Battle(
                this.playerCon.getPlayerList(),
                this.enemyCon.getEnemyList(),
                Integer.parseInt(this.iterationsField.getText())
        ));

        System.out.println("Starting Battle : " + this.getBattle());
        this.getBattle().run();
    }

    @FXML
    public void iterationsFieldChanged(){
        this.iterationsField.getText();
    }

    @FXML
    public void addEnemyButtonClicked(){
        this.addEnemyStage.show();
    }

    @FXML
    public void enemyPresetsButtonClicked(){}


    //---------------------------------------- PRIVATE METHODS -----------------------------------------

    private void createStages() {
        createPlayerStage();
        createEnemyStage();
        createPresetWindow();
    }

    private void createPlayerStage(){
        FXMLLoader loader = new FXMLLoader(AddPlayerController.class.getResource("../fxml/addPlayer.fxml"));

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
                                System.out.println("Should now update.");
                            }
                    );
                }
        );


    }

    private void createEnemyStage(){
        FXMLLoader loader = new FXMLLoader(AddEnemyController.class.getResource("../fxml/addEnemy.fxml"));

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
                                System.out.println("Should now update.");
                            }
                    );
                }
        );
    }

    private void createPresetWindow() {
        FXMLLoader loader = new FXMLLoader(PresetWindowController.class.getResource("../fxml/PresetWindow.fxml"));

        try {
            Parent root = loader.load();
            presetWindowCon = loader.getController();
            Scene s = new Scene(root);
            presetWindow = new Stage();
            presetWindow.setScene(s);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        presetWindow.setTitle("Choose a preset");

        /*
        stage.setOnHiding((WindowEvent event) -> {
            Platform.runLater(() -> {
                System.out.println("Player stage is only hiding, not exiting . ");
                stage.hide();
            });
        });
        */

    }

    private void initialize_TextFields(){
        this.setFieldToOnlyNumbers(this.iterationsField);
        this.iterationsField.setText("0");
    }

    private void initialize_TextAreas() {
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


    //---------------------------------------- GETTER AND SETTER ----------------------------------------


    public Battle getBattle() {
        return battle;
    }

    public void setBattle(Battle battle) {
        this.battle = battle;
    }
}
