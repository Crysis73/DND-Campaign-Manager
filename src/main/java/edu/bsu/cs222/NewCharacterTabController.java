package edu.bsu.cs222;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;

public class NewCharacterTabController {

    @FXML
    private TextField characterNameField, setAge, setHeight, setWeight, setEyeColor, setSkinColor, setAdditionalFeatures, setAlignment,
            setLanguages, setExoticLanguages, setPersonalityTrait1, setPersonalityTrait2, setIdeals, setBonds, setFlaws,
            setWealth, setXP;
    @FXML
    private ComboBox raceComboBox,dndClassComboBox;

    @FXML
    private AnchorPane additionalInformationPane;

    @FXML private MainController mainController;


    public void injectMainController(MainController mainController){
        this.mainController = mainController;
    }

    protected void initializeNewCharacterTab(){
        initializeDndClassChoices();
        initializeRaceChoices();
    }


    public void showCharacterCreationSuccessAlert(String characterName){
        Alert characterCreated = new Alert(Alert.AlertType.INFORMATION,characterName+" has been created!");
        characterCreated.setTitle("Character Created");
        characterCreated.setHeaderText("SUCCESS");
        characterCreated.showAndWait();
    }

    public String getRaceName(){
        Race race =(Race)raceComboBox.getItems().get(raceComboBox.getSelectionModel().getSelectedIndex());
        return race.getName();
    }

    public String getClassName(){
        dndClass dndClass =(dndClass) dndClassComboBox.getItems().get(dndClassComboBox.getSelectionModel().getSelectedIndex());
        return dndClass.getName();
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
        for(int i =0;i<mainController.getCampaign().getCharacters().size();i++){
            if(characterName.equals(mainController.getCampaign().getCharacters().get(i).getName().trim())){
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
        if(raceComboBox.getSelectionModel().getSelectedIndex() ==-1){
            characterErrors.append(" - Please choose a race for your character from the menu. \n");
        }
        if(dndClassComboBox.getSelectionModel().getSelectedIndex() == -1){
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


    public void onAddCharacterToCampaign() {
        if(validateCharacter()){
            String characterName = characterNameField.getText().trim();
            Character character = new Character(characterName, getClassName(),getRaceName());
            addDescription(character);
            mainController.getCampaign().addCharacer(character);
            mainController.createNewCharacterTab(character);
//            initializeCharacterChoices();
            showCharacterCreationSuccessAlert(characterName);
//            charactersPane.expandedProperty().set(true);
            clearAllNewCharacter();
        }

    }

    private void initializeDndClassChoices(){
        dndClassList dndClassList = new dndClassList();
        for(int i=0;i<dndClassList.getDndClasses().length;i++){
            dndClassComboBox.getItems().add(i,dndClassList.getDndClasses()[i]);
        }
        initializeDndClassTooltips();
        initializeDndClassComboBoxConverter();
    }



    private void initializeDndClassComboBoxConverter(){
        dndClassComboBox.setConverter(new StringConverter<dndClass>() {
            @Override
            public String toString(dndClass dndClass) {
                return dndClass.getName();
            }

            @Override
            public dndClass fromString(String string) {
                return (dndClass) dndClassComboBox.getItems().stream().filter(dndClass -> dndClass.getClass().getName().equals(string)).findFirst().orElse(null);
            }
        });
    }

    private void initializeDndClassTooltips(){
        dndClassComboBox.setCellFactory(param -> new ListCell<dndClass>(){
            @Override
            public void updateItem(dndClass item, boolean empty){
                super.updateItem(item,empty);

                if(item!=null){
                    setText(item.getName());
                    Tooltip tooltip = new Tooltip();
                    tooltip.setText(item.toString());
                    setTooltip(tooltip);
                }else{
                    setText(null);
                    setTooltip(null);
                }
            }
        });
    }

    private void initializeRaceChoices(){
        raceList raceList = new raceList();
        for(int i=0;i<raceList.getRaces().length;i++){
            raceComboBox.getItems().add(i,raceList.getRaces()[i]);
        }
        initializeRaceTooltips();
        initializeRaceComboBoxConverter();
    }

    private void initializeRaceTooltips(){
        raceComboBox.setCellFactory(param -> new ListCell<Race>(){
            @Override
            public void updateItem(Race item, boolean empty){
                super.updateItem(item,empty);

                if(item!=null){
                    setText(item.getName());
                    Tooltip tooltip = new Tooltip();
                    tooltip.setText(item.toString());
                    setTooltip(tooltip);
                }else{
                    setText(null);
                    setTooltip(null);
                }
            }
        });
    }

    private void initializeRaceComboBoxConverter(){
        raceComboBox.setConverter(new StringConverter<Race>() {
            @Override
            public String toString(Race race) {
                return race.getName();
            }

            @Override
            public Race fromString(String string) {
                return (Race) raceComboBox.getItems().stream().filter(race -> race.getClass().getName().equals(string)).findFirst().orElse(null);
            }
        });
    }



    public void clearAllNewCharacter(){
        raceComboBox.getSelectionModel().clearSelection();
        dndClassComboBox.getSelectionModel().clearSelection();
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
    }


    public void refreshNewCharacterTab(){
        raceComboBox.getItems().clear();
        dndClassComboBox.getItems().clear();
    }


}
