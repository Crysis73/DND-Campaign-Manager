package edu.bsu.cs222;

import java.util.ArrayList;

public class Race {
    private String raceName;
    private ArrayList<Trait> raceTraits;
    private Integer traitBonus; //Will need to be manually set for each race.
    private String traitName; //Will need to be manually set for each race. This is the trait that the trait bonus is applied to.
    private String raceDescription; //Will need to be manually set for each race.

    /*
    Passing all arguments through the constructor because Races are static. We will need to manually create each Race that
    users are able to choose from. These Races will have preset names, traitBonuses, traits, and descriptions.
     */

    public Race(String raceName,String traitName,Integer traitBonus, String raceDescription){
        this.raceName = raceName;
        this.traitBonus = traitBonus;
        this.raceDescription = raceDescription;
        this.traitName = traitName;
    }

    /*
    public void setTrait(Trait trait){
        this.trait = trait;
    }
    */

    public String getTraitName(){
        return this.traitName;
    }

    public Integer getTraitBonus(){
        return this.traitBonus;
    }


    public String toString(){
        return("Race : " + raceName + "|\n" + raceDescription + "\n\t , Adds " + traitBonus + " to " + traitName + ".");
    }

}
