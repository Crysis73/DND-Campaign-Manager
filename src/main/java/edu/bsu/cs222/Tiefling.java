package edu.bsu.cs222;

public class Tiefling implements RaceInterface{

    private String raceName;
    private TraitMap raceTraits;
    private String raceDescription;

    public Tiefling(){
        this.raceName = "Tiefling";
        this.raceDescription = "https://www.dndbeyond.com/compendium/rules/basic-rules/races";
        TraitMap traitMap = new TraitMap();
        traitMap.setAllValues(0);
        traitMap.setValue("Charisma", 2);
        traitMap.setValue("Intelligence", 1);
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