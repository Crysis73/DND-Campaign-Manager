package edu.bsu.cs222;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

class Campaign {
    private final ArrayList<Character> characters;
    private final String campaignName;
    private String log;


    Campaign(String campaignName) {
        this.campaignName = campaignName;
        this.characters = new ArrayList<>();
    }

    void addCharacer(Character character){
        characters.add(character);
    }

    ArrayList<Character> getCharacters(){
        return this.characters;
    }

    String getCampaignName(){
        return this.campaignName;
    }

    String getLog(){
        return this.log;
    }

    void addEntryToLog(String entry){
        this.log += "\n" + entry;
    }

    void writeLogToFile(){
        try (FileWriter file = new FileWriter(this.campaignName.replace(".json","")+".txt")) {
            file.flush();
            file.write(log.replace("null",""));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void fromFileToLog(){
        try {
            this.log = new String(Files.readAllBytes(Paths.get(this.campaignName+".txt")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    ArrayList generateCombatOrder(){
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

}
