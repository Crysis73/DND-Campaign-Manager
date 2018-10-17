package edu.bsu.cs222;

public class MountainDwarf implements RaceInterface {

    private String raceName;
    private TraitMap raceTraits;
    private String raceDescription;

    public MountainDwarf() {
        this.raceName = "Mountain Dwarf";
        this.raceDescription = "https://www.dndbeyond.com/compendium/rules/basic-rules/races";
        TraitMap traitMap = new TraitMap();
        traitMap.setAllValues(0);
        traitMap.setValue("Constitution", 2);
        traitMap.setValue("Strength", 2);
        this.raceTraits = traitMap;
    }

    @Override
    public String getRaceDescription() {
        return raceDescription;
    }

    @Override
    public String getRaceName() {
        return raceName;
    }

    @Override
    public TraitMap getRaceTraitBonuses() {
        return raceTraits;
    }
}