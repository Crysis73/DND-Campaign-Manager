package edu.bsu.cs222;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.MalformedParametersException;
import java.util.ArrayList;
import java.util.Map;

@SuppressWarnings({"WeakerAccess", "EmptyMethod", "unchecked"})
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
    private TextField setAge;
    @FXML
    private TextField setHeight;
    @FXML
    private TextField setWeight;
    @FXML
    private TextField setEyeColor;
    @FXML
    private TextField setSkinColor;
    @FXML
    private TextField setAlignment;
    @FXML
    private TextField setLanguages;
    @FXML
    private TextField setFlaws;
    @FXML
    private TextField setWealth;
    @FXML
    private TextField setXP;
    @FXML
    private ListView characterList;
    @FXML
    private TitledPane chartPane;
    @FXML
    private ListView CombatOrderDisplay;
    private String raceName;
    private String dndClassName;
    private Campaign campaign;
    private Character activeCharacter;

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
        try {
            String campaignName = inputCampaignText.getText();
            if(campaignName==null||campaignName.equals("")){
                throw new IllegalArgumentException("Campaign name is null");
            }
            this.campaign = new Campaign();
            campaign.setCampaignName(campaignName);
            Alert campaignCreated = new Alert(Alert.AlertType.CONFIRMATION, "Campaign " + campaign.getCampaignName() + " has been created", ButtonType.OK);
            campaignCreated.showAndWait();
            TabPane.getSelectionModel().select(CampaignViewTab);
        }catch(IllegalArgumentException e){
            Alert noCampaignName = new Alert(Alert.AlertType.ERROR,"Campaign name cannot be empty!",ButtonType.OK);
            noCampaignName.setHeaderText("No Campaign Name");
            noCampaignName.showAndWait();
        }
    }

    public String getCreatedCharacterName(){
        try{
            String name = characterNameField.getText();
            if(name==null || name.equals("")){
                throw new IllegalArgumentException("Name cannot be empty!");
            }
            return name;
        } catch(IllegalArgumentException e){
            Alert noNameAlert = new Alert(Alert.AlertType.ERROR,"Character name cannot be empty!",ButtonType.OK);
            noNameAlert.setHeaderText("No Character Name");
            noNameAlert.showAndWait();
        }
        return null;
    }



    public void onAddCharacterToCampaign(ActionEvent actionEvent) {
        try{
            String characterName = getCreatedCharacterName();
            if(characterName ==null || characterName.equals("")){
                throw new NullPointerException("Character name is empty");
            }
            for(int i =0;i<campaign.getCharacters().size();i++){
                if(characterName.equals(campaign.getCharacters().get(i).getName())){
                    throw new IllegalArgumentException("Character already exists!");
                }
            }
            if(this.raceName == null){
                throw new MalformedParametersException("Race not selected");
            }
            if(this.dndClassName == null){
                throw new IllegalArgumentException("Class not selected");
            }
            Character character = new Character(characterName, dndClassName, raceName);
            campaign.addCharacer(character);
            createNewCharacterTab(character.getName(),character);
            displayCharacters();
            Alert characterCreated = new Alert(Alert.AlertType.INFORMATION,characterName+" has been created!");
            characterCreated.setTitle("Character Created");
            characterCreated.setHeaderText("SUCCESS");
            characterCreated.showAndWait();
        }catch(MalformedParametersException e){
            Alert noRaceAlert = new Alert(Alert.AlertType.ERROR,"You must select a Race before trying to create a character",ButtonType.OK);
            noRaceAlert.setHeaderText("No Race");
            noRaceAlert.showAndWait();
        }catch(IllegalArgumentException e){
            if(e.getMessage().equals("Character already exists!")){
                Alert existingCharacter = new Alert(Alert.AlertType.ERROR,"Character already exists!");
                existingCharacter.setHeaderText("Existing Character");
                existingCharacter.showAndWait();
            }else {
                Alert noClassAlert = new Alert(Alert.AlertType.ERROR, "You must select a Class before trying to create a Character");
                noClassAlert.setHeaderText("No Class");
                noClassAlert.showAndWait();
            }
        }catch(NullPointerException e){
            //This is being caught to avoid redundant alerts for not having inputted a character name.
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

    public void setRaceName(ActionEvent actionEvent){
        MenuItem item = (MenuItem) actionEvent.getSource();
        this.raceName = item.getText();
    }

    public void setClassName(ActionEvent actionEvent){
        MenuItem item = (MenuItem) actionEvent.getSource();
        this.dndClassName = item.getText();
    }

    public void clearAllNewCharacter(ActionEvent actionEvent){
        setHeight.clear();
        setWeight.clear();
        setAge.clear();
        setEyeColor.clear();
        setSkinColor.clear();
        setAlignment.clear();
        setLanguages.clear();
        setFlaws.clear();
        characterNameField.clear();
        setWealth.setText("0");
        setXP.setText("0");
        this.raceName = null;
        this.dndClassName = null;
    }

    public void addDescription(Character character){
        ArrayList<String> descriptionItems = new ArrayList<>();
        descriptionItems.add(setAge.getText());
        descriptionItems.add(setHeight.getText());
        descriptionItems.add(setWeight.getText());
        descriptionItems.add(setEyeColor.getText());
        descriptionItems.add(setSkinColor.getText());
        descriptionItems.add(setAlignment.getText());
        descriptionItems.add(setLanguages.getText());
        descriptionItems.add(setFlaws.getText());
        descriptionItems.add(setWealth.getText());
        descriptionItems.add(setXP.getText());
        for (String descriptionItem : descriptionItems) {
            if (descriptionItem != null) {
                if (descriptionItem.equals(setAge.getText())) {
                    character.getCharacterDescription().setAge(setAge.getText());
                }
                if (descriptionItem.equals(setHeight.getText())) {
                    character.getCharacterDescription().setHeight(setHeight.getText());
                }
                if (descriptionItem.equals(setWeight.getText())) {
                    character.getCharacterDescription().setWeight(setWeight.getText());
                }
                if (descriptionItem.equals(setEyeColor.getText())) {
                    character.getCharacterDescription().setEyeColor(setEyeColor.getText());
                }
                if (descriptionItem.equals(setSkinColor.getText())) {
                    character.getCharacterDescription().setSkinColor(setSkinColor.getText());
                }
                if (descriptionItem.equals(setAlignment.getText())) {
                    character.getCharacterDescription().setAlignment(setAlignment.getText());
                }
                if (descriptionItem.equals(setLanguages.getText())) {
                    character.getCharacterDescription().setLanguages(setLanguages.getText());
                }
                if (descriptionItem.equals(setFlaws.getText())) {
                    character.getCharacterDescription().setFlaws(setFlaws.getText());
                }
                if (descriptionItem.equals(setWealth.getText())) {
                    character.setWealth(Integer.parseInt(setWealth.getText()));
                }
                if (descriptionItem.equals(setXP.getText())) {
                    character.setExperiencepoints(Integer.parseInt(setXP.getText()));
                }
            }
        }
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

    public void setActiveCharacter(String name){
        for(int i =0;i<campaign.getCharacters().size();i++){
            if(campaign.getCharacters().get(i).getName().equals(name)){
                this.activeCharacter = campaign.getCharacters().get(i);
            }
        }
    }

    public void createChart(MouseEvent actionEvent) {
        ListView view = (ListView) actionEvent.getSource();
        ObservableList activeCharacterName = view.getSelectionModel().getSelectedItems();
        String characterName = activeCharacterName.toString().replace("[","").replace("]","");
        setActiveCharacter(characterName);
        final XYChart.Series<String,Integer> series = new XYChart.Series<>();
        final Map<String,Integer> characterTraits = activeCharacter.getTraits().getTraitMap();
        series.setName(activeCharacter.getName() + "'s Traits");
        CategoryAxis xAxis = new CategoryAxis();
        Axis<? extends Number> yAxis = new NumberAxis();
        ObservableList<String> traitNames = FXCollections.observableArrayList(activeCharacter.getTraits().getTraitMap().keySet());
        ObservableList<Integer> traitValues = FXCollections.observableArrayList(activeCharacter.getTraits().getTraitMap().values());
        for(final Map.Entry<String,Integer> entry: characterTraits.entrySet()){
            series.getData().add(new XYChart.Data<>(entry.getKey(),entry.getValue()));
        }
        xAxis.setCategories(traitNames);
        final ObservableList<XYChart.Series<String,Integer>> chartData = FXCollections.observableArrayList();
        chartData.add(series);
        BarChart<String,Integer> chart = new BarChart<>(xAxis, (Axis<Integer>) yAxis);
        chart.getData().addAll(chartData);
        yAxis.setAutoRanging(false);
        ((NumberAxis) yAxis).setLowerBound(0);
        ((NumberAxis) yAxis).setUpperBound(20);
        chart.setLegendSide(Side.TOP);
        chart.setPadding(new Insets(2,2,2,2));
        chartPane.setContent(chart);



    }

    public void generateCombatOrder(ActionEvent actionEvent){
        ArrayList charactersInCombatOrder = campaign.generateCombatOrder();
        for(int i=0;i<charactersInCombatOrder.size();i++){
            Character character = (Character) charactersInCombatOrder.get(charactersInCombatOrder.size()-i-1);
            CombatOrderDisplay.getItems().add(character.getName() + "'s roll for initiative :"+(character.getInitiative()));
        }
    }

    public void clearCombatOrder(ActionEvent actionEvent){
        CombatOrderDisplay.getItems().setAll();
    }

}
