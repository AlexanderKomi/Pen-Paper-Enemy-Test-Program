package gui.fxml_controller;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import model.player.Player;

import java.util.LinkedList;
import java.util.List;

public class PlayerBoxController extends VBox {

    private List<CheckBox> checkboxes;

    @FXML
    VBox PlayerBox;


    public PlayerBoxController() {

        this.checkboxes = new LinkedList<>();
    }

    @FXML
    public void initialize() {
        this.checkboxes = new LinkedList<>();
    }


    public List<CheckBox> getCheckboxes() {
        return checkboxes;
    }

    public void setCheckboxes(List<CheckBox> checkboxes) {
        this.checkboxes = checkboxes;
    }

    public void addToCheckboxes(Player player) {
        this.checkboxes.add(new CheckBox(player.getName()));
    }

    public void updateCheckboxes(List<Player> list) {
        this.checkboxes = new LinkedList<>();

        for (Player p : list) {
            this.checkboxes.add(new CheckBox(p.getName()));
        }
        PlayerBox.getChildren().clear();
        PlayerBox.getChildren().addAll(checkboxes);

    }

}
