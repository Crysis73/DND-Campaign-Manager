package edu.bsu.cs222;

public class traitBonus {
    private String traitName;
    private Integer traitBonusValue;

    public traitBonus(String traitName, Integer traitBonusValue){
        this.traitName = traitName;
        this.traitBonusValue = traitBonusValue;
    }

    public String getTraitName(){
        return this.traitName;
    }

    public  Integer getTraitBonusValue(){
        return  this.traitBonusValue;
    }
}
