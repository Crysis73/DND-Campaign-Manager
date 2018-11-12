package edu.bsu.cs222;

import java.util.Objects;

@SuppressWarnings("unused")
class Character implements Comparable<Character> {
    private final String name;
    private TraitMap traits;
    private Integer wealth, experiencepoints, hitPoints;
    private Race race;
    private dndClass dndClass;
    private Integer initiative;
    private final Description characterDescription;

    public Character(String name, String dndClass, String raceName){
        this.name = name;
        setRace(raceName);
        setDndClass(dndClass);
        setTraits();
        rollForInitiative();
        this.experiencepoints = 0;
        this.wealth = 0;
        this.hitPoints = traits.getValue("Constitution") + this.dndClass.getHitPointBonus();
        this.characterDescription = new Description();
    }


    void setWealth(Integer startingValue){
        this.wealth = startingValue;
    }

    void setExperiencepoints(Integer value){
        this.experiencepoints = value;
    }

    void setHitPoints(Integer Value){
        this.hitPoints = Value;
    }

    private void setRace(String raceName){
        raceList races = new raceList();
        for(int i =0;i<races.getRaces().length;i++){
            if(races.getRaces()[i].getName().equals(raceName)){
                this.race = races.getRaces()[i];
            }
        }
    }

    private void setDndClass(String dndClass){
        dndClassList dndClasses = new dndClassList();
        for(int i =0;i<dndClasses.getDndClasses().length;i++){
            if(dndClass.equals(dndClasses.getDndClasses()[i].getName())){
                this.dndClass = dndClasses.getDndClasses()[i];
            }
        }
    }

    private void setTraits(){
        TraitMap characterTraits = new TraitMap();
        TraitMap raceBonuses = race.getTraitBonuses();
        characterTraits = characterTraits.mergeTraitMaps(characterTraits,raceBonuses);

        this.traits = characterTraits;
    }

    public void setInitiative(Integer initiative){
        this.initiative = initiative;
    }

    Description getCharacterDescription(){
        return this.characterDescription;
    }

    String getName(){
        return this.name;
    }

    public String getdndClass(){
        return this.dndClass.getName();
    }

    public String getRace(){
        return this.race.getName();
    }

    TraitMap getTraits(){
        return traits;
    }

    private void rollForInitiative(){
        Die d20 = new Die(20);
        this.initiative = d20.rollDie() + traits.getValue("Dexterity");
    }

    Integer getInitiative(){
        return this.initiative;
    }

    @Override
    public int compareTo(Character character){
        return(this.getInitiative() < character.getInitiative() ?-1 :
                (Objects.equals(this.getInitiative(), character.getInitiative()) ? 0:1));
    }

    public String toString(){

        String result = "";
        result+="Name : " + name +
                "\n\tWealth : " + wealth+
                "\n\tXP : " + experiencepoints+
                "\n\tHP : " + hitPoints+
                "\nTraits :"+
                "\n\tStrength : "+ this.traits.getValue("Strength")+
                "\n\tDexterity : "+ this.traits.getValue("Dexterity")+
                "\n\tConstitution : "+ this.traits.getValue("Constitution")+
                "\n\tIntelligence : "+ this.traits.getValue("Intelligence")+
                "\n\tWisdom : "+ this.traits.getValue("Wisdom")+
                "\n\tCharisma : "+ this.traits.getValue("Charisma")+
                "\nDescription: " +characterDescription+
                "\n"+ race.toString() + "\n"+
                dndClass.toString() + "\n";
        return result;

    }

    String generateJsonString(){
        //Add equipment
        return "{\"name\":\""+name+"\"," +
                "\"race\":\""+ this.race.getName()+"\","+
                "\"dndclass\":\""+ this.dndClass.getName()+"\","+
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
                "],"+
                "\"Description\": ["+
                getCharacterDescription().generateJsonString()+
                "]"+
                "}";
    }

}
