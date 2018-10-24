package edu.bsu.cs222;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.Axis;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

@SuppressWarnings({"WeakerAccess", "EmptyMethod"})
public class Controller extends Application {
    @FXML
    private TextField inputCampaignText;
    @FXML
    private TabPane TabPane;
    @FXML
    private Tab CampaignViewTab;
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
    @FXML
    private TreeTableColumn currentCharacterTreeTableDisplay;
    @FXML
    private BarChart traitChart;
    @FXML
    private ListView characterList;
    private String raceName;
    private String dndClassName;
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

    public void onCreateNewCampaign(ActionEvent actionEvent) {
        this.campaign = new Campaign();
        String campaignName = inputCampaignText.getText();
        campaign.setCampaignName(campaignName);
        Alert campaignCreated = new Alert(Alert.AlertType.CONFIRMATION,"Campaign "+campaign.getCampaignName()+" has been created",ButtonType.OK);
        campaignCreated.showAndWait();
        TabPane.getSelectionModel().select(CampaignViewTab);
    }

    public void onAddCharacterToCampaign(ActionEvent actionEvent){
        try {
            String name = characterNameField.getText();
            Character character = new Character(name,dndClassName, raceName);

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
        displayCharacters();
        TabPane.getSelectionModel().selectPrevious();
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

    public void setRaceName(ActionEvent actionEvent){
            MenuItem item = (MenuItem)actionEvent.getSource();
            this.raceName = item.getText();
    }

    public void setClassName(ActionEvent actionEvent){
        MenuItem item = (MenuItem)actionEvent.getSource();
        this.dndClassName = item.getText();
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
        this.dndClassName = null;
    }

    public void loadOldCampaign(ActionEvent actionEvent) {

    }

    public void displayCharacters(){
        ArrayList<String> characters = new ArrayList<>();
        for(int i=0;i<campaign.getCharacters().size();i++){
            characters.add(campaign.getCharacters().get(i).getName());
        }
        ObservableList<String> characterNames = FXCollections.observableArrayList(characters);
        characterList.setItems(characterNames);

    }

    public void createChart(ActionEvent actionEvent){
        Character activeCharacter = (Character)actionEvent.getSource();
        XYChart.Series series = new XYChart.Series();
        series.setName(activeCharacter.getName()+" Traits");
        String[] traitNames = (String[])activeCharacter.getTraits().getTraitMap().keySet().toArray();
        Integer[] traitValues = (Integer[])activeCharacter.getTraits().getTraitMap().entrySet().toArray();
        for(int i =0;i<activeCharacter.getTraits().getTraitMap().size();i++){
            series.getData().add(new XYChart.Data(traitNames[i],traitValues[i]));
            //series.getData().add();
        }
        traitChart.getData().add(series);
    }

}
