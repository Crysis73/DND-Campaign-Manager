package edu.bsu.cs222;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.Date;

class WealthModificationController{
    @FXML private ChoiceBox<String> wealthCharacterChoiceBox;
    @FXML private ChoiceBox<String> payToChoiceBox;
    @FXML private TextField modificationAmountWindow;
    @FXML private AnchorPane rootPane;
    private ArrayList<Character> characters;
    private String logUpdates;
    private boolean isDarkTheme;

    void toDarkTheme(){
        rootPane.getStylesheets().add(getClass().getResource("/Stylesheets/DarkThemeWealthModificationMenu.css").toExternalForm());
        rootPane.getStyleClass().add("DarkThemeWealthModificationMenu");
        isDarkTheme = true;
    }

    private boolean validateWealthModification(String characterName, String payTo, String modificationAmount){
        StringBuilder wealthModificationErrors = new StringBuilder();
        if(characterName == null){
            wealthModificationErrors.append(" - Please select a character to modify their wealth.\n");
        }
        if(modificationAmount.matches(".*[a-zA-Z]+.*")){
            wealthModificationErrors.append(" - Please ensure that the \"Modification Amount\" entered only contains integers 0-9.\n");
        }
        if(modificationAmount.trim().isEmpty()){
            wealthModificationErrors.append(" - Wealth modification amount cannot be empty.\n");
        }
        if(payTo == null){
            wealthModificationErrors.append(" - Please select a wealth recipient. (It can be the same character!)\n");
        }
        if(payTo!= null && characterName != null) {
            Character character = getCharacterInCampaign(characterName);
            assert character!=null;
            if (getCharacterInCampaign(characterName) == null) {
                wealthModificationErrors.append(" - Your selected character is not in the campaign.\n");
            }
            if (getCharacterInCampaign(payTo) == null && !payTo.equals("The Void")) {
                wealthModificationErrors.append(" - The \"Pay To\" Character you selected is not in the campaign.\n");
            }
            if(character.getWealth() < Integer.parseInt(modificationAmount) && !payTo.equals(characterName)){
                wealthModificationErrors.append(" - The entered modification amount is greater than the amount of wealth the selected character has available\n");
            }
        }

        if(wealthModificationErrors.length()!=0){
            Alert alert = new Alert(Alert.AlertType.ERROR, wealthModificationErrors.toString(), ButtonType.OK);
            alert.setHeaderText("Wealth Modification Errors");
            if(isDarkTheme) {
                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.getStylesheets().add(getClass().getResource("/Stylesheets/DarkTheme.css").toExternalForm());
                dialogPane.getStyleClass().add("DarkTheme");
            }
            alert.showAndWait();
            return false;
        }
        return true;

    }

    void initializeCharacterChoices(){
        ArrayList<String> characterNames = new ArrayList<>();
        for (Character character : characters) {
            characterNames.add(character.getName());
        }
        wealthCharacterChoiceBox.getItems().setAll(characterNames);
    }

    void initializePayToChoices(){
        ArrayList<String> characterNames = new ArrayList<>();
        for (Character character : characters) {
            characterNames.add(character.getName());
        }
        characterNames.add("The Void");
        payToChoiceBox.getItems().setAll(characterNames);
    }

    void injectCampaignCharacters(ArrayList<Character> characters) {
        this.characters = characters;
    }

    private Character getCharacterInCampaign(String name){
        for (Character character : characters) {
            if (character.getName().equals(name)) {
                return character;
            }
        }
        return null;
    }

    @FXML public void onConfirm() {
        String characterName = wealthCharacterChoiceBox.getSelectionModel().getSelectedItem();
        String payToName = payToChoiceBox.getSelectionModel().getSelectedItem();
        String modificationAmount = modificationAmountWindow.getText();
        if(validateWealthModification(characterName,payToName,modificationAmount)) {
            Character character = getCharacterInCampaign(characterName);
            assert character != null;
            if (payToName.equals("The Void")) {
                character.setWealth(character.getWealth() - Integer.parseInt(modificationAmount));
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,characterName + " successfully paid " + modificationAmount + " wealth to " + payToName+".");
                if(isDarkTheme) {
                    DialogPane dialogPane = alert.getDialogPane();
                    dialogPane.getStylesheets().add(getClass().getResource("/Stylesheets/DarkTheme.css").toExternalForm());
                    dialogPane.getStyleClass().add("DarkTheme");
                }
                alert.showAndWait();
                addUpdateToLog(new Date().toString() + ": "+characterName +" successfully paid " + modificationAmount + " wealth to "+payToName+".");
            } else {
                Character payTo = getCharacterInCampaign(payToName);
                assert payTo != null;
                if(payTo!=character) {
                    character.setWealth(character.getWealth() - Integer.parseInt(modificationAmount));
                }
                payTo.setWealth(payTo.getWealth() + Integer.parseInt(modificationAmount));
                String wealthRecipient;
                if(characterName.equals(payToName)){
                    wealthRecipient = "themself";
                }else{
                    wealthRecipient = payToName;
                }
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,characterName + " successfully paid " + modificationAmount + " wealth to " + wealthRecipient+".");
                if(isDarkTheme) {
                    DialogPane dialogPane = alert.getDialogPane();
                    dialogPane.getStylesheets().add(getClass().getResource("/Stylesheets/DarkTheme.css").toExternalForm());
                    dialogPane.getStyleClass().add("DarkTheme");
                }
                alert.showAndWait();
                addUpdateToLog(new Date().toString() + ": "+characterName + " successfully paid " +modificationAmount + " wealth to "+wealthRecipient+".");
            }
        }
    }

    private void addUpdateToLog(String update){
        if(!update.trim().isEmpty()) {
            this.logUpdates += update + "\n";
        }
    }

    String getLogUpdates(){
        if(logUpdates!=null) {
            return this.logUpdates.replace("null", "");
        }return null;
    }

}
