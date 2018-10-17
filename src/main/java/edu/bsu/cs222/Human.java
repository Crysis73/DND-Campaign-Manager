package edu.bsu.cs222;

public class Human implements RaceInterface{

    private String raceName;
    private TraitMap raceTraits;
    private String raceDescription;

    public Human(){
        this.raceName = "Human";
        this.raceDescription = "https://www.dndbeyond.com/compendium/rules/basic-rules/races";
        TraitMap traitMap = new TraitMap();
        traitMap.setAllValues(0);
        //Humans receive +1 bonuses on all ability scores
        traitMap.setValue("Strength", 1);
        traitMap.setValue("Dexterity", 1);
        traitMap.setValue("Constitution", 1);
        traitMap.setValue("Intelligence", 1);
        traitMap.setValue("Wisdom", 1);
        traitMap.setValue("Charisma", 1);
        this.raceTraits = traitMap;
    }

    @Override
    public String getRaceDescription() {
        return raceDescription;
    }
    @Override
    public String getRaceName(){
        return raceName;
    }

    @Override
    public TraitMap getRaceTraitBonuses(){
        return raceTraits;
    }
}