package edu.bsu.cs222;

import java.util.*;

public class TraitMap {

    private final Map<String, Integer> traitMap;
    private Map<String,Integer> bonusValues;

    TraitMap(){
        Map<String, Integer> traitmap = new HashMap<>();
        ArrayList<String> traitNames = new ArrayList<>(Arrays.asList("Strength", "Dexterity", "Constitution", "Intelligence", "Wisdom", "Charisma"));
        for (String traitName : traitNames) {
            Die d6 = new Die(6);
            Integer value = d6.rollD6FourTimesDropLeast();
            traitmap.put(traitName, value);
        }
        this.traitMap = traitmap;

    }

    TraitMap mergeTraitMaps(TraitMap characterTraits, TraitMap raceTraitBonuses){
        Map<String,Integer> bonusValues = new HashMap<>();
        for(Map.Entry<String,Integer> entry : raceTraitBonuses.getTraitMap().entrySet()){
            if(entry.getValue() != 0){
                Integer value = characterTraits.getTraitMap().get(entry.getKey()) + entry.getValue();
                characterTraits.getTraitMap().replace(entry.getKey(),value);
            }
        }
        setBonusValues(bonusValues);
        return characterTraits;

    }

    public Map<String,Integer> getTraitMap(){
        return traitMap;
    }

    Integer getValue(String trait){
        return traitMap.get(trait);
    }

    public void setValue(String trait, Integer value){
        traitMap.replace(trait, value);
    }

    public void setAllValues(Integer value){
        for (Map.Entry<String, Integer> entry : traitMap.entrySet()){
            entry.setValue(value);
        }
    }

    private void setBonusValues(Map<String, Integer> bonusValues){
        this.bonusValues = bonusValues;
    }

    public Map<String,Integer> getBonusValues(){
        return this.bonusValues;
    }

    public String toString(){
        LinkedList<String> listOfTraits = new LinkedList<>();
        for (Map.Entry<String, Integer> entry : traitMap.entrySet()){
            if(entry.getValue() != 0){
                String trait = "\n"+ entry.getKey() + ": "+ entry.getValue();
                listOfTraits.add(trait);
            }
        }
        return listOfTraits.toString();
    }

}
