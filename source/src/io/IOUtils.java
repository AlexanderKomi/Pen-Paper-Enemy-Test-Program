package io;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

final class IOUtils {

    private IOUtils() {
    }

    static File loadDialog() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("csv", "*.csv"));
        return fileChooser.showOpenDialog(new Stage());
    }

    static File saveDialog() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("csv", "*.csv"));
        return fileChooser.showSaveDialog(new Stage());
    }
}