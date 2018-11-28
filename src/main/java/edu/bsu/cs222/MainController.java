package edu.bsu.cs222;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;


import java.io.IOException;
import java.util.Date;

public class MainController{
    @FXML private CampaignOpenTabController campaignOpenTabController;
    @FXML private CampaignViewTabController campaignViewTabController;
    @FXML private NewCharacterTabController newCharacterTabController;
    @FXML private Tab CampaignViewTab,CharacterCreatorTab;
    @FXML private TabPane mainTabPane;
    private Campaign campaign;


    @FXML private void initialize(){
        campaignOpenTabController.injectMainController(this);
        campaignViewTabController.injectMainController(this);
        newCharacterTabController.injectMainController(this);
    }


    void setCharacterCreatorTab(){
        mainTabPane.getSelectionModel().select(CharacterCreatorTab);
        CampaignViewTab.disableProperty().setValue(false);
        campaignViewTabController.initializeCampaignViewTab();
        CharacterCreatorTab.disableProperty().setValue(false);
        newCharacterTabController.initializeNewCharacterTab();
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
        try {
            anchorPane = FXMLLoader.load(getClass().getResource("/CharacterTab.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        tab.setContent(anchorPane);
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

    public void loadOldCampaign() {
        refreshProgram();
        Date date = new Date();
        JsonLoader loader = new JsonLoader();
        Campaign campaign = loader.fromJsontoCampaign("My Campaign.json");
        this.campaign = campaign;
        campaign.fromFileToLog();
        campaignViewTabController.addEntryToLog(campaign.getLog() + "\n"+date.toString() +": " +this.campaign.getCampaignName().replace(".json","")+" loaded.");
        setCharacterCreatorTab();
        for(int j=0;j<campaign.getCharacters().size();j++){
            if(!campaign.getCharacters().get(j).getName().equals(mainTabPane.getTabs().get(j).getText())) {
                createNewCharacterTab(campaign.getCharacters().get(j));
            }
        }
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


}
