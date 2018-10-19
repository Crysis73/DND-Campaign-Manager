package edu.bsu.cs222;

import java.util.LinkedList;

//@SuppressWarnings("WeakerAccess")
class Character {
    private final String name;
    private final TraitMap traits;
    private final LinkedList<Equipment> equipment;
    private Integer wealth, experiencepoints, hitPoints;
    private final Race race;
    private final dndClass dndClass;
    private Description characterDescription;

    public Character(String name, dndClass dndClass, String raceName){
        this.name = name;
        this.dndClass = dndClass;
        this.equipment = new LinkedList<>();
        Race setRace = new Race(raceName);
        this.race = setRace;
        TraitMap characterTraits = new TraitMap();
        TraitMap raceBonuses = setRace.getTraitBonuses();
        characterTraits = characterTraits.mergeTraitMaps(characterTraits,raceBonuses);
        this.traits = characterTraits;
        this.experiencepoints = 0;
        this.hitPoints = traits.getValue("Constitution") + dndClass.getHitPointBonus();
    }

    void setWealth(Integer startingValue){
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

    TraitMap getTraits(){
        return traits;
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

    String generateJsonString(){
        //Add description
        //Add equipment
        return "{\"name\":\""+name+"\"," +
                "\"wealth\":\""+wealth+"\"," +
                "\"experiencepoints\":\""+experiencepoints+"\","+
                "\"hitPoints\":\""+hitPoints+"\","+
                "\"Traits\": [" +
                "{\"Strength\": \""+ this.traits.getValue("Strength")+"\"},"+
                "{\"Dexterity\": \""+ this.traits.getValue("Dexterity")+"\"},"+
                "{\"Constitution\": \""+ this.traits.getValue("Constitution")+"\"},"+
                "{\"Intelligence\": \""+ this.traits.getValue("Intelligence")+"\"},"+
                "{\"Wisdom\": \""+ this.traits.getValue("Wisdom")+"\"},"+
                "{\"Charisma\": \""+ this.traits.getValue("Charisma")+"\"}"+
                "]"+
                "}";
    }
}
