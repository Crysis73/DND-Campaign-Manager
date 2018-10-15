package edu.bsu.cs222;

public class Equipment {
    private String description;
    private TraitMap traitMap;
    private Integer traitBonus;
    private String trait;
    private String equipmentName;
    //How to add trait bonus

    public Equipment(String equipmentName, String trait, Integer traitBonus, String description){
        this.equipmentName = equipmentName;
        TraitMap traitMap = new TraitMap();
        traitMap.setAllValues(0);
        traitMap.setValue(trait,traitBonus);
        this.description = description;
        this.traitMap = traitMap;

    }

    public String toString(){
        return ("Name : "+this.equipmentName+"\n"+this.description+"\n\tAdds "+this.traitBonus+" points to "+this.trait);
    }
}
