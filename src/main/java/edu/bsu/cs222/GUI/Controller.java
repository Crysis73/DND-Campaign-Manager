package edu.bsu.cs222.GUI;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.IOException;

public class Controller extends Application {
    @FXML
    private Stage window;
    @FXML
    private Scene scene;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        window = stage;
        FXMLLoader loader = new FXMLLoader();
        String fxmlLocation = "F:\\Java stuff\\- CS222\\Final-Project-Team-Bravo\\src\\main\\resources\\GUIFile.fxml";
        FileInputStream fxmlStream = new FileInputStream(fxmlLocation);
        Pane root = loader.load(fxmlStream);
        scene = new Scene(root);
        window.setScene(scene);
        window.setTitle("D&D Game Master Thingy 3000");
        window.show();
    }
}
