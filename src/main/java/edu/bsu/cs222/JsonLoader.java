package edu.bsu.cs222;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonLoader {

    public Campaign fromJsontoCampaign(String filename){
        try {
            Campaign campaign = new Campaign();
            campaign.setCampaignName(filename);
            JsonParser parser = new JsonParser();
            String data = readFileAsString(filename);
            JsonElement rootElement = parser.parse(data);
            JsonObject rootObject = rootElement.getAsJsonObject();
            JsonObject jsonCampaign = rootObject.getAsJsonObject(filename.replace(".json",""));
            JsonArray characters = jsonCampaign.getAsJsonArray("Characters");
            for(int i =0;i<characters.size();i++){
                String characterName = characters.get(i).getAsJsonObject().get("name").toString().replace("\"","");
                String raceName = characters.get(i).getAsJsonObject().get("race").toString().replace("\"","");
                String dndClassName = characters.get(i).getAsJsonObject().get("dndclass").toString().replace("\"","");
                Character character = new Character(characterName,dndClassName,raceName);
                System.out.println(character);
                campaign.addCharacer(character);
            }
            System.out.println(characters.get(0));
            System.out.println(characters.get(1));
            System.out.println(jsonCampaign);
            System.out.println(rootObject);
            return campaign;

        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public String readFileAsString(String filename) throws IOException {
        String data ="";
        data = new String(Files.readAllBytes(Paths.get(filename)));
        return data;
    }
}
