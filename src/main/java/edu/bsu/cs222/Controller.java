package edu.bsu.cs222;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

@SuppressWarnings({"WeakerAccess", "unchecked"})
public class Controller extends Application {
    @FXML
    private TabPane TabPane;
    @FXML
    private Tab CampaignViewTab,CharacterCreatorTab;

    @FXML
    private MenuButton selectRaceNewCharacter,selectClassNewCharacter;
    @FXML
    private TextField inputCampaignText, characterNameField, setAge, setHeight, setWeight, setEyeColor, setSkinColor, setAdditionalFeatures, setAlignment,
            setLanguages, setExoticLanguages, setPersonalityTrait1, setPersonalityTrait2, setIdeals, setBonds, setFlaws,
            setWealth, setXP, resultWindow;
    @FXML
    private ListView characterList;
    @FXML
    private TitledPane chartPane,charactersPane;
    @FXML
    private ListView CombatOrderDisplay;
    @FXML
    private ComboBox difficultyClassComboBox;
    @FXML
    private ChoiceBox characterChoiceBox, traitChoiceBox;
    @FXML
    private Spinner<String> advantageSpinner;
    private String raceName;
    private String dndClassName;
    private Campaign campaign;
    private Character activeCharacter;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        Pane root = FXMLLoader.load(getClass().getResource("/GUIFile.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("D&D Game Master Thingy 3000");
        stage.show();

    }

    public void onCreateNewCampaign(ActionEvent actionEvent) {
        try {
            String campaignName = inputCampaignText.getText();
            if(campaignName==null||campaignName.equals("")||campaignName.equals("\\s")){
                throw new IllegalArgumentException("Campaign name is null");
            }
            if(campaignName.trim().isEmpty()){
                throw new IllegalArgumentException("Campaign name cannot only be spaces");
            }
            this.campaign = new Campaign();
            campaign.setCampaignName(campaignName);
            Alert campaignCreated = new Alert(Alert.AlertType.CONFIRMATION, "Campaign " + campaign.getCampaignName() + " has been created", ButtonType.OK);
            campaignCreated.showAndWait();
            TabPane.getSelectionModel().select(CampaignViewTab);
            initializeTraitChoices();
            initializeDifficultyClasses();
            initializeAdvantageStates();
            CampaignViewTab.disableProperty().setValue(false);
            CharacterCreatorTab.disableProperty().set(false);
        }catch(IllegalArgumentException e){
            showErrorMessage(e.getMessage());

        }
    }

    private String getCreatedCharacterName(){
        try{
            String name = characterNameField.getText();
            if(name==null || name.equals("")){
                throw new IllegalArgumentException("Name cannot be empty!");
            }
            return name;
        } catch(IllegalArgumentException e){
            showErrorMessage(e.getMessage());
        }
        return null;
    }

    public void checkCharacterValidity(String characterName){
        if(characterName ==null || characterName.equals("")){
            throw new NullPointerException("Name cannot be empty!");
        }
        if(characterName.trim().isEmpty()){
            throw new IllegalArgumentException("Character name cannot only be spaces");
        }
        for(int i =0;i<campaign.getCharacters().size();i++){
            if(characterName.trim().equals(campaign.getCharacters().get(i).getName().trim())){
                throw new IllegalArgumentException("Character already exists!");
            }
        }
        if(this.raceName == null){
            throw new MalformedParametersException("Race not selected");
        }
        if(this.dndClassName == null){
            throw new IllegalArgumentException("Class not selected");
        }
    }

    public void showErrorMessage(String errorMessage){
        if(errorMessage.equals("Character already exists!")){
            Alert existingCharacter = new Alert(Alert.AlertType.ERROR,"A character with that name already exists!");
            existingCharacter.setHeaderText("Existing Character");
            existingCharacter.showAndWait();
        }
        if(errorMessage.equals("Wealth must be an integer value")){
            Alert wealthNonInteger = new Alert(Alert.AlertType.ERROR,"Wealth must be an integer value");
            wealthNonInteger.setHeaderText("Non Integer Wealth");
            wealthNonInteger.show();
        }
        if(errorMessage.equals("XP must be an integer value")){
            Alert XPNonInteger = new Alert(Alert.AlertType.ERROR,"XP must be an integer value");
            XPNonInteger.setHeaderText("Non Integer XP");
            XPNonInteger.showAndWait();
        }
        if(errorMessage.equals("Class not selected")){
            Alert noClassAlert = new Alert(Alert.AlertType.ERROR, "You must select a Class before trying to create a Character");
            noClassAlert.setHeaderText("No Class");
            noClassAlert.showAndWait();
        }
        if(errorMessage.equals("Character name cannot only be spaces")){
            Alert nameIsSpace = new Alert(Alert.AlertType.ERROR,"Character name cannot only be spaces");
            nameIsSpace.setHeaderText("Name is only spaces");
            nameIsSpace.showAndWait();
        }
        if(errorMessage.equals("Name cannot be empty!")){
            Alert noNameAlert = new Alert(Alert.AlertType.ERROR,"Character name cannot be empty!",ButtonType.OK);
            noNameAlert.setHeaderText("No Character Name");
            noNameAlert.showAndWait();
        }
        if(errorMessage.equals("Campaign name is null")) {
            Alert noCampaignName = new Alert(Alert.AlertType.ERROR, "Campaign name cannot be empty!", ButtonType.OK);
            noCampaignName.setHeaderText("No Campaign Name");
            noCampaignName.showAndWait();
        }
        if(errorMessage.equals("Campaign name cannot only be spaces")){
            Alert nameIsSpaces = new Alert(Alert.AlertType.ERROR, "Campaign name cannot only be spaces", ButtonType.OK);
            nameIsSpaces.setHeaderText("Campaign Name is only spaces");
            nameIsSpaces.showAndWait();
        }
        if(errorMessage.equals("Race not selected")) {
            Alert noRaceAlert = new Alert(Alert.AlertType.ERROR, "You must select a Race before trying to create a character", ButtonType.OK);
            noRaceAlert.setHeaderText("No Race");
            noRaceAlert.showAndWait();
        }


        //e.printStackTrace();
    }

    //public void

    public void onAddCharacterToCampaign(ActionEvent actionEvent) {
        try{
            String characterName = getCreatedCharacterName();
            checkCharacterValidity(characterName);
            Character character = new Character(characterName, dndClassName, raceName);
            addDescription(character);
            campaign.addCharacer(character);
            createNewCharacterTab(character.getName(),character);
            displayCharacters();
            Alert characterCreated = new Alert(Alert.AlertType.INFORMATION,characterName+" has been created!");
            characterCreated.setTitle("Character Created");
            characterCreated.setHeaderText("SUCCESS");
            characterCreated.showAndWait();
            initializeCharacterChoices();
            charactersPane.expandedProperty().set(true);
        }catch(MalformedParametersException e){
            showErrorMessage(e.getMessage());

        }catch(IllegalArgumentException e){
            showErrorMessage(e.getMessage());

        }catch(NullPointerException e){
            //This is being caught to avoid redundant alerts for not having inputted a character name.
        }
    }

    private void createNewCharacterTab(String tabName, Character character){
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
        setAdditionalFeatures.clear();
        setAlignment.clear();
        setLanguages.clear();
        setExoticLanguages.clear();
        setPersonalityTrait1.clear();
        setPersonalityTrait2.clear();
        setIdeals.clear();
        setBonds.clear();
        setFlaws.clear();
        characterNameField.clear();
        setWealth.setText("0");
        setXP.setText("0");
        this.raceName = null;
        this.dndClassName = null;
    }

    public void addDescription(Character character){
        String age = (setAge.getText());
        String height = (setHeight.getText());
        String weight = (setWeight.getText());
        String eyeColor = (setEyeColor.getText());
        String skinColor = (setSkinColor.getText());
        String additionalFeatures = (setAdditionalFeatures.getText());
        String alignment = (setAlignment.getText());
        String languages = (setLanguages.getText());
        String exoticLanguages = (setExoticLanguages.getText());
        String personalityTrait1 = (setPersonalityTrait1.getText());
        String personalityTrait2 = (setPersonalityTrait2.getText());
        String ideals = (setIdeals.getText());
        String bonds = (setBonds.getText());
        String flaws = (setFlaws.getText());
        if(setWealth.getText().matches(".*[a-zA-Z]+.*")){
            throw new IllegalArgumentException("Wealth must be an integer value");
        }
        Integer wealth = Integer.parseInt(setWealth.getText());
        character.setWealth(wealth);
        if(setXP.getText().matches(".*[a-zA-Z]+.*")){
            throw new IllegalArgumentException("XP must be an integer value");
        }
        Integer XP = Integer.parseInt(setXP.getText());
        character.setExperiencepoints(XP);
        character.getCharacterDescription().setAllValues(height,weight,age,eyeColor,skinColor,additionalFeatures,alignment,languages,exoticLanguages,personalityTrait1,personalityTrait2,ideals,bonds,flaws);
        }

    private void displayCharacters(){
        ArrayList<String> characters = new ArrayList<>();
        for(int i=0;i<campaign.getCharacters().size();i++){
            characters.add(campaign.getCharacters().get(i).getName());
        }
        ObservableList<String> characterNames = FXCollections.observableArrayList(characters);
        characterList.setItems(characterNames);
    }

    private void setActiveCharacter(String name){
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
        chart.setLegendVisible(false);
        chart.animatedProperty().setValue(true);
        chartPane.setContent(chart);
        chartPane.expandedProperty().setValue(true);



    }

    public void generateCombatOrder(ActionEvent actionEvent){
        try{
            clearCombatOrder(actionEvent);
            ArrayList charactersInCombatOrder = campaign.generateCombatOrder();
            if(charactersInCombatOrder.size()==0){
                throw new IllegalArgumentException("No characters");
            }
            for (int i = 0; i < charactersInCombatOrder.size(); i++) {
                Character character = (Character) charactersInCombatOrder.get(charactersInCombatOrder.size() - i - 1);
                if (!CombatOrderDisplay.getItems().contains(character.getName() + "'s roll for initiative :" + (character.getInitiative()))) {
                    CombatOrderDisplay.getItems().add(character.getName() + "'s roll for initiative :" + (character.getInitiative()));
                }
            }
        }catch(IllegalArgumentException e){
            Alert noCharacters = new Alert(Alert.AlertType.ERROR,"You can't generate a combat order if there are no characters in your campaign");
            noCharacters.setHeaderText("No characters in campaign");
            noCharacters.showAndWait();
        }
    }

    @FXML
    private void clearCombatOrder(ActionEvent actionEvent){
        CombatOrderDisplay.getItems().setAll();
    }

    @FXML
    private void initializeCharacterChoices(){
        ArrayList<Character> characters = this.campaign.getCharacters();
        ArrayList<String> characterNames = new ArrayList<>();
        for (Character character : characters) {
            characterNames.add(character.getName());
        }
        characterChoiceBox.getItems().setAll(characterNames);
    }

    private void initializeTraitChoices(){
        TraitMap traits = new TraitMap();
        ArrayList<String> traitNames = new ArrayList<>();
        for(Map.Entry<String,Integer> entry : traits.getTraitMap().entrySet()){
            traitNames.add(entry.getKey());
        }
        traitChoiceBox.getItems().setAll(traitNames);
    }

    private void initializeDifficultyClasses(){
        ArrayList<String> difficultyClasses = new ArrayList<>();
        ArrayList<Integer> difficultyClassValues = new ArrayList<>();
        difficultyClasses.add("Very Easy");
        difficultyClasses.add("Easy");
        difficultyClasses.add("Medium");
        difficultyClasses.add("Hard");
        difficultyClasses.add("Very Hard");
        difficultyClasses.add("Nearly Impossible");
        difficultyClassValues.add(5);
        difficultyClassValues.add(10);
        difficultyClassValues.add(15);
        difficultyClassValues.add(20);
        difficultyClassValues.add(25);
        difficultyClassValues.add(30);
        difficultyClassComboBox.getItems().setAll(difficultyClasses);
        difficultyClassComboBox.getItems().setAll(difficultyClassValues);
    }

    private void initializeAdvantageStates(){
        ArrayList<String> advantageStates = new ArrayList<>();
        advantageStates.add("Neutral");
        advantageStates.add("Advantage");
        advantageStates.add("Disadvantage");
        ObservableList<String> list = FXCollections.observableArrayList(advantageStates);
        SpinnerValueFactory<String> valueFactory = new SpinnerValueFactory.ListSpinnerValueFactory<>(list);
        advantageSpinner.setValueFactory(valueFactory);
    }

    private Integer rollBasedOnAdvantage(){
        Integer value = 0;
        Die d20 = new Die(20);
        if(advantageSpinner.getValue().equals("Advantage")){
            value = d20.rollDieTwiceUseGreatest();
        }
        if(advantageSpinner.getValue().equals("Neutral")){
            value = d20.rollDie();
        }
        if(advantageSpinner.getValue().equals("Disadvantage")){
            value = d20.rollDieTwiceUseLeast();
        }
        return value;
    }

    private Character getCharacter(String name){
        for(int i=0;i<campaign.getCharacters().size();i++){
            if(campaign.getCharacters().get(i).getName().equals(name)){
                return campaign.getCharacters().get(i);
            }
        }
        return null;
    }

    private String getTraitToCheck(){
        return (String) traitChoiceBox.getItems().get(traitChoiceBox.getSelectionModel().getSelectedIndex());
    }

    private Integer getDifficultyClass(){
        return (Integer) difficultyClassComboBox.getItems().get(difficultyClassComboBox.getSelectionModel().getSelectedIndex());
    }

    public void onTraitCheck(ActionEvent actionEvent){
        try {
            String characterName = (String) characterChoiceBox.getItems().get(characterChoiceBox.getSelectionModel().getSelectedIndex());
            if (characterName.equals("") || characterName.isEmpty()) {
                throw new IllegalArgumentException("No Character Selected");
            }
            Character character = getCharacter(characterName);
            String traitName = getTraitToCheck();
            if(traitName.equals("")||traitName.isEmpty()){
                throw new IllegalArgumentException("No Trait Selected");
            }
            Integer difficultyClass = getDifficultyClass();
            if(difficultyClass == null){
                throw new IllegalArgumentException("No Difficulty Class Selected");
            }
            Integer advantageRoll = rollBasedOnAdvantage();
            if(advantageRoll == null){
                throw new IllegalArgumentException("Advantage Roll Not Performed");
            }
            assert character != null;
            Integer abilityModifier = ((character.getTraits().getValue(traitName) - 10) / 2);
            Integer traitCheckTotal = abilityModifier + advantageRoll;
            if (traitCheckTotal >= difficultyClass) {
                resultWindow.setText("SUCCESS");
                Alert success = new Alert(Alert.AlertType.INFORMATION, characterName + "'s roll of " + advantageRoll +
                        " added to their " + traitName + " modifier of " + abilityModifier +
                        " was greater than or equal to the difficulty class of :" + difficultyClass, ButtonType.OK);
                success.setHeaderText("Success");
                success.showAndWait();
            } else {
                resultWindow.setText("FAILURE");
                Alert failure = new Alert(Alert.AlertType.INFORMATION, characterName + "'s roll of " + advantageRoll +
                        " added to their " + traitName + " modifier of " + abilityModifier +
                        " was less than the difficulty class of :" + difficultyClass, ButtonType.OK);
                failure.setHeaderText("Failure");
                failure.showAndWait();
            }
        }catch(IllegalArgumentException e){
            if(e.getMessage().equals("No Character Selected")){
                Alert noCharacter = new Alert(Alert.AlertType.ERROR,"Can't perform trait check");
                noCharacter.setHeaderText("Please make sure you have selected a character, a trait, a difficulty class, and updated the advantage state to your desired value.");
                noCharacter.showAndWait();
            }
            if(e.getMessage().equals("No Trait Selected")){
                Alert noTrait = new Alert(Alert.AlertType.ERROR,"No trait selected");
                noTrait.setHeaderText("Please select a trait");
                noTrait.showAndWait();
            }
            if(e.getMessage().equals("No Difficulty Class Selected")){
                Alert noDifficultyClass = new Alert(Alert.AlertType.ERROR,"No Difficulty Class selected");
                noDifficultyClass.setHeaderText("Please select a Difficulty Class");
                noDifficultyClass.showAndWait();
            }
            if(e.getMessage().equals("Advantage Roll Not Performed")){
                Alert noRoll = new Alert(Alert.AlertType.ERROR,"No advantage roll performed");
                noRoll.showAndWait();
            }
        }catch(ArrayIndexOutOfBoundsException e){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Please select a character and try again.");
            alert.setHeaderText("No character selected");
            alert.showAndWait();
        }

    }

    @SuppressWarnings("EmptyMethod")
    public void loadOldCampaign(ActionEvent actionEvent) {
    }
}
