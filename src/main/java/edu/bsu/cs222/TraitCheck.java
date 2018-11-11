package edu.bsu.cs222;

public class TraitCheck {
    Character character;
    String traitName;
    DifficultyClass difficultyClass;
    Integer totalModifier;
    Integer abilityModifier;
    Integer finalValue;

    public TraitCheck(Character character, String traitName, DifficultyClass difficultyClass, String advantageType){
        this.character = character;
        this.traitName = traitName;
        Integer advantageRoll =0;
        Die d20 = new Die(20);
        if(advantageType.equals("Advantage")){
            advantageRoll = d20.rollDieTwiceUseGreatest();
        }
        if(advantageType.equals("Neutral")){
            advantageRoll = d20.rollDie();
        }
        if(advantageType.equals("Disadvantage")){
            advantageRoll = d20.rollDieTwiceUseLeast();
        }
        this.abilityModifier = ((character.getTraits().getValue(traitName)-10)/2);
        this.finalValue = abilityModifier+advantageRoll;

    }

    public String getTraitNameChecked(){
        return traitName;
    }
    public Integer getAbilityModifier(){
        return abilityModifier;
    }
    public String getDifficultyClass(){
        return difficultyClass.toString();
    }
    public Integer getFinalCheckValue(){
        return finalValue;
    }

    public String getCharacterName(){
        return character.getName();
    }

    public boolean getTraitCheckResult(){
        if(finalValue>difficultyClass.getValue()){
            return true;
        }
        return false;
    }

}
