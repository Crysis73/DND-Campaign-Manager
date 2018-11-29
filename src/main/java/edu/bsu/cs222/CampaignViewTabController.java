package edu.bsu.cs222;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class CampaignViewTabController {

    @FXML private TextField resultWindow, logEntry;
    @FXML private TextArea logDisplay;
    @FXML private ListView<String> characterList;
    @FXML private ListView<String> combatOrderDisplay;
    @FXML private TitledPane chartTitledPane, charactersPane;
    @FXML private ChoiceBox<String> characterChoiceBox;
    @FXML private ChoiceBox<String> traitChoiceBox;
    @FXML private ChoiceBox<DifficultyClass> difficultyClassChoiceBox;
    @FXML private Spinner<String> advantageSpinner;
    @FXML private VBox hpVBox, wealthVBox;
    @FXML private HBox hpHBox;
    @FXML private AnchorPane chartPane;
    @FXML private Button hpIncrement, hpDecrement, modifyWealth;
    @FXML private Label wealthLabel;
    @FXML private WealthModificationController wealthModificationController;
    @FXML private MainController mainController;
    private Character activeCharacter;

    void injectMainController(MainController mainController){
        this.mainController = mainController;
        wealthModificationController.injectCampaignViewTabController(this);
    }

    void injectWealthModificationController(WealthModificationController wealthModificationController){
        this.wealthModificationController = wealthModificationController;
    }

    void displayCharacters(){
        ArrayList<String> characters = new ArrayList<>();
        for(int i=0;i<mainController.getCampaign().getCharacters().size();i++){
            characters.add(mainController.getCampaign().getCharacters().get(i).getName());
        }
        ObservableList<String> characterNames = FXCollections.observableArrayList(characters);
        characterList.setItems(characterNames);

    }

    private void setActiveCharacter(String name){
        for(int i =0;i<mainController.getCampaign().getCharacters().size();i++){
            if(mainController.getCampaign().getCharacters().get(i).getName().equals(name)){
                this.activeCharacter = mainController.getCampaign().getCharacters().get(i);
            }
        }
    }

    Character getCharacterInCampaign(String name){
        for(int i=0;i<mainController.getCampaign().getCharacters().size();i++){
            if(mainController.getCampaign().getCharacters().get(i).getName().equals(name)){
                return mainController.getCampaign().getCharacters().get(i);
            }
        }
        return null;
    }


    //--------------------- START OF INITIALIZE CAMPAIGN VIEW CODE ---------------------------//

    void initializeCampaignViewTab(){
        initializeCharacterChoices();
        initializeTraitChoices();
        initializeAdvantageStates();
        initializeDifficultyClasses();
        charactersPane.setExpanded(true);
        chartTitledPane.setExpanded(true);
    }


    @FXML
    protected void initializeCharacterChoices(){
        ArrayList<Character> characters = mainController.getCampaign().getCharacters();
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
        ArrayList<DifficultyClass> difficultyClasses = new ArrayList<>();
        for(int i=0;i<difficultyClassNames.length;i++){
            difficultyClassValues.add(5*(i+1));
            DifficultyClass difficultyClass = new DifficultyClass(difficultyClassNames[i],difficultyClassValues.get(i));
            difficultyClasses.add(difficultyClass);
        }
        difficultyClassChoiceBox.getItems().setAll(difficultyClasses);
    }

    private void initializeAdvantageStates(){
        String[] advantageStates = {"Neutral","Advantage","Disadvantage"};
        ObservableList<String> list = FXCollections.observableArrayList(advantageStates);
        SpinnerValueFactory<String> valueFactory = new SpinnerValueFactory.ListSpinnerValueFactory<>(list);
        advantageSpinner.setValueFactory(valueFactory);
    }



    //--------------------- END OF CAMPAIGN VIEW INITIALIZATION CHARACTER CODE ---------------------------//

    //--------------------- START OF CHART POPULATION CODE ---------------------------//
    private boolean validateChartCharacter(){
        StringBuilder chartErrors = new StringBuilder();
        if(mainController.getCampaign().getCharacters().size()==0){
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

    public void createChart(MouseEvent mouseEvent) {
        if(validateChartCharacter() && mainController.getCampaign().getCharacters().size()!=0) {
            ObservableList<String> activeCharacterName = characterList.getSelectionModel().getSelectedItems();
            String characterName = activeCharacterName.toString().replace("[", "").replace("]", "");
            setActiveCharacter(characterName);
            ChartCreator chartCreator = new ChartCreator(activeCharacter.getTraits().getTraitMap());
            ObservableList<String> traitNames = FXCollections.observableArrayList(activeCharacter.getTraits().getTraitMap().keySet());
            BarChart chart = chartCreator.createChart(traitNames,characterName);
            chartPane.getChildren().clear();
            chartPane.getChildren().add(chart);
            AnchorPane.setTopAnchor(chart, (double)0);
            AnchorPane.setLeftAnchor(chart,(double)0);
            AnchorPane.setRightAnchor(chart,(double)0);
            AnchorPane.setBottomAnchor(chart,(double)0);
            chartTitledPane.expandedProperty().setValue(true);
            setHpProgressBar();
            setWealthDisplay();
        }
    }

    //--------------------- END OF CHART POPULATION CODE ---------------------------//

    //--------------------- START OF HP PROGRESS BAR CODE ---------------------------//

    private boolean validateHPIncrement(){
        if(activeCharacter!=null){
            return !activeCharacter.getCurrentHitPoints().equals(activeCharacter.getMaxHitPoints());
        }else{
            return false;
        }
    }
    private boolean validateHPDecrement(){
        if(activeCharacter!=null) {
            return activeCharacter.getCurrentHitPoints() != 0;
        }else{
            return false;
        }
    }
    private void setHpProgressBar(){
        hpVBox.getChildren().clear();
        Label label = new Label("HP :"+activeCharacter.getCurrentHitPoints() +"/"+activeCharacter.getMaxHitPoints());
        hpVBox.getChildren().add(label);
        float progress = (float)activeCharacter.getCurrentHitPoints() / activeCharacter.getMaxHitPoints();
        ProgressBar progressBar = new ProgressBar(progress);
        hpVBox.getChildren().add(progressBar);

    }

    public void incrementActiveCharacterHP(ActionEvent actionEvent){
        if(validateHPIncrement()) {
            activeCharacter.incrementCurrentHP();
            setHpProgressBar();
        }
    }

    public void decrementActiveCharacterHP(ActionEvent actionEvent){
        if(validateHPDecrement()) {
            activeCharacter.decrementCurrentHP();
            setHpProgressBar();
        }
    }

    //--------------------- END OF HP PROGRESS BAR CODE ---------------------------//


    //--------------------- START OF COMBAT ORDER CODE ---------------------------//
    private boolean validateCombatRoll(){
        StringBuilder combatOrderErrors = new StringBuilder();
        if(mainController.getCampaign().getCharacters().size()==0){
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
        combatOrderDisplay.getItems().clear();
        ArrayList charactersInCombatOrder = mainController.getCampaign().generateCombatOrder();
        if(validateCombatRoll()){
            for (int i = 0; i < charactersInCombatOrder.size(); i++) {
                Character character = (Character) charactersInCombatOrder.get(charactersInCombatOrder.size() - i - 1);
                if (!combatOrderDisplay.getItems().contains(character.getName() + "'s roll for initiative :" + (character.getInitiative()))) {
                    combatOrderDisplay.getItems().add(character.getName() + "'s roll for initiative :" + (character.getInitiative()));
                }
            }
        }
    }

    @FXML
    private void clearCombatOrder(ActionEvent actionEvent){
        combatOrderDisplay.getItems().clear();
    }

    //--------------------- END OF COMBAT ORDER CODE ---------------------------//

    //--------------------- START OF TRAIT CHECK CODE ---------------------------//
    private Character getCharacterToCheck(){
        String characterName = characterChoiceBox.getItems().get(characterChoiceBox.getSelectionModel().getSelectedIndex());
        for(int i=0;i<mainController.getCampaign().getCharacters().size();i++){
            if(mainController.getCampaign().getCharacters().get(i).getName().equals(characterName)){
                return mainController.getCampaign().getCharacters().get(i);
            }
        }
        return null;
    }

    private String getTraitToCheck(){
        return traitChoiceBox.getItems().get(traitChoiceBox.getSelectionModel().getSelectedIndex());
    }

    private DifficultyClass getDifficultyClass(){
        return difficultyClassChoiceBox.getItems().get(difficultyClassChoiceBox.getSelectionModel().getSelectedIndex());
    }

    private void showTraitCheckSuccessful(TraitCheck traitCheck){
        resultWindow.setText("SUCCESS");
        Alert success = new Alert(Alert.AlertType.INFORMATION, traitCheck.getCharacterName() + "'s roll of " + traitCheck.getAdvantageRoll() +
                " added to their " + traitCheck.getTraitNameChecked() + " modifier of " + traitCheck.getAbilityModifier() +
                " was greater than or equal to the difficulty class of : \"" + traitCheck.getDifficultyClass() + "\"", ButtonType.OK);
        success.setHeaderText("Success");
        success.showAndWait();
    }

    private void showTraitCheckFailure(TraitCheck traitCheck){
        resultWindow.setText("FAILURE");
        Alert failure = new Alert(Alert.AlertType.INFORMATION, traitCheck.getCharacterName() + "'s roll of " + traitCheck.getAdvantageRoll() +
                " added to their " + traitCheck.getTraitNameChecked() + " modifier of " + traitCheck.getAbilityModifier() +
                " was less than the difficulty class of : \"" + traitCheck.getDifficultyClass() + "\"", ButtonType.OK);
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
        if(difficultyClassChoiceBox.getSelectionModel().isEmpty()){
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
                if(!getDifficultyClass().getName().equals("Easy")) {
                    addEntryToLog(new Date().toString() + ": " + traitCheck.getCharacterName() + " attempted a " +getDifficultyClass().getName() + " "+ traitCheck.getTraitNameChecked() +
                            " check and SUCCEEDED");
                }else{
                    addEntryToLog(new Date().toString() + ": " + traitCheck.getCharacterName() + " attempted an " +getDifficultyClass().getName() + " " + traitCheck.getTraitNameChecked() +
                            " check and SUCCEEDED");
                }
            }
            else{
                showTraitCheckFailure(traitCheck);
                if(!getDifficultyClass().getName().equals("Easy")) {
                    addEntryToLog(new Date().toString() + ": " + traitCheck.getCharacterName() + " attempted a " +getDifficultyClass().getName()+" "+ traitCheck.getTraitNameChecked() +
                            " check and FAILED");
                }else{
                    addEntryToLog(new Date().toString() + ": " + traitCheck.getCharacterName() + " attempted an " +getDifficultyClass().getName() + " " + traitCheck.getTraitNameChecked() +
                            " check and they FAILED");
                }
            }

        }
    }
    //---------------------- END OF TRAIT CHECK CODE ------------------------------//


    //---------REFRESH AND LOAD OLD CAMPAIGN -------//

    void refreshCampaignView(){
        logEntry.clear();
        logDisplay.clear();
        hpVBox.getChildren().clear();
        chartPane.getChildren().clear();
        characterList.getItems().clear();
        combatOrderDisplay.getItems().clear();
    }


    //--------------------------- START OF LOG CODE ----------------------------------------//

    void addEntryToLog(String entry){
        mainController.getCampaign().addEntryToLog(entry);
        logDisplay.setText(mainController.getCampaign().getLog().replace("null",""));
    }

    public void onLogEntry(){
        addEntryToLog(new Date().toString() +": " + logEntry.getText());
        logEntry.clear();
    }

    //-------------------------------- END OF LOG CODE -------------------------------------//

    //--------------------------------- START OF WEALTH CODE -------------------------------------//

    private void setWealthDisplay(){
        if(activeCharacter!=null) {
            wealthLabel.setText("Wealth: " + activeCharacter.getWealth().toString());
        }
    }

    @FXML
    private void showWealthModificationMenu(ActionEvent actionEvent){
        FXMLLoader loader = new FXMLLoader();
        WealthModificationController wealthModificationController = new WealthModificationController();
        this.wealthModificationController = wealthModificationController;
        loader.setController(wealthModificationController);
        loader.setLocation(getClass().getResource("/WealthModificationMenu.fxml"));
        ArrayList<Character> characters = mainController.getCampaign().getCharacters();

        try {
            AnchorPane anchorPane = loader.load();
            wealthModificationController.injectCampaignCharacters(characters);
            wealthModificationController.initializeCharacterChoices();
            wealthModificationController.initializePayToChoices();
            Scene scene = new Scene(anchorPane);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Wealth Modification Menu");
            stage.show();
            stage.onCloseRequestProperty().set(event -> {
                setWealthDisplay();
                String logUpdates = wealthModificationController.getLogUpdates();
                addEntryToLog(logUpdates);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}
