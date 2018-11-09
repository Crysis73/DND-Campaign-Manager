package edu.bsu.cs222;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

class JsonLoader {

    @SuppressWarnings("UnusedReturnValue")
    Campaign fromJsontoCampaign(String filename){
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
                Character character = createCharacterFromJson(characters.get(i).getAsJsonObject());
                addDescriptionToCharacter(character,characters.get(i).getAsJsonObject().getAsJsonArray("Description"));
                setCharacterTraits(characters.get(i).getAsJsonObject(),character);
                setWealthXPHP(characters.get(i).getAsJsonObject(),character);
                campaign.addCharacer(character);
            }
            return campaign;
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }

    private Character createCharacterFromJson(JsonObject jsonCharacter){
        String characterName = jsonCharacter.get("name").toString().replace("\"","");
        String raceName = jsonCharacter.get("race").toString().replace("\"","");
        String dndClassName = jsonCharacter.get("dndclass").toString().replace("\"","");
        return new Character(characterName,dndClassName,raceName);

    }

    private void setCharacterTraits(JsonObject jsonCharacter, Character character){
        for(int i=0;i<character.getTraits().getTraitMap().size();i++){
            String traitName = jsonCharacter.getAsJsonArray("Traits").get(i).getAsJsonObject().keySet().toString().replace("[","").replace("]","");
            Integer traitValue = jsonCharacter.getAsJsonArray("Traits").get(i).getAsJsonObject().get(traitName).getAsInt();
            character.getTraits().setValue(traitName,traitValue);
        }
    }

    private void setWealthXPHP(JsonObject jsonCharacter, Character character){
        Integer wealth = jsonCharacter.get("wealth").getAsInt();
        Integer xp = jsonCharacter.get("experiencepoints").getAsInt();
        Integer hp = jsonCharacter.get("hitPoints").getAsInt();
        character.setExperiencepoints(xp);
        character.setWealth(wealth);
        character.setHitPoints(hp);
    }

    private void addDescriptionToCharacter(Character character, JsonArray description){
        String height = description.get(0).getAsJsonObject().get("height").toString().replace("\"","");
        String weight = description.get(1).getAsJsonObject().get("weight").toString().replace("\"","");
        String age = description.get(2).getAsJsonObject().get("age").toString().replace("\"","");
        String eyeColor = description.get(3).getAsJsonObject().get("eyeColor").toString().replace("\"","");
        String skinColor = description.get(4).getAsJsonObject().get("skinColor").toString().replace("\"","");
        String additionalFeatures = description.get(5).getAsJsonObject().get("additionalFeatures").toString().replace("\"","");
        String alignment = description.get(6).getAsJsonObject().get("alignment").toString().replace("\"","");
        String languages = description.get(7).getAsJsonObject().get("languages").toString().replace("\"","");
        String exoticLanguages = description.get(8).getAsJsonObject().get("exoticLanguages").toString().replace("\"","");
        String personalityTrait1 = description.get(9).getAsJsonObject().get("personalityTrait1").toString().replace("\"","");
        String personalityTrait2 = description.get(10).getAsJsonObject().get("personalityTrait2").toString().replace("\"","");
        String ideals = description.get(11).getAsJsonObject().get("ideals").toString().replace("\"","");
        String bonds = description.get(12).getAsJsonObject().get("bonds").toString().replace("\"","");
        String flaws = description.get(13).getAsJsonObject().get("flaws").toString().replace("\"","");
        character.getCharacterDescription().setAllValues(height,weight,age,eyeColor,skinColor,additionalFeatures,alignment,languages,exoticLanguages,personalityTrait1,personalityTrait2,ideals,bonds,flaws);
    }

    private String readFileAsString(String filename) throws IOException {
        String data;
        data = new String(Files.readAllBytes(Paths.get(filename)));
        return data;
    }
}
