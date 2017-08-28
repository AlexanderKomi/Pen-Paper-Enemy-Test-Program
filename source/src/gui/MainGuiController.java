package gui;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.enemies.Enemy;
import model.player.Player;

import javax.xml.soap.Text;
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

    private int iterations_done;
    private List<? extends Player> playerList;
    private List<? extends Enemy> enemyList;

    //Stages

    private Stage addPlayerStage = createPlayerStage();
    private Stage addEnemyStage = createEnemyStage();
    //---------------------------------------- FXML METHODS ----------------------------------------

    @FXML
    public void addPlayerButtonClicked(){
        this.addPlayerStage.show();
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

    private Stage createPlayerStage(){
        Stage stage = new Stage();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("addPlayer.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
        }
        catch(Exception e){
            e.printStackTrace();
        }

        stage.setTitle("Create a new Player");

        /*
        stage.setOnHiding((WindowEvent event) -> {
            Platform.runLater(() -> {
                System.out.println("Player stage is only hiding, not exiting . ");
                stage.hide();
            });
        });
        */

        return stage;
    }

    private Stage createEnemyStage(){
        Stage stage = new Stage();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("addEnemy.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
        }
        catch(Exception e){
            e.printStackTrace();
        }

        stage.setTitle("Create a new Enemy");

        /*
        stage.setOnHiding((WindowEvent event) -> {
            Platform.runLater(() -> {
                System.out.println("Enemy stage is only hiding, not exiting . ");
                stage.hide();
            });
        });
        */

        return stage;
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

    public void setAddPlayerStage(Stage addPlayerStage) {
        this.addPlayerStage = addPlayerStage;
    }

    public Stage getAddEnemyStage() {
        return addEnemyStage;
    }

    public void setAddEnemyStage(Stage addEnemyStage) {
        this.addEnemyStage = addEnemyStage;
    }
}
