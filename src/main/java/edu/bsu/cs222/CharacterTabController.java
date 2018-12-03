package edu.bsu.cs222;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


class CharacterTabController {
    @FXML private Label characterNameText, raceNameText, classNameText;
    @FXML private TextArea raceDescriptionBox, classDescriptionBox;
    @FXML private TextField wealthBox, currentHPBox, maxHPBox, xpBox, levelBox, strengthBox, dexterityBox, constitutionBox, intelligenceBox, wisdomBox, charismaBox;
    private final Character character;

    CharacterTabController(Character characterName){
        this.character = characterName;
    }

    void initialize(){
        this.characterNameText.setText(character.getName());
        this.raceNameText.setText(character.getRaceName());
        this.classNameText.setText(character.getdndClassName());
        this.raceDescriptionBox.setText(character.getRace().toString());
        this.classDescriptionBox.setText(character.getDndClass().toString());
        this.wealthBox.setText(character.getWealth().toString());
        this.currentHPBox.setText(character.getCurrentHitPoints().toString());
        this.maxHPBox.setText(character.getMaxHitPoints().toString());
        this.xpBox.setText(character.getExperiencepoints().toString());
        this.levelBox.setText(character.getLevel().getCurrentLevel().toString());
        this.strengthBox.setText(character.getTraits().getValue("Strength").toString());
        this.dexterityBox.setText(character.getTraits().getValue("Dexterity").toString());
        this.constitutionBox.setText(character.getTraits().getValue("Constitution").toString());
        this.intelligenceBox.setText(character.getTraits().getValue("Intelligence").toString());
        this.wisdomBox.setText(character.getTraits().getValue("Wisdom").toString());
        this.charismaBox.setText(character.getTraits().getValue("Charisma").toString());
    }

    protected void setCharacterWealth(String characterWealth){
        this.character.setWealth(Integer.valueOf(characterWealth));
        //this.characterWealth.setText(characterWealth);
    }

    protected void setCharacterCurrentHP(String characterCurrentHP){
        this.character.setCurrentHitPoints(Integer.valueOf(characterCurrentHP));
        //this.characterCurrentHP.setText(characterCurrentHP);
    }

    protected void setCharacterMaxHP(){
        String characterMaxHP = Integer.toString(this.character.getMaxHitPoints());
        //this.characterMaxHP.setText(characterMaxHP);
    }



}
