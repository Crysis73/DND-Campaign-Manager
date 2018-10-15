package edu.bsu.cs222;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TraitMap {

    private Map<String, Integer> traitMap;

    TraitMap(){
        Map<String, Integer> traitmap = new HashMap<>();
        ArrayList<String> traitNames = new ArrayList<>();
        traitNames.addAll(Arrays.asList("Strength", "Dexterity", "Intelligence", "Wisdom", "Charisma"));

        for (int i=0; i<=traitNames.size();i++){
            Die d6 = new Die(6);
            Integer value = d6.rollD6FourTimesDropLeast();
            traitmap.put(traitNames.get(i),value);
        }

        this.traitMap = traitmap;
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

}
