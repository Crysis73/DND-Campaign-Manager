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
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

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

    public void showCampaignCreationSuccessfulAlert(){
        Alert campaignCreated = new Alert(Alert.AlertType.CONFIRMATION, "Campaign " + campaign.getCampaignName() + " has been created", ButtonType.OK);
        campaignCreated.showAndWait();
    }

    public boolean validateNewCampaign(){
        StringBuilder campaignErrors = new StringBuilder();
        if(inputCampaignText.getText().trim().isEmpty()){
            campaignErrors.append("- Please input a campaign name. \n");
        }
        if(campaignErrors.length()>0){
            Alert campaignAlert = new Alert(Alert.AlertType.ERROR, campaignErrors.toString(),ButtonType.OK);
            campaignAlert.setHeaderText("Invalid Campaign Information");
            campaignAlert.showAndWait();
            return false;
        }
        return true;
    }

    public void onCreateNewCampaign(ActionEvent actionEvent) {
        if(validateNewCampaign()){
            this.campaign = new Campaign(inputCampaignText.getText());
            showCampaignCreationSuccessfulAlert();
            TabPane.getSelectionModel().select(CampaignViewTab);
            initializeTraitChoices();
            initializeAdvantageStates();
            initializeDifficultyClasses();
            CampaignViewTab.disableProperty().setValue(false);
            CharacterCreatorTab.disableProperty().setValue(false);
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
        String[] difficultyClassNames = {"Very Easy","Easy","Medium","Hard","Very Hard","Nearly Impossible"};
        ArrayList<Integer> difficultyClassValues = new ArrayList<>();
        for(int i=5;i<35;i+=5){
           difficultyClassValues.add(i);
        }
        ArrayList<DifficultyClass> difficultyClasses = new ArrayList<>();
        for(int i=0;i<difficultyClassNames.length;i++){
            DifficultyClass difficultyClass = new DifficultyClass(difficultyClassNames[i],difficultyClassValues.get(i));
            difficultyClasses.add(difficultyClass);
        }
        difficultyClassComboBox.getItems().setAll(difficultyClasses);
    }

    private void initializeAdvantageStates(){
        String[] advantageStates = {"Neutral","Advantage","Disadvantage"};
        ObservableList<String> list = FXCollections.observableArrayList(advantageStates);
        SpinnerValueFactory<String> valueFactory = new SpinnerValueFactory.ListSpinnerValueFactory<>(list);
        advantageSpinner.setValueFactory(valueFactory);
    }

    //--------------------- END OF CREATE NEW CAMPAIGN CODE ----------------------//

    //---------------------- START OF ADD CHARACTER TO CAMPAIGN CODE ----------------------------//


    public void showCharacterCreationSuccessAlert(String characterName){
        Alert characterCreated = new Alert(Alert.AlertType.INFORMATION,characterName+" has been created!");
        characterCreated.setTitle("Character Created");
        characterCreated.setHeaderText("SUCCESS");
        characterCreated.showAndWait();
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
        Integer wealth = Integer.parseInt(setWealth.getText());
        character.setWealth(wealth);
        Integer XP = Integer.parseInt(setXP.getText());
        character.setExperiencepoints(XP);
        character.getCharacterDescription().setAllValues(height,weight,age,eyeColor,skinColor,additionalFeatures,alignment,
                languages,exoticLanguages,personalityTrait1,personalityTrait2,ideals,bonds,flaws);
    }

    public boolean containsCharacter(String characterName){
        for(int i =0;i<campaign.getCharacters().size();i++){
            if(characterName.equals(campaign.getCharacters().get(i).getName().trim())){
                return true;
            }
        }
        return false;
    }

    public boolean validateCharacter(){
        StringBuilder characterErrors = new StringBuilder();
        if(characterNameField.getText() == null||characterNameField.getText().trim().isEmpty()){
            characterErrors.append(" - Please add a valid character name . \n");
        }
        if(containsCharacter(characterNameField.getText().trim())){
            characterErrors.append(" - This character already exists. Please input a new character name. \n");
        }
        if(raceName==null){
            characterErrors.append(" - Please choose a race for your character from the menu. \n");
        }
        if(dndClassName==null){
            characterErrors.append(" - Please choose a DnD class for your character from the menu. \n");
        }
        if(setWealth.getText().matches(".*[a-zA-Z]+.*")){
            characterErrors.append(" - Please use integers for character wealth.");
        }
        if(setXP.getText().matches(".*[a-zA-Z]+.*")){
            characterErrors.append(" - Please use integers for character XP.");
        }
        if(characterErrors.length()>0){
            Alert characterAlert = new Alert(Alert.AlertType.ERROR, characterErrors.toString(),ButtonType.OK);
            characterAlert.setHeaderText("Invalid Character Information");
            characterAlert.showAndWait();
            return false;
        }
        return true;
    }

    public void onAddCharacterToCampaign(ActionEvent actionEvent) {
        if(validateCharacter()){
            String characterName = characterNameField.getText().trim();
            Character character = new Character(characterName, dndClassName,raceName);
            addDescription(character);
            campaign.addCharacer(character);
            createNewCharacterTab(character.getName(),character);
            displayCharacters();
            initializeCharacterChoices();
            showCharacterCreationSuccessAlert(characterName);
            charactersPane.expandedProperty().set(true);
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

    private boolean validateChartCharacter(){
        StringBuilder chartErrors = new StringBuilder();
        if(campaign.getCharacters().size()==0){
            chartErrors.append(" - Please add a character to your campaign.\n");
        }

        if(chartErrors.length()>0){
            Alert chartAlert = new Alert(Alert.AlertType.ERROR, chartErrors.toString(),ButtonType.OK);
            chartAlert.setHeaderText("Invalid Chart Information");
            chartAlert.showAndWait();
            return false;
        }

    return true;
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
        if(validateChartCharacter()) {
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
        }
    }
    //------------------------ END OF TRAIT CHART CODE -------------------------//

    //------------------- START OF COMBAT ORDER CODE ---------------------//

    private boolean validateCombatRoll(){
        StringBuilder combatOrderErrors = new StringBuilder();
        if(campaign.getCharacters().size()==0){
            combatOrderErrors.append(" - Please add characters to your campaign before generating a combat order.");
        }
        if(combatOrderErrors.length()>0){
            Alert combatOrderAlert = new Alert(Alert.AlertType.ERROR, combatOrderErrors.toString(),ButtonType.OK);
            combatOrderAlert.setHeaderText("Invalid Combat Order Information");
            combatOrderAlert.showAndWait();
            return false;
        }
        return true;

    }

    public void generateCombatOrder(ActionEvent actionEvent){
            clearCombatOrder(actionEvent);
            ArrayList charactersInCombatOrder = campaign.generateCombatOrder();
            if(validateCombatRoll()){
                for (int i = 0; i < charactersInCombatOrder.size(); i++) {
                    Character character = (Character) charactersInCombatOrder.get(charactersInCombatOrder.size() - i - 1);
                    if (!CombatOrderDisplay.getItems().contains(character.getName() + "'s roll for initiative :" + (character.getInitiative()))) {
                        CombatOrderDisplay.getItems().add(character.getName() + "'s roll for initiative :" + (character.getInitiative()));
                    }
                }
            }
    }

    @FXML
    private void clearCombatOrder(ActionEvent actionEvent){
        CombatOrderDisplay.getItems().setAll();
    }
    //----------------- END OF COMBAT ORDER CODE --------------------------//

    //--------------------- START OF TRAIT CHECK CODE ---------------------------//

    private Character getCharacterToCheck(){
        String characterName = (String) characterChoiceBox.getItems().get(characterChoiceBox.getSelectionModel().getSelectedIndex());
        for(int i=0;i<campaign.getCharacters().size();i++){
            if(campaign.getCharacters().get(i).getName().equals(characterName)){
                return campaign.getCharacters().get(i);
            }
        }
        return null;
    }

    private String getTraitToCheck(){
        return (String) traitChoiceBox.getItems().get(traitChoiceBox.getSelectionModel().getSelectedIndex());
    }

    private DifficultyClass getDifficultyClass(){
        DifficultyClass difficultyClass = (DifficultyClass) difficultyClassComboBox.getItems().get(difficultyClassComboBox.getSelectionModel().getSelectedIndex());
        return difficultyClass;
    }

    public void showTraitCheckSuccessful(TraitCheck traitCheck){
        resultWindow.setText("SUCCESS");
        Alert success = new Alert(Alert.AlertType.INFORMATION, traitCheck.getCharacterName() + "'s roll of " + traitCheck.getFinalCheckValue() +
                " added to their " + traitCheck.getTraitNameChecked() + " modifier of " + traitCheck.getAbilityModifier() +
                " was greater than or equal to the difficulty class of :" + traitCheck.getDifficultyClass(), ButtonType.OK);
        success.setHeaderText("Success");
        success.showAndWait();
    }

    public void showTraitCheckFailure(TraitCheck traitCheck){
            //String characterName, Integer advantageRoll, String traitName, Integer abilityModifier, Integer difficultyClass){
        resultWindow.setText("FAILURE");
        Alert failure = new Alert(Alert.AlertType.INFORMATION, traitCheck.getCharacterName() + "'s roll of " + traitCheck.getFinalCheckValue() +
                " added to their " + traitCheck.getTraitNameChecked() + " modifier of " + traitCheck.getAbilityModifier() +
                " was less than the difficulty class of :" + traitCheck.getDifficultyClass(), ButtonType.OK);
        failure.setHeaderText("Failure");
        failure.showAndWait();
    }


    private boolean validateTraitCheck(){
        StringBuilder traitCheckErrors = new StringBuilder();
        if(characterChoiceBox.getSelectionModel().isEmpty()){
            traitCheckErrors.append(" - Please choose a character to perform a trait check. \n");
        }
        if(traitChoiceBox.getSelectionModel().isEmpty()){
            traitCheckErrors.append(" - Please choose a trait to perform a trait check. \n");
        }
        if(difficultyClassComboBox.getSelectionModel().isEmpty()){
            traitCheckErrors.append(" - Please choose a difficulty class to perform a trait check. \n");
        }
        if(traitCheckErrors.length()>0){
            Alert characterAlert = new Alert(Alert.AlertType.ERROR, traitCheckErrors.toString(),ButtonType.OK);
            characterAlert.setHeaderText("Invalid Trait Check Information");
            characterAlert.showAndWait();
            return false;
        }
        return true;
    }

    public void onTraitCheck(ActionEvent actionEvent){
        if(validateTraitCheck()){
            TraitCheck traitCheck = new TraitCheck(getCharacterToCheck(), getTraitToCheck(), getDifficultyClass(), advantageSpinner.getValue());
            if(traitCheck.getTraitCheckResult()){
                showTraitCheckSuccessful(traitCheck);
            }
            showTraitCheckFailure(traitCheck);
//            Character character = getCharacterToCheck();
//            String traitName = getTraitToCheck();
//            Integer difficultyClass = getDifficultyClass();
//            Integer advantageRoll = rollBasedOnAdvantage();
//            Integer abilityModifier = ((Objects.requireNonNull(character).getTraits().getValue(traitName) - 10) / 2);
//            Integer traitCheckTotal = abilityModifier + advantageRoll;
        }

    }

    //---------------------- END OF TRAIT CHECK CODE ------------------------------//

    @SuppressWarnings("EmptyMethod")
    public void loadOldCampaign(ActionEvent actionEvent) {
    }
}
