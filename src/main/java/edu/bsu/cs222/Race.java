package edu.bsu.cs222;

public class Race {
    private String name;
    private Integer traitBonus; //Will need to be manually set for each race.
    private Trait trait; //Will need to be manually set for each race.
    private String description; //Will need to be manually set for each race.

    public Race(String name,Trait trait, Integer traitBonus, String description){
        this.name = name;
        this.trait = trait;
        this.traitBonus = traitBonus;
        this.description = description;
    }

    public String toString(){
        return("Race : " + name + "|\n" + description + "\n\t , Adds " + traitBonus + " to " + trait.getName() + ".");
    }

}
