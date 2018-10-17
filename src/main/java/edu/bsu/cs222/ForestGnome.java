package edu.bsu.cs222;

public class ForestGnome implements RaceInterface{

    private String raceName;
    private TraitMap raceTraits;
    private String raceDescription;

    public ForestGnome(){
        this.raceName = "Forest Gnome";
        this.raceDescription = "https://www.dndbeyond.com/compendium/rules/basic-rules/races";
        TraitMap traitMap = new TraitMap();
        traitMap.setAllValues(0);
        traitMap.setValue("Intelligence", 2);
        traitMap.setValue("Dexterity", 1);
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