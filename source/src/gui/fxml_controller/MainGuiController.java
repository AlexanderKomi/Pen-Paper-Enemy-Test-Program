package gui.fxml_controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.enemies.Enemy;
import model.player.Player;

import java.io.IOException;
import java.util.List;

public class MainGuiController {

    //---------------------------------------- FXML MEMBERS ----------------------------------------

    @FXML
    Button addPlayerButton;
    @FXML
    Button playerPresetsButton;
    @FXML
    Button removePlayerButton;
    @FXML
    Button startButton;
    @FXML
    Button addEnemyButton;
    @FXML
    Button enemyPresetsButton;
    @FXML
    Button removeEnemyButton;
    @FXML
    TextField iterationsField;

    //------------------------------------------ MEMBERS ------------------------------------------

    private int iterations_done;                // Counts how many iterations are done
    private List<? extends Player> playerList;  // Contains all Players for the simulation
    private List<? extends Enemy> enemyList;    // Contains all Enemies for the simulation

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
        createPlayerStage();
        createEnemyStage();
        createPresetWindow();
    }

    //----------------

    @FXML
    public void addPlayerButtonClicked(){
        addPlayerStage.show();
    }

    @FXML
    public void playerPresetsButtonClicked(){}

    @FXML
    public void removePlayerButtonClicked(){}

    @FXML
    public void startButtonClicked(){}

    @FXML
    public void iterationsFieldChanged(){}

    @FXML
    public void addEnemyButtonClicked(){
        this.addEnemyStage.show();
    }

    @FXML
    public void enemyPresetsButtonClicked(){}

    @FXML
    public void removeEnemyButtonClicked(){}


    //---------------------------------------- PRIVATE METHODS -----------------------------------------


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

        /*
        stage.setOnHiding((WindowEvent event) -> {
            Platform.runLater(() -> {
                System.out.println("Player stage is only hiding, not exiting . ");
                stage.hide();
            });
        });
        */

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

        /*
        stage.setOnHiding((WindowEvent event) -> {
            Platform.runLater(() -> {
                System.out.println("Player stage is only hiding, not exiting . ");
                stage.hide();
            });
        });
        */
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


    //---------------------------------------- GETTER AND SETTER ----------------------------------------


    public List<? extends Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

    public List<? extends Enemy> getEnemyList() {
        return enemyList;
    }

    public void setEnemyList(List<Enemy> enemyList) {
        this.enemyList = enemyList;
    }

    public int getIterations_done() {
        return iterations_done;
    }

    public void setIterations_done(int iterations_done) {
        this.iterations_done = iterations_done;
    }

}
