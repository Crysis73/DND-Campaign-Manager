package edu.bsu.cs222;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.Date;

public class WealthModificationController{
    @FXML private ChoiceBox<String> wealthCharacterChoiceBox;
    @FXML private ChoiceBox<String> payToChoiceBox;
    @FXML private TextField modificationAmountWindow;
    private ArrayList<Character> characters;
    private String logUpdates;


    void injectCampaignViewTabController(CampaignViewTabController campaignViewTabController) {
    }


    private boolean validateWealthModification(String characterName, String payTo, String modificationAmount){
        StringBuilder wealthModificationErrors = new StringBuilder();
        Character character = getCharacterInCampaign(characterName);
        if(getCharacterInCampaign(characterName)==null){
            wealthModificationErrors.append("Your selected character is not in the campaign.");
        }
        if(getCharacterInCampaign(payTo) ==null && !payTo.equals("The Void")){
            wealthModificationErrors.append("The \"Pay To\" Character you selected is not in the campaign.");
        }
        if(modificationAmount.matches(".*[a-zA-Z]+.*")){
            wealthModificationErrors.append("Please ensure that the \"Modification Amount\" entered only contains integers 0-9.");
        }else {
            assert character != null;
            if(character.getWealth() < Integer.parseInt(modificationAmount) && !payTo.equals(characterName)){
                wealthModificationErrors.append("The entered modification amount is greater than the amount of wealth the selected character has available");
            }
        }

        if(wealthModificationErrors.length()!=0){
            Alert alert = new Alert(Alert.AlertType.ERROR, wealthModificationErrors.toString(), ButtonType.OK);
            alert.setHeaderText("Wealth Modification Errors");
            alert.showAndWait();
        }

        assert character != null;
        return (getCharacterInCampaign(characterName) != null && ( getCharacterInCampaign(payTo) != null || payTo.equals("The Void") )&& !modificationAmount.matches(".*[a-zA-Z]+.*") && !(character.getWealth() < Integer.parseInt(modificationAmount) && !payTo.equals(characterName)));
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

    public void onConfirm(javafx.event.ActionEvent actionEvent) {
        String characterName = wealthCharacterChoiceBox.getSelectionModel().getSelectedItem();
        String payToName = payToChoiceBox.getSelectionModel().getSelectedItem();
        String modificationAmount = modificationAmountWindow.getText();
        if(validateWealthModification(characterName,payToName,modificationAmount)) {
            Character character = getCharacterInCampaign(characterName);
            assert character != null;
            if (payToName.equals("The Void")) {
                character.setWealth(character.getWealth() - Integer.parseInt(modificationAmount));
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,characterName + " successfully paid " + modificationAmount + " wealth to " + payToName+".");
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
                alert.showAndWait();
                addUpdateToLog(new Date().toString() + ": "+characterName + " successfully paid " +modificationAmount + " wealth to "+wealthRecipient+".");
            }
        }
    }

    private void addUpdateToLog(String update){
        this.logUpdates += update+"\n";
    }

    String getLogUpdates(){
        if(logUpdates!=null) {
            return this.logUpdates.replace("null", "");
        }return null;
    }

}
