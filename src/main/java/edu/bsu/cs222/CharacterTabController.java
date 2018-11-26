package edu.bsu.cs222;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;


public class CharacterTabController {
//    @FXML
//    Text characterNameText;
    @FXML
    TextField characterWealth, characterCurrentHP, characterMaxHP,
            characterHeight,
            characterWeight,
            characterAge,
            characterEyeColor,
            characterSkinColor,
            characterAdditionalFeatures,
            characterLanguages,
            characterExoticLanguages,
            characterPersonalityTraits,
            characterIdeals,
            characterBonds,
            characterFlaws;
    Character character;


    protected CharacterTabController(Character characterName){
//        Text characterNameText = new Text(character.getName());
//        this.characterNameText = characterNameText;
    }

    protected void setCharacterWealth(String characterWealth){
        this.character.setWealth(Integer.valueOf(characterWealth));
        this.characterWealth.setText(characterWealth);
    }

    protected void setCharacterCurrentHP(String characterCurrentHP){
        this.character.setCurrentHitPoints(Integer.valueOf(characterCurrentHP));
        this.characterCurrentHP.setText(characterCurrentHP);
    }

    protected void setCharacterMaxHP(){
        String characterMaxHP = Integer.toString(this.character.getMaxHitPoints());
        this.characterMaxHP.setText(characterMaxHP);
    }



}
