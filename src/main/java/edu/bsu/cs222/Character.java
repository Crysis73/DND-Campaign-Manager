package edu.bsu.cs222;

import java.util.LinkedList;

public class Character {
    private String name;
    private TraitMap traits;
    private LinkedList<Equipment> equipment;
    private Integer wealth, experiencepoints, hitPoints;
    private Race race;
    private dndClass dndClass;
    private Description characterDescription;

    public Character(String name, dndClass dndClass, String race){
        this.name = name;
        this.dndClass = dndClass;
        this.equipment = new LinkedList<>();
        this.race = new Race(race);
        this.traits = new TraitMap();
        this.experiencepoints = 0;
        this.hitPoints = traits.getValue("Constitution") + dndClass.getHitPointBonus();
    }


    public void setWealth(Integer startingValue){
        this.wealth = startingValue;
    }

    public void setExperiencepoints(Integer value){
        this.experiencepoints = value;
    }

    public void setHitPoints(Integer Value){
        this.hitPoints = Value;
    }

    public void addEquipment(Equipment item){
        equipment.add(item);
    }


    public TraitMap getTraits(){
        return this.traits;
    }


    public String toString(){
        String result = "";
        result+="Name : " + name +
                    "\n\tWealth : " + wealth+
                    "\n\tXP : " + experiencepoints+
                    "\n\tHP : " + hitPoints+
                "\nDescription: " +characterDescription+
                "\nTraits :"+
                    "\n\tStrength : "+ this.traits.getValue("Strength")+
                    "\n\tDexterity : "+ this.traits.getValue("Dexterity")+
                    "\n\tConstitution : "+ this.traits.getValue("Constitution")+
                    "\n\tIntelligence : "+ this.traits.getValue("Intelligence")+
                    "\n\tWisdom : "+ this.traits.getValue("Wisdom")+
                    "\n\tCharisma : "+ this.traits.getValue("Charisma")+
                "\n"+ race.toString() + "\n"+
                dndClass.toString() + "\n"+
                "\nEquipment : " + equipment;

        return result;

    }
}
