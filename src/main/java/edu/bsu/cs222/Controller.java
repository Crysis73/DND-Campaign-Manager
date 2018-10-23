package edu.bsu.cs222;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

@SuppressWarnings({"WeakerAccess", "EmptyMethod"})
public class Controller extends Application {
    @FXML
    private TextField inputCampaignText;
    @FXML
    private TabPane TabPane;
    @FXML
    private Tab CharacterCreatorTab;
    @FXML
    private TextField characterNameField;
    @FXML
    private MenuButton selectRaceNewCharacter;
    @FXML
    private MenuButton selectClassNewCharacter;
    @FXML
    private TextField ageNewCharacter;
    @FXML
    private TextField heightNewCharacter;
    @FXML
    private TextField weightNewCharacter;
    @FXML
    private TextField eyeColorNewCharacter;
    @FXML
    private TextField skinColorNewCharacter;
    @FXML
    private TextField alignmentNewCharacter;
    @FXML
    private TextField languagesNewCharacter;
    @FXML
    private TextField flawsNewCharacter;
    @FXML
    private TextField wealthNewCharacter;
    @FXML
    private TextField xpNewCharacter;
    private String raceName;
    private Campaign campaign;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Pane root = FXMLLoader.load(getClass().getResource("/GUIFile.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("D&D Game Master Thingy 3000");
        stage.show();
    }
    public void onCreateNewCampaign(javafx.event.ActionEvent actionEvent) {
        this.campaign = new Campaign();
        String campaignName = inputCampaignText.getText();
        campaign.setCampaignName(campaignName);
        TabPane.getSelectionModel().select(CharacterCreatorTab);
    }
    public void onAddCharacterToCampaign(ActionEvent actionEvent){
        this.campaign = new Campaign();
        String name = characterNameField.getText();
        dndClass Bard = new dndClass("Bard","Description",8);
        try {
            Character character = new Character(name, Bard, raceName);

            if (ageNewCharacter.getText() != null) {
                character.getCharacterDescription().setAge(ageNewCharacter.getText());
            }
            if (heightNewCharacter.getText() != null) {
                character.getCharacterDescription().setHeight(heightNewCharacter.getText());
            }
            if (weightNewCharacter.getText() != null) {
                character.getCharacterDescription().setWeight(weightNewCharacter.getText());
            }
            if (eyeColorNewCharacter.getText() != null) {
                character.getCharacterDescription().setEyeColor(eyeColorNewCharacter.getText());
            }
            if (skinColorNewCharacter.getText() != null) {
                character.getCharacterDescription().setSkinColor(skinColorNewCharacter.getText());
            }
            if (alignmentNewCharacter.getText() != null) {
                character.getCharacterDescription().setAlignment(alignmentNewCharacter.getText());
            }
            if (languagesNewCharacter.getText() != null) {
                character.getCharacterDescription().setLanguages(languagesNewCharacter.getText());
            }
            if (flawsNewCharacter.getText() != null) {
                character.getCharacterDescription().setFlaws(flawsNewCharacter.getText());
            }

            if (!Objects.equals(wealthNewCharacter.getText(), "\\s")) {
                character.setWealth(Integer.parseInt(wealthNewCharacter.getText()));
            }
            if (!Objects.equals(xpNewCharacter.getText(), "\\s")) {
                character.setExperiencepoints(Integer.parseInt(xpNewCharacter.getText()));
            }
            this.campaign.addCharacer(character);
            createNewCharacterTab(character.getName(), character);
        }catch(NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Please enter a name and select a race and try again");
            alert.showAndWait();
        }catch(NumberFormatException e){
            Alert numberAlert = new Alert(Alert.AlertType.ERROR,"Please enter an integer value for your Wealth and XP.");
            numberAlert.showAndWait();
        }

    }

    @SuppressWarnings("EmptyMethod")
    public void loadCurrentCharacters(TreeTableColumn.CellEditEvent cellEditEvent) {

    }

    @SuppressWarnings("EmptyMethod")
    public void displayDieRollResultInResultWindow(ActionEvent actionEvent) {

    }
    public void createNewCharacterTab(String tabName,Character character){
        Tab tab = new Tab(tabName);
        TextArea textArea = new TextArea();
        tab.setContent(textArea);
        textArea.setText(character.toString());
        textArea.setEditable(false);
        TabPane.getTabs().add(tab);
    }

    public void setRaceNameDwarf(ActionEvent actionEvent){
        this.raceName="Dwarf";
    }
    public void setRaceNameHillDwarf(ActionEvent actionEvent){
        this.raceName="Hill Dwarf";
    }
    public void setRaceNameMountainDwarf(ActionEvent actionEvent){
        this.raceName="Hill Dwarf";
    }
    public void setRaceNameElf(ActionEvent actionEvent){
        this.raceName="Elf";
    }
    public void setRaceNameHighElf(ActionEvent actionEvent){
        this.raceName="High Elf";
    }
    public void setRaceNameWoodElf(ActionEvent actionEvent){
        this.raceName="Wood Elf";
    }
    public void setRaceNameHalfling(ActionEvent actionEvent){
        this.raceName="Halfling";
    }
    public void setRaceNameLightfoot(ActionEvent actionEvent){
        this.raceName="Lightfoot";
    }
    public void setRaceNameStout(ActionEvent actionEvent){
        this.raceName="Stout";
    }
    public void setRaceNameHuman(ActionEvent actionEvent){
        this.raceName="Human";
    }
    public void setRaceNameDragonborn(ActionEvent actionEvent){
        this.raceName="Dragonborn";
    }
    public void setRaceNameGnome(ActionEvent actionEvent){
        this.raceName="Gnome";
    }
    public void setRaceNameForestGnome(ActionEvent actionEvent){
        this.raceName="Forest Gnome";
    }
    public void setRaceNameDeepGnome(ActionEvent actionEvent){
        this.raceName="Deep Gnome";
    }
    public void setRaceNameRockGnome(ActionEvent actionEvent){
        this.raceName="Rock Gnome";
    }
    public void setRaceNameHalfOrc(ActionEvent actionEvent){
        this.raceName="Half-Orc";
    }
    public void setRaceNameTiefling(ActionEvent actionEvent){
        this.raceName="Tiefling";
    }

    public void clearAllNewCharacter(ActionEvent actionEvent){
        heightNewCharacter.clear();
        weightNewCharacter.clear();
        ageNewCharacter.clear();
        eyeColorNewCharacter.clear();
        skinColorNewCharacter.clear();
        alignmentNewCharacter.clear();
        languagesNewCharacter.clear();
        flawsNewCharacter.clear();
        characterNameField.clear();
        wealthNewCharacter.setText("0");
        xpNewCharacter.setText("0");
        this.raceName = null;
    }
    public void loadOldCampaign(ActionEvent actionEvent) {

    }
}
