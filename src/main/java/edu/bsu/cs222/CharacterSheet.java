package edu.bsu.cs222;

import java.util.ArrayList;
import java.util.LinkedList;

public class CharacterSheet {
    private Race race;
    private Trait strength, dexterity, constitution, intelligence, wisdom, charisma;
    private Integer wealth, xp, hp;
    private Description description;


    public void setRace(Race race){
        this.race = race;
    }

    public void setTrait(Trait name, Integer value){
        name.setValue(value);
    }
    public void setWealth(Integer startingValue){
        this.wealth = startingValue;
    }
    public void setXP(Integer value){
        this.xp = value;
    }
    public void setHp(Integer startingValue){
        this.hp = startingValue;
    }
    public void setDescription(){
        this.description = new Description();
    }


    public String toString(){
        return("Race: " + race + " , ");
    }


}
