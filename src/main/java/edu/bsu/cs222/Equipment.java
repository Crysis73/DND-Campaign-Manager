package edu.bsu.cs222;

class Equipment {
    private final String description;
    private Integer traitBonus;
    private String trait;
    private final String equipmentName;
    //How to add trait bonus

    public Equipment(String equipmentName, String trait, Integer traitBonus, String description){
        this.equipmentName = equipmentName;
        TraitMap traitMap = new TraitMap();
        traitMap.setAllValues(0);
        traitMap.setValue(trait,traitBonus);
        this.description = description;

    }

    public String toString(){
        return ("Name : "+this.equipmentName+"\n"+this.description+"\n\tAdds "+this.traitBonus+" points to "+this.trait);
    }
}
