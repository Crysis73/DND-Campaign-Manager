package edu.bsu.cs222;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;


public class CharacterTabController {
    @FXML private Label characterNameText, raceNameText, classNameText, raceDescription, classDescription;
    @FXML private TextArea raceDescriptionBox, classDescriptionBox;
    @FXML private TextField wealthBox, currentHPBox, maxHPBox, xpBox, levelBox,
            strengthBox, dexterityBox, constitutionBox, intelligenceBox, wisdomBox, charismaBox,
    heightBox, weightBox, ageBox, eyeColorBox, skinColorBox, additionalFeaturesBox,
            alignmentBox, languagesBox, exoticLanguagesBox, personalityTraitsBox, idealsBox, bondsBox, flawsBox;
    @FXML private AnchorPane rootPane;
    private boolean XPChanged, isDarkTheme;
    private final Character character;

    CharacterTabController(Character characterName){
        this.character = characterName;
    }

    void toDarkTheme(){
        rootPane.getStylesheets().add(getClass().getResource("/Stylesheets/DarkThemeCharacterSheet.css").toExternalForm());
        rootPane.getStyleClass().add("DarkThemeCharacterSheet");
        isDarkTheme = true;
    }

    void initialize(){
        this.characterNameText.setText(character.getName());
        this.raceNameText.setText(character.getRaceName());
        this.classNameText.setText(character.getdndClassName());
        this.wealthBox.setText(character.getWealth().toString());
        this.currentHPBox.setText(character.getCurrentHitPoints().toString());
        this.maxHPBox.setText(character.getMaxHitPoints().toString());
        this.xpBox.setText(character.getExperiencepoints().toString());
        this.levelBox.setText(character.getLevel().getCurrentLevel().toString());
        this.strengthBox.setText(character.getTraits().getValue("Strength").toString());
        this.dexterityBox.setText(character.getTraits().getValue("Dexterity").toString());
        this.constitutionBox.setText(character.getTraits().getValue("Constitution").toString());
        this.intelligenceBox.setText(character.getTraits().getValue("Intelligence").toString());
        this.wisdomBox.setText(character.getTraits().getValue("Wisdom").toString());
        this.charismaBox.setText(character.getTraits().getValue("Charisma").toString());
        this.heightBox.setText(character.getCharacterDescription().getHeight());
        this.weightBox.setText(character.getCharacterDescription().getWeight());
        this.ageBox.setText(character.getCharacterDescription().getAge());
        this.eyeColorBox.setText(character.getCharacterDescription().getEyeColor());
        this.skinColorBox.setText(character.getCharacterDescription().getSkinColor());
        this.additionalFeaturesBox.setText(character.getCharacterDescription().getAdditionalFeatures());
        this.alignmentBox.setText(character.getCharacterDescription().getAlignment());
        this.languagesBox.setText(character.getCharacterDescription().getLanguages());
        this.exoticLanguagesBox.setText(character.getCharacterDescription().getExoticLanguages());
        this.personalityTraitsBox.setText(character.getCharacterDescription().getPersonalityTrait1());
        this.idealsBox.setText(character.getCharacterDescription().getIdeals());
        this.bondsBox.setText(character.getCharacterDescription().getBonds());
        this.flawsBox.setText(character.getCharacterDescription().getFlaws());
        this.XPChanged = false;
    }

