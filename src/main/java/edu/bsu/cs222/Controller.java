//package edu.bsu.cs222;
//
//import javafx.application.Application;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Scene;
//import javafx.scene.chart.*;
//import javafx.scene.control.*;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.layout.*;
//
//import javafx.stage.Stage;
//import javafx.util.StringConverter;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Map;
//
//@SuppressWarnings({"WeakerAccess", "unchecked", "unused"})
//public class Controller extends Application {
//    @FXML
//    private TabPane TabPane;
//    @FXML
//    private Tab CampaignViewTab,CharacterCreatorTab;
//    @FXML
//    private TitledPane charactersPane, chartTitledPane;
//
//
//
//    private String raceName,dndClassName;
//    private Campaign campaign;
//    private Character activeCharacter;
//
//    public static void main(String[] args) {
//        Application.launch(args);
//    }
//
//    @Override
//    public void start(Stage stage) throws IOException {
//        Pane root = FXMLLoader.load(getClass().getResource("/main.fxml"));
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//        stage.setTitle("D&D Game Master Thingy 3000");
//        stage.show();
//    }
//
//    //----------------------- START OF CREATE NEW CAMPAIGN CODE -----------------------------//
//
//
//
//
//
//
//    //--------------------- END OF CREATE NEW CAMPAIGN CODE ----------------------//
//
//    //---------------------- START OF ADD CHARACTER TO CAMPAIGN CODE ----------------------------//
////    public void showCharacterCreationSuccessAlert(String characterName){
////        Alert characterCreated = new Alert(Alert.AlertType.INFORMATION,characterName+" has been created!");
////        characterCreated.setTitle("Character Created");
////        characterCreated.setHeaderText("SUCCESS");
////        characterCreated.showAndWait();
////    }
////
////    public void setRaceName(){
////        Race race =(Race)raceComboBox.getItems().get(raceComboBox.getSelectionModel().getSelectedIndex());
////        this.raceName = race.getName();
////    }
////
////    public void setClassName(){
////        dndClass dndClass =(dndClass) dndClassComboBox.getItems().get(dndClassComboBox.getSelectionModel().getSelectedIndex());
////        this.dndClassName = dndClass.getName();
////    }
////
////
////    public void addDescription(Character character){
////        String age = (setAge.getText());
////        String height = (setHeight.getText());
////        String weight = (setWeight.getText());
////        String eyeColor = (setEyeColor.getText());
////        String skinColor = (setSkinColor.getText());
////        String additionalFeatures = (setAdditionalFeatures.getText());
////        String alignment = (setAlignment.getText());
////        String languages = (setLanguages.getText());
////        String exoticLanguages = (setExoticLanguages.getText());
////        String personalityTrait1 = (setPersonalityTrait1.getText());
////        String personalityTrait2 = (setPersonalityTrait2.getText());
////        String ideals = (setIdeals.getText());
////        String bonds = (setBonds.getText());
////        String flaws = (setFlaws.getText());
////        Integer wealth = Integer.parseInt(setWealth.getText());
////        character.setWealth(wealth);
////        Integer XP = Integer.parseInt(setXP.getText());
////        character.setExperiencepoints(XP);
////        character.getCharacterDescription().setAllValues(height,weight,age,eyeColor,skinColor,additionalFeatures,alignment,
////                languages,exoticLanguages,personalityTrait1,personalityTrait2,ideals,bonds,flaws);
////    }
////
////    public boolean containsCharacter(String characterName){
////        for(int i =0;i<campaign.getCharacters().size();i++){
////            if(characterName.equals(campaign.getCharacters().get(i).getName().trim())){
////                return true;
////            }
////        }
////        return false;
////    }
////
////    public boolean validateCharacter(){
////        StringBuilder characterErrors = new StringBuilder();
////        if(characterNameField.getText() == null||characterNameField.getText().trim().isEmpty()){
////            characterErrors.append(" - Please add a valid character name . \n");
////        }
////        if(containsCharacter(characterNameField.getText().trim())){
////            characterErrors.append(" - This character already exists. Please input a new character name. \n");
////        }
////        if(raceComboBox.getSelectionModel().getSelectedIndex() ==-1){
////            characterErrors.append(" - Please choose a race for your character from the menu. \n");
////        }
////        if(dndClassComboBox.getSelectionModel().getSelectedIndex() == -1){
////            characterErrors.append(" - Please choose a DnD class for your character from the menu. \n");
////        }
////        if(setWealth.getText().matches(".*[a-zA-Z]+.*")){
////            characterErrors.append(" - Please use integers for character wealth.");
////        }
////        if(setXP.getText().matches(".*[a-zA-Z]+.*")){
////            characterErrors.append(" - Please use integers for character XP.");
////        }
////        if(characterErrors.length()>0){
////            Alert characterAlert = new Alert(Alert.AlertType.ERROR, characterErrors.toString(),ButtonType.OK);
////            characterAlert.setHeaderText("Invalid Character Information");
////            characterAlert.showAndWait();
////            return false;
////        }
////        return true;
////    }
////
////
////    public void onAddCharacterToCampaign(ActionEvent actionEvent) {
////        if(validateCharacter()){
////            setRaceName();
////            setClassName();
////            String characterName = characterNameField.getText().trim();
////            Character character = new Character(characterName, dndClassName,raceName);
////            addDescription(character);
////            campaign.addCharacer(character);
////            createNewCharacterTab(character.getName(),character);
////            displayCharacters();
////            initializeCharacterChoices();
////            showCharacterCreationSuccessAlert(characterName);
////            charactersPane.expandedProperty().set(true);
////        }
////
////    }
//
//
////    private void createNewCharacterTab(String tabName, Character character){
////        Tab tab = new Tab(tabName);
////        CharacterTabController characterTabController = new CharacterTabController(character.getName());
////        AnchorPane anchorPane = new AnchorPane();
////        try {
////            anchorPane = FXMLLoader.load(getClass().getResource("/CharacterTab.fxml"));
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////        tab.setContent(anchorPane);
////        TabPane.getTabs().add(tab);
////    }
//
////    public void clearAllNewCharacter(ActionEvent actionEvent){
////        raceComboBox.getSelectionModel().clearSelection();
////        dndClassComboBox.getSelectionModel().clearSelection();
////        setHeight.clear();
////        setWeight.clear();
////        setAge.clear();
////        setEyeColor.clear();
////        setSkinColor.clear();
////        setAdditionalFeatures.clear();
////        setAlignment.clear();
////        setLanguages.clear();
////        setExoticLanguages.clear();
////        setPersonalityTrait1.clear();
////        setPersonalityTrait2.clear();
////        setIdeals.clear();
////        setBonds.clear();
////        setFlaws.clear();
////        characterNameField.clear();
////        setWealth.setText("0");
////        setXP.setText("0");
////        this.raceName = null;
////        this.dndClassName = null;
////    }
//
////    private void displayCharacters(){
////        ArrayList<String> characters = new ArrayList<>();
////        for(int i=0;i<campaign.getCharacters().size();i++){
////            characters.add(campaign.getCharacters().get(i).getName());
////        }
////        ObservableList<String> characterNames = FXCollections.observableArrayList(characters);
////        characterList.setItems(characterNames);
////    }
//    //--------------------- END OF ADD CHARACTER TO CAMPAIGN CODE-------------------------//
//
//    //------------------- TRAIT CHART CODE --------------------------//
////    private void setActiveCharacter(String name){
////        for(int i =0;i<campaign.getCharacters().size();i++){
////            if(campaign.getCharacters().get(i).getName().equals(name)){
////                this.activeCharacter = campaign.getCharacters().get(i);
////            }
////        }
////    }
////
////    private boolean validateChartCharacter(){
////        StringBuilder chartErrors = new StringBuilder();
////        if(campaign.getCharacters().size()==0){
////            chartErrors.append(" - Please add a character to your campaign.\n");
////        }
////        if(chartErrors.length()>0){
////            Alert chartAlert = new Alert(Alert.AlertType.ERROR, chartErrors.toString(),ButtonType.OK);
////            chartAlert.setHeaderText("Invalid Chart Information");
////            chartAlert.showAndWait();
////            return false;
////        }
////        return true;
////    }
////
////    public void createChart(MouseEvent mouseEvent) {
////        if(validateChartCharacter() && campaign.getCharacters().size()!=0) {
////            ObservableList activeCharacterName = characterList.getSelectionModel().getSelectedItems();
////            String characterName = activeCharacterName.toString().replace("[", "").replace("]", "");
////            setActiveCharacter(characterName);
////            ChartCreator chartCreator = new ChartCreator(activeCharacter.getTraits().getTraitMap());
////            ObservableList<String> traitNames = FXCollections.observableArrayList(activeCharacter.getTraits().getTraitMap().keySet());
////            BarChart chart = chartCreator.createChart(traitNames,characterName);
////            chartPane.getChildren().clear();
////            chartPane.getChildren().add(chart);
////            chartTitledPane.expandedProperty().setValue(true);
////            setHpProgressBar();
////        }
////    }
////    //------------------------ END OF TRAIT CHART CODE -------------------------//
////
////    //------------------- START OF COMBAT ORDER CODE ---------------------//
////    private boolean validateCombatRoll(){
////        StringBuilder combatOrderErrors = new StringBuilder();
////        if(campaign.getCharacters().size()==0){
////            combatOrderErrors.append(" - Please add characters to your campaign before generating a combat order.");
////        }
////        if(combatOrderErrors.length()>0){
////            Alert combatOrderAlert = new Alert(Alert.AlertType.ERROR, combatOrderErrors.toString(),ButtonType.OK);
////            combatOrderAlert.setHeaderText("Invalid Combat Order Information");
////            combatOrderAlert.showAndWait();
////            return false;
////        }
////        return true;
////
////    }
////
////    public void generateCombatOrder(ActionEvent actionEvent){
////        clearCombatOrder(actionEvent);
////        ArrayList charactersInCombatOrder = campaign.generateCombatOrder();
////        if(validateCombatRoll()){
////            for (int i = 0; i < charactersInCombatOrder.size(); i++) {
////                Character character = (Character) charactersInCombatOrder.get(charactersInCombatOrder.size() - i - 1);
////                if (!CombatOrderDisplay.getItems().contains(character.getName() + "'s roll for initiative :" + (character.getInitiative()))) {
////                    CombatOrderDisplay.getItems().add(character.getName() + "'s roll for initiative :" + (character.getInitiative()));
////                }
////            }
////        }
////    }
////
////    @FXML
////    private void clearCombatOrder(ActionEvent actionEvent){
////        CombatOrderDisplay.getItems().clear();
////    }
//    //----------------- END OF COMBAT ORDER CODE --------------------------//
//
////    //--------------------- START OF TRAIT CHECK CODE ---------------------------//
////    private Character getCharacterToCheck(){
////        String characterName = (String) characterChoiceBox.getItems().get(characterChoiceBox.getSelectionModel().getSelectedIndex());
////        for(int i=0;i<campaign.getCharacters().size();i++){
////            if(campaign.getCharacters().get(i).getName().equals(characterName)){
////                return campaign.getCharacters().get(i);
////            }
////        }
////        return null;
////    }
////
////    private String getTraitToCheck(){
////        return (String) traitChoiceBox.getItems().get(traitChoiceBox.getSelectionModel().getSelectedIndex());
////    }
////
////    private DifficultyClass getDifficultyClass(){
////        return (DifficultyClass) difficultyClassChoiceBox.getItems().get(difficultyClassChoiceBox.getSelectionModel().getSelectedIndex());
////    }
////
////    public void showTraitCheckSuccessful(TraitCheck traitCheck){
////        resultWindow.setText("SUCCESS");
////        Alert success = new Alert(Alert.AlertType.INFORMATION, traitCheck.getCharacterName() + "'s roll of " + traitCheck.getAdvantageRoll() +
////                " added to their " + traitCheck.getTraitNameChecked() + " modifier of " + traitCheck.getAbilityModifier() +
////                " was greater than or equal to the difficulty class of : \"" + traitCheck.getDifficultyClass() + "\"", ButtonType.OK);
////        success.setHeaderText("Success");
////        success.showAndWait();
////    }
////
////    public void showTraitCheckFailure(TraitCheck traitCheck){
////        resultWindow.setText("FAILURE");
////        Alert failure = new Alert(Alert.AlertType.INFORMATION, traitCheck.getCharacterName() + "'s roll of " + traitCheck.getAdvantageRoll() +
////                " added to their " + traitCheck.getTraitNameChecked() + " modifier of " + traitCheck.getAbilityModifier() +
////                " was less than the difficulty class of : \"" + traitCheck.getDifficultyClass() + "\"", ButtonType.OK);
////        failure.setHeaderText("Failure");
////        failure.showAndWait();
////    }
////
////    private boolean validateTraitCheck(){
////        StringBuilder traitCheckErrors = new StringBuilder();
////        if(characterChoiceBox.getSelectionModel().isEmpty()){
////            traitCheckErrors.append(" - Please choose a character to perform a trait check. \n");
////        }
////        if(traitChoiceBox.getSelectionModel().isEmpty()){
////            traitCheckErrors.append(" - Please choose a trait to perform a trait check. \n");
////        }
////        if(difficultyClassChoiceBox.getSelectionModel().isEmpty()){
////            traitCheckErrors.append(" - Please choose a difficulty class to perform a trait check. \n");
////        }
////        if(traitCheckErrors.length()>0){
////            Alert characterAlert = new Alert(Alert.AlertType.ERROR, traitCheckErrors.toString(),ButtonType.OK);
////            characterAlert.setHeaderText("Invalid Trait Check Information");
////            characterAlert.showAndWait();
////            return false;
////        }
////        return true;
////    }
////
////    public void onTraitCheck(ActionEvent actionEvent){
////        if(validateTraitCheck()){
////            TraitCheck traitCheck = new TraitCheck(getCharacterToCheck(), getTraitToCheck(), getDifficultyClass(), advantageSpinner.getValue());
////            if(traitCheck.getTraitCheckResult()){
////                showTraitCheckSuccessful(traitCheck);
////            }
////            else{
////                showTraitCheckFailure(traitCheck);
////            }
////        }
////    }
////    //---------------------- END OF TRAIT CHECK CODE ------------------------------//
//
////    public void refreshProgram(){
////        raceComboBox.getItems().clear();
////        dndClassComboBox.getItems().clear();
////        CombatOrderDisplay.getItems().clear();
////        hpVBox.getChildren().clear();
////        chartPane.getChildren().clear();
////        characterList.getItems().clear();
////        for(int i=0;i<TabPane.getTabs().size();i++){
////            if(!(TabPane.getTabs().get(i).getText().equals("Campaign") || TabPane.getTabs().get(i).getText().equals("Campaign View") || TabPane.getTabs().get(i).getText().equals("Character Creator")) ) {
////                TabPane.getTabs().remove(i,TabPane.getTabs().size());
////            }
////        }
////    }
//
//    public void loadOldCampaign(ActionEvent actionEvent) {
//        refreshProgram();
//        JsonLoader loader = new JsonLoader();
//        Campaign campaign = loader.fromJsontoCampaign("myCampaign.json");
//        this.campaign = campaign;
//        for(int j=0;j<campaign.getCharacters().size();j++){
//            if(!campaign.getCharacters().get(j).getName().equals(TabPane.getTabs().get(j).getText())) {
//                createNewCharacterTab(campaign.getCharacters().get(j).getName(), campaign.getCharacters().get(j));
//            }
//        }
//        clearCombatOrder(actionEvent);
//        CharacterCreatorTab.setDisable(false);
//        CampaignViewTab.setDisable(false);
//        mainTabPane.getSelectionModel().select(CampaignViewTab);
//        displayCharacters();
//        initializeCharacterChoices();
//        initializeTraitChoices();
//        initializeDifficultyClasses();
//        initializeAdvantageStates();
//        initializeDndClassChoices();
//        initializeRaceChoices();
//        charactersPane.setExpanded(true);
//        chartTitledPane.setExpanded(true);
//        chartPane.getChildren().clear();
//        clearAllNewCharacter(actionEvent);
//    }
//
//    //---------------------------- END LOAD CAMPAIGN CODE --------------------------------//
//
////    //----------------------------- START PROGRESS BAR CODE -------------------------------------------//
////    public boolean validateHPIncrement(){
////        if(activeCharacter!=null){
////            return !activeCharacter.getCurrentHitPoints().equals(activeCharacter.getMaxHitPoints());
////        }else{
////            return false;
////        }
////    }
////    public boolean validateHPDecrement(){
////        if(activeCharacter!=null) {
////            return activeCharacter.getCurrentHitPoints() != 0;
////        }else{
////            return false;
////        }
////    }
////    public void setHpProgressBar(){
////        hpVBox.getChildren().clear();
////        Label label = new Label("HP :"+activeCharacter.getCurrentHitPoints() +"/"+activeCharacter.getMaxHitPoints());
////        hpVBox.getChildren().add(label);
////        float progress = (float)activeCharacter.getCurrentHitPoints() / activeCharacter.getMaxHitPoints();
////        ProgressBar progressBar = new ProgressBar(progress);
////        hpVBox.getChildren().add(progressBar);
////    }
////
////    public void incrementActiveCharacterHP(ActionEvent actionEvent){
////        if(validateHPIncrement()) {
////            activeCharacter.incrementCurrentHP();
////            setHpProgressBar();
////        }
////    }
////
////    public void decrementActiveCharacterHP(ActionEvent actionEvent){
////        if(validateHPDecrement()) {
////            activeCharacter.decrementCurrentHP();
////            setHpProgressBar();
////        }
////    }
//
//}