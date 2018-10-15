package edu.bsu.cs222;

import java.util.LinkedList;

public class Character {
    private String name;
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
        this.race = race;
        initializeTraits();
        initializeTraitBonuses();
        this.experiencepoints = 0;
        this.hitPoints = constitution.getValue() + dndClass.getHitPointBonus();
    }

    public void initializeTraits(){
        this.strength = new Trait("Strength");
        this.dexterity = new Trait("Dexterity");
        this.constitution = new Trait("Constitution");
        this.intelligence = new Trait("Intelligence");
        this.wisdom = new Trait("Wisdom");
        this.charisma = new Trait("Charisma");
        this.traits = new Trait[6];
        traits[0] = this.strength;
        traits[1] = this.dexterity;
        traits[2] = this.constitution;
        traits[3] = this.intelligence;
        traits[4] = this.wisdom;
        traits[5] = this.charisma;

    }

    public void initializeTraitBonuses(){
        for(int i =0;i<race.getTraitBonuses().size();i++){
            String traitName = race.getTraitBonuses().get(i).getTraitName();
            for(int j=0;j<traits.length;j++){
                String secondtraitName = traits[j].getName();
                if(traitName.equals(secondtraitName)){
                    Integer replacementValue = race.getTraitBonuses().get(i).getTraitBonusValue() + traits[j].getValue();
                    traits[j].setValue(replacementValue);
                }
            }
        }
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

    public String toString(){
        String result = "";
        result+="Name : " + name +
                    "\n\tWealth : " + wealth+
                    "\n\tXP : " + experiencepoints+
                    "\n\tHP : " + hitPoints+
                "\nTraits :"+
                    "\n\tStrength : "+ this.strength.getValue()+
                    "\n\tDexterity : "+ this.dexterity.getValue()+
                    "\n\tConstitution : "+ this.constitution.getValue()+
                    "\n\tIntelligence : "+ this.intelligence.getValue()+
                    "\n\tWisdom : "+ this.wisdom.getValue()+
                    "\n\tConstitution : "+ this.constitution.getValue()+
                "\n"+ race.toString() + "\n"+
                dndClass.toString() + "\n"+
                "\nEquipment : " + equipment;

        return result;

    }
}
