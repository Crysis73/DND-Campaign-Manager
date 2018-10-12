package edu.bsu.cs222;

import java.util.ArrayList;
import java.util.LinkedList;

public class CharacterSheet {
    private Race race;
    private dndClass dndClass;
    private Trait strength, dexterity, constitution, intelligence, wisdom, charisma;
    private Integer wealth, experiencepoints, hitPoints;
    private Description description;

    public CharacterSheet(){
        this.strength = new Trait("Strength");
        this.dexterity = new Trait("Dexterity");
        this.constitution = new Trait("Constitution");
        this.intelligence = new Trait("Intelligence");
        this.wisdom = new Trait("Wisdom");
        this.charisma = new Trait("Charisma");
    }

    public Trait getStrength(){
        return this.strength;
    }

    public Trait getDexterity(){
        return this.dexterity;
    }

    public Trait getConstitution(){
        return this.constitution;
    }

    public Trait getIntelligence(){
        return this.intelligence;
    }

    public Trait getWisdom(){
        return this.wisdom;
    }

    public Trait getCharisma(){
        return this.charisma;
    }

    public void setRace(Race race){
        this.race = race;
    }

    public void setTrait(Trait trait, Integer value){
        trait.setValue(value);
    }

    public void setWealth(Integer startingValue){
        this.wealth = startingValue;
    }

    public void setExperiencepoints(Integer value){
        this.experiencepoints = value;
    }

    public void setHitPoints(Integer startingValue){
        this.hitPoints = startingValue;
    }

    public void setDescription(){
        this.description = new Description();
    }

    public String toString(){
        String result ="";
        result += race.toString();
        result += dndClass.toString();
        result += strength.toString();
        result += dexterity.toString();
        result += constitution.toString();
        result += intelligence.toString();
        result += wisdom.toString();
        result += charisma.toString();
        result += "\n Wealth : " + wealth;
        result += "\n XP : " + experiencepoints;
        result += "\n HP : " + hitPoints;
        result += "\n Description : " + description.toString();
        return result;
    }



}
