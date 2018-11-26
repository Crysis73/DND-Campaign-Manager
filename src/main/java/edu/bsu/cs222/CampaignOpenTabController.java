package edu.bsu.cs222;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

public class CampaignOpenTabController {

    @FXML private TextField inputCampaignText;
    @FXML private MainController mainController;

    public void injectMainController(MainController mainController){
        this.mainController = mainController;
    }

    public void showCampaignCreationSuccessfulAlert(Campaign campaign){
        Alert campaignCreated = new Alert(Alert.AlertType.CONFIRMATION, "Campaign " + campaign.getCampaignName() + " has been created", ButtonType.OK);
        campaignCreated.showAndWait();
    }

    public boolean validateNewCampaign(){
        StringBuilder campaignErrors = new StringBuilder();
        if(inputCampaignText.getText().trim().isEmpty()){
            campaignErrors.append("- Please input a campaign name. \n");
        }
        if(campaignErrors.length()>0){
            Alert campaignAlert = new Alert(Alert.AlertType.ERROR, campaignErrors.toString(),ButtonType.OK);
            campaignAlert.setHeaderText("Invalid Campaign Information");
            campaignAlert.showAndWait();
            return false;
        }
        return true;
    }

    public void onCreateNewCampaign() {
        if(validateNewCampaign()){
            mainController.refreshProgram();
            Campaign campaign = new Campaign(inputCampaignText.getText());
            mainController.setCampaign(campaign);
            showCampaignCreationSuccessfulAlert(campaign);
            mainController.setCharacterCreatorTab();
        }
    }


    public void loadOldCampaign() {
        mainController.loadOldCampaign();
    }
}
