package edu.bsu.cs222;

import java.util.Map;

class Race {
    private final String name;
    private String description; //Will need to be manually set for each race.
    private final TraitMap traitBonus;
    private Map<String,Integer> bonusValues;

    Race(String name, TraitMap traitBonus){
        this.name = name;
        this.traitBonus = traitBonus;
    }

    public TraitMap getTraitBonuses(){
        return this.traitBonus;
    }

    public String toString(){
        StringBuilder result = new StringBuilder("Race : " + this.name + "\n\tDescription : " + this.description);

        for(Map.Entry<String,Integer> entry : traitBonus.getTraitMap().entrySet()){
            if(entry.getValue()!=0){
                result.append("\n\tAdds ").append(entry.getValue()).append(" to ").append(entry.getKey());
            }
        }
        return result.toString();
    }

    public String getName(){
        return this.name;
    }

    public void setDescription(String description){
        this.description = description;
    }

}
