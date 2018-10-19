package edu.bsu.cs222;

public class DeepGnome implements RaceInterface{

    private final String raceName;
    private final TraitMap raceTraits;
    private final String raceDescription;

    DeepGnome(){
        this.raceName = "Deep Gnome";
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