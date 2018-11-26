package edu.bsu.cs222;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController{
    @FXML private CampaignOpenTabController campaignOpenTabController;
    @FXML private CampaignViewTabController campaignViewTabController;
    @FXML private NewCharacterTabController newCharacterTabController;
    @FXML private Tab CampaignViewTab,CharacterCreatorTab;
    @FXML private TabPane mainTabPane;
    protected Campaign campaign;


    @FXML private void initialize(){
        campaignOpenTabController.injectMainController(this);
        campaignViewTabController.injectMainController(this);
        newCharacterTabController.injectMainController(this);
    }


    public void setCharacterCreatorTab(){
        mainTabPane.getSelectionModel().select(CharacterCreatorTab);
        CampaignViewTab.disableProperty().setValue(false);
        campaignViewTabController.initializeCampaignViewTab();
        CharacterCreatorTab.disableProperty().setValue(false);
        newCharacterTabController.initializeNewCharacterTab();
    }


    public void setCampaign(Campaign campaign){
        this.campaign = campaign;
    }

    public Campaign getCampaign(){
        return this.campaign;
    }


    public void createNewCharacterTab(Character character){
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

    public void refreshProgram(){
        campaignViewTabController.refreshCampaignView();
        newCharacterTabController.refreshNewCharacterTab();
        for(int i=0;i<mainTabPane.getTabs().size();i++){
            if(!(mainTabPane.getTabs().get(i).getText().equals("Campaign") || mainTabPane.getTabs().get(i).getText().equals("Campaign View") || mainTabPane.getTabs().get(i).getText().equals("Character Creator")) ) {
                mainTabPane.getTabs().remove(i,mainTabPane.getTabs().size());
            }
        }
    }

    public void loadOldCampaign() {
        setCharacterCreatorTab();
        refreshProgram();
        JsonLoader loader = new JsonLoader();
        Campaign campaign = loader.fromJsontoCampaign("myCampaign.json");
        this.campaign = campaign;
        for(int j=0;j<campaign.getCharacters().size();j++){
            if(!campaign.getCharacters().get(j).getName().equals(mainTabPane.getTabs().get(j).getText())) {
                createNewCharacterTab(campaign.getCharacters().get(j));
            }
        }
        CharacterCreatorTab.setDisable(false);
        CampaignViewTab.setDisable(false);
        mainTabPane.getSelectionModel().select(CampaignViewTab);
    }

}
