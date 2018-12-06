package edu.bsu.cs222;

import java.util.Objects;

class TraitCheck {
    private final Character character;
    private final String traitName;
    private final DifficultyClass difficultyClass;
    private final Integer abilityModifier;
    private final Integer finalValue;
    private Integer advantageRoll;

    TraitCheck(Character character, String traitName, DifficultyClass difficultyClass, String advantageType){
        this.character = character;
        this.traitName = traitName;
        this.difficultyClass = difficultyClass;
        Die d20 = new Die(20);
        if(advantageType.equals("Advantage")){
            advantageRoll = d20.rollDieTwiceUseGreatest();
        }
        if(advantageType.equals("Neutral")){
            advantageRoll = d20.rollDie();
        }else {
            advantageRoll = d20.rollDieTwiceUseLeast();
        }
        this.abilityModifier = ((Objects.requireNonNull(character).getTraits().getValue(traitName)-10)/2);
        this.finalValue = abilityModifier+advantageRoll;

    }

    String getTraitNameChecked(){
        return traitName;
    }

    Integer getAbilityModifier(){
        return abilityModifier;
    }

    String getDifficultyClass(){
        return difficultyClass.toString();
    }

    Integer getAdvantageRoll(){
        return this.advantageRoll;
    }

    String getCharacterName(){
        return character.getName();
    }

    boolean getTraitCheckResult(){
        return finalValue >= difficultyClass.getValue();
    }

}
