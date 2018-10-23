package edu.bsu.cs222;

import java.util.Map;
import java.util.Objects;

class Race {
    private final String name;
    private String description; //Will need to be manually set for each race.
    private TraitMap traitBonus;
    private Map<String,Integer> bonusValues;

    /*
    Passing all arguments through the constructor because Races are static. We will need to manually create each Race that
    users are able to choose from. These Races will have preset names, traitBonuses, traitNames, and descriptions.
     */

    Race(String name, TraitMap traitBonus){
        this.name = name;
        this.traitBonus = traitBonus;
    }

    public TraitMap getTraitBonuses(){
        return this.traitBonus;
    }

    public String toString(){
        StringBuilder result = new StringBuilder("Race : " + this.name + "\n\tDescription : " + this.description);
        /*
        for(Map.Entry<String,Integer> entry : traitBonus.getTraitMap().entrySet()){
            if(entry.getValue()!=0){
                result.append("\n\tAdds ").append(entry.getValue()).append(" to ").append(entry.getKey());
            }
        }
        */
        return result.toString();
    }

    public String getName(){
        return this.name;
    }

}
