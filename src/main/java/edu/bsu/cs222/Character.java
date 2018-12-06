package edu.bsu.cs222;

import java.util.Objects;

class Character implements Comparable<Character> {
    private final String name;
    private TraitMap traits;
    private Integer wealth, experiencepoints, currentHitPoints, maxHitPoints;
    private Level level,nextLevel;
    private Race race;
    private dndClass dndClass;
    private Integer initiative;
    private boolean levelUp;
    private final Description characterDescription;

    public Character(String name, String dndClass, String raceName){
        this.name = name;
        setRace(raceName);
        setDndClass(dndClass);
        setTraits();
        rollForInitiative();
        this.experiencepoints = 0;
        determineCharacterLevel();
        this.wealth = 0;
        Integer abilityModifier = ( traits.getValue("Constitution") -10 ) / 2;
        this.maxHitPoints = abilityModifier + this.dndClass.getHitDice();
        this.currentHitPoints = maxHitPoints;
        this.characterDescription = new Description();
    }

    void setCurrentHitPoints(Integer value){
        this.currentHitPoints = value;
    }

    Integer getWealth(){
        return this.wealth;
    }

    void setWealth(Integer startingValue){
        this.wealth = startingValue;
    }

    void setExperiencepoints(Integer value){
        this.experiencepoints = value;
        determineCharacterLevel();
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

    void setInitiative(Integer initiative){
        this.initiative = initiative;
    }

    Description getCharacterDescription(){
        return this.characterDescription;
    }

    String getName(){
        return this.name;
    }

    String getdndClassName(){
        return this.dndClass.getName();
    }

    String getRaceName(){
        return this.race.getName();
    }

    Level getLevel(){
        return  this.level;
    }

    Level getNextLevel(){
        return this.nextLevel;
    }

    TraitMap getTraits(){
        return traits;
    }

    private void rollForInitiative(){
        Die d20 = new Die(20);
        Integer traitModifier = (traits.getValue("Dexterity")-10)/2;
        this.initiative = d20.rollDie() + traitModifier;
    }

    Integer getInitiative(){
        return this.initiative;
    }

    Integer getMaxHitPoints(){
        return this.maxHitPoints;
    }

    Integer getCurrentHitPoints(){
        return this.currentHitPoints;
    }

    Integer getExperiencepoints(){
        return this.experiencepoints;
    }

    void setMaxHitPoints(Integer value){
        this.maxHitPoints = value;
    }

    void incrementCurrentHP(){
        this.currentHitPoints +=1;
    }

    void decrementCurrentHP(){
        this.currentHitPoints -=1;
    }

    private void updateHPFromLevel(){
        Die updateRoll = new Die(dndClass.getHitDice());
        this.maxHitPoints += updateRoll.rollDie();
        this.currentHitPoints = maxHitPoints;
    }

    private void determineCharacterLevel(){
        LevelList levels = new LevelList();
        for(int i=0;i<levels.getLevels().size();i++){
            if(i!=levels.getLevels().size()-1){
                Integer lowerBound = levels.getLevels().get(i).getLevelUpAt();
                Integer upperBound = levels.getLevels().get(i+1).getLevelUpAt();
                if(lowerBound<=experiencepoints && experiencepoints<upperBound){
                    if(hasLeveledUp(this.level, levels.getLevels().get(i))){
                        updateHPFromLevel();
                    }
                    this.level = levels.getLevels().get(i);
                    this.nextLevel = levels.getLevels().get(i+1);
                    break;
                }
            }else{
                updateHPFromLevel();
                this.level = levels.getLevels().get(i);
                this.nextLevel = levels.getLevels().get(i);
            }
        }
    }

    private boolean hasLeveledUp(Level previousLevel, Level currentLevel){
        if(previousLevel!=null) {
            if (previousLevel != currentLevel && previousLevel.getCurrentLevel() < currentLevel.getCurrentLevel()) {
                this.levelUp = true;
                return true;
            } else {
                this.levelUp = false;
                return false;
            }
        }
        this.levelUp = false;
        return false;
    }

    void setLevel(Integer currentLevel, Integer desiredLevel){
        for(int i=0;i<desiredLevel-currentLevel;i++){
            updateHPFromLevel();
        }
        LevelList levels = new LevelList();
        for(int i=0;i<levels.getLevels().size();i++){
            if(levels.getLevels().get(i).getCurrentLevel().equals(desiredLevel)){
                this.level = levels.getLevels().get(i);
                this.experiencepoints = level.getLevelUpAt();
                if(desiredLevel == 20) {
                    break;
                }
                this.nextLevel = levels.getLevels().get(i+1);
                break;
            }
        }
    }

    boolean getHasLeveledUp(){
        return this.levelUp;
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
                "\n\tMax HP : " + maxHitPoints+
                "\n\tCurrent HP : " + currentHitPoints+
                "\n\tLevel : "+ level.getCurrentLevel()+
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
        return "{\"name\":\""+name+"\"," +
                "\"race\":\""+ this.race.getName()+"\","+
                "\"dndclass\":\""+ this.dndClass.getName()+"\","+
                "\"wealth\":\""+wealth+"\"," +
                "\"experiencepoints\":\""+experiencepoints+"\","+
                "\"maxHitPoints\":\""+maxHitPoints+"\","+
                "\"currentHitPoints\":\""+currentHitPoints+"\","+
                "\"initiative\":\""+initiative+"\","+
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
