package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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

    //---------------------------------------- FXML METHODS ----------------------------------------

    @FXML
    public void addPlayerButtonClicked(){}

    @FXML
    public void playerPresetsButtonClicked(){}

    @FXML
    public void removePlayerButtonClicked(){}

    @FXML
    public void startButtonClicked(){}

    @FXML
    public void iterationsFieldChanged(){}

    @FXML
    public void addEnemyButtonClicked(){}

    @FXML
    public void enemyPresetsButtonClicked(){}

    @FXML
    public void removeEnemyButtonClicked(){}


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
