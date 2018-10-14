package edu.bsu.cs222;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Character {
    private String name;
    //private Map<Trait,Integer> traits;
    private Trait[] traits;
    private LinkedList<Equipment> equipment;
    private Trait strength, dexterity, constitution, intelligence, wisdom, charisma;
    private Integer wealth, experiencepoints, hitPoints;
    private Race race;
    private dndClass dndClass;
    private Description description;

    public Character(String name, dndClass dndClass, Race race){
        this.name = name;
        this.dndClass = dndClass;
        this.equipment = new LinkedList<>();
        this.strength = new Trait("Strength");
        this.dexterity = new Trait("Dexterity");
        this.constitution = new Trait("Constitution");
        this.intelligence = new Trait("Intelligence");
        this.wisdom = new Trait("Wisdom");
        this.charisma = new Trait("Charisma");
        this.traits = new Trait[6];
        this.race = race;
        traits[0] = this.strength;
        traits[1] = this.dexterity;
        traits[2] = this.constitution;
        traits[3] = this.intelligence;
        traits[4] = this.wisdom;
        traits[5] = this.charisma;
        for(int i =0;i<traits.length;i++){
            if(traits[i].getName().equals(race.getTraitName())){
                Integer replacementValue = traits[i].getValue() + race.getTraitBonus();
                setTrait(traits[i],replacementValue);
            }
        }
        //this.traits = createTraitMap();
        this.experiencepoints = 0;
        this.hitPoints = constitution.getValue() + dndClass.getHitPointBonus();
        /*
        String raceName = race.getTraitName();
        Integer traitBonus = race.getTraitBonus();
        Integer newTraitValue = traitBonus + race.getTraitBonus();
        traits.replace(traits(),newTraitValue);
        */
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

    public void addEquipment(Equipment item){
        equipment.add(item);
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

    public void setTrait(Trait trait, Integer value){
        trait.setValue(value);
    }

    /*
    public Map<Trait,Integer> createTraitMap(){
        Map<Trait,Integer> traits = new HashMap<>();
        traits.put(this.strength,strength.getValue());
        traits.put(this.dexterity,dexterity.getValue());
        traits.put(this.constitution,constitution.getValue());
        traits.put(this.intelligence,intelligence.getValue());
        traits.put(this.wisdom,wisdom.getValue());
        traits.put(this.charisma,charisma.getValue());
        return traits;
    }
    */

    /*
    public void setRace(Race race){
        this.race = race;
        Trait trait = race.getTrait();
        Integer replacementValue = race.getTraitBonus() + traits.get(trait);
        traits.replace(trait,replacementValue);
    }
    */

    public String toString(){
        String result = "";
        result+="Name : " + name +
                    "\n\t Wealth : " + wealth+
                    "\n\t XP : " + experiencepoints+
                    "\n\t HP : " + hitPoints+
                "\nTraits :"+
                    "\n\tStrength : "+ this.strength.getValue()+
                    "\n\t Dexterity : "+ this.dexterity.getValue()+
                    "\n\t Constitution : "+ this.constitution.getValue()+
                    "\n\t Intelligence : "+ this.intelligence.getValue()+
                    "\n\t Wisdom : "+ this.wisdom.getValue()+
                    "\n\t Constitution : "+ this.constitution.getValue()+
                "Race :"+ race.toString() + "\n"+
                "\nEquipment : " + equipment;

        return result;

    }
}
