package edu.bsu.cs222;

import java.util.*;

public class TraitMap {

    private Map<String, Integer> traitMap;

    TraitMap(){
        Map<String, Integer> traitmap = new HashMap<>();
        ArrayList<String> traitNames = new ArrayList<>();
        traitNames.addAll(Arrays.asList("Strength", "Dexterity", "Intelligence", "Wisdom", "Charisma", "Constitution"));

        for (int i=0; i<=traitNames.size()-1;i++){
            Die d6 = new Die(6);
            Integer value = d6.rollD6FourTimesDropLeast();
            traitmap.put(traitNames.get(i),value);
        }

        this.traitMap = traitmap;
    }

    public TraitMap mergeTraitMaps(TraitMap traitMap1){
        traitMap.forEach((k, v) -> traitMap1.getTraitMap().merge(k, v, Integer::sum));
        return traitMap1;
    }

    public Map<String,Integer> getTraitMap(){
        return traitMap;
    }

    public Integer getValue(String trait){
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