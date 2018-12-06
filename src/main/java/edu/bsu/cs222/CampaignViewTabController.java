package edu.bsu.cs222;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
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
    @FXML private VBox hpVBox, wealthVBox,XPVBox;
    @FXML private HBox hpHBox;
    @FXML private AnchorPane rootPane, chartPane;
    @FXML private Button hpIncrement, hpDecrement, modifyWealth, wealthDecrement, wealthIncrement,XPIncrement,XPDecrement;
    @FXML private MainController mainController;
    private boolean isDarkTheme;
    private Character activeCharacter;



    void toDarkTheme(){
        rootPane.getStylesheets().add(getClass().getResource("/Stylesheets/DarkTheme.css").toExternalForm());
        rootPane.getStyleClass().add("DarkTheme");
        isDarkTheme = true;
        if(activeCharacter!=null) {
            refreshProgressBars();
        }
    }

    void injectMainController(MainController mainController){
        this.mainController = mainController;
    }

    void displayCharacters(){
        onCharacterClicked();
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
    void initializeCharacterChoices(){
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
            if(isDarkTheme) {
                DialogPane dialogPane = chartAlert.getDialogPane();
                dialogPane.getStylesheets().add(getClass().getResource("/Stylesheets/DarkTheme.css").toExternalForm());
                dialogPane.getStyleClass().add("DarkTheme");
            }
            chartAlert.showAndWait();
            return false;
        }
        return true;
    }

    private void onCharacterClicked(){
        characterList.setOnMouseClicked(mouseEvent -> {
            if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                if(mouseEvent.getClickCount() == 2){
                    ObservableList<String> activeCharacterName = characterList.getSelectionModel().getSelectedItems();
                    String characterName = activeCharacterName.toString().replace("[", "").replace("]", "");
                    for(int i=0;i<mainController.getMainTabPane().getTabs().size();i++) {
                        if(mainController.getMainTabPane().getTabs().get(i).getText().equals(characterName)) {
                            mainController.getMainTabPane().getSelectionModel().select(i);
                        }
                    }
                }else{
                    createChart(mouseEvent);
                }
            }
        });
    }

    @FXML
    private void createChart(MouseEvent mouseEvent) {
        if(validateChartCharacter() && mainController.getCampaign().getCharacters().size()!=0) {
            ObservableList<String> activeCharacterName = characterList.getSelectionModel().getSelectedItems();
            String characterName = activeCharacterName.toString().replace("[", "").replace("]", "");
            if(!characterName.trim().isEmpty()) {
                setActiveCharacter(characterName);
                ChartCreator chartCreator = new ChartCreator(activeCharacter.getTraits().getTraitMap());
                ObservableList<String> traitNames = FXCollections.observableArrayList(activeCharacter.getTraits().getTraitMap().keySet());
                BarChart chart = chartCreator.createChart(traitNames, characterName);
                chartPane.getChildren().clear();
                chartPane.getChildren().add(chart);
                AnchorPane.setTopAnchor(chart, (double) 0);
                AnchorPane.setLeftAnchor(chart, (double) 0);
                AnchorPane.setRightAnchor(chart, (double) 0);
                AnchorPane.setBottomAnchor(chart, (double) 0);
                chartTitledPane.expandedProperty().setValue(true);
                setHpProgressBar();
                setWealthDisplay();
                setXPProgressBar();
            }
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
        Label label = new Label("HP: "+activeCharacter.getCurrentHitPoints() +"/"+activeCharacter.getMaxHitPoints());
        hpVBox.getChildren().add(label);
        float progress = (float)activeCharacter.getCurrentHitPoints() / activeCharacter.getMaxHitPoints();
        ProgressBar progressBar = new ProgressBar(progress);
        if(!isDarkTheme){
            progressBar.getStylesheets().add(getClass().getResource("/Stylesheets/LightThemeProgressBar.css").toExternalForm());
            progressBar.getStyleClass().add("LightThemeProgressBar");
        }
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

    //--------------------- START OF WEALTH PROGRESS BAR CODE ---------------------------//

    private void setWealthProgressBar(){
        wealthVBox.getChildren().clear();
        Label label = new Label("Wealth: "+activeCharacter.getWealth());
        wealthVBox.getChildren().add(label);
        ProgressBar progressBar = new ProgressBar((float)activeCharacter.getWealth());
        if(!isDarkTheme){
            progressBar.getStylesheets().add(getClass().getResource("/Stylesheets/LightThemeProgressBar.css").toExternalForm());
            progressBar.getStyleClass().add("LightThemeProgressBar");
        }
        wealthVBox.getChildren().add(progressBar);
    }

    @FXML
    private void incrementActiveCharacterWealth(ActionEvent actionEvent){
        if(activeCharacter!=null) {
            activeCharacter.setWealth(activeCharacter.getWealth() + 1);
            setWealthProgressBar();
        }
    }

    @FXML
    private void decrementActiveCharacterWealth(ActionEvent actionEvent){
        if(activeCharacter!=null) {
            if (activeCharacter.getWealth() != 0) {
                activeCharacter.setWealth(activeCharacter.getWealth() - 1);
                setWealthProgressBar();
            }
        }
    }

    //----------------------- END OF WEALTH PROGRESS BAR CODE ------------------------------//

    //----------------------- START OF XP PROGRESS BAR CODE ------------------------------------//
    @FXML
    private void incrementActiveCharacterXP(ActionEvent actionEvent){
        if(activeCharacter!=null) {
            Integer currentLevel = activeCharacter.getLevel().getCurrentLevel();
            Integer lowerBound = activeCharacter.getLevel().getLevelUpAt();
            Integer currentXP = activeCharacter.getExperiencepoints();
            Integer levelUp = activeCharacter.getNextLevel().getLevelUpAt();
            Integer currentMaxHP = activeCharacter.getMaxHitPoints();
            int incrementAmount = (levelUp - lowerBound) / 10;
            if ((levelUp - currentXP < incrementAmount)) {
                incrementAmount = levelUp - currentXP;
            }
            if (activeCharacter.getLevel().getCurrentLevel() != 20) {
                activeCharacter.setExperiencepoints(incrementAmount + currentXP);
            }
            setXPProgressBar();
            boolean hasLeveledUp = activeCharacter.getHasLeveledUp();
            if (hasLeveledUp) {
                String alertText = activeCharacter.getName() + " has increased their " + activeCharacter.getdndClassName() + " from " +
                        currentLevel.toString() + " to " + activeCharacter.getLevel().getCurrentLevel().toString() + ". As a result, their " +
                        "maximum HP has increased to " + activeCharacter.getMaxHitPoints().toString();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, alertText);
                alert.setHeaderText("LEVEL UP");
                if (isDarkTheme) {
                    DialogPane dialogPane = alert.getDialogPane();
                    dialogPane.getStylesheets().add(getClass().getResource("/Stylesheets/DarkTheme.css").toExternalForm());
                    dialogPane.getStyleClass().add("DarkTheme");
                }
                alert.showAndWait();
                addEntryToLog(new Date().toString() + ": " + activeCharacter.getName() + " leveled up from level "
                        + currentLevel.toString() + " to " + activeCharacter.getLevel().getCurrentLevel().toString()
                + " which increased their max HP from "+ currentMaxHP.toString() + " to " + activeCharacter.getMaxHitPoints().toString());
                refreshProgressBars();
            }
        }
    }

    void refreshProgressBars(){
        setXPProgressBar();
        setWealthProgressBar();
        setHpProgressBar();
    }

    @FXML
    private void decrementActiveCharacterXP(ActionEvent actionEvent){
        if(activeCharacter!=null) {
            Integer lowerBound = activeCharacter.getLevel().getLevelUpAt();
            Integer currentXP = activeCharacter.getExperiencepoints();
            Integer levelUp = activeCharacter.getNextLevel().getLevelUpAt();
            int decrementAmount = (levelUp - lowerBound) / 10;
            if (!currentXP.equals(lowerBound)) {
                if (currentXP - decrementAmount < lowerBound) {
                    decrementAmount = currentXP - lowerBound;
                }
                activeCharacter.setExperiencepoints(currentXP - decrementAmount);
            }
            setXPProgressBar();
        }
    }

    private void setXPProgressBar(){
        XPVBox.getChildren().clear();
        Label label = new Label("Level: "+activeCharacter.getLevel().getCurrentLevel());
        Label xpLabel = new Label(activeCharacter.getExperiencepoints().toString() + "/" + activeCharacter.getNextLevel().getLevelUpAt().toString());
        XPVBox.getChildren().add(label);
        ProgressBar progressBar = new ProgressBar(0);
        if(!isDarkTheme){
            progressBar.getStylesheets().add(getClass().getResource("/Stylesheets/LightThemeProgressBar.css").toExternalForm());
            progressBar.getStyleClass().add("LightThemeProgressBar");
        }
        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(progressBar);
        stackPane.getChildren().add(xpLabel);
        xpLabel.translateYProperty().setValue(-1);
        XPVBox.getChildren().add(stackPane);
    }

    //----------------------- END OF XP PROGRESS BAR CODE ---------------------------------------//


    //--------------------- START OF COMBAT ORDER CODE ---------------------------//

    private boolean validateCombatRoll(){
        StringBuilder combatOrderErrors = new StringBuilder();
        if(mainController.getCampaign().getCharacters().size()==0){
            combatOrderErrors.append(" - Please add characters to your campaign before generating a combat order.");
        }
        if(combatOrderErrors.length()>0){
            Alert combatOrderAlert = new Alert(Alert.AlertType.ERROR, combatOrderErrors.toString(),ButtonType.OK);
            combatOrderAlert.setHeaderText("Invalid Combat Order Information");
            if(isDarkTheme) {
                DialogPane dialogPane = combatOrderAlert.getDialogPane();
                dialogPane.getStylesheets().add(getClass().getResource("/Stylesheets/DarkTheme.css").toExternalForm());
                dialogPane.getStyleClass().add("DarkTheme");
            }
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
        if(isDarkTheme) {
            DialogPane dialogPane = success.getDialogPane();
            dialogPane.getStylesheets().add(getClass().getResource("/Stylesheets/DarkTheme.css").toExternalForm());
            dialogPane.getStyleClass().add("DarkTheme");
        }
        success.showAndWait();
    }

    private void showTraitCheckFailure(TraitCheck traitCheck){
        resultWindow.setText("FAILURE");
        Alert failure = new Alert(Alert.AlertType.INFORMATION, traitCheck.getCharacterName() + "'s roll of " + traitCheck.getAdvantageRoll() +
                " added to their " + traitCheck.getTraitNameChecked() + " modifier of " + traitCheck.getAbilityModifier() +
                " was less than the difficulty class of : \"" + traitCheck.getDifficultyClass() + "\"", ButtonType.OK);
        failure.setHeaderText("Failure");
        if(isDarkTheme) {
            DialogPane dialogPane = failure.getDialogPane();
            dialogPane.getStylesheets().add(getClass().getResource("/Stylesheets/DarkTheme.css").toExternalForm());
            dialogPane.getStyleClass().add("DarkTheme");
        }
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
            if(isDarkTheme) {
                DialogPane dialogPane = characterAlert.getDialogPane();
                dialogPane.getStylesheets().add(getClass().getResource("/Stylesheets/DarkTheme.css").toExternalForm());
                dialogPane.getStyleClass().add("DarkTheme");
            }
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
        logDisplay.setScrollTop(Double.MAX_VALUE);
    }

    public void onLogEntry(){
        addEntryToLog(new Date().toString() +": " + logEntry.getText());
        logEntry.clear();
    }

    //-------------------------------- END OF LOG CODE -------------------------------------//

    //--------------------------------- START OF WEALTH CODE -------------------------------------//

    private void setWealthDisplay(){
        if(activeCharacter!=null) {
            setWealthProgressBar();
        }
    }

    @FXML
    private void showWealthModificationMenu(ActionEvent actionEvent){
        FXMLLoader loader = new FXMLLoader();
        WealthModificationController wealthModificationController = new WealthModificationController();
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
            if(isDarkTheme){
                wealthModificationController.toDarkTheme();
            }
            stage.setResizable(false);
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
    //--------------------------- END OF WEALTH CODE --------------------//
    Character getActiveCharacter(){
        return this.activeCharacter;
    }

    @FXML
    private void showDieRollMenu(ActionEvent actionEvent){
        FXMLLoader loader = new FXMLLoader();
        DieRollerController dieRollerController = new DieRollerController();
        loader.setController(dieRollerController);
        loader.setLocation(getClass().getResource("/DieRoller.fxml"));
        ArrayList<Character> characters = mainController.getCampaign().getCharacters();
        try {
            AnchorPane anchorPane = loader.load();
            Scene scene = new Scene(anchorPane);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Die Roller");
            if(isDarkTheme){
                dieRollerController.toDarkTheme();
            }
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
