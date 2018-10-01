package edu.bsu.cs222;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class JavaFXLearningTest extends Application {
    private VBox parent = new VBox();

    @Override
    public void start(Stage primaryStage) {
        Label hello = new Label("Hello world");
        parent.getChildren().add(hello);

        Scene scene = new Scene(parent, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Team Bravo Final Project JavaFX Test");
        primaryStage.show();
    }
}
