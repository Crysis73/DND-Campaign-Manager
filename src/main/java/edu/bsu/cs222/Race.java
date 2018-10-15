package edu.bsu.cs222;

import java.util.ArrayList;

public class Race {
    private String name;
    private String description; //Will need to be manually set for each race.
    private ArrayList<traitBonus> traitBonus;

    /*
    Passing all arguments through the constructor because Races are static. We will need to manually create each Race that
    users are able to choose from. These Races will have preset names, traitBonuses, traitNames, and descriptions.
     */

    public Race(String name,ArrayList<traitBonus> traitBonus, String description){
        this.name = name;
        this.traitBonus = traitBonus;
        this.description = description;
    }

    public ArrayList<traitBonus> getTraitBonuses(){
        return this.traitBonus;
    }

    public String toString(){
        String result = "Race : "+this.name+"\n\tDescription : "+this.description;
        for(int i=0;i<traitBonus.size();i++){
            result += "\n\tAdds "+traitBonus.get(i).getTraitBonusValue()+" to "+traitBonus.get(i).getTraitName();
        }
        return result;
    }

}
