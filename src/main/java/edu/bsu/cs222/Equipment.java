package edu.bsu.cs222;

public class Equipment {
    private String description;
    private Trait trait;
    private Integer traitBonus;
    private String equipmentName;
    //How to add trait bonus

    public Equipment(String equipmentName, Trait trait, Integer traitBonus, String description){
        this.equipmentName = equipmentName;
        this.traitBonus = traitBonus;
        this.description = description;
        this.trait = trait;
        trait.setValue(traitBonus + trait.getValue());
    }

    public String toString(){
        return ("Name : "+this.equipmentName+"\n"+this.description+"\n\tAdds "+this.traitBonus+" points to "+this.trait);
    }
}
