package gui.fxml_controller;

import gui.fxml_controller.enemy.AddEnemyController;
import gui.fxml_controller.player.AddPlayerController;
import gui.fxml_controller.player.RemovePlayerController;
import gui.util.GuiUtils;
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
import model.Units.Enemy;
import model.Units.Player;
import model.battle.Battle;

import java.io.IOException;
import java.util.LinkedList;

public class MainGuiController {

    //---------------------------------------- FXML MEMBERS ----------------------------------------


    @FXML Button addPlayerButton, removePlayerButton, startButton, addEnemyButton, removeEnemyButton;

    @FXML TextField iterationsField;

    @FXML TextArea playerListField, enemyListField, summaryField;

    //FXML Controller
    @FXML private AddPlayerController playerCon;
    @FXML private AddEnemyController enemyCon;
    @FXML private RemovePlayerController removePlayerController;


    //------------------------------------------ MEMBERS ------------------------------------------

    private Battle battle; // Contains the simulation

    private Stage addPlayerStage;
    private Stage removePlayerStage;
    private Stage addEnemyStage;
    private Stage removeEnemyStage;


    //---------------------------------------- FXML METHODS ----------------------------------------

    @FXML
    private void initialize() {
        createStages();
        GuiUtils.setFieldToOnlyNumbers( this.iterationsField);
        this.iterationsField.setText("1");
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

        LinkedList<Player> player = new LinkedList<>();
        for(Player p : this.playerCon.getPlayerList()){
            player.add(new Player(p));
        }
        LinkedList<Enemy> enemies = new LinkedList<Enemy>();
        for(Enemy e : this.enemyCon.getEnemyList()){
            enemies.add(new Enemy(e));
        }

        this.setBattle(new Battle(
                player,
                enemies,
                Integer.parseInt(this.iterationsField.getText())
        ));

        //System.out.println("Starting Battle : " + this.getBattle());
        this.getBattle().run();
        this.summaryField.setText(this.getBattle().getSummary());
        System.out.println(summaryField.getText());

    }

    @FXML
    public void addPlayerButtonClicked() {
        this.addPlayerStage.show();
    }

    @FXML
    public void removePlayerButtonPressed(){
        //this.removePlayerStage.show();
    }

    @FXML
    public void addEnemyButtonClicked() {
        this.addEnemyStage.show();
    }

    @FXML
    public void removeEnemyButtonPressed(){

    }


    //---------------------------------------- PRIVATE METHODS -----------------------------------------


    // --------------------------------------- INITIALIZE AND CREATE ----------------------------------

    private void createStages() {
        createPlayerStage();
        createEnemyStage();
        //createPlayerRemoveStage();
        //createEnemyRemoveStage();
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

    private void createPlayerRemoveStage(){
        FXMLLoader loader = new FXMLLoader(AddPlayerController.class.getResource("../../fxml/player/RemovePlayer.fxml"));

        try {
            Parent root = loader.load();
            removePlayerController = loader.getController();
            Scene s = new Scene(root);
            removePlayerStage = new Stage();
            removePlayerStage.setScene(s);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        removePlayerStage.setTitle("Remove a Player");


        removePlayerStage.setOnHiding((WindowEvent event) -> Platform.runLater(() -> playerListField.setText(
                "Remove Player does not work currently."
        )));
    }

    private void createEnemyRemoveStage(){
        FXMLLoader loader = new FXMLLoader(AddEnemyController.class.getResource("../../fxml/enemy/RemoveEnemy.fxml"));

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
        addEnemyStage.setTitle("Remove an Enemy");

        addEnemyStage.setOnHiding((WindowEvent event) -> Platform.runLater(() -> enemyListField.setText(
                enemyCon.enemyListAsString()
        )));
    }


    //---------------------------------------- GETTER AND SETTER ----------------------------------------

    public Battle getBattle() {
        return battle;
    }

    public void setBattle(Battle battle) {
        this.battle = battle;
    }

}
