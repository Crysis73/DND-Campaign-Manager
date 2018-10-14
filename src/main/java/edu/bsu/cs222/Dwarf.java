package edu.bsu.cs222;

import java.util.ArrayList;

public class Dwarf implements RaceInterface{

    private String raceName;
    private ArrayList<Trait> raceTraits;
    private String raceDescription;

    public Dwarf(){
        this.raceName = "Dwarf";
        this.raceDescription = "https://www.dndbeyond.com/compendium/rules/basic-rules/races";
        ArrayList<Trait> raceTraits = new ArrayList<>();
        Trait constitutionBonus = new Trait("Constitution");
        constitutionBonus.setValue(2);
        raceTraits.add(constitutionBonus);
        this.raceTraits = raceTraits;
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
    public ArrayList<Trait> getRaceTraits(){
        return raceTraits;
    }
}
