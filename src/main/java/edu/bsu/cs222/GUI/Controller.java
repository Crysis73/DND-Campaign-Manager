package edu.bsu.cs222.GUI;

import edu.bsu.cs222.Campaign;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;

@SuppressWarnings("WeakerAccess")
public class Controller extends Application {
    @FXML
    private TextField inputCampaignText;
    @FXML
    private TabPane TabPane;
    @FXML
    private Tab CharacterCreatorTab;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        String fxmlLocation = "/Users/rebeccaauger/IdeaProjects/Final-Project-Team-Bravo/src/main/resources/GUIFile.fxml";
        FileInputStream fxmlStream = new FileInputStream(fxmlLocation);
        Pane root = loader.load(fxmlStream);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("D&D Game Master Thingy 3000");
        stage.show();
    }

    public void onCreateNewCampaign(javafx.event.ActionEvent actionEvent) {
        Campaign campaign = new Campaign();
        String campaignName = inputCampaignText.getText();
        campaign.setCampaignName(campaignName);
        TabPane.getSelectionModel().select(CharacterCreatorTab);
    }

}
