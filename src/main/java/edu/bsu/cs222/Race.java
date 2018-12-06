package edu.bsu.cs222;

import java.util.Map;

class Race {
    private final String name;
    private String description;
    private final TraitMap traitBonus;

    Race(String name, TraitMap traitBonus){
        this.name = name;
        this.traitBonus = traitBonus;
    }

    TraitMap getTraitBonuses(){
        return this.traitBonus;
    }

    public String toString(){
        StringBuilder result = new StringBuilder("Race : " + this.name + "\n\tDescription : " + this.description);

        for(Map.Entry<String,Integer> entry : traitBonus.getTraitMap().entrySet()){
            if(entry.getValue()!=0){
                result.append("\n\tAdds ").append(entry.getValue()).append(" to ").append(entry.getKey());
            }
        }
        return result.toString();
    }

    String getName(){
        return this.name;
    }

    void setDescription(String description){
        this.description = description;
    }

}
