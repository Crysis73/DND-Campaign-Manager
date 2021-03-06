package edu.bsu.cs222;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.util.Date;

public class CampaignOpenTabController {

    @FXML private TextField inputCampaignText;
    @FXML private MainController mainController;
    @FXML private AnchorPane rootPane;
    private boolean isDarkTheme;

    void injectMainController(MainController mainController){
        this.mainController = mainController;
    }

    private void showCampaignCreationSuccessfulAlert(Campaign campaign){
        Alert campaignCreated = new Alert(Alert.AlertType.CONFIRMATION, "Campaign " + campaign.getCampaignName() + " has been created", ButtonType.OK);
        if(isDarkTheme) {
            DialogPane dialogPane = campaignCreated.getDialogPane();
            dialogPane.getStylesheets().add(getClass().getResource("/Stylesheets/DarkTheme.css").toExternalForm());
            dialogPane.getStyleClass().add("DarkTheme");
        }
        campaignCreated.showAndWait();
    }

    private boolean validateNewCampaign(){
        StringBuilder campaignErrors = new StringBuilder();
        if(inputCampaignText.getText().trim().isEmpty()){
            campaignErrors.append("- Please input a campaign name. \n");
        }
        if(campaignErrors.length()>0){
            Alert campaignAlert = new Alert(Alert.AlertType.ERROR, campaignErrors.toString(),ButtonType.OK);
            campaignAlert.setHeaderText("Invalid Campaign Information");
            if(isDarkTheme) {
                DialogPane dialogPane = campaignAlert.getDialogPane();
                dialogPane.getStylesheets().add(getClass().getResource("/Stylesheets/DarkTheme.css").toExternalForm());
                dialogPane.getStyleClass().add("DarkTheme");
            }
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
            mainController.removeCampaignTabFromTabPane();
            campaign.addEntryToLog(new Date().toString() + ": "+campaign.getCampaignName() + " CREATED.");
        }
    }

    void toDarkTheme(){
        rootPane.getStylesheets().add(getClass().getResource("/Stylesheets/DarkTheme.css").toExternalForm());
        rootPane.getStyleClass().add("DarkTheme");
        isDarkTheme = true;
    }


    public void openCampaign(ActionEvent actionEvent) {
        mainController.openCampaign();
    }
}
