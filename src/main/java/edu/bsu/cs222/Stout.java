package edu.bsu.cs222;

//This race is a subrace of Halflings
public class Stout implements RaceInterface{

    private String raceName;
    private TraitMap raceTraits;
    private String raceDescription;

    public Stout(){
        this.raceName = "Stout";
        this.raceDescription = "https://www.dndbeyond.com/compendium/rules/basic-rules/races";
        TraitMap traitMap = new TraitMap();
        traitMap.setAllValues(0);
        traitMap.setValue("Dexterity", 2);
        traitMap.setValue("Constitution", 1);
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