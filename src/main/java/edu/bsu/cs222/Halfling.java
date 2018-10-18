package edu.bsu.cs222;

@SuppressWarnings("ALL")
public class Halfling implements RaceInterface{

    private String raceName;
    private TraitMap raceTraits;
    private String raceDescription;

    public Halfling(){
        this.raceName = "Halfling";
        this.raceDescription = "https://www.dndbeyond.com/compendium/rules/basic-rules/races";
        TraitMap traitMap = new TraitMap();
        traitMap.setAllValues(0);
        traitMap.setValue("Dexterity", 2);
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