package edu.bsu.cs222;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class Race {
    private String name;
    private String description; //Will need to be manually set for each race.
    private TraitMap traitBonus;
    private Map<String,Integer> bonusValues;

    /*
    Passing all arguments through the constructor because Races are static. We will need to manually create each Race that
    users are able to choose from. These Races will have preset names, traitBonuses, traitNames, and descriptions.
     */

    public Race(String name){
        this.name = name;
        if(name == "Dwarf"){
            Dwarf dwarf = new Dwarf();
            this.traitBonus = dwarf.getRaceTraitBonuses();
            this.description = dwarf.getRaceDescription();
        }


    }

    public TraitMap getTraitBonuses(){
        return this.traitBonus;
    }

    public String toString(){
        String result = "Race : "+this.name+"\n\tDescription : "+this.description;
        ArrayList<String> traitNames = new ArrayList<>();
        traitNames.addAll(Arrays.asList("Strength", "Dexterity", "Intelligence", "Wisdom", "Charisma", "Constitution"));
        for(Map.Entry<String,Integer> entry : traitBonus.getTraitMap().entrySet()){
            if(entry.getValue()!=0){
                result+= "\n\tAdds "+entry.getValue()+" to "+entry.getKey();
            }
        }
        return result;
    }

}
