package edu.bsu.cs222;

import java.util.ArrayList;
import java.util.Collections;

class Campaign {
    private final ArrayList<Character> characters;
    private String campaignName;
    private String log;


    public Campaign() {
        this.characters = new ArrayList<>();
    }

    void addCharacer(Character character){
        characters.add(character);
    }

    public ArrayList<Character> getCharacters(){
        return this.characters;
    }

    String getCampaignName(){
        return this.campaignName;
    }

    public ArrayList generateCombatOrder(){
        Collections.sort(characters);
        return characters;
    }


    public String toString(){
        StringBuilder result = new StringBuilder("Campaign : " + this.campaignName);
        for (Character character : characters) {
            result.append("\n").append(character.toString());
        }
        return result.toString();
    }

    String generateJsonString(){
        StringBuilder resultBuilder = new StringBuilder("{\"" + campaignName + "\":{" + "\"Characters\":[");
        for(int i = 0; i<characters.size(); i++){
            resultBuilder.append(characters.get(i).generateJsonString());
            if(i<characters.size()-1){
                resultBuilder.append(",");
            }
        }
        String result = resultBuilder.toString();
        result += "]}}";
        return result;
    }

    public void setCampaignName(String campaignName){
        this.campaignName = campaignName;
    }
}
