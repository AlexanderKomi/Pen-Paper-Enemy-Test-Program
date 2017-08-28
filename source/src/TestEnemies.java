import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TestEnemies extends Application{

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("gui/fxml/mainGui.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Pen and Paper : Enemy Tester");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
