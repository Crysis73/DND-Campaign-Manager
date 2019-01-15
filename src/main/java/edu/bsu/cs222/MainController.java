package edu.bsu.cs222;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.File;
import java.io.IOException;
import java.util.Date;

public class MainController{
    @FXML private CampaignOpenTabController campaignOpenTabController;
    @FXML private CampaignViewTabController campaignViewTabController;
    @FXML private NewCharacterTabController newCharacterTabController;
    @FXML private Tab CampaignViewTab,CharacterCreatorTab;
    @FXML private TabPane mainTabPane;
    @FXML private AnchorPane rootPane;
    private boolean isDarkTheme;
    private Campaign campaign;

    TabPane getMainTabPane(){
        return this.mainTabPane;
    }

    @FXML private void initialize(){
        campaignOpenTabController.injectMainController(this);
        campaignViewTabController.injectMainController(this);
        newCharacterTabController.injectMainController(this);
    }

    private void toDarkTheme(){
        rootPane.getStylesheets().add(getClass().getResource("/Stylesheets/DarkTheme.css").toExternalForm());
        rootPane.getStyleClass().add("DarkTheme");
        isDarkTheme = true;
    }

    public void toDarkTheme(ActionEvent actionEvent) {
        toDarkTheme();
        campaignOpenTabController.toDarkTheme();
        campaignViewTabController.toDarkTheme();
        newCharacterTabController.toDarkTheme();
    }


    void setCharacterCreatorTab(){
        mainTabPane.getSelectionModel().select(CharacterCreatorTab);
        CampaignViewTab.disableProperty().setValue(false);
        campaignViewTabController.initializeCampaignViewTab();
        CharacterCreatorTab.disableProperty().setValue(false);
        newCharacterTabController.initializeNewCharacterTab(isDarkTheme);
    }

    private void setLog(){
        campaignViewTabController.addEntryToLog(new Date().toString() +": \""+campaign.getCampaignName() + "\" was created.");
    }

    void addEntryToLog(String entry){
        campaignViewTabController.addEntryToLog(new Date().toString() + ": "+entry);
    }

    public void setCampaign(Campaign campaign){
        this.campaign = campaign;
        setLog();
    }

    public Campaign getCampaign(){
        return this.campaign;
    }

    void createNewCharacterTab(Character character){
        Tab tab = new Tab(character.getName());
        CharacterTabController characterTabController = new CharacterTabController(character);
        AnchorPane anchorPane = new AnchorPane();
        FXMLLoader loader = new FXMLLoader();
        loader.setController(characterTabController);
        loader.setLocation(getClass().getResource("/CharacterTab.fxml"));
        try {
            anchorPane = loader.load();
            characterTabController.initialize();
            if(isDarkTheme){
                characterTabController.toDarkTheme();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        tab.setContent(anchorPane);
        tab.setOnSelectionChanged(event -> {
            characterTabController.initialize();
            if(isDarkTheme){
                characterTabController.toDarkTheme();
            }
        });
        mainTabPane.getTabs().add(tab);
        campaignViewTabController.displayCharacters();
        campaignViewTabController.initializeCharacterChoices();
    }

    void refreshProgram(){
        campaignViewTabController.refreshCampaignView();
        newCharacterTabController.refreshNewCharacterTab();
        for(int i=0;i<mainTabPane.getTabs().size();i++){
            if(!(mainTabPane.getTabs().get(i).getText().equals("Campaign") || mainTabPane.getTabs().get(i).getText().equals("Campaign View") || mainTabPane.getTabs().get(i).getText().equals("Character Creator")) ) {
                mainTabPane.getTabs().remove(i,mainTabPane.getTabs().size());
            }
        }
    }

    void removeCampaignTabFromTabPane(){
        for(int i=0;i<mainTabPane.getTabs().size();i++){
            if(mainTabPane.getTabs().get(i).getText().equals("Campaign")){
                mainTabPane.getTabs().remove(i);
                break;
            }
        }
    }

    private void loadOldCampaign(String filename) {
        refreshProgram();
        Date date = new Date();
        JsonLoader loader = new JsonLoader();
        Campaign campaign = loader.fromJsontoCampaign(filename);
        this.campaign = campaign;
        try {
            campaign.fromFileToLog();
        } catch(NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.ERROR, "We couldn't seem to find a log associated with your campaign, one has been created for you.");
            alert.setHeaderText("No log found");
            if(isDarkTheme) {
                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.getStylesheets().add(getClass().getResource("/Stylesheets/DarkTheme.css").toExternalForm());
                dialogPane.getStyleClass().add("DarkTheme");
            }
            alert.showAndWait();
        }
        campaignViewTabController.addEntryToLog(date.toString() +": " +this.campaign.getCampaignName().replace(".json","")+" loaded.");
        setCharacterCreatorTab();
        for(int j=0;j<campaign.getCharacters().size();j++){
            if(!campaign.getCharacters().get(j).getName().equals(mainTabPane.getTabs().get(j).getText())) {
                createNewCharacterTab(campaign.getCharacters().get(j));
            }
        }
        removeCampaignTabFromTabPane();
        CharacterCreatorTab.setDisable(false);
        CampaignViewTab.setDisable(false);
        mainTabPane.getSelectionModel().select(CampaignViewTab);

    }

    public void saveCampaign(javafx.event.ActionEvent actionEvent) {
        campaignViewTabController.addEntryToLog(new Date().toString() + ": " + campaign.getCampaignName() + " was saved.");
        campaign.writeLogToFile();
        JsonWriter writer = new JsonWriter();
        writer.writeCampaignJson(campaign);
    }

    public void refreshCampaignViewTab(Event event) {
        if(campaignViewTabController.getActiveCharacter() != null) {
            campaignViewTabController.refreshProgressBars();
        }
    }

    public void openCampaign(){
        FileChooser fileChooser = new FileChooser();
        AnchorPane anchorPane = new AnchorPane();
        Scene scene = new Scene(anchorPane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Choose campaign file");
        File file = fileChooser.showOpenDialog(stage);
        if(file != null) {
            if (!file.getName().trim().isEmpty()) {
                if(file.getName().contains(".json")) {
                    loadOldCampaign(file.getName());
                }else{
                    Alert alert = new Alert(Alert.AlertType.ERROR, "The file you selected doesn't seem to be readable by our program.");
                    if (isDarkTheme) {
                        DialogPane dialogPane = alert.getDialogPane();
                        dialogPane.getStylesheets().add(getClass().getResource("/Stylesheets/DarkTheme.css").toExternalForm());
                        dialogPane.getStyleClass().add("DarkTheme");
                    }
                    alert.setHeaderText("Invalid File");
                    alert.showAndWait();

                }
            }
        }
    }

}