    @FXML
    private void onSaveChanges(){
        if(validateChanges()){
            updateTraits();
            updateStats();
            updateDescription();
            initialize();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"All values have been updated successfully", ButtonType.OK);
            if(isDarkTheme) {
                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.getStylesheets().add(getClass().getResource("/Stylesheets/DarkTheme.css").toExternalForm());
                dialogPane.getStyleClass().add("DarkTheme");
            }
            alert.showAndWait();
            this.XPChanged = false;
        }
    }

    private boolean validateChanges() {
        StringBuilder errors = new StringBuilder();
        try {
            if (
                    Integer.parseInt(strengthBox.getText()) > 20 ||
                            Integer.parseInt(dexterityBox.getText()) > 20 ||
                            Integer.parseInt(constitutionBox.getText()) > 20 ||
                            Integer.parseInt(intelligenceBox.getText()) > 20 ||
                            Integer.parseInt(wisdomBox.getText()) > 20 ||
                            Integer.parseInt(charismaBox.getText()) > 20
            ) {
                errors.append(" - Traits cannot have a value higher than 20.\n");
            }
            if (
                    strengthBox.getText().matches(".*[a-zA-Z]+.*") ||
                            dexterityBox.getText().matches(".*[a-zA-Z]+.*") ||
                            constitutionBox.getText().matches(".*[a-zA-Z]+.*") ||
                            intelligenceBox.getText().matches(".*[a-zA-Z]+.*") ||
                            wisdomBox.getText().matches(".*[a-zA-Z]+.*") ||
                            charismaBox.getText().matches(".*[a-zA-Z]+.*") ||
                            wealthBox.getText().matches(".*[a-zA-Z]+.*") ||
                            currentHPBox.getText().matches(".*[a-zA-Z]+.*") ||
                            maxHPBox.getText().matches(".*[a-zA-Z]+.*") ||
                            xpBox.getText().matches(".*[a-zA-Z]+.*") ||
                            levelBox.getText().matches(".*[a-zA-Z]+.*")
            ) {
                errors.append(" - Please ensure that all stats and traits only contain integers.\n");
            }
            if (Integer.parseInt(levelBox.getText()) > 20) {
                errors.append(" - 20 is the maximum level that a Character may reach.\n");

            }
            if (Integer.parseInt(xpBox.getText()) > 355000) {
                errors.append(" - 355000 is the maximum amount of XP that a character can earn\n");
            }
        }catch(NumberFormatException e){
            errors.append(" - Somewhere you entered a number that was way too big for our program to handle, and for this, you shall be punished.");
        }
        if(errors.length()!=0){
            Alert alert = new Alert(Alert.AlertType.ERROR,errors.toString(),ButtonType.OK);
            if(isDarkTheme) {
                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.getStylesheets().add(getClass().getResource("/Stylesheets/DarkTheme.css").toExternalForm());
                dialogPane.getStyleClass().add("DarkTheme");
            }
            alert.showAndWait();
        }
        return errors.length() <= 0;
    }


    private void updateTraits(){
        character.getTraits().setValue("Strength",Integer.parseInt(strengthBox.getText()));
        character.getTraits().setValue("Dexterity",Integer.parseInt(dexterityBox.getText()));
        character.getTraits().setValue("Constitution",Integer.parseInt(constitutionBox.getText()));
        character.getTraits().setValue("Intelligence",Integer.parseInt(intelligenceBox.getText()));
        character.getTraits().setValue("Wisdom",Integer.parseInt(wisdomBox.getText()));
        character.getTraits().setValue("Charisma",Integer.parseInt(charismaBox.getText()));

    }

    private void updateStats(){
        if(validateChanges()) {
            try {
                character.setWealth(Integer.parseInt(wealthBox.getText()));
                character.setCurrentHitPoints(Integer.parseInt(currentHPBox.getText()));
                character.setMaxHitPoints(Integer.parseInt(maxHPBox.getText()));
            }catch (NumberFormatException e ){
                Alert alert = new Alert(Alert.AlertType.ERROR,
                        "- Somewhere you entered a number that was way too big for our program to handle, and for this, you shall be punished.",ButtonType.OK);
                if(isDarkTheme) {
                    DialogPane dialogPane = alert.getDialogPane();
                    dialogPane.getStylesheets().add(getClass().getResource("/Stylesheets/DarkTheme.css").toExternalForm());
                    dialogPane.getStyleClass().add("DarkTheme");
                }
                alert.showAndWait();
            }
            if (XPChanged) {
                character.setExperiencepoints(Integer.parseInt(xpBox.getText()));
                this.levelBox.setText(character.getLevel().getCurrentLevel().toString());
            } else {
                character.setLevel(character.getLevel().getCurrentLevel(), Integer.parseInt(levelBox.getText()));
            }
        }
    }

    private void updateDescription(){
        character.getCharacterDescription().setAllValues(
                heightBox.getText(),
                weightBox.getText(),
                ageBox.getText(),
                eyeColorBox.getText(),
                skinColorBox.getText(),
                additionalFeaturesBox.getText(),
                alignmentBox.getText(),
                languagesBox.getText(),
                exoticLanguagesBox.getText(),
                personalityTraitsBox.getText(),
                personalityTraitsBox.getText(),
                idealsBox.getText(),
                bondsBox.getText(),
                flawsBox.getText());
    }


    @FXML public void setXPChanged() {
        if(validateChanges()) {
            XPChanged = character.getExperiencepoints() != Integer.parseInt(xpBox.getText());
        }
    }
}
