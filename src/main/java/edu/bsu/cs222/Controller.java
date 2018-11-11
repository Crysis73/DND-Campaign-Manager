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

@SuppressWarnings({"WeakerAccess", "unchecked", "unused"})
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

    //----------------------- START OF CREATE NEW CAMPAIGN CODE -----------------------------//
    public void checkCampaignNameValidity(String campaignName){
        if(campaignName==null||campaignName.equals("")||campaignName.equals("\\s")){
            throw new IllegalArgumentException("Campaign name is null");
        }
        if(campaignName.trim().isEmpty()){
            throw new IllegalArgumentException("Campaign name cannot ONLY be spaces");
        }
    }

    public void showCampaignNameIsNullAlert(){
        Alert noCampaignName = new Alert(Alert.AlertType.ERROR, "Campaign name cannot be empty!", ButtonType.OK);
        noCampaignName.setHeaderText("No Campaign Name");
        noCampaignName.showAndWait();
    }

    public void showCampaignNameIsOnlySpacesAlert(){
        Alert nameIsSpaces = new Alert(Alert.AlertType.ERROR, "Campaign name cannot only be spaces", ButtonType.OK);
        nameIsSpaces.setHeaderText("Campaign Name is only spaces");
        nameIsSpaces.showAndWait();
    }

    public void showCampaignCreationSuccessfulAlert(){
        Alert campaignCreated = new Alert(Alert.AlertType.CONFIRMATION, "Campaign " + campaign.getCampaignName() + " has been created", ButtonType.OK);
        campaignCreated.showAndWait();
    }

    public void onCreateNewCampaign(ActionEvent actionEvent) {
        try {
            String campaignName = inputCampaignText.getText();
            checkCampaignNameValidity(campaignName);
            this.campaign = new Campaign();
            campaign.setCampaignName(campaignName);
            showCampaignCreationSuccessfulAlert();
            TabPane.getSelectionModel().select(CampaignViewTab);
            initializeTraitChoices();
            initializeDifficultyClasses();
            initializeAdvantageStates();
            CampaignViewTab.disableProperty().setValue(false);
            CharacterCreatorTab.disableProperty().set(false);
        }catch(IllegalArgumentException e){
            if(e.getMessage().equals("Campaign name is null")) {
                showCampaignNameIsNullAlert();
            }
            if(e.getMessage().equals("Campaign name cannot ONLY be spaces")){
                showCharacterNameIsOnlySpacesAlert();
            }
        }
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
        for(int i=5;i<35;i+=5){
            difficultyClassValues.add(i);
        }
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

    //--------------------- END OF CREATE NEW CAMPAIGN CODE ----------------------//

    //---------------------- START OF ADD CHARACTER TO CAMPAIGN CODE ----------------------------//
    public void checkCharacterNameValidity(String name){
        if(name==null || name.equals("")){
            throw new IllegalArgumentException("Name cannot be empty!");
        }
    }

    public void showCharacterNameIsEmptyAlert(){
        Alert noNameAlert = new Alert(Alert.AlertType.ERROR,"Character name cannot be empty!",ButtonType.OK);
        noNameAlert.setHeaderText("No Character Name");
        noNameAlert.showAndWait();
    }

    private String getCreatedCharacterName(){
        try{
            String name = characterNameField.getText();
            checkCharacterNameValidity(name);
            return name;
        } catch(IllegalArgumentException e){
            showCharacterNameIsEmptyAlert();
        }
        return null;
    }

    public void checkCharacterValidity(String characterName){
        if(characterName ==null || characterName.equals("")){
            throw new NullPointerException("Character name is empty");
        }
        if(characterName.trim().isEmpty()){
            throw new IllegalArgumentException("Character name cannot ONLY be spaces");
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

    public void showNoRaceAlert(){
        Alert noRaceAlert = new Alert(Alert.AlertType.ERROR,"You must select a Race before trying to create a character",ButtonType.OK);
        noRaceAlert.setHeaderText("No Race");
        noRaceAlert.showAndWait();
    }

    public void showExistingCharacterAlert(){
        Alert existingCharacter = new Alert(Alert.AlertType.ERROR,"A character with that name already exists!");
        existingCharacter.setHeaderText("Existing Character");
        existingCharacter.showAndWait();
    }

    public void showNonIntegerWealthAlert(){
        Alert wealthNonInteger = new Alert(Alert.AlertType.ERROR,"Wealth must be an integer value");
        wealthNonInteger.setHeaderText("Non Integer Wealth");
        wealthNonInteger.show();
    }

    public void showNonIntegerXPAlert(){
        Alert XPNonInteger = new Alert(Alert.AlertType.ERROR,"XP must be an integer value");
        XPNonInteger.setHeaderText("Non Integer XP");
        XPNonInteger.showAndWait();
    }

    public void showNoClassAlert(){
        Alert noClassAlert = new Alert(Alert.AlertType.ERROR, "You must select a Class before trying to create a Character");
        noClassAlert.setHeaderText("No Class");
        noClassAlert.showAndWait();
    }

    public void showCharacterNameIsOnlySpacesAlert(){
        Alert nameIsSpace = new Alert(Alert.AlertType.ERROR,"Character name cannot only be spaces");
        nameIsSpace.setHeaderText("Name is only spaces");
        nameIsSpace.showAndWait();
    }

    public void showCharacterCreationSuccessAlert(String characterName){
        Alert characterCreated = new Alert(Alert.AlertType.INFORMATION,characterName+" has been created!");
        characterCreated.setTitle("Character Created");
        characterCreated.setHeaderText("SUCCESS");
        characterCreated.showAndWait();
    }

    public void chooseCampaignAlertToDisplay(IllegalArgumentException e){
        if(e.getMessage().equals("Character already exists!")){
            showExistingCharacterAlert();
        }
        if(e.getMessage().equals("Wealth must be an integer value")){
            showNonIntegerWealthAlert();
        }
        if(e.getMessage().equals("XP must be an integer value")){
            showNonIntegerXPAlert();
        }
        if(e.getMessage().equals("Class not selected")){
            showNoClassAlert();
        }
        if(e.getMessage().equals("Character name cannot ONLY be spaces")){
            showCharacterNameIsOnlySpacesAlert();
        }
    }

    public void setRaceName(ActionEvent actionEvent){
        MenuItem item = (MenuItem) actionEvent.getSource();
        this.raceName = item.getText();
    }

    public void setClassName(ActionEvent actionEvent){
        MenuItem item = (MenuItem) actionEvent.getSource();
        this.dndClassName = item.getText();
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
        character.getCharacterDescription().setAllValues(height,weight,age,eyeColor,skinColor,additionalFeatures,alignment,
                languages,exoticLanguages,personalityTrait1,personalityTrait2,ideals,bonds,flaws);
    }

    public void onAddCharacterToCampaign(ActionEvent actionEvent) {
        try{
            String characterName = getCreatedCharacterName();
            checkCharacterValidity(characterName);
            Character character = new Character(characterName, dndClassName, raceName);
            addDescription(character);
            campaign.addCharacer(character);
            createNewCharacterTab(character.getName(),character);
            displayCharacters();
            showCharacterCreationSuccessAlert(characterName);
            initializeCharacterChoices();
            charactersPane.expandedProperty().set(true);
        }catch(MalformedParametersException e){
            showNoRaceAlert();
        }catch(IllegalArgumentException e){
            chooseCampaignAlertToDisplay(e);
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

    private void displayCharacters(){
        ArrayList<String> characters = new ArrayList<>();
        for(int i=0;i<campaign.getCharacters().size();i++){
            characters.add(campaign.getCharacters().get(i).getName());
        }
        ObservableList<String> characterNames = FXCollections.observableArrayList(characters);
        characterList.setItems(characterNames);
    }
    //--------------------- END OF ADD CHARACTER TO CAMPAIGN CODE-------------------------//

    //------------------- TRAIT CHART CODE --------------------------//
    private void setActiveCharacter(String name){
        for(int i =0;i<campaign.getCharacters().size();i++){
            if(campaign.getCharacters().get(i).getName().equals(name)){
                this.activeCharacter = campaign.getCharacters().get(i);
            }
        }
    }

    public void checkChartCharacterValidity(String characterName){
        if(characterName.trim().isEmpty() || characterName.equals("")){
            throw new IllegalArgumentException("No character selected");
        }
    }

    public void showNoCharacterSelectedForChart(){
        Alert noCharacterForChart = new Alert(Alert.AlertType.ERROR,"No character selected for chart");
        noCharacterForChart.setHeaderText("No character");
        noCharacterForChart.showAndWait();
    }

    public ObservableList<XYChart.Series<String,Integer>> generateCharacterChartData(MouseEvent mouseEvent){
        try {
            ListView view = (ListView) mouseEvent.getSource();
            ObservableList activeCharacterName = view.getSelectionModel().getSelectedItems();
            String characterName = activeCharacterName.toString().replace("[", "").replace("]", "");
            checkChartCharacterValidity(characterName);
            setActiveCharacter(characterName);
            final XYChart.Series<String, Integer> series = new XYChart.Series<>();
            final Map<String, Integer> characterTraits = activeCharacter.getTraits().getTraitMap();
            series.setName(activeCharacter.getName() + "'s Traits");
            for (final Map.Entry<String, Integer> entry : characterTraits.entrySet()) {
                series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
            }
            final ObservableList<XYChart.Series<String, Integer>> chartData = FXCollections.observableArrayList();
            chartData.add(series);
            return chartData;
        }catch(IllegalArgumentException e){
            showNoCharacterSelectedForChart();
        }
        return null;
    }

    public void createChart(MouseEvent mouseEvent) {
        try {
            ObservableList<XYChart.Series<String, Integer>> chartData = generateCharacterChartData(mouseEvent);
            ObservableList<String> traitNames = FXCollections.observableArrayList(activeCharacter.getTraits().getTraitMap().keySet());
            CategoryAxis xAxis = new CategoryAxis();
            Axis<? extends Number> yAxis = new NumberAxis();
            xAxis.setCategories(traitNames);
            BarChart<String, Integer> chart = new BarChart<>(xAxis, (Axis<Integer>) yAxis);
            chart.getData().addAll(chartData);
            yAxis.setAutoRanging(false);
            ((NumberAxis) yAxis).setLowerBound(0);
            ((NumberAxis) yAxis).setUpperBound(20);
            chart.setLegendSide(Side.TOP);
            chart.setLegendVisible(false);
            chart.animatedProperty().setValue(true);
            chartPane.setContent(chart);
            chartPane.expandedProperty().setValue(true);
        } catch(NullPointerException e){
            Alert noCharacter = new Alert(Alert.AlertType.ERROR,"No characters are in your campaign yet");
            noCharacter.showAndWait();
        }
    }
    //------------------------ END OF TRAIT CHART CODE -------------------------//

    //------------------- START OF COMBAT ORDER CODE ---------------------//
    public void showNoCharactersAlert(){
        Alert noCharacters = new Alert(Alert.AlertType.ERROR,"You can't generate a combat order if there are no characters in your campaign");
        noCharacters.setHeaderText("No characters in campaign");
        noCharacters.showAndWait();
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
            showNoCharactersAlert();
        }
    }

    @FXML
    private void clearCombatOrder(ActionEvent actionEvent){
        CombatOrderDisplay.getItems().setAll();
    }
    //----------------- END OF COMBAT ORDER CODE --------------------------//

    //--------------------- START OF TRAIT CHECK CODE ---------------------------//
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

    public void checkTraitCheckValidity(String characterName, String traitName, Integer difficultyClass, Integer advantageRoll){
        if (characterName.equals("") || characterName.isEmpty()) {
            throw new IllegalArgumentException("No Character Selected");
        }
        if(traitName.equals("")||traitName.isEmpty()){
            throw new IllegalArgumentException("No Trait Selected");
        }
        if(difficultyClass == null){
            throw new IllegalArgumentException("No Difficulty Class Selected");
        }
        if(advantageRoll == null){
            throw new IllegalArgumentException("Advantage Roll Not Performed");
        }
    }

    public void showTraitCheckSuccessful(String characterName, Integer advantageRoll, String traitName, Integer abilityModifier, Integer difficultyClass){
        resultWindow.setText("SUCCESS");
        Alert success = new Alert(Alert.AlertType.INFORMATION, characterName + "'s roll of " + advantageRoll +
                " added to their " + traitName + " modifier of " + abilityModifier +
                " was greater than or equal to the difficulty class of :" + difficultyClass, ButtonType.OK);
        success.setHeaderText("Success");
        success.showAndWait();
    }

    public void showTraitCheckFailure(String characterName, Integer advantageRoll, String traitName, Integer abilityModifier, Integer difficultyClass){
        resultWindow.setText("FAILURE");
        Alert failure = new Alert(Alert.AlertType.INFORMATION, characterName + "'s roll of " + advantageRoll +
                " added to their " + traitName + " modifier of " + abilityModifier +
                " was less than the difficulty class of :" + difficultyClass, ButtonType.OK);
        failure.setHeaderText("Failure");
        failure.showAndWait();
    }

    public void showNoCharacterSelectedAlert(){
        Alert noCharacter = new Alert(Alert.AlertType.ERROR,"Can't perform trait check");
        noCharacter.setHeaderText("No character selected");
        noCharacter.showAndWait();
    }

    public void showNoTraitSelectedAlert(){
        Alert noTrait = new Alert(Alert.AlertType.ERROR,"No trait selected");
        noTrait.setHeaderText("Please select a trait");
        noTrait.showAndWait();
    }

    public void showNoDifficultyClassSelected(){
        Alert noDifficultyClass = new Alert(Alert.AlertType.ERROR,"No Difficulty Class selected");
        noDifficultyClass.setHeaderText("Please select a Difficulty Class");
        noDifficultyClass.showAndWait();
    }

    public void showNoRollPerformedAlert(){
        Alert noRoll = new Alert(Alert.AlertType.ERROR,"No advantage roll performed");
        noRoll.showAndWait();
    }

    public void showUnableToPerformTraitCheckAlert(){
        Alert alert = new Alert(Alert.AlertType.ERROR,"Please make sure you have selected a character, a trait, a difficulty class, and updated the advantage state to your desired value.");
        alert.setHeaderText("Unable to perform trait check");
        alert.showAndWait();
    }

    public void chooseTraitCheckAlertToShow(IllegalArgumentException e){
        if(e.getMessage().equals("No Character Selected")){
            showNoCharacterSelectedAlert();
        }
        if(e.getMessage().equals("No Trait Selected")){
            showNoTraitSelectedAlert();
        }
        if(e.getMessage().equals("No Difficulty Class Selected")){
            showNoDifficultyClassSelected();
        }
        if(e.getMessage().equals("Advantage Roll Not Performed")){
            showNoRollPerformedAlert();
        }
    }

    public void onTraitCheck(ActionEvent actionEvent){
        try {
            String characterName = (String) characterChoiceBox.getItems().get(characterChoiceBox.getSelectionModel().getSelectedIndex());
            Character character = getCharacter(characterName);
            String traitName = getTraitToCheck();
            Integer difficultyClass = getDifficultyClass();
            Integer advantageRoll = rollBasedOnAdvantage();
            checkTraitCheckValidity(characterName,traitName,difficultyClass,advantageRoll);
            assert character != null;
            Integer abilityModifier = ((character.getTraits().getValue(traitName) - 10) / 2);
            Integer traitCheckTotal = abilityModifier + advantageRoll;
            if (traitCheckTotal >= difficultyClass) {
                showTraitCheckSuccessful(characterName,advantageRoll,traitName,abilityModifier,difficultyClass);
            } else {
                showTraitCheckFailure(characterName,advantageRoll,traitName,abilityModifier,difficultyClass);
            }
        }catch(IllegalArgumentException e){
            chooseTraitCheckAlertToShow(e);
        }catch(ArrayIndexOutOfBoundsException e){
            showUnableToPerformTraitCheckAlert();
        }

    }
    //---------------------- END OF TRAIT CHECK CODE ------------------------------//

    @SuppressWarnings("EmptyMethod")
    public void loadOldCampaign(ActionEvent actionEvent) {
    }
}
