package edu.bsu.cs222;

import java.util.ArrayList;

public class Campaign {
    private ArrayList<Character> characters = new ArrayList<>();
    private String campaignName;


    public Campaign(String campaignName) {
        this.characters = new ArrayList<>();
        this.campaignName = campaignName;
    }

    public void addCharacer(Character character){
        characters.add(character);
    }

    public ArrayList<Character> getCharacters(){
        return this.characters;
    }

    public String toString(){
        String result ="Campaign : "+this.campaignName;
        for(int i =0;i<characters.size();i++){
            result +="\n"+characters.get(i).toString();
        }
        return result;
    }

    public String generateJsonString(){
        String result = "{\""+campaignName+"\":{" + "\"Characters\":[";
        for(int i =0;i<characters.size();i++){
            result+=characters.get(i).generateJsonString();
            if(i<characters.size()-1){
                result+=",";
            }
        }
        result += "]}}";
        return result;
    }

}
